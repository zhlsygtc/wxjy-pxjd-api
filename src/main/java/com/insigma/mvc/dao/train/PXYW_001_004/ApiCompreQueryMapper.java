package com.insigma.mvc.dao.train.PXYW_001_004;

import java.util.List;

import com.insigma.mvc.model.train.Hb67;
import com.insigma.mvc.model.train.Hb68;
import com.insigma.mvc.model.train.Hb69;
import com.insigma.mvc.model.train.Hb69DTO;
import com.insigma.mvc.model.train.Hc60;
import com.insigma.mvc.model.train.Hc66;
import com.insigma.mvc.model.train.Zc10;
/**
 * ��ѵ�ۺϲ�ѯ
 * @author link 
 * 2018-01-29
 */
public interface ApiCompreQueryMapper {
	/**
	 * ��ѯ��ѵ�������Ϣ�б�
	 */
	List<Hb68> getInfoList(Hb68 hb68) throws Exception;

	/**
	 * ���ݺϲ���༶��ϲ�ǰ�༶�б�
	 */
	List<Hb68> getInfoListByChb067(Hb67 hb67) throws Exception;
	
	/**
     * ���ݰ༶�����ѯ��ѵ����ѧ���б�
     */
	List<Hc60> getHc60ById(Hc60 hc60) throws Exception;
	
	/**
     * ���ݰ༶�����ѯ��ѵ����Ϣ
     */
	Hb68 getHb68ById(String chb068) throws Exception;
	
	/**
     * ���ݰ༶�����ѯ��ѵ��γ��б�
     */
	List<Hb69> getHb69ById(Hb69 hb69) throws Exception;
	/**
	 * ���ݰ༶�����ѯ��ѵ��γ��б���ᣩ
	 */
	List<Hb69> getHb69ById_df(Hb69 hb69);
	
	List<Hb69> getHb69ByChb068(Hb69 hb69) throws Exception;

	/**
	 * ��ѯ��ѵ��ϲ��б�
	 */
	List<Hb67> getMergeList(Hb68 hb68) throws Exception;
	
	/**
	 * ���ݺϲ��༶�����ѯ�ϲ��༶��Ϣ
	 */
	Hb67 getHb67ById(String chb067) throws Exception;
	
	/**
	 * ��ѯ�ϲ��༶��ѧԱ�б�
	 */
	List<Hc60> getHc60StuList(Hc60 hc60) throws Exception;
	
	/**
	 * ��ѯ�ϲ��༶�пγ��б�
	 */
	List<Hb69> getHb69MergeCourseList(Hb69 hb69) throws Exception;
	
	/**
	 * ����ѧԱid��ѯѧԱ��Ϣ
	 */
	Hc66 getHc66ById(Hc66 hc66) throws Exception;
	
	/**
	 * ����ѧԱid��ѯѧԱ������Ϣ
	 */
	List<Zc10> getZc10ById(Zc10 zc10) throws Exception;
	
	/**
     * ��ȡѧԱ�γ̿�����ϸ
     */
	List<Hb69DTO> attendanceQueryList(Hb69DTO hb69dto);


}