package com.insigma.mvc.controller.appraisal.JDYW_002_004;

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
import com.insigma.mvc.MvcHelper;
import com.insigma.mvc.model.Hc63;
import com.insigma.mvc.model.appraisal.Hb75;
import com.insigma.mvc.model.appraisal.Hb75Temp;
import com.insigma.mvc.model.appraisal.Hc60Dto_Short;
import com.insigma.mvc.model.appraisal.Hc60Temp_Short;
import com.insigma.mvc.model.appraisal.Hc68Temp_Short;
import com.insigma.mvc.service.appraisal.JDYW_002_004.ApiAppraisalSpeciaService;

@RestController
@Api(description = "ר�������걨���������")
@RequestMapping("/api/appraisalSpecia")
public class ApiAppraisalSpeciaController extends MvcHelper{
	
	@Resource
	private ApiAppraisalSpeciaService apiAppraisalSpeciaService;
	
	/**
	 * �����걨�б����ݲ�ѯ
	* @author: liangy  
	* @date 2018��11��16��
	* @param @param hb61
	* @param @param result
	* @param @return
	* @param @throws Exception    
	* @return AjaxReturnMsg   
	* @throws
	 */
	@ApiOperation(value = "�����걨����", notes = "�����걨�б���������")
    @RequestMapping(value = "/getAppraisalList", method = RequestMethod.POST)
	public AjaxReturnMsg getAppraisalList(@ModelAttribute Hb75Temp hb75Temp, BindingResult result ) throws Exception {
		if (result.hasErrors()) {
            return validate(result);
        }
		PageInfo<Hb75Temp> pageinfo = apiAppraisalSpeciaService.getAppraisalSpeciaList(hb75Temp);
		return this.success(pageinfo);
	}

