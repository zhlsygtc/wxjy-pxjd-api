package com.insigma.mvc.controller.appraisal.JDYW_002_007;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

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
import com.insigma.mvc.model.appraisal.Hc73;
import com.insigma.mvc.model.train.Hb68;
import com.insigma.mvc.model.train.Hc74;
import com.insigma.mvc.service.appraisal.JDYW_002_007.ApiInvigilatorManageService;

@RestController
@Api(description = "考评员管理管理控制器")
@RequestMapping("/api/invigilatorManage")
public class ApiInvigilatorManageController extends MvcHelper{
	
	@Resource
	private ApiInvigilatorManageService invigilatorManageService;
	
	/**
	 * 获取考评员信息列表
	 * @param hc73 考评员搜索对象
	 * @return
	 */
	@ApiOperation(value = "考评员搜索", notes = "考评员搜索")
    @RequestMapping(value = "/getInvigilatorList", method = RequestMethod.POST)
	public AjaxReturnMsg getInvigilatorList(@ModelAttribute Hc73 hc73, BindingResult result ) throws Exception {
		if (result.hasErrors()) {
            return validate(result);
        }
		hc73.setAab001(hc73.getBaseinfoid());
		PageInfo<Hc73> pageinfo = invigilatorManageService.getInvigilatorList(hc73);
		return this.success(pageinfo);
	}
	
