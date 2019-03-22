package com.insigma.mvc.serviceimp.app.APP_001_003;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.insigma.common.listener.AppConfig;
import com.insigma.mvc.dao.app.APP_001_003.DetailMapper;
import com.insigma.mvc.model.train.Ad01;
import com.insigma.mvc.model.train.Hb61;
import com.insigma.mvc.model.train.Hb65;
import com.insigma.mvc.service.app.APP_001_003.DetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;

@Service
@Transactional
public class DetailServiceImpl implements DetailService{
    @Autowired
    DetailMapper detailMapper;

    @Override
    public Ad01 getDetailInst(String aab001) {
        String fore = AppConfig.getProperties("fileModule");
        return detailMapper.getDetailInst(fore,aab001);
    }

    @Override
    public List<String> getInstMajor(String aab001) {
        return detailMapper.getInstMajor(aab001);
    }

    @Override
    public List<Hb61> getInstTea(String aab001) {
        String fore = AppConfig.getProperties("fileModule");
        return detailMapper.getInstTea(fore,aab001);
    }

    @Override
    public List<String> getTeaQua(String chb061) {
        return detailMapper.getTeaQua(chb061);
    }

    @Override
    public List<HashMap<String,String>> getInstVideo(String aab001) {
        String fore = AppConfig.getProperties("fileModule");
        return detailMapper.getInstFile(fore,aab001);
    }

    @Override
    public Hb65 getDetailPlan(Hb65 hb65) {
        String fore = AppConfig.getProperties("fileModule");
        return detailMapper.getDetailPlan(fore,hb65.getChb055(),hb65.getUserid());
    }

    @Override
    public List<Hb65> getInstPlan(Hb65 hb65) {
        String fore = AppConfig.getProperties("fileModule");
        List<Hb65> list = detailMapper.getInstPlan(fore,hb65.getAab001());
        return list;
    }

    @Override
    public List<Hb65> getSimilarPlan(Hb65 hb65) {
        String fore = AppConfig.getProperties("fileModule");
        List<Hb65> list = detailMapper.getSimilarPlan(fore,hb65.getAca111());
        return list;
    }
}
