package com.insigma.mvc.serviceimp.app.APP_001_010;

import com.insigma.common.listener.AppConfig;
import com.insigma.mvc.dao.app.APP_001_010.ApiNearbyInstMapper;
import com.insigma.mvc.model.train.Hb65;
import com.insigma.mvc.service.app.APP_001_010.ApiNearbyInstService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ApiNearbyInstServiceImpl implements ApiNearbyInstService {
    private final double DISTANCE = 3000;
    @Autowired
    ApiNearbyInstMapper apiNearbyInstMapper;

    @Override
    public List<Hb65> getNearbyInst(String destLon, String destLat) {
        if (destLat == null || destLon == null) {
            return null;
        }
        double lon = Double.parseDouble(destLon);
        double lat = Double.parseDouble(destLat);
        String fore = AppConfig.getProperties("fileModule");
        List<Hb65> hb65List = apiNearbyInstMapper.getNearbyInst(fore);
        List<Hb65> hb65s = new ArrayList<>();
        for (int i = 0; i < hb65List.size(); i++) {
            double srcLon = hb65List.get(i).getGps_lon();
            double srcLat = hb65List.get(i).getGps_lat();
            double trueDistance = getDistance(lon, lat, srcLon, srcLat);
            hb65List.get(i).setDistance(trueDistance);
            if (trueDistance < DISTANCE) {
                hb65s.add(hb65List.get(i));
            }
        }
        return hb65s;
    }

    private final double EARTH_RADIUS = 6378137;//赤道半径

    private double rad(double d) {
        return d * Math.PI / 180.0;
    }
    //    传入坐标经纬度，计算距离，返回值以米为单位
    public double getDistance(double lon1, double lat1, double lon2, double lat2) {
        double radLat1 = rad(lat1);
        double radLat2 = rad(lat2);
        double a = radLat1 - radLat2;
        double b = rad(lon1) - rad(lon2);
        double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a / 2), 2) + Math.cos(radLat1) * Math.cos(radLat2) * Math.pow(Math.sin(b / 2), 2)));
        s = s * EARTH_RADIUS;
        return s;//单位米
    }
}
