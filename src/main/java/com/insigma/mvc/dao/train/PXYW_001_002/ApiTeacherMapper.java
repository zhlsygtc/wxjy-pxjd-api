package com.insigma.mvc.dao.train.PXYW_001_002;

import java.util.List;

import com.insigma.mvc.model.train.Hb61;
import com.insigma.mvc.model.train.Hb68;
import com.insigma.mvc.model.train.Hc74;


public interface ApiTeacherMapper {
	
	List<Hb61> getTeacherList(Hb61 hb61 );

	Hb61 getTeacherById(String id);

	List<Hb61> checkAac002(Hb61 hb61);

	int addTeacher(Hb61 hb61);

	int updateTeacher(Hb61 hb61);

	int deleteTeacher(String chb061);

	int deleteQualification(String chc074);

	int saveQualification(Hc74 hc74);

	int updateQualification(Hc74 hc74);

	List<Hc74> getQualificationList(String chb061);

	int deleteQualifications(String[] chb061s);

	List<Hc74> checkQualification(Hc74 hc74);

	Hc74 getQualification(String chc074);

	int batDelete(String[] ids);

	Hb61 getTeacherGBKById(String chb061);

	List<Hb68> getTeacherClasses(Hb61 hb61);
	
}
