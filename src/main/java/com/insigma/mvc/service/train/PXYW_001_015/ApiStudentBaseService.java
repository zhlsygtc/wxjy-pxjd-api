package com.insigma.mvc.service.train.PXYW_001_015;


import org.springframework.web.multipart.MultipartFile;

import com.github.pagehelper.PageInfo;
import com.insigma.dto.AjaxReturnMsg;
import com.insigma.mvc.model.train.Hb68;
import com.insigma.mvc.model.train.Student;

public interface ApiStudentBaseService {


	PageInfo<Student> getStudentList(Student stu);

	AjaxReturnMsg getStudentById(String chc111);

	AjaxReturnMsg uploadLogo(String userid, String file_bus_id, String file_name, MultipartFile multipartFile);

	AjaxReturnMsg getStudentGBKById(String chc111);

	AjaxReturnMsg updateStu(Student stu);


	PageInfo<Hb68> getTrainClasses(Student stu);


}
