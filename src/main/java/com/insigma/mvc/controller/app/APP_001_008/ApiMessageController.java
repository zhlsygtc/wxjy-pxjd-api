package com.insigma.mvc.controller.app.APP_001_008;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.insigma.dto.AjaxReturnMsg;
import com.insigma.mvc.MvcHelper;
import com.insigma.mvc.model.train.Hb65;
import com.insigma.mvc.model.train.S_info;
import com.insigma.mvc.service.app.APP_001_008.ApiMessageService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/Message")
public class ApiMessageController extends MvcHelper{
	
	@Resource
	ApiMessageService apiMessageService;
	
	
	/**
     * 获取未读消息数量
     * @param info 消息对象
     * @throws Exception
     */
    @ApiOperation(value = "获取未读消息数量", notes = "获取未读消息数量", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/getInfoNum", method = RequestMethod.POST)
    public AjaxReturnMsg getInfoNum(@ModelAttribute S_info info) throws Exception {
    	String num = apiMessageService.getInfoNum(info);
    	Map map = new HashMap();
    	map.put("num", num);
        return this.success(map);
    }
	
    /**
     * 获取消息
     * @param info 消息对象
     * @throws Exception
     */
    @ApiOperation(value = "获取消息", notes = "获取消息", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/getInfoS", method = RequestMethod.POST)
    public AjaxReturnMsg getInfoS(@ModelAttribute S_info info) throws Exception {
        return apiMessageService.getInfoS(info);
    }
    
    /**
     * 阅读消息
     * @param info 消息对象
     * @throws Exception
     */
    @ApiOperation(value = "阅读消息", notes = "阅读消息", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/readInfo", method = RequestMethod.POST)
    public AjaxReturnMsg readInfo(@ModelAttribute S_info info) throws Exception {
        return apiMessageService.readInfo(info);
    }
    
}
