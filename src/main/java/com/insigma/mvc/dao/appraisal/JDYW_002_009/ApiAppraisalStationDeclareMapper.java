package com.insigma.mvc.dao.appraisal.JDYW_002_009;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.insigma.mvc.model.appraisal.Hb74;
import com.insigma.mvc.model.appraisal.Hb74Temp_Short;
import com.insigma.mvc.model.appraisal.Hb75Temp;
import com.insigma.mvc.model.appraisal.Hc60Temp_Short;


public interface ApiAppraisalStationDeclareMapper {
	
	List<Hb74Temp_Short> getAppraisalSpeciaList(Hb74Temp_Short hb74Temp_Short);

	Hb74 getAppraisalInfo(String chb140);

	String getSQ_HB74_CHB140();

	int insertAppraisalPlan(Hb74 hb74);

	Hb74 getcheckAppraisalPlan(@Param(value="chb140")String chb140, @Param(value="aae100")String aae100);

	int saveAppraisBatchHb74(@Param(value="hb75map")Map hb75map);

	int saveAppraisBatchHc63(@Param(value="hb75map")Map hb75map);

	List<Hb75Temp> getAppraisalDeclareist(Hb75Temp hb75Temp);

	List<Hc60Temp_Short> getAppraisThisStudentList(Hc60Temp_Short hc60Temp_Short);

	int savePerponSubmit(Hb74 hb74);

	int savePerponSum(@Param(value="chb140")String chb140, @Param(value="aae100")String aae100);

	int delAppraisalDeclare(Hb74 hb74);

	int delAppraisalDeclareBatch(@Param(value="hb74map")Map hb74map);

	int delAppraisAllEmptyHb74(@Param(value="hb74map")Map hb74map);

	int delAppraisAllEmptyHc63(@Param(value="hb74map")Map hb74map);
}
