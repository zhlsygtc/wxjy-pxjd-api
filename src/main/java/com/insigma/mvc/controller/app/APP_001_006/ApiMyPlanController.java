package com.insigma.mvc.controller.app.APP_001_006;

import com.github.pagehelper.PageInfo;
import com.insigma.dto.AjaxReturnMsg;
import com.insigma.mvc.MvcHelper;
import com.insigma.mvc.controller.app.APP_001_001.Test;
import com.insigma.mvc.model.train.*;
import com.insigma.mvc.service.app.APP_001_001.ApiCommonService;
import com.insigma.mvc.service.app.APP_001_003.DetailService;
import com.insigma.mvc.service.app.APP_001_006.ApiMyPlanService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/myPlan")
public class ApiMyPlanController extends MvcHelper {
    @Autowired
    ApiMyPlanService apiMyPlanService;
    @Autowired
    ApiCommonService apiCommonService;
    @Autowired
    DetailService detailService;
    @Autowired
    Test test;

    @ApiOperation(value = "获取我的计划信息", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/getMyPlans", method = RequestMethod.POST)
    public AjaxReturnMsg getMyPlans(Hb65 hb65) throws Exception {
        Hc61 hc61 = apiCommonService.getStudentInfo(hb65);
//        Hc61 hc61 = test.getHc61(hb65.getTestId());
        hb65.setChc111(hc61.getChc111());
        PageInfo<Hb65> hb65List = apiMyPlanService.getMyPlans(hb65);
        return this.success(hb65List);
    }

    @ApiOperation(value = "获取我的计划未开班详情页面", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/getMyDetailPlan", method = RequestMethod.POST)
    public AjaxReturnMsg getMyDetailPlan(Hb65 hb651) throws Exception {
        Hb65 hb65 = detailService.getDetailPlan(hb651);
        return this.success(hb65);
    }

    @ApiOperation(value = "获取我的计划已开班详情页面", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/getMyDetailClass", method = RequestMethod.POST)
    public AjaxReturnMsg getMyDetailClass(Hb65 hb65) throws Exception {

        String chb068 = hb65.getChb068();
        String chc111 = hb65.getChc111();
        String chc001 = apiMyPlanService.getChc001(chc111, chb068);
        Map<String, Object> map = new HashMap<>();
        Hb68 hb68 = apiMyPlanService.getMyDetailClass(chb068);



        List<Hb61> hb61List = apiMyPlanService.getMyClassTea(chb068);

        for (Hb61 hb61 : hb61List) {
            List<String> quaList = detailService.getTeaQua(hb61.getChb061());
            hb61.setA_aca111(quaList.toArray(new String[quaList.size()]));
        }

        Hc66 hc66 = apiMyPlanService.getMyScore(chc001, chb068);
        map.put("hb68", hb68);
        map.put("hb61List", hb61List);
        map.put("hc66", hc66);
        return this.success(map);
    }

    @ApiOperation(value = "获取我的计划已开班课表页面", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/getMyDetailCurr", method = RequestMethod.POST)
    public AjaxReturnMsg getMyDetailCurr(Hb69 hb69) throws Exception {
        Map map = new HashMap();
        String chb068 = hb69.getChb068();
        String chc111 = hb69.getChc111();

        String chc001 = apiMyPlanService.getChc001(chc111, chb068);
        hb69.setChc001(chc001);

        PageInfo<String> stringList = apiMyPlanService.getChb160(hb69);
        List<String> strings = stringList.getList();

        Map<String,List<Hb69>> hb69Map = new LinkedHashMap<>();
        for(String str:strings){
            hb69.setChb160(str);
            List<Hb69> hb69List = apiMyPlanService.getMyCourse(hb69);
            hb69Map.put(str,hb69List);
        }
        map.put("pageinfo",stringList);
        map.put("hb69Map",hb69Map);
        return this.success(map);
    }
}
