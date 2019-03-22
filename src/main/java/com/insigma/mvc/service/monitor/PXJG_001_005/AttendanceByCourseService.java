package com.insigma.mvc.service.monitor.PXJG_001_005;


import com.github.pagehelper.PageInfo;
import com.insigma.dto.AjaxReturnMsg;
import com.insigma.mvc.model.train.*;

/**
 * ��
 * @author jewel
 * 2018-01-10
 */
public interface AttendanceByCourseService {
	/**
	 * ��ѯ�б�
	 */
    public PageInfo<Hb69DTO> getInfoList(Hb69DTO param) throws Exception;
    
	/**
     * ���ݰ༶�����ѯ��ѵ����ѧ���б�
     */
	public PageInfo<Hc60> getHc60ById(Hc60 hc60) throws Exception;
	  
	 
    /**
     * ���ݰ༶�����ѯ��ѵ����ѧ���б�
     */
    AjaxReturnMsg getHc60ById1(Hc60 hc60) throws Exception;
	/**
     * ���ݿγ������ѯ��ѵ����Ϣ
     */
	public Hb69 getHb69ById(Hb69 hb69) throws Exception;
		
	AjaxReturnMsg saveData(Zc10 zc10) throws Exception;
	
	
}
