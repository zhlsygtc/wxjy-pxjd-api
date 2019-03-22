package com.insigma.mvc.dao.app.APP_001_002;

import com.insigma.mvc.model.SBanner;
import com.insigma.mvc.model.train.Hb65;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface IndexMapper {


    /**
     * �ֻ��˲�ѯ������ѵ�ƻ�
     *
     * @throws Exception
     */
    List<Hb65> getAllPlan(@Param("fore") String fore, @Param("chc111") String chc111,@Param("userId") String userId);

    /**
     * ��ȡ��ѵBANNER
     * @return
     */
	List<SBanner> getBannerList(@Param("type") String type,@Param("fileModule") String fileModule);


}
