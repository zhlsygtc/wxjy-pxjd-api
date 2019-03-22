package com.insigma.mvc.dao.train.PXYW_001_016;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.insigma.mvc.model.Ce01;
import com.insigma.mvc.model.train.Hb67;
import com.insigma.mvc.model.train.Student;

public interface ApiSkillTrnSubsidyMapper {

	/**
	 * ��ȡ�ϲ��༶��Ϣ�б�
	 * @param hb67
	 * @return
	 */
	List<Hb67> getClasssList(Hb67 hb67);

	/**
	 * ��ȡ�ϲ��༶�µ�ѧԱ��Ϣ�б�
	 * @param stu
	 * @return
	 */
	List<Student> getStuList(Student stu);

	/**
	 * ͨ��ѧԱ�����ȡCE01
	 * @param s
	 * @return
	 */
	Ce01 getCe01(String chc001);

	/**
	 * ��ȡ��ӦѧԱ����
	 * @param s
	 * @return
	 */
	String getHc60(String chc001);

	/**
	 * У���Ƿ��ظ�����
	 * @param chc001
	 * @param chb067
	 * @return
	 */
	String check(@Param(value="aac001") String eec001, @Param(value="chb067") String chb067);

	/**
	 * ���漼����ѵ����������Ϣ
	 * @param stu
	 */
	void save(Student stu);

	/**
	 * ���ݺϲ��༶�����ѯ�ϲ��༶��Ϣ
	 * @param chb067
	 * @return
	 */
	Hb67 getHb67ById(String chb067);

	/**
	 * ѧԱ���벹����Ϣ
	 * @param stu
	 * @return
	 */
	List<Student> getApplyStusForLook(Student stu);

	/**
	 * �õ����κ�
	 * @return
	 */
	String getEec100_suffix();

	/**
	 * �������ݵ������α�(����ϵͳ�ǵ���ģ����ϵͳ���ɣ��Ա㲹��ϵͳ�ܲ����)
	 */
	void insertDd41(Student stu);


}
