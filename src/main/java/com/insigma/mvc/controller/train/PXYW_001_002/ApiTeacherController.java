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
@Api(description = "ʦ�ʹ��������")
@RequestMapping("/api/teacher")
public class ApiTeacherController extends MvcHelper{
	
	@Resource
	private ApiTeacherService apiTeacherService;
	
	/**
	 * ��ȡ��ʦ��Ϣ�б�
	 * @param hb61 ��ʦ��������
	 * @return
	 */
	@ApiOperation(value = "��ʦ����", notes = "��ʦ����")
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
	 * ����id��ȡ��ʦ��Ϣ
	 * @param hb61 ʦ�ʶ���
	 * @return
	 */
	@ApiOperation(value = "����id��ȡ��ʦ��Ϣ", notes = "����id��ȡ��ʦ��Ϣ", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/getInfo", method = RequestMethod.POST)
    public AjaxReturnMsg getTeacherInfo(@ModelAttribute Hb61 hb61) throws Exception {
        return apiTeacherService.getTeacherById(hb61.getChb061());
    }
	
	/**
	 * ����id��ȡ��ʦ��Ϣ����
	 * @param hb61 ʦ�ʶ���
	 * @return
	 */
	@ApiOperation(value = "����id��ȡ��ʦ��Ϣ����", notes = "����id��ȡ��ʦ��Ϣ����", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/getInfoGBK", method = RequestMethod.POST)
    public AjaxReturnMsg getTeacherInfoGBK(@ModelAttribute Hb61 hb61) throws Exception {
        return apiTeacherService.getTeacherGBKById(hb61.getChb061());
    }
	
	/**
     * �������֤�Ƿ����ڽ�ʦ��
     *
     * @param hb61 ʦ�ʶ���
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "�������֤�Ƿ����ڽ�ʦ��", notes = "�������֤�Ƿ����ڽ�ʦ��", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/checkAac002", method = RequestMethod.POST)
    public AjaxReturnMsg checkAac002(@ModelAttribute Hb61 hb61) throws Exception {
    	return apiTeacherService.checkAac002(hb61);
    }
	
	
	/**
     * �����ʦ������Ϣ
     *
     * @param hb61 ʦ�ʶ���
     * @param result
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "�����ʦ������Ϣ", notes = "�����ʦ������Ϣ", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/savedata", method = RequestMethod.POST)
    public AjaxReturnMsg saveData(@ModelAttribute @Valid Hb61 hb61, BindingResult result) throws Exception {
    	//��������
        if (result.hasErrors()) {
            return validate(result);
        }
        return apiTeacherService.saveData(hb61);
    }
	
    /**
     * ɾ����ʦ��Ϣ
     *
     * @param hb61 ʦ�ʶ���
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "ɾ����ʦ��Ϣ", notes = "ɾ����ʦ��Ϣ", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/deleteTeacher", method = RequestMethod.POST)
    public AjaxReturnMsg deleteTeacher(@ModelAttribute @Valid Hb61 hb61, BindingResult result) throws Exception {
    	//��������
        if (result.hasErrors()) {
            return validate(result);
        }
    	return apiTeacherService.deleteTeacher(hb61);
    }
    
    /**
     * ����ɾ����ʦ��Ϣ
     *
     * @param hb61 ʦ�ʶ���
     * @param result
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "����ɾ����ʦ��Ϣ", notes = "����ɾ����ʦ��Ϣ", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/batdelete", method = RequestMethod.POST)
    public AjaxReturnMsg batDelete(@ModelAttribute @Valid Hb61 hb61, BindingResult result) throws Exception {
    	//��������
        if (result.hasErrors()) {
            return validate(result);
        }
    	return apiTeacherService.batDelete(hb61);
    }
    
    /**
     * ����˽�ʦ�Ƿ����д�����
     *
     * @param hc74 ���ʶ���
     * @param result
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "����˽�ʦ�Ƿ����д�����", notes = "����˽�ʦ�Ƿ����д�����", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/checkQualification", method = RequestMethod.POST)
    public AjaxReturnMsg checkQualification(@ModelAttribute Hc74 hc74) throws Exception {
    	return apiTeacherService.checkQualification(hc74);
    }
    
	/**
	 * ��ȡ��ʦ������Ϣ�б�
	 * @param hc74 ���ʶ���
	 * @return
	 */
	@ApiOperation(value = "��ʦ������Ϣ", notes = "��ʦ������Ϣ")
    @RequestMapping(value = "/getQualificationList", method = RequestMethod.POST)
	public AjaxReturnMsg getQualificationList(@ModelAttribute Hc74 hc74 ) throws Exception {
		PageInfo<Hc74> pageinfo = apiTeacherService.getQualificationList(hc74);
		return this.success(pageinfo);
		
	}
	
	/**
     * �����ʦ������Ϣ
     *
     * @param hc74 ���ʶ���
     * @param result
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "�����ʦ������Ϣ", notes = "�����ʦ������Ϣ", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/saveQualification", method = RequestMethod.POST)
    public AjaxReturnMsg saveQualification(@ModelAttribute @Valid Hc74 hc74, BindingResult result) throws Exception {
        //��������
        if (result.hasErrors()) {
            return validate(result);
        }
        return apiTeacherService.saveQualification(hc74);
    }
	
    /**
	 * ����id��ȡ��ʦ������Ϣ
	 * @param hc74 ���ʶ���
	 * @return
	 */
    @ApiOperation(value = "����id��ȡ��ʦ������Ϣ", notes = "����id��ȡ��ʦ������Ϣ", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/getQualification", method = RequestMethod.POST)
    public AjaxReturnMsg getQualification(@ModelAttribute Hc74 hc74) throws Exception {
        return apiTeacherService.getQualification(hc74.getChc074());
    }
	
    /**
     * ɾ����ʦ������Ϣ
     *
     * @param hc74 ���ʶ���
     * @param result
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "ɾ����ʦ������Ϣ", notes = "ɾ����ʦ������Ϣ", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/deleteQualification", method = RequestMethod.POST)
    public AjaxReturnMsg deleteQualification(@ModelAttribute @Valid Hc74 hc74, BindingResult result) throws Exception {
    	//��������
        if (result.hasErrors()) {
            return validate(result);
        }
        return apiTeacherService.deleteQualification(hc74.getChc074());
    }
    
    /**
	 * ��ʦͷ���ϴ�
	 *
	 * @param file_bus_id
	 * @param file_name
	 * @param multipartFile
	 * @return
	 */
	@ApiOperation(value = "��ʦͷ���ϴ�", notes = "��ʦͷ���ϴ�", consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiImplicitParams({
			@ApiImplicitParam(name = "file_bus_id", value = "ʦ������", required = true, paramType = "query"),
			@ApiImplicitParam(name = "file_name", value = "�ļ����ƣ�û�к�׺", required = true, paramType = "query"),
			@ApiImplicitParam(name = "userid", value = "����������", required = true, paramType = "query")
	})
	@RequestMapping(value = "/uploadLogo", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public AjaxReturnMsg uploadFile(String file_bus_id, String file_name,String userid,
									@RequestParam("uploadFile") MultipartFile multipartFile) {
		return apiTeacherService.uploadLogo(userid, file_bus_id, file_name, multipartFile);
	}

	/**
	 * ��ѵ������ʦ����ͼƬ�ϴ�
	 *
	 * @param file_bus_id
	 * @param file_name
	 * @param multipartFile
	 * @return
	 */
	@ApiOperation(value = "��ѵ������ʦ����ͼƬ", notes = "��ѵ������ʦ����ͼƬ", consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiImplicitParams({
			@ApiImplicitParam(name = "file_bus_id", value = "ʦ����������", required = true, paramType = "query"),
			@ApiImplicitParam(name = "file_name", value = "�ļ����ƣ�û�к�׺", required = true, paramType = "query"),
			@ApiImplicitParam(name = "userid", value = "����������", required = true, paramType = "query")
	})
	@RequestMapping(value = "/uploadImage", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public AjaxReturnMsg uploadImage(String file_bus_id, String file_name,String userid,
									@RequestParam("uploadFile") MultipartFile multipartFile) {
		return apiTeacherService.uploadImage(userid, file_bus_id, file_name, multipartFile);
	}
    
	/**
	 * ��ȡ��ʦ��ѧ��Ϣ
	 * @param hb61 ��ʦ����
	 * @return
	 */
	@ApiOperation(value = "��ѧ��Ϣ", notes = "��ѧ��Ϣ")
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
