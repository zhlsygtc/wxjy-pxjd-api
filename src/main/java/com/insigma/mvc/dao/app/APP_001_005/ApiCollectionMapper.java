package com.insigma.mvc.dao.app.APP_001_005;

import java.util.List;

import com.insigma.mvc.model.train.Hb65;

public interface ApiCollectionMapper {

	/**
	 * 获取培训收藏信息列表
	 */
	List<Hb65> getCollectionList(Hb65 hb65);

	/**
	 * 收藏计划详情界面
	 */
	Hb65 getPlanInfo(Hb65 hb65);

	/**
     * 收藏计划
     */
	int doCollect(Hb65 hb65);

	/**
	 * 取消收藏
	 */
	int deleteCollect(Hb65 hb65);

}
