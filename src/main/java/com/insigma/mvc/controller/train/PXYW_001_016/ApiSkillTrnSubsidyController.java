package com.insigma.mvc.controller.train.PXYW_001_016;

import javax.annotation.Resource;

import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageInfo;
import com.insigma.dto.AjaxReturnMsg;
import com.insigma.mvc.MvcHelper;
import com.insigma.mvc.model.train.Hb67;
import com.insigma.mvc.model.train.Student;
import com.insigma.mvc.service.train.PXYW_001_016.ApiSkillTrnSubsidyService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(description = "������ѵ����������")
@RequestMapping("/api/jnpxbt")
public class ApiSkillTrnSubsidyController extends MvcHelper{
	
	@Resource
	private ApiSkillTrnSubsidyService apiSkillTrnSubsidyService;
	
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
		PageInfo<Hb67> pageinfo = apiSkillTrnSubsidyService.getClasssList(hb67);
		return this.success(pageinfo);
	}
	
	/**
	 * ��ȡ�ϲ��༶�µ�ѧԱ��Ϣ�б�
	 * @param stu ѧԱ����
	 * @return
	 */
	@ApiOperation(value = "�ϲ��༶�µĺϸ���Ա����", notes = "�ϲ��༶�µĺϸ���Ա����")
    @RequestMapping(value = "/getStuList", method = RequestMethod.POST)
	public AjaxReturnMsg getStuList(@ModelAttribute Student stu ) throws Exception {
		PageInfo<Student> pageinfo = apiSkillTrnSubsidyService.getStuList(stu);
		return this.success(pageinfo);
	}
	
	/**
	 * ���뱣��
	 */
	@ApiOperation(value = "���뱣��", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/apply",method = RequestMethod.POST)
	public AjaxReturnMsg apply(Student stu) throws Exception {
		return apiSkillTrnSubsidyService.saveStu(stu);
	}
	
	/**
	 * ���ݺϲ��༶�����ѯ�ϲ��༶��Ϣ 
	 */
    @RequestMapping(value = "/getHb67ById")
    public AjaxReturnMsg getHb67ById(Hb67 hb67) throws Exception{
		return this.success(apiSkillTrnSubsidyService.getHb67ById(hb67.getChb067()));
    }
	
    /**
	 * ѧԱ���벹����Ϣ
	 * @param stu ѧԱ����
	 * @return
	 */
	@ApiOperation(value = "ѧԱ���벹����Ϣ ", notes = "ѧԱ���벹����Ϣ ")
    @RequestMapping(value = "/getApplyStusForLook", method = RequestMethod.POST)
	public AjaxReturnMsg getApplyStusForLook(@ModelAttribute Student stu) throws Exception {
		PageInfo<Student> pageinfo = apiSkillTrnSubsidyService.getApplyStusForLook(stu);
		return this.success(pageinfo);
	}
    
}
