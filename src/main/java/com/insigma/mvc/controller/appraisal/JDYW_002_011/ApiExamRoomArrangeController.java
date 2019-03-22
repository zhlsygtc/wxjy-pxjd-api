package com.insigma.mvc.controller.appraisal.JDYW_002_011;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageInfo;
import com.insigma.dto.AjaxReturnMsg;
import com.insigma.mvc.MvcHelper;
import com.insigma.mvc.model.Hc63;
import com.insigma.mvc.model.appraisal.Hb70;
import com.insigma.mvc.model.appraisal.Hb74;
import com.insigma.mvc.model.appraisal.Hb74Temp_Short;
import com.insigma.mvc.model.appraisal.Hb76;
import com.insigma.mvc.model.appraisal.Hb77;
import com.insigma.mvc.model.appraisal.Hb78;
import com.insigma.mvc.service.appraisal.JDYW_002_011.ApiExamRoomArrangeService;

@RestController
@Api(description = "������վ����걨���������")
@RequestMapping("/api/examRoomArrange")
public class ApiExamRoomArrangeController extends MvcHelper{
	
	@Resource
	private ApiExamRoomArrangeService apiExamRoomArrangeService;
	
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
	@ApiOperation(value = "������վ��������", notes = "������վ�����б���������")
    @RequestMapping(value = "/getAppraisalFreeList", method = RequestMethod.POST)
	public AjaxReturnMsg getAppraisalList(@ModelAttribute Hb74Temp_Short hb74Temp_Short) throws Exception {
		PageInfo<Hb74Temp_Short> pageinfo = apiExamRoomArrangeService.getAppraisalSpeciaList(hb74Temp_Short);
		return this.success(pageinfo);
	}

	/**
	 * ����������Ϣ����
	* @author: liangy  
	* @date 2018��12��19��
	* @param @param hb77
	* @param @return
	* @param @throws Exception    
	* @return AjaxReturnMsg   
	* @throws
	 */
	@ApiOperation(value = "����������Ϣ����", notes = "����������Ϣ����")
    @RequestMapping(value = "/getExamRoomBatchList", method = RequestMethod.POST)
	public AjaxReturnMsg getExamRoomBatchList(@ModelAttribute Hb70 hb70) throws Exception {
		PageInfo<Hb70> pageinfo = apiExamRoomArrangeService.getExamRoomBatchList(hb70);
		return this.success(pageinfo);
	}

	/**
	 * ������Ϣ����
	* @author: liangy  
	* @date 2018��12��19��
	* @param @param hb77
	* @param @return
	* @param @throws Exception    
	* @return AjaxReturnMsg   
	* @throws
	 */
	@ApiOperation(value = "�����б���Ϣ����", notes = "�����б���Ϣ����")
    @RequestMapping(value = "/getExamRoomList", method = RequestMethod.POST)
	public AjaxReturnMsg getExamRoomList(@ModelAttribute Hb77 hb77) throws Exception {
		PageInfo<Hb77> pageinfo = apiExamRoomArrangeService.getExamRoomList(hb77);
		return this.success(pageinfo);
	}

	/**
	 * ��ѯ������վ��������
	* @author: liangy  
	* @date 2018��12��3��
	* @param @param hb74Temp_Short
	* @param @return
	* @param @throws Exception    
	* @return AjaxReturnMsg   
	* @throws
	 */
	@ApiOperation(value = "������վ���������ѯ", notes = "������վ���������ѯ")
    @RequestMapping(value = "/getExamRoomInfo", method = RequestMethod.POST)
	public AjaxReturnMsg getAppraisalInfo(@ModelAttribute Hb74 hb74) throws Exception {
		return apiExamRoomArrangeService.getAppraisalInfo(hb74.getChb140());
	}

