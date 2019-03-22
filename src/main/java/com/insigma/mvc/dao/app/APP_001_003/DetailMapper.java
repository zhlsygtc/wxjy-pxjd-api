package com.insigma.mvc.dao.app.APP_001_003;

import com.insigma.mvc.model.train.Ad01;
import com.insigma.mvc.model.train.Hb61;
import com.insigma.mvc.model.train.Hb65;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;

public interface DetailMapper {
    /**
     * �ֻ�����ѵ�ƻ���ϸ��Ϣ
     *
     * @param chb055
     * @return
     */
    Hb65 getDetailPlan(@Param("fore") String fore, @Param("chb055") String chb055,@Param("userid") String userid);

    /**
     * �����ڵ���ѵ�ƻ�
     *
     * @param aab001
     * @return
     */
    List<Hb65> getInstPlan(@Param("fore") String fore, @Param("aab001") String aab001);

    List<Hb65> getSimilarPlan(@Param("fore") String fore, @Param("aca111") String aca111);

    //    ������ϸ��Ϣ
    Ad01 getDetailInst(@Param("fore") String fore, @Param("aab001") String aab001);//����id

    //  ����רҵ
    List<String> getInstMajor(String aab001);

    //  ������ʦ
    List<Hb61> getInstTea(@Param("fore") String fore, @Param("aab001") String aab001);

    //  ��ʦרҵ
    List<String> getTeaQua(String chb061);//��ʦid

    //  �����ļ�
    List<HashMap<String, String>> getInstFile(@Param("fore") String fore, @Param("aab001") String aab001);
}
