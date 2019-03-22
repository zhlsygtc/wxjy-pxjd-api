package com.insigma.mvc.serviceimp.appraisal.JDYW_002_007;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.insigma.common.listener.AppConfig;
import com.insigma.common.util.FileUtil;
import com.insigma.common.util.StringUtil;
import com.insigma.dto.AjaxReturnMsg;
import com.insigma.enums.COMAAE100Enum;
import com.insigma.enums.HC73CAC022Enum;
import com.insigma.enums.HC75CAC007Enum;
import com.insigma.enums.HC75CAC009Enum;
import com.insigma.enums.HC75EAE052Enum;
import com.insigma.mvc.MvcHelper;
import com.insigma.mvc.dao.appraisal.JDYW_002_007.ApiInvigilatorManageMapper;
import com.insigma.mvc.dao.common.fileupload.ApiFileUploadMapper;
import com.insigma.mvc.model.Param;
import com.insigma.mvc.model.SFileRecord;
import com.insigma.mvc.model.appraisal.Hc73;
import com.insigma.mvc.model.appraisal.Hc75;
import com.insigma.mvc.model.train.Hb68;
import com.insigma.mvc.model.train.Hc74;
import com.insigma.mvc.service.appraisal.JDYW_002_007.ApiInvigilatorManageService;
import com.insigma.mvc.service.common.fileupload.ApiFileUploadService;

@Service
@Transactional(rollbackFor=Exception.class)
public class ApiInvigilatorManageServiceImpl extends MvcHelper implements ApiInvigilatorManageService{

	@Resource
	private ApiInvigilatorManageMapper apiInvigilatorManageMapper;

	@Resource
	private ApiFileUploadService fileLoadService;
	
	@Resource
    private ApiFileUploadMapper apiFileUploadMapper;
	
	//获取相应机构的监考员
	@Override
	public PageInfo<Hc73> getInvigilatorList(Hc73 hc73) {
		PageHelper.offsetPage(hc73.getOffset(), hc73.getLimit());
		if(StringUtils.isNotEmpty(hc73.getAac004())){
			hc73.setA_aac004(hc73.getAac004().split(","));
		}
		if(StringUtils.isNotEmpty(hc73.getAac011())){
			hc73.setA_aac011(hc73.getAac011().split(","));
		}
		if(StringUtils.isNotEmpty(hc73.getAca111())){
			hc73.setA_aca111(hc73.getAca111().split(","));
		}
		// 查询有效数据
		hc73.setAae100(COMAAE100Enum.DATA_EFFECTIVE.getCode());
		// 是监考员
		hc73.setCac023(HC73CAC022Enum.YESANDNO_YES.getCode());
		List<Hc73> list=apiInvigilatorManageMapper.getInvigilatorList(hc73);
		PageInfo<Hc73> pageinfo = new PageInfo<>(list);
		return pageinfo;
		
	}

	//根据监考员内码获取监考员信息
	@Override
	public AjaxReturnMsg getInvigilatorById(String id, String aab001) {
		Hc73 hc73 = apiInvigilatorManageMapper.getInvigilatorById(id, aab001, COMAAE100Enum.DATA_EFFECTIVE.getCode(), HC73CAC022Enum.YESANDNO_YES.getCode());
		if(StringUtil.isNotEmpty(hc73.getPhoto_url())){
			//将图片域名拼接上去
			String fileModule = AppConfig.getProperties("fileModule");
			hc73.setPhoto_url(fileModule+hc73.getPhoto_url());
		}
		return this.success(hc73);
	}

	//校验身份证
	@Override
	public AjaxReturnMsg<String> checkAac002(Hc73 hc73) {
		List<Hc73> list=apiInvigilatorManageMapper.checkAac002(hc73.getAac002(), COMAAE100Enum.DATA_EFFECTIVE.getCode());
		if(list.size()>0){
			return this.error("此身份证已在监考员库");
		}else{
			return this.success("身份证可入库");
		}
	
	}

