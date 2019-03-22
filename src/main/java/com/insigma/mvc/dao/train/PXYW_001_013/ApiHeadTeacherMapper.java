package com.insigma.mvc.dao.train.PXYW_001_013;

import com.insigma.mvc.model.train.Hb57;
import com.insigma.mvc.model.train.Hb68;

import java.util.List;


public interface ApiHeadTeacherMapper {

	List<Hb57> getHeadTeacherList(Hb57 hb57);

	Hb57 getHeadTeacherById(String chb057);

	Hb57 getHeadTeacherByUserId(String userid);

	int checkAac002(Hb57 hb57);

	int addHeadTeacher(Hb57 hb57);

	int updHeadTeacher(Hb57 hb57);

	int deleteHeadTeacherById(String chb057);

	int deleteHeadTeacherByIds(String[] ids);

	List<Hb68> getHeadTeacherClasses(Hb57 hb57);
	
}
