package com.insigma.mvc.controller.train.PXYW_001_004;

import javax.annotation.Resource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.insigma.dto.AjaxReturnMsg;
import com.insigma.mvc.MvcHelper;
import com.insigma.mvc.model.train.Hb67;
import com.insigma.mvc.model.train.Hb68;
import com.insigma.mvc.model.train.Hb69;
import com.insigma.mvc.model.train.Hb69DTO;
import com.insigma.mvc.model.train.Hc60;
import com.insigma.mvc.model.train.Hc66;
import com.insigma.mvc.model.train.Zc10;
import com.insigma.mvc.service.train.PXYW_001_004.ApiCompreQueryService;
/**
 * ��ѵ���ۺϲ�ѯcontoller
 * @author link
 * 2018-01-29
 */
@RestController
@RequestMapping("/api/compreQuery")
public class ApiCompreQueryController extends MvcHelper{
	@Resource
	private ApiCompreQueryService compreQueryService;
	/**
	 * ��ʼ����ѵ�������Ϣ�б� 
	 */
    @RequestMapping(value = "/getInfoList")
	public AjaxReturnMsg getHb68List(Hb68 hb68 ) throws Exception {
		return this.success(compreQueryService.getInfoList(hb68));	
	}

	/**
	 * ���ݺϲ���༶��ϲ�ǰ�༶�б�
	 */
	@RequestMapping(value = "/getInfoListByChb067")
	public AjaxReturnMsg getInfoListByChb067(Hb67 hb67 ) throws Exception {
		return this.success(compreQueryService.getInfoListByChb067(hb67));
	}
    
    /**
     * ���ݰ༶�����ѯ��ѵ����ѧ���б�
     */
    @RequestMapping(value = "/getHc60ById")
    public AjaxReturnMsg getHc60ById(Hc60 hc60) throws Exception {
		return this.success(compreQueryService.getHc60ById(hc60));	
	}
    
    /**
     * ���ݰ༶�����ѯ��ѵ����Ϣ
     */
    @RequestMapping(value = "/getHb68ById")
    public AjaxReturnMsg getHb68ById(Hb68 hb68) throws Exception{
		return this.success(compreQueryService.getHb68ById(hb68.getChb068()));
    }
    
    
    
    /**
     * ���ݰ༶�����ѯ��ѵ��γ��б�
     */
    @RequestMapping(value = "/getHb69List")
    public AjaxReturnMsg getHb69ById(Hb69 hb69) throws Exception{
		return this.success(compreQueryService.getHb69ById(hb69));	
    }
    
    
    @RequestMapping(value = "/getHb69List1")
    public AjaxReturnMsg getHb69ByChb068(Hb69 hb69) throws Exception{
		return this.success(compreQueryService.getHb69ByChb068(hb69));	
    }
    
	/**
	 * ��ѯ��ѵ��ϲ��б� 
	 */
    @RequestMapping(value = "/getMergeList")
	public AjaxReturnMsg getMergeList(Hb68 hb68) throws Exception {
		return this.success(compreQueryService.getMergeList(hb68));	
	}
    
    /**
	 * ���ݺϲ��༶�����ѯ�ϲ��༶��Ϣ 
	 */
    @RequestMapping(value = "/getHb67ById")
    public AjaxReturnMsg getHb67ById(Hb67 hb67) throws Exception{
		return this.success(compreQueryService.getHb67ById(hb67.getChb067()));
    }
    
    /**
	 * ��ѯ�ϲ��༶��ѧԱ�б� 
	 */
    @RequestMapping(value = "/getHc60StuList")
	public AjaxReturnMsg getHc60StuList(Hc60 hc60) throws Exception {
		return this.success(compreQueryService.getHc60StuList(hc60));	
	}
    
    /**
	 * ��ѯ�ϲ��༶�пγ��б� 
	 */
    @RequestMapping(value = "/getHb69MergeCourseList")
	public AjaxReturnMsg getHb69MergeCourseList(Hb69 hb69) throws Exception {
		return this.success(compreQueryService.getHb69MergeCourseList(hb69));	
	}
    
    /**
     * ����ѧԱid��ѯѧԱ�ɼ���Ϣ
     */
    @RequestMapping(value = "/getHc66ById")
	public AjaxReturnMsg getHc66ById(Hc66 hc66) throws Exception {
		return this.success(compreQueryService.getHc66ById(hc66));	
	}
    
    /**
     * ����ѧԱid��ѯѧԱ������Ϣ
     */
    @RequestMapping(value = "/getZc10List")
    public AjaxReturnMsg getZc10List(Zc10 zc10) throws Exception{
		return this.success(compreQueryService.getZc10ById(zc10));	
    }
    
    /**
     * ��ȡѧԱ�γ̿�����ϸ
     */
    @RequestMapping(value = "/attendanceQueryList")
    public AjaxReturnMsg attendanceQueryList(Hb69DTO hb69DTO) throws Exception{
		return this.success(compreQueryService.attendanceQueryList(hb69DTO));	
    }
    
}