	//保存监考员信息
	@Override
	public AjaxReturnMsg saveData(Hc73 hc73) {
		hc73.setAab001(hc73.getBaseinfoid());
		hc73.setAae011(hc73.getUserid());
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String dataS = hc73.getAac002().substring(6, 14);
		Date aac006 = null;
		try {
			aac006 = sdf.parse(dataS);
		} catch (ParseException e) {
			return this.error("时间转换出错");
		}
		hc73.setAac006(aac006);
		// 监考员新增操作
		if(StringUtils.isBlank(hc73.getChc075())) {
			//判断此身份证是否已经在考评人员库
			List<Hc73> list=apiInvigilatorManageMapper.checkAac002(hc73.getAac002(), COMAAE100Enum.DATA_EFFECTIVE.getCode());
			if(list != null && list.size()>0){
				// 该人员基本信息已存在 仅新增聘用关系
				hc73.setChc073(list.get(0).getChc073());
				// 校验参数合法性
				if (HC75CAC007Enum.fromCode(hc73.getCac007()) == null || HC75CAC009Enum.fromCode(hc73.getCac009()) == null) {
					return this.error("人员身份、聘任情况参数为空或参数不合法");
				}
				// 查询是否存在聘任关系 存在则放弃新增
				Hc73 checkHc73 = new Hc73();
				checkHc73.setAab001(hc73.getAab001());
				checkHc73.setAae100(COMAAE100Enum.DATA_EFFECTIVE.getCode());
				checkHc73.setCac023(HC73CAC022Enum.YESANDNO_YES.getCode());
				checkHc73.setAac002(hc73.getAac002());
				Integer n = apiInvigilatorManageMapper.getCheckAppointmentPresence(checkHc73);
				if (n != null && n > 0) {
					return this.error("该人员已存在与鉴定所站聘任关系，请勿重复新增");
				}
				// 存在该人员基本信息 但未获得工作人员身份类型 “监考员” 添加其监考员身份
//				checkHc73 = new Hc73();
//				checkHc73.setAac002(hc73.getAac002());
//				checkHc73.setAae100(COMAAE100Enum.DATA_EFFECTIVE.getCode());
//				checkHc73.setCac023(HC73CAC022Enum.YESANDNO_YES.getCode());
//				checkHc73.setAab001(hc73.getAab001());
				// 赋予用户监考员身份
//				int num = apiInvigilatorManageMapper.updatePerponIdentity(checkHc73);
//				if (num != 1) {
//					return this.error("信息保存失败，请重试");
//				}
//				return this.success("新增监考员信息成功，请继续填写资质信息",hc73.getChc073());
			} else {
				// 该人员基本信息不存在 新增基本信息
				String str = hc73.getAac002().substring(16, 17);
				int i = Integer.parseInt(str) % 2;
				if(i == 0) {
					hc73.setAac004("2");
				}else{
					hc73.setAac004("1");
				}
				
				hc73.setAae100(COMAAE100Enum.DATA_EFFECTIVE.getCode());
				hc73.setAae036(new Date());
				// 是监考员
//				hc73.setCac023(HC73CAC022Enum.YESANDNO_YES.getCode());
				int num = apiInvigilatorManageMapper.addInvigilator(hc73);
				if(num != 1) {
					return this.error("保存基本信息失败");
				}
			}
			// 无论是否存在 都新增鉴定所与人员聘任关系
			Hc75 hc75 = new Hc75();
			hc75.setChc073(hc73.getChc073()); // 考评人员内码
			hc75.setCac007(hc73.getCac007()); //人员身份代码
			hc75.setCac009(hc73.getCac009()); //人员聘任情况代码 
			hc75.setAab001(hc73.getBaseinfoid()); //鉴定所站内码
			hc75.setAae100(COMAAE100Enum.DATA_EFFECTIVE.getCode()); // 信息有效代码
			hc75.setAae011(hc73.getUserid()); //经办人
			hc75.setEae052(HC75EAE052Enum.AUDIT_PASS.getCode()); //审核标志代码(通过后台新增的考评人员 默认通过审核 监考员不需要审核)
			hc75.setCac023(HC73CAC022Enum.YESANDNO_YES.getCode());// 任聘关系 监考员
			// 新增聘任关系
			int num2 = apiInvigilatorManageMapper.insertAppointment(hc75);
			if(num2 != 1) {
				return this.error("保存聘任关系信息失败");
			}
			return this.success("新增监考员信息成功",hc73.getChc073());
		}else{
			// 校验参数合法性
			if (HC75CAC007Enum.fromCode(hc73.getCac007()) == null || HC75CAC009Enum.fromCode(hc73.getCac009()) == null) {
				return this.error("人员身份、聘任情况参数为空或参数不合法");
			}
			// 修改基本信息
			int num = apiInvigilatorManageMapper.updateInvigilator(hc73);
			if(num != 1) {
				return this.error("保存失败");
			}
			// 修改监考员聘任关系
			Hc75 hc75 = new Hc75();
			hc75.setChc075(hc73.getChc075());
			hc75.setChc073(hc73.getChc073()); // 考评人员内码
			hc75.setCac007(hc73.getCac007()); //人员身份代码
			hc75.setCac009(hc73.getCac009()); //人员聘任情况代码 
			hc75.setAab001(hc73.getBaseinfoid()); //鉴定所站内码
			hc75.setAae100(COMAAE100Enum.DATA_EFFECTIVE.getCode()); // 信息有效代码
			hc75.setAae011(hc73.getUserid()); //经办人
			hc75.setCac023(HC73CAC022Enum.YESANDNO_YES.getCode()); //监考员
			//只要是修改就需要审核
			hc75.setEae052(HC75EAE052Enum.WAIT_AUDIT.getCode()); //审核标志代码(通过后台新增的考评人员 默认通过审核)
			int num2 = apiInvigilatorManageMapper.updateAppointment(hc75);
			if(num2 != 1) {
				return this.error("保存聘任关系信息失败");
			}
			return this.success("保存监考员信息成功",hc73.getChc073());
		}
	}

