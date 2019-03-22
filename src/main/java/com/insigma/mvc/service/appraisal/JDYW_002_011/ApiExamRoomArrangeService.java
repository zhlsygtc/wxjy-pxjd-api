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
	 * ��ѯ�������Ž׶�������Ϣ
	* @author: liangy  
	* @date 2018��12��19��
	* @param @param hb74Temp_Short
	* @param @return    
	* @return PageInfo<Hb74Temp_Short>   
	* @throws
	 */
	PageInfo<Hb74Temp_Short> getAppraisalSpeciaList(Hb74Temp_Short hb74Temp_Short);

	/**
	 * ����������Ϣ��ѯ
	* @author: liangy  
	* @date 2018��12��19��
	* @param @param hb77
	* @param @return    
	* @return PageInfo<Hb70>   
	* @throws
	 */
	PageInfo<Hb70> getExamRoomBatchList(Hb70 hb70);

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
	* @date 2019��1��24��
	* @param @param hb70
	* @param @return    
	* @return AjaxReturnMsg   
	* @throws
	 */
	AjaxReturnMsg examRoomBatchSave(Hb70 hb70);

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
	* @date 2019��1��24��
	* @param @return    
	* @return AjaxReturnMsg   
	* @throws
	 */
	AjaxReturnMsg getExamRoomBaseBatchInfo(String chb070);

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
	 * ��ѯ��ǰ�������Ѱ��ſ������͵�����
	* @author: liangy  
	* @date 2018��12��29��
	* @param @param chb140 �������α��
	* @param @param chb070 �������α��
	* @param @return    
	* @return AjaxReturnMsg   
	* @throws
	 */
	AjaxReturnMsg getselectExamRoomPerponNum(String chb070, String chb140);

	/**
	 * ��ȡ����list��Ϣ
	* @author: liangy  
	* @date 2019��1��4��
	* @param @param aab001
	* @param @return    
	* @return List<Hb76>   
	* @throws
	 */
	List<Hb76> getTestCenterList(String aab001);

	/**
	 * ��ȡ����list
	* @author: liangy  
	* @date 2019��1��4��
	* @param @param chb076
	* @param @return    
	* @return List<Hb78>   
	* @throws
	 */
	List<Hb78> getExamRoomSelectList(String chb076);

	/**
	 * ����ɾ������������Ϣ
	* @author: liangy  
	* @date 2019��1��28��
	* @param @param hb70
	* @param @return    
	* @return AjaxReturnMsg   
	* @throws
	 */
	AjaxReturnMsg deleteExaminationBatch(Hb70 hb70);

	/**
	 * �����Զ�����
	* @author: liangy  
	* @date 2019��2��19��
	* @param @param hb70
	* @param @return    
	* @return AjaxReturnMsg   
	* @throws
	 */
	AjaxReturnMsg automaticExamArrange(Hb70 hb70);
}
