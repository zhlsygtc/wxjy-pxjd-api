package com.insigma.mvc.serviceimp.train.PXYW_001_012;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.insigma.mvc.MvcHelper;
import com.insigma.mvc.dao.train.PXYW_001_001.ApiCompanyMapper;
import com.insigma.mvc.dao.train.PXYW_001_012.ApiGraduationMapper;
import com.insigma.mvc.model.train.Hb66;
import com.insigma.mvc.model.train.Hb67;
import com.insigma.mvc.model.train.Hb68;
import com.insigma.mvc.model.train.Hc60;
import com.insigma.mvc.model.train.Student;
import com.insigma.mvc.service.train.PXYW_001_012.ApiGraduationService;
/**
 * 班级结业管理
 * @author link
 * 2018-03-14
 */
@Service
@Transactional(rollbackFor=Exception.class)
public class ApiGraduationServiceImpl extends MvcHelper implements ApiGraduationService {

	@Resource
	private ApiGraduationMapper apiGraduationMapper;
	
	@Resource
	private ApiCompanyMapper apiCompanyMapper;
	
	/**
	 * 初始化合并班期信息列表 
	 */
	@Override
	public PageInfo<Hb67> getHb67List(Hb67 hb67) throws Exception {
		List<Hb67> list = apiGraduationMapper.getHb67List(hb67);
		PageInfo<Hb67> pageInfo = new PageInfo<Hb67>(list);
		return pageInfo;
	}

	/**
	 * 初始化培训班基础信息列表 
	 */
	@Override
	public PageInfo<Hb68> getHb68List(Hb68 hb68) throws Exception {
		PageHelper.offsetPage(0, 100);
		List<Hb68> list = apiGraduationMapper.getHb68List(hb68);
		PageInfo<Hb68> pageInfo = new PageInfo<Hb68>(list);
		return pageInfo;
	}

	/**
     * 根据班级编号查询详细信息
     */
	@Override
	public Hb68 getHb68ByChb068(Hb68 hb68) throws Exception {
		Hb68 newHb68 = new Hb68();
		newHb68.setChb068(hb68.getChb068().split(",")[0]);
		newHb68 = apiGraduationMapper.getHb68ByChb068(newHb68);
		hb68.setChb068s(hb68.getChb068().split(","));
		newHb68.setChb106(apiGraduationMapper.getChb106All(hb68).get(0).getChb106());
		return newHb68;
	}
	/**
	 * 根据合并班级内码查询合并班级信息
	 */
	@Override
	public Hb67 getHb67ById(String chb067) throws Exception {
		return apiGraduationMapper.getHb67ById(chb067);
	}
	/**
	 * 查询合并班级中学员列表
	 */
	@Override
	public PageInfo<Hc60> mergeHGStuList(Hc60 hc60) throws Exception {
		PageHelper.offsetPage(hc60.getOffset(), hc60.getLimit());
		List<Hc60> list=apiGraduationMapper.mergeHGStuList(hc60);
		PageInfo<Hc60> pageinfo = new PageInfo<Hc60>(list);
		return pageinfo;
	}
	/**
	 * 合并班级信息保存
	 */
	@Override
	public void addMargeClass(Hb67 hb67) throws Exception {
		String uuid = UUID.randomUUID().toString().replaceAll("-", "");
		hb67.setAae036(new Date());
		hb67.setAae100("1");
		hb67.setChb067(uuid);
		hb67.setChb528("0");
		hb67.setChb316("0");
		hb67.setChb188("1");
		int num = apiGraduationMapper.addMargeClass(hb67);
		if(num>0){
			Hb68 hb68 = new Hb68();
			String [] chb068s = hb67.getChb068().split(",");
			hb68.setChb068s(chb068s);
			int index = 0;
			for(int i=0;i<chb068s.length;i++) {
				Hb66 hb66 = new Hb66();
				hb66.setChb067(uuid);
				hb66.setChb068(chb068s[i]);
				apiGraduationMapper.addHb66(hb66);
				index++;
			}
			int num2 = apiGraduationMapper.updateClass(hb68);
			if(num2>0 && index==chb068s.length) {
				return;
			}else {
				throw new Exception("保存错误");
			}
		}else {
			return;
		}
	}

	/**
	 * 班期结业状态修改
	 */
	@Override
	public void updateChb528(Hb67 hb67) throws Exception {
		hb67.setChb067s(hb67.getChb067().split(","));
		int num = apiGraduationMapper.updateHb67Chb528(hb67);
		if(num>0) {
			List<Hb66> list = apiGraduationMapper.getHb66ByChb067(hb67);
			String chb068s ="";
			for(int i=0; i<list.size(); i++) {
				chb068s += list.get(i).getChb068() + ",";
			}
			chb068s = chb068s.substring(0,chb068s.length()-1);
			if(list.size() == chb068s.split(",").length) {
				Hb68 hb68 = new Hb68();
				hb68.setChb068s(chb068s.split(","));
				int num2 = apiGraduationMapper.updateHb68Chb528(hb68);
				if(num2>0) {
					return;
				}else {
					throw new Exception("班级结业失败");
				}
			}else {
				throw new Exception("班级结业失败");
			}

		}else {
			return;
		}
	}

	@Override
	public List<HashMap> toExp(Student stu) throws Exception {
		return apiGraduationMapper.getStuList(stu);
	}
	

}
