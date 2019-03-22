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
 * 培训班综合查询contoller
 * @author link
 * 2018-01-29
 */
@RestController
@RequestMapping("/api/compreQuery")
public class ApiCompreQueryController extends MvcHelper{
	@Resource
	private ApiCompreQueryService compreQueryService;
	/**
	 * 初始化培训班基础信息列表 
	 */
    @RequestMapping(value = "/getInfoList")
	public AjaxReturnMsg getHb68List(Hb68 hb68 ) throws Exception {
		return this.success(compreQueryService.getInfoList(hb68));	
	}

	/**
	 * 根据合并后班级查合并前班级列表
	 */
	@RequestMapping(value = "/getInfoListByChb067")
	public AjaxReturnMsg getInfoListByChb067(Hb67 hb67 ) throws Exception {
		return this.success(compreQueryService.getInfoListByChb067(hb67));
	}
    
    /**
     * 根据班级内码查询培训班中学生列表
     */
    @RequestMapping(value = "/getHc60ById")
    public AjaxReturnMsg getHc60ById(Hc60 hc60) throws Exception {
		return this.success(compreQueryService.getHc60ById(hc60));	
	}
    
    /**
     * 根据班级内码查询培训班信息
     */
    @RequestMapping(value = "/getHb68ById")
    public AjaxReturnMsg getHb68ById(Hb68 hb68) throws Exception{
		return this.success(compreQueryService.getHb68ById(hb68.getChb068()));
    }
    
    
    
    /**
     * 根据班级内码查询培训班课程列表
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
	 * 查询培训班合并列表 
	 */
    @RequestMapping(value = "/getMergeList")
	public AjaxReturnMsg getMergeList(Hb68 hb68) throws Exception {
		return this.success(compreQueryService.getMergeList(hb68));	
	}
    
    /**
	 * 根据合并班级内码查询合并班级信息 
	 */
    @RequestMapping(value = "/getHb67ById")
    public AjaxReturnMsg getHb67ById(Hb67 hb67) throws Exception{
		return this.success(compreQueryService.getHb67ById(hb67.getChb067()));
    }
    
    /**
	 * 查询合并班级中学员列表 
	 */
    @RequestMapping(value = "/getHc60StuList")
	public AjaxReturnMsg getHc60StuList(Hc60 hc60) throws Exception {
		return this.success(compreQueryService.getHc60StuList(hc60));	
	}
    
    /**
	 * 查询合并班级中课程列表 
	 */
    @RequestMapping(value = "/getHb69MergeCourseList")
	public AjaxReturnMsg getHb69MergeCourseList(Hb69 hb69) throws Exception {
		return this.success(compreQueryService.getHb69MergeCourseList(hb69));	
	}
    
    /**
     * 根据学员id查询学员成绩信息
     */
    @RequestMapping(value = "/getHc66ById")
	public AjaxReturnMsg getHc66ById(Hc66 hc66) throws Exception {
		return this.success(compreQueryService.getHc66ById(hc66));	
	}
    
    /**
     * 根据学员id查询学员考勤信息
     */
    @RequestMapping(value = "/getZc10List")
    public AjaxReturnMsg getZc10List(Zc10 zc10) throws Exception{
		return this.success(compreQueryService.getZc10ById(zc10));	
    }
    
    /**
     * 获取学员课程考勤明细
     */
    @RequestMapping(value = "/attendanceQueryList")
    public AjaxReturnMsg attendanceQueryList(Hb69DTO hb69DTO) throws Exception{
		return this.success(compreQueryService.attendanceQueryList(hb69DTO));	
    }
    
}