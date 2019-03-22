package com.insigma.mvc.service.train.PXYW_001_002;

import org.springframework.web.multipart.MultipartFile;

import com.github.pagehelper.PageInfo;
import com.insigma.dto.AjaxReturnMsg;
import com.insigma.mvc.model.train.Hb61;
import com.insigma.mvc.model.train.Hb68;
import com.insigma.mvc.model.train.Hc74;

public interface ApiTeacherService {

	PageInfo<Hb61> getTeacherList(Hb61 hb61);

	AjaxReturnMsg getTeacherById(String id);

	AjaxReturnMsg<String> checkAac002(Hb61 hb61);

	AjaxReturnMsg saveData(Hb61 hb61);

	AjaxReturnMsg<String> deleteTeacher(Hb61 hb61);

	AjaxReturnMsg saveQualification(Hc74 hc74);

	PageInfo<Hc74> getQualificationList(Hc74 hc74);

	AjaxReturnMsg<String> deleteQualification(String chc074);

	AjaxReturnMsg getQualification(String chc074);

	AjaxReturnMsg checkQualification(Hc74 hc74);

	AjaxReturnMsg batDelete(Hb61 hb61);

	AjaxReturnMsg getTeacherGBKById(String chb061);

	AjaxReturnMsg uploadLogo(String userid, String file_bus_id, String file_name, MultipartFile multipartFile);

	AjaxReturnMsg uploadImage(String userid, String file_bus_id, String file_name, MultipartFile multipartFile);

	PageInfo<Hb68> getTeacherClasses(Hb61 hb61);
}
