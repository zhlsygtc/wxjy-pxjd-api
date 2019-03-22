package com.insigma.mvc.dao.app.APP_001_004;

import com.insigma.mvc.model.train.Hb65;
import com.insigma.mvc.model.train.Student;

public interface ApiPersonMgmtMapper {
	
	/**
     * ��ѯѧԱ�Ƿ��ѱ����üƻ�
     */
	public String findStuWithPlan(Student stu);
	
	/**
	 * ��ѯѧԱһ�����Ƿ��Ѳμ���ѵ
	 */
	public String checkNum(Student stu);
	
	/**
	 * ��ѯ��ǰҪ�����ļƻ�
	 */
	public Hb65 getCurrentPlan(Student stu);
	
	/**
	 * У��֮ǰ��ͬ���ֵĽϸߣ�������ȣ��ȼ���ѵ
	 */
	public String checkGrade(Hb65 hb65);
	
	/**
	 * ��ȡѧԱ������Ϣ
	 */
	public Student getStudentInfo(Student stu);

	/**
	 * ��ȡ��Ա������Ϣ
	 */
	public Student getPersonInfo(Student stu);
	
	/**
	 * ��ȡѧԱ�����
	 */
	public String getNextNo();
	
	/**
	 * ����ѧԱ��Ϣ
	 */
	public int saveStu(Student s);

	/**
	 * ����ѧԱ��Ϣ
	 */
	public int updateStu(Student stu);

	/**
     * ѧԱ�����ƻ�����
     */
	public int apply(Student stu);

	
}
