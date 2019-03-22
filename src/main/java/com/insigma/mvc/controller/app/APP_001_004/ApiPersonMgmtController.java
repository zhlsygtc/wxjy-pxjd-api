package com.insigma.mvc.controller.app.APP_001_004;

import javax.annotation.Resource;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.insigma.dto.AjaxReturnMsg;
import com.insigma.mvc.MvcHelper;
import com.insigma.mvc.model.train.Student;
import com.insigma.mvc.service.app.APP_001_004.ApiPersonMgmtService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/personMgmt")
public class ApiPersonMgmtController extends MvcHelper{
	
	@Resource
	ApiPersonMgmtService apiPersonMgmtService ;
	
    
    /**
     * ѧԱ�����ƻ���ʼ
     * @param stu ѧԱ����
     * @throws Exception
     */
    @ApiOperation(value = "ѧԱ�����ƻ���ʼ", notes = "ѧԱ�����ƻ���ʼ", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public AjaxReturnMsg signup(@ModelAttribute Student stu) throws Exception {
        return apiPersonMgmtService.signup(stu);
    }
    
    /**
     * ��ȡѧԱ������Ϣ
     * @param stu ѧԱ����
     * @throws Exception
     */
    @ApiOperation(value = "��ȡѧԱ������Ϣ", notes = "��ȡѧԱ������Ϣ", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/getStudentInfo", method = RequestMethod.POST)
    public AjaxReturnMsg getStudentInfo(@ModelAttribute Student stu) throws Exception {
        return apiPersonMgmtService.getStudentInfo(stu);
    }
    
    /**
     * ��ȡ��(ѧ)Ա������Ϣ
     * @param stu ѧԱ����
     * @throws Exception
     */
    @ApiOperation(value = "��ȡ��(ѧ)Ա������Ϣ", notes = "��ȡ��(ѧ)Ա������Ϣ", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/getPersonInfo", method = RequestMethod.POST)
    public AjaxReturnMsg getPersonInfo(@ModelAttribute Student stu) throws Exception {
        return apiPersonMgmtService.getPersonInfo(stu);
    }
   
    /**
     * ����ѧԱ������Ϣ
     * @param stu ѧԱ����
     * @throws Exception
     */
    @ApiOperation(value = "����ѧԱ������Ϣ", notes = "����ѧԱ������Ϣ", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/saveStudent", method = RequestMethod.POST)
    public AjaxReturnMsg saveStudent(@ModelAttribute Student stu) throws Exception {
        return apiPersonMgmtService.saveStudent(stu);
    }
    
    /**
     * ѧԱ�����ƻ�����
     * @param stu ѧԱ����
     * @throws Exception
     */
    @ApiOperation(value = "ѧԱ�����ƻ�����", notes = "ѧԱ�����ƻ�����", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/apply", method = RequestMethod.POST)
    public AjaxReturnMsg apply(@ModelAttribute Student stu) throws Exception {
        return apiPersonMgmtService.apply(stu);
    }
    
}
