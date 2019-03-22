package com.insigma.mvc.serviceimp.monitor.PXJG_001_003;

import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import com.insigma.mvc.dao.train.PXYW_001_013.ApiHeadTeacherMapper;
import com.insigma.mvc.model.train.Hb57;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.insigma.common.util.DateUtil;
import com.insigma.common.util.StringUtil;
import com.insigma.dto.AjaxReturnMsg;
import com.insigma.mvc.MvcHelper;
import com.insigma.mvc.dao.monitor.PXJG_001_002.AttendanceMapper;
import com.insigma.mvc.dao.monitor.PXJG_001_003.LeaveMapper;
import com.insigma.mvc.model.train.Zc07DTO;
import com.insigma.mvc.model.Ce21Type;
import com.insigma.mvc.model.train.Hc60;
import com.insigma.mvc.model.train.Zc07;
import com.insigma.mvc.service.common.fileupload.ApiFileUploadService;
import com.insigma.mvc.service.monitor.PXJG_001_002.AttendanceService;
import com.insigma.mvc.service.monitor.PXJG_001_003.LeaveService;

/**
 * 请假
 * 
 * @author
 */
@Service
public class LeaveServiceImpl extends MvcHelper implements LeaveService {

	@Resource
	private LeaveMapper leaveMapper;
	@Resource
	private ApiFileUploadService fileLoadService;

    @Resource
    private ApiHeadTeacherMapper apiHeadTeacherMapper;

	/**
	 * 查询请假学员列表
	 */
	@Override
	public PageInfo<Zc07DTO> getInfoList(Zc07DTO zc07DTO) throws Exception {
		Hb57 hb57 = apiHeadTeacherMapper.getHeadTeacherByUserId(zc07DTO.getAae011());
		if(hb57 != null && "02".equals(hb57.getChb299())) {
			zc07DTO.setChb057(hb57.getChb057());
		}
		PageHelper.offsetPage(zc07DTO.getOffset(), zc07DTO.getLimit());
		List<Zc07DTO> list = leaveMapper.getInfoList(zc07DTO);
		PageInfo<Zc07DTO> pageinfo = new PageInfo<Zc07DTO>(list);
		return pageinfo;
	}
	//根据学员内码获取学员信息
	@Override
	public AjaxReturnMsg getStuById(String chc001) {
		Hc60 hc60 = leaveMapper.getStuById(chc001);
		return this.success(hc60);
	}

	//根据学员内码获取学员请假信息
	@Override
	public PageInfo<Zc07> getStuList(Zc07 zc07) throws Exception {
		PageHelper.offsetPage(zc07.getOffset(), zc07.getLimit());
		List<Zc07> list2 = leaveMapper.getStuList(zc07);
		PageInfo<Zc07> pageinfo2 = new PageInfo<Zc07>(list2);
		return pageinfo2;
	}
	//删除学员请假信息
	@Override
	public AjaxReturnMsg<String> deleteData(String czc007) {
		
            leaveMapper.deleteZc07Det(czc007);

		    leaveMapper.deleteData(czc007);
		
			return this.success("删除成功");
		
	}
	
	// 保存信息
	@Override
	public AjaxReturnMsg saveData(Zc07 zc07) {

		zc07.setAae036(new Date());
		zc07.setAae100("1");
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
		Date date = new Date();
		//新增的请假记录
	    leaveMapper.saveData(zc07);
	    
        //新增的请假课程明细
	    String[] chb069s = StringUtil.isEmpty(zc07.getChb069s()) ? new String[0] : zc07.getChb069s().split(",");
        for (String chb069 : chb069s) {
        	zc07.setChb069(chb069);
            leaveMapper.saveZc07Det(zc07);
         
        }

		return this.success("保存成功");
	}
}
