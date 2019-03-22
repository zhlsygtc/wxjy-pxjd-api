package com.insigma.mvc.serviceimp.app.APP_001_009;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.insigma.common.listener.AppConfig;
import com.insigma.mvc.dao.app.APP_001_009.ApiSearchMapper;
import com.insigma.mvc.model.train.Hb65;
import com.insigma.mvc.service.app.APP_001_009.ApiSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ApiSearchServiceImpl implements ApiSearchService {
    @Autowired
    ApiSearchMapper apiSearchMapper;

    @Override
    public PageInfo<Hb65> getSearchResult(Hb65 hb65) {
        PageHelper.startPage(hb65.getCurpage(), 10);
        String fore = AppConfig.getProperties("fileModule");
        List<Hb65> list = apiSearchMapper.getSearchResult(hb65.getChc111(),hb65.getParam(),fore);
        PageInfo<Hb65> pageinfo = new PageInfo<>(list);
        return pageinfo;
    }
}
