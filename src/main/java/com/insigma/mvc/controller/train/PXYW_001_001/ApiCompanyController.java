package com.insigma.mvc.controller.train.PXYW_001_001;

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
import com.insigma.mvc.model.train.Ad01;
import com.insigma.mvc.model.train.Hb62;
import com.insigma.mvc.model.train.Hb63;
import com.insigma.mvc.model.train.Hb64;
import com.insigma.mvc.service.train.PXYW_001_001.ApiCompanyService;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 * ������Ϣά��
 * @author link
 * 2018-02-26
 */
@RestController
@RequestMapping("/api/company")
public class ApiCompanyController extends MvcHelper{
	@Resource
	private ApiCompanyService apiCompanyService;
	
	@RequestMapping(value = "/getAd01ById")
    public AjaxReturnMsg getHb68ById(Ad01 ad01) throws Exception{
		return this.success(apiCompanyService.getAD01ById(ad01.getGroupid()));
    }
	/**
	 * ��ʼ����ѵ������Ϣ
	 */
	@ApiOperation(value = "��ʼ����ѵ������Ϣ", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/getGroundInfo",method = RequestMethod.POST)
	public AjaxReturnMsg getGroundInfo(Hb63 hb63) throws Exception {
		PageInfo<Hb63> pageInfo = apiCompanyService.getGroundInfo(hb63);
		return this.success(pageInfo);
		
	}
	/**
	 * ��ѯcode_value�е�chb065�ֶ�
	 */
	@ApiOperation(value = "��ѯcode_value�е�chb065�ֶ�", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/getCodeValueChb065",method = RequestMethod.POST)
	public AjaxReturnMsg getCodeValueChb065(Hb64 hb64) throws Exception{
		List<Hb64> hb64List = apiCompanyService.getCodeValueChb065();
		return this.success(hb64List);
	}
	/**
	 * ��ѯcode_value�е�chb163�ֶ�
	 */
	@ApiOperation(value = "��ѯcode_value�е�chb163�ֶ�", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/getCodeValueChb163",method = RequestMethod.POST)
	public AjaxReturnMsg getCodeValueChb163(Hb64 hb64) throws Exception{
		List<Hb64> hb64List = apiCompanyService.getCodeValueChb163();
		return this.success(hb64List);
	}
	/**
	 * ��ѯcode_value�е�chb070�ֶ�
	 */
	@ApiOperation(value = "��ѯcode_value�е�chb070�ֶ�", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/getCodeValueChb070",method = RequestMethod.POST)
	public AjaxReturnMsg getCodeValueChb070(Hb64 hb64) throws Exception{
		List<Hb64> hb64List = apiCompanyService.getCodeValueChb070();
		return this.success(hb64List);
	}
	/**
	 * ��ʼ����ѵ������Ϣ
	 */
	@ApiOperation(value = "��ʼ����ѵ������Ϣ", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/getClassroomInfo",method = RequestMethod.POST)
	public AjaxReturnMsg getClassroomInfo(Hb63 hb63) throws Exception {
		PageInfo<Hb63> pageInfo = apiCompanyService.getClassroomInfo(hb63);
		return this.success(pageInfo);
		
	}
	
	/**
	 * ��ʼ��������Ϣ
	 */
	@ApiOperation(value = "��ʼ��������Ϣ", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/getQualityInfo",method = RequestMethod.POST)
	public AjaxReturnMsg getQualityInfo(Hb62 hb62) throws Exception {
		PageInfo<Hb62> pageInfo = apiCompanyService.getQualityInfo(hb62);
		return this.success(pageInfo);
		
	}
	 /**
	 * ������ѵ����������Ϣ
	 */
	@ApiOperation(value = "������ѵ����������Ϣ", produces = MediaType.APPLICATION_JSON_VALUE)
   @RequestMapping(value = "/saveGroundInfo",method = RequestMethod.POST)
	public AjaxReturnMsg saveGroundInfo(Hb63 hb63) throws Exception {
		return apiCompanyService.saveGroundInfo(hb63);
	}
	 /**
	 * ������ѵ����������Ϣ
	 */
	@ApiOperation(value = "������ѵ����������Ϣ", produces = MediaType.APPLICATION_JSON_VALUE)
   @RequestMapping(value = "/saveClassroomInfo",method = RequestMethod.POST)
	public AjaxReturnMsg saveClassroomInfo(Hb63 hb63) throws Exception {
		return apiCompanyService.saveClassroomInfo(hb63);
	}
	 
	/**
	 * ������ѵ����������Ϣ
	 */
	@ApiOperation(value = "������ѵ����������Ϣ", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/saveQualityInfo",method = RequestMethod.POST)
	public AjaxReturnMsg saveQualityInfo(Hb62 hb62) throws Exception {
		return apiCompanyService.saveQualityInfo(hb62);
	}
	
	
	/**
	 * ������Ƭ�ϴ�
	 *
	 * @param file_bus_id
	 * @param file_name
	 * @param multipartFile
	 * @return
	 */
	@ApiOperation(value = "��ѵ����LOGO�ϴ�", notes = "��ѵ����LOGO�ϴ�", consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiImplicitParams({
			@ApiImplicitParam(name = "file_bus_id", value = "��ѵ��������", required = true, paramType = "query"),
			@ApiImplicitParam(name = "file_name", value = "�ļ����ƣ�û�к�׺", required = true, paramType = "query")
	})
	@RequestMapping(value = "/uploadLogo", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public AjaxReturnMsg uploadFile(String file_bus_id, String file_name,String userid,
									@RequestParam("uploadFile") MultipartFile multipartFile) {
		return apiCompanyService.uploadLogo(userid, file_bus_id, file_name, multipartFile);
	}
	
	/**
	 * ������Ϣ�޸�
	 */
	@ApiOperation(value = "������Ϣά��", notes = "������Ϣά��", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/savedata", method = RequestMethod.POST)
    public AjaxReturnMsg saveData(@ModelAttribute @Valid Ad01 ad01, BindingResult result) throws Exception {
    	//��������
        if (result.hasErrors()) {
            return validate(result);
        }
        return apiCompanyService.saveData(ad01);
    }
	
	
	 /**
   	 * ����רҵ���Ʋ�ѯ��ѵ�������Ƽ�רҵ���
   	 */
	@ApiOperation(value = "����רҵ���Ʋ�ѯ��ѵ�������Ƽ�רҵ���", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/getAca112",method = RequestMethod.POST)
   	public AjaxReturnMsg getAca112(CodeValue codevalue) throws Exception{
   		codevalue = apiCompanyService.getAca112(codevalue.getCode_name());
   		return this.success(codevalue);
   	}
}
