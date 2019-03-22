package com.insigma.mvc.dao.app.APP_001_001;

import com.insigma.mvc.model.AdditionalModel;
import com.insigma.mvc.model.Ce01;
import com.insigma.mvc.model.train.Hc61;

public interface ApiCommonMapper {
	
	/**
	 * 获得个人基本信息
	 */
	Ce01 getCe01ByEec001(String eec001);

	/**
	 * 获得学员基本信息
	 */
	Hc61 getStudentInfo(AdditionalModel add);
}
