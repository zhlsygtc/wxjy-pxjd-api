package com.insigma.mvc.dao.appraisal.JDYW_002_006;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.insigma.mvc.model.appraisal.Hc73;
import com.insigma.mvc.model.appraisal.Hc75;
import com.insigma.mvc.model.train.Hb68;
import com.insigma.mvc.model.train.Hc74;


public interface ApiCareerAssessorManageMapper {
	List<Hc73> getCareerAssessorList(Hc73 hc73 );

	Hc73 getCareerAssessorById(@Param(value="chc073")String chc073, @Param(value="aab001")String aab001, @Param(value="aae100")String aae100, @Param(value="cac022")String cac022);

	List<Hc73> checkAac002(@Param(value="aac002")String aac002, @Param(value="aae100")String aae100);

	int addCareerAssessor(Hc73 hc73);

	int updateCareerAssessor(Hc73 hc73);

	int deleteCareerAssessor(String chb061);

	int deleteQualification(String chc074);

	int saveQualification(Hc74 hc74);

	int updateQualification(Hc74 hc74);

	List<Hc74> getQualificationList(String chb061);

	int deleteQualifications(String[] chb061s);

	List<Hc74> checkQualification(Hc74 hc74);

	Hc74 getQualification(String chc074);

	int batDelete(String[] ids);

	Hc73 getCareerAssessorGBKById(@Param(value="chc073")String chc073, @Param(value="aab001")String aab001, @Param(value="aae100")String aae100, @Param(value="cac022")String cac022);

	Hc73 getIdcardInfo(@Param(value="aac002")String aac002, @Param(value="aae100")String aae100);

	List<Hb68> getCareerAssessorClasses(Hc73 hc73);

	int insertAppointment(Hc75 hc75);
	
	int updateAppointment(Hc75 hc75);

	Integer getCheckAppointmentPresence(Hc73 hc73);

	int updatePerponIdentity(Hc73 hc73);

	int deleteEmployment(@Param(value="chc073")String chc073, @Param(value="aab001")String aab00, @Param(value="aae100")String aae100, @Param(value="cac022")String cac022);

	int batDeleteEmployment(@Param(value="hc73Map")Map hc73Map);
}
