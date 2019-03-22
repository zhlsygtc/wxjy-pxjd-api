package com.insigma.mvc.serviceimp.app.APP_001_006;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.insigma.common.listener.AppConfig;
import com.insigma.mvc.dao.app.APP_001_006.ApiMyPlanMapper;
import com.insigma.mvc.model.train.*;
import com.insigma.mvc.service.app.APP_001_006.ApiMyPlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ApiMyPlanServiceImpl implements ApiMyPlanService {
    @Autowired
    ApiMyPlanMapper apiMyPlanMapper;

    @Override
    public PageInfo<Hb65> getMyPlans(Hb65 hb65) {
        PageHelper.startPage(hb65.getCurpage(), 10);
        List<Hb65> list = apiMyPlanMapper.getMyPlans(hb65.getChc111());
        PageInfo<Hb65> pageinfo = new PageInfo<>(list);
        return pageinfo;
    }

    @Override
    public Hb68 getMyDetailClass(String chb068) {
        return apiMyPlanMapper.getMyDetailClass(chb068);
    }

    @Override
    public List<Hb61> getMyClassTea(String chb068) {
        String fore = AppConfig.getProperties("fileModule");
        return apiMyPlanMapper.getMyClassTea(fore, chb068);
    }

    @Override
    public String getChc001(String chc111, String chb068) {
        return apiMyPlanMapper.getChc001(chc111, chb068);
    }

    @Override
    public Hc66 getMyScore(String chc001, String chb068) {
        return apiMyPlanMapper.getMyScore(chc001, chb068);
    }

    @Override
    public List<Hb69> getMyCourse(Hb69 hb69) {
        List<Hb69> list = apiMyPlanMapper.getMyCourse(hb69.getChc001(), hb69.getChb068(), hb69.getChb160());
        return list;
    }

    @Override
    public PageInfo<String> getChb160(Hb69 hb69) {
        PageHelper.startPage(hb69.getCurpage(), 5);
        List<String> strings = apiMyPlanMapper.getChb160(hb69.getChb068());
        PageInfo<String> pageInfo = new PageInfo<>(strings);
        return pageInfo;
    }
}
