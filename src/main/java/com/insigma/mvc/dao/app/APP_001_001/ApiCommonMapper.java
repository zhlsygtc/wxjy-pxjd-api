package com.insigma.mvc.dao.app.APP_001_001;

import com.insigma.mvc.model.AdditionalModel;
import com.insigma.mvc.model.Ce01;
import com.insigma.mvc.model.train.Hc61;

public interface ApiCommonMapper {
	
	/**
	 * ��ø��˻�����Ϣ
	 */
	Ce01 getCe01ByEec001(String eec001);

	/**
	 * ���ѧԱ������Ϣ
	 */
	Hc61 getStudentInfo(AdditionalModel add);
}
