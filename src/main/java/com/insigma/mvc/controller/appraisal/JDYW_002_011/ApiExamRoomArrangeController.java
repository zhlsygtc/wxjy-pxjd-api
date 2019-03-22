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
@Api(description = "鉴定所站审核申报管理控制器")
@RequestMapping("/api/examRoomArrange")
public class ApiExamRoomArrangeController extends MvcHelper{
	
	@Resource
	private ApiExamRoomArrangeService apiExamRoomArrangeService;
	
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
    @RequestMapping(value = "/getAppraisalFreeList", method = RequestMethod.POST)
	public AjaxReturnMsg getAppraisalList(@ModelAttribute Hb74Temp_Short hb74Temp_Short) throws Exception {
		PageInfo<Hb74Temp_Short> pageinfo = apiExamRoomArrangeService.getAppraisalSpeciaList(hb74Temp_Short);
		return this.success(pageinfo);
	}

	/**
	 * 考试批次信息搜索
	* @author: liangy  
	* @date 2018年12月19日
	* @param @param hb77
	* @param @return
	* @param @throws Exception    
	* @return AjaxReturnMsg   
	* @throws
	 */
	@ApiOperation(value = "考试批次信息搜索", notes = "考试批次信息搜索")
    @RequestMapping(value = "/getExamRoomBatchList", method = RequestMethod.POST)
	public AjaxReturnMsg getExamRoomBatchList(@ModelAttribute Hb70 hb70) throws Exception {
		PageInfo<Hb70> pageinfo = apiExamRoomArrangeService.getExamRoomBatchList(hb70);
		return this.success(pageinfo);
	}

