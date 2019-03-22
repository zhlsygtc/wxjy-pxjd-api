package com.insigma.mvc.service.train.PXYW_001_016;

import com.github.pagehelper.PageInfo;
import com.insigma.dto.AjaxReturnMsg;
import com.insigma.mvc.model.train.Hb67;
import com.insigma.mvc.model.train.Student;

public interface ApiSkillTrnSubsidyService {

	/**
	 * ��ȡ�ϲ��༶��Ϣ�б�
	 * @param hb67
	 * @return
	 */
	PageInfo<Hb67> getClasssList(Hb67 hb67);

	/**
	 * ��ȡ�ϲ��༶�µ�ѧԱ��Ϣ�б�
	 * @param stu
	 * @return
	 */
	PageInfo<Student> getStuList(Student stu);

	/**
	 * ���뱣��
	 * @param stu
	 * @return
	 */
	AjaxReturnMsg saveStu(Student stu);

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
	PageInfo<Student> getApplyStusForLook(Student stu);

}
