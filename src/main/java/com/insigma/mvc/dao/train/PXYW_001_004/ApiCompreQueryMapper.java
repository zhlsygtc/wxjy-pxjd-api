package com.insigma.mvc.dao.train.PXYW_001_004;

import java.util.List;

import com.insigma.mvc.model.train.Hb67;
import com.insigma.mvc.model.train.Hb68;
import com.insigma.mvc.model.train.Hb69;
import com.insigma.mvc.model.train.Hb69DTO;
import com.insigma.mvc.model.train.Hc60;
import com.insigma.mvc.model.train.Hc66;
import com.insigma.mvc.model.train.Zc10;
/**
 * 培训综合查询
 * @author link 
 * 2018-01-29
 */
public interface ApiCompreQueryMapper {
	/**
	 * 查询培训班基础信息列表
	 */
	List<Hb68> getInfoList(Hb68 hb68) throws Exception;

	/**
	 * 根据合并后班级查合并前班级列表
	 */
	List<Hb68> getInfoListByChb067(Hb67 hb67) throws Exception;
	
	/**
     * 根据班级内码查询培训班中学生列表
     */
	List<Hc60> getHc60ById(Hc60 hc60) throws Exception;
	
	/**
     * 根据班级内码查询培训班信息
     */
	Hb68 getHb68ById(String chb068) throws Exception;
	
	/**
     * 根据班级内码查询培训班课程列表
     */
	List<Hb69> getHb69ById(Hb69 hb69) throws Exception;
	/**
	 * 根据班级内码查询培训班课程列表（大丰）
	 */
	List<Hb69> getHb69ById_df(Hb69 hb69);
	
	List<Hb69> getHb69ByChb068(Hb69 hb69) throws Exception;

	/**
	 * 查询培训班合并列表
	 */
	List<Hb67> getMergeList(Hb68 hb68) throws Exception;
	
	/**
	 * 根据合并班级内码查询合并班级信息
	 */
	Hb67 getHb67ById(String chb067) throws Exception;
	
	/**
	 * 查询合并班级中学员列表
	 */
	List<Hc60> getHc60StuList(Hc60 hc60) throws Exception;
	
	/**
	 * 查询合并班级中课程列表
	 */
	List<Hb69> getHb69MergeCourseList(Hb69 hb69) throws Exception;
	
	/**
	 * 根据学员id查询学员信息
	 */
	Hc66 getHc66ById(Hc66 hc66) throws Exception;
	
	/**
	 * 根据学员id查询学员考勤信息
	 */
	List<Zc10> getZc10ById(Zc10 zc10) throws Exception;
	
	/**
     * 获取学员课程考勤明细
     */
	List<Hb69DTO> attendanceQueryList(Hb69DTO hb69dto);


}