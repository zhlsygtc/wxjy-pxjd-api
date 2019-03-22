package com.insigma.mvc.controller.app.APP_001_001;

import javax.annotation.Resource;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import com.insigma.dto.AjaxReturnMsg;
import com.insigma.mvc.MvcHelper;
import com.insigma.mvc.model.SUser;
import com.insigma.mvc.service.app.APP_001_001.ApiCommonService;

import io.swagger.annotations.ApiOperation;


/**
 * 培训APP共同转化接口
 * @author Ace
 *
 */
@RestController
@RequestMapping("/api/common")
public class ApiCommonController extends MvcHelper{
	
	@Resource
	ApiCommonService apiCommonService;
	
	
	/**
     * 获取个人基本信息
     * @param suser
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "获取个人基本信息)", notes = "获取个人基本信息", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/baseinfo", method = RequestMethod.POST)
    public AjaxReturnMsg getPersonBaseInfo(@RequestBody SUser suser) throws Exception {
        return apiCommonService.getPersonBaseInfo(suser);
    }


}
