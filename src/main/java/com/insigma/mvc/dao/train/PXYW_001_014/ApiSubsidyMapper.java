package com.insigma.mvc.dao.train.PXYW_001_014;

import java.util.List;

import com.insigma.mvc.model.train.Hb50;
import com.insigma.mvc.model.train.Hb50Dto;
import com.insigma.mvc.model.train.Hb67;
import com.insigma.mvc.model.train.Hb69;
import com.insigma.mvc.model.train.Hc60;
import com.insigma.mvc.model.train.Student;

/**
 * �����걨
 * @author Ace
 *
 */
public interface ApiSubsidyMapper {

	/**
	 * ������Ϣ�б�
	 */
	List<Hb67> getHb67List(Hb67 hb67) throws Exception;

	/**
	 * ���ݰ��������ѯ������Ϣ
	 */
	Hb67 getHb67ById(Hb67 hb67) throws Exception;

	/**
	 * ���ɲ���������Ϣ
	 */
	int addHb50(Hb50 hb50);

	/**
	 * ����ѧԱ������Ϣ
	 */
	int addHc50(Hb50 hb50);

	/**
	 * ��ѯ������Ϣ
	 */
	Hb67 getHb67ById(String chb067) throws Exception;
	
	/**
	 * ��ѯ�ϲ��༶��ѧԱ�б�
	 */
	List<Hc60> getHc60StuList(Hc60 hc60) throws Exception;
	
	/**
	 * ��ѯ������ѧԱ�����б� 
	 */
	List<Student> subsidyList(Student stu);
	
	/**
	 * ɾ��ѧԱ������Ϣ
	 */
	int delete(Student stu);
	
	/**
	 * ��ѯѧԱ��д��Ϣ 
	 */
	Student getHc50ById(Student stu);
	
	/**
	 *  ����ѧԱ������Ϣ
	 */
	int update(Student stu);
	
	/**
     * �ύ���ڲ�����Ϣ
     */
	int submit(Hb67 hb67);

	/**
	 * ɾ�����в��������
	 */
	int deleteHc50(Hb50 hb50);
	
	/**
	 * ɾ�����в��������
	 */
	int deleteHb50(Hb50 hb50);
	
	/**
	 * ���ݲ��������ȡ������Ϣ
	 */
	public Hb50Dto getSubsidyById(String chb050);
	
	/**
	 * ������ѵ����ѧԱ������
	 */
	List<Student> getexportStuBT(Student stu);

}
