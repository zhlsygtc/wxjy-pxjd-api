package com.insigma.mvc.service.train.PXYW_001_010;

import com.github.pagehelper.PageInfo;
import com.insigma.dto.AjaxReturnMsg;
import com.insigma.mvc.model.CodeValue;
import com.insigma.mvc.model.train.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;
/**
 * 开班计划
 * @author zhanghl
 * 2018-04-25
 */
public interface ApiClassPlanService {
	/**
	 * 查询开班计划信息列表
	 */
	public PageInfo<Hb65> getPlanList(Hb65 hb65) throws Exception;
	/**
	 * 保存培训计划信息
	 */
	public AjaxReturnMsg addPlan(Hb65 hb65) throws Exception;
	/**
	 * 根据ID查询培训计划信息
	 */
	public Hb65 getPlanById(Hb65 hb65) throws Exception;
	/**
	 * 获取单位资质
	 */
	AjaxReturnMsg<List<CodeValue>> getAca111List(CodeValue codevalue);
	/**
	 * 获取单位资质等级
	 */
	AjaxReturnMsg<List<CodeValue>> getAca11aList(String id, String aca111);
	/**
	 * 获取上级部门
	 */
	AjaxReturnMsg<List<CodeValue>> getAab301List();
	/**
	 * 查询该公司所属行政区划
	 */
	public SmtGroup getCompanyAab301(String groupId) throws Exception;
	/**
	 * 根据专业名称查询培训工种名称及专业类别
	 */
	public Hb65 getAca112(String aca111) throws Exception;
	/**
	 * 根据id获取smtgroup
	 */
	public SmtGroup getSmtGroupById(String groupId) throws Exception;
	/**
	 * 查看培训学员信息
	 */
	public PageInfo<Student> signedStuForLook(Student stu) throws Exception;
	/**
	 * 根据主键删除开班信息(改为无效)
	 */
	public AjaxReturnMsg<String> delPlan(Hb65 hb65);
	/**
	 * 改变培训计划对外状态
	 */
	public AjaxReturnMsg<String> changePlan(Hb65 hb65);
	/**
	 * 报名
	 */
	public AjaxReturnMsg signUp(Student stu) throws Exception;
	/**
	 * 根据ID查询培训学员信息
	 */
	public Hc61 findStuById(Student stu) throws Exception;
	/**
	 * 根据ID查询培训学员附件信息
	 */
	public Hc61 findFileById(Student stu) throws Exception;
	/**
	 * 上传学员头像
	 */
	AjaxReturnMsg uploadHc61Photo(String userid, String file_bus_id, String file_name, MultipartFile multipartFile);
	 /**
	  * 上传学员相关文件
     * @param request
     * @param multipartFile
     * @return
     * @throws Exception
     */
    AjaxReturnMsg fileUpload(HttpServletRequest request, MultipartFile multipartFile) throws Exception;
    /**
     * 根据id删除图片
     */
	public AjaxReturnMsg<String> delPicById(Student stu);
	/**
	 *批量导入学员 报名保存
	 */
	public AjaxReturnMsg signUpSave(Hc61_temp hc61Temp) throws Exception;
	/**
	 * 获取导出word所需信息
	 */
	public AjaxReturnMsg exportDJK(Student stu);
}
