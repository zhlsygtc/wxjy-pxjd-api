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
@Api(description = "鉴定所站审核申报管理控制器")
@RequestMapping("/api/appraisalStationDeclare")
public class ApiAppraisalStationDeclareController extends MvcHelper{
	
	@Resource
	private ApiAppraisalStationDeclareService appraisalStationDeclareService;
	
	/**
	 * 鉴定所站批次列表数据搜索
	* @author: liangy  
	* @date 2018年11月16日
	* @param @param hb61
	* @param @param result
	* @param @return
	* @param @throws Exception    
	* @return AjaxReturnMsg   
	* @throws
	 */
	@ApiOperation(value = "鉴定所站批次搜索", notes = "鉴定所站批次列表数据搜索")
    @RequestMapping(value = "/getAppraisalList", method = RequestMethod.POST)
	public AjaxReturnMsg getAppraisalList(@ModelAttribute Hb74Temp_Short hb74Temp_Short) throws Exception {
		PageInfo<Hb74Temp_Short> pageinfo = appraisalStationDeclareService.getAppraisalSpeciaList(hb74Temp_Short);
		return this.success(pageinfo);
	}

	/**
	 * 查询鉴定所站批次详情
	* @author: liangy  
	* @date 2018年12月3日
	* @param @param hb74Temp_Short
	* @param @return
	* @param @throws Exception    
	* @return AjaxReturnMsg   
	* @throws
	 */
	@ApiOperation(value = "鉴定所站批次详情查询", notes = "鉴定所站批次详情查询")
    @RequestMapping(value = "/getAppraisalInfo", method = RequestMethod.POST)
	public AjaxReturnMsg getAppraisalInfo(@ModelAttribute Hb74 hb74) throws Exception {
		return appraisalStationDeclareService.getAppraisalInfo(hb74.getChb140());
	}

	/**
     * 鉴定所站鉴定计划制定(加入计划)
     * @param Hb75 申报批次对象
     * @param result
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "鉴定所站鉴定计划制定(加入计划)", notes = "鉴定所站鉴定计划制定(加入计划)", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/savePerponAuditIn", method = RequestMethod.POST)
    public AjaxReturnMsg savePerponAuditIn(@ModelAttribute @Valid Hb75Temp hb75Temp, BindingResult result) throws Exception {
    	//检验输入
//        if (result.hasErrors()) {
//            return validate(result);
//        }
        return appraisalStationDeclareService.savePerponAudit(hb75Temp, "in");
    }

    /**
     * 鉴定所站鉴定计划制定(移出计划)
     * @param Hb75 申报批次对象
     * @param result
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "鉴定所站鉴定计划制定(移出计划)", notes = "鉴定所站鉴定计划制定(移出计划)", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/savePerponAuditOut", method = RequestMethod.POST)
    public AjaxReturnMsg savePerponAuditOut(@ModelAttribute @Valid Hb75Temp hb75Temp, BindingResult result) throws Exception {
    	//检验输入
//        if (result.hasErrors()) {
//            return validate(result);
//        }
        return appraisalStationDeclareService.savePerponAudit(hb75Temp, "out");
    }

    /**
     * 查询空闲批次
    * @author: liangy  
    * @date 2018年11月29日
    * @param @param hb74Temp_Short
    * @param @return
    * @param @throws Exception    
    * @return AjaxReturnMsg   
    * @throws
     */
    @ApiOperation(value = "查询空闲批次", notes = "查询空闲批次")
    @RequestMapping(value = "/getAppraisalFreeList", method = RequestMethod.POST)
	public AjaxReturnMsg getAppraisalFreeList(@ModelAttribute Hb75Temp hb75Temp) throws Exception {
		// 参数设置0  默认查询未被加入计划的批次信息
    	hb75Temp.setChb140("0");
    	// 查询审核通过的数据
    	hb75Temp.setChb312(HB75CHB312Enum.AUDIT_PASS.getCode());
    	hb75Temp.setChb146(HB75CHB146Enum.APPRAISAL_REVIEW_PASS.getCode());
		PageInfo<Hb75Temp> pageinfo = appraisalStationDeclareService.getAppraisalDeclareist(hb75Temp);
		return this.success(pageinfo);
	}

