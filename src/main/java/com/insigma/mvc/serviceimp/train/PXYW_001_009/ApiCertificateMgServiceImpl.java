package com.insigma.mvc.serviceimp.train.PXYW_001_009;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.insigma.common.util.FileUtil;
import com.insigma.dto.AjaxReturnMsg;
import com.insigma.mvc.MvcHelper;
import com.insigma.mvc.dao.common.fileupload.ApiFileUploadMapper;
import com.insigma.mvc.dao.train.PXYW_001_001.ApiCompanyMapper;
import com.insigma.mvc.dao.train.PXYW_001_009.ApiCertificateMgMapper;
import com.insigma.mvc.model.CodeValue;
import com.insigma.mvc.model.Param;
import com.insigma.mvc.model.SFileRecord;
import com.insigma.mvc.model.train.Hb67;
import com.insigma.mvc.model.train.Student;
import com.insigma.mvc.service.common.fileupload.ApiFileUploadService;
import com.insigma.mvc.service.train.PXYW_001_009.ApiCertificateMgService;

@Service
@Transactional(rollbackFor=Exception.class)
public class ApiCertificateMgServiceImpl extends MvcHelper implements ApiCertificateMgService{

	@Resource
	private ApiCertificateMgMapper apiCertificateMgMapper;
	@Resource
	private ApiFileUploadService fileLoadService;
	@Resource
	private ApiCompanyMapper apiCompanyMapper;
	@Resource
    private ApiFileUploadMapper apiFileUploadMapper;
	
	@Override
	public PageInfo<Hb67> getClasssList(Hb67 hb67) {
		PageHelper.offsetPage(hb67.getOffset(), hb67.getLimit());
		List<Hb67> list=apiCertificateMgMapper.getClasssList(hb67);
		PageInfo<Hb67> pageinfo = new PageInfo<>(list);
		return pageinfo;
	}

	@Override
	public PageInfo<Student> getStuList(Student stu) {
		PageHelper.offsetPage(0, 1000);
		String aaa005 = apiCompanyMapper.getAa01ByAaa001("AREA_ID");
		List<Student> list = new ArrayList<Student>(); 
 		if("320904".equals(aaa005)) {
 			list=apiCertificateMgMapper.getStuList_df(stu);
 		}else{
 			list=apiCertificateMgMapper.getStuList(stu);
 		}
		PageInfo<Student> pageinfo = new PageInfo<>(list);
		return pageinfo;
	}

	@Override
	public AjaxReturnMsg saveDate(Student stu) throws Exception {
		if(!"".equals(stu.getParam())) {
            String str = stu.getParam().substring(0, stu.getParam().length()-1);
            String[] params = str.split(";");
            String aaa005 = apiCompanyMapper.getAa01ByAaa001("AREA_ID");
    		if("320904".equals(aaa005)) {
    			CodeValue codevalue= new CodeValue();
				codevalue.setCode_type("AAB004S");
    			for (int i=0;i<params.length;i++) {
                    String[] param = params[i].split(",");
                    Student stus = apiCertificateMgMapper.getHc67(param[1]);
                    codevalue.setCode_name(param[5]);
                    if(stus == null) {
                        //新增
                        stu.setChc001(param[0]);
                        stu.setChc067(param[1]);
                        stu.setChc023(param[2]);
                        stu.setChc024(param[3]);
                        stu.setChc028(param[4]);
                        stu.setAab004(apiCompanyMapper.getCodeValueByName(codevalue).get(0).getCode_value());
                        stu.setAae011(stu.getUserid());
                        stu.setAae100("1");
                        apiCertificateMgMapper.saveDate(stu);
                    }else{
                        //更新
                        stu.setChc067(param[1]);
                        stu.setChc023(param[2]);
                        stu.setChc024(param[3]);
                        stu.setChc028(param[4]);
                        stu.setAab004(apiCompanyMapper.getCodeValueByName(codevalue).get(0).getCode_value());
                        stu.setAae011(stu.getUserid());
                        stu.setAae100("1");
                        apiCertificateMgMapper.update(stu);
                    }

                }
    		}else{
    			for (int i=0;i<params.length;i++) {
                    String[] param = params[i].split(",");
                    Student stus = apiCertificateMgMapper.getHc67(param[1]);
                    if(stus == null) {
                        //新增
                        stu.setChc001(param[0]);
                        stu.setChc067(param[1]);
                        stu.setChc023(param[2]);
                        stu.setChc024(param[3]);
                        stu.setChc028(param[4]);
                        stu.setAab004(param[5]);
                        stu.setAae011(stu.getUserid());
                        stu.setAae100("1");
                        apiCertificateMgMapper.saveDate(stu);
                    }else{
                        //更新
                        stu.setChc067(param[1]);
                        stu.setChc023(param[2]);
                        stu.setChc024(param[3]);
                        stu.setChc028(param[4]);
                        stu.setAab004(param[5]);
                        stu.setAae011(stu.getUserid());
                        stu.setAae100("1");
                        apiCertificateMgMapper.update(stu);
                    }
                }
    		}
        }
		apiCertificateMgMapper.updateState(stu.getChb067());
		return this.success("保存成功");
	}

