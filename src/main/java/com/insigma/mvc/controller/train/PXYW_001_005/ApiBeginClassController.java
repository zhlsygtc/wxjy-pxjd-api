package com.insigma.mvc.controller.train.PXYW_001_005;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import com.insigma.mvc.MvcHelper;
import com.insigma.mvc.model.CodeValue;
import com.insigma.mvc.model.train.Ad01;
import com.insigma.mvc.model.train.Hb68;
import com.insigma.mvc.model.train.Hb69;
import com.insigma.mvc.model.train.Hb69Temp;
import com.insigma.mvc.model.train.SmtGroup;
import com.insigma.mvc.model.train.Student;
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
import com.insigma.mvc.service.train.PXYW_001_005.ApiBeginClassService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
/**
 * 开班申报contoller
 * @author zhanghl
 */
@RestController
@Api(description = "开班申报控制器")
@RequestMapping("/api/beginClass")
public class ApiBeginClassController extends MvcHelper{
	@Resource
	private ApiBeginClassService beginClassService;
	/**
	 * 初始化开班基础信息列表
	 */
	@ApiOperation(value = "获取班级基础信息", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/getInfoList",method = RequestMethod.POST)
	public AjaxReturnMsg getClassList(Hb68 hb68) throws Exception {
		PageInfo<Hb68> pageInfo = beginClassService.getInfoList(hb68);
		return this.success(pageInfo);
		
	}
    /**
	 * 查询学员信息
	 */
	@ApiOperation(value = "查询学员信息", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/getStuList",method = RequestMethod.POST)
	public AjaxReturnMsg getStuList(Student stu) throws Exception {
		PageHelper.offsetPage(stu.getOffset(), stu.getLimit());
    	PageInfo<Student> pageInfo = new PageInfo<Student>(beginClassService.getStuList(stu));
		return this.success(pageInfo);
		
	}
//	/**
//	 * 查询课程表信息
//	 */
//	@ApiOperation(value = "查询课程表信息", produces = MediaType.APPLICATION_JSON_VALUE)
//    @RequestMapping(value = "/getCourseList",method = RequestMethod.POST)
//	public AjaxReturnMsg getCourseList(Hb69 hb69) throws Exception {
//		PageHelper.offsetPage(hb69.getOffset(), hb69.getLimit());
//    	PageInfo<Hb69> pageInfo = new PageInfo<Hb69>(beginClassService.getCourseListForLook(hb69));
//		return this.success(pageInfo);
//		
//	}
    /**
	 * 初始化页面_获取可选学员
	 */
	@ApiOperation(value = "获取可选学员", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/getCheck",method = RequestMethod.POST)
	public AjaxReturnMsg getCheck(Student stu) throws Exception {
    	PageHelper.offsetPage(0,1000);//学员显示在一页，方便选择
    	PageInfo<Student> pageInfo = new PageInfo<Student>(beginClassService.getCheck(stu));
		return this.success(pageInfo);
	}
	/**
	 * 查询smt_group对象
	 */
	@ApiOperation(value = "查询smt_group对象", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/getSmtGroupById",method = RequestMethod.POST)
	public AjaxReturnMsg getSmtGroupById(SmtGroup smtGroup) throws Exception{
		smtGroup = beginClassService.getSmtGroupById(smtGroup.getGroupid());
		return this.success(smtGroup);
	}
	/**
     * 获取上级部门
     * @param stu 学员对象
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "获取上级部门", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/getAab301List", method = RequestMethod.GET)
    public AjaxReturnMsg<List<CodeValue>> getAab301List() throws Exception {
        return beginClassService.getAab301List();
    }
    /**
	 * 查询该公司所属的行政区划
	 */
	@ApiOperation(value = "查询该公司所属的行政区划", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/getCompanyAab301",method = RequestMethod.POST)
	public AjaxReturnMsg getCompanyAab301(SmtGroup smtGroup) throws Exception{
		smtGroup = beginClassService.getCompanyAab301(smtGroup.getGroupid());
		return this.success(smtGroup);
	}
    /**
	 * 修改开班信息
	 */
	@ApiOperation(value = "修改开班信息", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/getById",method = RequestMethod.POST)
	public AjaxReturnMsg getById(Hb68 hb68,Ad01 ad01) throws Exception{
		hb68 = beginClassService.getById(hb68.getChb100());
		return this.success(hb68);
	}
	/**
     * 获取机构培训资质
     *
     * @param stu 学员对象
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "保存学员信息", notes = "保存学员信息", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/getAca111List", method = RequestMethod.POST)
    public AjaxReturnMsg<List<CodeValue>> getAca111List(@ModelAttribute CodeValue codevalue) throws Exception {
        return beginClassService.getAca111List(codevalue);
    }
    /**
	 * 保存开班基础信息
	 */
	@ApiOperation(value = "保存开班基础信息", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/saveBaseInfo",method = RequestMethod.POST)
	public AjaxReturnMsg saveBaseInfo(Hb68 hb68) throws Exception {
		return beginClassService.saveBaseInfo(hb68);
	}
    /**
	 * 保存学员信息
	 */
	@ApiOperation(value = "保存学员信息", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/saveStu",method = RequestMethod.POST)
	public AjaxReturnMsg saveStu(Student stu) throws Exception {
    	String[] chc111s = stu.getSelectnodes().split(",");
    	stu.setChc111s(chc111s);
		return beginClassService.saveStu(stu);
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
		return beginClassService.delClass(hb68);
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
		return beginClassService.submitClass(hb68);
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
		return beginClassService.revokeClass(hb68);
	}
	/**
	 * 查看班级信息
	 * @param hb68
	 * @return
	 * @throws Exception
	 */
	@ApiOperation(value = "查看开班信息", produces = MediaType.APPLICATION_JSON_VALUE)
	@RequestMapping(value="/doLook",method = RequestMethod.POST)
	public AjaxReturnMsg<String> doLook(Hb68 hb68)throws Exception {
		hb68 = beginClassService.getById(hb68.getChb100());
		return this.success(hb68);
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
		PageInfo<Student> pageInfo = beginClassService.getStuListForLook(stu);
		return this.success(pageInfo);
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
		//PageHelper.offsetPage(0,1000);//课程表信息显示在一页，方便查看
		PageInfo<Hb69> pageInfo = new PageInfo<Hb69>(beginClassService.getCourseListForLook(hb69));
		return this.success(pageInfo);
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
		PageInfo<Hb69Temp> pageinfo = beginClassService.getTempCourseList(temp);
		return this.success(pageinfo);
	}
	/**
	 * 录入课程表————校验加保存课程表信息
	 */
	@ApiOperation(value = "录入加保存课程表信息", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/varifyCourse",method = RequestMethod.POST)
	public AjaxReturnMsg varifyCourse(Hb69Temp hb69Temp) throws Exception {
		return beginClassService.varifyCourse(hb69Temp);
	}
    /**
	 * 导入课程表————保存课程表信息
	 */
	@ApiOperation(value = "保存课程表信息", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/saveCourseData",method = RequestMethod.POST)
	public AjaxReturnMsg saveCourseData(Hb69Temp hb69Temp) throws Exception {
		String[] chb069s = hb69Temp.getSelectnodes().split(",");
		hb69Temp.setChb069s(chb069s);
		return beginClassService.saveCourseData(hb69Temp);
	}
	/**
	 * 录入课程表————保存课程表信息
	 */
	@ApiOperation(value = "录入保存课程表信息", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/saveCourse",method = RequestMethod.POST)
	public AjaxReturnMsg saveCourse(Hb69Temp hb69Temp) throws Exception {
		return beginClassService.saveCourse(hb69Temp);
	}
	/**
	 * 修改课程表
	 */
	@ApiOperation(value = "修改课程表", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/saveCourseForChange",method = RequestMethod.POST)
	public AjaxReturnMsg saveCourseForChange(Hb69Temp hb69Temp) throws Exception {
		return beginClassService.saveCourseForChange(hb69Temp);
	}
	/**
     * 查询同操作人员同类型最新的导入
     * @param temp 人员对象
     * @param result
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "查询同操作人员同类型最新的导入", notes = "查询同操作人员同类型最新的导入", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/getExcel_batch_number", method = RequestMethod.POST)
    public AjaxReturnMsg getExcel_batch_number(@ModelAttribute Hb69Temp hb69Temp) throws Exception {
    	return beginClassService.getExcel_batch_number(hb69Temp);
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
		return beginClassService.delStudent(chc001);
	}
    /**
   	 * 根据专业名称查询培训工种名称及专业类别
   	 */
	@ApiOperation(value = "根据专业名称查询培训工种名称及专业类别", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/getAca112",method = RequestMethod.POST)
   	public AjaxReturnMsg getAca112(Hb68 hb68) throws Exception{
   		hb68 = beginClassService.getAca112(hb68.getAca111());
   		return this.success(hb68);
   	}
	/**
   	 * 根据专业名称查询补贴标准
   	 */
	@ApiOperation(value = "根据专业名称查询补贴标准", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/getAca131",method = RequestMethod.POST)
   	public AjaxReturnMsg getAca131(Hb68 hb68) throws Exception{
   		hb68 = beginClassService.getAca131(hb68.getAca109());
   		return this.success(hb68);
   	}
	/**
	 * 查看修改课程表页面的当前课程信息
	 * @param chb068
	 * @return
	 * @throws Exception
	 */
	@ApiOperation(value = "查看修改课程表页面的当前课程信息", produces = MediaType.APPLICATION_JSON_VALUE)
	@RequestMapping(value="/getCourseListForChange",method = RequestMethod.POST)
	public AjaxReturnMsg<String> getCourseListForChange(Hb69 hb69)throws Exception {
		PageInfo<Hb69> pageInfo = new PageInfo<Hb69>(beginClassService.getCourseListForChange(hb69));
		return this.success(pageInfo);
	}
}