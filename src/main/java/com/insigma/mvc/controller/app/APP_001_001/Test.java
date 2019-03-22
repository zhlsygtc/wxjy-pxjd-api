package com.insigma.mvc.controller.app.APP_001_001;

import com.insigma.mvc.dao.app.APP_001_001.TestMapper;
import com.insigma.mvc.model.train.Hc61;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Test {
    @Autowired
    TestMapper testMapper;

    public Hc61 getHc61(String testId){
        return testMapper.getStudentInfo(testId);
    }
}
