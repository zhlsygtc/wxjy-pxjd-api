package com.insigma.mvc.service.train.PXYW_001_016;

import com.github.pagehelper.PageInfo;
import com.insigma.dto.AjaxReturnMsg;
import com.insigma.mvc.model.train.Hb67;
import com.insigma.mvc.model.train.Student;

public interface ApiSkillTrnSubsidyService {

	/**
	 * 获取合并班级信息列表
	 * @param hb67
	 * @return
	 */
	PageInfo<Hb67> getClasssList(Hb67 hb67);

	/**
	 * 获取合并班级下的学员信息列表
	 * @param stu
	 * @return
	 */
	PageInfo<Student> getStuList(Student stu);

	/**
	 * 申请保存
	 * @param stu
	 * @return
	 */
	AjaxReturnMsg saveStu(Student stu);

	/**
	 * 根据合并班级内码查询合并班级信息 
	 * @param chb067
	 * @return
	 */
	Hb67 getHb67ById(String chb067);

	/**
	 * 学员申请补贴信息
	 * @param stu
	 * @return
	 */
	PageInfo<Student> getApplyStusForLook(Student stu);

}