	/**
	 * ���Ի�����Ϣ����
	* @author: liangy  
	* @date 2018��12��3��
	* @param @param hb74Temp_Short
	* @param @return
	* @param @throws Exception    
	* @return AjaxReturnMsg   
	* @throws
	 */
	@ApiOperation(value = "���Ի�����Ϣ����", notes = "���Ի�����Ϣ����")
    @RequestMapping(value = "/examRoomBatchSave", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public AjaxReturnMsg examRoomBatchSave(@ModelAttribute @Valid Hb70 hb70, BindingResult result) throws Exception {
		return apiExamRoomArrangeService.examRoomBatchSave(hb70);
	}

	/**
	 * �����Զ�����
	* @author: liangy  
	* @date 2018��12��3��
	* @param @param hb74Temp_Short
	* @param @return
	* @param @throws Exception    
	* @return AjaxReturnMsg   
	* @throws
	 */
	@ApiOperation(value = "�����Զ�����", notes = "�����Զ�����")
    @RequestMapping(value = "/automaticExamArrange", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public AjaxReturnMsg automaticExamArrange(@ModelAttribute @Valid Hb70 hb70, BindingResult result) throws Exception {
		return apiExamRoomArrangeService.automaticExamArrange(hb70);
	}

	/**
	 * ����������Ϣ����
	* @author: liangy  
	* @date 2018��12��3��
	* @param @param hb74Temp_Short
	* @param @return
	* @param @throws Exception    
	* @return AjaxReturnMsg   
	* @throws
	 */
	@ApiOperation(value = "����������Ϣ����", notes = "����������Ϣ����")
    @RequestMapping(value = "/examRoomSave", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public AjaxReturnMsg examRoomSave(@ModelAttribute @Valid Hb77 hb77, BindingResult result) throws Exception {
		return apiExamRoomArrangeService.examRoomSave(hb77);
	}

	/**
	 * ��ǰ������Ա��Ϣ��ѯ
	* @author: liangy  
	* @date 2018��12��19��
	* @param @param hb77
	* @param @return
	* @param @throws Exception    
	* @return AjaxReturnMsg   
	* @throws
	 */
	@ApiOperation(value = "��ǰ������Ա��Ϣ��ѯ", notes = "��ǰ������Ա��Ϣ��ѯ")
    @RequestMapping(value = "/getExamRoomPerponList", method = RequestMethod.POST)
	public AjaxReturnMsg getExamRoomPerponList(@ModelAttribute Hc63 hc63) throws Exception {
		PageInfo<Hc63> pageinfo = apiExamRoomArrangeService.getExamRoomPerponList(hc63);
		return this.success(pageinfo);
	}

	/**
	 * ������Ա��Ϣѡ���ѯ
	* @author: liangy  
	* @date 2018��12��20��
	* @param @param hc63
	* @param @return
	* @param @throws Exception    
	* @return AjaxReturnMsg   
	* @throws
	 */
	@ApiOperation(value = "������Ա��Ϣѡ���ѯ", notes = "������Ա��Ϣѡ���ѯ")
    @RequestMapping(value = "/getExamRoomPerponSelectList", method = RequestMethod.POST)
	public AjaxReturnMsg getExamRoomPerponSelectList(@ModelAttribute Hc63 hc63) throws Exception {
		PageInfo<Hc63> pageinfo = apiExamRoomArrangeService.getExamRoomPerponSelectList(hc63);
		return this.success(pageinfo);
	}

	/**
	 * ������Ա��Ϣ����
	* @author: liangy  
	* @date 2018��12��20��
	* @param @param hc63
	* @param @param result
	* @param @return
	* @param @throws Exception    
	* @return AjaxReturnMsg   
	* @throws
	 */
	@ApiOperation(value = "������Ա��Ϣ����", notes = "������Ա��Ϣ����")
    @RequestMapping(value = "/savePerponExamRoom", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public AjaxReturnMsg savePerponExamRoom(@ModelAttribute @Valid Hc63 hc63, BindingResult result) throws Exception {
		return apiExamRoomArrangeService.savePerponExamRoom(hc63);
	}

	/**
	 * �ӿ��������Ƴ���Ա��Ϣ
	* @author: liangy  
	* @date 2018��12��21��
	* @param @param hc63
	* @param @param result
	* @param @return
	* @param @throws Exception    
	* @return AjaxReturnMsg   
	* @throws
	 */
	@ApiOperation(value = "�ӿ��������Ƴ���Ա��Ϣ", notes = "�ӿ��������Ƴ���Ա��Ϣ")
    @RequestMapping(value = "/deletePerponExamRoom", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public AjaxReturnMsg deletePerponExamRoom(@ModelAttribute @Valid Hc63 hc63, BindingResult result) throws Exception {
		return apiExamRoomArrangeService.deletePerponExamRoom(hc63);
	}

	/**
	 * ������Ϣ����
	* @author: liangy  
	* @date 2018��12��21��
	* @param @param hb74
	* @param @return
	* @param @throws Exception    
	* @return AjaxReturnMsg   
	* @throws
	 */
	@ApiOperation(value = "������Ϣ����", notes = "������Ϣ����")
    @RequestMapping(value = "/getExamRoomBaseBatchInfo", method = RequestMethod.POST)
	public AjaxReturnMsg getExamRoomBaseBatchInfo(@ModelAttribute Hb70 hb70) throws Exception {
		return apiExamRoomArrangeService.getExamRoomBaseBatchInfo(hb70.getChb070());
	}

	/**
	 * ������Ϣ����
	* @author: liangy  
	* @date 2018��12��21��
	* @param @param hb74
	* @param @return
	* @param @throws Exception    
	* @return AjaxReturnMsg   
	* @throws
	 */
	@ApiOperation(value = "������Ϣ����", notes = "������Ϣ����")
    @RequestMapping(value = "/getExamRoomBaseInfo", method = RequestMethod.POST)
	public AjaxReturnMsg getExamRoomBaseInfo(@ModelAttribute Hb77 hb77) throws Exception {
		return apiExamRoomArrangeService.getExamRoomBaseInfo(hb77.getChb077());
	}

	/**
	 * ����ɾ��������Ϣ
	* @author: liangy  
	* @date 2018��12��21��
	* @param @param hc63
	* @param @param result
	* @param @return
	* @param @throws Exception    
	* @return AjaxReturnMsg   
	* @throws
	 */
	@ApiOperation(value = "����ɾ��������Ϣ", notes = "����ɾ��������Ϣ")
    @RequestMapping(value = "/deletePerponExamRoomBatch", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public AjaxReturnMsg deletePerponExamRoomBatch(@ModelAttribute @Valid Hb77 hb77, BindingResult result) throws Exception {
		return apiExamRoomArrangeService.deletePerponExamRoomBatch(hb77);
	}

	/**
	 * ����������Ϣ�ύ
	* @author: liangy  
	* @date 2018��12��24��
	* @param @param hb74
	* @param @param result
	* @param @return
	* @param @throws Exception    
	* @return AjaxReturnMsg   
	* @throws
	 */
	@ApiOperation(value = "����������Ϣ�ύ", notes = "����������Ϣ�ύ")
    @RequestMapping(value = "/saveExamRoomSubmit", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public AjaxReturnMsg saveExamRoomSubmit(@ModelAttribute @Valid Hb74 hb74, BindingResult result) throws Exception {
		return apiExamRoomArrangeService.saveExamRoomSubmit(hb74);
	}

	/**
	 * ��ѯ������ǰ�ѱ�������
	* @author: liangy  
	* @date 2018��12��29��
	* @param @param hb77
	* @param @param result
	* @param @return
	* @param @throws Exception    
	* @return AjaxReturnMsg   
	* @throws
	 */
	@ApiOperation(value = "��ѯ������ǰ�ѱ�������", notes = "��ѯ������ǰ�ѱ�������")
    @RequestMapping(value = "/getselectExamRoomPerponNum", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public AjaxReturnMsg getselectExamRoomPerponNum(@ModelAttribute @Valid Hb77 hb77, BindingResult result) throws Exception {
		return apiExamRoomArrangeService.getselectExamRoomPerponNum(hb77.getChb070(), hb77.getChb140());
	} 

	/**
	 * ��ѯ����list
	* @author: liangy  
	* @date 2019��1��4��
	* @param @param hb77
	* @param @param result
	* @param @return
	* @param @throws Exception    
	* @return AjaxReturnMsg   
	* @throws
	 */
	@ApiOperation(value = "��ѯ����list", notes = "��ѯ����list")
    @RequestMapping(value = "/getTestCenterList", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public AjaxReturnMsg getTestCenterList(@ModelAttribute @Valid Hb76 hb76) throws Exception {
		List<Hb76> hb79list = apiExamRoomArrangeService.getTestCenterList(hb76.getAab001());
		Map<String, Object> result = new HashMap<>();
	    result.put("list", hb79list);
	    return this.success(result);
	}

	/**
	 * ��ȡ����list
	* @author: liangy  
	* @date 2019��1��4��
	* @param @param hb78
	* @param @return
	* @param @throws Exception    
	* @return AjaxReturnMsg   
	* @throws
	 */
	@ApiOperation(value = "��ѯ����list", notes = "��ѯ����list")
    @RequestMapping(value = "/getExamRoomSelectList", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public AjaxReturnMsg getExamRoomSelectList(@ModelAttribute @Valid Hb78 hb78) throws Exception {
		List<Hb78> hb79list = apiExamRoomArrangeService.getExamRoomSelectList(hb78.getChb076());
		Map<String, Object> result = new HashMap<>();
	    result.put("list", hb79list);
	    return this.success(result);
	}

	/**
	 * ����ɾ������������Ϣ
	* @author: liangy  
	* @date 2019��1��28��
	* @param @param hb70
	* @param @return
	* @param @throws Exception    
	* @return AjaxReturnMsg   
	* @throws
	 */
	@ApiOperation(value = "����ɾ������������Ϣ", notes = "����ɾ������������Ϣ")
    @RequestMapping(value = "/deleteExaminationBatch", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public AjaxReturnMsg deleteExaminationBatch(@ModelAttribute @Valid Hb70 hb70) throws Exception {
		return apiExamRoomArrangeService.deleteExaminationBatch(hb70);
	}
}
