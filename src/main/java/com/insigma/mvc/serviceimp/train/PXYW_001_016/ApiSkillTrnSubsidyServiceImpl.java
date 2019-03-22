package com.insigma.mvc.serviceimp.train.PXYW_001_016;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.insigma.dto.AjaxReturnMsg;
import com.insigma.mvc.MvcHelper;
import com.insigma.mvc.dao.train.PXYW_001_016.ApiSkillTrnSubsidyMapper;
import com.insigma.mvc.model.Ce01;
import com.insigma.mvc.model.train.Hb67;
import com.insigma.mvc.model.train.Student;
import com.insigma.mvc.service.train.PXYW_001_016.ApiSkillTrnSubsidyService;

@Service
@Transactional(rollbackFor=Exception.class)
public class ApiSkillTrnSubsidyServiceImpl extends MvcHelper implements ApiSkillTrnSubsidyService{
	
	@Resource
	private ApiSkillTrnSubsidyMapper apiSkillTrnSubsidyMapper;

	/**
	 * ��ȡ�ϲ��༶��Ϣ�б�
	 */
	@Override
	public PageInfo<Hb67> getClasssList(Hb67 hb67) {
		PageHelper.offsetPage(hb67.getOffset(), hb67.getLimit());
		List<Hb67> list=apiSkillTrnSubsidyMapper.getClasssList(hb67);
		PageInfo<Hb67> pageinfo = new PageInfo<>(list);
		return pageinfo;
	}

	/**
	 * ��ȡ�ϲ��༶�µ�ѧԱ��Ϣ�б�
	 */
	@Override
	public PageInfo<Student> getStuList(Student stu) {
		PageHelper.offsetPage(0, 1000);
		List<Student> list=apiSkillTrnSubsidyMapper.getStuList(stu);
		PageInfo<Student> pageinfo = new PageInfo<>(list);
		return pageinfo;
	}

	/**
	 * ���뱣��
	 */
	@Override
	public AjaxReturnMsg saveStu(Student stu) {
		String[] chc001s = stu.getSelectnodes().split(",");
		int imp_num = 0;
		String eec100 = "";
		for(String chc001:chc001s) {
			//ͨ��ѧԱ�����ȡCE01
			Ce01 ce01 = apiSkillTrnSubsidyMapper.getCe01(chc001);
			if(ce01 == null) {
				//��ȡ��ӦѧԱ����  ---  �˴���ѡ��ҳ��ƴ�Ӵ��ݹ����ǿ��ǵ����ھ�ҵ�����Ա����
				String aac003 = apiSkillTrnSubsidyMapper.getHc60(chc001);
				return this.error("ѧԱ["+aac003+"]δ�ھ�ҵ�⣬�����ڴ���ҵע��Ǽǻ�������������ҵ���������Ϣϵͳ������Ա��æϵͳ�Ǽ�");
			}
			stu.setAac001(ce01.getAac001());
			//У���Ƿ��ظ�����
			String num = apiSkillTrnSubsidyMapper.check(ce01.getAac001(),stu.getChb067());
			if(!"0".equals(num)) {
				String aac003 = apiSkillTrnSubsidyMapper.getHc60(chc001);
				return this.error("ѧԱ["+aac003+"]�����뼼����ѵ�����������ظ�����");
			}
			if("".equals(eec100)) {
				SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMdd");
				eec100 = sdf.format(new Date());
				String eec100_suffix = apiSkillTrnSubsidyMapper.getEec100_suffix();
				eec100 = eec100 + eec100_suffix;
			}
			imp_num++;
			stu.setParam(eec100);
			stu.setChc066(imp_num+"");
			stu.setChc001(chc001);
			apiSkillTrnSubsidyMapper.save(stu);	
		}
		apiSkillTrnSubsidyMapper.insertDd41(stu);
		return this.success("����ɹ�");
	}

	/**
	 * ���ݺϲ��༶�����ѯ�ϲ��༶��Ϣ 
	 */
	@Override
	public Hb67 getHb67ById(String chb067) {
		return apiSkillTrnSubsidyMapper.getHb67ById(chb067);
	}

	/**
	 * ѧԱ���벹����Ϣ
	 */
	@Override
	public PageInfo<Student> getApplyStusForLook(Student stu) {
		PageHelper.offsetPage(stu.getOffset(), stu.getLimit());
		List<Student> list=apiSkillTrnSubsidyMapper.getApplyStusForLook(stu);
		PageInfo<Student> pageinfo = new PageInfo<>(list);
		return pageinfo;
	}
	
}
