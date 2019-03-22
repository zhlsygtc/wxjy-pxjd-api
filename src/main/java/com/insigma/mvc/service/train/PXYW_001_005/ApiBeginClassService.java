package com.insigma.mvc.service.train.PXYW_001_005;

import com.github.pagehelper.PageInfo;
import com.insigma.dto.AjaxReturnMsg;
import com.insigma.mvc.model.CodeValue;
import com.insigma.mvc.model.train.*;
import java.util.List;
/**
 * ��������
 * @author zhanghl
 * 2018-01-10
 */
public interface ApiBeginClassService {
	/**
	 * ��ѯ��ѵҵ���౸���б�
	 */
	public PageInfo<Hb68> getInfoList(Hb68 hb68) throws Exception;
	/**
	 * ����ID��ѯ�༶��Ϣ
	 */
	public Hb68 getById(String chb100) throws Exception;
	/**
	 * ��ȡ��λ����
	 */
	AjaxReturnMsg<List<CodeValue>> getAca111List(CodeValue codevalue);
	/**
	 * ��ȡ�ϼ�����
	 */
	AjaxReturnMsg<List<CodeValue>> getAab301List();
	/**
	 * ��ѯ�ù�˾������������
	 */
	public SmtGroup getCompanyAab301(String groupId) throws Exception;
	/**
	 * ����id��ȡsmtgroup
	 */
	public SmtGroup getSmtGroupById(String groupId) throws Exception;
	/**
	 * ������ѵ��౸��������Ϣ
	 */
	public AjaxReturnMsg saveBaseInfo(Hb68 hb68) throws Exception;
	/**
	 * ����רҵ���Ʋ�ѯ��ѵ�������Ƽ�רҵ���
	 */
	public Hb68 getAca112(String aca110) throws Exception;
	/**
	 * ����רҵ���Ʋ�ѯ������׼
	 */
	public Hb68 getAca131(String aca109) throws Exception;
	/**
	 * ��ѯѧԱ��ѧԱ�б�
	 */
	public List<Student> getStuList(Student stu) throws Exception;
	/**
	 * ��ѯ��ѡ��ѧԱ��Ϣ
	 */
	public List<Student> getCheck(Student stu) throws Exception;
	/**
	 * ����ѧԱ��ѧԱ��hb60
	 */
	public AjaxReturnMsg saveStu(Student stu) throws Exception;
	/**
	 * ��������ɾ��������Ϣ
	 */
	public AjaxReturnMsg<String> delClass(Hb68 hb68);
	/**
	 * �ύ������Ϣ
	 */
	public AjaxReturnMsg<String> submitClass(Hb68 hb68);
	/**
	 * ����������Ϣ
	 */
	public AjaxReturnMsg<String> revokeClass(Hb68 hb68);
	/**
	 * ��ѯ��ѵѧԱ��Ϣ
	 */
	public PageInfo<Student> getStuListForLook(Student stu) throws Exception;
	/**
	 * ��ѯ��ѵ�γ̱���Ϣ
	 */
	public List<Hb69> getCourseListForLook(Hb69 hb69) throws Exception;
	/**
	 * ��ѯ�ı��Ŀγ̱���Ϣ
	 */
	public List<Hb69> getCourseListForChange(Hb69 hb69) throws Exception;
	/**
	 * ��ѯ����γ̱�
	 */
	PageInfo<Hb69Temp> getTempCourseList(Hb69Temp temp);
	/**
	 * ¼��ӱ���γ̱�
	 */
	public AjaxReturnMsg varifyCourse(Hb69Temp temp) throws Exception;
	/**
	 * ����---�����γ̱�hb69
	 */
	public AjaxReturnMsg saveCourseData(Hb69Temp temp) throws Exception;
	/**
	 * ¼��---�����γ̱�hb69
	 */
	public AjaxReturnMsg saveCourse(Hb69Temp temp) throws Exception;
	/**
	 * �޸Ŀγ̱�
	 */
	public AjaxReturnMsg saveCourseForChange(Hb69Temp temp) throws Exception;
	/**
	 * ��ѯ�������κ�
	 */
	public AjaxReturnMsg getExcel_batch_number(Hb69Temp hb69Temp);
	/**
	 * ɾ����ѵѧԱ
	 */
	public AjaxReturnMsg<String> delStudent(String chc001);
}
