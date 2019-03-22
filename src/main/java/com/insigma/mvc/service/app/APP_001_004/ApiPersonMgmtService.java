package com.insigma.mvc.service.app.APP_001_004;

import com.insigma.dto.AjaxReturnMsg;
import com.insigma.mvc.model.train.Student;

public interface ApiPersonMgmtService {

	/**
     * ѧԱ�����ƻ���ʼ
     */
	AjaxReturnMsg signup(Student stu);
	
	/**
     * ��ȡѧԱ������Ϣ
     */
	AjaxReturnMsg getStudentInfo(Student stu);

	/**
     * ��ȡ��(ѧ)Ա������Ϣ
     */
	AjaxReturnMsg getPersonInfo(Student stu);
	
	/**
     * ����ѧԱ��Ϣ
     */
	AjaxReturnMsg saveStudent(Student stu);

	/**
     * ѧԱ�����ƻ�����
     */
	AjaxReturnMsg apply(Student stu);
	
	
}
