package com.insigma.mvc.dao.monitor.PXJG_001_001;

import com.insigma.mvc.model.train.*;
import java.util.List;
import java.util.Map;
/**
 * �ɼ�
 * @author jewel 
 * 2018-01-10
 */
public interface CollectMapper {
	/**
	 * ��ѯѧԱ�б�
	 */
	List<Hc60DTO> getInfoList(Hc60DTO hc60dto) throws Exception;
	
	List<Hb69DTO> getTeacherInfoList(Hb69DTO hb69dto) throws Exception;
	
	
	Hc60DTO getStuById(String chc001);
	
	Hc60DTO getTeacherById(String chb061);
	
	List<Hc60DTO> checkAac002(String aac002);
	
	int addCollect(Hc60DTO hc60dto);

	int updateCollect(Hc60DTO hc60dto);
	
}
