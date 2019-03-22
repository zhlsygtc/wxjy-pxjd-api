package com.insigma.mvc.dao.monitor.PXJG_001_002;

import com.insigma.mvc.model.train.*;
import java.util.List;
import java.util.Map;
/**
 * �ɼ�
 * @author jewel 
 * 2018-01-10
 */
public interface AttendanceMapper {
	/**
	 * ��ѯ�б�
	 */
	List<Hb68> getInfoList(Hb68 hb68) throws Exception;
	
	List<Zc02> checkZc02(Zc02 zc02);
	
	List<Hc60> getHc60ByAac002(Zc02 zc02);
	
	int addAttendance(Zc02 zc02);

	int updateAttendance(Zc02 zc02);
}
