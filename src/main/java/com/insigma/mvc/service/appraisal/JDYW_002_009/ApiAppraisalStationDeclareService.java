package com.insigma.mvc.service.appraisal.JDYW_002_009;

import com.github.pagehelper.PageInfo;
import com.insigma.dto.AjaxReturnMsg;
import com.insigma.mvc.model.appraisal.Hb74;
import com.insigma.mvc.model.appraisal.Hb74Temp_Short;
import com.insigma.mvc.model.appraisal.Hb75Temp;
import com.insigma.mvc.model.appraisal.Hc60Temp_Short;

public interface ApiAppraisalStationDeclareService {

	PageInfo<Hb74Temp_Short> getAppraisalSpeciaList(Hb74Temp_Short hb74Temp_Short);

	/**
	 * 
	* @author: liangy  
	* @date 2018年11月29日
	* @param @param hb75Temp
	* @param @param operation 移入操作"in"  移出操作"out"
	* @param @return    
	* @return AjaxReturnMsg   
	* @throws
	 */
	AjaxReturnMsg savePerponAudit(Hb75Temp hb75Temp, String operation);

	/**
	 * 查询空闲批次/查询本批次信息列表
	* @author: liangy  
	* @date 2018年11月29日
	* @param @param hb75Temp
	* @param @return    
	* @return PageInfo<Hb75Temp>   
	* @throws
	 */
	PageInfo<Hb75Temp> getAppraisalDeclareist(Hb75Temp hb75Temp);

	/**
	 * 查询鉴定所站详情信息
	* @author: liangy  
	* @date 2018年12月3日
	* @param @param chb140
	* @param @return    
	* @return AjaxReturnMsg   
	* @throws
	 */
	AjaxReturnMsg getAppraisalInfo(String chb140);

	/**
	 * 培训机构批次提交学员信息查询
	* @author: liangy  
	* @date 2018年12月3日
	* @param @param hc60Temp_Short
	* @param @return    
	* @return PageInfo<Hc60Temp_Short>   
	* @throws
	 */
	PageInfo<Hc60Temp_Short> getAppraisThisStudentList(Hc60Temp_Short hc60Temp_Short);

	/**
	 * 保存人员信息
	* @author: liangy  
	* @date 2018年12月3日
	* @param @return    
	* @return int   
	* @throws
	 */
	AjaxReturnMsg savePerponSubmit(Hb74 hb74);

	/**
	 * 鉴定所站鉴定申请单个删除
	* @author: liangy  
	* @date 2018年12月4日
	* @param @param hb74
	* @param @return    
	* @return AjaxReturnMsg   
	* @throws
	 */
	AjaxReturnMsg delAppraisalDeclare(Hb74 hb74);

	/**
	 * 鉴定所站鉴定申请批量删除
	* @author: liangy  
	* @date 2018年12月4日
	* @param @param hb74
	* @param @return    
	* @return AjaxReturnMsg   
	* @throws
	 */
	AjaxReturnMsg delAppraisalDeclareBatch(Hb74 hb74);
}
