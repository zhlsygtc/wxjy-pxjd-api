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
	 * ��ѯ�������Ž׶�������Ϣ
	* @author: liangy  
	* @date 2018��12��19��
	* @param @param hb74Temp_Short
	* @param @return    
	* @return PageInfo<Hb74Temp_Short>   
	* @throws
	 */
	PageInfo<Hb74Temp_Short> getPersondispatchList(Hb74Temp_Short hb74Temp_Short);

	/**
	 * ������Ϣ��ѯ
	* @author: liangy  
	* @date 2018��12��19��
	* @param @param hb77
	* @param @return    
	* @return PageInfo<Hb77>   
	* @throws
	 */
	PageInfo<Hb77> getExamRoomList(Hb77 hb77);

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
	 * ������Ϣ����
	* @author: liangy  
	* @date 2018��12��19��
	* @param @param hb77
	* @param @return    
	* @return AjaxReturnMsg   
	* @throws
	 */
	AjaxReturnMsg examRoomSave(Hb77 hb77);

	/**
	 * ��ǰ������Ա��Ϣ��ѯ
	* @author: liangy  
	* @date 2018��12��19��
	* @param @param hc63
	* @param @return    
	* @return PageInfo<Hc63>   
	* @throws
	 */
	PageInfo<Hc63> getExamRoomPerponList(Hc63 hc63);

	/**
	 * ������Ա��Ϣѡ���ѯ
	* @author: liangy  
	* @date 2018��12��20��
	* @param @return    
	* @return PageInfo<Hc63>   
	* @throws
	 */
	PageInfo<Hc63> getExamRoomPerponSelectList(Hc63 hc63);

	/**
	 * ������Ա��Ϣ���� 
	* @author: liangy  
	* @date 2018��12��20��
	* @param @param hc63
	* @param @return    
	* @return AjaxReturnMsg   
	* @throws
	 */
	AjaxReturnMsg savePerponExamRoom(Hc63 hc63);

	/**
	 * �ӿ��������Ƴ���Ա��Ϣ
	* @author: liangy  
	* @date 2018��12��21��
	* @param @param hc63
	* @param @return    
	* @return AjaxReturnMsg   
	* @throws
	 */
	AjaxReturnMsg deletePerponExamRoom(Hc63 hc63);

	/**
	 * ��ȡ������Ϣ����
	* @author: liangy  
	* @date 2018��12��21��
	* @param @param chb077
	* @param @return    
	* @return AjaxReturnMsg   
	* @throws
	 */
	AjaxReturnMsg getExamRoomBaseInfo(String chb077);

	/**
	 * ����ɾ��������Ϣ
	* @author: liangy  
	* @date 2018��12��21��
	* @param @param hb77
	* @param @return    
	* @return AjaxReturnMsg   
	* @throws
	 */
	AjaxReturnMsg deletePerponExamRoomBatch(Hb77 hb77);

	/**
	 * ���Ա����ύ�����
	* @author: liangy  
	* @date 2018��12��24��
	* @param @param hb74
	* @param @return    
	* @return AjaxReturnMsg   
	* @throws
	 */
	AjaxReturnMsg saveExamRoomSubmit(Hb74 hb74);

	/**
	 * ��ȡ������վ��Ա��Ϣ
	* @author: liangy  
	* @date 2018��12��26��
	* @param @param hc73Temp_Short
	* @param @return    
	* @return AjaxReturnMsg   
	* @throws
	 */
	List<Hc73Temp_Short> getAppraiserList(Hc73Temp_Short hc73Temp_Short);

	/**
	 * ���������վ��Աָ����Ϣ
	* @author: liangy  
	* @date 2018��12��26��
	* @param @param hb79
	* @param @return    
	* @return AjaxReturnMsg   
	* @throws
	 */
	AjaxReturnMsg saveAppraisCorePerson(Hb79 hb79);

	/**
	 * ����Աָ����Ϣ����
	* @author: liangy  
	* @date 2018��12��27��
	* @param @param chb140   ���α���
	* @param @param chb17c ָ��״̬
	* @param @return    
	* @return AjaxReturnMsg   
	* @throws
	 */
	AjaxReturnMsg saveAppraisPersonStatus(String chb140, String chb17c);

	/**
	 * ��ȡ������ָ����Ա
	* @author: liangy  
	* @date 2018��12��27��
	* @param @param chb140
	* @param @param chb077
	* @param @param aab001
	* @param @return    
	* @return AjaxReturnMsg   
	* @throws
	 */
	List<Hc73> getAppraisCorePersonList(String chb140, String chb077, String aab001);
}
