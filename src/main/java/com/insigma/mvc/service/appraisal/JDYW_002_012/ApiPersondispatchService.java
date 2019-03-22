package com.insigma.mvc.service.appraisal.JDYW_002_012;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.insigma.dto.AjaxReturnMsg;
import com.insigma.mvc.model.Hc63;
import com.insigma.mvc.model.appraisal.Hb74;
import com.insigma.mvc.model.appraisal.Hb74Temp_Short;
import com.insigma.mvc.model.appraisal.Hb77;
import com.insigma.mvc.model.appraisal.Hb79;
import com.insigma.mvc.model.appraisal.Hc73;
import com.insigma.mvc.model.appraisal.Hc73Temp_Short;

public interface ApiPersondispatchService {

	/**
	 * 查询考场编排阶段批次信息
	* @author: liangy  
	* @date 2018年12月19日
	* @param @param hb74Temp_Short
	* @param @return    
	* @return PageInfo<Hb74Temp_Short>   
	* @throws
	 */
	PageInfo<Hb74Temp_Short> getPersondispatchList(Hb74Temp_Short hb74Temp_Short);

	/**
	 * 考场信息查询
	* @author: liangy  
	* @date 2018年12月19日
	* @param @param hb77
	* @param @return    
	* @return PageInfo<Hb77>   
	* @throws
	 */
	PageInfo<Hb77> getExamRoomList(Hb77 hb77);

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
	 * 考场信息保存
	* @author: liangy  
	* @date 2018年12月19日
	* @param @param hb77
	* @param @return    
	* @return AjaxReturnMsg   
	* @throws
	 */
	AjaxReturnMsg examRoomSave(Hb77 hb77);

	/**
	 * 当前考场人员信息查询
	* @author: liangy  
	* @date 2018年12月19日
	* @param @param hc63
	* @param @return    
	* @return PageInfo<Hc63>   
	* @throws
	 */
	PageInfo<Hc63> getExamRoomPerponList(Hc63 hc63);

	/**
	 * 考场人员信息选择查询
	* @author: liangy  
	* @date 2018年12月20日
	* @param @return    
	* @return PageInfo<Hc63>   
	* @throws
	 */
	PageInfo<Hc63> getExamRoomPerponSelectList(Hc63 hc63);

	/**
	 * 考场人员信息保存 
	* @author: liangy  
	* @date 2018年12月20日
	* @param @param hc63
	* @param @return    
	* @return AjaxReturnMsg   
	* @throws
	 */
	AjaxReturnMsg savePerponExamRoom(Hc63 hc63);

	/**
	 * 从考场批量移出人员信息
	* @author: liangy  
	* @date 2018年12月21日
	* @param @param hc63
	* @param @return    
	* @return AjaxReturnMsg   
	* @throws
	 */
	AjaxReturnMsg deletePerponExamRoom(Hc63 hc63);

	/**
	 * 获取考场信息详情
	* @author: liangy  
	* @date 2018年12月21日
	* @param @param chb077
	* @param @return    
	* @return AjaxReturnMsg   
	* @throws
	 */
	AjaxReturnMsg getExamRoomBaseInfo(String chb077);

	/**
	 * 批量删除考场信息
	* @author: liangy  
	* @date 2018年12月21日
	* @param @param hb77
	* @param @return    
	* @return AjaxReturnMsg   
	* @throws
	 */
	AjaxReturnMsg deletePerponExamRoomBatch(Hb77 hb77);

	/**
	 * 考试编排提交待审核
	* @author: liangy  
	* @date 2018年12月24日
	* @param @param hb74
	* @param @return    
	* @return AjaxReturnMsg   
	* @throws
	 */
	AjaxReturnMsg saveExamRoomSubmit(Hb74 hb74);

	/**
	 * 获取鉴定所站人员信息
	* @author: liangy  
	* @date 2018年12月26日
	* @param @param hc73Temp_Short
	* @param @return    
	* @return AjaxReturnMsg   
	* @throws
	 */
	List<Hc73Temp_Short> getAppraiserList(Hc73Temp_Short hc73Temp_Short);

	/**
	 * 保存鉴定所站人员指派信息
	* @author: liangy  
	* @date 2018年12月26日
	* @param @param hb79
	* @param @return    
	* @return AjaxReturnMsg   
	* @throws
	 */
	AjaxReturnMsg saveAppraisCorePerson(Hb79 hb79);

	/**
	 * 考评员指派信息保存
	* @author: liangy  
	* @date 2018年12月27日
	* @param @param chb140   批次编码
	* @param @param chb17c 指派状态
	* @param @return    
	* @return AjaxReturnMsg   
	* @throws
	 */
	AjaxReturnMsg saveAppraisPersonStatus(String chb140, String chb17c);

	/**
	 * 获取该批次指派人员
	* @author: liangy  
	* @date 2018年12月27日
	* @param @param chb140
	* @param @param chb077
	* @param @param aab001
	* @param @return    
	* @return AjaxReturnMsg   
	* @throws
	 */
	List<Hc73> getAppraisCorePersonList(String chb140, String chb077, String aab001);
}
