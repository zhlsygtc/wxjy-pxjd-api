package com.insigma.mvc.service.monitor.PXJG_001_006;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.insigma.dto.AjaxReturnMsg;
import com.insigma.mvc.model.train.*;

/**
 * 考勤查询
 * @author jewel
 * 2018-01-10
 */
public interface QueryAttendanceService {
	/**
	 * 查询列表
	 */
    public PageInfo<Hb69DTO> getInfoList(Hb69DTO param) throws Exception;
    
  
	public PageInfo<Hc60DTO> getHc60List(Hc60DTO hc60DTO) throws Exception;
	

	public PageInfo<Hb69DTO> getHb69List(Hb69DTO hb69DTO) throws Exception;
	
	

	public PageInfo<Hb69DTO> attendanceQueryList(Hb69DTO hb69DTO) throws Exception;
	/**
	 * 获取导出现场确认表所需学员信息
	 * @param hb69
	 * @return
	 */
	public List<Hc60> getStuForSceneSure(Hc60 hc60);
	
}