	/**
	 * 根据id获取考评员信息
	 * @param hc73 师资对象
	 * @return
	 */
	@ApiOperation(value = "根据id获取考评员信息", notes = "根据id获取考评员信息", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/getInfo", method = RequestMethod.POST)
    public AjaxReturnMsg getInvigilatorInfo(@ModelAttribute Hc73 hc73) throws Exception {
        return invigilatorManageService.getInvigilatorById(hc73.getChc073(), hc73.getAab001());
    }
	
	/**
	 * 根据id获取考评员信息中文
	 * @param hc73 师资对象
	 * @return
	 */
	@ApiOperation(value = "根据id获取考评员信息中文", notes = "根据id获取考评员信息中文", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/getInfoGBK", method = RequestMethod.POST)
    public AjaxReturnMsg getInvigilatorInfoGBK(@ModelAttribute Hc73 hc73) throws Exception {
        return invigilatorManageService.getInvigilatorGBKById(hc73.getChc073(), hc73.getAab001());
    }

	/**
	 * 根据身份证获取获取考评员信息
	 * @param hc73 师资对象
	 * @return
	 */
	@ApiOperation(value = "根据id获取考评员信息", notes = "根据id获取考评员信息", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/getIdcardInfo", method = RequestMethod.POST)
    public AjaxReturnMsg getIdcardInfo(@ModelAttribute Hc73 hc73) throws Exception {
        return invigilatorManageService.getIdcardInfo(hc73.getAac002());
    }

	/**
     * 检验身份证是否已在考评员库
     *
     * @param hc73 师资对象
     * @param result
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "检验身份证是否已在考评员库", notes = "检验身份证是否已在考评员库", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/checkAac002", method = RequestMethod.POST)
    public AjaxReturnMsg checkAac002(@ModelAttribute Hc73 hc73) throws Exception {
    	return invigilatorManageService.checkAac002(hc73);
    }
	
	
	/**
     * 保存考评员基本信息
     *
     * @param hc73 师资对象
     * @param result
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "保存考评员基本信息", notes = "保存考评员基本信息", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/savedata", method = RequestMethod.POST)
    public AjaxReturnMsg saveData(@ModelAttribute @Valid Hc73 hc73, BindingResult result) throws Exception {
    	//检验输入
        if (result.hasErrors()) {
            return validate(result);
        }
        return invigilatorManageService.saveData(hc73);
    }
	
    /**
     * 删除考评员信息
     *
     * @param hc73 师资对象
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "删除考评员信息", notes = "删除考评员信息", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/deleteInvigilator", method = RequestMethod.POST)
    public AjaxReturnMsg deleteInvigilator(@ModelAttribute @Valid Hc73 hc73, BindingResult result) throws Exception {
    	//检验输入
        if (result.hasErrors()) {
            return validate(result);
        }
    	return invigilatorManageService.deleteInvigilator(hc73);
    }
    
    /**
     * 批量删除考评员信息
     *
     * @param hc73 师资对象
     * @param result
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "批量删除考评员信息", notes = "批量删除考评员信息", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/batdelete", method = RequestMethod.POST)
    public AjaxReturnMsg batDelete(@ModelAttribute @Valid Hc73 hc73, BindingResult result) throws Exception {
    	//检验输入
        if (result.hasErrors()) {
            return validate(result);
        }
    	return invigilatorManageService.batDelete(hc73);
    }
    
    /**
     * 检验此考评员是否已有此资质
     *
     * @param hc74 资质对象
     * @param result
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "检验此考评员是否已有此资质", notes = "检验此考评员是否已有此资质", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/checkQualification", method = RequestMethod.POST)
    public AjaxReturnMsg checkQualification(@ModelAttribute Hc74 hc74) throws Exception {
    	return invigilatorManageService.checkQualification(hc74);
    }
    
	/**
	 * 获取考评员资质信息列表
	 * @param hc74 资质对象
	 * @return
	 */
	@ApiOperation(value = "考评员资质信息", notes = "考评员资质信息")
    @RequestMapping(value = "/getQualificationList", method = RequestMethod.POST)
	public AjaxReturnMsg getQualificationList(@ModelAttribute Hc74 hc74 ) throws Exception {
		PageInfo<Hc74> pageinfo = invigilatorManageService.getQualificationList(hc74);
		return this.success(pageinfo);
		
	}
	
	/**
     * 保存考评员资质信息
     *
     * @param hc74 资质对象
     * @param result
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "保存考评员资质信息", notes = "保存考评员资质信息", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/saveQualification", method = RequestMethod.POST)
    public AjaxReturnMsg saveQualification(@ModelAttribute @Valid Hc74 hc74, BindingResult result) throws Exception {
        //检验输入
        if (result.hasErrors()) {
            return validate(result);
        }
        return invigilatorManageService.saveQualification(hc74);
    }
	
    /**
	 * 根据id获取考评员资质信息
	 * @param hc74 资质对象
	 * @return
	 */
    @ApiOperation(value = "根据id获取考评员资质信息", notes = "根据id获取考评员资质信息", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/getQualification", method = RequestMethod.POST)
    public AjaxReturnMsg getQualification(@ModelAttribute Hc74 hc74) throws Exception {
        return invigilatorManageService.getQualification(hc74.getChc074());
    }
	
    /**
     * 删除考评员资质信息
     *
     * @param hc74 资质对象
     * @param result
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "删除考评员资质信息", notes = "删除考评员资质信息", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/deleteQualification", method = RequestMethod.POST)
    public AjaxReturnMsg deleteQualification(@ModelAttribute @Valid Hc74 hc74, BindingResult result) throws Exception {
    	//检验输入
        if (result.hasErrors()) {
            return validate(result);
        }
        return invigilatorManageService.deleteQualification(hc74.getChc074());
    }
    
    /**
	 * 考评员头像上传
	 *
	 * @param file_bus_id
	 * @param file_name
	 * @param multipartFile
	 * @return
	 */
	@ApiOperation(value = "考评员头像上传", notes = "考评员头像上传", consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiImplicitParams({
			@ApiImplicitParam(name = "file_bus_id", value = "师资内码", required = true, paramType = "query"),
			@ApiImplicitParam(name = "file_name", value = "文件名称，没有后缀", required = true, paramType = "query"),
			@ApiImplicitParam(name = "userid", value = "经办人内码", required = true, paramType = "query")
	})
	@RequestMapping(value = "/uploadLogo", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public AjaxReturnMsg uploadFile(String file_bus_id, String file_name,String userid,
									@RequestParam("uploadFile") MultipartFile multipartFile) {
		return invigilatorManageService.uploadLogo(userid, file_bus_id, file_name, multipartFile);
	}

	/**
	 * 培训机构考评员资质图片上传
	 *
	 * @param file_bus_id
	 * @param file_name
	 * @param multipartFile
	 * @return
	 */
	@ApiOperation(value = "培训机构考评员资质图片", notes = "培训机构考评员资质图片", consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiImplicitParams({
			@ApiImplicitParam(name = "file_bus_id", value = "师资资质内码", required = true, paramType = "query"),
			@ApiImplicitParam(name = "file_name", value = "文件名称，没有后缀", required = true, paramType = "query"),
			@ApiImplicitParam(name = "userid", value = "经办人内码", required = true, paramType = "query")
	})
	@RequestMapping(value = "/uploadImage", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public AjaxReturnMsg uploadImage(String file_bus_id, String file_name,String userid,
									@RequestParam("uploadFile") MultipartFile multipartFile) {
		return invigilatorManageService.uploadImage(userid, file_bus_id, file_name, multipartFile);
	}
    
	/**
	 * 获取考评员教学信息
	 * @param hc73 考评员对象
	 * @return
	 */
	@ApiOperation(value = "教学信息", notes = "教学信息")
    @RequestMapping(value = "/getInvigilatorClasses", method = RequestMethod.POST)
	public AjaxReturnMsg getInvigilatorClasses(@ModelAttribute Hc73 hc73, BindingResult result ) throws Exception {
		if (result.hasErrors()) {
            return validate(result);
        }
		hc73.setAab001(hc73.getBaseinfoid());
		PageInfo<Hb68> pageinfo = invigilatorManageService.getInvigilatorClasses(hc73);
		return this.success(pageinfo);
	}
}
