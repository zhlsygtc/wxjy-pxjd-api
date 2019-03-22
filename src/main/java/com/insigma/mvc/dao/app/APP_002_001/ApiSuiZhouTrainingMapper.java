package com.insigma.mvc.dao.app.APP_002_001;

import com.insigma.mvc.model.Hc82;
import com.insigma.mvc.model.SFileRecord;
import com.insigma.mvc.model.train.*;

import java.util.List;

public interface ApiSuiZhouTrainingMapper {
    Hb61 getLoginPerson(Hb61 hb61);

    void updateLoginInfo(Hb61 hb61);

    void quitSystem(String aac002);

    Hb61 getTeacher(String chb061);

    Hb68 getCurrentClass(String aac002);

    Hb69 getCurrentCourse(String aac002);

    Hb69 getNextCourse(String aac002);

    void updatePassword(Hb61 hb61);

    Hb68 getCurrentClassDetail(String chb068);

    List<Hb69> getCurrentClassCourseList(String chb068);

    Hb69DTO getCurrentCourseDetail(String chb069);

    Integer getTrainingNum(String chb068);

    Integer getTrainingState(String chb069);

    List<Hb69> getTrainingVideoInfo(String chb069);

    List<Hc61Dto> getStudents(String chb069);

    List<Hc61Dto> getStudentInfoList(String chb069);

    List<Hb68> getPastClassList(String aac002);

    Hc82 getVideoRecordInfo(String aac002);

    void updateVideoRecordInfo(String id);

    void updateSFileRecord(SFileRecord sFileRecord);
}
