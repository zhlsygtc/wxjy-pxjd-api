package com.insigma.mvc.serviceimp.train.PXYW_001_013;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.insigma.common.listener.AppConfig;
import com.insigma.common.util.FileUtil;
import com.insigma.common.util.StringUtil;
import com.insigma.dto.AjaxReturnMsg;
import com.insigma.mvc.MvcHelper;
import com.insigma.mvc.dao.common.fileupload.ApiFileUploadMapper;
import com.insigma.mvc.dao.person.ApiRegistMapper;
import com.insigma.mvc.dao.train.PXYW_001_013.ApiHeadTeacherMapper;
import com.insigma.mvc.model.Param;
import com.insigma.mvc.model.SFileRecord;
import com.insigma.mvc.model.train.Hb57;
import com.insigma.mvc.model.train.Hb68;
import com.insigma.mvc.service.common.fileupload.ApiFileUploadService;
import com.insigma.mvc.service.train.PXYW_001_013.ApiHeadTeacherService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Service
@Transactional(rollbackFor=Exception.class)
public class ApiHeadTeacherServiceImpl extends MvcHelper implements ApiHeadTeacherService {

	@Resource
	private ApiHeadTeacherMapper apiHeadTeacherMapper;

	@Resource
	private ApiRegistMapper apiRegistMapper;
	
	@Resource
	private ApiFileUploadService fileLoadService;
	
	@Resource
    private ApiFileUploadMapper apiFileUploadMapper;
	
	//获取班主任信息列表
	@Override
	public PageInfo<Hb57> getHeadTeacherList(Hb57 hb57) {
		PageHelper.offsetPage(hb57.getOffset(), hb57.getLimit());
		List<Hb57> list=apiHeadTeacherMapper.getHeadTeacherList(hb57);
		PageInfo<Hb57> pageinfo = new PageInfo<>(list);
		return pageinfo;
	}

	//根据id获取班主任信息
	@Override
	public AjaxReturnMsg getHeadTeacherById(String chb059) {
		Hb57 hb57 = apiHeadTeacherMapper.getHeadTeacherById(chb059);
		if(StringUtil.isNotEmpty(hb57.getPhoto_url())){
			//将图片域名拼接上去
			String fileModule = AppConfig.getProperties("fileModule");
			hb57.setPhoto_url(fileModule+hb57.getPhoto_url());
		}
		return this.success(hb57);
	}

	//检验身份证是否已在班主任库
	@Override
	public AjaxReturnMsg checkAac002(Hb57 hb57) {
		int count = apiHeadTeacherMapper.checkAac002(hb57);
		if(count > 0) {
			return this.error("此身份证已在班主任库");
		}else{
			return this.success("身份证可入库");
		}
	
	}

	//保存班主任信息
	@Override
	public AjaxReturnMsg saveHeadTeacher(Hb57 hb57) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		if("".equals(hb57.getChb057())) {
			//判断此身份证是否已经在班主任库
			//int count = apiHeadTeacherMapper.checkAac002(hb57);
			//if(count > 0){
			//	return this.error("此身份证已在班主任库");
			//}
			String dataS = hb57.getAac002().substring(6, 14);
			Date aac006 = null;
			try {
				aac006 = sdf.parse(dataS);
			} catch (ParseException e) {
				return this.error("时间转换出错");
			}
			hb57.setAac006(aac006);
			String str = hb57.getAac002().substring(16, 17);
			int i = Integer.parseInt(str) % 2;
			if(i == 0) {
				hb57.setAac004("2");
			}else{
				hb57.setAac004("1");
			}
			hb57.setAae100("1");
			hb57.setAae036(new Date());
			int num = apiHeadTeacherMapper.addHeadTeacher(hb57);
			if(num==1) {
				return this.success("保存成功", hb57.getChb057());
			}else{
				return this.error("保存失败");
			}
		}else{
			int num = apiHeadTeacherMapper.updHeadTeacher(hb57);
			if(num==1) {
				return this.success("保存成功", hb57.getChb057());
			}else{
				return this.error("保存失败");
			}
		}
	}

	//删除班主任信息
	@Override
	public AjaxReturnMsg deleteHeadTeacherById(Hb57 hb57) {
		hb57 = apiHeadTeacherMapper.getHeadTeacherById(hb57.getChb057());
		String userId = hb57.getUser_id();
		String bus_uuid = hb57.getBus_uuid() != null ? hb57.getBus_uuid() : "";
		if(hb57.getBus_uuid() != null) {
			deleteFile(hb57.getBus_uuid());
		}
		int deletenum = apiHeadTeacherMapper.deleteHeadTeacherById(hb57.getChb057());
		if(deletenum == 1) {
            if(!"".equals(bus_uuid)) {
                deleteFile(bus_uuid);
            }
			HashMap map = new HashMap();
            map.put("userid", userId);
            map.put("rolecode", "TRAIN-HEADMASTER");
            apiRegistMapper.deleteSUserRoleByUserIdAndRolecode(map);
            if("02".equals(hb57.getChb299())) {
                apiRegistMapper.deleteSUserByUserId(userId);
            }
			return this.success("删除成功");
		}else{
			return this.error("删除失败");
		}
	}

	//批量删除班主任信息
	@Override
	public AjaxReturnMsg deleteHeadTeacherByIds(Hb57 hb57) {
		hb57.setChb057_arr(hb57.getSelectnodes().split(","));
		List idsList = new ArrayList();
		List idsListAll = new ArrayList();
		List<Hb57> list = apiHeadTeacherMapper.getHeadTeacherList(hb57);
		for (Hb57 h:list) {
            idsListAll.add(h.getUser_id());
            if("02".equals(h.getChb299())) {
                idsList.add(h.getUser_id());
            }
			if(h.getBus_uuid() != null) {
				deleteFile(h.getBus_uuid());
			}
		}
		String[] ids = (String[]) idsList.toArray(new String[idsList.size()]);
        String[] idsAll = (String[]) idsListAll.toArray(new String[idsListAll.size()]);
		int deletenum = apiHeadTeacherMapper.deleteHeadTeacherByIds(hb57.getChb057_arr());
		if(deletenum == hb57.getChb057_arr().length){
            HashMap map = new HashMap();
            map.put("ids", idsAll);
            map.put("rolecode", "TRAIN-HEADMASTER");
            apiRegistMapper.deleteSUserRoleByUserIdsAndRolecode(map);
            apiRegistMapper.deleteSUserByUserIds(ids);
			return this.success("批量删除成功");
		}else{
			return this.success("批量删除成功,但存在失败的数据,请检查");
		}
	}

	//上传班主任头像
	@Override
	public AjaxReturnMsg uploadLogo(String userid, String file_bus_id, String file_name, MultipartFile multipartFile) {
		try {
			//保存图片到文件服务器，同时保存图片记录
			SFileRecord sFileRecord = fileLoadService.uploadFile(multipartFile, Param.TRAIN_GROUP_HEADTEACHER_LOGO, file_name,
					file_bus_id);
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

	//班主任负责班级列表查询
	@Override
	public PageInfo<Hb68> getHeadTeacherClasses(Hb57 hb57) {
		PageHelper.offsetPage(hb57.getOffset(), hb57.getLimit());
		List<Hb68> list=apiHeadTeacherMapper.getHeadTeacherClasses(hb57);
		PageInfo<Hb68> pageinfo = new PageInfo<Hb68>(list);
		return pageinfo;
	}

}
