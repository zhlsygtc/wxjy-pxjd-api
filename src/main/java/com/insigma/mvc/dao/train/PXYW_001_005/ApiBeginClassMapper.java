package com.insigma.mvc.dao.train.PXYW_001_005;

import com.insigma.mvc.model.CodeValue;
import com.insigma.mvc.model.SysExcelBatch;
import com.insigma.mvc.model.train.*;
import java.util.List;
/**
 * �����걨
 * @author Administrator 
 * 2018-01-10
 */
public interface ApiBeginClassMapper {
	/**
	 * ��ѯ��ѵ��౸���б�
	 */
	List<Hb68> getInfoList(Hb68 hb68) throws Exception;
	 /**
	 * ��ѯ��ѵ��౸����Ϣ
	 */
	Hb68 getById(String chb100) throws Exception;
	/**
	 * ��ȡ��λ����
	 */
	public List<CodeValue> getAca111List(CodeValue codevalue);
	/**
	 * ��ȡ�ϼ�����
	 */
	public List<CodeValue> getAab301List();
	/**
	 * ��ѯ�û���������������
	 */
	SmtGroup getCompanyAab301(String groupId) throws Exception;
	/**
	 * ��ȡsmtgroup����
	 */
	SmtGroup getSmtGroupById(String groupId) throws Exception;
	/**
	 * ��ȡ������
	 */
	String getChb100() throws Exception;
	/**
	 * (����)������ѵ��౸��������Ϣ
	 */
	int saveBaseInfo(Hb68 hb68) throws Exception;
	/**
	 * (�޸�)������ѵ��౸��������Ϣ
	 */
	int updateBaseInfo(Hb68 hb68) throws Exception;
	/**
	 * ����רҵ���Ʋ�ѯ��ѵ�������Ƽ�רҵ���
	 */
	Hb68 getAca112(String aca110) throws Exception;
	/**
	 * ����רҵ���Ʋ�ѯ������׼
	 */
	Hb68 getAca131(String aca109) throws Exception;
	/**
	 * ��ѯѧԱ��ѧԱ��Ϣ
	 */
	List<Student> getStuList(Student stu) throws Exception;
	/**
	 * ��ѯ��ѡѧԱ��Ϣ
	 */
	List<Student> getCheck(Student stu) throws Exception;
	/**
	 * ���湴ѡ��ѧԱ��Ϣ��hb60
	 */
	int saveStu(Student stu) throws Exception;
	/**
	 * ����ʱ����61��ѡ���ѧԱ��ѡ��״̬
	 */
	int updateHc61(Student stu) throws Exception;
	/**
	 * ����61���Ƴ���ѧԱ��ѡ��״̬
	 */
	int updatePassHc61(Student stu) throws Exception;
	/**
	 * ɾ��60���Ƴ���ѧԱ
	 */
	int delPassHc60(Student stu) throws Exception;
	/**
	 * ɾ��������Ϣ(2��)
	 */
	//1.ɾ���༶��Ϣ(��Ϊ��Ч)
	int delClassBase(Hb68 hb68) throws Exception;
	//2.ɾ��ѧԱ��Ϣ(��Ϊ��Ч)
	int delClassStu(Hb68 hb68) throws Exception;
	/**
	 * �ύ������Ϣ
	 */
	int submitClass(Hb68 hb68) throws Exception;
	/**
	 * У�鵱ǰ�༶�ɼ�¼��״̬
	 */
	Hb68 checkStatus(Hb68 hb68) throws Exception;
	/**
	 * ����������Ϣ
	 */
	int revokeClass(Hb68 hb68) throws Exception;
	/**
	 * ��ѯ��ѵѧԱ��Ϣ
	 */
	List<Student> getStuListForLook(String chb100) throws Exception;
	/**
	 * ��ѯ��ѵ�γ̱���Ϣ
	 */
	List<Hb69> getCourseListForLook(String chb100) throws Exception;
	/**
	 * ��ȡ����Ŀγ̱���Ϣ
	 */
	List<Hb69Temp> getTempCourseList(Hb69Temp temp);
	/**
	 * ɾ���γ̱�
	 */
	int delHb69(String chb100) throws Exception;
	/**
	 * ֮ǰ�������γ̱���ɾ����ǰ��Ч�Ŀγ̱�
	 */
	int delEffectHb69(String chb100) throws Exception;
	/**
	 * ��ѯ��ǰ69�����Ƿ����пγ̱�
	 */
	String findHb69(String chb100) throws Exception;
	/**
	 * ��69���пγ̱��Ϊ��Ч
	 */
	int changeToNoEffect(String chb100) throws Exception;
	/**
	 * ɾ���γ̻����
	 */
	int delHb69Temp(String chb100) throws Exception;
	/**
	 * ����γ̱�hb69
	 */
	int insertHb69(Hb69Temp hb69Temp) throws Exception;
	/**
	 *����chb100��ȡchb068
	 */
	String getChb068(String chb100) throws Exception;
	/**
	 * ������ʱ��
	 */
	public void insertExcelTempData(Hb69Temp temp);
	/**
	 * ִ�д洢���� 
	 */
    public void executeProc(SysExcelBatch sExcelBatch);
	/**
	 * ��ѯ�������κ�
	 */
	public String getExcel_batch_number(Hb69Temp hb69Temp);
	/**
	 * ɾ����ѵѧԱ
	 */
	int delHc60(String chc001);
	/**
	 * ɾ��ѧԱʱ����61��ѡ���ѧԱ��ѡ��״̬
	 */
	int updateHc61ById(String chc001);
	/**
	 * ɾ���༶ʱ����61��ѡ���ѧԱ��ѡ��״̬
	 */
	void updateStu(Hb68 hb68);
	/**
	 * ����hb69temp������
	 */
	int insertHb69Temp(Hb69Temp hb69Temp);
	/**
	 * ����γ̱�hb69����
	 */
	int insertHb69Input(Hb69Temp hb69Temp);
	/**
	 * ����γ̱�hb69���������޸Ŀγ̱�
	 */
	int insertHb69InputForChange(Hb69Temp hb69Temp);
	/**
	 * ��ѯ�ð༶hb69temp���е�����
	 */
	List<Hb69Temp> getHb69tempList(Hb69Temp hb69Temp) throws Exception;
}
