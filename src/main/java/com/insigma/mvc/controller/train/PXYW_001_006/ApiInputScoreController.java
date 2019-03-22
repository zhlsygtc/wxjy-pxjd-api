package com.insigma.mvc.controller.train.PXYW_001_006;

import javax.annotation.Resource;
import com.insigma.mvc.MvcHelper;
import com.insigma.mvc.model.train.Hb68;
import com.insigma.mvc.model.train.Student;
import com.insigma.mvc.model.train.Zc13;
import com.insigma.mvc.service.train.PXYW_001_006.ApiInputScoreService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.github.pagehelper.PageInfo;
import com.insigma.dto.AjaxReturnMsg;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
/**
 * �ɼ�¼��contoller
 * @author zhanghl
 */
@RestController
@Api(description = "�ɼ�¼�������")
@RequestMapping("/api/inputScore")
public class ApiInputScoreController extends MvcHelper{
	@Resource
	private ApiInputScoreService inputScoreService;
	/**
	 * ��ʼ�����������Ϣ�б� 
	 */
	@ApiOperation(value = "��ȡ�༶������Ϣ", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/getInfoList",method = RequestMethod.POST)
	public AjaxReturnMsg getHb61List(Hb68 hb68) throws Exception {
		PageInfo<Hb68> pageInfo = inputScoreService.getInfoList(hb68);
		return this.success(pageInfo);
		
	}
    /**
	 * ����ѧԱ�ɼ�--��ѡ�ϸ��־
	 */
	@ApiOperation(value = "����ѧԱ�ɼ�(��ѡ�ϸ��־)", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/doQualified",method = RequestMethod.POST)
	public AjaxReturnMsg doQualified(Student stu) throws Exception {
    	String[] chc001s = stu.getSelectnodes().split(",");
    	stu.setChc001s(chc001s);
		return inputScoreService.doQualified(stu);
	}
	 /**
	 * ����ѧԱ�ɼ�--�ֹ�¼��
	 */
	@ApiOperation(value = "����ѧԱ�ɼ�(�ֹ�¼��)", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/saveScore",method = RequestMethod.POST)
	public AjaxReturnMsg saveScore(Student stu) throws Exception {
		return inputScoreService.saveScore(stu);
	}
	/**
	 * �ύ�ɼ���Ϣ
	 * @param hb68
	 * @return
	 * @throws Exception
	 */
	@ApiOperation(value = "�ύ�ɼ���Ϣ", produces = MediaType.APPLICATION_JSON_VALUE)
	@RequestMapping(value="/submitScore",method = RequestMethod.POST)
	public AjaxReturnMsg<String> submitScore(Hb68 hb68)throws Exception {
		return inputScoreService.submitScore(hb68);
	}
	/**
	 * �����ɼ���Ϣ
	 * @param hb68
	 * @return
	 * @throws Exception
	 */
	@ApiOperation(value = "�����ɼ���Ϣ", produces = MediaType.APPLICATION_JSON_VALUE)
	@RequestMapping(value="/revokeScore",method = RequestMethod.POST)
	public AjaxReturnMsg<String> revokeScore(Hb68 hb68)throws Exception {
		return inputScoreService.revokeScore(hb68);
	}
	/**
	 * �鿴�༶��Ϣ
	 * @param hb68
	 * @return
	 * @throws Exception
	 */
	@ApiOperation(value = "�鿴������Ϣ", produces = MediaType.APPLICATION_JSON_VALUE)
	@RequestMapping(value="/doLook",method = RequestMethod.POST)
	public AjaxReturnMsg<String> doLook(@ModelAttribute Hb68 hb68)throws Exception {
		hb68 = inputScoreService.getById(hb68);
		return this.success(hb68);
	}
	/**
	 * ��ѯ�ɼ���׼
	 * @param chb068
	 * @return
	 * @throws Exception
	 */
	@ApiOperation(value = "��ѯ�ɼ���׼", produces = MediaType.APPLICATION_JSON_VALUE)
	@RequestMapping(value="/getZc13",method = RequestMethod.POST)
	public AjaxReturnMsg<String> getZc13(@ModelAttribute Zc13 zc13)throws Exception {
		if(zc13.getAab001() == null) {
			return this.error("��¼��Ϣ��֤ʧ�ܣ������µ�¼");
		}
		zc13 = inputScoreService.getZc13(zc13.getAab001()); 
		return this.success(zc13);
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
		PageInfo<Student> pageInfo = inputScoreService.getStuListForLook(stu);
		return this.success(pageInfo);
	}
    /**
   	 * ����רҵ���Ʋ�ѯ��ѵ�������Ƽ�רҵ���
   	 */
	@ApiOperation(value = "����רҵ���Ʋ�ѯ��ѵ�������Ƽ�רҵ���", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/getAca112",method = RequestMethod.POST)
   	public AjaxReturnMsg getAca112(Hb68 hb68) throws Exception{
   		hb68 = inputScoreService.getAca112(hb68.getAca111());
   		return this.success(hb68);
   	}
}