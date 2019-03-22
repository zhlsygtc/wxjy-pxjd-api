package com.insigma.mvc.dao.train.PXYW_001_008;

import java.util.List;

import com.insigma.mvc.model.train.Hb66;
import com.insigma.mvc.model.train.Hb67;
import com.insigma.mvc.model.train.Hb68;
import com.insigma.mvc.model.train.Hc60;
/**
 * 班级结业管理
 * @author link 
 * 2018-03-14
 */
public interface ApiMergeMapper {

	/**
	 * 查询合并班级信息列表
	 */
	List<Hb67> getHb67List(Hb67 hb67) throws Exception;
	
	/**
	 * 查询未合并班级信息列表
	 */
	List<Hb68> getHb68List(Hb68 hb68) throws Exception;
	
	/**
     * 根据班级编号查询详细信息
     */
	Hb68 getHb68ByChb068(Hb68 hb68) throws Exception;
	
	/**
	 * 获取班级信息
	 */
	List<Hb68> getChb106All(Hb68 hb68) throws Exception;
	/**
	 * 根据合并班级内码查询合并班级信息
	 */
	Hb67 getHb67ById(String chb067) throws Exception;
	/**
	 * 查询合并班级中合格学员列表
	 */
	List<Hc60> mergeHGStuList(Hc60 hc60) throws Exception;
	/**
	 * 添加合并班期
	 */
	int addMargeClass(Hb67 hb67);
	
	/**
	 * 修改班级合并状态
	 */
	int updateClass(Hb68 hb68);
	
	/**
	 * 添加合并班级信息表
	 */
	void addHb66(Hb66 hb66);
	
	/**
	 * 修改班期结业状态
	 */
	int updateHb67Chb528(Hb67 hb67);
	
	/**
	 * 查询合并班期信息表，获取班级内码
	 */
	List<Hb66> getHb66ByChb067(Hb67 hb67);
	
	/**
	 * 修改班级结业状态
	 */
	int updateHb68Chb528(Hb68 hb68);
}