	//删除监考员信息(因为监考员基本信息共用关系 删除操作不删除基本信息 只解除雇佣关系)
	@Override
	public AjaxReturnMsg<String> deleteInvigilator(Hc73 hc73) {
		hc73 = apiInvigilatorManageMapper.getInvigilatorById(hc73.getChc073(), hc73.getAab001(), COMAAE100Enum.DATA_EFFECTIVE.getCode(), HC73CAC022Enum.YESANDNO_YES.getCode());
//		if(hc73.getBus_uuid()!=null) {
//			deleteFile(hc73.getBus_uuid());
//		}
		// 解除聘任关系
		int deletenum = apiInvigilatorManageMapper.deleteEmployment(hc73.getChc073(), hc73.getAab001(), COMAAE100Enum.DATA_INVALID.getCode(), HC73CAC022Enum.YESANDNO_YES.getCode());
		if(deletenum == 1) {
			//资质删除
//			String[] ids = {hc73.getChc073()};
//			deletenum = apiInvigilatorManageMapper.deleteQualifications(ids);
			
			return this.success("删除成功");
		}else{
			return this.error("删除失败");
		}
	}

	//批量删除监考员信息
	@Override
	public AjaxReturnMsg batDelete(Hc73 hc73) {
		hc73.setA_chb061(hc73.getSelectnodes().split(","));
//		List<Hc73> list=apiInvigilatorManageMapper.getInvigilatorList(hc73);
//		for (Hc73 h:list) {
//			if(h.getBus_uuid()!=null) {
//				deleteFile(h.getBus_uuid());
//			}
//		}
		// 解除聘任关系
		Map<String, Object> hc73Map = new HashMap();
		hc73Map.put("aab001", hc73.getAab001());
		hc73Map.put("aae100", COMAAE100Enum.DATA_INVALID.getCode());
		hc73Map.put("A_chb061", hc73.getA_chb061());
		hc73Map.put("cac022", HC73CAC022Enum.YESANDNO_YES.getCode());
		int batdeletenum=apiInvigilatorManageMapper.batDeleteEmployment(hc73Map);
		if(batdeletenum==hc73.getA_chb061().length){
//			apiInvigilatorManageMapper.deleteQualifications(hc73.getA_chb061());
			return this.success("批量删除成功");
		}else{
			return this.success("批量删除成功,但存在失败的数据,请检查");
		}
	}

	
	//获取监考员资质信息列表
	@Override
	public PageInfo<Hc74> getQualificationList(Hc74 hc74) {
		PageHelper.offsetPage(0, 200);
		List<Hc74> list=apiInvigilatorManageMapper.getQualificationList(hc74.getChb061());
		PageInfo<Hc74> pageinfo = new PageInfo<Hc74>(list);
		return pageinfo;
	}
	
	//保存监考员资质信息
	@Override
	public AjaxReturnMsg saveQualification(Hc74 hc74) {
		
		List<Hc74> list = apiInvigilatorManageMapper.checkQualification(hc74);
		if(list.size()>0) {
			return this.error("此监考员已拥有该资质，无法重复保存");
		}
		
		hc74.setAae011(hc74.getUserid());
		if("".equals(hc74.getChc074())) {
			hc74.setAae100("1");
			hc74.setAae036(new Date());
			int num = apiInvigilatorManageMapper.saveQualification(hc74);
			if(num == 1) {
				return this.success("保存成功", hc74.getChc074());
			}else{
				return this.error("保存失败");
			}
		}else{
			int num = apiInvigilatorManageMapper.updateQualification(hc74);
			if(num == 1) {
				return this.success("保存成功", hc74.getChc074());
			}else{
				return this.error("保存失败");
			}
		}
	}

