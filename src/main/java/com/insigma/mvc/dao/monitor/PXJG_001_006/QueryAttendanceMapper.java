package com.insigma.mvc.dao.monitor.PXJG_001_006;

import com.insigma.mvc.model.train.*;
import java.util.List;
import java.util.Map;
/**
 * ���ڲ�ѯ
 * @author jewel 
 * 2018-01-10
 */
public interface QueryAttendanceMapper {
	/**
	 * ��ѯ�б�
	 */
	List<Hb69DTO> getInfoList(Hb69DTO param) throws Exception;
	
	List<Hc60DTO> getHc60List(Hc60DTO hc60DTO) throws Exception;
	
	List<Hb69DTO> getHb69List(Hb69DTO hb69DTO) throws Exception;
	
	List<Hb69DTO> attendanceQueryList(Hb69DTO hb69DTO) throws Exception;

	/**
	 * �����ֳ����ȷ�ϱ�����ѧԱ��Ϣ
	 */
	List<Hc60> getStuForSceneSure(Hc60 hc60);
}
