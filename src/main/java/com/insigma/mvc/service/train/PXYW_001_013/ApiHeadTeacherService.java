package com.insigma.mvc.service.train.PXYW_001_013;

import com.github.pagehelper.PageInfo;
import com.insigma.dto.AjaxReturnMsg;
import com.insigma.mvc.model.train.Hb57;
import com.insigma.mvc.model.train.Hb68;
import org.springframework.web.multipart.MultipartFile;


public interface ApiHeadTeacherService {

	PageInfo<Hb57> getHeadTeacherList(Hb57 hb57);

	AjaxReturnMsg getHeadTeacherById(String chb057);

	AjaxReturnMsg checkAac002(Hb57 hb57);

	AjaxReturnMsg saveHeadTeacher(Hb57 hb57);

	AjaxReturnMsg deleteHeadTeacherById(Hb57 hb57);

	AjaxReturnMsg deleteHeadTeacherByIds(Hb57 hb57);

	AjaxReturnMsg uploadLogo(String userid, String file_bus_id, String file_name, MultipartFile multipartFile);

	PageInfo<Hb68> getHeadTeacherClasses(Hb57 hb57);
}
