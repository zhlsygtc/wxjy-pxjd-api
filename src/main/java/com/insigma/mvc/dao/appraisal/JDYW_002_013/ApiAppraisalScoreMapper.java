package com.insigma.mvc.dao.appraisal.JDYW_002_013;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.insigma.mvc.model.Hc63;
import com.insigma.mvc.model.appraisal.Hb74;
import com.insigma.mvc.model.appraisal.Hb74Temp_Short;
import com.insigma.mvc.model.appraisal.Hc64;


public interface ApiAppraisalScoreMapper {
	
	List<Hb74Temp_Short> getAppraisalScoreList(Hb74Temp_Short hb74Temp_Short);

	List<Hc63> getExamineeList(Hc63 hc63);

	int saveExamineeInfo(Hc64 hc64);

	int insertExamineeInfo(Hc64 hc64);

	int getCountChc018(@Param(value="chb140")String chb140, @Param(value="chc018")String chc018, @Param(value="aae100")String aae100);
	
	int saveHb74(Hb74 hb74);

	int insertExcelTempData(@Param(value="list")List<Hc64> hc64list);

	Hb74 countHc64Chc014(@Param(value="chb140")String chb140, @Param(value="aae100")String aae100);

	Hb74Temp_Short getAppraisalScoreInfo(@Param(value="chb140")String chb140, @Param(value="aae100")String aae100);

	int saveSubmitScore(@Param(value="chb140")String chb140, @Param(value="chb146")String chb146, @Param(value="chb355")String chb355);
}
