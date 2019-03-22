package com.insigma.mvc.serviceimp.monitor.PXJG_001_006;

import java.util.List;

import javax.annotation.Resource;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.insigma.mvc.MvcHelper;
import com.insigma.mvc.dao.monitor.PXJG_001_006.QueryAttendanceMapper;
import com.insigma.mvc.model.train.*;
import com.insigma.mvc.service.common.fileupload.ApiFileUploadService;
import com.insigma.mvc.service.monitor.PXJG_001_006.QueryAttendanceService;

/**
 * 采集
 * 
 * @author jewel 2018-01-10
 */
@Service
public class QueryAttendanceServiceImpl extends MvcHelper implements QueryAttendanceService {

	@Resource
	private QueryAttendanceMapper queryAttendanceMapper;
	@Resource
	private ApiFileUploadService fileLoadService;


	/**
	 * 查询培训开班列表
	 */
	@Override
	public PageInfo<Hb69DTO> getInfoList(Hb69DTO param) throws Exception {
		PageHelper.offsetPage(param.getOffset(), param.getLimit());
		if (StringUtils.isNotEmpty(param.getChb103())) {
			param.setChb103s(param.getChb103().split(","));
		}
		List<Hb69DTO> list = queryAttendanceMapper.getInfoList(param);
		PageInfo<Hb69DTO> pageinfo = new PageInfo<Hb69DTO>(list);
		return pageinfo;
	}

	
	
	public PageInfo<Hc60DTO> getHc60List(Hc60DTO hc60DTO) throws Exception{
		PageHelper.offsetPage(hc60DTO.getOffset(), hc60DTO.getLimit());
		List<Hc60DTO> list = queryAttendanceMapper.getHc60List(hc60DTO);
		PageInfo<Hc60DTO> pageinfo = new PageInfo<Hc60DTO>(list);
		return pageinfo;
	}
	

	public PageInfo<Hb69DTO> getHb69List(Hb69DTO hb69DTO) throws Exception{
		
		PageHelper.offsetPage(hb69DTO.getOffset(), hb69DTO.getLimit());
		List<Hb69DTO> list = queryAttendanceMapper.getHb69List(hb69DTO);
		PageInfo<Hb69DTO> pageinfo = new PageInfo<Hb69DTO>(list);
		return pageinfo;
	}
	
	

	public PageInfo<Hb69DTO> attendanceQueryList(Hb69DTO hb69DTO) throws Exception{
		PageHelper.offsetPage(hb69DTO.getOffset(), hb69DTO.getLimit());
		List<Hb69DTO> list = queryAttendanceMapper.attendanceQueryList(hb69DTO);
		PageInfo<Hb69DTO> pageinfo = new PageInfo<Hb69DTO>(list);
		return pageinfo;
	}
	
	/**
	 * 导出现场确认表所需学员信息
	 */
	@Override
	public List<Hc60> getStuForSceneSure(Hc60 hc60){
		List<Hc60> list = queryAttendanceMapper.getStuForSceneSure(hc60);
        return list;
	}

}
