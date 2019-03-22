package com.insigma.mvc.service.monitor.PXJG_001_001;

import com.github.pagehelper.PageInfo;
import com.insigma.dto.AjaxReturnMsg;
import com.insigma.mvc.model.train.*;

import org.springframework.web.multipart.MultipartFile;
/**
 * 采集
 * @author jewel
 * 2018-01-10
 */
public interface CollectService {
	/**
	 * 查询学员列表
	 */
	public PageInfo<Hc60DTO> getInfoList(Hc60DTO hc60dto) throws Exception;
	
	public PageInfo<Hb69DTO> getTeacherInfoList(Hb69DTO hb69dto) throws Exception;
	
	AjaxReturnMsg getStuById(String id);
	
	AjaxReturnMsg getTeacherById(String id);
	
	AjaxReturnMsg saveData(Hc60DTO hc60dto);
	
	AjaxReturnMsg uploadLogo(String userid, String file_bus_id, String file_name, MultipartFile multipartFile);
	
	AjaxReturnMsg uploadCard(String userid, String file_bus_id, String file_name, MultipartFile multipartFile);

	
}
