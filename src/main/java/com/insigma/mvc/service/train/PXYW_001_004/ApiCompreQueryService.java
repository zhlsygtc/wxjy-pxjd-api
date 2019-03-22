package com.insigma.mvc.service.train.PXYW_001_004;

import com.github.pagehelper.PageInfo;
import com.insigma.mvc.model.train.Hb67;
import com.insigma.mvc.model.train.Hb68;
import com.insigma.mvc.model.train.Hb69;
import com.insigma.mvc.model.train.Hb69DTO;
import com.insigma.mvc.model.train.Hc60;
import com.insigma.mvc.model.train.Hc66;
import com.insigma.mvc.model.train.Zc10;

/**
 * �ۺϲ�ѯ
 * @author link
 * 2018-01-29
 */
public interface ApiCompreQueryService {
	
	/**
	 * ��ѯ��ѵ�������Ϣ�б�
	 */
	public PageInfo<Hb68> getInfoList(Hb68 hb68) throws Exception;

	/**
	 * ���ݺϲ���༶��ϲ�ǰ�༶�б�
	 */
	public PageInfo<Hb68> getInfoListByChb067(Hb67 hb67) throws Exception;
	
	/**
     * ���ݰ༶�����ѯ��ѵ����ѧ���б�
     */
	public PageInfo<Hc60> getHc60ById(Hc60 hc60) throws Exception;
	
	/**
     * ���ݰ༶�����ѯ��ѵ����Ϣ
     */
	public Hb68 getHb68ById(String chb068) throws Exception;
	
	/**
     * ���ݰ༶�����ѯ��ѵ��γ��б�
     */
	public PageInfo<Hb69> getHb69ById(Hb69 hb69) throws Exception;
	public PageInfo<Hb69> getHb69ByChb068(Hb69 hb69) throws Exception;

	/**
	 * ��ѯ��ѵ��ϲ��б�
	 */
	public PageInfo<Hb67> getMergeList(Hb68 hb68) throws Exception;
	
	/**
	 * ���ݺϲ��༶�����ѯ�ϲ��༶��Ϣ
	 */
	public Hb67 getHb67ById(String chb067) throws Exception;
	
	/**
	 * ��ѯ�ϲ��༶��ѧԱ�б�
	 */
	public PageInfo<Hc60> getHc60StuList(Hc60 hc60) throws Exception;
	
	/**
	 * ��ѯ�ϲ��༶�пγ��б�
	 */
	public PageInfo<Hb69> getHb69MergeCourseList(Hb69 hb69) throws Exception;
	
	/**
	 * ����ѧԱid��ѯѧԱ��ϸ��Ϣ
	 */
	public Hc66 getHc66ById(Hc66 hc66) throws Exception;
	
	/**
	 * ����ѧԱid��ѯѧԱ������Ϣ�б�
	 */
	public PageInfo<Zc10> getZc10ById(Zc10 zc10) throws Exception;
	
	/**
     * ��ȡѧԱ�γ̿�����ϸ
     */
	public PageInfo<Hb69DTO> attendanceQueryList(Hb69DTO hb69dto);
}
