package com.insigma.mvc.service.train.PXYW_001_005;

import com.github.pagehelper.PageInfo;
import com.insigma.dto.AjaxReturnMsg;
import com.insigma.mvc.model.CodeValue;
import com.insigma.mvc.model.train.*;
import java.util.List;
/**
 * 开班申请
 * @author zhanghl
 * 2018-01-10
 */
public interface ApiBeginClassService {
	/**
	 * 查询培训业务办班备案列表
	 */
	public PageInfo<Hb68> getInfoList(Hb68 hb68) throws Exception;
	/**
	 * 根据ID查询班级信息
	 */
	public Hb68 getById(String chb100) throws Exception;
	/**
	 * 获取单位资质
	 */
	AjaxReturnMsg<List<CodeValue>> getAca111List(CodeValue codevalue);
	/**
	 * 获取上级部门
	 */
	AjaxReturnMsg<List<CodeValue>> getAab301List();
	/**
	 * 查询该公司所属行政区划
	 */
	public SmtGroup getCompanyAab301(String groupId) throws Exception;
	/**
	 * 根据id获取smtgroup
	 */
	public SmtGroup getSmtGroupById(String groupId) throws Exception;
	/**
	 * 保存培训办班备案基础信息
	 */
	public AjaxReturnMsg saveBaseInfo(Hb68 hb68) throws Exception;
	/**
	 * 根据专业名称查询培训工种名称及专业类别
	 */
	public Hb68 getAca112(String aca110) throws Exception;
	/**
	 * 根据专业名称查询补贴标准
	 */
	public Hb68 getAca131(String aca109) throws Exception;
	/**
	 * 查询学员库学员列表
	 */
	public List<Student> getStuList(Student stu) throws Exception;
	/**
	 * 查询勾选的学员信息
	 */
	public List<Student> getCheck(Student stu) throws Exception;
	/**
	 * 新增学员至学员表hb60
	 */
	public AjaxReturnMsg saveStu(Student stu) throws Exception;
	/**
	 * 根据主键删除开班信息
	 */
	public AjaxReturnMsg<String> delClass(Hb68 hb68);
	/**
	 * 提交开班信息
	 */
	public AjaxReturnMsg<String> submitClass(Hb68 hb68);
	/**
	 * 撤销开班信息
	 */
	public AjaxReturnMsg<String> revokeClass(Hb68 hb68);
	/**
	 * 查询培训学员信息
	 */
	public PageInfo<Student> getStuListForLook(Student stu) throws Exception;
	/**
	 * 查询培训课程表信息
	 */
	public List<Hb69> getCourseListForLook(Hb69 hb69) throws Exception;
	/**
	 * 查询改变后的课程表信息
	 */
	public List<Hb69> getCourseListForChange(Hb69 hb69) throws Exception;
	/**
	 * 查询导入课程表
	 */
	PageInfo<Hb69Temp> getTempCourseList(Hb69Temp temp);
	/**
	 * 录入加保存课程表
	 */
	public AjaxReturnMsg varifyCourse(Hb69Temp temp) throws Exception;
	/**
	 * 导入---新增课程表hb69
	 */
	public AjaxReturnMsg saveCourseData(Hb69Temp temp) throws Exception;
	/**
	 * 录入---新增课程表hb69
	 */
	public AjaxReturnMsg saveCourse(Hb69Temp temp) throws Exception;
	/**
	 * 修改课程表
	 */
	public AjaxReturnMsg saveCourseForChange(Hb69Temp temp) throws Exception;
	/**
	 * 查询导入批次号
	 */
	public AjaxReturnMsg getExcel_batch_number(Hb69Temp hb69Temp);
	/**
	 * 删除培训学员
	 */
	public AjaxReturnMsg<String> delStudent(String chc001);
}
