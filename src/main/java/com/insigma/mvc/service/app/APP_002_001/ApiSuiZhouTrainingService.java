package com.insigma.mvc.service.app.APP_002_001;

import com.github.pagehelper.PageInfo;
import com.insigma.mvc.model.Hc82;
import com.insigma.mvc.model.SFileRecord;
import com.insigma.mvc.model.train.*;

import java.util.List;

public interface ApiSuiZhouTrainingService {
    Hb61 getLoginPerson(Hb61 hb61);

    void updateLoginInfo(Hb61 hb61);
    void quitSystem(String aac002);

    Hb61 getTeacher(String chb061);

    Hb68 getCurrentClass(String aac002);
    Hb69 getCurrentCourse(String aac002);
    Hb69 getNextCourse(String aac002);
    void updatePassword(Hb61 hb61);
    Hb68 getCurrentClassDetail(String chb068);
    PageInfo<Hb69> getCurrentClassCourseList(Hb69 hb69);
    Hb69DTO getCurrentCourseDetail(String chb069);
    Integer getTrainingNum(String chb068);
    List<Hb69> getTrainingVideoInfo(String chb069);
    List<Hc61Dto> getStudents(String chb069);
    List<Hc61Dto> getStudentInfoList(String chb069);
    List<Hb68> getPastClassList(String aac002);
    Hc82 getVideoRecordInfo(String aac002);
    void updateVideoRecordInfo(String id);
    void updateSFileRecord(SFileRecord sFileRecord);

}
