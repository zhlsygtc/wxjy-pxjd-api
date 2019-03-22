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
@Api(description = "鉴定所站审核申报管理控制器")
@RequestMapping("/api/persondispatch")
public class ApiPersondispatchController extends MvcHelper{
	
	@Resource
	private ApiPersondispatchService apiPersondispatchService;
	
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
	@ApiOperation(value = "人员指派阶段批次搜索", notes = "人员指派阶段批次搜索")
    @RequestMapping(value = "/getPersondispatchList", method = RequestMethod.POST)
	public AjaxReturnMsg getAppraisalList(@ModelAttribute Hb74Temp_Short hb74Temp_Short) throws Exception {
		PageInfo<Hb74Temp_Short> pageinfo = apiPersondispatchService.getPersondispatchList(hb74Temp_Short);
		return this.success(pageinfo);
	}

	/**
	 * 批次考场信息搜索
	* @author: liangy  
	* @date 2018年12月19日
	* @param @param hb77
	* @param @return
	* @param @throws Exception    
	* @return AjaxReturnMsg   
	* @throws
	 */
	@ApiOperation(value = "批次考场信息搜索", notes = "批次考场信息搜索")
    @RequestMapping(value = "/getExamRoomList", method = RequestMethod.POST)
	public AjaxReturnMsg getExamRoomList(@ModelAttribute Hb77 hb77) throws Exception {
		PageInfo<Hb77> pageinfo = apiPersondispatchService.getExamRoomList(hb77);
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
		return apiPersondispatchService.getAppraisalInfo(hb74.getChb140());
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
		return apiPersondispatchService.examRoomSave(hb77);
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
		PageInfo<Hc63> pageinfo = apiPersondispatchService.getExamRoomPerponList(hc63);
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
		PageInfo<Hc63> pageinfo = apiPersondispatchService.getExamRoomPerponSelectList(hc63);
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
		return apiPersondispatchService.savePerponExamRoom(hc63);
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
		return apiPersondispatchService.deletePerponExamRoom(hc63);
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
		return apiPersondispatchService.getExamRoomBaseInfo(hb77.getChb077());
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
		return apiPersondispatchService.deletePerponExamRoomBatch(hb77);
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
		return apiPersondispatchService.saveExamRoomSubmit(hb74);
	}

	/**
	 * 获取鉴定所站人员信息
	* @author: liangy  
	* @date 2018年12月21日
	* @param @param hb74
	* @param @return
	* @param @throws Exception    
	* @return AjaxReturnMsg   
	* @throws
	 */
	@ApiOperation(value = "获取鉴定所站人员信息", notes = "获取鉴定所站人员信息")
    @RequestMapping(value = "/getAppraiserList", method = RequestMethod.POST)
	public AjaxReturnMsg getAppraiserList(@ModelAttribute Hc73Temp_Short hc73Temp_Short) throws Exception {
		List<Hc73Temp_Short> hc73list = apiPersondispatchService.getAppraiserList(hc73Temp_Short);
		Map<String, Object> result = new HashMap<>();
        result.put("list", hc73list);
        return this.success(result);
	}

	 /**
     * 保存鉴定所占人员派遣信息
    * @author: liangy  
    * @date 2018年12月26日
    * @param @param hc63
    * @param @param result
    * @param @return
    * @param @throws Exception    
    * @return AjaxReturnMsg   
    * @throws
     */
    @ApiOperation(value = "保存鉴定所占人员派遣信息", notes = "保存鉴定所占人员派遣信息", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/saveAppraisCorePerson", method = RequestMethod.POST)
    public AjaxReturnMsg saveAppraisCorePerson(@ModelAttribute @Valid Hb79 hb79, BindingResult result) throws Exception {
    	//检验输入
//        if (result.hasErrors()) {
//            return validate(result);
//        }
        return apiPersondispatchService.saveAppraisCorePerson(hb79);
    }

    /**
     * 人员指派数据提交
    * @author: liangy  
    * @date 2018年12月27日
    * @param @param hb79
    * @param @param result
    * @param @return
    * @param @throws Exception    
    * @return AjaxReturnMsg   
    * @throws
     */
    @ApiOperation(value = "人员指派数据提交", notes = "人员指派数据提交", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/saveAppraisPersonStatus", method = RequestMethod.POST)
    public AjaxReturnMsg saveAppraisPersonStatus(@ModelAttribute @Valid Hb79 hb79, BindingResult result) throws Exception {
        return apiPersondispatchService.saveAppraisPersonStatus(hb79.getChb140(), HB74CHB17CEnum.WAIT_AUDIT.getCode());
    }

    /**
     * 人员指派信息获取
    * @author: liangy  
    * @date 2018年12月27日
    * @param @param hb79
    * @param @param result
    * @param @return
    * @param @throws Exception    
    * @return AjaxReturnMsg   
    * @throws
     */
    @ApiOperation(value = "人员指派信息获取", notes = "人员指派信息获取", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/getAppraisCorePersonList", method = RequestMethod.POST)
    public AjaxReturnMsg getAppraisCorePersonList(@ModelAttribute @Valid Hb79 hb79) throws Exception {
    	List<Hc73> hb79list = apiPersondispatchService.getAppraisCorePersonList(hb79.getChb140(), hb79.getChb077(), hb79.getAab001());
		Map<String, Object> result = new HashMap<>();
        result.put("list", hb79list);
        return this.success(result);
    }
}
