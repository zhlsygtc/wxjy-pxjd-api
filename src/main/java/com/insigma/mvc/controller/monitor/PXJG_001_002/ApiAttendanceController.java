package com.insigma.mvc.controller.monitor.PXJG_001_002;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import com.insigma.mvc.MvcHelper;
import com.insigma.mvc.model.train.Hb61;
import com.insigma.mvc.model.train.Hb68;
import com.insigma.mvc.model.train.Hc60DTO;
import com.insigma.mvc.model.train.Zc02;
import com.insigma.mvc.service.monitor.PXJG_001_002.AttendanceService;

import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.insigma.dto.AjaxReturnMsg;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
/**
 * 考勤
 * @author jewel
 */
@RestController
@Api(description = "考勤打卡控制器")
@RequestMapping("/api/attendance")
public class ApiAttendanceController extends MvcHelper{
	@Resource
	private AttendanceService attendanceService;
	/**
	 * 初始化班级信息列表 
	 */
	@ApiOperation(value = "获取班级信息", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/getInfoList",method = RequestMethod.POST)
	public AjaxReturnMsg getHb68List(Hb68 hb68) throws Exception {
		PageInfo<Hb68> pageInfo = attendanceService.getInfoList(hb68);
		return this.success(pageInfo);
		
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
    public AjaxReturnMsg saveData(@ModelAttribute @Valid Zc02 zc02, BindingResult result) throws Exception {
    	//检验输入
        if (result.hasErrors()) {
            return validate(result);
        }
        return attendanceService.saveData(zc02);
    }
    
    
}