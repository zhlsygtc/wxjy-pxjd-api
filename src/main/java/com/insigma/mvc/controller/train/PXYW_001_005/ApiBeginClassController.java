package com.insigma.mvc.controller.train.PXYW_001_005;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import com.insigma.mvc.MvcHelper;
import com.insigma.mvc.model.CodeValue;
import com.insigma.mvc.model.train.Ad01;
import com.insigma.mvc.model.train.Hb68;
import com.insigma.mvc.model.train.Hb69;
import com.insigma.mvc.model.train.Hb69Temp;
import com.insigma.mvc.model.train.SmtGroup;
import com.insigma.mvc.model.train.Student;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.insigma.dto.AjaxReturnMsg;
import com.insigma.mvc.service.train.PXYW_001_005.ApiBeginClassService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
/**
 * �����걨contoller
 * @author zhanghl
 */
@RestController
@Api(description = "�����걨������")
@RequestMapping("/api/beginClass")
public class ApiBeginClassController extends MvcHelper{
	@Resource
	private ApiBeginClassService beginClassService;
	/**
	 * ��ʼ�����������Ϣ�б�
	 */
	@ApiOperation(value = "��ȡ�༶������Ϣ", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/getInfoList",method = RequestMethod.POST)
	public AjaxReturnMsg getClassList(Hb68 hb68) throws Exception {
		PageInfo<Hb68> pageInfo = beginClassService.getInfoList(hb68);
		return this.success(pageInfo);
		
	}
    /**
	 * ��ѯѧԱ��Ϣ
	 */
	@ApiOperation(value = "��ѯѧԱ��Ϣ", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/getStuList",method = RequestMethod.POST)
	public AjaxReturnMsg getStuList(Student stu) throws Exception {
		PageHelper.offsetPage(stu.getOffset(), stu.getLimit());
    	PageInfo<Student> pageInfo = new PageInfo<Student>(beginClassService.getStuList(stu));
		return this.success(pageInfo);
		
	}
//	/**
//	 * ��ѯ�γ̱���Ϣ
//	 */
//	@ApiOperation(value = "��ѯ�γ̱���Ϣ", produces = MediaType.APPLICATION_JSON_VALUE)
//    @RequestMapping(value = "/getCourseList",method = RequestMethod.POST)
//	public AjaxReturnMsg getCourseList(Hb69 hb69) throws Exception {
//		PageHelper.offsetPage(hb69.getOffset(), hb69.getLimit());
//    	PageInfo<Hb69> pageInfo = new PageInfo<Hb69>(beginClassService.getCourseListForLook(hb69));
//		return this.success(pageInfo);
//		
//	}
    /**
	 * ��ʼ��ҳ��_��ȡ��ѡѧԱ
	 */
	@ApiOperation(value = "��ȡ��ѡѧԱ", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/getCheck",method = RequestMethod.POST)
	public AjaxReturnMsg getCheck(Student stu) throws Exception {
    	PageHelper.offsetPage(0,1000);//ѧԱ��ʾ��һҳ������ѡ��
    	PageInfo<Student> pageInfo = new PageInfo<Student>(beginClassService.getCheck(stu));
		return this.success(pageInfo);
	}
	/**
	 * ��ѯsmt_group����
	 */
	@ApiOperation(value = "��ѯsmt_group����", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/getSmtGroupById",method = RequestMethod.POST)
	public AjaxReturnMsg getSmtGroupById(SmtGroup smtGroup) throws Exception{
		smtGroup = beginClassService.getSmtGroupById(smtGroup.getGroupid());
		return this.success(smtGroup);
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
        return beginClassService.getAab301List();
    }
    /**
	 * ��ѯ�ù�˾��������������
	 */
	@ApiOperation(value = "��ѯ�ù�˾��������������", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/getCompanyAab301",method = RequestMethod.POST)
	public AjaxReturnMsg getCompanyAab301(SmtGroup smtGroup) throws Exception{
		smtGroup = beginClassService.getCompanyAab301(smtGroup.getGroupid());
		return this.success(smtGroup);
	}
    /**
	 * �޸Ŀ�����Ϣ
	 */
	@ApiOperation(value = "�޸Ŀ�����Ϣ", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/getById",method = RequestMethod.POST)
	public AjaxReturnMsg getById(Hb68 hb68,Ad01 ad01) throws Exception{
		hb68 = beginClassService.getById(hb68.getChb100());
		return this.success(hb68);
	}
	/**
     * ��ȡ������ѵ����
     *
     * @param stu ѧԱ����
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "����ѧԱ��Ϣ", notes = "����ѧԱ��Ϣ", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/getAca111List", method = RequestMethod.POST)
    public AjaxReturnMsg<List<CodeValue>> getAca111List(@ModelAttribute CodeValue codevalue) throws Exception {
        return beginClassService.getAca111List(codevalue);
    }
    /**
	 * ���濪�������Ϣ
	 */
	@ApiOperation(value = "���濪�������Ϣ", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/saveBaseInfo",method = RequestMethod.POST)
	public AjaxReturnMsg saveBaseInfo(Hb68 hb68) throws Exception {
		return beginClassService.saveBaseInfo(hb68);
	}
    /**
	 * ����ѧԱ��Ϣ
	 */
	@ApiOperation(value = "����ѧԱ��Ϣ", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/saveStu",method = RequestMethod.POST)
	public AjaxReturnMsg saveStu(Student stu) throws Exception {
    	String[] chc111s = stu.getSelectnodes().split(",");
    	stu.setChc111s(chc111s);
		return beginClassService.saveStu(stu);
	}
	/**
	 * ����idɾ��������Ϣ(��Ϊ��Ч)
	 * @param request
	 * @param model
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "����idɾ��������Ϣ(��Ϊ��Ч)", produces = MediaType.APPLICATION_JSON_VALUE)
	@RequestMapping(value="/delClass",method = RequestMethod.POST)
	public AjaxReturnMsg<String> delClass(Hb68 hb68)throws Exception {
		return beginClassService.delClass(hb68);
	}
	/**
	 * �ύ������Ϣ
	 * @param hb68
	 * @return
	 * @throws Exception
	 */
	@ApiOperation(value = "�ύ������Ϣ", produces = MediaType.APPLICATION_JSON_VALUE)
	@RequestMapping(value="/submitClass",method = RequestMethod.POST)
	public AjaxReturnMsg<String> submitClass(Hb68 hb68)throws Exception {
		return beginClassService.submitClass(hb68);
	}
	/**
	 * ����������Ϣ
	 * @param hb68
	 * @return
	 * @throws Exception
	 */
	@ApiOperation(value = "����������Ϣ", produces = MediaType.APPLICATION_JSON_VALUE)
	@RequestMapping(value="/revokeClass",method = RequestMethod.POST)
	public AjaxReturnMsg<String> revokeClass(Hb68 hb68)throws Exception {
		return beginClassService.revokeClass(hb68);
	}
	/**
	 * �鿴�༶��Ϣ
	 * @param hb68
	 * @return
	 * @throws Exception
	 */
	@ApiOperation(value = "�鿴������Ϣ", produces = MediaType.APPLICATION_JSON_VALUE)
	@RequestMapping(value="/doLook",method = RequestMethod.POST)
	public AjaxReturnMsg<String> doLook(Hb68 hb68)throws Exception {
		hb68 = beginClassService.getById(hb68.getChb100());
		return this.success(hb68);
	}
	/**
	 * �鿴ѧԱ��Ϣ
	 * @param chb068
	 * @return
	 * @throws Exception
	 */
	@ApiOperation(value = "�鿴ѧԱ��Ϣ", produces = MediaType.APPLICATION_JSON_VALUE)
	@RequestMapping(value="/getStuListForLook",method = RequestMethod.POST)
	public AjaxReturnMsg<String> getStuListForLook(Student stu)throws Exception {
		PageInfo<Student> pageInfo = beginClassService.getStuListForLook(stu);
		return this.success(pageInfo);
	}
	/**
	 * �鿴�γ̱���Ϣ
	 * @param chb068
	 * @return
	 * @throws Exception
	 */
	@ApiOperation(value = "�鿴�γ̱���Ϣ", produces = MediaType.APPLICATION_JSON_VALUE)
	@RequestMapping(value="/getCourseListForLook",method = RequestMethod.POST)
	public AjaxReturnMsg<String> getCourseListForLook(Hb69 hb69)throws Exception {
		//PageHelper.offsetPage(0,1000);//�γ̱���Ϣ��ʾ��һҳ������鿴
		PageInfo<Hb69> pageInfo = new PageInfo<Hb69>(beginClassService.getCourseListForLook(hb69));
		return this.success(pageInfo);
	}
	/**
	 * ��ȡ����γ̱��б�
	 * @param temp �γ̱������
	 * @return
	 */
	@ApiOperation(value = "�γ̱����б���ʾ", notes = "�γ̱�������ʾ")
    @RequestMapping(value = "/getTempCourseList", method = RequestMethod.POST)
	public AjaxReturnMsg getTempCourseList(@ModelAttribute Hb69Temp temp, BindingResult result ) throws Exception {
		if (result.hasErrors()) {
            return validate(result);
        }
		PageInfo<Hb69Temp> pageinfo = beginClassService.getTempCourseList(temp);
		return this.success(pageinfo);
	}
	/**
	 * ¼��γ̱�������У��ӱ���γ̱���Ϣ
	 */
	@ApiOperation(value = "¼��ӱ���γ̱���Ϣ", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/varifyCourse",method = RequestMethod.POST)
	public AjaxReturnMsg varifyCourse(Hb69Temp hb69Temp) throws Exception {
		return beginClassService.varifyCourse(hb69Temp);
	}
    /**
	 * ����γ̱�����������γ̱���Ϣ
	 */
	@ApiOperation(value = "����γ̱���Ϣ", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/saveCourseData",method = RequestMethod.POST)
	public AjaxReturnMsg saveCourseData(Hb69Temp hb69Temp) throws Exception {
		String[] chb069s = hb69Temp.getSelectnodes().split(",");
		hb69Temp.setChb069s(chb069s);
		return beginClassService.saveCourseData(hb69Temp);
	}
	/**
	 * ¼��γ̱�����������γ̱���Ϣ
	 */
	@ApiOperation(value = "¼�뱣��γ̱���Ϣ", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/saveCourse",method = RequestMethod.POST)
	public AjaxReturnMsg saveCourse(Hb69Temp hb69Temp) throws Exception {
		return beginClassService.saveCourse(hb69Temp);
	}
	/**
	 * �޸Ŀγ̱�
	 */
	@ApiOperation(value = "�޸Ŀγ̱�", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/saveCourseForChange",method = RequestMethod.POST)
	public AjaxReturnMsg saveCourseForChange(Hb69Temp hb69Temp) throws Exception {
		return beginClassService.saveCourseForChange(hb69Temp);
	}
	/**
     * ��ѯͬ������Աͬ�������µĵ���
     * @param temp ��Ա����
     * @param result
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "��ѯͬ������Աͬ�������µĵ���", notes = "��ѯͬ������Աͬ�������µĵ���", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/getExcel_batch_number", method = RequestMethod.POST)
    public AjaxReturnMsg getExcel_batch_number(@ModelAttribute Hb69Temp hb69Temp) throws Exception {
    	return beginClassService.getExcel_batch_number(hb69Temp);
    }
	/**
	 * ����idɾ����ѵѧԱ
	 * @param request
	 * @param model
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "����idɾ����ѵѧԱ", produces = MediaType.APPLICATION_JSON_VALUE)
	@RequestMapping(value="/delStudent/{chc001}",method = RequestMethod.DELETE)
	public AjaxReturnMsg<String> delStudent(HttpServletRequest request,@PathVariable String chc001){
		return beginClassService.delStudent(chc001);
	}
    /**
   	 * ����רҵ���Ʋ�ѯ��ѵ�������Ƽ�רҵ���
   	 */
	@ApiOperation(value = "����רҵ���Ʋ�ѯ��ѵ�������Ƽ�רҵ���", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/getAca112",method = RequestMethod.POST)
   	public AjaxReturnMsg getAca112(Hb68 hb68) throws Exception{
   		hb68 = beginClassService.getAca112(hb68.getAca111());
   		return this.success(hb68);
   	}
	/**
   	 * ����רҵ���Ʋ�ѯ������׼
   	 */
	@ApiOperation(value = "����רҵ���Ʋ�ѯ������׼", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/getAca131",method = RequestMethod.POST)
   	public AjaxReturnMsg getAca131(Hb68 hb68) throws Exception{
   		hb68 = beginClassService.getAca131(hb68.getAca109());
   		return this.success(hb68);
   	}
	/**
	 * �鿴�޸Ŀγ̱�ҳ��ĵ�ǰ�γ���Ϣ
	 * @param chb068
	 * @return
	 * @throws Exception
	 */
	@ApiOperation(value = "�鿴�޸Ŀγ̱�ҳ��ĵ�ǰ�γ���Ϣ", produces = MediaType.APPLICATION_JSON_VALUE)
	@RequestMapping(value="/getCourseListForChange",method = RequestMethod.POST)
	public AjaxReturnMsg<String> getCourseListForChange(Hb69 hb69)throws Exception {
		PageInfo<Hb69> pageInfo = new PageInfo<Hb69>(beginClassService.getCourseListForChange(hb69));
		return this.success(pageInfo);
	}
}