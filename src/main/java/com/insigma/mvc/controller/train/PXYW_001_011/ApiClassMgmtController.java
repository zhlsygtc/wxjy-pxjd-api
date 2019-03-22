package com.insigma.mvc.controller.train.PXYW_001_011;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.insigma.dto.AjaxReturnMsg;
import com.insigma.mvc.MvcHelper;
import com.insigma.mvc.model.CodeValue;
import com.insigma.mvc.model.train.Hb65;
import com.insigma.mvc.model.train.Hb68;
import com.insigma.mvc.model.train.Hb69;
import com.insigma.mvc.model.train.Hb69Temp;
import com.insigma.mvc.model.train.Student;
import com.insigma.mvc.service.train.PXYW_001_011.ApiClassMgmtService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 班级管理contoller
 * @author yugw
 */
@RestController
@Api(description = "班级管理控制器")
@RequestMapping("/api/class")
public class ApiClassMgmtController extends MvcHelper{

	@Resource
	private ApiClassMgmtService apiClassMgmtService;
	
	/**
	 * 初始化班级信息列表 
	 */
	@ApiOperation(value = "获取班级基础信息", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/getInfoList",method = RequestMethod.POST)
	public AjaxReturnMsg getClassList(Hb68 hb68) throws Exception {
		PageInfo<Hb68> pageInfo = apiClassMgmtService.getInfoList(hb68);
		return this.success(pageInfo);
		
	}
	
	/**
     * 获取机构培训计划
     *
     * @param codevalue 代码对象
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "获取机构培训计划", notes = "获取机构培训计划", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/getPlans", method = RequestMethod.POST)
    public AjaxReturnMsg<List<CodeValue>> getPlans(@ModelAttribute CodeValue codevalue) throws Exception {
        return apiClassMgmtService.getPlans(codevalue);
    }
	
	/**
	 * 根据id获取培训计划信息
	 * @param planid 计划内码
	 * @return
	 */
	@ApiOperation(value = "根据id获取培训计划信息", notes = "根据id获取培训计划信息", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/getPlan", method = RequestMethod.POST)
    public AjaxReturnMsg getTeacherInfo(Hb65 hb65) throws Exception {
        return apiClassMgmtService.getPlanById(hb65.getChb055());
    }
	
	/**
	 * 保存开班基础信息
	 */
	@ApiOperation(value = "保存开班基础信息", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/saveBaseInfo",method = RequestMethod.POST)
	public AjaxReturnMsg saveBaseInfo(Hb68 hb68) throws Exception {
		return apiClassMgmtService.saveBaseInfo(hb68);
	}
	
	/**
	 * 查询学员信息
	 */
	@ApiOperation(value = "查询学员信息", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/getStuList",method = RequestMethod.POST)
	public AjaxReturnMsg getStuList(Student stu) throws Exception {
		PageHelper.offsetPage(stu.getOffset(), stu.getLimit());
    	PageInfo<Student> pageInfo = new PageInfo<Student>(apiClassMgmtService.getStuList(stu));
		return this.success(pageInfo);
		
	}
	
	/**
	 * 初始化页面_获取可选学员
	 */
	@ApiOperation(value = "获取可选学员", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/getCheck",method = RequestMethod.POST)
	public AjaxReturnMsg getCheck(Student stu) throws Exception {
    	PageHelper.offsetPage(0,1000);//学员显示在一页，方便选择
    	PageInfo<Student> pageInfo = new PageInfo<Student>(apiClassMgmtService.getCheck(stu));
		return this.success(pageInfo);
	}
	
	/**
	 * 保存学员信息
	 */
	@ApiOperation(value = "保存学员信息", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/saveStu",method = RequestMethod.POST)
	public AjaxReturnMsg saveStu(Student stu) throws Exception {
    	String[] chc111s = stu.getSelectnodes().split(",");
    	stu.setChc111s(chc111s);
		return apiClassMgmtService.saveStu(stu);
	}
	
	/**
	 * 根据id删除培训学员
	 * @param request
	 * @param model
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "根据id删除培训学员", produces = MediaType.APPLICATION_JSON_VALUE)
	@RequestMapping(value="/delStudent/{chc001}",method = RequestMethod.DELETE)
	public AjaxReturnMsg<String> delStudent(HttpServletRequest request,@PathVariable String chc001){
		return apiClassMgmtService.delStudent(chc001);
	}
	
	/**
	 * 查看课程表信息
	 * @param chb068
	 * @return
	 * @throws Exception
	 */
	@ApiOperation(value = "查看课程表信息", produces = MediaType.APPLICATION_JSON_VALUE)
	@RequestMapping(value="/getCourseListForLook",method = RequestMethod.POST)
	public AjaxReturnMsg<String> getCourseListForLook(Hb69 hb69)throws Exception {
		PageHelper.offsetPage(0,1000);//课程表信息显示在一页，方便查看
		PageInfo<Hb69> pageInfo = new PageInfo<Hb69>(apiClassMgmtService.getCourseListForLook(hb69));
		return this.success(pageInfo);
	}
	
	/**
     * 查询同操作人员同类型最新的导入
     * @param temp 人员对象
     * @param result
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "查询同操作人员同类型最新的导入", notes = "查询同操作人员同类型最新的导入", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/getSysexcelbatch", method = RequestMethod.POST)
    public AjaxReturnMsg getSysexcelbatch(@ModelAttribute Hb69Temp hb69Temp) throws Exception {
    	return apiClassMgmtService.getSysexcelbatch(hb69Temp);
    }
	
	/**
	 * 获取导入课程表列表
	 * @param temp 课程表导入对象
	 * @return
	 */
	@ApiOperation(value = "课程表导入列表显示", notes = "课程表导入结果显示")
    @RequestMapping(value = "/getTempCourseList", method = RequestMethod.POST)
	public AjaxReturnMsg getTempCourseList(@ModelAttribute Hb69Temp temp, BindingResult result ) throws Exception {
		if (result.hasErrors()) {
            return validate(result);
        }
		PageHelper.offsetPage(0,1000);//课程表信息显示在一页，方便查看
		PageInfo<Hb69Temp> pageinfo = apiClassMgmtService.getTempCourseList(temp);
		return this.success(pageinfo);
	}
	
	/**
	 * 保存课程表信息
	 */
	@ApiOperation(value = "保存课程表信息", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/saveCourseData",method = RequestMethod.POST)
	public AjaxReturnMsg saveCourseData(Hb69Temp hb69Temp) throws Exception {
		String[] chb069s = hb69Temp.getSelectnodes().split(",");
		hb69Temp.setChb069s(chb069s);
		return apiClassMgmtService.saveCourseData(hb69Temp);
	}
	
	/**
	 * 提交开班信息
	 * @param hb68
	 * @return
	 * @throws Exception
	 */
	@ApiOperation(value = "提交开班信息", produces = MediaType.APPLICATION_JSON_VALUE)
	@RequestMapping(value="/submitClass",method = RequestMethod.POST)
	public AjaxReturnMsg<String> submitClass(Hb68 hb68)throws Exception {
		return apiClassMgmtService.submitClass(hb68);
	}
	
	/**
	 * 撤销开班信息
	 * @param hb68
	 * @return
	 * @throws Exception
	 */
	@ApiOperation(value = "撤销开班信息", produces = MediaType.APPLICATION_JSON_VALUE)
	@RequestMapping(value="/revokeClass",method = RequestMethod.POST)
	public AjaxReturnMsg<String> revokeClass(Hb68 hb68)throws Exception {
		return apiClassMgmtService.revokeClass(hb68);
	}
	
	/**
	 * 查看班级信息
	 * @param chb068
	 * @return
	 * @throws Exception
	 */
	@ApiOperation(value = "查看开班信息", produces = MediaType.APPLICATION_JSON_VALUE)
	@RequestMapping(value="/getById",method = RequestMethod.POST)
	public AjaxReturnMsg<String> getById(Hb68 hb68)throws Exception {
		hb68 = apiClassMgmtService.getById(hb68.getChb068());
		return this.success(hb68);
	}
	
	/**
	 * 根据id删除开班信息(改为无效)
	 * @param request
	 * @param model
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "根据id删除开班信息(改为无效)", produces = MediaType.APPLICATION_JSON_VALUE)
	@RequestMapping(value="/delClass",method = RequestMethod.POST)
	public AjaxReturnMsg<String> delClass(Hb68 hb68)throws Exception {
		return apiClassMgmtService.delClass(hb68);
	}
	
	/**
	 * 查看学员信息
	 * @param chb068
	 * @return
	 * @throws Exception
	 */
	@ApiOperation(value = "查看学员信息", produces = MediaType.APPLICATION_JSON_VALUE)
	@RequestMapping(value="/getStuListForLook",method = RequestMethod.POST)
	public AjaxReturnMsg<String> getStuListForLook(Student stu)throws Exception {
		PageInfo<Student> pageInfo = apiClassMgmtService.getStuListForLook(stu);
		return this.success(pageInfo);
	}

	/**
	 * 导出学员花名册
	 */
	@ApiOperation(value = "导出学员花名册", notes = "导出花名册", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/expStuRoster", method = RequestMethod.POST)
    public AjaxReturnMsg expStuRoster(@ModelAttribute  Student stu) throws Exception {
    	List<HashMap> list = apiClassMgmtService.expStuRoster(stu);
    	Map<String, Object> result = new HashMap<>();
        result.put("list", list);
        return  this.success(result);
    }
	/**
	 * 获取导出开班申请确认标所需信息
	 * @param stu 学员对象
	 * @return
	 */
	@ApiOperation(value = "获取导出开班申请确认标所需信息", notes = "获取导出开班申请确认标所需信息", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/expClassSure", method = RequestMethod.POST)
    public AjaxReturnMsg expClassSure(@ModelAttribute Hb68 hb68) throws Exception {
        return apiClassMgmtService.expClassSure(hb68);
    }
	/**
	 * 获取导出课程表所需信息
	 * @param chb068
	 * @return
	 * @throws Exception
	 */
	@ApiOperation(value = "获取导出课程表所需信息", produces = MediaType.APPLICATION_JSON_VALUE)
	@RequestMapping(value="/getClassCourse",method = RequestMethod.POST)
	public AjaxReturnMsg getClassCourse(Hb69 hb69)throws Exception {
		List<Hb69> list = apiClassMgmtService.getClassCourse(hb69);
        return  this.success(list);
	}
	/**
	 * 查看班级附件信息
	 * @param hb68
	 * @return
	 * @throws Exception
	 */
	@ApiOperation(value = "查看班级附件信息", produces = MediaType.APPLICATION_JSON_VALUE)
	@RequestMapping(value="/getClassFile",method = RequestMethod.POST)
	public AjaxReturnMsg getClassFile(Hb68 hb68) throws Exception {
        return apiClassMgmtService.getClassFile(hb68);
    }
}
