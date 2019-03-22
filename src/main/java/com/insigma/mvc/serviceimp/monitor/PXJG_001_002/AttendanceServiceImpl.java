package com.insigma.mvc.serviceimp.monitor.PXJG_001_002;

import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
import com.insigma.dto.AjaxReturnMsg;
import com.insigma.mvc.MvcHelper;
import com.insigma.mvc.dao.monitor.PXJG_001_002.AttendanceMapper;
import com.insigma.mvc.model.train.Hb68;
import com.insigma.mvc.model.train.Hc60;
import com.insigma.mvc.model.train.Zc02;
import com.insigma.mvc.service.common.fileupload.ApiFileUploadService;
import com.insigma.mvc.service.monitor.PXJG_001_002.AttendanceService;

/**
 * 采集
 * 
 * @author jewel 2018-01-10
 */
@Service
public class AttendanceServiceImpl extends MvcHelper implements AttendanceService {

	@Resource
	private AttendanceMapper attendanceMapper;
	@Resource
	private ApiFileUploadService fileLoadService;

    @Resource
    private ApiHeadTeacherMapper apiHeadTeacherMapper;

	/**
	 * 查询培训开班列表
	 */
	@Override
	public PageInfo<Hb68> getInfoList(Hb68 hb68) throws Exception {
		Hb57 hb57 = apiHeadTeacherMapper.getHeadTeacherByUserId(hb68.getAae011());
		if(hb57 != null && "02".equals(hb57.getChb299())) {
			hb68.setChb057(hb57.getChb057());
		}
		PageHelper.offsetPage(hb68.getOffset(), hb68.getLimit());
		if (StringUtils.isNotEmpty(hb68.getChb103())) {
			hb68.setChb103s(hb68.getChb103().split(","));
		}
		if (StringUtils.isNotEmpty(hb68.getChb315())) {
			hb68.setChb315s(hb68.getChb315().split(","));
		}
		List<Hb68> list = attendanceMapper.getInfoList(hb68);
		PageInfo<Hb68> pageinfo = new PageInfo<Hb68>(list);
		return pageinfo;
	}

	// 保存信息
	@Override
	public AjaxReturnMsg saveData(Zc02 zc02) {
		zc02.setAae036(new Date());
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
		Date date = new Date();
		
		//判断学员
		List<Hc60> list0 = attendanceMapper.getHc60ByAac002(zc02);
		if (list0.size() > 0) {
			zc02.setChc001(list0.get(0).getChc001());
			zc02.setAac001(list0.get(0).getAac001());
		}else{
			return this.error("身份信息不存在！");
		}
		
		// 判断此身份证是否已经考勤
		List<Zc02> list = attendanceMapper.checkZc02(zc02);
		if (list.size() > 0) {
			zc02.setCzc002(list.get(0).getCzc002());
			if (checkTime(df.format(new Date()), 12)) {// 12点之前二次打卡
				zc02.setEdc363(date);
			} else {
				if (null == list.get(0).getEdc446() || "".equals(list.get(0).getEdc446())) {
					zc02.setEdc446(date);
				} else {
					zc02.setEdc447(date);
				}
			}
			int num = attendanceMapper.updateAttendance(zc02);
			if (num == 1) {
				return this.success("打卡成功", zc02.getAac002());
			} else {
				return this.error("打卡失败");
			}
		} else {
			if (checkTime(df.format(new Date()), 12)) {
				zc02.setEdc362(date);
			} else {
				zc02.setEdc446(date);
			}
			int num = attendanceMapper.addAttendance(zc02);
			if (num == 1) {
				return this.success("打卡成功", zc02.getAac002());
			} else {
				return this.error("打卡失败");
			}
		}
	}

	public boolean checkTime(String date, int time) {
		if (Integer.parseInt(date.substring(11, 13)) < time) {
			return true;
		}
		return false;
	}

}
