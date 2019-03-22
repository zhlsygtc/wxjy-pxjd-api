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
	 * 获取合并班级信息列表
	 */
	@Override
	public PageInfo<Hb67> getClasssList(Hb67 hb67) {
		PageHelper.offsetPage(hb67.getOffset(), hb67.getLimit());
		List<Hb67> list=apiSkillTrnSubsidyMapper.getClasssList(hb67);
		PageInfo<Hb67> pageinfo = new PageInfo<>(list);
		return pageinfo;
	}

	/**
	 * 获取合并班级下的学员信息列表
	 */
	@Override
	public PageInfo<Student> getStuList(Student stu) {
		PageHelper.offsetPage(0, 1000);
		List<Student> list=apiSkillTrnSubsidyMapper.getStuList(stu);
		PageInfo<Student> pageinfo = new PageInfo<>(list);
		return pageinfo;
	}

	/**
	 * 申请保存
	 */
	@Override
	public AjaxReturnMsg saveStu(Student stu) {
		String[] chc001s = stu.getSelectnodes().split(",");
		int imp_num = 0;
		String eec100 = "";
		for(String chc001:chc001s) {
			//通过学员内码获取CE01
			Ce01 ce01 = apiSkillTrnSubsidyMapper.getCe01(chc001);
			if(ce01 == null) {
				//获取对应学员姓名  ---  此处不选择页面拼接传递过来是考虑到不在就业库的人员很少
				String aac003 = apiSkillTrnSubsidyMapper.getHc60(chc001);
				return this.error("学员["+aac003+"]未在就业库，请先在大丰就业注册登记或请大丰区公共就业服务管理信息系统工作人员帮忙系统登记");
			}
			stu.setAac001(ce01.getAac001());
			//校验是否重复申请
			String num = apiSkillTrnSubsidyMapper.check(ce01.getAac001(),stu.getChb067());
			if(!"0".equals(num)) {
				String aac003 = apiSkillTrnSubsidyMapper.getHc60(chc001);
				return this.error("学员["+aac003+"]已申请技能培训补贴，不能重复申请");
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
		return this.success("申请成功");
	}

	/**
	 * 根据合并班级内码查询合并班级信息 
	 */
	@Override
	public Hb67 getHb67ById(String chb067) {
		return apiSkillTrnSubsidyMapper.getHb67ById(chb067);
	}

	/**
	 * 学员申请补贴信息
	 */
	@Override
	public PageInfo<Student> getApplyStusForLook(Student stu) {
		PageHelper.offsetPage(stu.getOffset(), stu.getLimit());
		List<Student> list=apiSkillTrnSubsidyMapper.getApplyStusForLook(stu);
		PageInfo<Student> pageinfo = new PageInfo<>(list);
		return pageinfo;
	}
	
}
