package com.insigma.mvc.service.app.APP_001_007;

import com.github.pagehelper.PageInfo;
import com.insigma.mvc.model.train.Hc61Dto;

public interface ApiAttendancePerService {

	/**
	 * 获取学员培训考勤记录信息列表
	 */
	PageInfo<Hc61Dto> getAttendanceList(Hc61Dto dto);

	/**
	 * 获取学员课程考勤记录信息列表
	 */
	PageInfo<Hc61Dto> getClassAttendanceList(Hc61Dto dto);

}
