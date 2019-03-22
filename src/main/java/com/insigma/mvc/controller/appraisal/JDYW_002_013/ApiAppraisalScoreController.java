package com.insigma.mvc.controller.appraisal.JDYW_002_013;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageInfo;

import org.springframework.http.MediaType;

import com.insigma.dto.AjaxReturnMsg;
import com.insigma.mvc.MvcHelper;
import com.insigma.mvc.model.Hc63;
import com.insigma.mvc.model.appraisal.Hb74;
import com.insigma.mvc.model.appraisal.Hb74Temp_Short;
import com.insigma.mvc.model.appraisal.Hc64;
import com.insigma.mvc.service.appraisal.JDYW_002_013.ApiAppraisalScoreService;

@RestController
@Api(description = "������վ�����ɼ��걨���������")
@RequestMapping("/api/appraisalscore")
public class ApiAppraisalScoreController extends MvcHelper{
	
	@Resource
	private ApiAppraisalScoreService apiAppraisalScoreService;
	
	/**
	 * ������վ�����б���������
	* @author: liangy  
	* @date 2018��11��16��
	* @param @param hb61
	* @param @param result
	* @param @return
	* @param @throws Exception    
	* @return AjaxReturnMsg   
	* @throws
	 */
	@ApiOperation(value = "�����ɼ��б���Ϣ", notes = "�����ɼ��б���Ϣ")
    @RequestMapping(value = "/getAppraisalScoreList", method = RequestMethod.POST)
	public AjaxReturnMsg getAppraisalScoreList(@ModelAttribute Hb74Temp_Short hb74Temp_Short) throws Exception {
		PageInfo<Hb74Temp_Short> pageinfo = apiAppraisalScoreService.getAppraisalScoreList(hb74Temp_Short);
		return this.success(pageinfo);
	}

	/**
	 * ��ȡ���εĿ������ɼ���Ϣ
	* @author: liangy  
	* @date 2019��3��5��
	* @param @param hb77
	* @param @return
	* @param @throws Exception    
	* @return AjaxReturnMsg   
	* @throws
	 */
	@ApiOperation(value = "��ȡ���εĿ������ɼ���Ϣ", notes = "��ȡ���εĿ������ɼ���Ϣ")
    @RequestMapping(value = "/getExamineeList", method = RequestMethod.POST)
	public AjaxReturnMsg getExamineeList(@ModelAttribute Hc63 hc63) throws Exception {
		PageInfo<Hc63> pageinfo = apiAppraisalScoreService.getExamineeList(hc63);
		return this.success(pageinfo);
	}

	/**
	 * �����޸�ѧԱ��Ϣ(Ϊ�ύǰ)
	* @author: liangy  
	* @date 2019��3��5��
	* @param @param hc64
	* @param @param result
	* @param @return
	* @param @throws Exception    
	* @return AjaxReturnMsg   
	* @throws
	 */
	@ApiOperation(value = "�����޸�ѧԱ��Ϣ(δ�ύǰ)", notes = "�����޸�ѧԱ��Ϣ(δ�ύǰ)")
    @RequestMapping(value = "/saveExamineeInfo", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public AjaxReturnMsg examRoomSave(@ModelAttribute @Valid Hc64 hc64, BindingResult result) throws Exception {
		return apiAppraisalScoreService.saveExamineeInfo(hc64);
	}

	/**
	 * �ɼ��ύ
	* @author: liangy  
	* @date 2019��3��11��
	* @param @param hb74
	* @param @param result
	* @param @return
	* @param @throws Exception    
	* @return AjaxReturnMsg   
	* @throws
	 */
	@ApiOperation(value = "�����޸�ѧԱ��Ϣ(δ�ύǰ)", notes = "�����޸�ѧԱ��Ϣ(δ�ύǰ)")
    @RequestMapping(value = "/saveSubmitScore", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public AjaxReturnMsg saveSubmitScore(@ModelAttribute @Valid Hb74Temp_Short hb74Temp_Short, BindingResult result) throws Exception {
		return apiAppraisalScoreService.saveSubmitScore(hb74Temp_Short);
	}
}
