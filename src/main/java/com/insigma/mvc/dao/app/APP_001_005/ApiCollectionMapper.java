package com.insigma.mvc.dao.app.APP_001_005;

import java.util.List;

import com.insigma.mvc.model.train.Hb65;

public interface ApiCollectionMapper {

	/**
	 * ��ȡ��ѵ�ղ���Ϣ�б�
	 */
	List<Hb65> getCollectionList(Hb65 hb65);

	/**
	 * �ղؼƻ��������
	 */
	Hb65 getPlanInfo(Hb65 hb65);

	/**
     * �ղؼƻ�
     */
	int doCollect(Hb65 hb65);

	/**
	 * ȡ���ղ�
	 */
	int deleteCollect(Hb65 hb65);

}
