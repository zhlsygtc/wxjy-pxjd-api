package com.insigma.mvc.controller.appraisal.JDYW_002_009;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

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
import com.insigma.enums.HB74CHB326Enum;
import com.insigma.enums.HB75CHB146Enum;
import com.insigma.enums.HB75CHB312Enum;
import com.insigma.mvc.MvcHelper;
import com.insigma.mvc.model.appraisal.Hb74;
import com.insigma.mvc.model.appraisal.Hb74Temp_Short;
import com.insigma.mvc.model.appraisal.Hb75Temp;
import com.insigma.mvc.model.appraisal.Hc60Temp_Short;
import com.insigma.mvc.service.appraisal.JDYW_002_009.ApiAppraisalStationDeclareService;

@RestController
@Api(description = "������վ����걨���������")
@RequestMapping("/api/appraisalStationDeclare")
public class ApiAppraisalStationDeclareController extends MvcHelper{
	
	@Resource
	private ApiAppraisalStationDeclareService appraisalStationDeclareService;
	
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
    @RequestMapping(value = "/getAppraisalList", method = RequestMethod.POST)
	public AjaxReturnMsg getAppraisalList(@ModelAttribute Hb74Temp_Short hb74Temp_Short) throws Exception {
		PageInfo<Hb74Temp_Short> pageinfo = appraisalStationDeclareService.getAppraisalSpeciaList(hb74Temp_Short);
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
    @RequestMapping(value = "/getAppraisalInfo", method = RequestMethod.POST)
	public AjaxReturnMsg getAppraisalInfo(@ModelAttribute Hb74 hb74) throws Exception {
		return appraisalStationDeclareService.getAppraisalInfo(hb74.getChb140());
	}

	/**
     * ������վ�����ƻ��ƶ�(����ƻ�)
     * @param Hb75 �걨���ζ���
     * @param result
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "������վ�����ƻ��ƶ�(����ƻ�)", notes = "������վ�����ƻ��ƶ�(����ƻ�)", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/savePerponAuditIn", method = RequestMethod.POST)
    public AjaxReturnMsg savePerponAuditIn(@ModelAttribute @Valid Hb75Temp hb75Temp, BindingResult result) throws Exception {
    	//��������
//        if (result.hasErrors()) {
//            return validate(result);
//        }
        return appraisalStationDeclareService.savePerponAudit(hb75Temp, "in");
    }

    /**
     * ������վ�����ƻ��ƶ�(�Ƴ��ƻ�)
     * @param Hb75 �걨���ζ���
     * @param result
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "������վ�����ƻ��ƶ�(�Ƴ��ƻ�)", notes = "������վ�����ƻ��ƶ�(�Ƴ��ƻ�)", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/savePerponAuditOut", method = RequestMethod.POST)
    public AjaxReturnMsg savePerponAuditOut(@ModelAttribute @Valid Hb75Temp hb75Temp, BindingResult result) throws Exception {
    	//��������
//        if (result.hasErrors()) {
//            return validate(result);
//        }
        return appraisalStationDeclareService.savePerponAudit(hb75Temp, "out");
    }

    /**
     * ��ѯ��������
    * @author: liangy  
    * @date 2018��11��29��
    * @param @param hb74Temp_Short
    * @param @return
    * @param @throws Exception    
    * @return AjaxReturnMsg   
    * @throws
     */
    @ApiOperation(value = "��ѯ��������", notes = "��ѯ��������")
    @RequestMapping(value = "/getAppraisalFreeList", method = RequestMethod.POST)
	public AjaxReturnMsg getAppraisalFreeList(@ModelAttribute Hb75Temp hb75Temp) throws Exception {
		// ��������0  Ĭ�ϲ�ѯδ������ƻ���������Ϣ
    	hb75Temp.setChb140("0");
    	// ��ѯ���ͨ��������
    	hb75Temp.setChb312(HB75CHB312Enum.AUDIT_PASS.getCode());
    	hb75Temp.setChb146(HB75CHB146Enum.APPRAISAL_REVIEW_PASS.getCode());
		PageInfo<Hb75Temp> pageinfo = appraisalStationDeclareService.getAppraisalDeclareist(hb75Temp);
		return this.success(pageinfo);
	}

    /**
     * ��ѯ��������Ϣ�б�
    * @author: liangy  
    * @date 2018��11��29��
    * @param @param hb74Temp_Short
    * @param @param result
    * @param @return
    * @param @throws Exception    
    * @return AjaxReturnMsg   
    * @throws
     */
    @ApiOperation(value = "��ѯ��������Ϣ�б�", notes = "��ѯ��������Ϣ�б�")
    @RequestMapping(value = "/getAppraisalNotFreeList", method = RequestMethod.POST)
	public AjaxReturnMsg getAppraisalNotFreeList(@ModelAttribute Hb75Temp hb75Temp) throws Exception {
		PageInfo<Hb75Temp> pageinfo = appraisalStationDeclareService.getAppraisalDeclareist(hb75Temp);
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
		PageInfo<Hc60Temp_Short> pageinfo = appraisalStationDeclareService.getAppraisThisStudentList(hc60Temp_Short);
		return this.success(pageinfo);
	}

	/**
	 * ������վ���������걨��Ϣ����
	* @author: liangy  
	* @date 2018��12��3��
	* @param @param hb74
	* @param @param result
	* @param @return
	* @param @throws Exception    
	* @return AjaxReturnMsg   
	* @throws
	 */
	@ApiOperation(value = "������վ���������걨��Ϣ����", notes = "������վ���������걨��Ϣ����", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/savePerponDelare", method = RequestMethod.POST)
    public AjaxReturnMsg savePerponDelare(@ModelAttribute @Valid Hb74 hb74, BindingResult result) throws Exception {
    	//��������
//        if (result.hasErrors()) {
//            return validate(result);
//        }
		hb74.setChb326(HB74CHB326Enum.UNSUBMITTED.getCode());
        return appraisalStationDeclareService.savePerponSubmit(hb74);
    }

	/**
	 * ������վ���������걨��Ϣ�ύ
	* @author: liangy  
	* @date 2018��12��3��
	* @param @param hb74
	* @param @param result
	* @param @return
	* @param @throws Exception    
	* @return AjaxReturnMsg   
	* @throws
	 */
	@ApiOperation(value = "������վ���������걨��Ϣ�ύ", notes = "������վ���������걨��Ϣ�ύ", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/savePerponSubmit", method = RequestMethod.POST)
    public AjaxReturnMsg savePerponSubmit(@ModelAttribute @Valid Hb74 hb74, BindingResult result) throws Exception {
    	//��������
//        if (result.hasErrors()) {
//            return validate(result);
//        }
		hb74.setChb326(HB74CHB326Enum.SUBMITTED.getCode());
        return appraisalStationDeclareService.savePerponSubmit(hb74);
    }

	/**
	 * ������վ�������뵥��ɾ��
	* @author: liangy  
	* @date 2018��12��4��
	* @param @param hb74
	* @param @param result
	* @param @return
	* @param @throws Exception    
	* @return AjaxReturnMsg   
	* @throws
	 */
	@ApiOperation(value = "������վ�������뵥��ɾ��", notes = "������վ�������뵥��ɾ��", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/delAppraisalDeclare", method = RequestMethod.POST)
    public AjaxReturnMsg delAppraisalDeclare(@ModelAttribute @Valid Hb74 hb74, BindingResult result) throws Exception {
    	//��������
//        if (result.hasErrors()) {
//            return validate(result);
//        }
        return appraisalStationDeclareService.delAppraisalDeclare(hb74);
    }

	/**
	 * ������վ������������ɾ��
	* @author: liangy  
	* @date 2018��12��4��
	* @param @param hb74
	* @param @param result
	* @param @return
	* @param @throws Exception    
	* @return AjaxReturnMsg   
	* @throws
	 */
	@ApiOperation(value = "������վ������������ɾ��", notes = "������վ������������ɾ��", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/delAppraisalDeclareBatch", method = RequestMethod.POST)
    public AjaxReturnMsg delAppraisalDeclareBatch(@ModelAttribute @Valid Hb74 hb74, BindingResult result) throws Exception {
    	//��������
//        if (result.hasErrors()) {
//            return validate(result);
//        }
        return appraisalStationDeclareService.delAppraisalDeclareBatch(hb74);
    }
}
