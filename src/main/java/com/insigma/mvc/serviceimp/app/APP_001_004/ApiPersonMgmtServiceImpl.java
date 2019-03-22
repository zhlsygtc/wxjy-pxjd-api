package com.insigma.mvc.serviceimp.app.APP_001_004;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.insigma.common.util.UUIDGenerator;
import com.insigma.dto.AjaxReturnMsg;
import com.insigma.mvc.MvcHelper;
import com.insigma.mvc.dao.app.APP_001_001.ApiCommonMapper;
import com.insigma.mvc.dao.app.APP_001_004.ApiPersonMgmtMapper;
import com.insigma.mvc.model.train.Hb65;
import com.insigma.mvc.model.train.Hc61;
import com.insigma.mvc.model.train.Student;
import com.insigma.mvc.service.app.APP_001_004.ApiPersonMgmtService;

@Service
public class ApiPersonMgmtServiceImpl extends MvcHelper implements ApiPersonMgmtService {

	@Resource
	ApiPersonMgmtMapper apiPersonMgmtMapper;
	
	@Resource
	ApiCommonMapper apiCommonMapper;
	
	/**
	 * 学员报名计划开始
	 */
	@Override
	public AjaxReturnMsg signup(Student stu) {
		
		Hc61 hc61 = apiCommonMapper.getStudentInfo(stu);
		
		Map map = new HashMap();
		
		//检查是否已登记了学员信息
		if(hc61 == null) {
			map.put("status", "0");
			return this.success("请先登记学员信息", map);
		}
		stu.setChc111(hc61.getChc111());
		
		//查询学员是否已报名该计划
		String count = apiPersonMgmtMapper.findStuWithPlan(stu);
		if(!count.equals("0")) {
			map.put("status", "2");
			return this.error("您已报名该计划，不能重复报名", map);
		}
		
		//查询学员一年内是否已参加培训
		String num = apiPersonMgmtMapper.checkNum(stu);
		if(!num.equals("0")) {
			map.put("status", "2");
			return this.error("报名失败，一年内只能参加一次培训", map);
		}
		
		//查询当前要报名的计划
		Hb65 hb65 = apiPersonMgmtMapper.getCurrentPlan(stu);
		
		hb65.setChc111(hc61.getChc111());
		
		//校验之前是否有同工种的较高等级培训
		String index = apiPersonMgmtMapper.checkGrade(hb65);
		if(!index.equals("0")) {
			map.put("status", "2");
			return this.error("报名失败，您已参加过更高等级的该专业培训。", map);
		}else{
			//校验之前是否有同工种的同等级培训
			hb65.setFlag("1");
			index = apiPersonMgmtMapper.checkGrade(hb65);
			if(!index.equals("0")) {
				map.put("status", "2");
				return this.error("报名失败，您已参加过同等级的该专业培训。", map);
			}
		}
		
		map.put("status", "1");
		return this.success("完善信息",map);
		
	}

	/**
     * 获取学员基本信息
     */
	@Override
	public AjaxReturnMsg getStudentInfo(Student stu) {
		stu = apiPersonMgmtMapper.getStudentInfo(stu);
		return this.success(stu);
	}

	/**
     * 获取人(学)员基本信息
     */
	@Override
	public AjaxReturnMsg getPersonInfo(Student stu) {
		
		Hc61 hc61 = apiCommonMapper.getStudentInfo(stu);
		if(hc61 == null) {
			stu = apiPersonMgmtMapper.getPersonInfo(stu);
		}else{
			stu.setChc111(hc61.getChc111());
			stu = apiPersonMgmtMapper.getStudentInfo(stu);
		}
		return this.success(stu);
	}

	/**
     * 保存学员信息
     */
	@Override
	public AjaxReturnMsg saveStudent(Student stu) {
		
		if(stu.getChc111()==null || "".equals(stu.getChc111())) {
			String nextNo = apiPersonMgmtMapper.getNextNo();
			stu.setChc111(UUIDGenerator.generate());
			stu.setAac001("0");
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
			String dataS = stu.getAac002().substring(6, 14);
			Date aac006 = null;
			try {
				aac006 = sdf.parse(dataS);
			} catch (ParseException e) {
				return this.error("时间转换出错");
			}
			stu.setAac006(aac006);
			String str = stu.getAac002().substring(16, 17);
			int i = Integer.parseInt(str) % 2;
			if(i == 0) {
				stu.setAac004("2");
			}else{
				stu.setAac004("1");
			}
			
			stu.setAac006(aac006);
			stu.setIsselected("0");
			stu.setEae052("1");
			stu.setAae100("1");
			stu.setAab001(stu.getBaseinfoid());
			stu.setChc066(nextNo);
			
			int num = apiPersonMgmtMapper.saveStu(stu);
			if(num == 1) {
				Map map = new HashMap();
				map.put("chc111", stu.getChc111());
				return this.success("保存成功", map);
			}else{
				return this.error("保存失败");
			}
		}else{
			stu.setAae036(new Date());
			int num = apiPersonMgmtMapper.updateStu(stu);
			Map map = new HashMap();
			map.put("chc111", stu.getChc111());
			if(num == 1) {
				return this.success("保存成功", map);
			}else{
				return this.error("保存失败");
			}
		}
		
		
	}
	
	/**
     * 学员报名计划结束
     * @param stu 学员对象
     * @throws Exception
     */
	@Override
	public AjaxReturnMsg apply(Student stu) {
		int num = apiPersonMgmtMapper.apply(stu);
		if(num == 1) {
			return this.success("报名成功");
		}else{
			return this.error("报名失败");
		}
	}
	
	
	
}
