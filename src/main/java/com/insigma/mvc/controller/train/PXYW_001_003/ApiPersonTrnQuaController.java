package com.insigma.mvc.controller.train.PXYW_001_003;

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
import com.insigma.mvc.model.train.Hb68;
import com.insigma.mvc.model.train.Hc61_temp;
import com.insigma.mvc.model.train.Student;
import com.insigma.mvc.service.train.PXYW_001_003.ApiPersonTrnQuaService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(description = "个人培训资格认定控制器")
@RequestMapping("/api/personQua")
public class ApiPersonTrnQuaController extends MvcHelper{

	@Resource
	private ApiPersonTrnQuaService apiPersonTrnQuaService;
	
	
	/**
	 * 获取学员信息列表
	 * @param stu 学员对象
	 * @return
	 */
	@ApiOperation(value = "学员信息显示", notes = "学员信息显示")
    @RequestMapping(value = "/getStudentList", method = RequestMethod.POST)
	public AjaxReturnMsg getStudentList(@ModelAttribute Student stu, BindingResult result ) throws Exception {
		PageInfo<Student> pageinfo = apiPersonTrnQuaService.getStudentList(stu);
		return this.success(pageinfo);
	}
	
	/**
     * 获取机构培训资质
     *
     * @param codevalue 代码对象
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "获取机构培训资质", notes = "获取机构培训资质", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/getAca111List", method = RequestMethod.POST)
    public AjaxReturnMsg<List<CodeValue>> getAca111List(@ModelAttribute CodeValue codevalue) throws Exception {
        return apiPersonTrnQuaService.getAca111List(codevalue);
    }
    
    
    /**
     * 获取机构培训专业
     *
     * @param codevalue 代码对象
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "获取机构培训专业", notes = "获取机构培训专业", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/getAca109List", method = RequestMethod.POST)
    public AjaxReturnMsg<List<CodeValue>> getAca109List(@ModelAttribute CodeValue codevalue) throws Exception {
        return apiPersonTrnQuaService.getAca109List(codevalue);
    }
	
	/**
	 * 根据id获取学员信息
	 * @param stu 人员对象
	 * @return
	 */
	@ApiOperation(value = "根据id获取学员信息", notes = "根据id获取学员信息", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/getInfo", method = RequestMethod.POST)
    public AjaxReturnMsg getPersonInfo(@ModelAttribute Student stu) throws Exception {
		return apiPersonTrnQuaService.getStudentById(stu.getChc111());
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
        return apiPersonTrnQuaService.updateStu(stu);
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
		return apiPersonTrnQuaService.uploadLogo(userid, file_bus_id, file_name, multipartFile);
	}
    
    /**
     * 检验身份证对应人员是否在库中
     *
     * @param stu 人员对象
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "检验身份证对应人员是否在库中", notes = "检验身份证对应人员是否在库中", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/checkAac002", method = RequestMethod.POST)
    public AjaxReturnMsg checkAac002(@ModelAttribute Student stu) throws Exception {
    	return apiPersonTrnQuaService.checkAac002(stu);
    }
    
    /**
     * 保存学员基本信息
     *
     * @param stu 学员对象
     * @param result
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "保存学员基本信息", notes = "保存学员基本信息", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/saveStu", method = RequestMethod.POST)
    public AjaxReturnMsg saveStu(@ModelAttribute @Valid Student stu, BindingResult result) throws Exception {
    	//检验输入
        if (result.hasErrors()) {
            return validate(result);
        }
        return apiPersonTrnQuaService.saveStu(stu);
    }
    
	/**
	 * 根据id获取学员信息中文
	 * @param stu 学员对象
	 * @return
	 */
	@ApiOperation(value = "根据id获取学员信息中文", notes = "根据id获取学员信息中文", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/getInfoGBK", method = RequestMethod.POST)
    public AjaxReturnMsg getStudentGBKById(@ModelAttribute Student stu) throws Exception {
        return apiPersonTrnQuaService.getStudentGBKById(stu.getChc111());
    }
	
	/**
     * 查询同操作人员同类型最新的导入
     *
     * @param temp 人员对象
     * @param result
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "查询同操作人员同类型最新的导入", notes = "查询同操作人员同类型最新的导入", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/getExcel_batch_number", method = RequestMethod.POST)
    public AjaxReturnMsg getExcel_batch_number(@ModelAttribute Hc61_temp temp) throws Exception {
    	return apiPersonTrnQuaService.getExcel_batch_number(temp);
    }
	
	/**
	 * 获取导入人员信息列表
	 * @param temp 人员导入对象
	 * @return
	 */
	@ApiOperation(value = "人员导入结果显示", notes = "人员导入结果显示")
    @RequestMapping(value = "/getPersonList", method = RequestMethod.POST)
	public AjaxReturnMsg getTeacherList(@ModelAttribute Hc61_temp temp, BindingResult result ) throws Exception {
		if (result.hasErrors()) {
            return validate(result);
        }
		PageInfo<Hc61_temp> pageinfo = apiPersonTrnQuaService.getPersonList(temp);
		return this.success(pageinfo);
	}
	
	/**
     * 保存导入人员信息
     *
     * @param temp 人员对象
     * @param result
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "保存导入人员信息", notes = "保存导入人员信息", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/saveDate", method = RequestMethod.POST)
    public AjaxReturnMsg saveDate(@ModelAttribute @Valid Hc61_temp temp, BindingResult result) throws Exception {
        //检验输入
        if (result.hasErrors()) {
            return validate(result);
        }
        return apiPersonTrnQuaService.saveDate(temp);
    }
	
	/**
     * 删除学员信息
     *
     * @param stu 学员对象
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "删除学员信息", notes = "删除学员信息", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/deletebyid", method = RequestMethod.POST)
    public AjaxReturnMsg deleteQualification(@ModelAttribute Student stu) throws Exception {
    	return apiPersonTrnQuaService.deleteById(stu);
    }
    
    
    /**
     * 确认学员信息
     *
     * @param stu 学员对象
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "确认学员信息", notes = "确认学员信息", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/confirmbyid", method = RequestMethod.POST)
    public AjaxReturnMsg confirmbyid(@ModelAttribute Student stu) throws Exception {
    	return apiPersonTrnQuaService.confirmById(stu);
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
		PageInfo<Hb68> pageinfo = apiPersonTrnQuaService.getTrainClasses(stu);
		return this.success(pageinfo);
	}
    
}
