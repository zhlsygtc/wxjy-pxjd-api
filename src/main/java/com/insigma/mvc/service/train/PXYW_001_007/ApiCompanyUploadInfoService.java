package com.insigma.mvc.service.train.PXYW_001_007;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;

import com.github.pagehelper.PageInfo;
import com.insigma.dto.AjaxReturnMsg;
import com.insigma.mvc.model.train.Ad01File;
/**
 * 机构风采管理
 * @author link
 * 2018-03-01
 */
public interface ApiCompanyUploadInfoService {

	/**
	 * 查询机构风采信息列表
	 */
	public PageInfo<Ad01File> getInfoList(Ad01File ad01File) throws Exception;
	
	/**
     * 上传机构照片
     */
	public AjaxReturnMsg uploadImage(String userid, String file_bus_id, String file_name, String desc, MultipartFile multipartFile);
	
	/**
	 * 根据机构file_uuid删除机构附件信息
	 */
	public AjaxReturnMsg delete(Ad01File ad01file) throws Exception;
	
	/**
	 * 上传机构视频
	 */
	public AjaxReturnMsg uploadVideo(HttpServletRequest request, MultipartFile multipartFile) throws Exception;
	
	/**
	 * 根据机构id获取机构已有图片、视频及上限
	 * @param ad01file
	 * @return
	 */
	public Ad01File getFileNum(Ad01File ad01file);
}
