package com.insigma.mvc.serviceimp.train.PXYW_001_014;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.insigma.dto.AjaxReturnMsg;
import com.insigma.mvc.MvcHelper;
import com.insigma.mvc.dao.train.PXYW_001_014.ApiSubsidyMapper;
import com.insigma.mvc.model.train.Hb50;
import com.insigma.mvc.model.train.Hb50Dto;
import com.insigma.mvc.model.train.Hb67;
import com.insigma.mvc.model.train.Hc60;
import com.insigma.mvc.model.train.Student;
import com.insigma.mvc.service.train.PXYW_001_014.ApiSubsidyService;

/**
 * �����걨
 * @author Ace
 *
 */
@Service
public class ApiSubsidyServiceImpl extends MvcHelper implements ApiSubsidyService{

	@Resource
	private ApiSubsidyMapper apiSubsidyMapper;
	
	/**
	 * ������Ϣ�б�
	 * @param hb67 ���ڶ���
	 * @return
	 * @throws Exception
	 */
	@Override
	public PageInfo<Hb67> getHb67List(Hb67 hb67) throws Exception {
		List<Hb67> list = apiSubsidyMapper.getHb67List(hb67);
		PageInfo<Hb67> pageInfo = new PageInfo<Hb67>(list);
		return pageInfo;
	}

	/**
     * ���ɰ��ڲ�����Ϣ
     *
     * @param hb67 ���ڶ���
     * @return
     * @throws Exception
     */
	@Override
	@Transactional
	public AjaxReturnMsg generate(Hb67 hb67) throws Exception {

		Hb50 hb50 = new Hb50();
		hb50.setChb067(hb67.getChb067());
		hb50.setUserid(hb67.getUserid());
		hb50.setBaseinfoid(hb67.getBaseinfoid());
		//���ɲ��������
		int num = apiSubsidyMapper.addHb50(hb50);
		boolean flag = true;
		if(num == 1) {
			//����ѧԱ������Ϣ��
			num  = apiSubsidyMapper.addHc50(hb50);
			if( num <= 0 ) {
				flag = false;
			}
		}else{
			flag = false;
		}
		if(flag) {
			return this.success("������Ϣ�������");
		}else{
			return this.error("������Ϣ����ʧ��");
		}
	}

	/**
	 * ��ѯ������Ϣ
	 */
	@Override
	public Hb67 getHb67ById(String chb067) throws Exception {
		return apiSubsidyMapper.getHb67ById(chb067);
	}
	
	/**
	 * ��ѯ�ϲ��༶��ѧԱ�б�
	 */
	@Override
	public PageInfo<Hc60> getHc60StuList(Hc60 hc60) throws Exception {
		PageHelper.offsetPage(hc60.getOffset(), hc60.getLimit());
		List<Hc60> list=apiSubsidyMapper.getHc60StuList(hc60);
		PageInfo<Hc60> pageinfo = new PageInfo<Hc60>(list);
		return pageinfo;
	}
	
	/**
	 * ��ѯ������ѧԱ�����б�
	 */
	@Override
	public PageInfo<Student> subsidyList(Student stu) {
		PageHelper.offsetPage(stu.getOffset(), stu.getLimit());
		List<Student> list=apiSubsidyMapper.subsidyList(stu);
		PageInfo<Student> pageinfo = new PageInfo<Student>(list);
		return pageinfo;
	}
	
	/**
	 * ɾ��ѧԱ������Ϣ
	 * @param request
	 * @param stu ѧԱ����
	 * @return
	 */
	@Override
	public AjaxReturnMsg delete(Student stu) {
		int num = apiSubsidyMapper.delete(stu);
		if(num == 1) {
			return this.success("ɾ�����");
		}else{
			return this.error("ɾ��ʧ��");
		}
	}
	
	/**
	 *  ��ѯѧԱ��д��Ϣ 
	 */
	@Override
	public Student getHc50ById(Student stu) {
		return apiSubsidyMapper.getHc50ById(stu);
	}
	
	/**
	 *  ����ѧԱ������Ϣ
	 */
	@Override
	public AjaxReturnMsg update(Student stu) {
		int num = apiSubsidyMapper.update(stu);
		if(num == 1) {
			return this.success("����ɹ�");
		}else{
			return this.error("����ʧ��");
		}
	}
	
	/**
     * �������ɰ��ڲ�����Ϣ
     *
     * @param Hb50 ��������
     * @return
     * @throws Exception
     */
	@Override
	@Transactional
	public AjaxReturnMsg generateAgain(Hb50 hb50) {
		
		boolean flag = true;
		//ɾ������ѧԱ������Ϣ��
		int num = apiSubsidyMapper.deleteHc50(hb50);
		if(num > 0) {
			//ɾ�����в��������
			num = apiSubsidyMapper.deleteHb50(hb50);
			if(num == 1) {
				//���ɲ��������
				num = apiSubsidyMapper.addHb50(hb50);
				if(num == 1) {
					//����ѧԱ������Ϣ��
					num  = apiSubsidyMapper.addHc50(hb50);
					if( num <= 0 ) {
						flag = false;
					}
				}else{
					flag = false;
				}
				
			}else{
				flag = false;
			}
		}else{
			flag = false;
		}
		if(flag) {
			return this.success("������Ϣ�������");
		}else{
			return this.error("������Ϣ����ʧ��");
		}
	}

	/**
     * �ύ���ڲ�����Ϣ
     *
     * @param hb67 ���ڶ���
     * @return
     * @throws Exception
     */
	@Override
	public AjaxReturnMsg submit(Hb67 hb67) {
		int num = apiSubsidyMapper.submit(hb67);
		if(num == 1) {
			return this.success("�ύ�ɹ�");
		}else{
			return this.error("�ύʧ��");
		}
	}
	@Override
	public AjaxReturnMsg getSubsidyById(String chb050) {
		Hb50Dto hb50 = apiSubsidyMapper.getSubsidyById(chb050);
		return this.success(hb50);
	}
	
	/**
	 * ������ѵ����ѧԱ������
	 */
	@Override
	public List<Student> getexportStuBT(Student stu){
		List<Student> list = apiSubsidyMapper.getexportStuBT(stu);
        return list;
	}
}
