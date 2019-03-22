package com.insigma.mvc.dao.train.PXYW_001_007;

import java.util.List;

import com.insigma.mvc.model.train.Ad01File;

/**
 * 机构风采管理
 * @author link
 * 2018-03-01
 */
public interface ApiCompanyUploadInfoMapper {
	
	/**
	 * 查询机构风采信息列表
	 */
	List<Ad01File> getInfoList(Ad01File ad01file) throws Exception;
	
	/**
	 * 新增ad01file机构风采信息
	 */
	int addAd01File(Ad01File ad01file) throws Exception;
	
	/**
	 * 根据file_uuid删除ad01file信息
	 */
	int delAd01File(Ad01File ad01file) throws Exception;
	
	/**
	 * 根据file_uuid删除s_file_record信息
	 */
	int delFileRecord(Ad01File ad01file) throws Exception;
	
	/**
	 * 根据file_uuid删除s_bus_file_record信息
	 */
	int delBusFileRecord(Ad01File ad01file) throws Exception;
	
	/**
	 * 根据机构id获取图片数量
	 */
	Ad01File getPhotoNum(Ad01File ad01file) throws Exception;
	
	/**
	 * 根据机构id获取视频数量
	 */
	Ad01File getVideoNum(Ad01File ad01file) throws Exception;
	
	/**
	 * 获取机构图片上限
	 */
	Ad01File getMaxPhoto(Ad01File ad01file) throws Exception;
	
	/**
	 * 获取机构视频上限
	 */
	Ad01File getMaxVideo(Ad01File ad01file) throws Exception;
}
