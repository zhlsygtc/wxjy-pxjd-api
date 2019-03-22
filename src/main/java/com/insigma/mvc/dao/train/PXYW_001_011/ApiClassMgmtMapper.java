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
 * �༶����
 * @author Administrator 
 * 2018-04-26
 */
public interface ApiClassMgmtMapper {

	/**
	 * ��ѯ�༶�б�
	 */
	List<Hb68> getInfoList(Hb68 hb68) throws Exception;

	/**
	 * ����ID��ȡ��ѵ�ƻ���Ϣ
	 */
	Hb65 getPlanById(String planid);

	/**
	 * (����)������ѵ��౸��������Ϣ
	 */
	void saveBaseInfo(Hb68 hb68);
	
	/**
	 * ������ѵ�༶����ѵ�ƻ���ϵ
	 */
	void addRelation(Hb68 hb68);

	/**
	 * ������ѵ�ƻ���״̬
	 * @param hb68 
	 */
	void updatePlan(Hb68 hb68);
	
	/**
	 * (�޸�)������ѵ��౸��������Ϣ
	 */
	void updateBaseInfo(Hb68 hb68);

	/**
	 * ��ȡ������
	 */
	String getChb100();
	
	/**
	 * ��ȡ�����ţ����ţ�
	 */
	String getChb100Tm(Hb68 hb68) throws Exception;

	/**
	 * ��ѯ��ѵѧԱ��Ϣ
	 */
	List<Student> getStuList(Student stu);

	/**
	 * ����ID��ѯ��ѵ�༶��Ϣ
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
	 * ����ѧԱ������
	 */
	List<HashMap> expStuRoster(Student stu);
	/**
	 * ������������ȷ�ϱ�������Ϣ
	 */
	public Hb68 expClassSure(Hb68 hb68);
	/**
	 * ������ѧ�ƻ���������Ϣ
	 */
	List<Hb69> getClassCourse(Hb69 hb69);
	/**
     * ��ȡ�ļ��б���Ϣ
     * @param hb68
     * @return
     */
    List<Hb68> getClassFile(Hb68 hb68);
}
