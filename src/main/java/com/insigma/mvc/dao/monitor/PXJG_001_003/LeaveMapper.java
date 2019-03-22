package com.insigma.mvc.dao.monitor.PXJG_001_003;

import com.insigma.mvc.model.train.*;
import java.util.List;
import java.util.Map;
/**
 * «ÎºŸ
 * @author  
 *
 */
public interface LeaveMapper {
	/**
	 * ≤È—Ø¡–±Ì
	 */
	List<Zc07DTO> getInfoList(Zc07DTO zc07DTO) throws Exception;

	List<Zc07> getStuList(Zc07 zc07) throws Exception;

	void saveData(Zc07 zc07);
	
	void saveZc07Det(Zc07 zc07);

	void deleteData(String czc007);

	void deleteZc07Det(String czc007);
	
	Hc60 getStuById(String chc001);
}
