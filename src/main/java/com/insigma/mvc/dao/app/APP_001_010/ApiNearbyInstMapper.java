package com.insigma.mvc.dao.app.APP_001_010;

import com.insigma.mvc.model.train.Hb65;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ApiNearbyInstMapper {
    List<Hb65> getNearbyInst(@Param("fore") String fore);
}
