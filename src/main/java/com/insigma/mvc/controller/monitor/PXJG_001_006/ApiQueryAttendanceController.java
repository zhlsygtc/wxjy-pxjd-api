package com.insigma.mvc.controller.monitor.PXJG_001_006;

import java.util.List;

import javax.annotation.Resource;

import com.insigma.mvc.MvcHelper;
import com.insigma.mvc.model.train.*;
import com.insigma.mvc.service.monitor.PXJG_001_006.QueryAttendanceService;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageInfo;
import com.insigma.dto.AjaxReturnMsg;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
/**
 * 考勤查询
 * @author jewel
 */
@RestController
@Api(description = "考勤查询控制器")
@RequestMapping("/api/queryAttendance")
public class ApiQueryAttendanceController extends MvcHelper{
	@Resource
	private QueryAttendanceService queryAttendanceService;
	/**
	 * 初始化班级信息列表 
	 */
	@ApiOperation(value = "获取班级信息", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/getInfoList",method = RequestMethod.POST)
	public AjaxReturnMsg getInfoList(Hb69DTO param) throws Exception {
		PageInfo<Hb69DTO> pageInfo = queryAttendanceService.getInfoList(param);
		return this.success(pageInfo);
		
	}
    
	
	/**
     * 获取课程考勤汇总
     */
    @RequestMapping(value = "/getHb69List")
    public AjaxReturnMsg getHb69List(Hb69DTO hb69DTO) throws Exception{
		return this.success(queryAttendanceService.getHb69List(hb69DTO));	
    }
	
	 /**
     * 获取学员考勤汇总
     */
    @RequestMapping(value = "/getHc60List")
    public AjaxReturnMsg getHc60List(Hc60DTO hc60DTO) throws Exception{
		return this.success(queryAttendanceService.getHc60List(hc60DTO));	
    }
    
    
    /**
     * 获取学员课程考勤明细
     */
    @RequestMapping(value = "/attendanceQueryList")
    public AjaxReturnMsg attendanceQueryList(Hb69DTO hb69DTO) throws Exception{
		return this.success(queryAttendanceService.attendanceQueryList(hb69DTO));	
    }
    /**
	 * 获取导出现场确认表所需学员信息
	 * @param chb068
	 * @return
	 * @throws Exception
	 */
	@ApiOperation(value = "获取导出课程表所需信息", produces = MediaType.APPLICATION_JSON_VALUE)
	@RequestMapping(value="/getStuForSceneSure",method = RequestMethod.POST)
	public AjaxReturnMsg getStuForSceneSure(Hc60 hc60)throws Exception {
		List<Hc60> list = queryAttendanceService.getStuForSceneSure(hc60);
        return  this.success(list);
	}
}