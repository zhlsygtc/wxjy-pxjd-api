package com.insigma.mvc.dao.appraisal.JDYW_002_004;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.insigma.mvc.model.Hc63;
import com.insigma.mvc.model.appraisal.Hb75;
import com.insigma.mvc.model.appraisal.Hb75Temp;
import com.insigma.mvc.model.appraisal.Hc60Dto_Short;
import com.insigma.mvc.model.appraisal.Hc60Temp_Short;
import com.insigma.mvc.model.appraisal.Hc68Temp_Short;


public interface ApiAppraisalSpeciaMapper {
	
	List<Hb75Temp> getAppraisalSpeciaList(Hb75Temp hb75Temp);

	Hb75 getAppraisalInfo(String chb120);

	List<Hc60Temp_Short> getAppraisStudentSubmitList(Hc60Dto_Short hc60Dto_Short);

	List<Hc60Temp_Short> getAppraisStudentNotSubmitList(Hc60Temp_Short hc60Temp_Short);

	List<Hc60Temp_Short> getAppraisThisStudentList(Hc60Temp_Short hc60Temp_Short);

	List<Hc68Temp_Short> getAppraisClassList(Hc68Temp_Short hc68Temp_Short);
	
	List<Hc68Temp_Short> getAppraisClassListForLook(Hc68Temp_Short hc68Temp_Short);

	String getSQ_HB75_CHB120();

	int addAppraisBatch(Hb75 hb75);

	int saveAppraisBatch(Hb75 hb75);
	
	int savePerponDataBath(@Param(value="hc63map")Map hc63map);

	int saveStudentBatch(Hc63 hc63);

	int deletePerponDataBatch(@Param(value="aae100")String aae100, @Param(value="chb120")String chb120, @Param(value="chb068")String chb068);
	
	int saveStudentHc63Batch(@Param(value="hc75map")Map hc75map);

	int deletebyid(Hb75 hb75);
	
	int deletebyBatch(@Param(value="hc75map")Map hc75map);

	int saveStudentBatchStatus(Hc63 hc63);
}
