package com.insigma.mvc.service.app.APP_001_001;

import com.insigma.dto.AjaxReturnMsg;
import com.insigma.mvc.model.AdditionalModel;
import com.insigma.mvc.model.SUser;
import com.insigma.mvc.model.train.Hc61;

public interface ApiCommonService {
		
	/*
	 * ��ȡ������Ϣ
	 */
	AjaxReturnMsg getPersonBaseInfo(SUser suser);
	
	/*
	 * �����û���Ϣ��ȡѧԱ��Ϣ
	 */
	Hc61 getStudentInfo(AdditionalModel add);
}
