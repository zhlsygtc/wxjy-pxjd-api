package com.insigma.mvc.service.appraisal.JDYW_002_011;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.insigma.dto.AjaxReturnMsg;
import com.insigma.mvc.model.Hc63;
import com.insigma.mvc.model.appraisal.Hb70;
import com.insigma.mvc.model.appraisal.Hb74;
import com.insigma.mvc.model.appraisal.Hb74Temp_Short;
import com.insigma.mvc.model.appraisal.Hb76;
import com.insigma.mvc.model.appraisal.Hb77;
import com.insigma.mvc.model.appraisal.Hb78;

public interface ApiExamRoomArrangeService {

	/**
	 * 查询考场编排阶段批次信息
	* @author: liangy  
	* @date 2018年12月19日
	* @param @param hb74Temp_Short
	* @param @return    
	* @return PageInfo<Hb74Temp_Short>   
	* @throws
	 */
	PageInfo<Hb74Temp_Short> getAppraisalSpeciaList(Hb74Temp_Short hb74Temp_Short);

	/**
	 * 考试批次信息查询
	* @author: liangy  
	* @date 2018年12月19日
	* @param @param hb77
	* @param @return    
	* @return PageInfo<Hb70>   
	* @throws
	 */
	PageInfo<Hb70> getExamRoomBatchList(Hb70 hb70);

	/**
	 * 考试信息查询
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
	 * 考试信息保存
	* @author: liangy  
	* @date 2019年1月24日
	* @param @param hb70
	* @param @return    
	* @return AjaxReturnMsg   
	* @throws
	 */
	AjaxReturnMsg examRoomBatchSave(Hb70 hb70);

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
	 * 获取考试信息详情
	* @author: liangy  
	* @date 2019年1月24日
	* @param @return    
	* @return AjaxReturnMsg   
	* @throws
	 */
	AjaxReturnMsg getExamRoomBaseBatchInfo(String chb070);

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
	 * 查询当前批次中已安排考试类型的人数
	* @author: liangy  
	* @date 2018年12月29日
	* @param @param chb140 鉴定批次编号
	* @param @param chb070 考试批次编号
	* @param @return    
	* @return AjaxReturnMsg   
	* @throws
	 */
	AjaxReturnMsg getselectExamRoomPerponNum(String chb070, String chb140);

	/**
	 * 获取考点list信息
	* @author: liangy  
	* @date 2019年1月4日
	* @param @param aab001
	* @param @return    
	* @return List<Hb76>   
	* @throws
	 */
	List<Hb76> getTestCenterList(String aab001);

	/**
	 * 获取考场list
	* @author: liangy  
	* @date 2019年1月4日
	* @param @param chb076
	* @param @return    
	* @return List<Hb78>   
	* @throws
	 */
	List<Hb78> getExamRoomSelectList(String chb076);

	/**
	 * 批量删除考试批次信息
	* @author: liangy  
	* @date 2019年1月28日
	* @param @param hb70
	* @param @return    
	* @return AjaxReturnMsg   
	* @throws
	 */
	AjaxReturnMsg deleteExaminationBatch(Hb70 hb70);

	/**
	 * 考场自动编排
	* @author: liangy  
	* @date 2019年2月19日
	* @param @param hb70
	* @param @return    
	* @return AjaxReturnMsg   
	* @throws
	 */
	AjaxReturnMsg automaticExamArrange(Hb70 hb70);
}
