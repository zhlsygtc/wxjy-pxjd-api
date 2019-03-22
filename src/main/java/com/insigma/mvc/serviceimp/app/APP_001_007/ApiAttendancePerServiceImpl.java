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
	 * ��ȡѧԱ��ѵ���ڼ�¼��Ϣ�б�
	 */
	@Override
	public PageInfo<Hc61Dto> getAttendanceList(Hc61Dto dto) {
		PageHelper.startPage(dto.getCurpage(), 10);
		List<Hc61Dto> list = apiAttendancePerMapper.getAttendanceList(dto);
		PageInfo<Hc61Dto> pageinfo= new PageInfo<>(list);
		return pageinfo;
	}

	/**
	 * ��ȡѧԱ�γ̿��ڼ�¼��Ϣ�б�
	 */
	@Override
	public PageInfo<Hc61Dto> getClassAttendanceList(Hc61Dto dto) {
		PageHelper.startPage(dto.getCurpage(), 10);
		List<Hc61Dto> list = apiAttendancePerMapper.getClassAttendanceList(dto);
		PageInfo<Hc61Dto> pageinfo= new PageInfo<>(list);
		return pageinfo;
	}
	
	
	
	
}
