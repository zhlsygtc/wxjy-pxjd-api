package com.insigma.mvc.dao.train.PXYW_001_005;

import com.insigma.mvc.model.CodeValue;
import com.insigma.mvc.model.SysExcelBatch;
import com.insigma.mvc.model.train.*;
import java.util.List;
/**
 * 开班申报
 * @author Administrator 
 * 2018-01-10
 */
public interface ApiBeginClassMapper {
	/**
	 * 查询培训办班备案列表
	 */
	List<Hb68> getInfoList(Hb68 hb68) throws Exception;
	 /**
	 * 查询培训办班备案信息
	 */
	Hb68 getById(String chb100) throws Exception;
	/**
	 * 获取单位资质
	 */
	public List<CodeValue> getAca111List(CodeValue codevalue);
	/**
	 * 获取上级部门
	 */
	public List<CodeValue> getAab301List();
	/**
	 * 查询该机构所属行政区划
	 */
	SmtGroup getCompanyAab301(String groupId) throws Exception;
	/**
	 * 获取smtgroup对象
	 */
	SmtGroup getSmtGroupById(String groupId) throws Exception;
	/**
	 * 获取开班编号
	 */
	String getChb100() throws Exception;
	/**
	 * (新增)保存培训办班备案基础信息
	 */
	int saveBaseInfo(Hb68 hb68) throws Exception;
	/**
	 * (修改)保存培训办班备案基础信息
	 */
	int updateBaseInfo(Hb68 hb68) throws Exception;
	/**
	 * 根据专业名称查询培训工种名称及专业类别
	 */
	Hb68 getAca112(String aca110) throws Exception;
	/**
	 * 根据专业名称查询补贴标准
	 */
	Hb68 getAca131(String aca109) throws Exception;
	/**
	 * 查询学员库学员信息
	 */
	List<Student> getStuList(Student stu) throws Exception;
	/**
	 * 查询可选学员信息
	 */
	List<Student> getCheck(Student stu) throws Exception;
	/**
	 * 保存勾选的学员信息到hb60
	 */
	int saveStu(Student stu) throws Exception;
	/**
	 * 新增时更新61表被选择的学员被选中状态
	 */
	int updateHc61(Student stu) throws Exception;
	/**
	 * 更新61表被移除的学员可选中状态
	 */
	int updatePassHc61(Student stu) throws Exception;
	/**
	 * 删除60表被移除的学员
	 */
	int delPassHc60(Student stu) throws Exception;
	/**
	 * 删除开班信息(2步)
	 */
	//1.删除班级信息(改为无效)
	int delClassBase(Hb68 hb68) throws Exception;
	//2.删除学员信息(改为无效)
	int delClassStu(Hb68 hb68) throws Exception;
	/**
	 * 提交开班信息
	 */
	int submitClass(Hb68 hb68) throws Exception;
	/**
	 * 校验当前班级成绩录入状态
	 */
	Hb68 checkStatus(Hb68 hb68) throws Exception;
	/**
	 * 撤销开班信息
	 */
	int revokeClass(Hb68 hb68) throws Exception;
	/**
	 * 查询培训学员信息
	 */
	List<Student> getStuListForLook(String chb100) throws Exception;
	/**
	 * 查询培训课程表信息
	 */
	List<Hb69> getCourseListForLook(String chb100) throws Exception;
	/**
	 * 获取导入的课程表信息
	 */
	List<Hb69Temp> getTempCourseList(Hb69Temp temp);
	/**
	 * 删除课程表
	 */
	int delHb69(String chb100) throws Exception;
	/**
	 * 之前调整过课程表，现删除当前有效的课程表
	 */
	int delEffectHb69(String chb100) throws Exception;
	/**
	 * 查询当前69表中是否已有课程表
	 */
	String findHb69(String chb100) throws Exception;
	/**
	 * 将69表中课程表改为无效
	 */
	int changeToNoEffect(String chb100) throws Exception;
	/**
	 * 删除课程缓存表
	 */
	int delHb69Temp(String chb100) throws Exception;
	/**
	 * 保存课程表hb69
	 */
	int insertHb69(Hb69Temp hb69Temp) throws Exception;
	/**
	 *根据chb100获取chb068
	 */
	String getChb068(String chb100) throws Exception;
	/**
	 * 插入临时表
	 */
	public void insertExcelTempData(Hb69Temp temp);
	/**
	 * 执行存储过程 
	 */
    public void executeProc(SysExcelBatch sExcelBatch);
	/**
	 * 查询导入批次号
	 */
	public String getExcel_batch_number(Hb69Temp hb69Temp);
	/**
	 * 删除培训学员
	 */
	int delHc60(String chc001);
	/**
	 * 删除学员时更新61表被选择的学员被选中状态
	 */
	int updateHc61ById(String chc001);
	/**
	 * 删除班级时更新61表被选择的学员被选中状态
	 */
	void updateStu(Hb68 hb68);
	/**
	 * 插入hb69temp表数据
	 */
	int insertHb69Temp(Hb69Temp hb69Temp);
	/**
	 * 插入课程表hb69数据
	 */
	int insertHb69Input(Hb69Temp hb69Temp);
	/**
	 * 插入课程表hb69————修改课程表
	 */
	int insertHb69InputForChange(Hb69Temp hb69Temp);
	/**
	 * 查询该班级hb69temp表中的数据
	 */
	List<Hb69Temp> getHb69tempList(Hb69Temp hb69Temp) throws Exception;
}
