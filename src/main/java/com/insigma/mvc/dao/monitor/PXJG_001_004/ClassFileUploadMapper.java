package com.insigma.mvc.dao.monitor.PXJG_001_004;

import com.insigma.mvc.model.SFileRecord;
import com.insigma.mvc.model.train.*;
import java.util.List;
import java.util.Map;
/**
 * �ɼ�
 * @author jewel 
 * 2018-01-10
 */
public interface ClassFileUploadMapper {
	/**
	 * ��ѯ�б�
	 */
    /**
     * ��ȡ�ļ��б���Ϣ
     * @param sFileRecord
     * @return
     */
    List<StuFileDTO> getBusFileRecordListByBusId(StuFileDTO sFileRecord);
    List<StuFileDTO> getBusFileRecordListByBusId2(StuFileDTO sFileRecord);
}
