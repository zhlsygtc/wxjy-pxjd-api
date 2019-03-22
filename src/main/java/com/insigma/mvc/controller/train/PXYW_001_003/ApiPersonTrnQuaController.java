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
@Api(description = "������ѵ�ʸ��϶�������")
@RequestMapping("/api/personQua")
public class ApiPersonTrnQuaController extends MvcHelper{

	@Resource
	private ApiPersonTrnQuaService apiPersonTrnQuaService;
	
	
	/**
	 * ��ȡѧԱ��Ϣ�б�
	 * @param stu ѧԱ����
	 * @return
	 */
	@ApiOperation(value = "ѧԱ��Ϣ��ʾ", notes = "ѧԱ��Ϣ��ʾ")
    @RequestMapping(value = "/getStudentList", method = RequestMethod.POST)
	public AjaxReturnMsg getStudentList(@ModelAttribute Student stu, BindingResult result ) throws Exception {
		PageInfo<Student> pageinfo = apiPersonTrnQuaService.getStudentList(stu);
		return this.success(pageinfo);
	}
	
	/**
     * ��ȡ������ѵ����
     *
     * @param codevalue �������
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "��ȡ������ѵ����", notes = "��ȡ������ѵ����", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/getAca111List", method = RequestMethod.POST)
    public AjaxReturnMsg<List<CodeValue>> getAca111List(@ModelAttribute CodeValue codevalue) throws Exception {
        return apiPersonTrnQuaService.getAca111List(codevalue);
    }
    
    
    /**
     * ��ȡ������ѵרҵ
     *
     * @param codevalue �������
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "��ȡ������ѵרҵ", notes = "��ȡ������ѵרҵ", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/getAca109List", method = RequestMethod.POST)
    public AjaxReturnMsg<List<CodeValue>> getAca109List(@ModelAttribute CodeValue codevalue) throws Exception {
        return apiPersonTrnQuaService.getAca109List(codevalue);
    }
	
	/**
	 * ����id��ȡѧԱ��Ϣ
	 * @param stu ��Ա����
	 * @return
	 */
	@ApiOperation(value = "����id��ȡѧԱ��Ϣ", notes = "����id��ȡѧԱ��Ϣ", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/getInfo", method = RequestMethod.POST)
    public AjaxReturnMsg getPersonInfo(@ModelAttribute Student stu) throws Exception {
		return apiPersonTrnQuaService.getStudentById(stu.getChc111());
    }
	
	/**
     * ����ѧԱ��Ϣ
     *
     * @param temp ѧԱ����
     * @param result
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "����ѧԱ��Ϣ", notes = "����ѧԱ��Ϣ", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/updateStu", method = RequestMethod.POST)
    public AjaxReturnMsg updateStu(@ModelAttribute @Valid Student stu, BindingResult result) throws Exception {
        //��������
        if (result.hasErrors()) {
            return validate(result);
        }
        return apiPersonTrnQuaService.updateStu(stu);
    }
	
    /**
	 * ѧԱͷ���ϴ�
	 *
	 * @param file_bus_id
	 * @param file_name
	 * @param multipartFile
	 * @return
	 */
	@ApiOperation(value = "ѧԱͷ���ϴ�", notes = "ѧԱͷ���ϴ�", consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiImplicitParams({
			@ApiImplicitParam(name = "file_bus_id", value = "ѧԱ����", required = true, paramType = "query"),
			@ApiImplicitParam(name = "file_name", value = "�ļ����ƣ�û�к�׺", required = true, paramType = "query"),
			@ApiImplicitParam(name = "userid", value = "����������", required = true, paramType = "query")
	})
	@RequestMapping(value = "/uploadLogo", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public AjaxReturnMsg uploadFile(String file_bus_id, String file_name,String userid,
									@RequestParam("uploadFile") MultipartFile multipartFile) {
		return apiPersonTrnQuaService.uploadLogo(userid, file_bus_id, file_name, multipartFile);
	}
    
    /**
     * �������֤��Ӧ��Ա�Ƿ��ڿ���
     *
     * @param stu ��Ա����
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "�������֤��Ӧ��Ա�Ƿ��ڿ���", notes = "�������֤��Ӧ��Ա�Ƿ��ڿ���", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/checkAac002", method = RequestMethod.POST)
    public AjaxReturnMsg checkAac002(@ModelAttribute Student stu) throws Exception {
    	return apiPersonTrnQuaService.checkAac002(stu);
    }
    
    /**
     * ����ѧԱ������Ϣ
     *
     * @param stu ѧԱ����
     * @param result
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "����ѧԱ������Ϣ", notes = "����ѧԱ������Ϣ", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/saveStu", method = RequestMethod.POST)
    public AjaxReturnMsg saveStu(@ModelAttribute @Valid Student stu, BindingResult result) throws Exception {
    	//��������
        if (result.hasErrors()) {
            return validate(result);
        }
        return apiPersonTrnQuaService.saveStu(stu);
    }
    
	/**
	 * ����id��ȡѧԱ��Ϣ����
	 * @param stu ѧԱ����
	 * @return
	 */
	@ApiOperation(value = "����id��ȡѧԱ��Ϣ����", notes = "����id��ȡѧԱ��Ϣ����", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/getInfoGBK", method = RequestMethod.POST)
    public AjaxReturnMsg getStudentGBKById(@ModelAttribute Student stu) throws Exception {
        return apiPersonTrnQuaService.getStudentGBKById(stu.getChc111());
    }
	
	/**
     * ��ѯͬ������Աͬ�������µĵ���
     *
     * @param temp ��Ա����
     * @param result
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "��ѯͬ������Աͬ�������µĵ���", notes = "��ѯͬ������Աͬ�������µĵ���", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/getExcel_batch_number", method = RequestMethod.POST)
    public AjaxReturnMsg getExcel_batch_number(@ModelAttribute Hc61_temp temp) throws Exception {
    	return apiPersonTrnQuaService.getExcel_batch_number(temp);
    }
	
	/**
	 * ��ȡ������Ա��Ϣ�б�
	 * @param temp ��Ա�������
	 * @return
	 */
	@ApiOperation(value = "��Ա��������ʾ", notes = "��Ա��������ʾ")
    @RequestMapping(value = "/getPersonList", method = RequestMethod.POST)
	public AjaxReturnMsg getTeacherList(@ModelAttribute Hc61_temp temp, BindingResult result ) throws Exception {
		if (result.hasErrors()) {
            return validate(result);
        }
		PageInfo<Hc61_temp> pageinfo = apiPersonTrnQuaService.getPersonList(temp);
		return this.success(pageinfo);
	}
	
	/**
     * ���浼����Ա��Ϣ
     *
     * @param temp ��Ա����
     * @param result
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "���浼����Ա��Ϣ", notes = "���浼����Ա��Ϣ", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/saveDate", method = RequestMethod.POST)
    public AjaxReturnMsg saveDate(@ModelAttribute @Valid Hc61_temp temp, BindingResult result) throws Exception {
        //��������
        if (result.hasErrors()) {
            return validate(result);
        }
        return apiPersonTrnQuaService.saveDate(temp);
    }
	
	/**
     * ɾ��ѧԱ��Ϣ
     *
     * @param stu ѧԱ����
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "ɾ��ѧԱ��Ϣ", notes = "ɾ��ѧԱ��Ϣ", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/deletebyid", method = RequestMethod.POST)
    public AjaxReturnMsg deleteQualification(@ModelAttribute Student stu) throws Exception {
    	return apiPersonTrnQuaService.deleteById(stu);
    }
    
    
    /**
     * ȷ��ѧԱ��Ϣ
     *
     * @param stu ѧԱ����
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "ȷ��ѧԱ��Ϣ", notes = "ȷ��ѧԱ��Ϣ", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/confirmbyid", method = RequestMethod.POST)
    public AjaxReturnMsg confirmbyid(@ModelAttribute Student stu) throws Exception {
    	return apiPersonTrnQuaService.confirmById(stu);
    }
	
    /**
	 * ��ȡѧԱ��ѵ��Ϣ�б�
	 * @param stu ѧԱ����
	 * @return
	 */
	@ApiOperation(value = "��ѵ��Ϣ", notes = "��ѵ��Ϣ")
    @RequestMapping(value = "/getTrainClasses", method = RequestMethod.POST)
	public AjaxReturnMsg getTrainClasses(@ModelAttribute Student stu) throws Exception {
		stu.setAab001(stu.getBaseinfoid());
		PageInfo<Hb68> pageinfo = apiPersonTrnQuaService.getTrainClasses(stu);
		return this.success(pageinfo);
	}
    
}
