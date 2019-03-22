package com.insigma.mvc.dao.train.PXYW_001_016;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.insigma.mvc.model.Ce01;
import com.insigma.mvc.model.train.Hb67;
import com.insigma.mvc.model.train.Student;

public interface ApiSkillTrnSubsidyMapper {

	/**
	 * 获取合并班级信息列表
	 * @param hb67
	 * @return
	 */
	List<Hb67> getClasssList(Hb67 hb67);

	/**
	 * 获取合并班级下的学员信息列表
	 * @param stu
	 * @return
	 */
	List<Student> getStuList(Student stu);

	/**
	 * 通过学员内码获取CE01
	 * @param s
	 * @return
	 */
	Ce01 getCe01(String chc001);

	/**
	 * 获取对应学员姓名
	 * @param s
	 * @return
	 */
	String getHc60(String chc001);

	/**
	 * 校验是否重复申请
	 * @param chc001
	 * @param chb067
	 * @return
	 */
	String check(@Param(value="aac001") String eec001, @Param(value="chb067") String chb067);

	/**
	 * 保存技能培训补贴申请信息
	 * @param stu
	 */
	void save(Student stu);

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
	List<Student> getApplyStusForLook(Student stu);

	/**
	 * 得到批次号
	 * @return
	 */
	String getEec100_suffix();

	/**
	 * 补贴数据导入批次表(补贴系统是导入的，这边系统生成，以便补贴系统能查出来)
	 */
	void insertDd41(Student stu);


}