	@Override
	public AjaxReturnMsg submit(Hb67 hb67) {
		int num = apiCertificateMgMapper.submit(hb67);
		if(num == 1) {
			return this.success("提交成功");
		}else{
			return this.error("提交失败");
		}
	}

	@Override
	public AjaxReturnMsg undo(Hb67 hb67) {
		int num = apiCertificateMgMapper.undo(hb67);
		if(num == 1) {
			return this.success("撤销成功");
		}else{
			return this.error("撤销失败");
		}
	}

	@Override
	public AjaxReturnMsg uploadLogo(String userid, String file_bus_id, String file_name, MultipartFile multipartFile, String chc001) {
		try {
			//保存图片到文件服务器，同时保存图片记录
			SFileRecord sFileRecord = fileLoadService.uploadFile(multipartFile, Param.TRAIN_STUDENT_CERTIFICATE_IMAGE, file_name,
					file_bus_id);
			if (sFileRecord != null) {
				Student stus = apiCertificateMgMapper.getHc67(file_bus_id);
				Student stu = new Student();
				if(stus == null) {
					stu.setChc067(file_bus_id);
					stu.setChc001(chc001);
					stu.setAae011(userid);
					stu.setAae100("1");
					stu.setBus_uuid(sFileRecord.getBus_uuid());
					apiCertificateMgMapper.saveDate(stu);
				}else{
					stu.setChc067(file_bus_id);
					stu.setAae011(userid);
					stu.setAae100("1");
					stu.setBus_uuid(sFileRecord.getBus_uuid());
					apiCertificateMgMapper.update(stu);
				}
				
			}
			return this.success(sFileRecord);
		} catch (Exception e) {
			return this.error(e.getMessage());
		}
	}

	@Override
	public PageInfo<Student> getStuListForLook(Student stu) {
		PageHelper.offsetPage(stu.getOffset(), stu.getLimit());
		String aaa005 = apiCompanyMapper.getAa01ByAaa001("AREA_ID");
		List<Student> list = new ArrayList<Student>(); 
 		if("320904".equals(aaa005)) {
 			list=apiCertificateMgMapper.getStuList_df(stu);
 		}else{
 			list=apiCertificateMgMapper.getStuList(stu);
 		}
		PageInfo<Student> pageinfo = new PageInfo<>(list);
		return pageinfo;
	}

	@Override
	public AjaxReturnMsg doSet(Student stu) {
		apiCertificateMgMapper.doSet(stu);
		if("0".equals(stu.getChc029())) {
			apiCertificateMgMapper.deleteCertificate(stu);
		}
		if(!"1".equals(stu.getBus_uuid())){
			deleteFile(stu.getBus_uuid());
		}
		return this.success("设置成功");
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

	@Override
	public Hb67 getHb67ById(String chb067) {
		String aaa005 = apiCompanyMapper.getAa01ByAaa001("AREA_ID");
		if("320904".equals(aaa005)) {
			return apiCertificateMgMapper.getHb67ById_df(chb067);
		}else if("610000".equals(aaa005)){
			return apiCertificateMgMapper.getHb67ById_sx(chb067);
	    }else{
			return apiCertificateMgMapper.getHb67ById(chb067);
		}
		
	}

	
}
