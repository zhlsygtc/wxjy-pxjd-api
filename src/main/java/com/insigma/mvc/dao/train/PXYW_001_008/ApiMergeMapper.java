package com.insigma.mvc.dao.train.PXYW_001_008;

import java.util.List;

import com.insigma.mvc.model.train.Hb66;
import com.insigma.mvc.model.train.Hb67;
import com.insigma.mvc.model.train.Hb68;
import com.insigma.mvc.model.train.Hc60;
/**
 * �༶��ҵ����
 * @author link 
 * 2018-03-14
 */
public interface ApiMergeMapper {

	/**
	 * ��ѯ�ϲ��༶��Ϣ�б�
	 */
	List<Hb67> getHb67List(Hb67 hb67) throws Exception;
	
	/**
	 * ��ѯδ�ϲ��༶��Ϣ�б�
	 */
	List<Hb68> getHb68List(Hb68 hb68) throws Exception;
	
	/**
     * ���ݰ༶��Ų�ѯ��ϸ��Ϣ
     */
	Hb68 getHb68ByChb068(Hb68 hb68) throws Exception;
	
	/**
	 * ��ȡ�༶��Ϣ
	 */
	List<Hb68> getChb106All(Hb68 hb68) throws Exception;
	/**
	 * ���ݺϲ��༶�����ѯ�ϲ��༶��Ϣ
	 */
	Hb67 getHb67ById(String chb067) throws Exception;
	/**
	 * ��ѯ�ϲ��༶�кϸ�ѧԱ�б�
	 */
	List<Hc60> mergeHGStuList(Hc60 hc60) throws Exception;
	/**
	 * ��Ӻϲ�����
	 */
	int addMargeClass(Hb67 hb67);
	
	/**
	 * �޸İ༶�ϲ�״̬
	 */
	int updateClass(Hb68 hb68);
	
	/**
	 * ��Ӻϲ��༶��Ϣ��
	 */
	void addHb66(Hb66 hb66);
	
	/**
	 * �޸İ��ڽ�ҵ״̬
	 */
	int updateHb67Chb528(Hb67 hb67);
	
	/**
	 * ��ѯ�ϲ�������Ϣ����ȡ�༶����
	 */
	List<Hb66> getHb66ByChb067(Hb67 hb67);
	
	/**
	 * �޸İ༶��ҵ״̬
	 */
	int updateHb68Chb528(Hb68 hb68);
}
