package com.insigma.mvc.controller.appraisal.JDYW_002_012;

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
import com.insigma.enums.HB74CHB17CEnum;
import com.insigma.mvc.MvcHelper;
import com.insigma.mvc.model.Hc63;
import com.insigma.mvc.model.appraisal.Hb74;
import com.insigma.mvc.model.appraisal.Hb74Temp_Short;
import com.insigma.mvc.model.appraisal.Hb77;
import com.insigma.mvc.model.appraisal.Hb79;
import com.insigma.mvc.model.appraisal.Hc73;
import com.insigma.mvc.model.appraisal.Hc73Temp_Short;
import com.insigma.mvc.service.appraisal.JDYW_002_012.ApiPersondispatchService;

@RestController
@Api(description = "������վ����걨���������")
@RequestMapping("/api/persondispatch")
public class ApiPersondispatchController extends MvcHelper{
	
	@Resource
	private ApiPersondispatchService apiPersondispatchService;
	
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
	@ApiOperation(value = "��Աָ�ɽ׶���������", notes = "��Աָ�ɽ׶���������")
    @RequestMapping(value = "/getPersondispatchList", method = RequestMethod.POST)
	public AjaxReturnMsg getAppraisalList(@ModelAttribute Hb74Temp_Short hb74Temp_Short) throws Exception {
		PageInfo<Hb74Temp_Short> pageinfo = apiPersondispatchService.getPersondispatchList(hb74Temp_Short);
		return this.success(pageinfo);
	}

	/**
	 * ���ο�����Ϣ����
	* @author: liangy  
	* @date 2018��12��19��
	* @param @param hb77
	* @param @return
	* @param @throws Exception    
	* @return AjaxReturnMsg   
	* @throws
	 */
	@ApiOperation(value = "���ο�����Ϣ����", notes = "���ο�����Ϣ����")
    @RequestMapping(value = "/getExamRoomList", method = RequestMethod.POST)
	public AjaxReturnMsg getExamRoomList(@ModelAttribute Hb77 hb77) throws Exception {
		PageInfo<Hb77> pageinfo = apiPersondispatchService.getExamRoomList(hb77);
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
		return apiPersondispatchService.getAppraisalInfo(hb74.getChb140());
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
		return apiPersondispatchService.examRoomSave(hb77);
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
		PageInfo<Hc63> pageinfo = apiPersondispatchService.getExamRoomPerponList(hc63);
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
		PageInfo<Hc63> pageinfo = apiPersondispatchService.getExamRoomPerponSelectList(hc63);
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
		return apiPersondispatchService.savePerponExamRoom(hc63);
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
		return apiPersondispatchService.deletePerponExamRoom(hc63);
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
		return apiPersondispatchService.getExamRoomBaseInfo(hb77.getChb077());
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
		return apiPersondispatchService.deletePerponExamRoomBatch(hb77);
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
		return apiPersondispatchService.saveExamRoomSubmit(hb74);
	}

	/**
	 * ��ȡ������վ��Ա��Ϣ
	* @author: liangy  
	* @date 2018��12��21��
	* @param @param hb74
	* @param @return
	* @param @throws Exception    
	* @return AjaxReturnMsg   
	* @throws
	 */
	@ApiOperation(value = "��ȡ������վ��Ա��Ϣ", notes = "��ȡ������վ��Ա��Ϣ")
    @RequestMapping(value = "/getAppraiserList", method = RequestMethod.POST)
	public AjaxReturnMsg getAppraiserList(@ModelAttribute Hc73Temp_Short hc73Temp_Short) throws Exception {
		List<Hc73Temp_Short> hc73list = apiPersondispatchService.getAppraiserList(hc73Temp_Short);
		Map<String, Object> result = new HashMap<>();
        result.put("list", hc73list);
        return this.success(result);
	}

	 /**
     * ���������ռ��Ա��ǲ��Ϣ
    * @author: liangy  
    * @date 2018��12��26��
    * @param @param hc63
    * @param @param result
    * @param @return
    * @param @throws Exception    
    * @return AjaxReturnMsg   
    * @throws
     */
    @ApiOperation(value = "���������ռ��Ա��ǲ��Ϣ", notes = "���������ռ��Ա��ǲ��Ϣ", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/saveAppraisCorePerson", method = RequestMethod.POST)
    public AjaxReturnMsg saveAppraisCorePerson(@ModelAttribute @Valid Hb79 hb79, BindingResult result) throws Exception {
    	//��������
//        if (result.hasErrors()) {
//            return validate(result);
//        }
        return apiPersondispatchService.saveAppraisCorePerson(hb79);
    }

    /**
     * ��Աָ�������ύ
    * @author: liangy  
    * @date 2018��12��27��
    * @param @param hb79
    * @param @param result
    * @param @return
    * @param @throws Exception    
    * @return AjaxReturnMsg   
    * @throws
     */
    @ApiOperation(value = "��Աָ�������ύ", notes = "��Աָ�������ύ", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/saveAppraisPersonStatus", method = RequestMethod.POST)
    public AjaxReturnMsg saveAppraisPersonStatus(@ModelAttribute @Valid Hb79 hb79, BindingResult result) throws Exception {
        return apiPersondispatchService.saveAppraisPersonStatus(hb79.getChb140(), HB74CHB17CEnum.WAIT_AUDIT.getCode());
    }

    /**
     * ��Աָ����Ϣ��ȡ
    * @author: liangy  
    * @date 2018��12��27��
    * @param @param hb79
    * @param @param result
    * @param @return
    * @param @throws Exception    
    * @return AjaxReturnMsg   
    * @throws
     */
    @ApiOperation(value = "��Աָ����Ϣ��ȡ", notes = "��Աָ����Ϣ��ȡ", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/getAppraisCorePersonList", method = RequestMethod.POST)
    public AjaxReturnMsg getAppraisCorePersonList(@ModelAttribute @Valid Hb79 hb79) throws Exception {
    	List<Hc73> hb79list = apiPersondispatchService.getAppraisCorePersonList(hb79.getChb140(), hb79.getChb077(), hb79.getAab001());
		Map<String, Object> result = new HashMap<>();
        result.put("list", hb79list);
        return this.success(result);
    }
}
