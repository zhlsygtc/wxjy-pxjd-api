package com.insigma.mvc.service.train.PXYW_001_004;

import com.github.pagehelper.PageInfo;
import com.insigma.mvc.model.train.Hb67;
import com.insigma.mvc.model.train.Hb68;
import com.insigma.mvc.model.train.Hb69;
import com.insigma.mvc.model.train.Hb69DTO;
import com.insigma.mvc.model.train.Hc60;
import com.insigma.mvc.model.train.Hc66;
import com.insigma.mvc.model.train.Zc10;

/**
 * 综合查询
 * @author link
 * 2018-01-29
 */
public interface ApiCompreQueryService {
	
	/**
	 * 查询培训班基础信息列表
	 */
	public PageInfo<Hb68> getInfoList(Hb68 hb68) throws Exception;

	/**
	 * 根据合并后班级查合并前班级列表
	 */
	public PageInfo<Hb68> getInfoListByChb067(Hb67 hb67) throws Exception;
	
	/**
     * 根据班级内码查询培训班中学生列表
     */
	public PageInfo<Hc60> getHc60ById(Hc60 hc60) throws Exception;
	
	/**
     * 根据班级内码查询培训班信息
     */
	public Hb68 getHb68ById(String chb068) throws Exception;
	
	/**
     * 根据班级内码查询培训班课程列表
     */
	public PageInfo<Hb69> getHb69ById(Hb69 hb69) throws Exception;
	public PageInfo<Hb69> getHb69ByChb068(Hb69 hb69) throws Exception;

	/**
	 * 查询培训班合并列表
	 */
	public PageInfo<Hb67> getMergeList(Hb68 hb68) throws Exception;
	
	/**
	 * 根据合并班级内码查询合并班级信息
	 */
	public Hb67 getHb67ById(String chb067) throws Exception;
	
	/**
	 * 查询合并班级中学员列表
	 */
	public PageInfo<Hc60> getHc60StuList(Hc60 hc60) throws Exception;
	
	/**
	 * 查询合并班级中课程列表
	 */
	public PageInfo<Hb69> getHb69MergeCourseList(Hb69 hb69) throws Exception;
	
	/**
	 * 根据学员id查询学员详细信息
	 */
	public Hc66 getHc66ById(Hc66 hc66) throws Exception;
	
	/**
	 * 根据学员id查询学员考勤信息列表
	 */
	public PageInfo<Zc10> getZc10ById(Zc10 zc10) throws Exception;
	
	/**
     * 获取学员课程考勤明细
     */
	public PageInfo<Hb69DTO> attendanceQueryList(Hb69DTO hb69dto);
}
