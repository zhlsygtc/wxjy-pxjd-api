package com.insigma.mvc.controller.app.APP_001_007;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageInfo;
import com.insigma.dto.AjaxReturnMsg;
import com.insigma.mvc.MvcHelper;
import com.insigma.mvc.model.train.Hc61;
import com.insigma.mvc.model.train.Hc61Dto;
import com.insigma.mvc.service.app.APP_001_001.ApiCommonService;
import com.insigma.mvc.service.app.APP_001_007.ApiAttendancePerService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/AttendancePer")
public class ApiAttendancePerController extends MvcHelper{
	
	@Resource
	ApiAttendancePerService apiAttendancePerService;
	@Resource
	ApiCommonService apiCommonService;
	
	/**
	 * 获取学员培训考勤记录信息列表
	 * @param dto
	 * @return
	 */
	@ApiOperation(value = "获取学员培训考勤记录信息列表", notes = "获取学员培训考勤记录信息列表")
    @RequestMapping(value = "/getAttendanceList", method = RequestMethod.POST)
	public AjaxReturnMsg getAttendanceList(@ModelAttribute Hc61Dto dto) throws Exception {
		
		Hc61 hc61 = apiCommonService.getStudentInfo(dto);
		dto.setChc111(hc61.getChc111());
		PageInfo<Hc61Dto> pageinfo = apiAttendancePerService.getAttendanceList(dto);
		return this.success(pageinfo);
	}

	/**
	 * 获取学员课程考勤记录信息列表
	 * @param dto
	 * @return
	 */
	@ApiOperation(value = "获取学员课程考勤记录信息列表", notes = "获取学员课程考勤记录信息列表")
    @RequestMapping(value = "/getClassAttendanceList", method = RequestMethod.POST)
	public AjaxReturnMsg getCollectionList(@ModelAttribute Hc61Dto dto) throws Exception {
		PageInfo<Hc61Dto> pageinfo = apiAttendancePerService.getClassAttendanceList(dto);
		return this.success(pageinfo);
	}
	
	
}
