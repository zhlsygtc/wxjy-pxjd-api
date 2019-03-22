package com.insigma.mvc.dao.app.APP_001_009;

import com.insigma.mvc.model.train.Hb65;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ApiSearchMapper {
    List<Hb65> getSearchResult(@Param("chc111") String chc111, @Param("param") String param,@Param("fore") String fore);
}
