package com.insigma.mvc.dao.train.PXYW_001_014;

import java.util.List;

import com.insigma.mvc.model.train.Hb50;
import com.insigma.mvc.model.train.Hb50Dto;
import com.insigma.mvc.model.train.Hb67;
import com.insigma.mvc.model.train.Hb69;
import com.insigma.mvc.model.train.Hc60;
import com.insigma.mvc.model.train.Student;

/**
 * 补贴申报
 * @author Ace
 *
 */
public interface ApiSubsidyMapper {

	/**
	 * 班期信息列表
	 */
	List<Hb67> getHb67List(Hb67 hb67) throws Exception;

	/**
	 * 根据班期内码查询班期信息
	 */
	Hb67 getHb67ById(Hb67 hb67) throws Exception;

	/**
	 * 生成补贴申请信息
	 */
	int addHb50(Hb50 hb50);

	/**
	 * 生成学员补贴信息
	 */
	int addHc50(Hb50 hb50);

	/**
	 * 查询班期信息
	 */
	Hb67 getHb67ById(String chb067) throws Exception;
	
	/**
	 * 查询合并班级中学员列表
	 */
	List<Hc60> getHc60StuList(Hc60 hc60) throws Exception;
	
	/**
	 * 查询班期下学员补贴列表 
	 */
	List<Student> subsidyList(Student stu);
	
	/**
	 * 删除学员补贴信息
	 */
	int delete(Student stu);
	
	/**
	 * 查询学员补写信息 
	 */
	Student getHc50ById(Student stu);
	
	/**
	 *  保存学员补贴信息
	 */
	int update(Student stu);
	
	/**
     * 提交班期补贴信息
     */
	int submit(Hb67 hb67);

	/**
	 * 删除现有补贴申请表
	 */
	int deleteHc50(Hb50 hb50);
	
	/**
	 * 删除现有补贴申请表
	 */
	int deleteHb50(Hb50 hb50);
	
	/**
	 * 根据补贴内码获取补贴信息
	 */
	public Hb50Dto getSubsidyById(String chb050);
	
	/**
	 * 导出培训补贴学员花名册
	 */
	List<Student> getexportStuBT(Student stu);

}
