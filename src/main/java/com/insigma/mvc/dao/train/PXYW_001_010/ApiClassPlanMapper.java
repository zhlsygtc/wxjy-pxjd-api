package com.insigma.mvc.dao.train.PXYW_001_010;

import com.insigma.mvc.model.CodeValue;
import com.insigma.mvc.model.SysExcelBatch;
import com.insigma.mvc.model.train.*;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
/**
 * ����ƻ�
 * @author zhanghl
 * 2018-04-25
 */
public interface ApiClassPlanMapper {
	/**
	 * ��ѯ��ѵ�ƻ���Ϣ
	 */
	List<Hb65> getPlanList(Hb65 hb65) throws Exception;
	/**
	 * (����)������ѵ�ƻ���Ϣ
	 */
	int addPlan(Hb65 hb65) throws Exception;
	/**
	 * (�޸�)������ѵ�ƻ���Ϣ
	 */
	int updatePlan(Hb65 hb65) throws Exception;
	/**��
	 * ��ѯ��ѵ��౸���б�
	 */
	Hb65 getPlanById(String chb055) throws Exception;
	/**��
	 * ��ѯ��ѵ��౸���б���ᣩ
	 */
	Hb65 getPlanById_df(String chb055) throws Exception;
	/**��
	 * ��ѯ��ѵ��౸���б�������
	 */
	Hb65 getPlanById_sx(String chb055);
	/**
	 * ��ȡ��λ����
	 */
	public List<CodeValue> getAca111List(CodeValue codevalue);
	/**
	 * ��ȡ��λ����
	 */
	public List<CodeValue> getAca11aList(@Param(value="id")String id, @Param(value="aca111")String aca111);
	/**
	 * ��ȡ��λ���ʣ�������
	 */
	List<CodeValue> getAca111List_sx(CodeValue codevalue);
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
	 * ����רҵ���Ʋ�ѯ��ѵ�������Ƽ�רҵ���
	 */
	Hb65 getAca112(String aca111) throws Exception;
	/**
	 * ����רҵ���Ʋ�ѯ��ѵ�������Ƽ�רҵ��𣨴�ᣩ
	 */
	Hb65 getAca112_df(String aca111) throws Exception;
	/**
	 * ����רҵ���Ʋ�ѯ��ѵ�������Ƽ�רҵ���������
	 */
	Hb65 getAca112_sx(String aca111);
	/**
	/**
	 * �鿴��ѵѧԱ��Ϣ
	 */
	List<Student> signedStuForLook(String chb055) throws Exception;
	/**
	 * ɾ����ѵ�ƻ�(��Ϊ��Ч)
	 * @param hb68
	 * @return
	 * @throws Exception
	 */
	int delPlan(Hb65 hb65) throws Exception;
	/**
	 * �л���ѵ״̬
	 * @param hb65
	 * @return
	 * @throws Exception
	 */
	int changePlan(Hb65 hb65) throws Exception;
	/**
	 * ��ѯѧԱ��Ϣ
	 */
	Hc61 findStuById(Student stu) throws Exception;
	/**
	 * ��ѯѧԱ������Ϣ
	 */
	Hc61 findFileById(Student stu) throws Exception;
	/**
	 * ����
	 * @return 
	 */
	int signUp(Student stu) throws Exception;
	/**
	 * ����ѧԱ
	 * @return 
	 */
	int addStu(Student stu) throws Exception;
	/**
	 * �޸�ѧԱ
	 * @return 
	 */
	int updateStu(Student stu) throws Exception;
	/**
	 * ��ѯ��ѧԱ�Ƿ����üƻ�
	 * @return 
	 */
	String findStuWithPlan(Student stu) throws Exception;
	/**
	 * �ϴ���Ƭ
	 * @param hb61
	 * @return
	 */
	int uploadHc61Photo(Student stu);
	/**
	 * У���������
	 * @return 
	 */
	String trainTypeCount(Student stu) throws Exception;
	/**
	 *  У����ѵ����
	 * @return 
	 */
	String trainProfeCount(Student stu) throws Exception;
	/**
	 * ɾ��ͼƬ
	 * @param picId
	 * @return
	 * @throws Exception
	 */
	int delFilePicById(String picId) throws Exception;
	int delBusFilePicById(String picId) throws Exception;
	
	/**
	 * У���Ƿ���ְ��Ա
	 */
	String getPersonWorkStatus(String aac002);
	/**
	 * У�鱾����Ƿ������ܾ�ҵ��ѵ����
	 */
	String getPersonSubsidyStatus(String aac002);
	
	/**
	 * У��ѧԱ��������ѵ
	 */
	String checkHc60(@Param(value="aac002") String aac002,@Param(value="aca11a") String aca11a);
	String checkSignPlan(@Param(value="aac002") String aac002,@Param(value="aca11a") String aca11a,@Param(value="chb055") String chb055);
	/**
	 * У����ʱ������
	 */
	public void executeProc(SysExcelBatch sExcelBatch);
	/**
	 * ��ȡ��ǰ�ƻ�ʣ�౨������
	 */
	public String getPeople(Hc61_temp hc61Temp);
	/**
	 * ����word������Ϣ
	 */
	public Student exportDJK(Student stu);
	
}
