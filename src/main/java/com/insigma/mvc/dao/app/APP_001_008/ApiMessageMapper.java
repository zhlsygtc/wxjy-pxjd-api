package com.insigma.mvc.dao.app.APP_001_008;

import java.util.List;

import com.insigma.mvc.model.train.S_info;

public interface ApiMessageMapper {

	/**
     * 获取消息
     */
	List<S_info> getInfos(S_info info);

	/**
     * 阅读消息
     */
	int readInfo(S_info info);

}