	/**
	 * �����걨�����ѯ
	* @author: liangy  
	* @date 2018��11��23��
	* @param @param hb75Temp
	* @param @return
	* @param @throws Exception    
	* @return AjaxReturnMsg   
	* @throws
	 */
	@ApiOperation(value = "�����걨�����ѯ", notes = "�����걨�����ѯ")
    @RequestMapping(value = "/getAppraisalInfo", method = RequestMethod.POST)
	public AjaxReturnMsg getAppraisalInfo(@ModelAttribute Hb75 hb75) throws Exception {
		return apiAppraisalSpeciaService.getAppraisalInfo(hb75.getChb120());
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
	public AjaxReturnMsg getAppraisStudentNotSubmitList(@ModelAttribute Hc60Temp_Short hc60Temp_Short) throws Exception {
		PageInfo<Hc60Temp_Short> pageinfo = apiAppraisalSpeciaService.getAppraisStudentNotSubmitList(hc60Temp_Short);
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
		PageInfo<Hc60Temp_Short> pageinfo = apiAppraisalSpeciaService.getAppraisThisStudentList(hc60Temp_Short);
		return this.success(pageinfo);
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
	@ApiOperation(value = "�������걨ѧԱ�б��ѯ", notes = "������ѧԱ�б��ѯ")
    @RequestMapping(value = "/getAppraisStudentSubmitList", method = RequestMethod.POST)
	public AjaxReturnMsg getAppraisStudentSubmitList(@ModelAttribute Hc60Dto_Short hc60Dto_Short, BindingResult result ) throws Exception {
		if (result.hasErrors()) {
            return validate(result);
        }
		PageInfo<Hc60Temp_Short> pageinfo = apiAppraisalSpeciaService.getAppraisStudentSubmitList(hc60Dto_Short);
		return this.success(pageinfo);
	}

	/**
	 * ������������༶�б��ѯ
	* @author: liangy  
	* @date 2018��11��19��
	* @param @param hb75
	* @param @param result
	* @param @return
	* @param @throws Exception    
	* @return AjaxReturnMsg   
	* @throws
	 */
	@ApiOperation(value = "������������༶�б��ѯ", notes = "������������༶�б��ѯ")
    @RequestMapping(value = "/getAppraisClassList", method = RequestMethod.POST)
	public AjaxReturnMsg getAppraisClassList(@ModelAttribute Hc68Temp_Short hc68Temp_Short, BindingResult result ) throws Exception {
		PageInfo<Hc68Temp_Short> pageinfo = apiAppraisalSpeciaService.getAppraisClassList(hc68Temp_Short);
		return this.success(pageinfo);
	}
	/**
	 *��ѯ�����걨�����µ�ѧԱ��Ϣ
	* @author: liangy  
	* @date 2018��11��19��
	* @param @param hb75
	* @param @param result
	* @param @return
	* @param @throws Exception    
	* @return AjaxReturnMsg   
	* @throws
	 */
	@ApiOperation(value = "��ѯ�����걨�����µ�ѧԱ��Ϣ", notes = "��ѯ�����걨�����µ�ѧԱ��Ϣ")
    @RequestMapping(value = "/getAppraisClassListForLook", method = RequestMethod.POST)
	public AjaxReturnMsg getAppraisClassListForLook(@ModelAttribute Hc68Temp_Short hc68Temp_Short, BindingResult result ) throws Exception {
		PageInfo<Hc68Temp_Short> pageinfo = apiAppraisalSpeciaService.getAppraisClassListForLook(hc68Temp_Short);
		return this.success(pageinfo);
	}
	/**
     * ����������������Ϣ
     *
     * @param Hb75 �걨���ζ���
     * @param result
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "���������걨���λ�����Ϣ", notes = "���������걨����", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/insertData", method = RequestMethod.POST)
    public AjaxReturnMsg saveData(@ModelAttribute @Valid Hb75 hb75, BindingResult result) throws Exception {
    	//��������
//        if (result.hasErrors()) {
//            return validate(result);
//        }
        return apiAppraisalSpeciaService.insertData(hb75);
    }

    /**
     * �������������걨��¼
    * @author: liangy  
    * @date 2018��11��24��
    * @param @param hc63
    * @param @param result
    * @param @return
    * @param @throws Exception    
    * @return AjaxReturnMsg   
    * @throws
     */
    @ApiOperation(value = "�������������걨��¼", notes = "�������������걨��¼", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/savePerponDataBath", method = RequestMethod.POST)
    public AjaxReturnMsg savePerponDataBath(@ModelAttribute @Valid Hc63 hc63, BindingResult result) throws Exception {
    	//��������
//        if (result.hasErrors()) {
//            return validate(result);
//        }
        return apiAppraisalSpeciaService.savePerponDataBath(hc63);
    }

    /**
     * ����ɾ������
    * @author: liangy  
    * @date 2018��11��24��
    * @param @param hb75
    * @param @param result
    * @param @return
    * @param @throws Exception    
    * @return AjaxReturnMsg   
    * @throws
     */
    @ApiOperation(value = "����ɾ������", notes = "����ɾ������", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/deletebyid", method = RequestMethod.POST)
    public AjaxReturnMsg deletebyid(@ModelAttribute @Valid Hb75 hb75, BindingResult result) throws Exception {
    	//��������
//        if (result.hasErrors()) {
//            return validate(result);
//        }
        return apiAppraisalSpeciaService.deletebyid(hb75);
    }

    /**
     * ��������ɾ������
    * @author: liangy  
    * @date 2018��11��24��
    * @param @param hb75
    * @param @param result
    * @param @return
    * @param @throws Exception    
    * @return AjaxReturnMsg   
    * @throws
     */
    @ApiOperation(value = "��������ɾ������", notes = "��������ɾ������", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/deletebybatch", method = RequestMethod.POST)
    public AjaxReturnMsg deletebybatch(@ModelAttribute @Valid Hb75 hb75, BindingResult result) throws Exception {
    	//��������
//        if (result.hasErrors()) {
//            return validate(result);
//        }
        return apiAppraisalSpeciaService.deletebybatch(hb75);
    }
}
