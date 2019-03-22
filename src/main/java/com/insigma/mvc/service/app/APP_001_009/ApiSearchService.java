package com.insigma.mvc.service.app.APP_001_009;

import com.github.pagehelper.PageInfo;
import com.insigma.mvc.model.train.Hb65;

public interface ApiSearchService {
    PageInfo<Hb65> getSearchResult (Hb65 hb65);
}
