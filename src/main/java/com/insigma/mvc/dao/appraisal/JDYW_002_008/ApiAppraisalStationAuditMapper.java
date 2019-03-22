package com.insigma.mvc.dao.appraisal.JDYW_002_008;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.insigma.mvc.model.CodeValue;
import com.insigma.mvc.model.Hc63;
import com.insigma.mvc.model.appraisal.Hb75;
import com.insigma.mvc.model.appraisal.Hb75Temp;
import com.insigma.mvc.model.appraisal.Hc60Temp_Short;
import com.insigma.mvc.model.appraisal.Hc63Temp_Short;


public interface ApiAppraisalStationAuditMapper {
	
	List<Hb75Temp> getAppraisalSpeciaList(Hb75Temp hb75Temp);

	Hb75 getAppraisalInfo(String chb120);

	List<Hc60Temp_Short> getAppraisStudentNotSubmitList(Hc60Temp_Short hc60Temp_Short);

	List<Hc60Temp_Short> getAppraisThisStudentList(Hc60Temp_Short hc60Temp_Short);

	int saveAppraisBatch(Hb75 hb75);
	
	int saveStudentBatch(Hc63 hc63);

	Hc63Temp_Short getPerponAuditInfo(String chc063);

	int savePerponAudit(Hc63Temp_Short hc63Temp_Short);

	int savePerponInfoAuditBath(@Param(value="hc60map")Map hc60map);

	Hc63Temp_Short getPerponAuditIsPass(@Param(value="chb120")String chb120, @Param(value="chb312")String chb312, @Param(value="aae100")String aae100);

	List<CodeValue> getTrainComName(CodeValue codevalue);
}
