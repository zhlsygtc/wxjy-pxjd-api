package com.insigma.mvc.serviceimp.app.APP_001_007;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.insigma.mvc.MvcHelper;
import com.insigma.mvc.dao.app.APP_001_007.ApiAttendancePerMapper;
import com.insigma.mvc.model.train.Hc61Dto;
import com.insigma.mvc.service.app.APP_001_007.ApiAttendancePerService;

@Service
public class ApiAttendancePerServiceImpl extends MvcHelper implements ApiAttendancePerService{

	@Resource
	ApiAttendancePerMapper apiAttendancePerMapper;

	/**
	 * 获取学员培训考勤记录信息列表
	 */
	@Override
	public PageInfo<Hc61Dto> getAttendanceList(Hc61Dto dto) {
		PageHelper.startPage(dto.getCurpage(), 10);
		List<Hc61Dto> list = apiAttendancePerMapper.getAttendanceList(dto);
		PageInfo<Hc61Dto> pageinfo= new PageInfo<>(list);
		return pageinfo;
	}

	/**
	 * 获取学员课程考勤记录信息列表
	 */
	@Override
	public PageInfo<Hc61Dto> getClassAttendanceList(Hc61Dto dto) {
		PageHelper.startPage(dto.getCurpage(), 10);
		List<Hc61Dto> list = apiAttendancePerMapper.getClassAttendanceList(dto);
		PageInfo<Hc61Dto> pageinfo= new PageInfo<>(list);
		return pageinfo;
	}
	
	
	
	
}
