package com.insigma.mvc.controller.app.APP_001_004;

import javax.annotation.Resource;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.insigma.dto.AjaxReturnMsg;
import com.insigma.mvc.MvcHelper;
import com.insigma.mvc.model.train.Student;
import com.insigma.mvc.service.app.APP_001_004.ApiPersonMgmtService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/personMgmt")
public class ApiPersonMgmtController extends MvcHelper{
	
	@Resource
	ApiPersonMgmtService apiPersonMgmtService ;
	
    
    /**
     * 学员报名计划开始
     * @param stu 学员对象
     * @throws Exception
     */
    @ApiOperation(value = "学员报名计划开始", notes = "学员报名计划开始", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public AjaxReturnMsg signup(@ModelAttribute Student stu) throws Exception {
        return apiPersonMgmtService.signup(stu);
    }
    
    /**
     * 获取学员基本信息
     * @param stu 学员对象
     * @throws Exception
     */
    @ApiOperation(value = "获取学员基本信息", notes = "获取学员基本信息", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/getStudentInfo", method = RequestMethod.POST)
    public AjaxReturnMsg getStudentInfo(@ModelAttribute Student stu) throws Exception {
        return apiPersonMgmtService.getStudentInfo(stu);
    }
    
    /**
     * 获取人(学)员基本信息
     * @param stu 学员对象
     * @throws Exception
     */
    @ApiOperation(value = "获取人(学)员基本信息", notes = "获取人(学)员基本信息", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/getPersonInfo", method = RequestMethod.POST)
    public AjaxReturnMsg getPersonInfo(@ModelAttribute Student stu) throws Exception {
        return apiPersonMgmtService.getPersonInfo(stu);
    }
   
    /**
     * 保存学员基本信息
     * @param stu 学员对象
     * @throws Exception
     */
    @ApiOperation(value = "保存学员基本信息", notes = "保存学员基本信息", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/saveStudent", method = RequestMethod.POST)
    public AjaxReturnMsg saveStudent(@ModelAttribute Student stu) throws Exception {
        return apiPersonMgmtService.saveStudent(stu);
    }
    
    /**
     * 学员报名计划结束
     * @param stu 学员对象
     * @throws Exception
     */
    @ApiOperation(value = "学员报名计划结束", notes = "学员报名计划结束", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/apply", method = RequestMethod.POST)
    public AjaxReturnMsg apply(@ModelAttribute Student stu) throws Exception {
        return apiPersonMgmtService.apply(stu);
    }
    
}
