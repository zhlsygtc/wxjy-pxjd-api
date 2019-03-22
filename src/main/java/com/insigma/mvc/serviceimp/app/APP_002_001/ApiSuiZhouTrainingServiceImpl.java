package com.insigma.mvc.serviceimp.app.APP_002_001;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.insigma.mvc.dao.app.APP_002_001.ApiSuiZhouTrainingMapper;
import com.insigma.mvc.model.Hc82;
import com.insigma.mvc.model.SFileRecord;
import com.insigma.mvc.model.train.*;
import com.insigma.mvc.service.app.APP_002_001.ApiSuiZhouTrainingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ApiSuiZhouTrainingServiceImpl implements ApiSuiZhouTrainingService {
    @Autowired
    ApiSuiZhouTrainingMapper trainingMapper;

    @Override
    public Hb61 getLoginPerson(Hb61 hb61) {
        return trainingMapper.getLoginPerson(hb61);
    }

    @Override
    public void updateLoginInfo(Hb61 hb61) {
        trainingMapper.updateLoginInfo(hb61);
    }

    @Override
    public void quitSystem(String aac002) {
        trainingMapper.quitSystem(aac002);
    }

    @Override
    public Hb61 getTeacher(String chb061) {
        return trainingMapper.getTeacher(chb061);
    }

    @Override
    public Hb68 getCurrentClass(String aac002) {
        return trainingMapper.getCurrentClass(aac002);
    }

    @Override
    public Hb69 getCurrentCourse(String aac002) {
        return trainingMapper.getCurrentCourse(aac002);
    }

    @Override
    public Hb69 getNextCourse(String aac002) {
        return trainingMapper.getNextCourse(aac002);
    }

    @Override
    public void updatePassword(Hb61 hb61) {
        trainingMapper.updatePassword(hb61);
    }

    @Override
    public Hb68 getCurrentClassDetail(String chb068) {
        return trainingMapper.getCurrentClassDetail(chb068);
    }

    @Override
    public PageInfo<Hb69> getCurrentClassCourseList(Hb69 hb69) {
        PageHelper.startPage(hb69.getCurpage(), hb69.getLimit());
        List<Hb69> hb69List = trainingMapper.getCurrentClassCourseList(hb69.getChb068());
        for (Hb69 hb691 : hb69List) {
            int count = trainingMapper.getTrainingState(hb691.getChb069());
            if (count == 0) {
                hb691.setState(false);
            } else {
                hb691.setState(true);
            }
        }
        PageInfo<Hb69> pageinfo = new PageInfo<>(hb69List);
        return pageinfo;
    }

    @Override
    public Hb69DTO getCurrentCourseDetail(String chb069) {
        return trainingMapper.getCurrentCourseDetail(chb069);
    }

    @Override
    public Integer getTrainingNum(String chb068) {
        return trainingMapper.getTrainingNum(chb068);
    }

    @Override
    public List<Hb69> getTrainingVideoInfo(String chb069) {
        return trainingMapper.getTrainingVideoInfo(chb069);
    }

    @Override
    public List<Hc61Dto> getStudents(String chb069) {
        return trainingMapper.getStudents(chb069);
    }

    @Override
    public List<Hc61Dto> getStudentInfoList(String chb069) {
        return trainingMapper.getStudentInfoList(chb069);
    }

    @Override
    public List<Hb68> getPastClassList(String aac002) {
        return trainingMapper.getPastClassList(aac002);
    }

    @Override
    public Hc82 getVideoRecordInfo(String aac002) {
        return trainingMapper.getVideoRecordInfo(aac002);
    }

    @Override
    public void updateVideoRecordInfo(String id) {
        trainingMapper.updateVideoRecordInfo(id);
    }

    @Override
    public void updateSFileRecord(SFileRecord sFileRecord) {
        trainingMapper.updateSFileRecord(sFileRecord);
    }
}
