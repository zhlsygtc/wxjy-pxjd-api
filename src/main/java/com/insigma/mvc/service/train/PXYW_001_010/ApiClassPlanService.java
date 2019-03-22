package com.insigma.mvc.service.train.PXYW_001_010;

import com.github.pagehelper.PageInfo;
import com.insigma.dto.AjaxReturnMsg;
import com.insigma.mvc.model.CodeValue;
import com.insigma.mvc.model.train.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;
/**
 * ����ƻ�
 * @author zhanghl
 * 2018-04-25
 */
public interface ApiClassPlanService {
	/**
	 * ��ѯ����ƻ���Ϣ�б�
	 */
	public PageInfo<Hb65> getPlanList(Hb65 hb65) throws Exception;
	/**
	 * ������ѵ�ƻ���Ϣ
	 */
	public AjaxReturnMsg addPlan(Hb65 hb65) throws Exception;
	/**
	 * ����ID��ѯ��ѵ�ƻ���Ϣ
	 */
	public Hb65 getPlanById(Hb65 hb65) throws Exception;
	/**
	 * ��ȡ��λ����
	 */
	AjaxReturnMsg<List<CodeValue>> getAca111List(CodeValue codevalue);
	/**
	 * ��ȡ��λ���ʵȼ�
	 */
	AjaxReturnMsg<List<CodeValue>> getAca11aList(String id, String aca111);
	/**
	 * ��ȡ�ϼ�����
	 */
	AjaxReturnMsg<List<CodeValue>> getAab301List();
	/**
	 * ��ѯ�ù�˾������������
	 */
	public SmtGroup getCompanyAab301(String groupId) throws Exception;
	/**
	 * ����רҵ���Ʋ�ѯ��ѵ�������Ƽ�רҵ���
	 */
	public Hb65 getAca112(String aca111) throws Exception;
	/**
	 * ����id��ȡsmtgroup
	 */
	public SmtGroup getSmtGroupById(String groupId) throws Exception;
	/**
	 * �鿴��ѵѧԱ��Ϣ
	 */
	public PageInfo<Student> signedStuForLook(Student stu) throws Exception;
	/**
	 * ��������ɾ��������Ϣ(��Ϊ��Ч)
	 */
	public AjaxReturnMsg<String> delPlan(Hb65 hb65);
	/**
	 * �ı���ѵ�ƻ�����״̬
	 */
	public AjaxReturnMsg<String> changePlan(Hb65 hb65);
	/**
	 * ����
	 */
	public AjaxReturnMsg signUp(Student stu) throws Exception;
	/**
	 * ����ID��ѯ��ѵѧԱ��Ϣ
	 */
	public Hc61 findStuById(Student stu) throws Exception;
	/**
	 * ����ID��ѯ��ѵѧԱ������Ϣ
	 */
	public Hc61 findFileById(Student stu) throws Exception;
	/**
	 * �ϴ�ѧԱͷ��
	 */
	AjaxReturnMsg uploadHc61Photo(String userid, String file_bus_id, String file_name, MultipartFile multipartFile);
	 /**
	  * �ϴ�ѧԱ����ļ�
     * @param request
     * @param multipartFile
     * @return
     * @throws Exception
     */
    AjaxReturnMsg fileUpload(HttpServletRequest request, MultipartFile multipartFile) throws Exception;
    /**
     * ����idɾ��ͼƬ
     */
	public AjaxReturnMsg<String> delPicById(Student stu);
	/**
	 *��������ѧԱ ��������
	 */
	public AjaxReturnMsg signUpSave(Hc61_temp hc61Temp) throws Exception;
	/**
	 * ��ȡ����word������Ϣ
	 */
	public AjaxReturnMsg exportDJK(Student stu);
}
