package com.insigma.mvc.serviceimp.app.APP_001_005;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.insigma.common.listener.AppConfig;
import com.insigma.dto.AjaxReturnMsg;
import com.insigma.mvc.MvcHelper;
import com.insigma.mvc.dao.app.APP_001_005.ApiCollectionMapper;
import com.insigma.mvc.model.train.Hb65;
import com.insigma.mvc.service.app.APP_001_005.ApiCollectionService;

@Service
public class ApiCollectionServiceImpl extends MvcHelper implements ApiCollectionService{
	
	@Resource
	ApiCollectionMapper apiCollectionMapper;

	/**
	 * ��ȡ��ѵ�ղ���Ϣ�б�
	 */
	@Override
	public PageInfo<Hb65> getCollectionList(Hb65 hb65) {
		
		String fore = AppConfig.getProperties("fileModule");
		hb65.setFore(fore);
		PageHelper.startPage(hb65.getCurpage(), 10);
		List<Hb65> list = apiCollectionMapper.getCollectionList(hb65);
		PageInfo<Hb65> pageinfo = new PageInfo<>(list);
		return pageinfo;
	}

	/**
	 * �ղؼƻ��������
	 */
	@Override
	public AjaxReturnMsg getPlanInfo(Hb65 hb65) {
		String fore = AppConfig.getProperties("fileModule");
		hb65.setFore(fore);
		hb65 = apiCollectionMapper.getPlanInfo(hb65);
		return this.success(hb65);
	}

	/**
     * �ղؼƻ�
     */
	@Override
	public AjaxReturnMsg doCollect(Hb65 hb65) {
		int num = 0;
		if("1".equals(hb65.getFlag())) {
			num = apiCollectionMapper.doCollect(hb65);
			if(num == 1) {
				return this.success("�ղسɹ�");
			}else{
				return this.error("�ղ�ʧ��");
			}
		}else{
			num = apiCollectionMapper.deleteCollect(hb65);
			if(num == 1) {
				return this.success("ȡ���ղسɹ�");
			}else{
				return this.error("ȡ���ղ�ʧ��");
			}
		}
	}

	
}
