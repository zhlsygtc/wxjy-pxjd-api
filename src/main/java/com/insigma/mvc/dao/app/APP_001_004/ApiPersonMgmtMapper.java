package com.insigma.mvc.dao.app.APP_001_004;

import com.insigma.mvc.model.train.Hb65;
import com.insigma.mvc.model.train.Student;

public interface ApiPersonMgmtMapper {
	
	/**
     * 查询学员是否已报名该计划
     */
	public String findStuWithPlan(Student stu);
	
	/**
	 * 查询学员一年内是否已参加培训
	 */
	public String checkNum(Student stu);
	
	/**
	 * 查询当前要报名的计划
	 */
	public Hb65 getCurrentPlan(Student stu);
	
	/**
	 * 校验之前有同工种的较高（包括相等）等级培训
	 */
	public String checkGrade(Hb65 hb65);
	
	/**
	 * 获取学员基本信息
	 */
	public Student getStudentInfo(Student stu);

	/**
	 * 获取人员基本信息
	 */
	public Student getPersonInfo(Student stu);
	
	/**
	 * 获取学员库序号
	 */
	public String getNextNo();
	
	/**
	 * 新增学员信息
	 */
	public int saveStu(Student s);

	/**
	 * 更新学员信息
	 */
	public int updateStu(Student stu);

	/**
     * 学员报名计划结束
     */
	public int apply(Student stu);

	
}
