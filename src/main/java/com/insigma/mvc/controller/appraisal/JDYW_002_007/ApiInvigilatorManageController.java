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
@Api(description = "����Ա������������")
@RequestMapping("/api/invigilatorManage")
public class ApiInvigilatorManageController extends MvcHelper{
	
	@Resource
	private ApiInvigilatorManageService invigilatorManageService;
	
	/**
	 * ��ȡ����Ա��Ϣ�б�
	 * @param hc73 ����Ա��������
	 * @return
	 */
	@ApiOperation(value = "����Ա����", notes = "����Ա����")
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
	 * ����id��ȡ����Ա��Ϣ
	 * @param hc73 ʦ�ʶ���
	 * @return
	 */
	@ApiOperation(value = "����id��ȡ����Ա��Ϣ", notes = "����id��ȡ����Ա��Ϣ", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/getInfo", method = RequestMethod.POST)
    public AjaxReturnMsg getInvigilatorInfo(@ModelAttribute Hc73 hc73) throws Exception {
        return invigilatorManageService.getInvigilatorById(hc73.getChc073(), hc73.getAab001());
    }
	
	/**
	 * ����id��ȡ����Ա��Ϣ����
	 * @param hc73 ʦ�ʶ���
	 * @return
	 */
	@ApiOperation(value = "����id��ȡ����Ա��Ϣ����", notes = "����id��ȡ����Ա��Ϣ����", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/getInfoGBK", method = RequestMethod.POST)
    public AjaxReturnMsg getInvigilatorInfoGBK(@ModelAttribute Hc73 hc73) throws Exception {
        return invigilatorManageService.getInvigilatorGBKById(hc73.getChc073(), hc73.getAab001());
    }

	/**
	 * �������֤��ȡ��ȡ����Ա��Ϣ
	 * @param hc73 ʦ�ʶ���
	 * @return
	 */
	@ApiOperation(value = "����id��ȡ����Ա��Ϣ", notes = "����id��ȡ����Ա��Ϣ", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/getIdcardInfo", method = RequestMethod.POST)
    public AjaxReturnMsg getIdcardInfo(@ModelAttribute Hc73 hc73) throws Exception {
        return invigilatorManageService.getIdcardInfo(hc73.getAac002());
    }

	/**
     * �������֤�Ƿ����ڿ���Ա��
     *
     * @param hc73 ʦ�ʶ���
     * @param result
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "�������֤�Ƿ����ڿ���Ա��", notes = "�������֤�Ƿ����ڿ���Ա��", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/checkAac002", method = RequestMethod.POST)
    public AjaxReturnMsg checkAac002(@ModelAttribute Hc73 hc73) throws Exception {
    	return invigilatorManageService.checkAac002(hc73);
    }
	
	
	/**
     * ���濼��Ա������Ϣ
     *
     * @param hc73 ʦ�ʶ���
     * @param result
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "���濼��Ա������Ϣ", notes = "���濼��Ա������Ϣ", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/savedata", method = RequestMethod.POST)
    public AjaxReturnMsg saveData(@ModelAttribute @Valid Hc73 hc73, BindingResult result) throws Exception {
    	//��������
        if (result.hasErrors()) {
            return validate(result);
        }
        return invigilatorManageService.saveData(hc73);
    }
	
    /**
     * ɾ������Ա��Ϣ
     *
     * @param hc73 ʦ�ʶ���
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "ɾ������Ա��Ϣ", notes = "ɾ������Ա��Ϣ", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/deleteInvigilator", method = RequestMethod.POST)
    public AjaxReturnMsg deleteInvigilator(@ModelAttribute @Valid Hc73 hc73, BindingResult result) throws Exception {
    	//��������
        if (result.hasErrors()) {
            return validate(result);
        }
    	return invigilatorManageService.deleteInvigilator(hc73);
    }
    
    /**
     * ����ɾ������Ա��Ϣ
     *
     * @param hc73 ʦ�ʶ���
     * @param result
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "����ɾ������Ա��Ϣ", notes = "����ɾ������Ա��Ϣ", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/batdelete", method = RequestMethod.POST)
    public AjaxReturnMsg batDelete(@ModelAttribute @Valid Hc73 hc73, BindingResult result) throws Exception {
    	//��������
        if (result.hasErrors()) {
            return validate(result);
        }
    	return invigilatorManageService.batDelete(hc73);
    }
    
    /**
     * ����˿���Ա�Ƿ����д�����
     *
     * @param hc74 ���ʶ���
     * @param result
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "����˿���Ա�Ƿ����д�����", notes = "����˿���Ա�Ƿ����д�����", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/checkQualification", method = RequestMethod.POST)
    public AjaxReturnMsg checkQualification(@ModelAttribute Hc74 hc74) throws Exception {
    	return invigilatorManageService.checkQualification(hc74);
    }
    
	/**
	 * ��ȡ����Ա������Ϣ�б�
	 * @param hc74 ���ʶ���
	 * @return
	 */
	@ApiOperation(value = "����Ա������Ϣ", notes = "����Ա������Ϣ")
    @RequestMapping(value = "/getQualificationList", method = RequestMethod.POST)
	public AjaxReturnMsg getQualificationList(@ModelAttribute Hc74 hc74 ) throws Exception {
		PageInfo<Hc74> pageinfo = invigilatorManageService.getQualificationList(hc74);
		return this.success(pageinfo);
		
	}
	
	/**
     * ���濼��Ա������Ϣ
     *
     * @param hc74 ���ʶ���
     * @param result
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "���濼��Ա������Ϣ", notes = "���濼��Ա������Ϣ", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/saveQualification", method = RequestMethod.POST)
    public AjaxReturnMsg saveQualification(@ModelAttribute @Valid Hc74 hc74, BindingResult result) throws Exception {
        //��������
        if (result.hasErrors()) {
            return validate(result);
        }
        return invigilatorManageService.saveQualification(hc74);
    }
	
    /**
	 * ����id��ȡ����Ա������Ϣ
	 * @param hc74 ���ʶ���
	 * @return
	 */
    @ApiOperation(value = "����id��ȡ����Ա������Ϣ", notes = "����id��ȡ����Ա������Ϣ", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/getQualification", method = RequestMethod.POST)
    public AjaxReturnMsg getQualification(@ModelAttribute Hc74 hc74) throws Exception {
        return invigilatorManageService.getQualification(hc74.getChc074());
    }
	
    /**
     * ɾ������Ա������Ϣ
     *
     * @param hc74 ���ʶ���
     * @param result
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "ɾ������Ա������Ϣ", notes = "ɾ������Ա������Ϣ", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/deleteQualification", method = RequestMethod.POST)
    public AjaxReturnMsg deleteQualification(@ModelAttribute @Valid Hc74 hc74, BindingResult result) throws Exception {
    	//��������
        if (result.hasErrors()) {
            return validate(result);
        }
        return invigilatorManageService.deleteQualification(hc74.getChc074());
    }
    
    /**
	 * ����Աͷ���ϴ�
	 *
	 * @param file_bus_id
	 * @param file_name
	 * @param multipartFile
	 * @return
	 */
	@ApiOperation(value = "����Աͷ���ϴ�", notes = "����Աͷ���ϴ�", consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiImplicitParams({
			@ApiImplicitParam(name = "file_bus_id", value = "ʦ������", required = true, paramType = "query"),
			@ApiImplicitParam(name = "file_name", value = "�ļ����ƣ�û�к�׺", required = true, paramType = "query"),
			@ApiImplicitParam(name = "userid", value = "����������", required = true, paramType = "query")
	})
	@RequestMapping(value = "/uploadLogo", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public AjaxReturnMsg uploadFile(String file_bus_id, String file_name,String userid,
									@RequestParam("uploadFile") MultipartFile multipartFile) {
		return invigilatorManageService.uploadLogo(userid, file_bus_id, file_name, multipartFile);
	}

	/**
	 * ��ѵ��������Ա����ͼƬ�ϴ�
	 *
	 * @param file_bus_id
	 * @param file_name
	 * @param multipartFile
	 * @return
	 */
	@ApiOperation(value = "��ѵ��������Ա����ͼƬ", notes = "��ѵ��������Ա����ͼƬ", consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiImplicitParams({
			@ApiImplicitParam(name = "file_bus_id", value = "ʦ����������", required = true, paramType = "query"),
			@ApiImplicitParam(name = "file_name", value = "�ļ����ƣ�û�к�׺", required = true, paramType = "query"),
			@ApiImplicitParam(name = "userid", value = "����������", required = true, paramType = "query")
	})
	@RequestMapping(value = "/uploadImage", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public AjaxReturnMsg uploadImage(String file_bus_id, String file_name,String userid,
									@RequestParam("uploadFile") MultipartFile multipartFile) {
		return invigilatorManageService.uploadImage(userid, file_bus_id, file_name, multipartFile);
	}
    
	/**
	 * ��ȡ����Ա��ѧ��Ϣ
	 * @param hc73 ����Ա����
	 * @return
	 */
	@ApiOperation(value = "��ѧ��Ϣ", notes = "��ѧ��Ϣ")
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
