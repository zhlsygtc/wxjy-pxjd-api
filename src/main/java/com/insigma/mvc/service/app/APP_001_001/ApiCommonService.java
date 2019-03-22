package com.insigma.mvc.service.app.APP_001_001;

import com.insigma.dto.AjaxReturnMsg;
import com.insigma.mvc.model.AdditionalModel;
import com.insigma.mvc.model.SUser;
import com.insigma.mvc.model.train.Hc61;

public interface ApiCommonService {
		
	/*
	 * 获取个人信息
	 */
	AjaxReturnMsg getPersonBaseInfo(SUser suser);
	
	/*
	 * 根据用户信息获取学员信息
	 */
	Hc61 getStudentInfo(AdditionalModel add);
}
