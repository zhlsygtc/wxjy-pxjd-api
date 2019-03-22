package com.insigma.mvc.service.train.PXYW_001_012;

import java.util.HashMap;
import java.util.List;

import com.github.pagehelper.PageInfo;
import com.insigma.mvc.model.train.Hb67;
import com.insigma.mvc.model.train.Hb68;
import com.insigma.mvc.model.train.Hc60;
import com.insigma.mvc.model.train.Student;

/**
 * 班级结业管理
 * @author link
 * 2018-03-14
 */
public interface ApiGraduationService {

	/**
	 * 查询合并班级信息列表
	 */
	public PageInfo<Hb67> getHb67List(Hb67 hb67) throws Exception;
	
	/**
	 * 查询未合并班级信息列表
	 */
	public PageInfo<Hb68> getHb68List(Hb68 hb68) throws Exception;
	
	/**
     * 根据班级编号查询详细信息
     */
	public Hb68 getHb68ByChb068(Hb68 hb68) throws Exception;
	/**
	 * 根据合并班级内码查询合并班级信息
	 */
	public Hb67 getHb67ById(String chb067) throws Exception;
	/**
	 * 查询合并班级中合格学员列表
	 */
	public PageInfo<Hc60> mergeHGStuList(Hc60 hc60) throws Exception;
	/**
	 * 合并班级信息保存
	 */
	public void addMargeClass(Hb67 hb67) throws Exception;
	
	/**
	 * 班期结业状态修改
	 */
	public void updateChb528(Hb67 hb67) throws Exception;

	/**
	 * 导出花名册
	 * @param hb67
	 * @return
	 */
	List<HashMap> toExp(Student stu) throws Exception ;
	
}
