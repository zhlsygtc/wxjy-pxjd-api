package com.insigma.mvc.service.app.APP_001_008;

import com.insigma.dto.AjaxReturnMsg;
import com.insigma.mvc.model.train.S_info;

public interface ApiMessageService {

	/**
     * ��ȡδ����Ϣ����
     */
	String getInfoNum(S_info info);

	/**
     * ��ȡ��Ϣ
     */
	AjaxReturnMsg getInfoS(S_info info);

	/**
	 * �Ķ���Ϣ
	 */
	AjaxReturnMsg readInfo(S_info info);
	
}
