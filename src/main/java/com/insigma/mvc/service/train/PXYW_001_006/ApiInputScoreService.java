package com.insigma.mvc.service.train.PXYW_001_006;

import com.github.pagehelper.PageInfo;
import com.insigma.dto.AjaxReturnMsg;
import com.insigma.mvc.model.train.*;
/**
 * ��������
 * @author zhanghl
 * 2018-01-10
 */
public interface ApiInputScoreService {
	/**
	 * ��ѯ��ѵҵ���౸���б�
	 */
	public PageInfo<Hb68> getInfoList(Hb68 hb68) throws Exception;
	/**
	 * ����ID��ѯ�༶��Ϣ
	 */
	public Hb68 getById(Hb68 hb68) throws Exception;
	/**
	 * ����רҵ���Ʋ�ѯ��ѵ�������Ƽ�רҵ���
	 */
	public Hb68 getAca112(String aca110) throws Exception;
	/**
	 * ��ѯ��ѵѧԱ��Ϣ
	 */
	public PageInfo<Student> getStuListForLook(Student stu) throws Exception;
	/**
	 * ����ѧԱ�ɼ�--��ѡ�ϸ��־
	 */
	public AjaxReturnMsg doQualified(Student stu) throws Exception;
	/**
	 * ����ѧԱ�ɼ�--�ֹ�¼��
	 */
	public AjaxReturnMsg saveScore(Student stu) throws Exception;
	/**
	 * �ύ�༶�ɼ���Ϣ
	 */
	public AjaxReturnMsg submitScore(Hb68 hb68) throws Exception;
	/**
	 * �����ɼ���Ϣ
	 */
	public AjaxReturnMsg<String> revokeScore(Hb68 hb68) throws Exception;
	/**
	 * ��ѯ�ɼ���׼
	 */
	public Zc13 getZc13(String aab001) throws Exception;
}
