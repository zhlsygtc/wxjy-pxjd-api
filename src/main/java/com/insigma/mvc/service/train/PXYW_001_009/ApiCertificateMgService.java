package com.insigma.mvc.service.train.PXYW_001_009;

import org.springframework.web.multipart.MultipartFile;

import com.github.pagehelper.PageInfo;
import com.insigma.dto.AjaxReturnMsg;
import com.insigma.mvc.model.train.Hb67;
import com.insigma.mvc.model.train.Student;

public interface ApiCertificateMgService {

	PageInfo<Hb67> getClasssList(Hb67 hb67);

	PageInfo<Student> getStuList(Student stu);

	AjaxReturnMsg saveDate(Student stu) throws Exception;

	AjaxReturnMsg submit(Hb67 hb67);

	AjaxReturnMsg undo(Hb67 hb67);

	AjaxReturnMsg uploadLogo(String userid, String file_bus_id, String file_name, MultipartFile multipartFile, String chc001);

	PageInfo<Student> getStuListForLook(Student stu);

	AjaxReturnMsg doSet(Student stu);

	Hb67 getHb67ById(String chb067);

}
