package com.insigma.mvc.dao.appraisal.JDYW_002_012;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.insigma.mvc.model.Hc63;
import com.insigma.mvc.model.appraisal.Hb74;
import com.insigma.mvc.model.appraisal.Hb74Temp_Short;
import com.insigma.mvc.model.appraisal.Hb77;
import com.insigma.mvc.model.appraisal.Hb79;
import com.insigma.mvc.model.appraisal.Hc73;
import com.insigma.mvc.model.appraisal.Hc73Temp_Short;


public interface ApiPersondispatchMapper {
	
	List<Hb74Temp_Short> getPersondispatchList(Hb74Temp_Short hb74Temp_Short);

	List<Hb77> getExamRoomList(Hb77 hb77);

	Hb74 getAppraisalInfo(String chb140);

	int examRoomInsert(Hb77 hb77);

	int examRoomSave(Hb77 hb77);

	List<Hc63> getExamRoomPerponList(Hc63 hc63);

	List<Hc63> getExamRoomPerponSelectList(Hc63 hc63);

	Hb77 getExamRoomInfo(@Param(value="chb077")String chb077, @Param(value="aae100")String aae100);

	List<String> getseatnumber(Hb77 hb77);

	int savePerponExamHc63Batch(@Param(value="hc63Map")Map hc63Map);

	int deletePerponExamHc63Batch(@Param(value="hc63Map")Map hc63Map);

	int getcountHb74CHB341(@Param(value="chb140")String chb140, @Param(value="examtype")String examtype, @Param(value="aae100")String aae100);

	int saveHb74Chb341(@Param(value="chb140")String chb140, @Param(value="chb341")String chb341, @Param(value="aae100")String aae100, @Param(value="chb146")String chb146, @Param(value="chb326")String chb326);

	Hb77 getExamRoomBaseInfo(@Param(value="chb077")String chb077, @Param(value="aae100")String aae100);

	int deletePerponExamRoomBatch(@Param(value="hc63Map")Map hc63Map);
	
	int deleteExamRoomBatch(@Param(value="hc63Map")Map hc63Map);

	List<Hc73Temp_Short> getAppraiserList(Hc73Temp_Short hc73Temp_Short);

	int saveAppraisCorePerson(@Param(value="hb79list")List hb79list);

	int saveAppraisPersonStatus(@Param(value="chb140")String chb140, @Param(value="chb17c")String chb17c, @Param(value="chb146")String chb146, @Param(value="aae100")String aae100);

	int deleteAppraisCorePerson(@Param(value="chb140")String chb140, @Param(value="aae100")String aae100);

	List<Hc73> getAppraisCorePersonList(@Param(value="chb140")String chb140, @Param(value="chb077")String chb077, @Param(value="aab001")String aab001, @Param(value="aae100")String aae100);

	List<String> getAca109List(@Param(value="chb077")String chb077, @Param(value="examtype")String examtype, @Param(value="aae100")String aae100);
}
