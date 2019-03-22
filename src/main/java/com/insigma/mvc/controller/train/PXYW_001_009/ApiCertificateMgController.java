package com.insigma.mvc.controller.train.PXYW_001_009;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.github.pagehelper.PageInfo;
import com.insigma.dto.AjaxReturnMsg;
import com.insigma.mvc.MvcHelper;
import com.insigma.mvc.model.train.Hb67;
import com.insigma.mvc.model.train.Student;
import com.insigma.mvc.service.train.PXYW_001_009.ApiCertificateMgService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(description = "证书录入控制器")
@RequestMapping("/api/certificate")
public class ApiCertificateMgController extends MvcHelper{

	@Resource
	private ApiCertificateMgService apiCertificateMgService;
	
	/**
	 * 获取合并班级信息列表
	 * @param hb67 合并班级对象
	 * @return
	 */
	@ApiOperation(value = "合并班级搜索", notes = "合并班级搜索")
    @RequestMapping(value = "/getClasssList", method = RequestMethod.POST)
	public AjaxReturnMsg getClasssList(@ModelAttribute Hb67 hb67, BindingResult result ) throws Exception {
		if (result.hasErrors()) {
            return validate(result);
        }
		hb67.setAab001(hb67.getBaseinfoid());
		PageInfo<Hb67> pageinfo = apiCertificateMgService.getClasssList(hb67);
		return this.success(pageinfo);
	}
	
	/**
	 * 根据合并班级内码查询合并班级信息 
	 */
    @RequestMapping(value = "/getHb67ById")
    public AjaxReturnMsg getHb67ById(Hb67 hb67) throws Exception{
		return this.success(apiCertificateMgService.getHb67ById(hb67.getChb067()));
    }
	
	/**
	 * 班期下学员证书情况详情
	 * @param stu 学员对象
	 * @return
	 */
	@ApiOperation(value = "班期下学员证书情况详情", notes = "班期下学员证书情况详情")
    @RequestMapping(value = "/getStuListForLook", method = RequestMethod.POST)
	public AjaxReturnMsg getStuListForLook(@ModelAttribute Student stu) throws Exception {
		PageInfo<Student> pageinfo = apiCertificateMgService.getStuListForLook(stu);
		return this.success(pageinfo);
	}
	
	/**
	 * 获取合并班级下的学员信息列表
	 * @param stu 学员对象
	 * @return
	 */
	@ApiOperation(value = "合并班级下的合格人员搜索", notes = "合并班级下的合格人员搜索")
    @RequestMapping(value = "/getStuList", method = RequestMethod.POST)
	public AjaxReturnMsg getStuList(@ModelAttribute Student stu, BindingResult result ) throws Exception {
		PageInfo<Student> pageinfo = apiCertificateMgService.getStuList(stu);
		return this.success(pageinfo);
	}
	
	/**
     * 设置学员为不拿证
     *
     * @param stu 学员对象
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "提交班期证书信息", notes = "提交班期证书信息", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/doSet", method = RequestMethod.POST)
    public AjaxReturnMsg doSet(@ModelAttribute @Valid Student stu, BindingResult result) throws Exception {
    	//检验输入
        if (result.hasErrors()) {
            return validate(result);
        }
    	return apiCertificateMgService.doSet(stu);
    }
	
	/**
     * 保存学员证书信息
     *
     * @param stu 学员对象
     * @param result
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "保存学员证书信息", notes = "保存学员证书信息", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/saveDate", method = RequestMethod.POST)
    public AjaxReturnMsg saveDate(@ModelAttribute @Valid Student stu, BindingResult result) throws Exception {
        return apiCertificateMgService.saveDate(stu);
    }
    
    /**
     * 提交班期证书信息
     *
     * @param hb67 班期对象
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "提交班期证书信息", notes = "提交班期证书信息", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/submit", method = RequestMethod.POST)
    public AjaxReturnMsg submit(@ModelAttribute @Valid Hb67 hb67, BindingResult result) throws Exception {
    	//检验输入
        if (result.hasErrors()) {
            return validate(result);
        }
    	return apiCertificateMgService.submit(hb67);
    }
    
    /**
     * 撤销班期证书信息
     *
     * @param hb67 班期对象
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "撤销班期证书信息", notes = "撤销班期证书信息", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/undo", method = RequestMethod.POST)
    public AjaxReturnMsg undo(@ModelAttribute @Valid Hb67 hb67, BindingResult result) throws Exception {
    	//检验输入
        if (result.hasErrors()) {
            return validate(result);
        }
    	return apiCertificateMgService.undo(hb67);
    }
    
    /**
	 * 学员证书图片上传
	 *
	 * @param file_bus_id
	 * @param file_name
	 * @param multipartFile
	 * @return
	 */
	@ApiOperation(value = "学员证书图片上传", notes = "学员证书图片上传", consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiImplicitParams({
			@ApiImplicitParam(name = "file_bus_id", value = "学员证书内码", required = true, paramType = "query"),
			@ApiImplicitParam(name = "file_name", value = "文件名称，没有后缀", required = true, paramType = "query"),
			@ApiImplicitParam(name = "userid", value = "经办人内码", required = true, paramType = "query")
	})
	@RequestMapping(value = "/uploadImage/{chc001}", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public AjaxReturnMsg uploadFile(String file_bus_id, String file_name,String userid,
									@RequestParam("uploadFile") MultipartFile multipartFile,@PathVariable String chc001) {
		return apiCertificateMgService.uploadLogo(userid, file_bus_id, file_name, multipartFile,chc001);
	}
    
}
