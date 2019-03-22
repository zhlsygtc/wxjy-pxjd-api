package com.insigma.mvc.service.app.APP_001_003;

import com.github.pagehelper.PageInfo;
import com.insigma.mvc.model.train.Ad01;
import com.insigma.mvc.model.train.Hb61;
import com.insigma.mvc.model.train.Hb65;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;

public interface DetailService {

    Hb65 getDetailPlan(Hb65 hb65);

    List<Hb65> getInstPlan(Hb65 hb65);

    List<Hb65> getSimilarPlan(Hb65 hb65);

    //  机构详细信息
    Ad01 getDetailInst(String aab001);//机构id

    //  机构专业
    List<String> getInstMajor(String aab001);

    //  机构老师
    List<Hb61> getInstTea(String aab001);

    //  老师专业
    List<String> getTeaQua(String chb061);//老师id

    //  机构文件
    List<HashMap<String,String>> getInstVideo(String aab001);
}
