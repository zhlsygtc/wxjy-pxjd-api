package com.insigma.mvc.service.appraisal.JDYW_002_013;

import com.github.pagehelper.PageInfo;
import com.insigma.dto.AjaxReturnMsg;
import com.insigma.mvc.model.Hc63;
import com.insigma.mvc.model.appraisal.Hb74Temp_Short;
import com.insigma.mvc.model.appraisal.Hc64;

public interface ApiAppraisalScoreService {

	/**
	 * 查询考场编排阶段批次信息
	* @author: liangy  
	* @date 2018年12月19日
	* @param @param hb74Temp_Short
	* @param @return    
	* @return PageInfo<Hb74Temp_Short>   
	* @throws
	 */
	PageInfo<Hb74Temp_Short> getAppraisalScoreList(Hb74Temp_Short hb74Temp_Short);

	/**
	 * 查询当前批次人员成绩信息
	* @author: liangy  
	* @date 2019年3月5日
	* @param @param Hc63
	* @param @return    
	* @return PageInfo<Hc63>   
	* @throws
	 */
	PageInfo<Hc63> getExamineeList(Hc63 hc63);

	/**
	 * 单个修改学员成绩信息
	* @author: liangy  
	* @date 2019年3月5日
	* @param @param hc64
	* @param @return    
	* @return AjaxReturnMsg   
	* @throws
	 */
	AjaxReturnMsg saveExamineeInfo(Hc64 hc64);

	/**
	 * 成绩提交
	* @author: liangy  
	* @date 2019年3月11日
	* @param @param hb74
	* @param @return    
	* @return AjaxReturnMsg   
	* @throws
	 */
	AjaxReturnMsg saveSubmitScore(Hb74Temp_Short hb74Temp_Short);
}
