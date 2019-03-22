package com.insigma.mvc.controller.train.PXYW_001_011;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

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
import com.insigma.mvc.MvcHelper;
import com.insigma.mvc.model.CodeValue;
import com.insigma.mvc.model.train.Hb65;
import com.insigma.mvc.model.train.Hb68;
import com.insigma.mvc.model.train.Hb69;
import com.insigma.mvc.model.train.Hb69Temp;
import com.insigma.mvc.model.train.Student;
import com.insigma.mvc.service.train.PXYW_001_011.ApiClassMgmtService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * �༶����contoller
 * @author yugw
 */
@RestController
@Api(description = "�༶���������")
@RequestMapping("/api/class")
public class ApiClassMgmtController extends MvcHelper{

	@Resource
	private ApiClassMgmtService apiClassMgmtService;
	
	/**
	 * ��ʼ���༶��Ϣ�б� 
	 */
	@ApiOperation(value = "��ȡ�༶������Ϣ", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/getInfoList",method = RequestMethod.POST)
	public AjaxReturnMsg getClassList(Hb68 hb68) throws Exception {
		PageInfo<Hb68> pageInfo = apiClassMgmtService.getInfoList(hb68);
		return this.success(pageInfo);
		
	}
	
	/**
     * ��ȡ������ѵ�ƻ�
     *
     * @param codevalue �������
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "��ȡ������ѵ�ƻ�", notes = "��ȡ������ѵ�ƻ�", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/getPlans", method = RequestMethod.POST)
    public AjaxReturnMsg<List<CodeValue>> getPlans(@ModelAttribute CodeValue codevalue) throws Exception {
        return apiClassMgmtService.getPlans(codevalue);
    }
	
	/**
	 * ����id��ȡ��ѵ�ƻ���Ϣ
	 * @param planid �ƻ�����
	 * @return
	 */
	@ApiOperation(value = "����id��ȡ��ѵ�ƻ���Ϣ", notes = "����id��ȡ��ѵ�ƻ���Ϣ", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/getPlan", method = RequestMethod.POST)
    public AjaxReturnMsg getTeacherInfo(Hb65 hb65) throws Exception {
        return apiClassMgmtService.getPlanById(hb65.getChb055());
    }
	
	/**
	 * ���濪�������Ϣ
	 */
	@ApiOperation(value = "���濪�������Ϣ", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/saveBaseInfo",method = RequestMethod.POST)
	public AjaxReturnMsg saveBaseInfo(Hb68 hb68) throws Exception {
		return apiClassMgmtService.saveBaseInfo(hb68);
	}
	
	/**
	 * ��ѯѧԱ��Ϣ
	 */
	@ApiOperation(value = "��ѯѧԱ��Ϣ", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/getStuList",method = RequestMethod.POST)
	public AjaxReturnMsg getStuList(Student stu) throws Exception {
		PageHelper.offsetPage(stu.getOffset(), stu.getLimit());
    	PageInfo<Student> pageInfo = new PageInfo<Student>(apiClassMgmtService.getStuList(stu));
		return this.success(pageInfo);
		
	}
	
	/**
	 * ��ʼ��ҳ��_��ȡ��ѡѧԱ
	 */
	@ApiOperation(value = "��ȡ��ѡѧԱ", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/getCheck",method = RequestMethod.POST)
	public AjaxReturnMsg getCheck(Student stu) throws Exception {
    	PageHelper.offsetPage(0,1000);//ѧԱ��ʾ��һҳ������ѡ��
    	PageInfo<Student> pageInfo = new PageInfo<Student>(apiClassMgmtService.getCheck(stu));
		return this.success(pageInfo);
	}
	
	/**
	 * ����ѧԱ��Ϣ
	 */
	@ApiOperation(value = "����ѧԱ��Ϣ", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/saveStu",method = RequestMethod.POST)
	public AjaxReturnMsg saveStu(Student stu) throws Exception {
    	String[] chc111s = stu.getSelectnodes().split(",");
    	stu.setChc111s(chc111s);
		return apiClassMgmtService.saveStu(stu);
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
		return apiClassMgmtService.delStudent(chc001);
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
		PageHelper.offsetPage(0,1000);//�γ̱���Ϣ��ʾ��һҳ������鿴
		PageInfo<Hb69> pageInfo = new PageInfo<Hb69>(apiClassMgmtService.getCourseListForLook(hb69));
		return this.success(pageInfo);
	}
	
	/**
     * ��ѯͬ������Աͬ�������µĵ���
     * @param temp ��Ա����
     * @param result
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "��ѯͬ������Աͬ�������µĵ���", notes = "��ѯͬ������Աͬ�������µĵ���", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/getSysexcelbatch", method = RequestMethod.POST)
    public AjaxReturnMsg getSysexcelbatch(@ModelAttribute Hb69Temp hb69Temp) throws Exception {
    	return apiClassMgmtService.getSysexcelbatch(hb69Temp);
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
		PageHelper.offsetPage(0,1000);//�γ̱���Ϣ��ʾ��һҳ������鿴
		PageInfo<Hb69Temp> pageinfo = apiClassMgmtService.getTempCourseList(temp);
		return this.success(pageinfo);
	}
	
	/**
	 * ����γ̱���Ϣ
	 */
	@ApiOperation(value = "����γ̱���Ϣ", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/saveCourseData",method = RequestMethod.POST)
	public AjaxReturnMsg saveCourseData(Hb69Temp hb69Temp) throws Exception {
		String[] chb069s = hb69Temp.getSelectnodes().split(",");
		hb69Temp.setChb069s(chb069s);
		return apiClassMgmtService.saveCourseData(hb69Temp);
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
		return apiClassMgmtService.submitClass(hb68);
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
		return apiClassMgmtService.revokeClass(hb68);
	}
	
	/**
	 * �鿴�༶��Ϣ
	 * @param chb068
	 * @return
	 * @throws Exception
	 */
	@ApiOperation(value = "�鿴������Ϣ", produces = MediaType.APPLICATION_JSON_VALUE)
	@RequestMapping(value="/getById",method = RequestMethod.POST)
	public AjaxReturnMsg<String> getById(Hb68 hb68)throws Exception {
		hb68 = apiClassMgmtService.getById(hb68.getChb068());
		return this.success(hb68);
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
		return apiClassMgmtService.delClass(hb68);
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
		PageInfo<Student> pageInfo = apiClassMgmtService.getStuListForLook(stu);
		return this.success(pageInfo);
	}

	/**
	 * ����ѧԱ������
	 */
	@ApiOperation(value = "����ѧԱ������", notes = "����������", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/expStuRoster", method = RequestMethod.POST)
    public AjaxReturnMsg expStuRoster(@ModelAttribute  Student stu) throws Exception {
    	List<HashMap> list = apiClassMgmtService.expStuRoster(stu);
    	Map<String, Object> result = new HashMap<>();
        result.put("list", list);
        return  this.success(result);
    }
	/**
	 * ��ȡ������������ȷ�ϱ�������Ϣ
	 * @param stu ѧԱ����
	 * @return
	 */
	@ApiOperation(value = "��ȡ������������ȷ�ϱ�������Ϣ", notes = "��ȡ������������ȷ�ϱ�������Ϣ", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/expClassSure", method = RequestMethod.POST)
    public AjaxReturnMsg expClassSure(@ModelAttribute Hb68 hb68) throws Exception {
        return apiClassMgmtService.expClassSure(hb68);
    }
	/**
	 * ��ȡ�����γ̱�������Ϣ
	 * @param chb068
	 * @return
	 * @throws Exception
	 */
	@ApiOperation(value = "��ȡ�����γ̱�������Ϣ", produces = MediaType.APPLICATION_JSON_VALUE)
	@RequestMapping(value="/getClassCourse",method = RequestMethod.POST)
	public AjaxReturnMsg getClassCourse(Hb69 hb69)throws Exception {
		List<Hb69> list = apiClassMgmtService.getClassCourse(hb69);
        return  this.success(list);
	}
	/**
	 * �鿴�༶������Ϣ
	 * @param hb68
	 * @return
	 * @throws Exception
	 */
	@ApiOperation(value = "�鿴�༶������Ϣ", produces = MediaType.APPLICATION_JSON_VALUE)
	@RequestMapping(value="/getClassFile",method = RequestMethod.POST)
	public AjaxReturnMsg getClassFile(Hb68 hb68) throws Exception {
        return apiClassMgmtService.getClassFile(hb68);
    }
}
