package com.insigma.mvc.controller.app.APP_001_003;

import com.github.pagehelper.PageInfo;
import com.insigma.dto.AjaxReturnMsg;
import com.insigma.mvc.MvcHelper;
import com.insigma.mvc.model.train.Ad01;
import com.insigma.mvc.model.train.Hb61;
import com.insigma.mvc.model.train.Hb65;
import com.insigma.mvc.service.app.APP_001_003.DetailService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/index")
public class DetailController extends MvcHelper {
    @Autowired
    DetailService detailService;


    @ApiOperation(value = "获取详细计划信息", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/getDetailPlan", method = RequestMethod.POST)
    public AjaxReturnMsg getDetailPlan(Hb65 hb651) throws Exception {
        Map<String, Object> map = new HashMap<>();
        Hb65 hb65 = detailService.getDetailPlan(hb651);
        List<Hb65> hb65List = detailService.getSimilarPlan(hb651);
        map.put("hb65", hb65);
        map.put("hb65List", hb65List);
        return this.success(map);
    }

    @ApiOperation(value = "获取机构详细信息", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/getDetailInst", method = RequestMethod.POST)
    public AjaxReturnMsg getDetailInst(Hb65 hb65) throws Exception {
        String aab001 = hb65.getAab001();
        Map<String, Object> map = new HashMap<>();

        Ad01 ad01 = detailService.getDetailInst(aab001);//机构

        List<String> majorList = detailService.getInstMajor(aab001);//工种
        ad01.setMajorString(majorList.toArray(new String[majorList.size()]));

        List<Hb61> teaList = detailService.getInstTea(aab001);//老师
        for (Hb61 hb61 : teaList) {
            List<String> quaList = detailService.getTeaQua(hb61.getChb061());//老师的工种
            hb61.setA_aca111(quaList.toArray(new String[quaList.size()]));
        }

        List<HashMap<String,String>> file_url = detailService.getInstVideo(aab001);//文件

        List<Hb65> hb65List = detailService.getInstPlan(hb65);
        map.put("ad01", ad01);
        map.put("teaList", teaList);
        map.put("hb65List", hb65List);
        map.put("file_url", file_url);
        return this.success(map);
    }
}
