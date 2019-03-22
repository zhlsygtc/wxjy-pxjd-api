package com.insigma.mvc.service.train.PXYW_001_001;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.github.pagehelper.PageInfo;
import com.insigma.dto.AjaxReturnMsg;
import com.insigma.mvc.model.CodeValue;
import com.insigma.mvc.model.train.Ad01;
import com.insigma.mvc.model.train.Hb62;
import com.insigma.mvc.model.train.Hb63;
import com.insigma.mvc.model.train.Hb64;
import com.insigma.mvc.model.train.Hb68;
import com.insigma.mvc.model.train.Student;
/**
 * ������Ϣά��
 * @author link
 * 2018-02-26
 */
public interface ApiCompanyService {
	
	/**
     * ������֯id��ȡ������Ϣ
     */
	public Ad01 getAD01ById(String groupid) throws Exception;
	/**
	 * ��ʼ������������Ϣ
	 */
	public PageInfo<Hb63> getGroundInfo(Hb63 hb63) throws Exception;
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
	public PageInfo<Hb63> getClassroomInfo(Hb63 hb63) throws Exception;
	
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
