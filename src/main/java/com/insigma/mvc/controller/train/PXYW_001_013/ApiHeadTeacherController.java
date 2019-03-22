package com.insigma.mvc.controller.train.PXYW_001_013;

import com.github.pagehelper.PageInfo;
import com.insigma.dto.AjaxReturnMsg;
import com.insigma.mvc.MvcHelper;
import com.insigma.mvc.model.CodeValue;
import com.insigma.mvc.model.train.Hb57;
import com.insigma.mvc.model.train.Hb68;
import com.insigma.mvc.service.train.PXYW_001_013.ApiHeadTeacherService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

@RestController
@Api(description = "班主任管理控制器")
@RequestMapping("/api/headTeacher")
public class ApiHeadTeacherController extends MvcHelper{
	
	@Resource
	private ApiHeadTeacherService apiHeadTeacherService;
	
	/**
	 * 获取班主任信息列表
	 * @param hb57 班主任对象
	 * @return
	 */
	@ApiOperation(value = "班主任列表", notes = "班主任列表")
    @RequestMapping(value = "/query", method = RequestMethod.POST)
	public AjaxReturnMsg getHeadTeacherList(@ModelAttribute Hb57 hb57, BindingResult result ) throws Exception {
		if (result.hasErrors()) {
            return validate(result);
        }
		PageInfo<Hb57> pageinfo = apiHeadTeacherService.getHeadTeacherList(hb57);
		return this.success(pageinfo);
	}

	/**
	 * 根据id获取班主任信息
	 * @param chb057 班主任内码
	 * @return
	 */
	@ApiOperation(value = "根据id获取班主任信息", notes = "根据id获取班主任信息", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiImplicitParams({
		@ApiImplicitParam(name = "chb057", value = "班主任内码", required = true, paramType = "path")
	})
    @RequestMapping(value = "/getInfo/{chb057}", method = RequestMethod.GET)
    public AjaxReturnMsg getTeacherInfo(@PathVariable String chb057) throws Exception {
        return apiHeadTeacherService.getHeadTeacherById(chb057);
    }
	
	/**
     * 检验身份证是否已在班主任库
     *
     * @param hb57 班主任对象
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "检验身份证是否已在班主任库", notes = "检验身份证是否已在班主任库", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/checkAac002", method = RequestMethod.POST)
    public AjaxReturnMsg checkAac002(@ModelAttribute Hb57 hb57) throws Exception {
    	return apiHeadTeacherService.checkAac002(hb57);
    }
	
	
	/**
     * 保存班主任基本信息
     *
     * @param hb57
     * @param result
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "保存班主任基本信息", notes = "保存班主任基本信息", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/saveHeadTeacher", method = RequestMethod.POST)
    public AjaxReturnMsg saveHeadTeacher(@ModelAttribute @Valid Hb57 hb57, BindingResult result) throws Exception {
    	//检验输入
        if (result.hasErrors()) {
            return validate(result);
        }
        return apiHeadTeacherService.saveHeadTeacher(hb57);
    }
	
    /**
     * 删除班主任信息
     *
     * @param hb57
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "删除班主任信息", notes = "删除班主任信息", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/deleteHeadTeacherById", method = RequestMethod.POST)
    public AjaxReturnMsg deleteHeadTeacherById(@ModelAttribute @Valid Hb57 hb57, BindingResult result) throws Exception {
    	//检验输入
        if (result.hasErrors()) {
            return validate(result);
        }
    	return apiHeadTeacherService.deleteHeadTeacherById(hb57);
    }
    
    /**
     * 批量删除班主任信息
     *
     * @param hb57
     * @param result
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "批量删除班主任信息", notes = "批量删除班主任信息", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/deleteHeadTeacherByIds", method = RequestMethod.POST)
    public AjaxReturnMsg deleteHeadTeacherByIds(@ModelAttribute @Valid Hb57 hb57, BindingResult result) throws Exception {
    	//检验输入
        if (result.hasErrors()) {
            return validate(result);
        }
    	return apiHeadTeacherService.deleteHeadTeacherByIds(hb57);
    }
    
    /**
	 * 班主任头像上传
	 *
	 * @param file_bus_id
	 * @param file_name
	 * @param multipartFile
	 * @return
	 */
	@ApiOperation(value = "班主任头像上传", notes = "班主任头像上传", consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiImplicitParams({
			@ApiImplicitParam(name = "file_bus_id", value = "班主任内码", required = true, paramType = "query"),
			@ApiImplicitParam(name = "file_name", value = "文件名称，没有后缀", required = true, paramType = "query"),
			@ApiImplicitParam(name = "userid", value = "经办人内码", required = true, paramType = "query")
	})
	@RequestMapping(value = "/uploadLogo", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public AjaxReturnMsg uploadFile(String file_bus_id, String file_name,String userid,
									@RequestParam("uploadFile") MultipartFile multipartFile) {
		return apiHeadTeacherService.uploadLogo(userid, file_bus_id, file_name, multipartFile);
	}
    
	/**
	 * 班主任负责班级列表
	 * @param hb57
	 * @return
	 */
	@ApiOperation(value = "班主任负责班级列表", notes = "班主任负责班级列表")
    @RequestMapping(value = "/getHeadTeacherClasses", method = RequestMethod.POST)
	public AjaxReturnMsg getHeadTeacherClasses(@ModelAttribute Hb57 hb57, BindingResult result ) throws Exception {
		if (result.hasErrors()) {
            return validate(result);
        }
		PageInfo<Hb68> pageinfo = apiHeadTeacherService.getHeadTeacherClasses(hb57);
		return this.success(pageinfo);
	}
	
}
