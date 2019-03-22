package com.insigma.mvc.controller.monitor.PXJG_001_004;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import com.insigma.mvc.MvcHelper;
import com.insigma.mvc.model.SFileRecord;
import com.insigma.mvc.model.train.StuFileDTO;
import com.insigma.mvc.service.monitor.PXJG_001_004.ClassFileUploadService;

import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.insigma.dto.AjaxReturnMsg;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
/**
 * 监管附件
 * @author jewel
 */
@RestController
@Api(description = "监管附件控制器")
@RequestMapping("/api/classFileUpload")
public class ApiClassFileUploadController extends MvcHelper{
	@Resource
	private ClassFileUploadService classFileUploadService;
	
	
	
	  /**
     * 通过人员id获取文件列表
     *
     * @param sFileRecord
     * @return
     */
    @RequestMapping(value = "/stuFiles", method = RequestMethod.POST)
    @ApiOperation(value = " 通过人员id获取学员文件列表", produces = MediaType.APPLICATION_JSON_VALUE)
    public AjaxReturnMsg getUserListByGroupid(@ModelAttribute StuFileDTO sFileRecord) throws Exception {
        return classFileUploadService.getFileList(sFileRecord);
    }
    
    /**
     * 通过人员id获取文件列表
     *
     * @param sFileRecord
     * @return
     */
    @RequestMapping(value = "/stuFiles2", method = RequestMethod.POST)
    @ApiOperation(value = " 通过id获取课程文件列表", produces = MediaType.APPLICATION_JSON_VALUE)
    public AjaxReturnMsg stuFiles2(@ModelAttribute StuFileDTO sFileRecord) throws Exception {
        return classFileUploadService.getFileList2(sFileRecord);
    }
	
	/**
     * 上传申请相关文件
     * @param request
     * @param multipartFile
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "上传申请相关文件", notes = "上传申请相关文件", consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "file_bus_id", value = "基本信息id", required = true, paramType = "query"),
            @ApiImplicitParam(name = "file_name", value = "文件名称，没有后缀", required = true, paramType = "query")
    })
    @RequestMapping(value = "/uploadFile", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public AjaxReturnMsg uploadFile(HttpServletRequest request,
                                    @RequestParam("uploadFile") MultipartFile multipartFile) throws Exception {
        return classFileUploadService.uploadFile(request, multipartFile);
    }

    
    /**
	 * 删除文件信息
	 */
	@RequestMapping(value = "/deleteFile/{bus_uuid}", method = RequestMethod.GET)
    public AjaxReturnMsg getUserListByGroupid(@PathVariable String bus_uuid) throws Exception {
        return classFileUploadService.deleteFile(bus_uuid);
    }
}