package com.insigma.mvc.service.train.PXYW_001_014;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.insigma.dto.AjaxReturnMsg;
import com.insigma.mvc.model.train.Hb50;
import com.insigma.mvc.model.train.Hb67;
import com.insigma.mvc.model.train.Hb69;
import com.insigma.mvc.model.train.Hc60;
import com.insigma.mvc.model.train.Student;

/**
 * �����걨
 * @author Ace
 *
 */
public interface ApiSubsidyService {

	/**
	 * ������Ϣ�б�
	 * @param hb67 ���ڶ���
	 * @return
	 * @throws Exception
	 */
	public PageInfo<Hb67> getHb67List(Hb67 hb67) throws Exception;

	/**
     * ���ɰ��ڲ�����Ϣ
     */
	public AjaxReturnMsg generate(Hb67 hb67) throws Exception;

	/**
	 * ��ѯ������Ϣ
	 */
	public Hb67 getHb67ById(String chb067) throws Exception;
	
	/**
	 * ��ѯ�ϲ��༶��ѧԱ�б�
	 */
	public PageInfo<Hc60> getHc60StuList(Hc60 hc60) throws Exception;
	
	/**
	 * ��ѯ������ѧԱ�����б�
	 */
	public PageInfo<Student> subsidyList(Student stu);
	
	/**
	 * ɾ��ѧԱ������Ϣ
	 */
	public AjaxReturnMsg delete(Student stu);
	
	/**
	 * ����ѧԱ������Ϣ
	 */
	public AjaxReturnMsg update(Student stu);
	
	/**
	 *  ��ѯѧԱ��д��Ϣ 
	 */
	public Student getHc50ById(Student stu);
	
	/**
     * �������ɰ��ڲ�����Ϣ
     */
	public AjaxReturnMsg generateAgain(Hb50 hb50);
	
	/**
     * �ύ���ڲ�����Ϣ
     */
	public AjaxReturnMsg submit(Hb67 hb67);
	/**
	 * ���ݲ��������ȡ����ϸ��Ϣ
	 */
	public AjaxReturnMsg getSubsidyById(String chb050);

	/**
	 * ��ȡ�����γ̱�������Ϣ
	 * @param hb69
	 * @return
	 */
	public List<Student> getexportStuBT(Student stu);
}
