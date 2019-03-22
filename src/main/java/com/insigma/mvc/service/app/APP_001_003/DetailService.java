package com.insigma.mvc.service.app.APP_001_003;

import com.github.pagehelper.PageInfo;
import com.insigma.mvc.model.train.Ad01;
import com.insigma.mvc.model.train.Hb61;
import com.insigma.mvc.model.train.Hb65;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;

public interface DetailService {

    Hb65 getDetailPlan(Hb65 hb65);

    List<Hb65> getInstPlan(Hb65 hb65);

    List<Hb65> getSimilarPlan(Hb65 hb65);

    //  ������ϸ��Ϣ
    Ad01 getDetailInst(String aab001);//����id

    //  ����רҵ
    List<String> getInstMajor(String aab001);

    //  ������ʦ
    List<Hb61> getInstTea(String aab001);

    //  ��ʦרҵ
    List<String> getTeaQua(String chb061);//��ʦid

    //  �����ļ�
    List<HashMap<String,String>> getInstVideo(String aab001);
}
