package com.insigma.mvc.controller.appraisal.JDYW_002_002;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
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

import com.insigma.dto.AjaxReturnMsg;
import com.insigma.mvc.MvcHelper;
import com.insigma.mvc.model.train.Ad01File;
import com.insigma.mvc.service.appraisal.JDYW_002_002.ApiAppraisalCompanyUploadInfoService;
import com.insigma.mvc.service.common.fileupload.ApiFileUploadService;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
/**
 * ����������ɹ���
 * 2018-12-17
 */
@RestController
@RequestMapping("/api/appraisalCompanyUpload")
public class ApiappraisalCompanyUploadInfoController extends MvcHelper {

	@Resource
	private ApiAppraisalCompanyUploadInfoService apiAppraisalCompanyUploadInfoService;
	
	@Resource
	private ApiFileUploadService apiFileUploadService;
	
	/**
	 * ��ѯ���������Ϣ�б�
	 */
	@RequestMapping(value = "/getAd01FileById")
    public AjaxReturnMsg getAd01FileById(Ad01File ad01File) throws Exception{
		return this.success(apiAppraisalCompanyUploadInfoService.getInfoList(ad01File));
    }
	
	/**
	 * ��ѯ���������Ϣ�б�
	 */
	@RequestMapping(value = "/getPhotoNum")
    public AjaxReturnMsg getPhotoNum(Ad01File ad01File) throws Exception {
		return this.success(apiAppraisalCompanyUploadInfoService.getFileNum(ad01File));	
	}
	
	
	/**
	 * �ϴ�������Ƭ
	 */
	@ApiOperation(value = "�������ͼƬ�ϴ�", notes = "�������ͼƬ�ϴ�", consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiImplicitParams({
			@ApiImplicitParam(name = "file_bus_id", value = "��ѵ��������", required = true, paramType = "query"),
			@ApiImplicitParam(name = "file_name", value = "�ļ����ƣ�û�к�׺", required = true, paramType = "query")
	})
	@RequestMapping(value = "/uploadFile", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public AjaxReturnMsg uploadFile(String file_bus_id, String file_name,String userid,String desc,
									@RequestParam("uploadFile") MultipartFile multipartFile) {
		return apiAppraisalCompanyUploadInfoService.uploadImage(userid, file_bus_id, file_name,desc, multipartFile);
	}
	
	/**
	 * �ϴ�������Ƶ
	 */
	@ApiOperation(value = "�ϴ���Ƶ", notes = "�ϴ���Ƶ")
    @RequestMapping(value = "/uploadVideo",
            method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public AjaxReturnMsg uploadVideo(HttpServletRequest request,
                                     @RequestParam("uploadFile") MultipartFile multipartFile) throws Exception {
        return apiAppraisalCompanyUploadInfoService.uploadVideo(request, multipartFile);
    }
	
	/**
	 * ���ݻ���file_uuidɾ������������Ϣ
	 */
	@ApiOperation(value = "����ͼƬ/��Ƶɾ��", notes = "����ͼƬ/��Ƶɾ��", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public AjaxReturnMsg saveData(@ModelAttribute @Valid Ad01File ad01file, BindingResult result) throws Exception {
    	//��������
        if (result.hasErrors()) {
            return validate(result);
        }
        return apiAppraisalCompanyUploadInfoService.delete(ad01file);
    }
	
	/**
	 * ��ȡ��Ƶ�����ļ���Ϣ
	 */
	@RequestMapping(value = "/getFileInfo/{bus_uuid}", method = RequestMethod.GET)
    public AjaxReturnMsg getUserListByGroupid(@PathVariable String bus_uuid) throws Exception {
        return apiFileUploadService.getFileInfo(bus_uuid);
    }
}
