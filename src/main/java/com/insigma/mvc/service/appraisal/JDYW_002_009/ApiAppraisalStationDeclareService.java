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
	* @date 2018��11��29��
	* @param @param hb75Temp
	* @param @param operation �������"in"  �Ƴ�����"out"
	* @param @return    
	* @return AjaxReturnMsg   
	* @throws
	 */
	AjaxReturnMsg savePerponAudit(Hb75Temp hb75Temp, String operation);

	/**
	 * ��ѯ��������/��ѯ��������Ϣ�б�
	* @author: liangy  
	* @date 2018��11��29��
	* @param @param hb75Temp
	* @param @return    
	* @return PageInfo<Hb75Temp>   
	* @throws
	 */
	PageInfo<Hb75Temp> getAppraisalDeclareist(Hb75Temp hb75Temp);

	/**
	 * ��ѯ������վ������Ϣ
	* @author: liangy  
	* @date 2018��12��3��
	* @param @param chb140
	* @param @return    
	* @return AjaxReturnMsg   
	* @throws
	 */
	AjaxReturnMsg getAppraisalInfo(String chb140);

	/**
	 * ��ѵ���������ύѧԱ��Ϣ��ѯ
	* @author: liangy  
	* @date 2018��12��3��
	* @param @param hc60Temp_Short
	* @param @return    
	* @return PageInfo<Hc60Temp_Short>   
	* @throws
	 */
	PageInfo<Hc60Temp_Short> getAppraisThisStudentList(Hc60Temp_Short hc60Temp_Short);

	/**
	 * ������Ա��Ϣ
	* @author: liangy  
	* @date 2018��12��3��
	* @param @return    
	* @return int   
	* @throws
	 */
	AjaxReturnMsg savePerponSubmit(Hb74 hb74);

	/**
	 * ������վ�������뵥��ɾ��
	* @author: liangy  
	* @date 2018��12��4��
	* @param @param hb74
	* @param @return    
	* @return AjaxReturnMsg   
	* @throws
	 */
	AjaxReturnMsg delAppraisalDeclare(Hb74 hb74);

	/**
	 * ������վ������������ɾ��
	* @author: liangy  
	* @date 2018��12��4��
	* @param @param hb74
	* @param @return    
	* @return AjaxReturnMsg   
	* @throws
	 */
	AjaxReturnMsg delAppraisalDeclareBatch(Hb74 hb74);
}
