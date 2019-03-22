package com.insigma.mvc.controller.app.APP_001_002;

import com.github.pagehelper.PageInfo;
import com.insigma.dto.AjaxReturnMsg;
import com.insigma.mvc.MvcHelper;
import com.insigma.mvc.controller.app.APP_001_001.Test;
import com.insigma.mvc.model.train.Hb65;
import com.insigma.mvc.model.train.Hc61;
import com.insigma.mvc.service.app.APP_001_001.ApiCommonService;
import com.insigma.mvc.service.app.APP_001_002.IndexService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/index")
public class IndexController extends MvcHelper {
    @Autowired
    ApiCommonService apiCommonService;
    @Autowired
    IndexService indexService;
    @Autowired
    Test test;

    @ApiOperation(value = "获取开班计划前五条信息", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/getAllPlan", method = RequestMethod.POST)
    public AjaxReturnMsg getAllPlan(Hb65 hb65) {
        Hc61 hc61 = apiCommonService.getStudentInfo(hb65);
//        Hc61 hc61 = test.getHc61(hb65.getTestId());
        hb65.setChc111(hc61.getChc111());
//        hb65.setUserId(hb65.getTestId());
        PageInfo<Hb65> pageInfo = indexService.getAllPlan(hb65);
        return this.success(pageInfo);
    }
    
    /**
     * 获取培训BANNER
     * @return
     */
    @ApiOperation(value = "获取培训BANNER", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/getBannerList", method = RequestMethod.POST)
    public AjaxReturnMsg getBannerList() {
        return indexService.getBannerList();
    }


}
