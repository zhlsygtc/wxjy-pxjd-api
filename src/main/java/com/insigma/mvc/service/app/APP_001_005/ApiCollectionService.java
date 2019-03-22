package com.insigma.mvc.service.app.APP_001_005;

import com.github.pagehelper.PageInfo;
import com.insigma.dto.AjaxReturnMsg;
import com.insigma.mvc.model.train.Hb65;

public interface ApiCollectionService {

	/**
	 * ��ȡ��ѵ�ղ���Ϣ�б�
	 */
	PageInfo<Hb65> getCollectionList(Hb65 hb65);

	/**
	 * �ղؼƻ��������
	 */
	AjaxReturnMsg getPlanInfo(Hb65 hb65);

	/**
     * �ղؼƻ�
     */
	AjaxReturnMsg doCollect(Hb65 hb65);


}