    /**
     * 查询本批次信息列表
    * @author: liangy  
    * @date 2018年11月29日
    * @param @param hb74Temp_Short
    * @param @param result
    * @param @return
    * @param @throws Exception    
    * @return AjaxReturnMsg   
    * @throws
     */
    @ApiOperation(value = "查询本批次信息列表", notes = "查询本批次信息列表")
    @RequestMapping(value = "/getAppraisalNotFreeList", method = RequestMethod.POST)
	public AjaxReturnMsg getAppraisalNotFreeList(@ModelAttribute Hb75Temp hb75Temp) throws Exception {
		PageInfo<Hb75Temp> pageinfo = appraisalStationDeclareService.getAppraisalDeclareist(hb75Temp);
		return this.success(pageinfo);
	}

    /**
	 * 查询该批次学员信息
	* @author: liangy  
	* @date 2018年11月16日
	* @param @param hb61
	* @param @param result
	* @param @return
	* @param @throws Exception    
	* @return AjaxReturnMsg   
	* @throws
	 */
	@ApiOperation(value = "未申报学员列表查询", notes = "未申报学员列表查询")
    @RequestMapping(value = "/getAppraisThisStudentList", method = RequestMethod.POST)
	public AjaxReturnMsg getAppraisThisStudentList(@ModelAttribute Hc60Temp_Short hc60Temp_Short) throws Exception {
		PageInfo<Hc60Temp_Short> pageinfo = appraisalStationDeclareService.getAppraisThisStudentList(hc60Temp_Short);
		return this.success(pageinfo);
	}

	/**
	 * 鉴定所站鉴定批次申报信息保存
	* @author: liangy  
	* @date 2018年12月3日
	* @param @param hb74
	* @param @param result
	* @param @return
	* @param @throws Exception    
	* @return AjaxReturnMsg   
	* @throws
	 */
	@ApiOperation(value = "鉴定所站鉴定批次申报信息保存", notes = "鉴定所站鉴定批次申报信息保存", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/savePerponDelare", method = RequestMethod.POST)
    public AjaxReturnMsg savePerponDelare(@ModelAttribute @Valid Hb74 hb74, BindingResult result) throws Exception {
    	//检验输入
//        if (result.hasErrors()) {
//            return validate(result);
//        }
		hb74.setChb326(HB74CHB326Enum.UNSUBMITTED.getCode());
        return appraisalStationDeclareService.savePerponSubmit(hb74);
    }

	/**
	 * 鉴定所站鉴定批次申报信息提交
	* @author: liangy  
	* @date 2018年12月3日
	* @param @param hb74
	* @param @param result
	* @param @return
	* @param @throws Exception    
	* @return AjaxReturnMsg   
	* @throws
	 */
	@ApiOperation(value = "鉴定所站鉴定批次申报信息提交", notes = "鉴定所站鉴定批次申报信息提交", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/savePerponSubmit", method = RequestMethod.POST)
    public AjaxReturnMsg savePerponSubmit(@ModelAttribute @Valid Hb74 hb74, BindingResult result) throws Exception {
    	//检验输入
//        if (result.hasErrors()) {
//            return validate(result);
//        }
		hb74.setChb326(HB74CHB326Enum.SUBMITTED.getCode());
        return appraisalStationDeclareService.savePerponSubmit(hb74);
    }

	/**
	 * 鉴定所站鉴定申请单个删除
	* @author: liangy  
	* @date 2018年12月4日
	* @param @param hb74
	* @param @param result
	* @param @return
	* @param @throws Exception    
	* @return AjaxReturnMsg   
	* @throws
	 */
	@ApiOperation(value = "鉴定所站鉴定申请单个删除", notes = "鉴定所站鉴定申请单个删除", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/delAppraisalDeclare", method = RequestMethod.POST)
    public AjaxReturnMsg delAppraisalDeclare(@ModelAttribute @Valid Hb74 hb74, BindingResult result) throws Exception {
    	//检验输入
//        if (result.hasErrors()) {
//            return validate(result);
//        }
        return appraisalStationDeclareService.delAppraisalDeclare(hb74);
    }

	/**
	 * 鉴定所站鉴定申请批量删除
	* @author: liangy  
	* @date 2018年12月4日
	* @param @param hb74
	* @param @param result
	* @param @return
	* @param @throws Exception    
	* @return AjaxReturnMsg   
	* @throws
	 */
	@ApiOperation(value = "鉴定所站鉴定申请批量删除", notes = "鉴定所站鉴定申请批量删除", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/delAppraisalDeclareBatch", method = RequestMethod.POST)
    public AjaxReturnMsg delAppraisalDeclareBatch(@ModelAttribute @Valid Hb74 hb74, BindingResult result) throws Exception {
    	//检验输入
//        if (result.hasErrors()) {
//            return validate(result);
//        }
        return appraisalStationDeclareService.delAppraisalDeclareBatch(hb74);
    }
}
