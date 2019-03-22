package com.insigma.mvc.service.app.APP_001_006;

import com.github.pagehelper.PageInfo;
import com.insigma.mvc.model.train.*;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface ApiMyPlanService {
    PageInfo<Hb65> getMyPlans(Hb65 hb65);

    Hb68 getMyDetailClass(String chb068);

    List<Hb61> getMyClassTea(String chb068);

    String getChc001(String chc111, String chb068);

    Hc66 getMyScore(String chc001, String chb068);

    List<Hb69> getMyCourse(Hb69 hb69);

    PageInfo<String> getChb160(Hb69 hb69);
}
