package com.insigma.mvc.service.app.APP_001_007;

import com.github.pagehelper.PageInfo;
import com.insigma.mvc.model.train.Hc61Dto;

public interface ApiAttendancePerService {

	/**
	 * ��ȡѧԱ��ѵ���ڼ�¼��Ϣ�б�
	 */
	PageInfo<Hc61Dto> getAttendanceList(Hc61Dto dto);

	/**
	 * ��ȡѧԱ�γ̿��ڼ�¼��Ϣ�б�
	 */
	PageInfo<Hc61Dto> getClassAttendanceList(Hc61Dto dto);

}
