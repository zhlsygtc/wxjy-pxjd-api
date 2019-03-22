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
@Api(description = "֤��¼�������")
@RequestMapping("/api/certificate")
public class ApiCertificateMgController extends MvcHelper{

	@Resource
	private ApiCertificateMgService apiCertificateMgService;
	
	/**
	 * ��ȡ�ϲ��༶��Ϣ�б�
	 * @param hb67 �ϲ��༶����
	 * @return
	 */
	@ApiOperation(value = "�ϲ��༶����", notes = "�ϲ��༶����")
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
	 * ���ݺϲ��༶�����ѯ�ϲ��༶��Ϣ 
	 */
    @RequestMapping(value = "/getHb67ById")
    public AjaxReturnMsg getHb67ById(Hb67 hb67) throws Exception{
		return this.success(apiCertificateMgService.getHb67ById(hb67.getChb067()));
    }
	
	/**
	 * ������ѧԱ֤���������
	 * @param stu ѧԱ����
	 * @return
	 */
	@ApiOperation(value = "������ѧԱ֤���������", notes = "������ѧԱ֤���������")
    @RequestMapping(value = "/getStuListForLook", method = RequestMethod.POST)
	public AjaxReturnMsg getStuListForLook(@ModelAttribute Student stu) throws Exception {
		PageInfo<Student> pageinfo = apiCertificateMgService.getStuListForLook(stu);
		return this.success(pageinfo);
	}
	
	/**
	 * ��ȡ�ϲ��༶�µ�ѧԱ��Ϣ�б�
	 * @param stu ѧԱ����
	 * @return
	 */
	@ApiOperation(value = "�ϲ��༶�µĺϸ���Ա����", notes = "�ϲ��༶�µĺϸ���Ա����")
    @RequestMapping(value = "/getStuList", method = RequestMethod.POST)
	public AjaxReturnMsg getStuList(@ModelAttribute Student stu, BindingResult result ) throws Exception {
		PageInfo<Student> pageinfo = apiCertificateMgService.getStuList(stu);
		return this.success(pageinfo);
	}
	
	/**
     * ����ѧԱΪ����֤
     *
     * @param stu ѧԱ����
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "�ύ����֤����Ϣ", notes = "�ύ����֤����Ϣ", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/doSet", method = RequestMethod.POST)
    public AjaxReturnMsg doSet(@ModelAttribute @Valid Student stu, BindingResult result) throws Exception {
    	//��������
        if (result.hasErrors()) {
            return validate(result);
        }
    	return apiCertificateMgService.doSet(stu);
    }
	
	/**
     * ����ѧԱ֤����Ϣ
     *
     * @param stu ѧԱ����
     * @param result
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "����ѧԱ֤����Ϣ", notes = "����ѧԱ֤����Ϣ", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/saveDate", method = RequestMethod.POST)
    public AjaxReturnMsg saveDate(@ModelAttribute @Valid Student stu, BindingResult result) throws Exception {
        return apiCertificateMgService.saveDate(stu);
    }
    
    /**
     * �ύ����֤����Ϣ
     *
     * @param hb67 ���ڶ���
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "�ύ����֤����Ϣ", notes = "�ύ����֤����Ϣ", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/submit", method = RequestMethod.POST)
    public AjaxReturnMsg submit(@ModelAttribute @Valid Hb67 hb67, BindingResult result) throws Exception {
    	//��������
        if (result.hasErrors()) {
            return validate(result);
        }
    	return apiCertificateMgService.submit(hb67);
    }
    
    /**
     * ��������֤����Ϣ
     *
     * @param hb67 ���ڶ���
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "��������֤����Ϣ", notes = "��������֤����Ϣ", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/undo", method = RequestMethod.POST)
    public AjaxReturnMsg undo(@ModelAttribute @Valid Hb67 hb67, BindingResult result) throws Exception {
    	//��������
        if (result.hasErrors()) {
            return validate(result);
        }
    	return apiCertificateMgService.undo(hb67);
    }
    
    /**
	 * ѧԱ֤��ͼƬ�ϴ�
	 *
	 * @param file_bus_id
	 * @param file_name
	 * @param multipartFile
	 * @return
	 */
	@ApiOperation(value = "ѧԱ֤��ͼƬ�ϴ�", notes = "ѧԱ֤��ͼƬ�ϴ�", consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiImplicitParams({
			@ApiImplicitParam(name = "file_bus_id", value = "ѧԱ֤������", required = true, paramType = "query"),
			@ApiImplicitParam(name = "file_name", value = "�ļ����ƣ�û�к�׺", required = true, paramType = "query"),
			@ApiImplicitParam(name = "userid", value = "����������", required = true, paramType = "query")
	})
	@RequestMapping(value = "/uploadImage/{chc001}", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public AjaxReturnMsg uploadFile(String file_bus_id, String file_name,String userid,
									@RequestParam("uploadFile") MultipartFile multipartFile,@PathVariable String chc001) {
		return apiCertificateMgService.uploadLogo(userid, file_bus_id, file_name, multipartFile,chc001);
	}
    
}
