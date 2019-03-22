package com.insigma.mvc.dao.app.APP_001_003;

import com.insigma.mvc.model.train.Ad01;
import com.insigma.mvc.model.train.Hb61;
import com.insigma.mvc.model.train.Hb65;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;

public interface DetailMapper {
    /**
     * 手机端培训计划详细信息
     *
     * @param chb055
     * @return
     */
    Hb65 getDetailPlan(@Param("fore") String fore, @Param("chb055") String chb055,@Param("userid") String userid);

    /**
     * 机构内的培训计划
     *
     * @param aab001
     * @return
     */
    List<Hb65> getInstPlan(@Param("fore") String fore, @Param("aab001") String aab001);

    List<Hb65> getSimilarPlan(@Param("fore") String fore, @Param("aca111") String aca111);

    //    机构详细信息
    Ad01 getDetailInst(@Param("fore") String fore, @Param("aab001") String aab001);//机构id

    //  机构专业
    List<String> getInstMajor(String aab001);

    //  机构老师
    List<Hb61> getInstTea(@Param("fore") String fore, @Param("aab001") String aab001);

    //  老师专业
    List<String> getTeaQua(String chb061);//老师id

    //  机构文件
    List<HashMap<String, String>> getInstFile(@Param("fore") String fore, @Param("aab001") String aab001);
}
