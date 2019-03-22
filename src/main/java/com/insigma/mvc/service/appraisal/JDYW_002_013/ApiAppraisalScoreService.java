package com.insigma.mvc.service.appraisal.JDYW_002_013;

import com.github.pagehelper.PageInfo;
import com.insigma.dto.AjaxReturnMsg;
import com.insigma.mvc.model.Hc63;
import com.insigma.mvc.model.appraisal.Hb74Temp_Short;
import com.insigma.mvc.model.appraisal.Hc64;

public interface ApiAppraisalScoreService {

	/**
	 * ��ѯ�������Ž׶�������Ϣ
	* @author: liangy  
	* @date 2018��12��19��
	* @param @param hb74Temp_Short
	* @param @return    
	* @return PageInfo<Hb74Temp_Short>   
	* @throws
	 */
	PageInfo<Hb74Temp_Short> getAppraisalScoreList(Hb74Temp_Short hb74Temp_Short);

	/**
	 * ��ѯ��ǰ������Ա�ɼ���Ϣ
	* @author: liangy  
	* @date 2019��3��5��
	* @param @param Hc63
	* @param @return    
	* @return PageInfo<Hc63>   
	* @throws
	 */
	PageInfo<Hc63> getExamineeList(Hc63 hc63);

	/**
	 * �����޸�ѧԱ�ɼ���Ϣ
	* @author: liangy  
	* @date 2019��3��5��
	* @param @param hc64
	* @param @return    
	* @return AjaxReturnMsg   
	* @throws
	 */
	AjaxReturnMsg saveExamineeInfo(Hc64 hc64);

	/**
	 * �ɼ��ύ
	* @author: liangy  
	* @date 2019��3��11��
	* @param @param hb74
	* @param @return    
	* @return AjaxReturnMsg   
	* @throws
	 */
	AjaxReturnMsg saveSubmitScore(Hb74Temp_Short hb74Temp_Short);
}
