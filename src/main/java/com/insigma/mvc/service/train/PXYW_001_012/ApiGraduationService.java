package com.insigma.mvc.service.train.PXYW_001_012;

import java.util.HashMap;
import java.util.List;

import com.github.pagehelper.PageInfo;
import com.insigma.mvc.model.train.Hb67;
import com.insigma.mvc.model.train.Hb68;
import com.insigma.mvc.model.train.Hc60;
import com.insigma.mvc.model.train.Student;

/**
 * �༶��ҵ����
 * @author link
 * 2018-03-14
 */
public interface ApiGraduationService {

	/**
	 * ��ѯ�ϲ��༶��Ϣ�б�
	 */
	public PageInfo<Hb67> getHb67List(Hb67 hb67) throws Exception;
	
	/**
	 * ��ѯδ�ϲ��༶��Ϣ�б�
	 */
	public PageInfo<Hb68> getHb68List(Hb68 hb68) throws Exception;
	
	/**
     * ���ݰ༶��Ų�ѯ��ϸ��Ϣ
     */
	public Hb68 getHb68ByChb068(Hb68 hb68) throws Exception;
	/**
	 * ���ݺϲ��༶�����ѯ�ϲ��༶��Ϣ
	 */
	public Hb67 getHb67ById(String chb067) throws Exception;
	/**
	 * ��ѯ�ϲ��༶�кϸ�ѧԱ�б�
	 */
	public PageInfo<Hc60> mergeHGStuList(Hc60 hc60) throws Exception;
	/**
	 * �ϲ��༶��Ϣ����
	 */
	public void addMargeClass(Hb67 hb67) throws Exception;
	
	/**
	 * ���ڽ�ҵ״̬�޸�
	 */
	public void updateChb528(Hb67 hb67) throws Exception;

	/**
	 * ����������
	 * @param hb67
	 * @return
	 */
	List<HashMap> toExp(Student stu) throws Exception ;
	
}
