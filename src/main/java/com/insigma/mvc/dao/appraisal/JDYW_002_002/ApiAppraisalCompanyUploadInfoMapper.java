package com.insigma.mvc.dao.appraisal.JDYW_002_002;

import java.util.List;

import com.insigma.mvc.model.train.Ad01File;

/**
 * ����������ɹ���
 * 2018-12-17
 */
public interface ApiAppraisalCompanyUploadInfoMapper {
	
	/**
	 * ��ѯ���������Ϣ�б�
	 */
	List<Ad01File> getInfoList(Ad01File ad01file) throws Exception;
	
	/**
	 * ����ad01file���������Ϣ
	 */
	int addAd01File(Ad01File ad01file) throws Exception;
	
	/**
	 * ����file_uuidɾ��ad01file��Ϣ
	 */
	int delAd01File(Ad01File ad01file) throws Exception;
	
	/**
	 * ����file_uuidɾ��s_file_record��Ϣ
	 */
	int delFileRecord(Ad01File ad01file) throws Exception;
	
	/**
	 * ����file_uuidɾ��s_bus_file_record��Ϣ
	 */
	int delBusFileRecord(Ad01File ad01file) throws Exception;
	
	/**
	 * ���ݻ���id��ȡͼƬ����
	 */
	Ad01File getPhotoNum(Ad01File ad01file) throws Exception;
	
	/**
	 * ���ݻ���id��ȡ��Ƶ����
	 */
	Ad01File getVideoNum(Ad01File ad01file) throws Exception;
	
	/**
	 * ��ȡ����ͼƬ����
	 */
	Ad01File getMaxPhoto(Ad01File ad01file) throws Exception;
	
	/**
	 * ��ȡ������Ƶ����
	 */
	Ad01File getMaxVideo(Ad01File ad01file) throws Exception;
}
