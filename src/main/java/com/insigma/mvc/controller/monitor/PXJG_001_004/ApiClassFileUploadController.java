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
 * ��ܸ���
 * @author jewel
 */
@RestController
@Api(description = "��ܸ���������")
@RequestMapping("/api/classFileUpload")
public class ApiClassFileUploadController extends MvcHelper{
	@Resource
	private ClassFileUploadService classFileUploadService;
	
	
	
	  /**
     * ͨ����Աid��ȡ�ļ��б�
     *
     * @param sFileRecord
     * @return
     */
    @RequestMapping(value = "/stuFiles", method = RequestMethod.POST)
    @ApiOperation(value = " ͨ����Աid��ȡѧԱ�ļ��б�", produces = MediaType.APPLICATION_JSON_VALUE)
    public AjaxReturnMsg getUserListByGroupid(@ModelAttribute StuFileDTO sFileRecord) throws Exception {
        return classFileUploadService.getFileList(sFileRecord);
    }
    
    /**
     * ͨ����Աid��ȡ�ļ��б�
     *
     * @param sFileRecord
     * @return
     */
    @RequestMapping(value = "/stuFiles2", method = RequestMethod.POST)
    @ApiOperation(value = " ͨ��id��ȡ�γ��ļ��б�", produces = MediaType.APPLICATION_JSON_VALUE)
    public AjaxReturnMsg stuFiles2(@ModelAttribute StuFileDTO sFileRecord) throws Exception {
        return classFileUploadService.getFileList2(sFileRecord);
    }
	
	/**
     * �ϴ���������ļ�
     * @param request
     * @param multipartFile
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "�ϴ���������ļ�", notes = "�ϴ���������ļ�", consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "file_bus_id", value = "������Ϣid", required = true, paramType = "query"),
            @ApiImplicitParam(name = "file_name", value = "�ļ����ƣ�û�к�׺", required = true, paramType = "query")
    })
    @RequestMapping(value = "/uploadFile", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public AjaxReturnMsg uploadFile(HttpServletRequest request,
                                    @RequestParam("uploadFile") MultipartFile multipartFile) throws Exception {
        return classFileUploadService.uploadFile(request, multipartFile);
    }

    
    /**
	 * ɾ���ļ���Ϣ
	 */
	@RequestMapping(value = "/deleteFile/{bus_uuid}", method = RequestMethod.GET)
    public AjaxReturnMsg getUserListByGroupid(@PathVariable String bus_uuid) throws Exception {
        return classFileUploadService.deleteFile(bus_uuid);
    }
}