package com.insigma.mvc.service.app.APP_001_004;

import com.insigma.dto.AjaxReturnMsg;
import com.insigma.mvc.model.train.Student;

public interface ApiPersonMgmtService {

	/**
     * 学员报名计划开始
     */
	AjaxReturnMsg signup(Student stu);
	
	/**
     * 获取学员基本信息
     */
	AjaxReturnMsg getStudentInfo(Student stu);

	/**
     * 获取人(学)员基本信息
     */
	AjaxReturnMsg getPersonInfo(Student stu);
	
	/**
     * 保存学员信息
     */
	AjaxReturnMsg saveStudent(Student stu);

	/**
     * 学员报名计划结束
     */
	AjaxReturnMsg apply(Student stu);
	
	
}
