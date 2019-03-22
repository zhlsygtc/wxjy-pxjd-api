package com.insigma.mvc.dao.train.PXYW_001_006;

import com.insigma.mvc.model.train.*;
import java.util.List;
import java.util.Map;
/**
 * �ɼ�¼��
 * @author Administrator 
 * 2018-03-01
 */
public interface ApiInputScoreMapper {
	/**
	 * ��ѯ��ѵ��౸���б�
	 */
	List<Hb68> getInfoList(Hb68 hb68) throws Exception;
	 /**
	 * ����ID��ѯ�༶��Ϣ
	 */
	Hb68 getById(String chb100) throws Exception;
	/**
	 * ����ID��ѯ�༶��Ϣ����ᣩ
	 */
	Hb68 getById_df(String chb100);
	/**
	 * ����ID��ѯ�༶��Ϣ��������
	 */
	Hb68 getById_sx(String chb100);
	/**
	 * ����רҵ���Ʋ�ѯ��ѵ�������Ƽ�רҵ���
	 */
	Hb68 getAca112(String aca110) throws Exception;
	/**
	 * ��ѯ��ѵѧԱ��Ϣ
	 */
	List<Student> getStuListForLook(String chb100) throws Exception;
	/**
	 * ��ѯ�Ƿ�������hc66
	 */
	String checkHc66(String chb100) throws Exception;
	/**
	 * ���hc66���ѧԱ�ɼ�
	 */
	int clearHc66(String chb100) throws Exception;
	/**
	 * ͨ�����֤�Ų�ѯ�Ƿ�������hc66
	 */
	String checkByAac002AndChb100(Student stu) throws Exception;
	/**
	 * ����hc66������ѡ�ϸ��벻�ϸ�
	 */
    int createHc66(Student stu) throws Exception;
    /**
	 * �������֤�źͰ༶������ɳɼ���
	 */
    int createHc66ByAac002AndChb100(Student stu) throws Exception;
    /**
	 * �������֤�źͰ༶��Ÿ���hc66
	 */
    int updateHc66ByAac002AndChb100(Student stu) throws Exception;
    /**
	 * ����hc66������ѡ�ϸ��벻�ϸ�
	 */
    int updateHc66(Student stu) throws Exception;
    /**
   	 * ����hb68����¼��״̬
   	 */
    int updateHb68PartStatus(Student stu) throws Exception;
    /**
   	 * ����hb68�ɼ�¼��״̬����״̬
   	 */
    int updateHb68Status(Student stu) throws Exception;
    /**
   	 * ����hb68�ɼ��ύ״̬
   	 */
    int submitScore(Hb68 hb68) throws Exception;
	/**
	 * �����ɼ���Ϣ
	 */
	int revokeScore(Hb68 hb68) throws Exception;
	/**
	 * ��ѯ�ɼ���׼
	 */
	Zc13 getZc13(String groupid) throws Exception;
	/**
	 * ��ѯ�ͽ���������
	 */
	String getGroupId(String aab001) throws Exception;
	/**
	 * ��ѯ��ǰ������������
	 */
	String getAreaId() throws Exception;
	/**
	 * ����ѧԱ�ܷ���֤
	 */
	int updateHc60Chc029(Student stu) throws Exception;
	
}
