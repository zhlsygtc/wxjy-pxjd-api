package com.insigma.mvc.service.app.APP_001_002;

import com.github.pagehelper.PageInfo;
import com.insigma.dto.AjaxReturnMsg;
import com.insigma.mvc.model.train.Hb65;

import java.util.List;

public interface IndexService {

    PageInfo<Hb65> getAllPlan(Hb65 hb65);

	AjaxReturnMsg getBannerList();
}
