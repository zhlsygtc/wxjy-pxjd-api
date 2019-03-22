package com.insigma.mvc.dao.train.PXYW_001_015;

import java.util.List;

import com.insigma.mvc.model.train.Hb68;
import com.insigma.mvc.model.train.Hc61_temp;
import com.insigma.mvc.model.train.Student;

public interface ApiStudentBaseMapper {

	public List<Student> getStudentList(Student stu);

	public String getBaseinfoid(String aae011);

	public Student getStudentById(String chc111);

	public int updateStu(Student stu);

	public Student getStudentGBKById(String chc111);

	public Student getStudentByCardId(Hc61_temp t);

	public List<Hb68> getTrainClasses(Student stu);

}
