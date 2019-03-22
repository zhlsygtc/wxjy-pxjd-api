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
     * ��ȡδ����Ϣ����
     * @param info ��Ϣ����
     * @throws Exception
     */
    @ApiOperation(value = "��ȡδ����Ϣ����", notes = "��ȡδ����Ϣ����", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/getInfoNum", method = RequestMethod.POST)
    public AjaxReturnMsg getInfoNum(@ModelAttribute S_info info) throws Exception {
    	String num = apiMessageService.getInfoNum(info);
    	Map map = new HashMap();
    	map.put("num", num);
        return this.success(map);
    }
	
    /**
     * ��ȡ��Ϣ
     * @param info ��Ϣ����
     * @throws Exception
     */
    @ApiOperation(value = "��ȡ��Ϣ", notes = "��ȡ��Ϣ", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/getInfoS", method = RequestMethod.POST)
    public AjaxReturnMsg getInfoS(@ModelAttribute S_info info) throws Exception {
        return apiMessageService.getInfoS(info);
    }
    
    /**
     * �Ķ���Ϣ
     * @param info ��Ϣ����
     * @throws Exception
     */
    @ApiOperation(value = "�Ķ���Ϣ", notes = "�Ķ���Ϣ", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/readInfo", method = RequestMethod.POST)
    public AjaxReturnMsg readInfo(@ModelAttribute S_info info) throws Exception {
        return apiMessageService.readInfo(info);
    }
    
}
