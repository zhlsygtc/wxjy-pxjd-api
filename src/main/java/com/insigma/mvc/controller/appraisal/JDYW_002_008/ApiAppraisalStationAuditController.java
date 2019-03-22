package com.insigma.mvc.controller.appraisal.JDYW_002_008;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import java.util.List;

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
import com.insigma.mvc.model.CodeValue;
import com.insigma.mvc.model.appraisal.Hb75;
import com.insigma.mvc.model.appraisal.Hb75Temp;
import com.insigma.mvc.model.appraisal.Hc60Temp_Short;
import com.insigma.mvc.model.appraisal.Hc63Temp_Short;
import com.insigma.mvc.service.appraisal.JDYW_002_008.ApiAppraisalStationAuditService;

@RestController
@Api(description = "������վ����걨���������")
@RequestMapping("/api/appraisalStationAudit")
public class ApiAppraisalStationAuditController extends MvcHelper{
	
	@Resource
	private ApiAppraisalStationAuditService appraisalStationAuditService;
	
	/**
	 * ������վ����б����ݲ�ѯ
	* @author: liangy  
	* @date 2018��11��16��
	* @param @param hb61
	* @param @param result
	* @param @return
	* @param @throws Exception    
	* @return AjaxReturnMsg   
	* @throws
	 */
	@ApiOperation(value = "������վ�������", notes = "������վ����б���������")
    @RequestMapping(value = "/getAppraisalList", method = RequestMethod.POST)
	public AjaxReturnMsg getAppraisalList(@ModelAttribute Hb75Temp hb75Temp, BindingResult result ) throws Exception {
		PageInfo<Hb75Temp> pageinfo = appraisalStationAuditService.getAppraisalSpeciaList(hb75Temp);
		return this.success(pageinfo);
	}

	/**
     * ��ȡ�걨����ѵ����
     *
     * @param codevalue �������
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "��ȡ�걨����ѵ����", notes = "��ȡ�걨����ѵ����", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/getTrainComName", method = RequestMethod.POST)
    public AjaxReturnMsg<List<CodeValue>> getTrainComName(@ModelAttribute CodeValue codevalue) throws Exception {
        return appraisalStationAuditService.getTrainComName(codevalue);
    }
	
	/**
	 * ������վ��������ѯ
	* @author: liangy  
	* @date 2018��11��23��
	* @param @param hb75Temp
	* @param @return
	* @param @throws Exception    
	* @return AjaxReturnMsg   
	* @throws
	 */
	@ApiOperation(value = "������վ��������ѯ", notes = "������վ��������ѯ")
    @RequestMapping(value = "/getAppraisalInfo", method = RequestMethod.POST)
	public AjaxReturnMsg getAppraisalInfo(@ModelAttribute Hb75 hb75) throws Exception {
		return appraisalStationAuditService.getAppraisalInfo(hb75.getChb120());
	}

	/**
	 * δ�걨ѧԱ�б����ݲ�ѯ
	* @author: liangy  
	* @date 2018��11��16��
	* @param @param hb61
	* @param @param result
	* @param @return
	* @param @throws Exception    
	* @return AjaxReturnMsg   
	* @throws
	 */
	@ApiOperation(value = "δ�걨ѧԱ�б��ѯ", notes = "δ�걨ѧԱ�б��ѯ")
    @RequestMapping(value = "/getAppraisStudentNotSubmitList", method = RequestMethod.POST)
	public AjaxReturnMsg getAppraisStudentNotSubmitList(@ModelAttribute Hc60Temp_Short hc60Temp_Short, BindingResult result ) throws Exception {
		if (result.hasErrors()) {
            return validate(result);
        }
		PageInfo<Hc60Temp_Short> pageinfo = appraisalStationAuditService.getAppraisStudentNotSubmitList(hc60Temp_Short);
		return this.success(pageinfo);
	}

	/**
	 * ��ѯ������ѧԱ��Ϣ
	* @author: liangy  
	* @date 2018��11��16��
	* @param @param hb61
	* @param @param result
	* @param @return
	* @param @throws Exception    
	* @return AjaxReturnMsg   
	* @throws
	 */
	@ApiOperation(value = "δ�걨ѧԱ�б��ѯ", notes = "δ�걨ѧԱ�б��ѯ")
    @RequestMapping(value = "/getAppraisThisStudentList", method = RequestMethod.POST)
	public AjaxReturnMsg getAppraisThisStudentList(@ModelAttribute Hc60Temp_Short hc60Temp_Short) throws Exception {
		PageInfo<Hc60Temp_Short> pageinfo = appraisalStationAuditService.getAppraisThisStudentList(hc60Temp_Short);
		return this.success(pageinfo);
	}

    /**
     * ѧԱ���������Ϣ��ѯ
    * @author: liangy  
    * @date 2018��11��24��
    * @param @param hb75
    * @param @param result
    * @param @return
    * @param @throws Exception    
    * @return AjaxReturnMsg   
    * @throws
     */
    @ApiOperation(value = "ѧԱ���������Ϣ��ѯ", notes = "ѧԱ���������Ϣ��ѯ", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/getPerponAuditInfo", method = RequestMethod.POST)
    public AjaxReturnMsg getPerponAuditInfo(@ModelAttribute @Valid Hc63Temp_Short hc63Temp_Short, BindingResult result) throws Exception {
        return appraisalStationAuditService.getPerponAuditInfo(hc63Temp_Short.getChc063());
    }

    /**
     * ��˸��˼�������
     *
     * @param Hb75 �걨���ζ���
     * @param result
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "��˸��˼�������", notes = "��˸��˼�������", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/savePerponAudit", method = RequestMethod.POST)
    public AjaxReturnMsg savePerponAudit(@ModelAttribute Hc63Temp_Short hc63Temp_Short) throws Exception {
        return appraisalStationAuditService.savePerponAudit(hc63Temp_Short);
    }

    /**
     * ������ѧԱ������ͳ��
    * @author: liangy  
    * @date 2018��11��24��
    * @param @param hb75
    * @param @param result
    * @param @return
    * @param @throws Exception    
    * @return AjaxReturnMsg   
    * @throws
     */
    @ApiOperation(value = "ѧԱ���������Ϣ��ѯ", notes = "ѧԱ���������Ϣ��ѯ", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/getPerponAuditIsPass", method = RequestMethod.POST)
    public AjaxReturnMsg getPerponAuditIsPass(@ModelAttribute @Valid Hc63Temp_Short hc63Temp_Short, BindingResult result) throws Exception {
        return appraisalStationAuditService.getPerponAuditIsPass(hc63Temp_Short.getChb120());
    }

    /**
     * ��˼�������
     *
     * @param Hb75 �걨���ζ���
     * @param result
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "��˼�������", notes = "��˼�������", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/savePerponAuditAll", method = RequestMethod.POST)
    public AjaxReturnMsg savePerponAuditAll(@ModelAttribute Hb75 hb75) throws Exception {
        return appraisalStationAuditService.savePerponAuditAll(hb75);
    }
}
