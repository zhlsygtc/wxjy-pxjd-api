package com.insigma.mvc.dao.app.APP_001_007;

import java.util.List;

import com.insigma.mvc.model.train.Hc61Dto;

public interface ApiAttendancePerMapper {

	/**
	 * ��ȡѧԱ��ѵ���ڼ�¼��Ϣ�б�
	 */
	List<Hc61Dto> getAttendanceList(Hc61Dto dto);

	/**
	 * ��ȡѧԱ�γ̿��ڼ�¼��Ϣ�б�
	 */
	List<Hc61Dto> getClassAttendanceList(Hc61Dto dto);

}
