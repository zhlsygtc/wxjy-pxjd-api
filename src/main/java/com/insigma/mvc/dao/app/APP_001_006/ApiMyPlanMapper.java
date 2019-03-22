package com.insigma.mvc.dao.app.APP_001_006;

import com.insigma.mvc.model.train.*;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ApiMyPlanMapper {
    //    查询个人的培训计划
    List<Hb65> getMyPlans(String chc111);

    Hb68 getMyDetailClass(String chb068);

    List<Hb61> getMyClassTea(@Param("fore") String fore, @Param("chb068") String chb068);

    String getChc001(@Param("chc111") String chc111, @Param("chb068") String chb068);

    Hc66 getMyScore(@Param("chc001") String chc001, @Param("chb068") String chb068);

    List<Hb69> getMyCourse(@Param("chc001") String chc001, @Param("chb068") String chb068,@Param("chb160") String chb160);

    List<String> getChb160(String chb068);
}
