package com.insigma.mvc.controller.app.APP_001_009;

import com.github.pagehelper.PageInfo;
import com.insigma.dto.AjaxReturnMsg;
import com.insigma.mvc.MvcHelper;
import com.insigma.mvc.controller.app.APP_001_001.Test;
import com.insigma.mvc.model.train.Hb65;
import com.insigma.mvc.model.train.Hc61;
import com.insigma.mvc.service.app.APP_001_001.ApiCommonService;
import com.insigma.mvc.service.app.APP_001_009.ApiSearchService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/search")
public class ApiSearchController extends MvcHelper {
    @Autowired
    ApiSearchService apiSearchService;
    @Autowired
    ApiCommonService apiCommonService;
    @Autowired
    Test test;

    @ApiOperation(value = "获取搜索结果", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/getSearchResult", method = RequestMethod.POST)
    public AjaxReturnMsg getSearchResult(Hb65 hb65) throws Exception {
        Hc61 hc61 = apiCommonService.getStudentInfo(hb65);
//        Hc61 hc61 = test.getHc61(hb65.getTestId());
        hb65.setChc111(hc61.getChc111());
        PageInfo<Hb65> hb65List = apiSearchService.getSearchResult(hb65);
        return this.success(hb65List);
    }
}
