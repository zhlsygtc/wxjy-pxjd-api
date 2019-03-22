package com.insigma.mvc.dao.train.PXYW_001_003;

import java.util.List;

import com.insigma.mvc.model.Ce01;
import com.insigma.mvc.model.CodeValue;
import com.insigma.mvc.model.SysExcelBatch;
import com.insigma.mvc.model.train.Hb68;
import com.insigma.mvc.model.train.Hc61_temp;
import com.insigma.mvc.model.train.Student;

public interface ApiPersonTrnQuaMapper {
	
	public void insertExcelTempData(Hc61_temp temp);
    
    public void executeProc(SysExcelBatch sExcelBatch);

	public List<Hc61_temp> getTeacherList(Hc61_temp temp);

	public List<Hc61_temp> getPersonList(Hc61_temp temp);

	public List<Student> getStudentList(Student stu);

	public int deleteById(String chc111);
	
	public int confirmById(String chc111);

	public String getExcel_batch_number(Hc61_temp temp);

	public String getBaseinfoid(String aae011);

	public void updatePerson(Hc61_temp t);

	public int saveDate(Student s);

	public Student getStudentById(String chc111);

	public int updateStu(Student stu);
	public int updateStuhc60(Student stu);

	public Student getStudentGBKById(String chc111);

	public Ce01 getTest(Student stu);

	public Student getCheckStuInHc61(Student stu);

	public String getNextNo();

	public Student getStudentByCardId(Hc61_temp t);

	public List<Hc61_temp> getPersonCodeList(Hc61_temp temp);

	public String getDemand(String param);

	public List<Hb68> getTrainClasses(Student stu);

	public List<CodeValue> getAca111List(CodeValue codevalue);
	public List<CodeValue> getAca109List(CodeValue codevalue);
}