	//删除监考员资质信息
	@Override
	public AjaxReturnMsg<String> deleteQualification(String chc074) {
		Hc74 hc74 = apiInvigilatorManageMapper.getQualification(chc074);
		if(hc74.getBus_uuid()!=null) {
			deleteFile(hc74.getBus_uuid());
		}
		int deletenum = apiInvigilatorManageMapper.deleteQualification(chc074);
		if(deletenum == 1) {
			return this.success("删除成功");
		}else{
			return this.error("删除失败");
		}
	}

	//根据资质内码获取资质信息
	@Override
	public AjaxReturnMsg getQualification(String chc074) {
		Hc74 hc74 = apiInvigilatorManageMapper.getQualification(chc074);
		if(StringUtil.isNotEmpty(hc74.getPhoto_url())){
			//将图片域名拼接上去
			String fileModule = AppConfig.getProperties("fileModule");
            hc74.setPhoto_url(fileModule + hc74.getPhoto_url());
		}
		return this.success(hc74);
	}

	//检验此监考员是否已有此资质
	@Override
	public AjaxReturnMsg checkQualification(Hc74 hc74) {
		List<Hc74> list=apiInvigilatorManageMapper.checkQualification(hc74);
		if(list.size()>0){
			return this.error("此监考员已有该资质");
		}else{
			return this.success("资质可入库");
		}
	}

	//根据监考员内码获取监考员信息中文
	@Override
	public AjaxReturnMsg getInvigilatorGBKById(String chb061, String aab001) {
		Hc73 hc73 = apiInvigilatorManageMapper.getInvigilatorGBKById(chb061, aab001, COMAAE100Enum.DATA_EFFECTIVE.getCode(), HC73CAC022Enum.YESANDNO_YES.getCode());
		if(StringUtil.isNotEmpty(hc73.getPhoto_url())){
			//将图片域名拼接上去
			String fileModule = AppConfig.getProperties("fileModule");
			hc73.setPhoto_url(fileModule+hc73.getPhoto_url());
		}
		return this.success(hc73);
	}

	//根据监考员内码获取监考员信息中文
	@Override
	public AjaxReturnMsg getIdcardInfo(String aac002) {
		Hc73 hc73 = apiInvigilatorManageMapper.getIdcardInfo(aac002, COMAAE100Enum.DATA_EFFECTIVE.getCode());
		if(StringUtil.isNotEmpty(hc73.getPhoto_url())){
			//将图片域名拼接上去
			String fileModule = AppConfig.getProperties("fileModule");
			hc73.setPhoto_url(fileModule+hc73.getPhoto_url());
		}
		return this.success(hc73);
	}

	//上传监考员头像
	@Override
	public AjaxReturnMsg uploadLogo(String userid, String file_bus_id, String file_name, MultipartFile multipartFile) {
		try {
			//保存图片到文件服务器，同时保存图片记录
			SFileRecord sFileRecord = fileLoadService.uploadFile(multipartFile, Param.TRAIN_GROUP_TEACHER_LOGO, file_name,
					file_bus_id);
			return this.success(sFileRecord);
		} catch (Exception e) {
			return this.error(e.getMessage());
		}
	}

    //上传培训机构监考员资质图片
    @Override
    public AjaxReturnMsg uploadImage(String userid, String file_bus_id, String file_name, MultipartFile multipartFile) {
        try {
            //保存图片到文件服务器，同时保存图片记录
            SFileRecord sFileRecord = fileLoadService.uploadFile(multipartFile, Param.TRAIN_GROUP_TEACHER_QUALIFICATION_IMAGE, file_name, file_bus_id);
            return this.success(sFileRecord);
        } catch (Exception e) {
            return this.error(e.getMessage());
        }
    }

	/**
     * 删除文件记录和真实文件
     *
     * @param bus_uuid 业务上传文件内码
     */
	public AjaxReturnMsg deleteFile(String bus_uuid) {
		SFileRecord sFileRecord = apiFileUploadMapper.getBusFileRecordByBusId(bus_uuid);
		if (sFileRecord == null) {
            return this.error("文件记录不存在");
        }

        //删除文件业务记录
        apiFileUploadMapper.deleteFileByBusUuid(bus_uuid);
        //删除文件记录
        apiFileUploadMapper.deleteFileByFileUuid(sFileRecord.getFile_uuid());
        //删除真实文件
        try {
			FileUtil.delFileOnExist(sFileRecord.getFile_path());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	//查询监考员教学信息
	@Override
	public PageInfo<Hb68> getInvigilatorClasses(Hc73 hc73) {
		PageHelper.offsetPage(hc73.getOffset(), hc73.getLimit());
		List<Hb68> list=apiInvigilatorManageMapper.getInvigilatorClasses(hc73);
		PageInfo<Hb68> pageinfo = new PageInfo<Hb68>(list);
		return pageinfo;
	}
 	
}
