package com.insigma.mvc.service.appraisal.JDYW_002_002;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;

import com.github.pagehelper.PageInfo;
import com.insigma.dto.AjaxReturnMsg;
import com.insigma.mvc.model.train.Ad01File;
/**
 * ����������ɹ���
 * 2018-12-17
 */
public interface ApiAppraisalCompanyUploadInfoService {
	/**
	 * ��ѯ���������Ϣ�б�
	 */
	public PageInfo<Ad01File> getInfoList(Ad01File ad01File) throws Exception;
	
	/**
     * �ϴ�������Ƭ
     */
	public AjaxReturnMsg uploadImage(String userid, String file_bus_id, String file_name, String desc, MultipartFile multipartFile);
	
	/**
	 * ���ݻ���file_uuidɾ������������Ϣ
	 */
	public AjaxReturnMsg delete(Ad01File ad01file) throws Exception;
	
	/**
	 * �ϴ�������Ƶ
	 */
	public AjaxReturnMsg uploadVideo(HttpServletRequest request, MultipartFile multipartFile) throws Exception;
	
	/**
	 * ���ݻ���id��ȡ��������ͼƬ����Ƶ������
	 * @param ad01file
	 * @return
	 */
	public Ad01File getFileNum(Ad01File ad01file);
}
