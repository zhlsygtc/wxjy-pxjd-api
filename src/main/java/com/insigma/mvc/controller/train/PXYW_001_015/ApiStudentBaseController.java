package com.insigma.mvc.controller.train.PXYW_001_015;


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
import com.insigma.mvc.model.train.Hb68;
import com.insigma.mvc.model.train.Student;
import com.insigma.mvc.service.train.PXYW_001_015.ApiStudentBaseService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(description = "学员基本信息控制器")
@RequestMapping("/api/studentBase")
public class ApiStudentBaseController extends MvcHelper{

	@Resource
	private ApiStudentBaseService apiStudentBaseService;
	
	
	/**
	 * 获取学员信息列表
	 * @param stu 学员对象
	 * @return
	 */
	@ApiOperation(value = "学员信息显示", notes = "学员信息显示")
    @RequestMapping(value = "/getStudentList", method = RequestMethod.POST)
	public AjaxReturnMsg getStudentList(@ModelAttribute Student stu, BindingResult result ) throws Exception {
		PageInfo<Student> pageinfo = apiStudentBaseService.getStudentList(stu);
		return this.success(pageinfo);
	}
	
	/**
	 * 根据id获取学员信息
	 * @param stu 人员对象
	 * @return
	 */
	@ApiOperation(value = "根据id获取学员信息", notes = "根据id获取学员信息", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/getInfo", method = RequestMethod.POST)
    public AjaxReturnMsg getPersonInfo(@ModelAttribute Student stu) throws Exception {
		return apiStudentBaseService.getStudentById(stu.getChc111());
    }
	
	/**
     * 保存学员信息
     *
     * @param temp 学员对象
     * @param result
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "保存学员信息", notes = "保存学员信息", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/updateStu", method = RequestMethod.POST)
    public AjaxReturnMsg updateStu(@ModelAttribute @Valid Student stu, BindingResult result) throws Exception {
        //检验输入
        if (result.hasErrors()) {
            return validate(result);
        }
        return apiStudentBaseService.updateStu(stu);
    }
	
    /**
	 * 学员头像上传
	 *
	 * @param file_bus_id
	 * @param file_name
	 * @param multipartFile
	 * @return
	 */
	@ApiOperation(value = "学员头像上传", notes = "学员头像上传", consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiImplicitParams({
			@ApiImplicitParam(name = "file_bus_id", value = "学员内码", required = true, paramType = "query"),
			@ApiImplicitParam(name = "file_name", value = "文件名称，没有后缀", required = true, paramType = "query"),
			@ApiImplicitParam(name = "userid", value = "经办人内码", required = true, paramType = "query")
	})
	@RequestMapping(value = "/uploadLogo", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public AjaxReturnMsg uploadFile(String file_bus_id, String file_name,String userid,
									@RequestParam("uploadFile") MultipartFile multipartFile) {
		return apiStudentBaseService.uploadLogo(userid, file_bus_id, file_name, multipartFile);
	}
    
	/**
	 * 根据id获取学员信息中文
	 * @param stu 学员对象
	 * @return
	 */
	@ApiOperation(value = "根据id获取学员信息中文", notes = "根据id获取学员信息中文", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/getInfoGBK", method = RequestMethod.POST)
    public AjaxReturnMsg getStudentGBKById(@ModelAttribute Student stu) throws Exception {
        return apiStudentBaseService.getStudentGBKById(stu.getChc111());
    }
	
    /**
	 * 获取学员培训信息列表
	 * @param stu 学员对象
	 * @return
	 */
	@ApiOperation(value = "培训信息", notes = "培训信息")
    @RequestMapping(value = "/getTrainClasses", method = RequestMethod.POST)
	public AjaxReturnMsg getTrainClasses(@ModelAttribute Student stu) throws Exception {
		stu.setAab001(stu.getBaseinfoid());
		PageInfo<Hb68> pageinfo = apiStudentBaseService.getTrainClasses(stu);
		return this.success(pageinfo);
	}
    
}
