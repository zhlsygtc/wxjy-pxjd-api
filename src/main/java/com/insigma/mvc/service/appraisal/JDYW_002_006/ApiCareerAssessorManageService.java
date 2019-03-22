package com.insigma.mvc.service.appraisal.JDYW_002_006;

import org.springframework.web.multipart.MultipartFile;

import com.github.pagehelper.PageInfo;
import com.insigma.dto.AjaxReturnMsg;
import com.insigma.mvc.model.appraisal.Hc73;
import com.insigma.mvc.model.train.Hb68;
import com.insigma.mvc.model.train.Hc74;

public interface ApiCareerAssessorManageService {

	PageInfo<Hc73> getCareerAssessorList(Hc73 hc73);

	AjaxReturnMsg getCareerAssessorById(String id, String aab001);

	AjaxReturnMsg<String> checkAac002(Hc73 hc73);

	AjaxReturnMsg saveData(Hc73 hc73);

	AjaxReturnMsg<String> deleteCareerAssessor(Hc73 hc73);

	AjaxReturnMsg saveQualification(Hc74 hc74);

	PageInfo<Hc74> getQualificationList(Hc74 hc74);

	AjaxReturnMsg<String> deleteQualification(String chc074);

	AjaxReturnMsg getQualification(String chc074);

	AjaxReturnMsg checkQualification(Hc74 hc74);

	AjaxReturnMsg batDelete(Hc73 hc73);

	AjaxReturnMsg getCareerAssessorGBKById(String chb061, String aab001);
	
	AjaxReturnMsg getIdcardInfo(String aac002);

	AjaxReturnMsg uploadLogo(String userid, String file_bus_id, String file_name, MultipartFile multipartFile);

	AjaxReturnMsg uploadImage(String userid, String file_bus_id, String file_name, MultipartFile multipartFile);

	PageInfo<Hb68> getCareerAssessorClasses(Hc73 hc73);
	
}
