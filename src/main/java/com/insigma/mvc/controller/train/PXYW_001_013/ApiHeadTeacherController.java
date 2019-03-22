package com.insigma.mvc.controller.train.PXYW_001_013;

import com.github.pagehelper.PageInfo;
import com.insigma.dto.AjaxReturnMsg;
import com.insigma.mvc.MvcHelper;
import com.insigma.mvc.model.CodeValue;
import com.insigma.mvc.model.train.Hb57;
import com.insigma.mvc.model.train.Hb68;
import com.insigma.mvc.service.train.PXYW_001_013.ApiHeadTeacherService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

@RestController
@Api(description = "�����ι��������")
@RequestMapping("/api/headTeacher")
public class ApiHeadTeacherController extends MvcHelper{
	
	@Resource
	private ApiHeadTeacherService apiHeadTeacherService;
	
	/**
	 * ��ȡ��������Ϣ�б�
	 * @param hb57 �����ζ���
	 * @return
	 */
	@ApiOperation(value = "�������б�", notes = "�������б�")
    @RequestMapping(value = "/query", method = RequestMethod.POST)
	public AjaxReturnMsg getHeadTeacherList(@ModelAttribute Hb57 hb57, BindingResult result ) throws Exception {
		if (result.hasErrors()) {
            return validate(result);
        }
		PageInfo<Hb57> pageinfo = apiHeadTeacherService.getHeadTeacherList(hb57);
		return this.success(pageinfo);
	}

	/**
	 * ����id��ȡ��������Ϣ
	 * @param chb057 ����������
	 * @return
	 */
	@ApiOperation(value = "����id��ȡ��������Ϣ", notes = "����id��ȡ��������Ϣ", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiImplicitParams({
		@ApiImplicitParam(name = "chb057", value = "����������", required = true, paramType = "path")
	})
    @RequestMapping(value = "/getInfo/{chb057}", method = RequestMethod.GET)
    public AjaxReturnMsg getTeacherInfo(@PathVariable String chb057) throws Exception {
        return apiHeadTeacherService.getHeadTeacherById(chb057);
    }
	
	/**
     * �������֤�Ƿ����ڰ����ο�
     *
     * @param hb57 �����ζ���
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "�������֤�Ƿ����ڰ����ο�", notes = "�������֤�Ƿ����ڰ����ο�", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/checkAac002", method = RequestMethod.POST)
    public AjaxReturnMsg checkAac002(@ModelAttribute Hb57 hb57) throws Exception {
    	return apiHeadTeacherService.checkAac002(hb57);
    }
	
	
	/**
     * ��������λ�����Ϣ
     *
     * @param hb57
     * @param result
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "��������λ�����Ϣ", notes = "��������λ�����Ϣ", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/saveHeadTeacher", method = RequestMethod.POST)
    public AjaxReturnMsg saveHeadTeacher(@ModelAttribute @Valid Hb57 hb57, BindingResult result) throws Exception {
    	//��������
        if (result.hasErrors()) {
            return validate(result);
        }
        return apiHeadTeacherService.saveHeadTeacher(hb57);
    }
	
    /**
     * ɾ����������Ϣ
     *
     * @param hb57
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "ɾ����������Ϣ", notes = "ɾ����������Ϣ", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/deleteHeadTeacherById", method = RequestMethod.POST)
    public AjaxReturnMsg deleteHeadTeacherById(@ModelAttribute @Valid Hb57 hb57, BindingResult result) throws Exception {
    	//��������
        if (result.hasErrors()) {
            return validate(result);
        }
    	return apiHeadTeacherService.deleteHeadTeacherById(hb57);
    }
    
    /**
     * ����ɾ����������Ϣ
     *
     * @param hb57
     * @param result
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "����ɾ����������Ϣ", notes = "����ɾ����������Ϣ", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/deleteHeadTeacherByIds", method = RequestMethod.POST)
    public AjaxReturnMsg deleteHeadTeacherByIds(@ModelAttribute @Valid Hb57 hb57, BindingResult result) throws Exception {
    	//��������
        if (result.hasErrors()) {
            return validate(result);
        }
    	return apiHeadTeacherService.deleteHeadTeacherByIds(hb57);
    }
    
    /**
	 * ������ͷ���ϴ�
	 *
	 * @param file_bus_id
	 * @param file_name
	 * @param multipartFile
	 * @return
	 */
	@ApiOperation(value = "������ͷ���ϴ�", notes = "������ͷ���ϴ�", consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiImplicitParams({
			@ApiImplicitParam(name = "file_bus_id", value = "����������", required = true, paramType = "query"),
			@ApiImplicitParam(name = "file_name", value = "�ļ����ƣ�û�к�׺", required = true, paramType = "query"),
			@ApiImplicitParam(name = "userid", value = "����������", required = true, paramType = "query")
	})
	@RequestMapping(value = "/uploadLogo", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public AjaxReturnMsg uploadFile(String file_bus_id, String file_name,String userid,
									@RequestParam("uploadFile") MultipartFile multipartFile) {
		return apiHeadTeacherService.uploadLogo(userid, file_bus_id, file_name, multipartFile);
	}
    
	/**
	 * �����θ���༶�б�
	 * @param hb57
	 * @return
	 */
	@ApiOperation(value = "�����θ���༶�б�", notes = "�����θ���༶�б�")
    @RequestMapping(value = "/getHeadTeacherClasses", method = RequestMethod.POST)
	public AjaxReturnMsg getHeadTeacherClasses(@ModelAttribute Hb57 hb57, BindingResult result ) throws Exception {
		if (result.hasErrors()) {
            return validate(result);
        }
		PageInfo<Hb68> pageinfo = apiHeadTeacherService.getHeadTeacherClasses(hb57);
		return this.success(pageinfo);
	}
	
}
