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
@Api(description = "鉴定所站审核申报管理控制器")
@RequestMapping("/api/appraisalStationAudit")
public class ApiAppraisalStationAuditController extends MvcHelper{
	
	@Resource
	private ApiAppraisalStationAuditService appraisalStationAuditService;
	
	/**
	 * 鉴定所站审核列表数据查询
	* @author: liangy  
	* @date 2018年11月16日
	* @param @param hb61
	* @param @param result
	* @param @return
	* @param @throws Exception    
	* @return AjaxReturnMsg   
	* @throws
	 */
	@ApiOperation(value = "鉴定所站审核搜索", notes = "鉴定所站审核列表数据搜索")
    @RequestMapping(value = "/getAppraisalList", method = RequestMethod.POST)
	public AjaxReturnMsg getAppraisalList(@ModelAttribute Hb75Temp hb75Temp, BindingResult result ) throws Exception {
		PageInfo<Hb75Temp> pageinfo = appraisalStationAuditService.getAppraisalSpeciaList(hb75Temp);
		return this.success(pageinfo);
	}

	/**
     * 获取申报的培训机构
     *
     * @param codevalue 代码对象
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "获取申报的培训机构", notes = "获取申报的培训机构", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/getTrainComName", method = RequestMethod.POST)
    public AjaxReturnMsg<List<CodeValue>> getTrainComName(@ModelAttribute CodeValue codevalue) throws Exception {
        return appraisalStationAuditService.getTrainComName(codevalue);
    }
	
	/**
	 * 鉴定所站审核详情查询
	* @author: liangy  
	* @date 2018年11月23日
	* @param @param hb75Temp
	* @param @return
	* @param @throws Exception    
	* @return AjaxReturnMsg   
	* @throws
	 */
	@ApiOperation(value = "鉴定所站审核详情查询", notes = "鉴定所站审核详情查询")
    @RequestMapping(value = "/getAppraisalInfo", method = RequestMethod.POST)
	public AjaxReturnMsg getAppraisalInfo(@ModelAttribute Hb75 hb75) throws Exception {
		return appraisalStationAuditService.getAppraisalInfo(hb75.getChb120());
	}

	/**
	 * 未申报学员列表数据查询
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
    @RequestMapping(value = "/getAppraisStudentNotSubmitList", method = RequestMethod.POST)
	public AjaxReturnMsg getAppraisStudentNotSubmitList(@ModelAttribute Hc60Temp_Short hc60Temp_Short, BindingResult result ) throws Exception {
		if (result.hasErrors()) {
            return validate(result);
        }
		PageInfo<Hc60Temp_Short> pageinfo = appraisalStationAuditService.getAppraisStudentNotSubmitList(hc60Temp_Short);
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
		PageInfo<Hc60Temp_Short> pageinfo = appraisalStationAuditService.getAppraisThisStudentList(hc60Temp_Short);
		return this.success(pageinfo);
	}

    /**
     * 学员个人审核信息查询
    * @author: liangy  
    * @date 2018年11月24日
    * @param @param hb75
    * @param @param result
    * @param @return
    * @param @throws Exception    
    * @return AjaxReturnMsg   
    * @throws
     */
    @ApiOperation(value = "学员个人审核信息查询", notes = "学员个人审核信息查询", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/getPerponAuditInfo", method = RequestMethod.POST)
    public AjaxReturnMsg getPerponAuditInfo(@ModelAttribute @Valid Hc63Temp_Short hc63Temp_Short, BindingResult result) throws Exception {
        return appraisalStationAuditService.getPerponAuditInfo(hc63Temp_Short.getChc063());
    }

    /**
     * 审核个人鉴定申请
     *
     * @param Hb75 申报批次对象
     * @param result
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "审核个人鉴定申请", notes = "审核个人鉴定申请", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/savePerponAudit", method = RequestMethod.POST)
    public AjaxReturnMsg savePerponAudit(@ModelAttribute Hc63Temp_Short hc63Temp_Short) throws Exception {
        return appraisalStationAuditService.savePerponAudit(hc63Temp_Short);
    }

    /**
     * 批次中学员审核情况统计
    * @author: liangy  
    * @date 2018年11月24日
    * @param @param hb75
    * @param @param result
    * @param @return
    * @param @throws Exception    
    * @return AjaxReturnMsg   
    * @throws
     */
    @ApiOperation(value = "学员个人审核信息查询", notes = "学员个人审核信息查询", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/getPerponAuditIsPass", method = RequestMethod.POST)
    public AjaxReturnMsg getPerponAuditIsPass(@ModelAttribute @Valid Hc63Temp_Short hc63Temp_Short, BindingResult result) throws Exception {
        return appraisalStationAuditService.getPerponAuditIsPass(hc63Temp_Short.getChb120());
    }

    /**
     * 审核鉴定批次
     *
     * @param Hb75 申报批次对象
     * @param result
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "审核鉴定批次", notes = "审核鉴定批次", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/savePerponAuditAll", method = RequestMethod.POST)
    public AjaxReturnMsg savePerponAuditAll(@ModelAttribute Hb75 hb75) throws Exception {
        return appraisalStationAuditService.savePerponAuditAll(hb75);
    }
}
