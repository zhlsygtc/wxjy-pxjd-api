package com.insigma.mvc.controller.monitor.PXJG_001_005;


import javax.annotation.Resource;
import javax.validation.Valid;

import com.insigma.mvc.MvcHelper;
import com.insigma.mvc.model.train.*;
import com.insigma.mvc.service.monitor.PXJG_001_005.AttendanceByCourseService;

import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageInfo;
import com.insigma.dto.AjaxReturnMsg;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
/**
 * 考勤
 * @author jewel
 */
@RestController
@Api(description = "考勤打卡控制器")
@RequestMapping("/api/attendanceByCourse")
public class ApiAttendanceByCourseController extends MvcHelper{
	@Resource
	private AttendanceByCourseService attendanceByCourseService;
	/**
	 * 初始化班级信息列表 
	 */
	@ApiOperation(value = "获取班级信息", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/getInfoList",method = RequestMethod.POST)
	public AjaxReturnMsg getHb69List(Hb69DTO param) throws Exception {
		PageInfo<Hb69DTO> pageInfo = attendanceByCourseService.getInfoList(param);
		return this.success(pageInfo);
		
	}
	
	
	 /**
     * 根据班级内码查询培训班信息
     */
    @RequestMapping(value = "/getHb69ById")
    public AjaxReturnMsg getHb69ById(Hb69 hb69) throws Exception{
		return this.success(attendanceByCourseService.getHb69ById(hb69));
    }
	
    
    /**
     * 根据班级内码查询培训班中学生列表
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    @RequestMapping(value = "/getHc60ById")
    public AjaxReturnMsg getHc60ById(Hc60 hc60) throws Exception {
		return this.success(attendanceByCourseService.getHc60ById(hc60));	
	}
    
 
    /**
     * 根据班级内码查询培训班中学生列表1
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    @RequestMapping(value = "/getHc60ById1")
    public AjaxReturnMsg getHc60ById1(Hc60 hc60) throws Exception {
        AjaxReturnMsg msg = attendanceByCourseService.getHc60ById1(hc60);
        return msg;
          
    }
    
    
	/**
     * 保存采集基本信息
     *
     * @param Zc02 
     * @param result
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "保存采集基本信息", notes = "保存采集基本信息", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/savedata", method = RequestMethod.POST)
    public AjaxReturnMsg saveData(@ModelAttribute @Valid Zc10 zc10, BindingResult result) throws Exception {
    	//检验输入
        if (result.hasErrors()) {
            return validate(result);
        }
        return attendanceByCourseService.saveData(zc10);
    }
    
    
}