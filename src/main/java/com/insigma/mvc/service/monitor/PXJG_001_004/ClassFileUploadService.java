package com.insigma.mvc.service.monitor.PXJG_001_004;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;

import com.github.pagehelper.PageInfo;
import com.insigma.dto.AjaxReturnMsg;
import com.insigma.mvc.model.SFileRecord;
import com.insigma.mvc.model.train.*;

/**
 * 附件管理
 * @author jewel
 * 2018-01-10
 */
public interface ClassFileUploadService {

	
	AjaxReturnMsg getFileList(StuFileDTO sFileRecord);
	AjaxReturnMsg getFileList2(StuFileDTO sFileRecord);
	 /**
     * 上传申请相关文件
     * @param request
     * @param multipartFile
     * @return
     * @throws Exception
     */
    AjaxReturnMsg uploadFile(HttpServletRequest request, MultipartFile multipartFile) throws Exception;
    AjaxReturnMsg deleteFile(String bus_uuid) throws Exception;

	
	
}
