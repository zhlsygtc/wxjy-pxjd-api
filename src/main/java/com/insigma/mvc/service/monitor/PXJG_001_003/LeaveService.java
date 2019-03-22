package com.insigma.mvc.service.monitor.PXJG_001_003;

import com.github.pagehelper.PageInfo;
import com.insigma.dto.AjaxReturnMsg;
import com.insigma.mvc.model.train.*;

/**
 * «ÎºŸ
 * @author jewel
 * 2018-01-10
 */
public interface LeaveService {
	/**
	 * ≤È—Ø¡–±Ì
	 */
    public PageInfo<Zc07DTO> getInfoList(Zc07DTO zc07DTO) throws Exception;
		
	AjaxReturnMsg saveData(Zc07 zc07);

	AjaxReturnMsg getStuById(String chc001);

	public PageInfo<Zc07> getStuList(Zc07 zc07) throws Exception;

	AjaxReturnMsg<String> deleteData(String czc007);
}
