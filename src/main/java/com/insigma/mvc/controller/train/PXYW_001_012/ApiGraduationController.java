package com.insigma.mvc.controller.train.PXYW_001_012;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.insigma.mvc.model.train.Hb67;
import com.insigma.mvc.model.train.Hb68;
import com.insigma.mvc.model.train.Hc60;
import com.insigma.mvc.model.train.Student;
import com.insigma.mvc.service.train.PXYW_001_012.ApiGraduationService;

import io.swagger.annotations.ApiOperation;
/**
 * �༶��ҵ����
 * @author link
 * 2018-03-14
 */
@RestController
@RequestMapping("/api/graduation")
public class ApiGraduationController extends MvcHelper {
	
	@Resource
	private ApiGraduationService apiGraduationService;
	/**
	 * ��ʼ���ϲ�������Ϣ�б� 
	 */
    @RequestMapping(value = "/getHb67List")
	public AjaxReturnMsg getHb67List(Hb67 hb67 ) throws Exception {
		return this.success(apiGraduationService.getHb67List(hb67));	
	}
    
    /**
	 * ��ʼ����ѵ�������Ϣ�б� 
	 */
    @RequestMapping(value = "/getHb68List")
	public AjaxReturnMsg getHb68List(Hb68 hb68 ) throws Exception {
		return this.success(apiGraduationService.getHb68List(hb68));	
	}
    
    /**
     * ���ݰ༶��Ų�ѯ��ϸ��Ϣ
     */
    @RequestMapping(value = "/getHb68ByChb068")
    public AjaxReturnMsg getHb68ByChb068(Hb68 hb68) throws Exception{
		return this.success(apiGraduationService.getHb68ByChb068(hb68));
    }
    /**
	 * ���ݺϲ��༶�����ѯ�ϲ��༶��Ϣ 
	 */
    @RequestMapping(value = "/getHb67ById")
    public AjaxReturnMsg getHb67ById(Hb67 hb67) throws Exception{
		return this.success(apiGraduationService.getHb67ById(hb67.getChb067()));
    }
    /**
   	 * ��ѯ�ϲ��༶��ѧԱ�б� 
   	 */
       @RequestMapping(value = "/mergeHGStuList")
   	public AjaxReturnMsg mergeHGStuList(Hc60 hc60) throws Exception {
   		return this.success(apiGraduationService.mergeHGStuList(hc60));	
   	}
    /**
	 * �ϲ��༶��Ϣ����
	 */
	@ApiOperation(value = "�ϲ��༶��Ϣ����", notes = "�ϲ��༶��Ϣ����", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/savedata", method = RequestMethod.POST)
    public AjaxReturnMsg saveData(@ModelAttribute @Valid Hb67 hb67, BindingResult result) throws Exception {
    	//��������
        if (result.hasErrors()) {
            return validate(result);
        }
        try {
        	apiGraduationService.addMargeClass(hb67);
        }catch (Exception e) {
			return this.error("����ʧ��");
		}
        return this.success("����ɹ�");
        
    }
	
	/**
	 * ���ڽ�ҵ״̬�޸�
	 */
	@ApiOperation(value = "���ڽ�ҵ", notes = "���ڽ�ҵ", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/updateChb528", method = RequestMethod.POST)
    public AjaxReturnMsg updateCHb528(@ModelAttribute Hb67 hb67) throws Exception {
        try {
        	apiGraduationService.updateChb528(hb67);
        }catch (Exception e) {
			return this.error("�༶��ҵʧ��");
		}
        return this.success("�༶��ҵ�ɹ�");
    }
	
	/**
	 * ����������
	 */
	@ApiOperation(value = "����������", notes = "����������", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/toExp", method = RequestMethod.POST)
    public AjaxReturnMsg toExp(@ModelAttribute  Student stu) throws Exception {
    	List<HashMap> list = apiGraduationService.toExp(stu);
    	Map<String, Object> result = new HashMap<>();
        result.put("list", list);
        return  this.success(result);
    }
	
}
