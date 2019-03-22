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
 * ����
 * @author jewel
 */
@RestController
@Api(description = "���ڴ򿨿�����")
@RequestMapping("/api/attendanceByCourse")
public class ApiAttendanceByCourseController extends MvcHelper{
	@Resource
	private AttendanceByCourseService attendanceByCourseService;
	/**
	 * ��ʼ���༶��Ϣ�б� 
	 */
	@ApiOperation(value = "��ȡ�༶��Ϣ", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/getInfoList",method = RequestMethod.POST)
	public AjaxReturnMsg getHb69List(Hb69DTO param) throws Exception {
		PageInfo<Hb69DTO> pageInfo = attendanceByCourseService.getInfoList(param);
		return this.success(pageInfo);
		
	}
	
	
	 /**
     * ���ݰ༶�����ѯ��ѵ����Ϣ
     */
    @RequestMapping(value = "/getHb69ById")
    public AjaxReturnMsg getHb69ById(Hb69 hb69) throws Exception{
		return this.success(attendanceByCourseService.getHb69ById(hb69));
    }
	
    
    /**
     * ���ݰ༶�����ѯ��ѵ����ѧ���б�
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    @RequestMapping(value = "/getHc60ById")
    public AjaxReturnMsg getHc60ById(Hc60 hc60) throws Exception {
		return this.success(attendanceByCourseService.getHc60ById(hc60));	
	}
    
 
    /**
     * ���ݰ༶�����ѯ��ѵ����ѧ���б�1
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    @RequestMapping(value = "/getHc60ById1")
    public AjaxReturnMsg getHc60ById1(Hc60 hc60) throws Exception {
        AjaxReturnMsg msg = attendanceByCourseService.getHc60ById1(hc60);
        return msg;
          
    }
    
    
	/**
     * ����ɼ�������Ϣ
     *
     * @param Zc02 
     * @param result
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "����ɼ�������Ϣ", notes = "����ɼ�������Ϣ", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/savedata", method = RequestMethod.POST)
    public AjaxReturnMsg saveData(@ModelAttribute @Valid Zc10 zc10, BindingResult result) throws Exception {
    	//��������
        if (result.hasErrors()) {
            return validate(result);
        }
        return attendanceByCourseService.saveData(zc10);
    }
    
    
}