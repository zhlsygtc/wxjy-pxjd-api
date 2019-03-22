package com.insigma.mvc.controller.appraisal.JDYW_002_001;

import java.util.List;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.github.pagehelper.PageInfo;
import com.insigma.dto.AjaxReturnMsg;
import com.insigma.mvc.MvcHelper;
import com.insigma.mvc.model.CodeValue;
import com.insigma.mvc.model.appraisal.Hb76;
import com.insigma.mvc.model.appraisal.Hb78;
import com.insigma.mvc.model.train.Ad01;
import com.insigma.mvc.model.train.Hb62;
import com.insigma.mvc.model.train.Hb63;
import com.insigma.mvc.model.train.Hb64;
import com.insigma.mvc.service.appraisal.JDYW_002_001.ApiAppraisalCompanyService;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 * 鉴定机构信息维护
 * 2018-12-17
 */
@RestController
@RequestMapping("/api/appraisalCompany")
public class ApiAppraisalCompanyController extends MvcHelper{
	@Resource
	private ApiAppraisalCompanyService apiAppraisalCompanyService;
	
	@RequestMapping(value = "/getAd01ById")
    public AjaxReturnMsg getHb68ById(Ad01 ad01) throws Exception{
		return this.success(apiAppraisalCompanyService.getAD01ById(ad01.getGroupid()));
    }
	/**
	 * 初始化培训场地信息
	 */
	@ApiOperation(value = "初始化培训场地信息", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/getGroundInfo",method = RequestMethod.POST)
	public AjaxReturnMsg getGroundInfo(Hb76 hb76) throws Exception {
		PageInfo<Hb76> pageInfo = apiAppraisalCompanyService.getGroundInfo(hb76);
		return this.success(pageInfo);
		
	}
	/**
	 * 查询code_value中的chb065字段
	 */
	@ApiOperation(value = "查询code_value中的chb065字段", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/getCodeValueChb065",method = RequestMethod.POST)
	public AjaxReturnMsg getCodeValueChb065(Hb64 hb64) throws Exception{
		List<Hb64> hb64List = apiAppraisalCompanyService.getCodeValueChb065();
		return this.success(hb64List);
	}
	/**
	 * 查询code_value中的chb163字段
	 */
	@ApiOperation(value = "查询code_value中的chb163字段", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/getCodeValueChb163",method = RequestMethod.POST)
	public AjaxReturnMsg getCodeValueChb163(Hb64 hb64) throws Exception{
		List<Hb64> hb64List = apiAppraisalCompanyService.getCodeValueChb163();
		return this.success(hb64List);
	}
	/**
	 * 查询code_value中的chb070字段
	 */
	@ApiOperation(value = "查询code_value中的chb070字段", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/getCodeValueChb070",method = RequestMethod.POST)
	public AjaxReturnMsg getCodeValueChb070(Hb64 hb64) throws Exception{
		List<Hb64> hb64List = apiAppraisalCompanyService.getCodeValueChb070();
		return this.success(hb64List);
	}
	/**
	 * 初始化培训教室信息
	 */
	@ApiOperation(value = "初始化培训教室信息", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/getClassroomInfo",method = RequestMethod.POST)
	public AjaxReturnMsg getClassroomInfo(Hb78 hb78) throws Exception {
		PageInfo<Hb78> pageInfo = apiAppraisalCompanyService.getClassroomInfo(hb78);
		return this.success(pageInfo);
		
	}
	
	/**
	 * 初始化资质信息
	 */
	@ApiOperation(value = "初始化资质信息", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/getQualityInfo",method = RequestMethod.POST)
	public AjaxReturnMsg getQualityInfo(Hb62 hb62) throws Exception {
		PageInfo<Hb62> pageInfo = apiAppraisalCompanyService.getQualityInfo(hb62);
		return this.success(pageInfo);
		
	}
	 /**
	 * 保存培训机构场地信息
	 */
	@ApiOperation(value = "保存培训机构场地信息", produces = MediaType.APPLICATION_JSON_VALUE)
   @RequestMapping(value = "/saveGroundInfo",method = RequestMethod.POST)
	public AjaxReturnMsg saveGroundInfo(Hb63 hb63) throws Exception {
		return apiAppraisalCompanyService.saveGroundInfo(hb63);
	}
	 /**
	 * 保存培训机构教室信息
	 */
	@ApiOperation(value = "保存培训机构场地信息", produces = MediaType.APPLICATION_JSON_VALUE)
   @RequestMapping(value = "/saveClassroomInfo",method = RequestMethod.POST)
	public AjaxReturnMsg saveClassroomInfo(Hb63 hb63) throws Exception {
		return apiAppraisalCompanyService.saveClassroomInfo(hb63);
	}
	 
	/**
	 * 保存培训机构资质信息
	 */
	@ApiOperation(value = "保存培训机构资质信息", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/saveQualityInfo",method = RequestMethod.POST)
	public AjaxReturnMsg saveQualityInfo(Hb62 hb62) throws Exception {
		return apiAppraisalCompanyService.saveQualityInfo(hb62);
	}
	
	
	/**
	 * 机构照片上传
	 *
	 * @param file_bus_id
	 * @param file_name
	 * @param multipartFile
	 * @return
	 */
	@ApiOperation(value = "培训机构LOGO上传", notes = "培训机构LOGO上传", consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiImplicitParams({
			@ApiImplicitParam(name = "file_bus_id", value = "培训机构内码", required = true, paramType = "query"),
			@ApiImplicitParam(name = "file_name", value = "文件名称，没有后缀", required = true, paramType = "query")
	})
	@RequestMapping(value = "/uploadLogo", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public AjaxReturnMsg uploadFile(String file_bus_id, String file_name,String userid,
									@RequestParam("uploadFile") MultipartFile multipartFile) {
		return apiAppraisalCompanyService.uploadLogo(userid, file_bus_id, file_name, multipartFile);
	}
	
	/**
	 * 机构信息修改
	 */
	@ApiOperation(value = "机构信息维护", notes = "机构信息维护", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/savedata", method = RequestMethod.POST)
    public AjaxReturnMsg saveData(@ModelAttribute @Valid Ad01 ad01, BindingResult result) throws Exception {
    	//检验输入
        if (result.hasErrors()) {
            return validate(result);
        }
        return apiAppraisalCompanyService.saveData(ad01);
    }
	
	
	 /**
   	 * 根据专业名称查询培训工种名称及专业类别
   	 */
	@ApiOperation(value = "根据专业名称查询培训工种名称及专业类别", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/getAca112",method = RequestMethod.POST)
   	public AjaxReturnMsg getAca112(CodeValue codevalue) throws Exception{
   		codevalue = apiAppraisalCompanyService.getAca112(codevalue.getCode_name());
   		return this.success(codevalue);
   	}
}
