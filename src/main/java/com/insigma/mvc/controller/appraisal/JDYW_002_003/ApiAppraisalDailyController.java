package com.insigma.mvc.controller.appraisal.JDYW_002_003;

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
import com.insigma.mvc.model.CodeValue;
import com.insigma.mvc.model.Hc63;
import com.insigma.mvc.model.appraisal.Hb75;
import com.insigma.mvc.model.appraisal.Hb75Temp;
import com.insigma.mvc.model.appraisal.Hc60Dto_Short;
import com.insigma.mvc.model.appraisal.Hc60Temp_Short;
import com.insigma.mvc.model.appraisal.Hc68Temp_Short;
import com.insigma.mvc.service.appraisal.JDYW_002_003.ApiAppraisalDailyService;

@RestController
@Api(description = "日常鉴定申报管理控制器")
@RequestMapping("/api/appraisaldaily")
public class ApiAppraisalDailyController extends MvcHelper{
	
	@Resource
	private ApiAppraisalDailyService apiAppraisalDailyService;
	
	/**
	 * 鉴定申报列表数据查询
	* @author: liangy  
	* @date 2018年11月16日
	* @param @param hb61
	* @param @param result
	* @param @return
	* @param @throws Exception    
	* @return AjaxReturnMsg   
	* @throws
	 */
	@ApiOperation(value = "鉴定申报搜索", notes = "鉴定申报列表数据搜索")
    @RequestMapping(value = "/getAppraisalList", method = RequestMethod.POST)
	public AjaxReturnMsg getAppraisalList(@ModelAttribute Hb75Temp hb75Temp, BindingResult result ) throws Exception {
		if (result.hasErrors()) {
            return validate(result);
        }
		PageInfo<Hb75Temp> pageinfo = apiAppraisalDailyService.getAppraisalDailyList(hb75Temp);
		return this.success(pageinfo);
	}

