package com.insigma.mvc.service.app.APP_001_005;

import com.github.pagehelper.PageInfo;
import com.insigma.dto.AjaxReturnMsg;
import com.insigma.mvc.model.train.Hb65;

public interface ApiCollectionService {

	/**
	 * 获取培训收藏信息列表
	 */
	PageInfo<Hb65> getCollectionList(Hb65 hb65);

	/**
	 * 收藏计划详情界面
	 */
	AjaxReturnMsg getPlanInfo(Hb65 hb65);

	/**
     * 收藏计划
     */
	AjaxReturnMsg doCollect(Hb65 hb65);


}
