package com.insigma.mvc.controller.app.APP_001_005;

import javax.annotation.Resource;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageInfo;
import com.insigma.dto.AjaxReturnMsg;
import com.insigma.mvc.MvcHelper;
import com.insigma.mvc.model.train.Hb65;
import com.insigma.mvc.model.train.Hc61;
import com.insigma.mvc.service.app.APP_001_001.ApiCommonService;
import com.insigma.mvc.service.app.APP_001_005.ApiCollectionService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/collection")
public class ApiCollectionController extends MvcHelper{
	
	@Resource
	ApiCollectionService apiCollectionService;
	@Resource
	ApiCommonService apiCommonService;

	/**
	 * 获取培训收藏信息列表
	 * @param hb65
	 * @return
	 */
	@ApiOperation(value = "获取培训收藏信息列表", notes = "获取培训收藏信息列表")
    @RequestMapping(value = "/getCollectionList", method = RequestMethod.POST)
	public AjaxReturnMsg getCollectionList(@ModelAttribute Hb65 hb65) throws Exception {
		Hc61 hc61 = apiCommonService.getStudentInfo(hb65);
		hb65.setChc111(hc61.getChc111());
		PageInfo<Hb65> pageinfo = apiCollectionService.getCollectionList(hb65);
		return this.success(pageinfo);
	}
	
	/**
	 * 收藏计划详情界面
	 * @param hb65 计划对象
	 * @return
	 */
	@ApiOperation(value = "收藏计划详情界面", notes = "收藏计划详情界面", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/getPlanInfo", method = RequestMethod.POST)
    public AjaxReturnMsg getPlanInfo(@ModelAttribute Hb65 hb65) throws Exception {
        return apiCollectionService.getPlanInfo(hb65);
    }
	
	/**
     * 收藏计划
     * @param hb65 计划对象
     * @throws Exception
     */
    @ApiOperation(value = "收藏计划", notes = "收藏计划", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/doCollect", method = RequestMethod.POST)
    public AjaxReturnMsg doCollect(@ModelAttribute Hb65 hb65) throws Exception {
        return apiCollectionService.doCollect(hb65);
    }
	
    
}
