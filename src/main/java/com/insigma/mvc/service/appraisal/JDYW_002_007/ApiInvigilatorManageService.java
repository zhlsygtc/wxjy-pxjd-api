package com.insigma.mvc.service.appraisal.JDYW_002_007;

import org.springframework.web.multipart.MultipartFile;

import com.github.pagehelper.PageInfo;
import com.insigma.dto.AjaxReturnMsg;
import com.insigma.mvc.model.appraisal.Hc73;
import com.insigma.mvc.model.train.Hb68;
import com.insigma.mvc.model.train.Hc74;

public interface ApiInvigilatorManageService {

	PageInfo<Hc73> getInvigilatorList(Hc73 hc73);

	AjaxReturnMsg getInvigilatorById(String id, String aab001);

	AjaxReturnMsg<String> checkAac002(Hc73 hc73);

	AjaxReturnMsg saveData(Hc73 hc73);

	AjaxReturnMsg<String> deleteInvigilator(Hc73 hc73);

	AjaxReturnMsg saveQualification(Hc74 hc74);

	PageInfo<Hc74> getQualificationList(Hc74 hc74);

	AjaxReturnMsg<String> deleteQualification(String chc074);

	AjaxReturnMsg getQualification(String chc074);

	AjaxReturnMsg checkQualification(Hc74 hc74);

	AjaxReturnMsg batDelete(Hc73 hc73);

	AjaxReturnMsg getInvigilatorGBKById(String chb061, String aab001);

	AjaxReturnMsg getIdcardInfo(String aac002);

	AjaxReturnMsg uploadLogo(String userid, String file_bus_id, String file_name, MultipartFile multipartFile);

	AjaxReturnMsg uploadImage(String userid, String file_bus_id, String file_name, MultipartFile multipartFile);

	PageInfo<Hb68> getInvigilatorClasses(Hc73 hc73);
	
}
