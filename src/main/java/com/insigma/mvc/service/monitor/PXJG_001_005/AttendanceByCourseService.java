package com.insigma.mvc.service.monitor.PXJG_001_005;


import com.github.pagehelper.PageInfo;
import com.insigma.dto.AjaxReturnMsg;
import com.insigma.mvc.model.train.*;

/**
 * 打卡
 * @author jewel
 * 2018-01-10
 */
public interface AttendanceByCourseService {
	/**
	 * 查询列表
	 */
    public PageInfo<Hb69DTO> getInfoList(Hb69DTO param) throws Exception;
    
	/**
     * 根据班级内码查询培训班中学生列表
     */
	public PageInfo<Hc60> getHc60ById(Hc60 hc60) throws Exception;
	  
	 
    /**
     * 根据班级内码查询培训班中学生列表
     */
    AjaxReturnMsg getHc60ById1(Hc60 hc60) throws Exception;
	/**
     * 根据课程内码查询培训班信息
     */
	public Hb69 getHb69ById(Hb69 hb69) throws Exception;
		
	AjaxReturnMsg saveData(Zc10 zc10) throws Exception;
	
	
}
