package com.insigma.mvc.service.monitor.PXJG_001_004;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;

import com.github.pagehelper.PageInfo;
import com.insigma.dto.AjaxReturnMsg;
import com.insigma.mvc.model.SFileRecord;
import com.insigma.mvc.model.train.*;

/**
 * ��������
 * @author jewel
 * 2018-01-10
 */
public interface ClassFileUploadService {

	
	AjaxReturnMsg getFileList(StuFileDTO sFileRecord);
	AjaxReturnMsg getFileList2(StuFileDTO sFileRecord);
	 /**
     * �ϴ���������ļ�
     * @param request
     * @param multipartFile
     * @return
     * @throws Exception
     */
    AjaxReturnMsg uploadFile(HttpServletRequest request, MultipartFile multipartFile) throws Exception;
    AjaxReturnMsg deleteFile(String bus_uuid) throws Exception;

	
	
}
