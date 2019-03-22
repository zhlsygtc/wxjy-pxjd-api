package com.insigma.mvc.dao.train.PXYW_001_001;

import java.util.List;

import com.insigma.mvc.model.CodeValue;
import com.insigma.mvc.model.train.Ad01;
import com.insigma.mvc.model.train.Hb62;
import com.insigma.mvc.model.train.Hb63;
import com.insigma.mvc.model.train.Hb64;
import com.insigma.mvc.model.train.Student;
/**
 * ������Ϣά��
 * @author link
 * 2018-02-26
 */
public interface ApiCompanyMapper {
	/**
	 * ��ѯ��ѵ�����б�
	 */
	List<Hb63> getGroundInfo(Hb63 hb63) throws Exception;
	/**
	 * ��ѯ��ѵ�����б�
	 */
	List<Hb63> getClassroomInfo(Hb63 hb63) throws Exception;
	
	/**
	 * ��ѯ��ѵ�����б�
	 */
	List<Hb62> getQualityInfo(Hb62 hb62) throws Exception;
	
	/**
	 * ��ѯ��ѵ�����б�(���)
	 */
	List<Hb62> getQualityInfo_df(Hb62 hb62) throws Exception;
	
	/**
	 * ��ѯ��ѵ�����б�(����)
	 */
	List<Hb62> getQualityInfo_sx(Hb62 hb62);
	
	/**
	 * ��ȡcode_value�е�chb065
	 */
	List<Hb64> getCodeValueChb065() throws Exception;
	/**
	 * ��ȡcode_value�е�chb163
	 */
	List<Hb64> getCodeValueChb163() throws Exception;
	/**
	 * ��ȡcode_value�е�chb070
	 */
	List<Hb64> getCodeValueChb070() throws Exception;
	
	/**
	 * ����Ǹ��²��������޸Ĳ���---����
	 */
	String checkGround(String chb060) throws Exception;
	/**
	 * ����Ǹ��²��������޸Ĳ���---����
	 */
	String checkClassroom(String chb064) throws Exception;
	
	/**
	 * ����Ǹ��²��������޸Ĳ���---����
	 */
	String checkQualityInfo(String chb062) throws Exception;
	
	/**
	 * ����������Ϣ
	 */
    int insertHb63(Hb63 hb63) throws Exception;
    /**
	 * �޸ĳ�����Ϣ
	 */
    int updateHb63(Hb63 hb63) throws Exception;
    /**
	 * ����������Ϣ
	 */
    int insertHb64(Hb63 hb63) throws Exception;
    /**
	 * �޸Ľ�����Ϣ
	 */
    int updateHb64(Hb63 hb63) throws Exception;
    /**
   	 * ɾ��������Ϣ
   	 */
    int delHb63(Hb63 hb63) throws Exception;
    /**
   	 * ����ɾ��������Ϣ
   	 */
    int delHb64(Hb63 hb63) throws Exception;
    /**
   	 * ����ɾ��������Ϣ
   	 */
    int delSingleHb64(Hb63 hb63) throws Exception;
    
    List<CodeValue> getCodeValueByName(CodeValue codevalue) throws Exception;
    /**
	 * ����������Ϣ
	 */
    int insertHb62(Hb62 hb62) throws Exception;
    /**
	 * �޸�������Ϣ
	 */
    int updateHb62(Hb62 hb62) throws Exception;
    
    /**
   	 * ɾ��������Ϣ
   	 */
    int delHb62(Hb62 hb62) throws Exception;
	/**
     * ���ݸ�����֯id��ȡ������Ϣ
     */
	Ad01 getAD01ById(String groupid) throws Exception;
	/**
	 * ��ȡ������Ϣ
	 * @param bus_uuid
	 * @return
	 * @throws Exception
	 */
	Ad01 getAd01ByBusUuid(String bus_uuid) throws Exception;
	/**
     * ����file_bus_id�޸���֯������bus_uuid
     */
	int  updateAd01ByBusUuid(Ad01 ad01);
	/**
	 * ����groupid�޸�ad01���л�����Ϣ
	 */
	int saveAd01ByGroupid(Ad01 ad01);
	/**
	 * ����gourpid�޸�smt_groupid���л�����Ϣ
	 */
	int saveSmtgroupByGroupid(Ad01 ad01);
	
	/**
	 * ��ȡ��ѵ����
	 */
	CodeValue getAca112(String codename) throws Exception;
	
	/**
	 * ��ȡ��ѵ���֣���ᣩ
	 */
	CodeValue getAca112_df(String codename) throws Exception;
	
	/**
	 * ��ȡ��ѵ���֣�������
	 */
	CodeValue getAca112_sx(String codename);
	
	/**
	 * ��ȡAA01�е�ֵ
	 */
	String getAa01ByAaa001(String aaa001);
	
	/**
	 * ��ȡCA11����
	 */
	String getCa11ByAca112(String aca112);
	
}
