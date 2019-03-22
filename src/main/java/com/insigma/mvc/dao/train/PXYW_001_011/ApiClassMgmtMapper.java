package com.insigma.mvc.dao.train.PXYW_001_011;

import java.util.HashMap;
import java.util.List;

import com.insigma.mvc.model.CodeValue;
import com.insigma.mvc.model.SysExcelBatch;
import com.insigma.mvc.model.train.Hb65;
import com.insigma.mvc.model.train.Hb68;
import com.insigma.mvc.model.train.Hb69;
import com.insigma.mvc.model.train.Hb69Temp;
import com.insigma.mvc.model.train.StuFileDTO;
import com.insigma.mvc.model.train.Student;

/**
 * 班级管理
 * @author Administrator 
 * 2018-04-26
 */
public interface ApiClassMgmtMapper {

	/**
	 * 查询班级列表
	 */
	List<Hb68> getInfoList(Hb68 hb68) throws Exception;

	/**
	 * 根据ID获取培训计划信息
	 */
	Hb65 getPlanById(String planid);

	/**
	 * (新增)保存培训办班备案基础信息
	 */
	void saveBaseInfo(Hb68 hb68);
	
	/**
	 * 保存培训班级与培训计划关系
	 */
	void addRelation(Hb68 hb68);

	/**
	 * 更新培训计划表状态
	 * @param hb68 
	 */
	void updatePlan(Hb68 hb68);
	
	/**
	 * (修改)保存培训办班备案基础信息
	 */
	void updateBaseInfo(Hb68 hb68);

	/**
	 * 获取开班编号
	 */
	String getChb100();
	
	/**
	 * 获取开班编号（天门）
	 */
	String getChb100Tm(Hb68 hb68) throws Exception;

	/**
	 * 查询培训学员信息
	 */
	List<Student> getStuList(Student stu);

	/**
	 * 根据ID查询培训班级信息
	 */
	Hb68 getById(String chb068);

	List<Student> getCheck(Student stu);

	void delClassBase(Hb68 hb68);

	void delClassStu(Hb68 hb68);

	void delRelation(Hb68 hb68);

	void saveStu(Student stu);

	int delHc60(String chc001);

	List<Hb69> getCourseListForLook(String chb068);

	void delHb69(String chb068);

	void insertHb69(Hb69Temp hb69Temp);

	List<Hb69Temp> getTempCourseList(Hb69Temp temp);

	SysExcelBatch getSysexcelbatch(Hb69Temp hb69Temp);

	List<Student> getStuListForLook(String chb068);

	void insertExcelTempData(Hb69Temp hb69Temp);

	void executeProc(SysExcelBatch newsexcelbatch);

	void submitClass(Hb68 hb68);

	Hb68 checkStatus(Hb68 hb68);

	int revokeClass(Hb68 hb68);

	List<CodeValue> getPlans(CodeValue codevalue);
	/**
	 * 导出学员花名册
	 */
	List<HashMap> expStuRoster(Student stu);
	/**
	 * 导出开班申请确认表所需信息
	 */
	public Hb68 expClassSure(Hb68 hb68);
	/**
	 * 导出教学计划表所需信息
	 */
	List<Hb69> getClassCourse(Hb69 hb69);
	/**
     * 获取文件列表信息
     * @param hb68
     * @return
     */
    List<Hb68> getClassFile(Hb68 hb68);
}
