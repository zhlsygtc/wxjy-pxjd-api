package com.insigma.mvc.service.app.APP_001_008;

import com.insigma.dto.AjaxReturnMsg;
import com.insigma.mvc.model.train.S_info;

public interface ApiMessageService {

	/**
     * 获取未读消息数量
     */
	String getInfoNum(S_info info);

	/**
     * 获取消息
     */
	AjaxReturnMsg getInfoS(S_info info);

	/**
	 * 阅读消息
	 */
	AjaxReturnMsg readInfo(S_info info);
	
}
