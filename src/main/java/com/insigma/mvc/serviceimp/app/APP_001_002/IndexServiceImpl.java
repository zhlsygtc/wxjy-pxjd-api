package com.insigma.mvc.serviceimp.app.APP_001_002;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.insigma.common.listener.AppConfig;
import com.insigma.dto.AjaxReturnMsg;
import com.insigma.mvc.MvcHelper;
import com.insigma.mvc.dao.app.APP_001_002.IndexMapper;
import com.insigma.mvc.model.SBanner;
import com.insigma.mvc.model.train.Hb65;
import com.insigma.mvc.service.app.APP_001_002.IndexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class IndexServiceImpl extends MvcHelper implements IndexService {
    @Autowired
    IndexMapper indexMapper;
    @Override
    public PageInfo<Hb65> getAllPlan(Hb65 hb65) {
       //PageHelper.startPage(hb65.getCurpage(), hb65.getLimit());
        PageHelper.startPage(hb65.getCurpage(),10);
        String fore = AppConfig.getProperties("fileModule");
        List<Hb65> list=indexMapper.getAllPlan(fore,hb65.getChc111(),hb65.getUserid());
        PageInfo<Hb65> pageinfo = new PageInfo<>(list);
        return pageinfo;
    }
    /**
     * ªÒ»°≈‡—µBANNER
     * @return
     */
	@Override
	public AjaxReturnMsg getBannerList() {
		String fileModule = AppConfig.getProperties("fileModule");
		List<SBanner> list = indexMapper.getBannerList("010303",fileModule);
		return this.success(list);
	}
}
