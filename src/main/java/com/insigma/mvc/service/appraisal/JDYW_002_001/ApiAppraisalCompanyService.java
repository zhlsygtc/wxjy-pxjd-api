package com.insigma.mvc.service.appraisal.JDYW_002_001;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.github.pagehelper.PageInfo;
import com.insigma.dto.AjaxReturnMsg;
import com.insigma.mvc.model.CodeValue;
import com.insigma.mvc.model.appraisal.Hb76;
import com.insigma.mvc.model.appraisal.Hb78;
import com.insigma.mvc.model.train.Ad01;
import com.insigma.mvc.model.train.Hb62;
import com.insigma.mvc.model.train.Hb63;
import com.insigma.mvc.model.train.Hb64;
/**
 * ������Ϣά��
 * 2018-12-17
 */
public interface ApiAppraisalCompanyService {
	
	/**
     * ������֯id��ȡ������Ϣ
     */
	public Ad01 getAD01ById(String groupid) throws Exception;
	/**
	 * ��ʼ������������Ϣ
	 */
	public PageInfo<Hb76> getGroundInfo(Hb76 hb76) throws Exception;
	/**
	 * ��ѯchb065
	 */
	public List<Hb64> getCodeValueChb065() throws Exception;
	/**
	 * ��ѯchb163
	 */
	public List<Hb64> getCodeValueChb163() throws Exception;
	/**
	 * ��ѯchb070
	 */
	public List<Hb64> getCodeValueChb070() throws Exception;
	/**
	 * ��ʼ������������Ϣ
	 */
	public PageInfo<Hb78> getClassroomInfo(Hb78 hb78) throws Exception;
	
	/**
	 * ��ʼ��������Ϣ
	 */
	public PageInfo<Hb62> getQualityInfo(Hb62 hb62) throws Exception;
	
	/**
	 * ������ѵ������Ϣ
	 */
	public AjaxReturnMsg saveGroundInfo(Hb63 hb63) throws Exception;
	/**
	 * ������ѵ������Ϣ
	 */
	public AjaxReturnMsg saveClassroomInfo(Hb63 hb63) throws Exception;
	
	
	public AjaxReturnMsg saveQualityInfo(Hb62 hb62) throws Exception;
	
	/**
     * �ϴ���Ƭ
     */
	public AjaxReturnMsg uploadLogo(String userid, String file_bus_id, String file_name, MultipartFile multipartFile);
	
	/**
	 * ������Ϣ�޸�
	 */
	public AjaxReturnMsg saveData(Ad01 ad01);
	
	/**
	 * ����רҵ���Ʋ�ѯ��ѵ�������Ƽ�רҵ���
	 */
	public CodeValue getAca112(String codename) throws Exception;
}
