package com.insigma.mvc.controller.app.APP_001_010;

import com.insigma.dto.AjaxReturnMsg;
import com.insigma.mvc.MvcHelper;
import com.insigma.mvc.model.train.Hb65;
import com.insigma.mvc.service.app.APP_001_010.ApiNearbyInstService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/nearby")
public class ApiNearbyInstController extends MvcHelper {
    @Autowired
    ApiNearbyInstService apiNearbyInstService;

    @ApiOperation(value = "获取附近培训机构", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/getNearbyInst", method = RequestMethod.POST)
    public AjaxReturnMsg getNearbyInst(String gps_lon,String gps_lat) {
        List<Hb65> hb65List = apiNearbyInstService.getNearbyInst(gps_lon,gps_lat);
        return this.success(hb65List);
    }
}
