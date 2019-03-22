package com.insigma.mvc.service.app.APP_001_010;

import com.insigma.mvc.model.train.Hb65;

import java.util.List;

public interface ApiNearbyInstService {
    List<Hb65> getNearbyInst(String destLon,String destLat);
}
