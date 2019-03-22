package com.insigma.mvc.dao.train.PXYW_001_006;

import com.insigma.mvc.model.train.*;
import java.util.List;
import java.util.Map;
/**
 * 成绩录入
 * @author Administrator 
 * 2018-03-01
 */
public interface ApiInputScoreMapper {
	/**
	 * 查询培训办班备案列表
	 */
	List<Hb68> getInfoList(Hb68 hb68) throws Exception;
	 /**
	 * 根据ID查询班级信息
	 */
	Hb68 getById(String chb100) throws Exception;
	/**
	 * 根据ID查询班级信息（大丰）
	 */
	Hb68 getById_df(String chb100);
	/**
	 * 根据ID查询班级信息（陕西）
	 */
	Hb68 getById_sx(String chb100);
	/**
	 * 根据专业名称查询培训工种名称及专业类别
	 */
	Hb68 getAca112(String aca110) throws Exception;
	/**
	 * 查询培训学员信息
	 */
	List<Student> getStuListForLook(String chb100) throws Exception;
	/**
	 * 查询是否生成了hc66
	 */
	String checkHc66(String chb100) throws Exception;
	/**
	 * 清空hc66里的学员成绩
	 */
	int clearHc66(String chb100) throws Exception;
	/**
	 * 通过身份证号查询是否生成了hc66
	 */
	String checkByAac002AndChb100(Student stu) throws Exception;
	/**
	 * 生成hc66——勾选合格与不合格
	 */
    int createHc66(Student stu) throws Exception;
    /**
	 * 根据身份证号和班级编号生成成绩表
	 */
    int createHc66ByAac002AndChb100(Student stu) throws Exception;
    /**
	 * 根据身份证号和班级编号更新hc66
	 */
    int updateHc66ByAac002AndChb100(Student stu) throws Exception;
    /**
	 * 更新hc66——勾选合格与不合格
	 */
    int updateHc66(Student stu) throws Exception;
    /**
   	 * 更新hb68部分录入状态
   	 */
    int updateHb68PartStatus(Student stu) throws Exception;
    /**
   	 * 更新hb68成绩录入状态和总状态
   	 */
    int updateHb68Status(Student stu) throws Exception;
    /**
   	 * 更新hb68成绩提交状态
   	 */
    int submitScore(Hb68 hb68) throws Exception;
	/**
	 * 撤销成绩信息
	 */
	int revokeScore(Hb68 hb68) throws Exception;
	/**
	 * 查询成绩标准
	 */
	Zc13 getZc13(String groupid) throws Exception;
	/**
	 * 查询就近行政区划
	 */
	String getGroupId(String aab001) throws Exception;
	/**
	 * 查询当前部署行政区划
	 */
	String getAreaId() throws Exception;
	/**
	 * 更新学员能否拿证
	 */
	int updateHc60Chc029(Student stu) throws Exception;
	
}