	/**
	 * 鉴定申报详情查询
	* @author: liangy  
	* @date 2018年11月23日
	* @param @param hb75Temp
	* @param @return
	* @param @throws Exception    
	* @return AjaxReturnMsg   
	* @throws
	 */
	@ApiOperation(value = "鉴定申报详情查询", notes = "鉴定申报详情查询")
    @RequestMapping(value = "/getAppraisalInfo", method = RequestMethod.POST)
	public AjaxReturnMsg getAppraisalInfo(@ModelAttribute Hb75 hb75) throws Exception {
		return apiAppraisalDailyService.getAppraisalInfo(hb75.getChb120());
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
		PageInfo<Hc60Temp_Short> pageinfo = apiAppraisalDailyService.getAppraisStudentNotSubmitList(hc60Temp_Short);
		return this.success(pageinfo);
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
	@ApiOperation(value = "本批次申报学员列表查询", notes = "本批次学员列表查询")
    @RequestMapping(value = "/getAppraisStudentSubmitList", method = RequestMethod.POST)
	public AjaxReturnMsg getAppraisStudentSubmitList(@ModelAttribute Hc60Dto_Short hc60Dto_Short, BindingResult result ) throws Exception {
		if (result.hasErrors()) {
            return validate(result);
        }
		PageInfo<Hc60Temp_Short> pageinfo = apiAppraisalDailyService.getAppraisStudentSubmitList(hc60Dto_Short);
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
		PageInfo<Hc60Temp_Short> pageinfo = apiAppraisalDailyService.getAppraisThisStudentList(hc60Temp_Short);
		return this.success(pageinfo);
	}

	/**
	 * 鉴定申请机构班级列表查询
	* @author: liangy  
	* @date 2018年11月19日
	* @param @param hb75
	* @param @param result
	* @param @return
	* @param @throws Exception    
	* @return AjaxReturnMsg   
	* @throws
	 */
	@ApiOperation(value = "鉴定申请机构班级列表查询", notes = "鉴定申请机构班级列表查询")
    @RequestMapping(value = "/getAppraisClassList", method = RequestMethod.POST)
	public AjaxReturnMsg getAppraisClassList(@ModelAttribute Hc68Temp_Short hc68Temp_Short, BindingResult result ) throws Exception {
		PageInfo<Hc68Temp_Short> pageinfo = apiAppraisalDailyService.getAppraisClassList(hc68Temp_Short);
		return this.success(pageinfo);
	}

	/**
	 *查询鉴定申报批次下的学员信息
	* @author: liangy  
	* @date 2018年11月19日
	* @param @param hb75
	* @param @param result
	* @param @return
	* @param @throws Exception    
	* @return AjaxReturnMsg   
	* @throws
	 */
	@ApiOperation(value = "查询鉴定申报批次下的学员信息", notes = "查询鉴定申报批次下的学员信息")
    @RequestMapping(value = "/getAppraisClassListForLook", method = RequestMethod.POST)
	public AjaxReturnMsg getAppraisClassListForLook(@ModelAttribute Hc68Temp_Short hc68Temp_Short, BindingResult result ) throws Exception {
		PageInfo<Hc68Temp_Short> pageinfo = apiAppraisalDailyService.getAppraisClassListForLook(hc68Temp_Short);
		return this.success(pageinfo);
	}
	/**
     * 保存鉴定申请基本信息
     *
     * @param Hb75 申报批次对象
     * @param result
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "新增鉴定申报批次基本信息", notes = "新增鉴定申报批次", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/insertData", method = RequestMethod.POST)
    public AjaxReturnMsg saveData(@ModelAttribute Hb75 hb75) throws Exception {
        return apiAppraisalDailyService.insertData(hb75);
    }

    /**
     * 批量新增鉴定申报记录
    * @author: liangy  
    * @date 2018年11月24日
    * @param @param hc63
    * @param @param result
    * @param @return
    * @param @throws Exception    
    * @return AjaxReturnMsg   
    * @throws
     */
    @ApiOperation(value = "批量新增鉴定申报记录", notes = "批量新增鉴定申报记录", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/savePerponDataBath", method = RequestMethod.POST)
    public AjaxReturnMsg savePerponDataBath(@ModelAttribute @Valid Hc63 hc63, BindingResult result) throws Exception {
    	//检验输入
//        if (result.hasErrors()) {
//            return validate(result);
//        }
        return apiAppraisalDailyService.savePerponDataBath(hc63);
    }

    /**
     * 批次删除操作
    * @author: liangy  
    * @date 2018年11月24日
    * @param @param hb75
    * @param @param result
    * @param @return
    * @param @throws Exception    
    * @return AjaxReturnMsg   
    * @throws
     */
    @ApiOperation(value = "批次删除操作", notes = "批次删除操作", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/deletebyid", method = RequestMethod.POST)
    public AjaxReturnMsg deletebyid(@ModelAttribute @Valid Hb75 hb75, BindingResult result) throws Exception {
    	//检验输入
//        if (result.hasErrors()) {
//            return validate(result);
//        }
        return apiAppraisalDailyService.deletebyid(hb75);
    }

    /**
     * 批次批量删除操作
    * @author: liangy  
    * @date 2018年11月24日
    * @param @param hb75
    * @param @param result
    * @param @return
    * @param @throws Exception    
    * @return AjaxReturnMsg   
    * @throws
     */
    @ApiOperation(value = "批次批量删除操作", notes = "批次批量删除操作", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/deletebybatch", method = RequestMethod.POST)
    public AjaxReturnMsg deletebybatch(@ModelAttribute @Valid Hb75 hb75, BindingResult result) throws Exception {
    	//检验输入
//        if (result.hasErrors()) {
//            return validate(result);
//        }
        return apiAppraisalDailyService.deletebybatch(hb75);
    }

    /**
     * 本市/上级鉴定中心查询
    * @author: liangy  
    * @date 2018年11月30日
    * @param @param hb75
    * @param @param result
    * @param @return
    * @param @throws Exception    
    * @return AjaxReturnMsg   
    * @throws
     */
    @ApiOperation(value = "本市/上级鉴定中心查询", notes = "本市/上级鉴定中心查询", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/getAppraisCoreList", method = RequestMethod.POST)
    public AjaxReturnMsg getAppraisCoreList(@ModelAttribute @Valid Hb75Temp hb75Temp) throws Exception {
    	List<Hb75Temp> list = apiAppraisalDailyService.getAppraisCoreList(hb75Temp);
    	Map<String, Object> result = new HashMap<>();
        result.put("list", list);
        return this.success(result);
    }

    /**
     * 获取机构培训资质
     * @param codevalue 资质编号
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "获取机构培训资质", notes = "资质编号", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/getAca111List", method = RequestMethod.POST)
    public AjaxReturnMsg<List<CodeValue>> getAca111List(@ModelAttribute CodeValue codevalue) throws Exception {
        return apiAppraisalDailyService.getAca111List(codevalue);
    }
    /**
     * 获取机构培训资质等级
     * @param codevalue 资质编号
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "获取机构培训资质等级", notes = "资质编号", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/getAca11aList", method = RequestMethod.POST)
    public AjaxReturnMsg<List<CodeValue>> getAca11aList(@ModelAttribute CodeValue codevalue) throws Exception {
        return apiAppraisalDailyService.getAca11aList(codevalue.getId(), codevalue.getCode_type());
    }
}
