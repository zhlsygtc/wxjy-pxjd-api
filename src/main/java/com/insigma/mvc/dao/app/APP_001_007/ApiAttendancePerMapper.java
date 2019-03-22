package com.insigma.mvc.dao.app.APP_001_007;

import java.util.List;

import com.insigma.mvc.model.train.Hc61Dto;

public interface ApiAttendancePerMapper {

	/**
	 * 获取学员培训考勤记录信息列表
	 */
	List<Hc61Dto> getAttendanceList(Hc61Dto dto);

	/**
	 * 获取学员课程考勤记录信息列表
	 */
	List<Hc61Dto> getClassAttendanceList(Hc61Dto dto);

}
