package com.insigma.mvc.service.train.PXYW_001_014;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.insigma.dto.AjaxReturnMsg;
import com.insigma.mvc.model.train.Hb50;
import com.insigma.mvc.model.train.Hb67;
import com.insigma.mvc.model.train.Hb69;
import com.insigma.mvc.model.train.Hc60;
import com.insigma.mvc.model.train.Student;

/**
 * 补贴申报
 * @author Ace
 *
 */
public interface ApiSubsidyService {

	/**
	 * 班期信息列表
	 * @param hb67 班期对象
	 * @return
	 * @throws Exception
	 */
	public PageInfo<Hb67> getHb67List(Hb67 hb67) throws Exception;

	/**
     * 生成班期补贴信息
     */
	public AjaxReturnMsg generate(Hb67 hb67) throws Exception;

	/**
	 * 查询班期信息
	 */
	public Hb67 getHb67ById(String chb067) throws Exception;
	
	/**
	 * 查询合并班级中学员列表
	 */
	public PageInfo<Hc60> getHc60StuList(Hc60 hc60) throws Exception;
	
	/**
	 * 查询班期下学员补贴列表
	 */
	public PageInfo<Student> subsidyList(Student stu);
	
	/**
	 * 删除学员补贴信息
	 */
	public AjaxReturnMsg delete(Student stu);
	
	/**
	 * 保存学员补贴信息
	 */
	public AjaxReturnMsg update(Student stu);
	
	/**
	 *  查询学员补写信息 
	 */
	public Student getHc50ById(Student stu);
	
	/**
     * 重新生成班期补贴信息
     */
	public AjaxReturnMsg generateAgain(Hb50 hb50);
	
	/**
     * 提交班期补贴信息
     */
	public AjaxReturnMsg submit(Hb67 hb67);
	/**
	 * 根据补贴内码获取补贴细信息
	 */
	public AjaxReturnMsg getSubsidyById(String chb050);

	/**
	 * 获取导出课程表所需信息
	 * @param hb69
	 * @return
	 */
	public List<Student> getexportStuBT(Student stu);
}
