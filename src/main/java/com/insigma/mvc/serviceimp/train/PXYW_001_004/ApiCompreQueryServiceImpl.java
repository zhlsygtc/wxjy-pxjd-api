package com.insigma.mvc.serviceimp.train.PXYW_001_004;

import java.util.List;

import javax.annotation.Resource;

import com.insigma.mvc.dao.train.PXYW_001_013.ApiHeadTeacherMapper;
import com.insigma.mvc.model.train.*;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.insigma.mvc.MvcHelper;
import com.insigma.mvc.dao.train.PXYW_001_001.ApiCompanyMapper;
import com.insigma.mvc.dao.train.PXYW_001_004.ApiCompreQueryMapper;
import com.insigma.mvc.service.train.PXYW_001_004.ApiCompreQueryService;
/**
 * 培训综合查询
 * @author link
 * 2018-01-29
 */
@Service
public class ApiCompreQueryServiceImpl extends MvcHelper implements ApiCompreQueryService {

	@Resource
	private ApiCompreQueryMapper compreQueryMapper;

	@Resource
	private ApiCompanyMapper apiCompanyMapper;
	
    @Resource
    private ApiHeadTeacherMapper apiHeadTeacherMapper;
	
	/**
	 * 初始化培训班基础信息列表 
	 */
	@Override
	public PageInfo<Hb68> getInfoList(Hb68 hb68) throws Exception {
		PageHelper.offsetPage(hb68.getOffset(), hb68.getLimit());
		Hb57 hb57 = apiHeadTeacherMapper.getHeadTeacherByUserId(hb68.getAae011());
		if(hb57 != null && "02".equals(hb57.getChb299())) {
			hb68.setChb057(hb57.getChb057());
		}
		List<Hb68> list=compreQueryMapper.getInfoList(hb68);
		PageInfo<Hb68> pageinfo = new PageInfo<Hb68>(list);
		return pageinfo;
	}

	@Override
	public PageInfo<Hb68> getInfoListByChb067(Hb67 hb67) throws Exception {
		PageHelper.offsetPage(hb67.getOffset(), hb67.getLimit());
		List<Hb68> list=compreQueryMapper.getInfoListByChb067(hb67);
		PageInfo<Hb68> pageinfo = new PageInfo<Hb68>(list);
		return pageinfo;
	}

	/**
	   * 根据班级内码查询培训班中学生列表
     */
	@Override
	public PageInfo<Hc60> getHc60ById(Hc60 hc60) throws Exception {
		PageHelper.offsetPage(hc60.getOffset(), hc60.getLimit());
		List<Hc60> list = compreQueryMapper.getHc60ById(hc60);
		PageInfo<Hc60> pageinfo = new PageInfo<Hc60>(list);
		return pageinfo;
	}
	/**
     * 根据班级内码查询培训班信息
     */
	@Override
	public Hb68 getHb68ById(String chb068) throws Exception {
		return compreQueryMapper.getHb68ById(chb068);
	}

	/**
     * 根据班级内码查询培训班课程列表
     */
	@Override
	public PageInfo<Hb69> getHb69ById(Hb69 hb69) throws Exception {
		PageHelper.offsetPage(hb69.getOffset(), hb69.getLimit());
		String aaa005 = apiCompanyMapper.getAa01ByAaa001("AREA_ID");
		if("320904".equals(aaa005)) {
			List<Hb69> list = compreQueryMapper.getHb69ById_df(hb69);
			PageInfo<Hb69> pageinfo = new PageInfo<Hb69>(list);
			return pageinfo;
		}else{
			List<Hb69> list = compreQueryMapper.getHb69ById(hb69);
			PageInfo<Hb69> pageinfo = new PageInfo<Hb69>(list);
			return pageinfo;
		}
		
	}
	
	/**
     * 根据班级内码查询培训班课程列表
     */
	@Override
	public PageInfo<Hb69> getHb69ByChb068(Hb69 hb69) throws Exception {
		PageHelper.offsetPage(hb69.getOffset(), hb69.getLimit());
		List<Hb69> list = compreQueryMapper.getHb69ByChb068(hb69);
		PageInfo<Hb69> pageinfo = new PageInfo<Hb69>(list);
		return pageinfo;
	}
	
	/**
     * 查询培训班合并列表
     */
	@Override
	public PageInfo<Hb67> getMergeList(Hb68 hb68) throws Exception {
		PageHelper.offsetPage(hb68.getOffset(), hb68.getLimit());
		List<Hb67> list=compreQueryMapper.getMergeList(hb68);
		PageInfo<Hb67> pageinfo = new PageInfo<Hb67>(list);
		return pageinfo;
	}
	
	/**
	 * 根据合并班级内码查询合并班级信息
	 */
	@Override
	public Hb67 getHb67ById(String chb067) throws Exception {
		return compreQueryMapper.getHb67ById(chb067);
	}
	
	/**
	 * 查询合并班级中学员列表
	 */
	@Override
	public PageInfo<Hc60> getHc60StuList(Hc60 hc60) throws Exception {
		PageHelper.offsetPage(hc60.getOffset(), hc60.getLimit());
		List<Hc60> list=compreQueryMapper.getHc60StuList(hc60);
		PageInfo<Hc60> pageinfo = new PageInfo<Hc60>(list);
		return pageinfo;
	}
	
	/**
	 * 查询合并班级中课程列表
	 */
	@Override
	public PageInfo<Hb69> getHb69MergeCourseList(Hb69 hb69) throws Exception {
		PageHelper.offsetPage(hb69.getOffset(), hb69.getLimit());
		List<Hb69> list=compreQueryMapper.getHb69MergeCourseList(hb69);
		PageInfo<Hb69> pageinfo = new PageInfo<Hb69>(list);
		return pageinfo;
	}

	/**
	 * 根据学员id查询学员详细信息
	 */
	@Override
	public Hc66 getHc66ById(Hc66 hc66) throws Exception {
		return compreQueryMapper.getHc66ById(hc66);
	}

	/**
	 * 根据学员id查询学员详细信息
	 */
	@Override
	public PageInfo<Zc10> getZc10ById(Zc10 zc10) throws Exception {
		PageHelper.offsetPage(zc10.getOffset(), zc10.getLimit());
		List<Zc10> list = compreQueryMapper.getZc10ById(zc10);
		PageInfo<Zc10> pageinfo = new PageInfo<Zc10>(list);
		return pageinfo;
	}

	/**
     * 获取学员课程考勤明细
     */
	@Override
	public PageInfo<Hb69DTO> attendanceQueryList(Hb69DTO hb69dto) {
		PageHelper.offsetPage(hb69dto.getOffset(), hb69dto.getLimit());
		List<Hb69DTO> list = compreQueryMapper.attendanceQueryList(hb69dto);
		PageInfo<Hb69DTO> pageinfo = new PageInfo<Hb69DTO>(list);
		return pageinfo;
	}	
}