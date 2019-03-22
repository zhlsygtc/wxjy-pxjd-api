package com.insigma.mvc.service.train.PXYW_001_011;

import java.util.HashMap;
import java.util.List;

import com.github.pagehelper.PageInfo;
import com.insigma.dto.AjaxReturnMsg;
import com.insigma.mvc.model.CodeValue;
import com.insigma.mvc.model.train.Hb68;
import com.insigma.mvc.model.train.Hb69;
import com.insigma.mvc.model.train.Hb69Temp;
import com.insigma.mvc.model.train.StuFileDTO;
import com.insigma.mvc.model.train.Student;

/**
 * �༶����
 * @author yugw
 * 2018-04-26
 */
public interface ApiClassMgmtService {

	/**
	 * ��ѯ��ѵҵ���౸���б�
	 */
	public PageInfo<Hb68> getInfoList(Hb68 hb68) throws Exception;

	public AjaxReturnMsg getPlanById(String planid);

	public AjaxReturnMsg saveBaseInfo(Hb68 hb68);

	public List<Student> getStuList(Student stu);

	public Hb68 getById(String chb068);

	public List<Student> getCheck(Student stu);

	public AjaxReturnMsg<String> delClass(Hb68 hb68);

	public AjaxReturnMsg saveStu(Student stu);

	public AjaxReturnMsg<String> delStudent(String chc001);

	public List<Hb69> getCourseListForLook(Hb69 hb69);

	public AjaxReturnMsg saveCourseData(Hb69Temp hb69Temp);

	public PageInfo<Hb69Temp> getTempCourseList(Hb69Temp temp);

	public AjaxReturnMsg getSysexcelbatch(Hb69Temp hb69Temp);

	public PageInfo<Student> getStuListForLook(Student stu);

	public AjaxReturnMsg<String> submitClass(Hb68 hb68);

	public AjaxReturnMsg<String> revokeClass(Hb68 hb68);

	public AjaxReturnMsg<List<CodeValue>> getPlans(CodeValue codevalue);
	/**
	 * ѧԱ����������
	 */
	List<HashMap> expStuRoster(Student stu) throws Exception ;
	/**
	 * ��ȡ������������ȷ�ϱ�������Ϣ
	 */
	public AjaxReturnMsg expClassSure(Hb68 hb68);
	/**
	 * ��ȡ�����γ̱�������Ϣ
	 * @param hb69
	 * @return
	 */
	public List<Hb69> getClassCourse(Hb69 hb69);
	/**
	 * ͨ��ҵ��ID��ȡ�ļ���Ϣ
	 * @param hb68
	 * @return
	 */
	AjaxReturnMsg getClassFile(Hb68 hb68);
}
