package com.insigma.mvc.dao.appraisal.JDYW_002_011;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.insigma.mvc.model.Hc63;
import com.insigma.mvc.model.appraisal.Hb70;
import com.insigma.mvc.model.appraisal.Hb74;
import com.insigma.mvc.model.appraisal.Hb74Temp_Short;
import com.insigma.mvc.model.appraisal.Hb76;
import com.insigma.mvc.model.appraisal.Hb77;
import com.insigma.mvc.model.appraisal.Hb78;


public interface ApiExamRoomArrangeMapper {
	
	List<Hb74Temp_Short> getAppraisalSpeciaList(Hb74Temp_Short hb74Temp_Short);

	List<Hb77> getExamRoomList(Hb77 hb77);

	List<Hb77> getExamRoomSpaceList(@Param(value="chb070")String chb070, @Param(value="aae100")String aae100);

	List<Hb70> getExamRoomBatchList(Hb70 hb70);

	Hb74 getAppraisalInfo(String chb140);

	int examRoomBatchInsert(Hb70 hb70);

	int examRoomBatchSave(Hb70 hb70);

	int examRoomInsert(Hb77 hb77);

	int examRoomSave(Hb77 hb77);

	List<Hc63> getExamRoomPerponList(Hc63 hc63);

	List<Hc63> getExamRoomPerponSelectList(Hc63 hc63);

	Hb77 getExamRoomInfo(@Param(value="chb077")String chb077, @Param(value="aae100")String aae100);

	List<String> getseatnumber(Hb77 hb77);

	int savePerponExamHb71Batch(@Param(value="hc63Map")Map hc63Map);

	int deletePerponExamHb71Batch(@Param(value="hc63Map")Map hc63Map);

	int getcountHb74CHB341(@Param(value="chb140")String chb140, @Param(value="examtype")String examtype, @Param(value="aae100")String aae100);

	int saveHb74Chb341(@Param(value="chb140")String chb140, @Param(value="chb341")String chb341, @Param(value="aae100")String aae100, @Param(value="chb146")String chb146);

	Hb70 getExamRoomBaseBatchInfo(@Param(value="chb070")String chb070, @Param(value="aae100")String aae100);

	Hb77 getExamRoomBaseInfo(@Param(value="chb077")String chb077, @Param(value="aae100")String aae100);

	int deletePerponExamRoomBatch(@Param(value="hc63Map")Map hc63Map);
	
	int deleteExamRoomBatch(@Param(value="hc63Map")Map hc63Map);

	List<Hb76> getTestCenterList(@Param(value="aab001")String aab001, @Param(value="aae100")String aae100);

	List<Hb78> getExamRoomSelectList(@Param(value="chb076")String chb076, @Param(value="aae100")String aae100);

	int saveBatchExamination(Hb70 hb70);

	/**
	 * 删除考试批次
	* @author: liangy  
	* @date 2019年1月28日
	* @param @param hb70map
	* @param @return    
	* @return int   
	* @throws
	 */
	int deleteExaminationBatch(@Param(value="hb70map")Map hb70map);

	/**
	 * 根据考试批次删除考试记录
	* @author: liangy  
	* @date 2019年1月28日
	* @param @param hb70map
	* @param @return    
	* @return int   
	* @throws
	 */
	int deleteExaminationRecord(@Param(value="hb70map")Map hb70map);

	/**
	 * 根据考试批次清空考试人员 
	* @author: liangy  
	* @date 2019年1月28日
	* @param @param hb70map
	* @param @return    
	* @return int   
	* @throws
	 */
	int deleteExaminationPerpon(@Param(value="hb70map")Map hb70map);

	/**
	 * 统计当前批次人数、当前批次未编排人数 
	* @author: liangy  
	* @date 2019年1月30日
	* @param @param chb140  鉴定批次编号
	* @param @param chb070 考试批次编号(为空时 统计所有批次人员)
	* @param @param aae100 数据有效代码
	* @param @param notaae100 数据无效代码
	* @param @return    
	* @return int   
	* @throws
	 */
	int getStatisPerponNumber(@Param(value="chb140")String chb140, @Param(value="chb070")String chb070, @Param(value="aae100")String aae100, @Param(value="notaae100")String notaae100);
}
