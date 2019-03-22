package com.insigma.mvc.service.monitor.PXJG_001_002;

import com.github.pagehelper.PageInfo;
import com.insigma.dto.AjaxReturnMsg;
import com.insigma.mvc.model.train.*;

/**
 * 打卡
 * @author jewel
 * 2018-01-10
 */
public interface AttendanceService {
	/**
	 * 查询列表
	 */
    public PageInfo<Hb68> getInfoList(Hb68 hb68) throws Exception;
		
	AjaxReturnMsg saveData(Zc02 zc02);
	
	
}