	/**
	 * 考试信息搜索
	* @author: liangy  
	* @date 2018年12月19日
	* @param @param hb77
	* @param @return
	* @param @throws Exception    
	* @return AjaxReturnMsg   
	* @throws
	 */
	@ApiOperation(value = "考试列表信息搜索", notes = "考试列表信息搜索")
    @RequestMapping(value = "/getExamRoomList", method = RequestMethod.POST)
	public AjaxReturnMsg getExamRoomList(@ModelAttribute Hb77 hb77) throws Exception {
		PageInfo<Hb77> pageinfo = apiExamRoomArrangeService.getExamRoomList(hb77);
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
    @RequestMapping(value = "/getExamRoomInfo", method = RequestMethod.POST)
	public AjaxReturnMsg getAppraisalInfo(@ModelAttribute Hb74 hb74) throws Exception {
		return apiExamRoomArrangeService.getAppraisalInfo(hb74.getChb140());
	}

	/**
	 * 考试基本信息保存
	* @author: liangy  
	* @date 2018年12月3日
	* @param @param hb74Temp_Short
	* @param @return
	* @param @throws Exception    
	* @return AjaxReturnMsg   
	* @throws
	 */
	@ApiOperation(value = "考试基本信息保存", notes = "考试基本信息保存")
    @RequestMapping(value = "/examRoomBatchSave", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public AjaxReturnMsg examRoomBatchSave(@ModelAttribute @Valid Hb70 hb70, BindingResult result) throws Exception {
		return apiExamRoomArrangeService.examRoomBatchSave(hb70);
	}

	/**
	 * 考场自动分配
	* @author: liangy  
	* @date 2018年12月3日
	* @param @param hb74Temp_Short
	* @param @return
	* @param @throws Exception    
	* @return AjaxReturnMsg   
	* @throws
	 */
	@ApiOperation(value = "考场自动分配", notes = "考场自动分配")
    @RequestMapping(value = "/automaticExamArrange", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public AjaxReturnMsg automaticExamArrange(@ModelAttribute @Valid Hb70 hb70, BindingResult result) throws Exception {
		return apiExamRoomArrangeService.automaticExamArrange(hb70);
	}

	/**
	 * 考场基本信息保存
	* @author: liangy  
	* @date 2018年12月3日
	* @param @param hb74Temp_Short
	* @param @return
	* @param @throws Exception    
	* @return AjaxReturnMsg   
	* @throws
	 */
	@ApiOperation(value = "考场基本信息保存", notes = "考场基本信息保存")
    @RequestMapping(value = "/examRoomSave", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public AjaxReturnMsg examRoomSave(@ModelAttribute @Valid Hb77 hb77, BindingResult result) throws Exception {
		return apiExamRoomArrangeService.examRoomSave(hb77);
	}

	/**
	 * 当前考场人员信息查询
	* @author: liangy  
	* @date 2018年12月19日
	* @param @param hb77
	* @param @return
	* @param @throws Exception    
	* @return AjaxReturnMsg   
	* @throws
	 */
	@ApiOperation(value = "当前考场人员信息查询", notes = "当前考场人员信息查询")
    @RequestMapping(value = "/getExamRoomPerponList", method = RequestMethod.POST)
	public AjaxReturnMsg getExamRoomPerponList(@ModelAttribute Hc63 hc63) throws Exception {
		PageInfo<Hc63> pageinfo = apiExamRoomArrangeService.getExamRoomPerponList(hc63);
		return this.success(pageinfo);
	}

	/**
	 * 考场人员信息选择查询
	* @author: liangy  
	* @date 2018年12月20日
	* @param @param hc63
	* @param @return
	* @param @throws Exception    
	* @return AjaxReturnMsg   
	* @throws
	 */
	@ApiOperation(value = "考场人员信息选择查询", notes = "考场人员信息选择查询")
    @RequestMapping(value = "/getExamRoomPerponSelectList", method = RequestMethod.POST)
	public AjaxReturnMsg getExamRoomPerponSelectList(@ModelAttribute Hc63 hc63) throws Exception {
		PageInfo<Hc63> pageinfo = apiExamRoomArrangeService.getExamRoomPerponSelectList(hc63);
		return this.success(pageinfo);
	}

	/**
	 * 考场人员信息保存
	* @author: liangy  
	* @date 2018年12月20日
	* @param @param hc63
	* @param @param result
	* @param @return
	* @param @throws Exception    
	* @return AjaxReturnMsg   
	* @throws
	 */
	@ApiOperation(value = "考场人员信息保存", notes = "考场人员信息保存")
    @RequestMapping(value = "/savePerponExamRoom", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public AjaxReturnMsg savePerponExamRoom(@ModelAttribute @Valid Hc63 hc63, BindingResult result) throws Exception {
		return apiExamRoomArrangeService.savePerponExamRoom(hc63);
	}

	/**
	 * 从考场批量移出人员信息
	* @author: liangy  
	* @date 2018年12月21日
	* @param @param hc63
	* @param @param result
	* @param @return
	* @param @throws Exception    
	* @return AjaxReturnMsg   
	* @throws
	 */
	@ApiOperation(value = "从考场批量移出人员信息", notes = "从考场批量移出人员信息")
    @RequestMapping(value = "/deletePerponExamRoom", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public AjaxReturnMsg deletePerponExamRoom(@ModelAttribute @Valid Hc63 hc63, BindingResult result) throws Exception {
		return apiExamRoomArrangeService.deletePerponExamRoom(hc63);
	}

	/**
	 * 考试信息详情
	* @author: liangy  
	* @date 2018年12月21日
	* @param @param hb74
	* @param @return
	* @param @throws Exception    
	* @return AjaxReturnMsg   
	* @throws
	 */
	@ApiOperation(value = "考试信息详情", notes = "考试信息详情")
    @RequestMapping(value = "/getExamRoomBaseBatchInfo", method = RequestMethod.POST)
	public AjaxReturnMsg getExamRoomBaseBatchInfo(@ModelAttribute Hb70 hb70) throws Exception {
		return apiExamRoomArrangeService.getExamRoomBaseBatchInfo(hb70.getChb070());
	}

	/**
	 * 考场信息详情
	* @author: liangy  
	* @date 2018年12月21日
	* @param @param hb74
	* @param @return
	* @param @throws Exception    
	* @return AjaxReturnMsg   
	* @throws
	 */
	@ApiOperation(value = "考场信息详情", notes = "考场信息详情")
    @RequestMapping(value = "/getExamRoomBaseInfo", method = RequestMethod.POST)
	public AjaxReturnMsg getExamRoomBaseInfo(@ModelAttribute Hb77 hb77) throws Exception {
		return apiExamRoomArrangeService.getExamRoomBaseInfo(hb77.getChb077());
	}

	/**
	 * 批量删除考场信息
	* @author: liangy  
	* @date 2018年12月21日
	* @param @param hc63
	* @param @param result
	* @param @return
	* @param @throws Exception    
	* @return AjaxReturnMsg   
	* @throws
	 */
	@ApiOperation(value = "批量删除考场信息", notes = "批量删除考场信息")
    @RequestMapping(value = "/deletePerponExamRoomBatch", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public AjaxReturnMsg deletePerponExamRoomBatch(@ModelAttribute @Valid Hb77 hb77, BindingResult result) throws Exception {
		return apiExamRoomArrangeService.deletePerponExamRoomBatch(hb77);
	}

	/**
	 * 考场编排信息提交
	* @author: liangy  
	* @date 2018年12月24日
	* @param @param hb74
	* @param @param result
	* @param @return
	* @param @throws Exception    
	* @return AjaxReturnMsg   
	* @throws
	 */
	@ApiOperation(value = "考场编排信息提交", notes = "考场编排信息提交")
    @RequestMapping(value = "/saveExamRoomSubmit", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public AjaxReturnMsg saveExamRoomSubmit(@ModelAttribute @Valid Hb74 hb74, BindingResult result) throws Exception {
		return apiExamRoomArrangeService.saveExamRoomSubmit(hb74);
	}

	/**
	 * 查询人数当前已编排人数
	* @author: liangy  
	* @date 2018年12月29日
	* @param @param hb77
	* @param @param result
	* @param @return
	* @param @throws Exception    
	* @return AjaxReturnMsg   
	* @throws
	 */
	@ApiOperation(value = "查询人数当前已编排人数", notes = "查询人数当前已编排人数")
    @RequestMapping(value = "/getselectExamRoomPerponNum", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public AjaxReturnMsg getselectExamRoomPerponNum(@ModelAttribute @Valid Hb77 hb77, BindingResult result) throws Exception {
		return apiExamRoomArrangeService.getselectExamRoomPerponNum(hb77.getChb070(), hb77.getChb140());
	} 

	/**
	 * 查询考点list
	* @author: liangy  
	* @date 2019年1月4日
	* @param @param hb77
	* @param @param result
	* @param @return
	* @param @throws Exception    
	* @return AjaxReturnMsg   
	* @throws
	 */
	@ApiOperation(value = "查询考点list", notes = "查询考点list")
    @RequestMapping(value = "/getTestCenterList", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public AjaxReturnMsg getTestCenterList(@ModelAttribute @Valid Hb76 hb76) throws Exception {
		List<Hb76> hb79list = apiExamRoomArrangeService.getTestCenterList(hb76.getAab001());
		Map<String, Object> result = new HashMap<>();
	    result.put("list", hb79list);
	    return this.success(result);
	}

	/**
	 * 获取考场list
	* @author: liangy  
	* @date 2019年1月4日
	* @param @param hb78
	* @param @return
	* @param @throws Exception    
	* @return AjaxReturnMsg   
	* @throws
	 */
	@ApiOperation(value = "查询考场list", notes = "查询考场list")
    @RequestMapping(value = "/getExamRoomSelectList", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public AjaxReturnMsg getExamRoomSelectList(@ModelAttribute @Valid Hb78 hb78) throws Exception {
		List<Hb78> hb79list = apiExamRoomArrangeService.getExamRoomSelectList(hb78.getChb076());
		Map<String, Object> result = new HashMap<>();
	    result.put("list", hb79list);
	    return this.success(result);
	}

	/**
	 * 批量删除考试批次信息
	* @author: liangy  
	* @date 2019年1月28日
	* @param @param hb70
	* @param @return
	* @param @throws Exception    
	* @return AjaxReturnMsg   
	* @throws
	 */
	@ApiOperation(value = "批量删除考试批次信息", notes = "批量删除考试批次信息")
    @RequestMapping(value = "/deleteExaminationBatch", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public AjaxReturnMsg deleteExaminationBatch(@ModelAttribute @Valid Hb70 hb70) throws Exception {
		return apiExamRoomArrangeService.deleteExaminationBatch(hb70);
	}
}
