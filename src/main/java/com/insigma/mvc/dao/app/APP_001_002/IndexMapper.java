package com.insigma.mvc.dao.app.APP_001_002;

import com.insigma.mvc.model.SBanner;
import com.insigma.mvc.model.train.Hb65;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface IndexMapper {


    /**
     * 手机端查询所有培训计划
     *
     * @throws Exception
     */
    List<Hb65> getAllPlan(@Param("fore") String fore, @Param("chc111") String chc111,@Param("userId") String userId);

    /**
     * 获取培训BANNER
     * @return
     */
	List<SBanner> getBannerList(@Param("type") String type,@Param("fileModule") String fileModule);


}
