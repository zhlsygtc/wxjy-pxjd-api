package com.insigma.mvc.dao.monitor.PXJG_001_005;

import com.insigma.mvc.model.train.*;
import java.util.List;
/**
 * �ɼ�
 * @author jewel 
 * 2018-01-10
 */
public interface AttendanceByCourseMapper {
	/**
	 * ��ѯ�б�
	 */
	List<Hb69DTO> getInfoList(Hb69DTO param) throws Exception;
	
	/**
     * ���ݰ༶�����ѯ��ѵ����ѧ���б�
     */
	List<Hc60> getHc60ById(Hc60 hc60) throws Exception;
	/**
     * ���ݰ༶�����ѯ��ѵ����ѧ���б�
     */
    List<Hc60> getHc60ById1(Hc60 hc60) throws Exception;
	/**
     * ���ݿγ������ѯ��ѵ����Ϣ
     */
	Hb69 getHb69ById(Hb69 hb069) throws Exception;
	
	List<Zc10> checkZc10(Zc10 zc10);
	
	List<Hc60> getHc60ByAac002(Zc10 zc10);
	
	int addAttendance(Zc10 zc10);

	int updateAttendance(Zc10 zc10);
	
	
    List<Zc10> checkZc11(Zc10 zc10);
	
	List<Hb61> getHb61ByAac002(Zc10 zc10);
	
	int addTeacherAttendance(Zc10 zc10);

	int updateTeacherAttendance(Zc10 zc10);
}
