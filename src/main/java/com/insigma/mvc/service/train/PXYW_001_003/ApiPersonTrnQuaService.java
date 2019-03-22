package com.insigma.mvc.service.train.PXYW_001_003;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.github.pagehelper.PageInfo;
import com.insigma.dto.AjaxReturnMsg;
import com.insigma.mvc.model.CodeValue;
import com.insigma.mvc.model.train.Hb68;
import com.insigma.mvc.model.train.Hc61_temp;
import com.insigma.mvc.model.train.Student;

public interface ApiPersonTrnQuaService {

	PageInfo<Hc61_temp> getPersonList(Hc61_temp temp);

	PageInfo<Student> getStudentList(Student stu);

	AjaxReturnMsg deleteById(Student stu);
	
	AjaxReturnMsg confirmById(Student stu);
	
	AjaxReturnMsg getExcel_batch_number(Hc61_temp temp);

	AjaxReturnMsg saveDate(Hc61_temp temp);

	AjaxReturnMsg getStudentById(String chc111);

	AjaxReturnMsg uploadLogo(String userid, String file_bus_id, String file_name, MultipartFile multipartFile);

	AjaxReturnMsg getStudentGBKById(String chc111);

	AjaxReturnMsg updateStu(Student stu);

	AjaxReturnMsg saveStu(Student stu);

	AjaxReturnMsg checkAac002(Student stu);

	PageInfo<Hb68> getTrainClasses(Student stu);

	AjaxReturnMsg<List<CodeValue>> getAca111List(CodeValue codevalue);
	AjaxReturnMsg<List<CodeValue>> getAca109List(CodeValue codevalue);

}
