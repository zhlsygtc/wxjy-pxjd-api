package com.insigma.mvc.dao.app.APP_001_008;

import java.util.List;

import com.insigma.mvc.model.train.S_info;

public interface ApiMessageMapper {

	/**
     * ��ȡ��Ϣ
     */
	List<S_info> getInfos(S_info info);

	/**
     * �Ķ���Ϣ
     */
	int readInfo(S_info info);

}
