package com.insigma.mvc.controller.train.PXYW_001_002;

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
import com.insigma.mvc.model.train.Hb61;
import com.insigma.mvc.model.train.Hb68;
import com.insigma.mvc.model.train.Hc74;
import com.insigma.mvc.service.train.PXYW_001_002.ApiTeacherService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(description = "师资管理控制器")
@RequestMapping("/api/teacher")
public class ApiTeacherController extends MvcHelper{
	
	@Resource
	private ApiTeacherService apiTeacherService;
	
	/**
	 * 获取教师信息列表
	 * @param hb61 教师搜索对象
	 * @return
	 */
	@ApiOperation(value = "教师搜索", notes = "教师搜索")
    @RequestMapping(value = "/getTeacherList", method = RequestMethod.POST)
	public AjaxReturnMsg getTeacherList(@ModelAttribute Hb61 hb61, BindingResult result ) throws Exception {
		if (result.hasErrors()) {
            return validate(result);
        }
		hb61.setAab001(hb61.getBaseinfoid());
		PageInfo<Hb61> pageinfo = apiTeacherService.getTeacherList(hb61);
		return this.success(pageinfo);
	}
	
	/**
	 * 根据id获取教师信息
	 * @param hb61 师资对象
	 * @return
	 */
	@ApiOperation(value = "根据id获取教师信息", notes = "根据id获取教师信息", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/getInfo", method = RequestMethod.POST)
    public AjaxReturnMsg getTeacherInfo(@ModelAttribute Hb61 hb61) throws Exception {
        return apiTeacherService.getTeacherById(hb61.getChb061());
    }
	
	/**
	 * 根据id获取教师信息中文
	 * @param hb61 师资对象
	 * @return
	 */
	@ApiOperation(value = "根据id获取教师信息中文", notes = "根据id获取教师信息中文", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/getInfoGBK", method = RequestMethod.POST)
    public AjaxReturnMsg getTeacherInfoGBK(@ModelAttribute Hb61 hb61) throws Exception {
        return apiTeacherService.getTeacherGBKById(hb61.getChb061());
    }
	
	/**
     * 检验身份证是否已在教师库
     *
     * @param hb61 师资对象
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "检验身份证是否已在教师库", notes = "检验身份证是否已在教师库", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/checkAac002", method = RequestMethod.POST)
    public AjaxReturnMsg checkAac002(@ModelAttribute Hb61 hb61) throws Exception {
    	return apiTeacherService.checkAac002(hb61);
    }
	
	
	/**
     * 保存教师基本信息
     *
     * @param hb61 师资对象
     * @param result
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "保存教师基本信息", notes = "保存教师基本信息", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/savedata", method = RequestMethod.POST)
    public AjaxReturnMsg saveData(@ModelAttribute @Valid Hb61 hb61, BindingResult result) throws Exception {
    	//检验输入
        if (result.hasErrors()) {
            return validate(result);
        }
        return apiTeacherService.saveData(hb61);
    }
	
    /**
     * 删除教师信息
     *
     * @param hb61 师资对象
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "删除教师信息", notes = "删除教师信息", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/deleteTeacher", method = RequestMethod.POST)
    public AjaxReturnMsg deleteTeacher(@ModelAttribute @Valid Hb61 hb61, BindingResult result) throws Exception {
    	//检验输入
        if (result.hasErrors()) {
            return validate(result);
        }
    	return apiTeacherService.deleteTeacher(hb61);
    }
    
    /**
     * 批量删除教师信息
     *
     * @param hb61 师资对象
     * @param result
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "批量删除教师信息", notes = "批量删除教师信息", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/batdelete", method = RequestMethod.POST)
    public AjaxReturnMsg batDelete(@ModelAttribute @Valid Hb61 hb61, BindingResult result) throws Exception {
    	//检验输入
        if (result.hasErrors()) {
            return validate(result);
        }
    	return apiTeacherService.batDelete(hb61);
    }
    
    /**
     * 检验此教师是否已有此资质
     *
     * @param hc74 资质对象
     * @param result
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "检验此教师是否已有此资质", notes = "检验此教师是否已有此资质", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/checkQualification", method = RequestMethod.POST)
    public AjaxReturnMsg checkQualification(@ModelAttribute Hc74 hc74) throws Exception {
    	return apiTeacherService.checkQualification(hc74);
    }
    
	/**
	 * 获取教师资质信息列表
	 * @param hc74 资质对象
	 * @return
	 */
	@ApiOperation(value = "教师资质信息", notes = "教师资质信息")
    @RequestMapping(value = "/getQualificationList", method = RequestMethod.POST)
	public AjaxReturnMsg getQualificationList(@ModelAttribute Hc74 hc74 ) throws Exception {
		PageInfo<Hc74> pageinfo = apiTeacherService.getQualificationList(hc74);
		return this.success(pageinfo);
		
	}
	
	/**
     * 保存教师资质信息
     *
     * @param hc74 资质对象
     * @param result
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "保存教师资质信息", notes = "保存教师资质信息", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/saveQualification", method = RequestMethod.POST)
    public AjaxReturnMsg saveQualification(@ModelAttribute @Valid Hc74 hc74, BindingResult result) throws Exception {
        //检验输入
        if (result.hasErrors()) {
            return validate(result);
        }
        return apiTeacherService.saveQualification(hc74);
    }
	
    /**
	 * 根据id获取教师资质信息
	 * @param hc74 资质对象
	 * @return
	 */
    @ApiOperation(value = "根据id获取教师资质信息", notes = "根据id获取教师资质信息", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/getQualification", method = RequestMethod.POST)
    public AjaxReturnMsg getQualification(@ModelAttribute Hc74 hc74) throws Exception {
        return apiTeacherService.getQualification(hc74.getChc074());
    }
	
    /**
     * 删除教师资质信息
     *
     * @param hc74 资质对象
     * @param result
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "删除教师资质信息", notes = "删除教师资质信息", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/deleteQualification", method = RequestMethod.POST)
    public AjaxReturnMsg deleteQualification(@ModelAttribute @Valid Hc74 hc74, BindingResult result) throws Exception {
    	//检验输入
        if (result.hasErrors()) {
            return validate(result);
        }
        return apiTeacherService.deleteQualification(hc74.getChc074());
    }
    
    /**
	 * 教师头像上传
	 *
	 * @param file_bus_id
	 * @param file_name
	 * @param multipartFile
	 * @return
	 */
	@ApiOperation(value = "教师头像上传", notes = "教师头像上传", consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiImplicitParams({
			@ApiImplicitParam(name = "file_bus_id", value = "师资内码", required = true, paramType = "query"),
			@ApiImplicitParam(name = "file_name", value = "文件名称，没有后缀", required = true, paramType = "query"),
			@ApiImplicitParam(name = "userid", value = "经办人内码", required = true, paramType = "query")
	})
	@RequestMapping(value = "/uploadLogo", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public AjaxReturnMsg uploadFile(String file_bus_id, String file_name,String userid,
									@RequestParam("uploadFile") MultipartFile multipartFile) {
		return apiTeacherService.uploadLogo(userid, file_bus_id, file_name, multipartFile);
	}

	/**
	 * 培训机构教师资质图片上传
	 *
	 * @param file_bus_id
	 * @param file_name
	 * @param multipartFile
	 * @return
	 */
	@ApiOperation(value = "培训机构教师资质图片", notes = "培训机构教师资质图片", consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiImplicitParams({
			@ApiImplicitParam(name = "file_bus_id", value = "师资资质内码", required = true, paramType = "query"),
			@ApiImplicitParam(name = "file_name", value = "文件名称，没有后缀", required = true, paramType = "query"),
			@ApiImplicitParam(name = "userid", value = "经办人内码", required = true, paramType = "query")
	})
	@RequestMapping(value = "/uploadImage", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public AjaxReturnMsg uploadImage(String file_bus_id, String file_name,String userid,
									@RequestParam("uploadFile") MultipartFile multipartFile) {
		return apiTeacherService.uploadImage(userid, file_bus_id, file_name, multipartFile);
	}
    
	/**
	 * 获取教师教学信息
	 * @param hb61 教师对象
	 * @return
	 */
	@ApiOperation(value = "教学信息", notes = "教学信息")
    @RequestMapping(value = "/getTeacherClasses", method = RequestMethod.POST)
	public AjaxReturnMsg getTeacherClasses(@ModelAttribute Hb61 hb61, BindingResult result ) throws Exception {
		if (result.hasErrors()) {
            return validate(result);
        }
		hb61.setAab001(hb61.getBaseinfoid());
		PageInfo<Hb68> pageinfo = apiTeacherService.getTeacherClasses(hb61);
		return this.success(pageinfo);
	}
	
}
