package com.insigma.mvc.controller.train.PXYW_001_014;

import java.util.List;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.insigma.dto.AjaxReturnMsg;
import com.insigma.mvc.MvcHelper;
import com.insigma.mvc.model.train.Hb50;
import com.insigma.mvc.model.train.Hb50Dto;
import com.insigma.mvc.model.train.Hb67;
import com.insigma.mvc.model.train.Hb69;
import com.insigma.mvc.model.train.Hc60;
import com.insigma.mvc.model.train.Student;
import com.insigma.mvc.service.train.PXYW_001_014.ApiSubsidyService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(description = "�����걨")
@RequestMapping("/api/subsidy")
public class ApiSubsidyController extends MvcHelper{
	
	@Resource
	private ApiSubsidyService apiSubsidyService;

	/**
	 * ������Ϣ�б�
	 * @param hb67 ���ڶ���
	 * @return
	 * @throws Exception
	 */
    @RequestMapping(value = "/getHb67List")
	public AjaxReturnMsg getHb67List(Hb67 hb67 ) throws Exception {
		return this.success(apiSubsidyService.getHb67List(hb67));	
	}
	
    /**
     * ���ɰ��ڲ�����Ϣ
     *
     * @param hb67 ���ڶ���
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "���ɰ��ڲ�����Ϣ", notes = "���ɰ��ڲ�����Ϣ", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/generate", method = RequestMethod.POST)
    public AjaxReturnMsg generate(@ModelAttribute Hb67 hb67) throws Exception {
    	
    	return apiSubsidyService.generate(hb67);
    }
    
    /**
	 * ��ѯ������Ϣ 
	 */
    @RequestMapping(value = "/getHb67ById")
    public AjaxReturnMsg getHb67ById(Hb67 hb67) throws Exception{
		return this.success(apiSubsidyService.getHb67ById(hb67.getChb067()));
    }
    
    /**
	 * ��ѯ�ϲ��༶��ѧԱ�б� 
	 */
    @RequestMapping(value = "/getHc60StuList")
	public AjaxReturnMsg getHc60StuList(Hc60 hc60) throws Exception {
		return this.success(apiSubsidyService.getHc60StuList(hc60));	
	}
    
    /**
	 * ��ѯ������ѧԱ�����б�
	 */
    @RequestMapping(value = "/subsidyList")
	public AjaxReturnMsg subsidyList(Student stu) throws Exception {
		return this.success(apiSubsidyService.subsidyList(stu));	
	}
    
    /**
	 * ɾ��ѧԱ������Ϣ
	 * @param request
	 * @param stu ѧԱ����
	 * @return
	 */
    @ApiOperation(value = "ɾ��ѧԱ������Ϣ", notes = "ɾ��ѧԱ������Ϣ", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public AjaxReturnMsg delete(@ModelAttribute @Valid Student stu, BindingResult result) throws Exception {
    	//��������
        if (result.hasErrors()) {
            return validate(result);
        }
    	return apiSubsidyService.delete(stu);
    }
    
    /**
	 * ����ѧԱ������Ϣ
	 * @param request
	 * @param stu ѧԱ����
	 * @return
	 */
    @ApiOperation(value = "����ѧԱ������Ϣ", notes = "����ѧԱ������Ϣ", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public AjaxReturnMsg update(@ModelAttribute @Valid Student stu, BindingResult result) throws Exception {
    	//��������
        if (result.hasErrors()) {
            return validate(result);
        }
    	return apiSubsidyService.update(stu);
    }
    
    /**
	 * ��ѯѧԱ������Ϣ 
	 */
    @RequestMapping(value = "/toedit")
    public AjaxReturnMsg getHc50ById(Student stu) throws Exception{
		return this.success(apiSubsidyService.getHc50ById(stu));
    }
    
    /**
     * �������ɰ��ڲ�����Ϣ
     *
     * @param Hb50 ��������
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "�������ɰ��ڲ�����Ϣ", notes = "�������ɰ��ڲ�����Ϣ", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/generateAgain", method = RequestMethod.POST)
    public AjaxReturnMsg generateAgain(@ModelAttribute Hb50 hb50) throws Exception {
    	
    	return apiSubsidyService.generateAgain(hb50);
    }
    
    /**
     * �ύ���ڲ�����Ϣ
     *
     * @param hb67 ���ڶ���
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "�ύ���ڲ�����Ϣ", notes = "�ύ���ڲ�����Ϣ", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/submit", method = RequestMethod.POST)
    public AjaxReturnMsg submit(@ModelAttribute Hb67 hb67) throws Exception {
    	
    	return apiSubsidyService.submit(hb67);
    }
    /**
	 * ����id��ѯ������Ϣ
	 * @param hb50 ������Ϣ����
	 * @return
	 */
	@ApiOperation(value = "����id��ѯ������Ϣ", notes = "����id��ѯ������Ϣ", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/getSubsidy", method = RequestMethod.POST)
    public AjaxReturnMsg getSubsidyById(@ModelAttribute Hb50Dto hb50) throws Exception {
        return apiSubsidyService.getSubsidyById(hb50.getChb050());
    }
	/**
	 * ��ȡ������ѵѧԱ����������
	 * @param chb068
	 * @return
	 * @throws Exception
	 */
	@ApiOperation(value = " ��ȡ������ѵѧԱ����������", produces = MediaType.APPLICATION_JSON_VALUE)
	@RequestMapping(value="/getexportStuBT",method = RequestMethod.POST)
	public AjaxReturnMsg getexportStuBT(Student stu)throws Exception {
		List<Student> list = apiSubsidyService.getexportStuBT(stu);
        return  this.success(list);
	}
}
