package com.insigma.mvc.controller.train.PXYW_001_010;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import com.insigma.mvc.MvcHelper;
import com.insigma.mvc.model.CodeValue;
import com.insigma.mvc.model.train.Hb65;
import com.insigma.mvc.model.train.Hc61;
import com.insigma.mvc.model.train.Hc61_temp;
import com.insigma.mvc.model.train.SmtGroup;
import com.insigma.mvc.model.train.Student;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.github.pagehelper.PageInfo;
import com.insigma.dto.AjaxReturnMsg;
import com.insigma.mvc.service.train.PXYW_001_010.ApiClassPlanService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
/**
 * ����ƻ�contoller
 * @author zhanghl
 * 2018-04-25
 */
@RestController
@Api(description = "����ƻ�������")
@RequestMapping("/api/classPlan")
public class ApiClassPlanController extends MvcHelper{
	@Resource
	private ApiClassPlanService apiClassPlanService;
	/**
	 * ��ʼ������ƻ�
	 */
	@ApiOperation(value = "��ȡ����ƻ���Ϣ", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/getPlanList",method = RequestMethod.POST)
	public AjaxReturnMsg getPlanList(Hb65 hb65) throws Exception {
		PageInfo<Hb65> pageInfo = apiClassPlanService.getPlanList(hb65);
		return this.success(pageInfo);
		
	}
    /**
	 * ������ѵ����ƻ���Ϣ
	 */
	@ApiOperation(value = "������ѵ����ƻ���Ϣ", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/addPlan",method = RequestMethod.POST)
	public AjaxReturnMsg addPlan(Hb65 hb65) throws Exception {
		return apiClassPlanService.addPlan(hb65);
	}
   /**
	 * �޸���ѵ�ƻ���Ϣ
	 */
	@ApiOperation(value = "�޸���ѵ�ƻ���Ϣ", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/getPlanById",method = RequestMethod.POST)
	public AjaxReturnMsg getPlanById(Hb65 hb65) throws Exception{
		hb65 = apiClassPlanService.getPlanById(hb65);
		return this.success(hb65);
	}
	/**
	 * ��ѯsmt_group����
	 */
	@ApiOperation(value = "��ѯsmt_group����", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/getSmtGroupById",method = RequestMethod.POST)
	public AjaxReturnMsg getSmtGroupById(SmtGroup smtGroup) throws Exception{
		smtGroup = apiClassPlanService.getSmtGroupById(smtGroup.getGroupid());
		return this.success(smtGroup);
	}
	/**
     * ��ȡ������ѵ����
     * @param codevalue ���ʱ��
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "��ȡ������ѵ����", notes = "���ʱ��", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/getAca111List", method = RequestMethod.POST)
    public AjaxReturnMsg<List<CodeValue>> getAca111List(@ModelAttribute CodeValue codevalue) throws Exception {
        return apiClassPlanService.getAca111List(codevalue);
    }
    /**
     * ��ȡ������ѵ���ʵȼ�
     * @param codevalue ���ʱ��
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "��ȡ������ѵ���ʵȼ�", notes = "���ʱ��", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/getAca11aList", method = RequestMethod.POST)
    public AjaxReturnMsg<List<CodeValue>> getAca11aList(@ModelAttribute CodeValue codevalue) throws Exception {
        return apiClassPlanService.getAca11aList(codevalue.getId(), codevalue.getCode_type());
    }
    /**
     * ��ȡ�ϼ�����
     * @param stu ѧԱ����
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "��ȡ�ϼ�����", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/getAab301List", method = RequestMethod.GET)
    public AjaxReturnMsg<List<CodeValue>> getAab301List() throws Exception {
        return apiClassPlanService.getAab301List();
    }
    /**
	 * ��ѯ�ù�˾��������������
	 */
	@ApiOperation(value = "��ѯ�ù�˾��������������", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/getCompanyAab301",method = RequestMethod.POST)
	public AjaxReturnMsg getCompanyAab301(SmtGroup smtGroup) throws Exception{
		smtGroup = apiClassPlanService.getCompanyAab301(smtGroup.getGroupid());
		return this.success(smtGroup);
	}
    /**
   	 * ����רҵ���Ʋ�ѯ��ѵ�������Ƽ�רҵ���
   	 */
	@ApiOperation(value = "����רҵ���Ʋ�ѯ��ѵ�������Ƽ�רҵ���", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/getAca112",method = RequestMethod.POST)
   	public AjaxReturnMsg getAca112(Hb65 hb65) throws Exception{
		hb65 = apiClassPlanService.getAca112(hb65.getAca111());
   		return this.success(hb65);
   	}
	/**
	 * ����idɾ����ѵ�ƻ�(��Ϊ��Ч)
	 * @param request
	 * @param model
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "����idɾ����ѵ�ƻ�", produces = MediaType.APPLICATION_JSON_VALUE)
	@RequestMapping(value="/delPlan",method = RequestMethod.POST)
	public AjaxReturnMsg<String> delPlan(Hb65 hb65)throws Exception {
		return apiClassPlanService.delPlan(hb65);
	}
	/**
	 * �л���ѵ�ƻ�����״̬
	 * @param request
	 * @param model
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "�л���ѵ�ƻ�����״̬", produces = MediaType.APPLICATION_JSON_VALUE)
	@RequestMapping(value="/changePlan",method = RequestMethod.POST)
	public AjaxReturnMsg<String> changePlan(Hb65 hb65)throws Exception {
		return apiClassPlanService.changePlan(hb65);
	}
	/**
	 * �鿴��ѵ�ƻ���Ϣ
	 * @param chb055
	 * @return
	 * @throws Exception
	 */
	@ApiOperation(value = "�鿴��ѵ�ƻ���Ϣ", produces = MediaType.APPLICATION_JSON_VALUE)
	@RequestMapping(value="/lookPlan",method = RequestMethod.POST)
	public AjaxReturnMsg<String> lookPlan(Hb65 hb65)throws Exception {
		hb65 = apiClassPlanService.getPlanById(hb65);
		return this.success(hb65);
	}
	/**
	 * �鿴�ѱ���ѧԱ��Ϣ
	 * @param chb055
	 * @return
	 * @throws Exception
	 */
	@ApiOperation(value = "�鿴�ѱ���ѧԱ��Ϣ", produces = MediaType.APPLICATION_JSON_VALUE)
	@RequestMapping(value="/signedStuForLook",method = RequestMethod.POST)
	public AjaxReturnMsg<String> signedStuForLook(Student stu)throws Exception {
		PageInfo<Student> pageInfo = apiClassPlanService.signedStuForLook(stu);
		return this.success(pageInfo);
	}
    /**
	 * ����
	 */
	@ApiOperation(value = "����ѧԱ��Ϣ", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/signUp",method = RequestMethod.POST)
	public AjaxReturnMsg signUp(Student stu) throws Exception {
		return apiClassPlanService.signUp(stu);
	}
	/**
	 * �������֤�Ż�ȡѧԱ������Ϣ
	 */
	@ApiOperation(value = "�������֤�Ż�ȡѧԱ������Ϣ", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/findStuById",method = RequestMethod.POST)
	public AjaxReturnMsg findStuById(Student stu) throws Exception{
		Hc61 hc61 = apiClassPlanService.findStuById(stu);
		return this.success(hc61);
	}
	/**
	 * �������֤�Ż�ȡѧԱ������Ϣ
	 */
	@ApiOperation(value = "�������֤�Ż�ȡѧԱ������Ϣ", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/findFileById",method = RequestMethod.POST)
	public AjaxReturnMsg findFileById(Student stu) throws Exception{
		Hc61 hc61 = apiClassPlanService.findFileById(stu);
		return this.success(hc61);
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
	@RequestMapping(value = "/uploadHc61Photo", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public AjaxReturnMsg uploadHc61Photo(String file_bus_id, String file_name,String userid,
									@RequestParam("uploadFile") MultipartFile multipartFile) {
		return apiClassPlanService.uploadHc61Photo(userid, file_bus_id, file_name, multipartFile);
	}
	/**
     *�ϴ�ѧԱ����ļ�
     * @param request
     * @param multipartFile
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "�ϴ���������ļ�", notes = "�ϴ�ѧԱ����ļ�", consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "file_bus_id", value = "������Ϣid", required = true, paramType = "query"),
            @ApiImplicitParam(name = "file_name", value = "�ļ����ƣ�û�к�׺", required = true, paramType = "query")
    })
    @RequestMapping(value = "/fileUpload", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public AjaxReturnMsg uploadFile(HttpServletRequest request,
                                    @RequestParam("uploadFile") MultipartFile multipartFile) throws Exception {
        return apiClassPlanService.fileUpload(request, multipartFile);
    }
    /**
	 * ����idɾ��ͼƬ
	 * @param request
	 * @param model
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "����idɾ��ͼƬ", produces = MediaType.APPLICATION_JSON_VALUE)
	@RequestMapping(value="/delPicById",method = RequestMethod.POST)
	public AjaxReturnMsg<String> delPicById(Student stu)throws Exception {
		return apiClassPlanService.delPicById(stu);
	}
	/**
	 * �������뱨������
	 */
	@ApiOperation(value = "�������뱨������", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/signUpSave",method = RequestMethod.POST)
	public AjaxReturnMsg signUpSave(Hc61_temp hc61Temp) throws Exception {
		return apiClassPlanService.signUpSave(hc61Temp);
	}
	/**
	 * ��ȡ����word������Ϣ
	 * @param stu ѧԱ����
	 * @return
	 */
	@ApiOperation(value = "��ȡ����word������Ϣ", notes = "��ȡ����word������Ϣ", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/exportDJK", method = RequestMethod.POST)
    public AjaxReturnMsg exportDJK(@ModelAttribute Student stu) throws Exception {
        return apiClassPlanService.exportDJK(stu);
    }
}