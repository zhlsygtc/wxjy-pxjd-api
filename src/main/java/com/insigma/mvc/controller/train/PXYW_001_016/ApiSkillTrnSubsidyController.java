package com.insigma.mvc.controller.train.PXYW_001_016;

import javax.annotation.Resource;

import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageInfo;
import com.insigma.dto.AjaxReturnMsg;
import com.insigma.mvc.MvcHelper;
import com.insigma.mvc.model.train.Hb67;
import com.insigma.mvc.model.train.Student;
import com.insigma.mvc.service.train.PXYW_001_016.ApiSkillTrnSubsidyService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(description = "技能培训补贴控制器")
@RequestMapping("/api/jnpxbt")
public class ApiSkillTrnSubsidyController extends MvcHelper{
	
	@Resource
	private ApiSkillTrnSubsidyService apiSkillTrnSubsidyService;
	
	/**
	 * 获取合并班级信息列表
	 * @param hb67 合并班级对象
	 * @return
	 */
	@ApiOperation(value = "合并班级搜索", notes = "合并班级搜索")
    @RequestMapping(value = "/getClasssList", method = RequestMethod.POST)
	public AjaxReturnMsg getClasssList(@ModelAttribute Hb67 hb67, BindingResult result ) throws Exception {
		if (result.hasErrors()) {
            return validate(result);
        }
		hb67.setAab001(hb67.getBaseinfoid());
		PageInfo<Hb67> pageinfo = apiSkillTrnSubsidyService.getClasssList(hb67);
		return this.success(pageinfo);
	}
	
	/**
	 * 获取合并班级下的学员信息列表
	 * @param stu 学员对象
	 * @return
	 */
	@ApiOperation(value = "合并班级下的合格人员搜索", notes = "合并班级下的合格人员搜索")
    @RequestMapping(value = "/getStuList", method = RequestMethod.POST)
	public AjaxReturnMsg getStuList(@ModelAttribute Student stu ) throws Exception {
		PageInfo<Student> pageinfo = apiSkillTrnSubsidyService.getStuList(stu);
		return this.success(pageinfo);
	}
	
	/**
	 * 申请保存
	 */
	@ApiOperation(value = "申请保存", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/apply",method = RequestMethod.POST)
	public AjaxReturnMsg apply(Student stu) throws Exception {
		return apiSkillTrnSubsidyService.saveStu(stu);
	}
	
	/**
	 * 根据合并班级内码查询合并班级信息 
	 */
    @RequestMapping(value = "/getHb67ById")
    public AjaxReturnMsg getHb67ById(Hb67 hb67) throws Exception{
		return this.success(apiSkillTrnSubsidyService.getHb67ById(hb67.getChb067()));
    }
	
    /**
	 * 学员申请补贴信息
	 * @param stu 学员对象
	 * @return
	 */
	@ApiOperation(value = "学员申请补贴信息 ", notes = "学员申请补贴信息 ")
    @RequestMapping(value = "/getApplyStusForLook", method = RequestMethod.POST)
	public AjaxReturnMsg getApplyStusForLook(@ModelAttribute Student stu) throws Exception {
		PageInfo<Student> pageinfo = apiSkillTrnSubsidyService.getApplyStusForLook(stu);
		return this.success(pageinfo);
	}
    
}
