package com.insigma.mvc.service.train.PXYW_001_006;

import com.github.pagehelper.PageInfo;
import com.insigma.dto.AjaxReturnMsg;
import com.insigma.mvc.model.train.*;
/**
 * 开班申请
 * @author zhanghl
 * 2018-01-10
 */
public interface ApiInputScoreService {
	/**
	 * 查询培训业务办班备案列表
	 */
	public PageInfo<Hb68> getInfoList(Hb68 hb68) throws Exception;
	/**
	 * 根据ID查询班级信息
	 */
	public Hb68 getById(Hb68 hb68) throws Exception;
	/**
	 * 根据专业名称查询培训工种名称及专业类别
	 */
	public Hb68 getAca112(String aca110) throws Exception;
	/**
	 * 查询培训学员信息
	 */
	public PageInfo<Student> getStuListForLook(Student stu) throws Exception;
	/**
	 * 保存学员成绩--勾选合格标志
	 */
	public AjaxReturnMsg doQualified(Student stu) throws Exception;
	/**
	 * 保存学员成绩--手工录入
	 */
	public AjaxReturnMsg saveScore(Student stu) throws Exception;
	/**
	 * 提交班级成绩信息
	 */
	public AjaxReturnMsg submitScore(Hb68 hb68) throws Exception;
	/**
	 * 撤销成绩信息
	 */
	public AjaxReturnMsg<String> revokeScore(Hb68 hb68) throws Exception;
	/**
	 * 查询成绩标准
	 */
	public Zc13 getZc13(String aab001) throws Exception;
}
