package com.insigma.mvc.service.monitor.PXJG_001_006;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.insigma.dto.AjaxReturnMsg;
import com.insigma.mvc.model.train.*;

/**
 * ���ڲ�ѯ
 * @author jewel
 * 2018-01-10
 */
public interface QueryAttendanceService {
	/**
	 * ��ѯ�б�
	 */
    public PageInfo<Hb69DTO> getInfoList(Hb69DTO param) throws Exception;
    
  
	public PageInfo<Hc60DTO> getHc60List(Hc60DTO hc60DTO) throws Exception;
	

	public PageInfo<Hb69DTO> getHb69List(Hb69DTO hb69DTO) throws Exception;
	
	

	public PageInfo<Hb69DTO> attendanceQueryList(Hb69DTO hb69DTO) throws Exception;
	/**
	 * ��ȡ�����ֳ�ȷ�ϱ�����ѧԱ��Ϣ
	 * @param hb69
	 * @return
	 */
	public List<Hc60> getStuForSceneSure(Hc60 hc60);
	
}
