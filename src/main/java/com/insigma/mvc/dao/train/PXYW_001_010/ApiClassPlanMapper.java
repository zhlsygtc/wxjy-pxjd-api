package com.insigma.mvc.dao.train.PXYW_001_010;

import com.insigma.mvc.model.CodeValue;
import com.insigma.mvc.model.SysExcelBatch;
import com.insigma.mvc.model.train.*;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
/**
 * 开班计划
 * @author zhanghl
 * 2018-04-25
 */
public interface ApiClassPlanMapper {
	/**
	 * 查询培训计划信息
	 */
	List<Hb65> getPlanList(Hb65 hb65) throws Exception;
	/**
	 * (新增)保存培训计划信息
	 */
	int addPlan(Hb65 hb65) throws Exception;
	/**
	 * (修改)保存培训计划信息
	 */
	int updatePlan(Hb65 hb65) throws Exception;
	/**　
	 * 查询培训办班备案列表
	 */
	Hb65 getPlanById(String chb055) throws Exception;
	/**　
	 * 查询培训办班备案列表（大丰）
	 */
	Hb65 getPlanById_df(String chb055) throws Exception;
	/**　
	 * 查询培训办班备案列表（陕西）
	 */
	Hb65 getPlanById_sx(String chb055);
	/**
	 * 获取单位资质
	 */
	public List<CodeValue> getAca111List(CodeValue codevalue);
	/**
	 * 获取单位资质
	 */
	public List<CodeValue> getAca11aList(@Param(value="id")String id, @Param(value="aca111")String aca111);
	/**
	 * 获取单位资质（陕西）
	 */
	List<CodeValue> getAca111List_sx(CodeValue codevalue);
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
	 * 根据专业名称查询培训工种名称及专业类别
	 */
	Hb65 getAca112(String aca111) throws Exception;
	/**
	 * 根据专业名称查询培训工种名称及专业类别（大丰）
	 */
	Hb65 getAca112_df(String aca111) throws Exception;
	/**
	 * 根据专业名称查询培训工种名称及专业类别（陕西）
	 */
	Hb65 getAca112_sx(String aca111);
	/**
	/**
	 * 查看培训学员信息
	 */
	List<Student> signedStuForLook(String chb055) throws Exception;
	/**
	 * 删除培训计划(改为无效)
	 * @param hb68
	 * @return
	 * @throws Exception
	 */
	int delPlan(Hb65 hb65) throws Exception;
	/**
	 * 切换培训状态
	 * @param hb65
	 * @return
	 * @throws Exception
	 */
	int changePlan(Hb65 hb65) throws Exception;
	/**
	 * 查询学员信息
	 */
	Hc61 findStuById(Student stu) throws Exception;
	/**
	 * 查询学员附件信息
	 */
	Hc61 findFileById(Student stu) throws Exception;
	/**
	 * 报名
	 * @return 
	 */
	int signUp(Student stu) throws Exception;
	/**
	 * 新增学员
	 * @return 
	 */
	int addStu(Student stu) throws Exception;
	/**
	 * 修改学员
	 * @return 
	 */
	int updateStu(Student stu) throws Exception;
	/**
	 * 查询该学员是否报名该计划
	 * @return 
	 */
	String findStuWithPlan(Student stu) throws Exception;
	/**
	 * 上传照片
	 * @param hb61
	 * @return
	 */
	int uploadHc61Photo(Student stu);
	/**
	 * 校验类别种类
	 * @return 
	 */
	String trainTypeCount(Student stu) throws Exception;
	/**
	 *  校验培训工种
	 * @return 
	 */
	String trainProfeCount(Student stu) throws Exception;
	/**
	 * 删除图片
	 * @param picId
	 * @return
	 * @throws Exception
	 */
	int delFilePicById(String picId) throws Exception;
	int delBusFilePicById(String picId) throws Exception;
	
	/**
	 * 校验是否在职人员
	 */
	String getPersonWorkStatus(String aac002);
	/**
	 * 校验本年度是否已享受就业培训补贴
	 */
	String getPersonSubsidyStatus(String aac002);
	
	/**
	 * 校验学员五年内培训
	 */
	String checkHc60(@Param(value="aac002") String aac002,@Param(value="aca11a") String aca11a);
	String checkSignPlan(@Param(value="aac002") String aac002,@Param(value="aca11a") String aca11a,@Param(value="chb055") String chb055);
	/**
	 * 校验临时表数据
	 */
	public void executeProc(SysExcelBatch sExcelBatch);
	/**
	 * 获取当前计划剩余报名人数
	 */
	public String getPeople(Hc61_temp hc61Temp);
	/**
	 * 导出word所需信息
	 */
	public Student exportDJK(Student stu);
	
}
