package com.insigma.mvc.serviceimp.appraisal.JDYW_002_011;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.eclipse.jetty.util.StringUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.insigma.common.util.DateUtil;
import com.insigma.dto.AjaxReturnMsg;
import com.insigma.enums.COMAAE100Enum;
import com.insigma.enums.HB74CHB341Enum;
import com.insigma.enums.HB75CHB146Enum;
import com.insigma.enums.HB77EXAMTYPEEnum;
import com.insigma.mvc.MvcHelper;
import com.insigma.mvc.dao.appraisal.JDYW_002_011.ApiExamRoomArrangeMapper;
import com.insigma.mvc.model.CodeValue;
import com.insigma.mvc.model.Hc63;
import com.insigma.mvc.model.appraisal.Hb70;
import com.insigma.mvc.model.appraisal.Hb71;
import com.insigma.mvc.model.appraisal.Hb74;
import com.insigma.mvc.model.appraisal.Hb74Temp_Short;
import com.insigma.mvc.model.appraisal.Hb76;
import com.insigma.mvc.model.appraisal.Hb77;
import com.insigma.mvc.model.appraisal.Hb78;
import com.insigma.mvc.service.appraisal.JDYW_002_011.ApiExamRoomArrangeService;
import com.insigma.resolver.AppException;

@Service
@Transactional(rollbackFor=Exception.class)
public class ApiExamRoomArrangeServiceImpl extends MvcHelper implements ApiExamRoomArrangeService{

	@Resource
	private ApiExamRoomArrangeMapper apiExamRoomArrangeMapper;

	//获取相应机构的鉴定申报数据
	@Override
	public PageInfo<Hb74Temp_Short> getAppraisalSpeciaList(Hb74Temp_Short hb74Temp_Short) {
		PageHelper.offsetPage(hb74Temp_Short.getOffset(), hb74Temp_Short.getLimit());
		// 查询考场编排阶段数据(不包含考场编排通过数据)
		List<String> chb146list = new ArrayList();
		chb146list.add(HB75CHB146Enum.IDENTIFICATION_CENTER_PASS.getCode());
		chb146list.add(HB75CHB146Enum.EXAMINATION_ROOM_ARRANGEMENT_NOT_PASS.getCode());
		chb146list.add(HB75CHB146Enum.EXAMINATION_ROOM_ARRANGEMENT.getCode());
		chb146list.add(HB75CHB146Enum.EXAMINATION_ROOM_ARRANGEMENT_PASS.getCode());
		chb146list.add(HB75CHB146Enum.PERPON_ASSIGN.getCode());
		chb146list.add(HB75CHB146Enum.PERPON_ASSIGN_PASS.getCode());
		chb146list.add(HB75CHB146Enum.PERPON_ASSIGN_NOT_PASS.getCode());
		chb146list.add(HB75CHB146Enum.MAKING_TEST_PAPERS.getCode());
		chb146list.add(HB75CHB146Enum.GRADE_ENTRY.getCode());
		chb146list.add(HB75CHB146Enum.GRADE_ENTRY_PASS.getCode());
		chb146list.add(HB75CHB146Enum.GRADE_ENTRY_NOT_PASS.getCode());
		chb146list.add(HB75CHB146Enum.PAYMENT_CONFIRMATION.getCode());
		chb146list.add(HB75CHB146Enum.QUALIFIED_LIST_CONFIRMATION.getCode());
		chb146list.add(HB75CHB146Enum.CERTIFICATE_GENERATION.getCode());
		chb146list.add(HB75CHB146Enum.MARK_CERTIFICATION.getCode());
		chb146list.add(HB75CHB146Enum.RECEIVE_CERTIFICATION.getCode());
		chb146list.add(HB75CHB146Enum.PIGEONHOLE.getCode());
		hb74Temp_Short.setChb146list(chb146list);
		// 处理编排状态多选
		if (StringUtils.isNotBlank(hb74Temp_Short.getChb341())) {
			chb146list = new ArrayList();
			if (hb74Temp_Short.getChb341().contains(",")) {
				// 多选
				String[] chb341s = hb74Temp_Short.getChb341().split(",");
				for (String chb341 : chb341s) {
					chb146list.add(chb341);
				}
			} else {
				// 单选
				chb146list.add(hb74Temp_Short.getChb341());
			}
			hb74Temp_Short.setChb341list(chb146list);
		}
		// 考场编排数据状态排序指定
		List<CodeValue> chb146Code = new ArrayList();
		CodeValue codeValue = new CodeValue();
		codeValue.setCode_value("4");// 排序 倒序
		codeValue.setCode_name(HB75CHB146Enum.IDENTIFICATION_CENTER_PASS.getCode());
		chb146Code.add(codeValue);
		codeValue = new CodeValue();
		codeValue.setCode_value("3");// 排序 倒序
		codeValue.setCode_name(HB75CHB146Enum.EXAMINATION_ROOM_ARRANGEMENT_NOT_PASS.getCode());
		chb146Code.add(codeValue);
		codeValue = new CodeValue();
		codeValue.setCode_value("2");// 排序 倒序
		codeValue.setCode_name(HB75CHB146Enum.EXAMINATION_ROOM_ARRANGEMENT.getCode());
		chb146Code.add(codeValue);
		codeValue = new CodeValue();
		codeValue.setCode_value("1");// 排序 倒序
		codeValue.setCode_name(HB75CHB146Enum.EXAMINATION_ROOM_ARRANGEMENT_PASS.getCode());
		chb146Code.add(codeValue);
		// 加入排序条件
		hb74Temp_Short.setChb146Code(chb146Code);
		// 查询有效数据
		hb74Temp_Short.setAae100(COMAAE100Enum.DATA_EFFECTIVE.getCode());
		List<Hb74Temp_Short> list= apiExamRoomArrangeMapper.getAppraisalSpeciaList(hb74Temp_Short);
		PageInfo<Hb74Temp_Short> pageinfo = new PageInfo<>(list);
		return pageinfo;
	}

	@Override
	public PageInfo<Hb70> getExamRoomBatchList(Hb70 hb70) {
		PageHelper.offsetPage(hb70.getOffset(), hb70.getLimit());
		// 处理考试类型多选
		if (StringUtils.isNotBlank(hb70.getExamtype())) {
			List<String> examtypeList = new ArrayList();
			if (hb70.getExamtype().contains(",")) {
				// 多选
				String[] examtypes = hb70.getExamtype().split(",");
				for (String examtype : examtypes) {
					examtypeList.add(examtype);
				}
			} else {
				// 单选
				examtypeList.add(hb70.getExamtype());
			}
			hb70.setExamtypeList(examtypeList);
		}
		hb70.setAae100(COMAAE100Enum.DATA_EFFECTIVE.getCode());
		List<Hb70> list= apiExamRoomArrangeMapper.getExamRoomBatchList(hb70);
		PageInfo<Hb70> pageinfo = new PageInfo<>(list);
		return pageinfo;
	}

	@Override
	public PageInfo<Hb77> getExamRoomList(Hb77 hb77) {
		PageHelper.offsetPage(hb77.getOffset(), hb77.getLimit());
		// 处理考试类型多选
		if (StringUtils.isNotBlank(hb77.getExamtype())) {
			List<String> examtypeList = new ArrayList();
			if (hb77.getExamtype().contains(",")) {
				// 多选
				String[] examtypes = hb77.getExamtype().split(",");
				for (String examtype : examtypes) {
					examtypeList.add(examtype);
				}
			} else {
				// 单选
				examtypeList.add(hb77.getExamtype());
			}
			hb77.setExamtypeList(examtypeList);
		}
		hb77.setAae100(COMAAE100Enum.DATA_EFFECTIVE.getCode());
		List<Hb77> list= apiExamRoomArrangeMapper.getExamRoomList(hb77);
		PageInfo<Hb77> pageinfo = new PageInfo<>(list);
		return pageinfo;
	}

	@Override
	public AjaxReturnMsg examRoomBatchSave(Hb70 hb70) {
		// 考试开始、结束时间 比较
		if (StringUtils.isNotBlank(hb70.getChb165()) && StringUtils.isNotBlank(hb70.getChb16a())) {
			try {
				// 时间格式类型转换
				Date chb165 = DateUtil.stringToDate(hb70.getChb164_string() + " " + hb70.getChb165(), "yyyy-MM-dd HH:mm");
				Date chb16a = DateUtil.stringToDate(hb70.getChb164_string() + " " + hb70.getChb16a(), "yyyy-MM-dd HH:mm");
				// 时间校验
				if (!DateUtil.compare(chb16a, chb165, 0)) {
					return this.error("考试结束时间必须晚于开始时间");
				}
			} catch (AppException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			return this.error("考试开始日期、开始时间、结束时间不能为空");
		}
		hb70.setChb165(hb70.getChb165() + "~" + hb70.getChb16a());
		// 考试日期数据类型转换
		if (StringUtils.isNotBlank(hb70.getChb164_string())) {
			try {
				hb70.setChb164(DateUtil.stringToDate(hb70.getChb164_string(), "yyyy-MM-dd"));
			} catch (AppException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		hb70.setAae100(COMAAE100Enum.DATA_EFFECTIVE.getCode());
		int result = 0;
		if (StringUtils.isBlank(hb70.getChb070())) {
			// 校验考场类型参数合法性
			if (HB77EXAMTYPEEnum.fromCode(hb70.getExamtype()) == null) {
				return this.error("考场类型参数不能为空");
			}
			// 新增操作
			result = apiExamRoomArrangeMapper.examRoomBatchInsert(hb70);
		} else {
			// 获取旧的考试批次信息
			Hb70 oldhb70 = apiExamRoomArrangeMapper.getExamRoomBaseBatchInfo(hb70.getChb070(), COMAAE100Enum.DATA_EFFECTIVE.getCode());
			// 编辑操作
			result = apiExamRoomArrangeMapper.examRoomBatchSave(hb70);
			// 如果时间发生变更 更新考试批次下所有考试记录信息
			if (oldhb70.getChb164() != hb70.getChb164()) {
				oldhb70 = new Hb70();
				oldhb70.setChb070(hb70.getChb070());
				oldhb70.setChb164(hb70.getChb164());
				oldhb70.setAae011(hb70.getAae011());
				oldhb70.setAae100(hb70.getAae100());
				int result1 = apiExamRoomArrangeMapper.saveBatchExamination(oldhb70);
			}
		}
		saveHb74Status(hb70.getChb140(), countHb74CHB341(hb70.getChb140()), COMAAE100Enum.DATA_EFFECTIVE.getCode(), null);
		if (result == 1) {
			return this.success("更新成功", hb70.getChb070());
		}
		return this.error("更新失败", hb70.getChb070());
	}

	@Override
	public AjaxReturnMsg examRoomSave(Hb77 hb77) {
		// 考试开始、结束时间 比较
		if (StringUtils.isNotBlank(hb77.getChb165()) && StringUtils.isNotBlank(hb77.getChb16a())) {
			try {
				// 时间格式类型转换
				Date chb165 = DateUtil.stringToDate(hb77.getChb164_string() + " " + hb77.getChb165(), "yyyy-MM-dd HH:mm");
				Date chb16a = DateUtil.stringToDate(hb77.getChb164_string() + " " + hb77.getChb16a(), "yyyy-MM-dd HH:mm");
				// 时间校验
				if (!DateUtil.compare(chb16a, chb165, 0)) {
					return this.error("考试结束时间必须晚于开始时间");
				}
			} catch (AppException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			return this.error("考试开始日期、开始时间、结束时间不能为空");
		}
		hb77.setChb165(hb77.getChb165() + "~" + hb77.getChb16a());
		// 考试日期数据类型转换
		if (StringUtils.isNotBlank(hb77.getChb164_string())) {
			try {
				hb77.setChb164(DateUtil.stringToDate(hb77.getChb164_string(), "yyyy-MM-dd"));
			} catch (AppException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		hb77.setAae100(COMAAE100Enum.DATA_EFFECTIVE.getCode());
		int result = 0;
		if (StringUtils.isBlank(hb77.getChb077())) {
			// 校验考场类型参数合法性
			if (HB77EXAMTYPEEnum.fromCode(hb77.getExamtype()) == null) {
				return this.error("考场类型参数不能为空");
			}
			// 如果当前考试批次考生分配完毕 不允许继续新增考场
			// 查询当前批次待分配考生数量
			Hc63 hc63 = new Hc63();
			hc63.setAae100(COMAAE100Enum.DATA_EFFECTIVE.getCode());
			hc63.setChb070(hb77.getChb070());
			hc63.setChb140(hb77.getChb140());
			List<Hc63> hc63list = apiExamRoomArrangeMapper.getExamRoomPerponSelectList(hc63);
			if (hc63list.isEmpty()) {
				return this.error("当前考试批次考生已经分配完毕,不允许继续新增考场！！");
			}
			// 新增操作
			result = apiExamRoomArrangeMapper.examRoomInsert(hb77);
		} else {
			// 编辑操作
			result = apiExamRoomArrangeMapper.examRoomSave(hb77);
		}
		saveHb74Status(hb77.getChb140(), countHb74CHB341(hb77.getChb140()), COMAAE100Enum.DATA_EFFECTIVE.getCode(), null);
		if (result == 1) {
			return this.success("更新成功", hb77.getChb077());
		}
		return this.error("更新失败", hb77.getChb077());
	}

	@Override
	public AjaxReturnMsg getAppraisalInfo(String chb140) {
		if (StringUtils.isBlank(chb140)) {
			return this.error("参数错误，请登录重试");
		}
		return this.success(apiExamRoomArrangeMapper.getAppraisalInfo(chb140));
	}

	@Override
	public PageInfo<Hc63> getExamRoomPerponList(Hc63 hc63) {
		PageHelper.offsetPage(hc63.getOffset(), hc63.getLimit());
		// 查询有效数据
		hc63.setAae100(COMAAE100Enum.DATA_EFFECTIVE.getCode());
		List<Hc63> list= apiExamRoomArrangeMapper.getExamRoomPerponList(hc63);
		PageInfo<Hc63> pageinfo = new PageInfo<>(list);
		return pageinfo;
	}

	@Override
	public PageInfo<Hc63> getExamRoomPerponSelectList(Hc63 hc63) {
		PageHelper.offsetPage(hc63.getOffset(), hc63.getLimit());
		// 查询有效人员数据
		hc63.setAae100(COMAAE100Enum.DATA_EFFECTIVE.getCode());
		List<Hc63> list= apiExamRoomArrangeMapper.getExamRoomPerponSelectList(hc63);
		PageInfo<Hc63> pageinfo = new PageInfo<>(list);
		return pageinfo;
	}

	@Override
	public AjaxReturnMsg savePerponExamRoom(Hc63 hc63) {
		if (hc63 != null && StringUtils.isNotBlank(hc63.getSelectnodes()) && !",".equals(hc63.getSelectnodes())) {
			// 查询当前考场信息
			Hb77 hb77info = apiExamRoomArrangeMapper.getExamRoomInfo(hc63.getChb077(), COMAAE100Enum.DATA_EFFECTIVE.getCode());
			if (hb77info == null) {
				return this.error("获取当前考场信息失败");
			}
			// 查询当前考场座位使用情况
			Hb77 hb77number = new Hb77();
			hb77number.setChb077(hc63.getChb077());
			hb77number.setChb140(hc63.getChb140());
			hb77number.setAae100(COMAAE100Enum.DATA_EFFECTIVE.getCode());
			// 获取批次中所有本考场人员信息
			List<String> chb330 = apiExamRoomArrangeMapper.getseatnumber(hb77number);
			// 获取空座位集合
			List<Integer> zuowei = seatNumberMake(chb330, hb77info.getChb166());
			String[] chc063Batch = null;
			if (hc63.getSelectnodes().contains(",")) {
			//去掉最后一个空字符
			hc63.setSelectnodes(hc63.getSelectnodes().substring(0, hc63.getSelectnodes().length() - 1));
			chc063Batch = hc63.getSelectnodes().split(",");
			} else {
				// 只选中一个值
				chc063Batch = new String[]{hc63.getSelectnodes()};
			}
			// 如果需要安排的座位数大于目前空闲的座位数 则放弃分配 直接返回
			if (chc063Batch.length > zuowei.size()) {
				return this.error("已超过当前考场可容纳人数 剩余可容纳人数" + zuowei.size() + "人，待安排座位人数" + chc063Batch.length + "人" );
			}
			Map hc63map = new HashMap();
			List<Hc63> hc63Batch = new ArrayList();
			int jishu = 0;
			// 遍历对象
			for (String chc063 : chc063Batch) {
				// 填充个人信息
				Hc63 hc = new Hc63();
				hc.setChb330(String.valueOf(zuowei.get(jishu)));
				hc.setChc063(chc063);
				hc63Batch.add(hc);
				jishu ++;
			}
			hc63map.put("chb077", hc63.getChb077());
			hc63map.put("aae011", hc63.getAae011());
			hc63map.put("chb140", hc63.getChb140());
			hc63map.put("aae100", COMAAE100Enum.DATA_EFFECTIVE.getCode());
			hc63map.put("hc63Batch", hc63Batch);
			int result = apiExamRoomArrangeMapper.savePerponExamHb71Batch(hc63map);
			// 更新考场编排状态
			saveHb74Status(hc63.getChb140(), countHb74CHB341(hc63.getChb140()), COMAAE100Enum.DATA_EFFECTIVE.getCode(), null);
			return this.success("加入考场成功");
		}
		return this.error("参数错误,请检查");
	}

	@Override
	public AjaxReturnMsg deletePerponExamRoom(Hc63 hc63) {
		if (hc63 != null && StringUtils.isNotBlank(hc63.getSelectnodes()) && !",".equals(hc63.getSelectnodes())) {
			//去掉最后一个空字符
			hc63.setSelectnodes(hc63.getSelectnodes().substring(0, hc63.getSelectnodes().length() - 1));
			String[] chb077Batch = hc63.getSelectnodes().split(",");
			Map hc63map = new HashMap();
			List<Hb71> hc71Batch = new ArrayList();
			// 遍历对象
			for (String chb071 : chb077Batch) {
				// 填充个人信息
				Hb71 hc = new Hb71();
				hc.setChb071(chb071);
				hc71Batch.add(hc);
			}
			hc63map.put("examtype", hc63.getExamtype());
			// 清空座位号
			hc63map.put("chb330", null);
			// 清空所在考场
			hc63map.put("chb060", null);
			hc63map.put("aae100", COMAAE100Enum.DATA_INVALID.getCode());
			hc63map.put("hc71Batch", hc71Batch);
			int result = apiExamRoomArrangeMapper.deletePerponExamHb71Batch(hc63map);
			// 更新考场编排状态
			saveHb74Status(hc63.getChb140(), countHb74CHB341(hc63.getChb140()), COMAAE100Enum.DATA_EFFECTIVE.getCode(), null);
			if (result > 0) {
				return this.success("移出考场成功");
			} else {
				return this.error("移出考场失败");
			}
		}
		return this.error("参数错误,请检查");
	}


	@Override
	public AjaxReturnMsg getExamRoomBaseBatchInfo(String chb070) {
		if (StringUtils.isBlank(chb070)) {
			return this.error("参数错误，请登录重试");
		}
		Hb70 hb70 = apiExamRoomArrangeMapper.getExamRoomBaseBatchInfo(chb070, COMAAE100Enum.DATA_EFFECTIVE.getCode());
		if (hb70 != null) {
			hb70.setChb16a(hb70.getChb165().split("~")[1]);
			hb70.setChb165(hb70.getChb165().split("~")[0]);
		}
		return this.success(hb70);
	}

	@Override
	public AjaxReturnMsg getExamRoomBaseInfo(String chb077) {
		if (StringUtils.isBlank(chb077)) {
			return this.error("参数错误，请登录重试");
		}
		Hb77 hb77 = apiExamRoomArrangeMapper.getExamRoomBaseInfo(chb077, COMAAE100Enum.DATA_EFFECTIVE.getCode());
		if (hb77 != null) {
			hb77.setChb16a(hb77.getChb165().split("~")[1]);
			hb77.setChb165(hb77.getChb165().split("~")[0]);
		}
		return this.success(hb77);
	}

	@Override
	public AjaxReturnMsg deletePerponExamRoomBatch(Hb77 hb77) {
		if (hb77 != null && StringUtils.isNotBlank(hb77.getSelectnodes()) && !",".equals(hb77.getSelectnodes())) {
			//去掉最后一个空字符
			hb77.setSelectnodes(hb77.getSelectnodes().substring(0, hb77.getSelectnodes().length() - 1));
			// 获取内码与类型字符数组
			String[] chb077AndExamtypeBatch = hb77.getSelectnodes().split(",");
			// 内码数组
			String[] chb077Batch = new String[hb77.getSelectnodes().split(",").length];
			// 类型数组
			String[] examtypeBatch = new String[hb77.getSelectnodes().split(",").length];
			// 计数
			int i = 0;
			for (String chb077AndExamtype : chb077AndExamtypeBatch) {
				if (StringUtils.isNotBlank(chb077AndExamtype)) {
					chb077Batch[i] = chb077AndExamtype.split(";")[0];
					examtypeBatch[i] = chb077AndExamtype.split(";")[1];
					i ++;
				}
			}
			Map hb77map = new HashMap();
			List<Hb77> hb77Batch = new ArrayList();
			// 清空重新计数
			i = 0;
			// 遍历对象
			for (String chb077 : chb077Batch) {
				// 填充个人信息
				Hb77 hc = new Hb77();
				hc.setChb077(chb077);
				hc.setExamtype(examtypeBatch[i]);
				hb77Batch.add(hc);
				i ++;
			}
			hb77map.put("examtype", hb77.getExamtype());
			// 清空座位号
			hb77map.put("chb330", null);
			// 清空所在考场
			hb77map.put("chb060", null);
			hb77map.put("aae100", COMAAE100Enum.DATA_INVALID.getCode());
			hb77map.put("hb77Batch", hb77Batch);
			int result = apiExamRoomArrangeMapper.deletePerponExamRoomBatch(hb77map);
			int result2 = apiExamRoomArrangeMapper.deleteExamRoomBatch(hb77map);
			return this.success("删除成功");
		}
		return this.error("参数错误,请检查");
	}

	@Override
	public AjaxReturnMsg saveExamRoomSubmit(Hb74 hb74) {
		// 如果是提交操作  更改总状态
		if (hb74 != null && HB74CHB341Enum.WAIT_AUDIT.getCode().equals(hb74.getChb341()) && StringUtils.isNotBlank(hb74.getChb140())) {
			// 修改批次中总状态
			int result = saveHb74Status(hb74.getChb140(), HB74CHB341Enum.WAIT_AUDIT.getCode(), COMAAE100Enum.DATA_EFFECTIVE.getCode(), HB75CHB146Enum.EXAMINATION_ROOM_ARRANGEMENT.getCode());
			if (result == 1) {
				return this.success("提交成功");
			} else {
				return this.error("提交失败");
			}
		}
		return this.error("参数错误,请检查");
	}

	@Override
	public AjaxReturnMsg getselectExamRoomPerponNum(String chb070, String chb140){
		if (StringUtils.isNotBlank(chb140)) {
			// 根据考试批次编号 考试类型 查询统计人数
			return this.success(apiExamRoomArrangeMapper.getStatisPerponNumber(chb140, chb070, COMAAE100Enum.DATA_EFFECTIVE.getCode(), COMAAE100Enum.DATA_INVALID.getCode()));
		}
		return this.error("参数错误,请检查");
	}

	@Override
	public List<Hb76> getTestCenterList(String aab001) {
		return apiExamRoomArrangeMapper.getTestCenterList(aab001, COMAAE100Enum.DATA_EFFECTIVE.getCode());
	}

	@Override
	public List<Hb78> getExamRoomSelectList(String chb076) {
		return apiExamRoomArrangeMapper.getExamRoomSelectList(chb076, COMAAE100Enum.DATA_EFFECTIVE.getCode());
	}

	@Override
	public AjaxReturnMsg automaticExamArrange(Hb70 hb70) {
		if (StringUtil.isBlank(hb70.getChb070())) {
			return this.error("参数错误,请检查");
		}
		// 查询考试批次信息
		Hb70 hb = apiExamRoomArrangeMapper.getExamRoomBaseBatchInfo(hb70.getChb070(), COMAAE100Enum.DATA_EFFECTIVE.getCode());
		if (null == hb) {
			return this.error("未能查询到当前考场批次信息");
		}
		// 查询该考试批次中所有考场信息
		List<Hb77> hb77list = apiExamRoomArrangeMapper.getExamRoomSpaceList(hb70.getChb070(), COMAAE100Enum.DATA_EFFECTIVE.getCode());
		if (hb77list.isEmpty()) {
			return this.error("未能查询到考试批次对应的考场信息");
		}
		// 查询当前批次待分配考生数量
		Hc63 hc63 = new Hc63();
		hc63.setAae100(COMAAE100Enum.DATA_EFFECTIVE.getCode());
		hc63.setChb070(hb70.getChb070());
		hc63.setChb140(hb.getChb140());
		List<Hc63> hc63list = apiExamRoomArrangeMapper.getExamRoomPerponSelectList(hc63);
		if (hc63list.isEmpty()) {
			return this.error("未能查询到未分配的考生，请检查考生是否已经分配完毕");
		}

		// 总可容纳人数
		int spaceCount = 0;
		for (Hb77 h77 : hb77list) {
			// 加入操作人
			h77.setAae011(hb70.getAae011());
			spaceCount += h77.getChb166();
		}
		BigDecimal spaceSum = new BigDecimal(spaceCount);
		// 总待编排人数
		BigDecimal perpleSum = new BigDecimal(hc63list.size());
		if (perpleSum.compareTo(spaceSum) > 0) {
			return this.error("考生人数大于当前已有考场可容纳人数，无法自动编排，请新增考场");
		}
		// 计算考场编排饱和率 
		BigDecimal spacePadding = perpleSum.divide(spaceSum, 2, RoundingMode.HALF_UP);
		return automaticExamOperating(hc63list, hb77list, hb.getChb140(), spacePadding);
	}

	private AjaxReturnMsg automaticExamOperating(List<Hc63> hc63list, List<Hb77> hb77list, String chb140, BigDecimal spacePadding) {
		for (Hb77 hb77 : hb77list) {
			// 查询当前考场座位使用情况
			Hb77 hb77number = new Hb77();
			hb77number.setChb077(hb77.getChb077());
			hb77number.setChb140(chb140);
			hb77number.setAae100(COMAAE100Enum.DATA_EFFECTIVE.getCode());
			// 获取批次中所有本考场人员信息
			List<String> chb330 = apiExamRoomArrangeMapper.getseatnumber(hb77number);
			// 获取空座位集合
			List<Integer> zuowei = seatNumberMake(chb330, hb77.getChb166_sum());
			// 根据饱和率进行分配
			BigDecimal perpleSum = new BigDecimal(hb77.getChb166());
			// 四舍五入保留整数
			perpleSum = perpleSum.multiply(spacePadding).setScale(2, BigDecimal.ROUND_DOWN);
			// 循环获取考生内码
			String selectnodes = "";
			for (int i = hc63list.size() - 1; i >= 0; i --) {
				if (null == hc63list.get(i)) {
					continue;
				}
				// 比较是否已经超出饱和度规定的分配次数
				if (perpleSum.subtract(new BigDecimal(1)).compareTo(new BigDecimal(0)) < 0 && hb77 != hb77list.get(hb77list.size() -1)) {
					// 已超出 终止循环
					break;
				}
				// 拼接考生内码
				selectnodes += hc63list.get(i).getChc063() + ",";
				// 将已添加的考生从集合中移除
				hc63list.remove(i);
				perpleSum = perpleSum.subtract(new BigDecimal(1));
			}

			if (StringUtils.isBlank(selectnodes)) {
				continue;
			}
			String[] chc063Batch = null;
			//去掉最后一个空字符
			selectnodes = selectnodes.substring(0, selectnodes.length() - 1);
			if (selectnodes.contains(",")) {
				chc063Batch = selectnodes.split(",");
			} else {
				// 只选中一个值
				chc063Batch = new String[]{selectnodes};
			}
			// 如果需要安排的座位数大于目前空闲的座位数 则放弃分配 直接返回
			if (chc063Batch.length > zuowei.size()) {
				return this.error("已超过当前考场可容纳人数 剩余可容纳人数" + zuowei.size() + "人，待安排座位人数" + chc063Batch.length + "人");
			}
			Map hc63map = new HashMap();
			List<Hc63> hc63Batch = new ArrayList();
			int jishu = 0;
			// 遍历对象
			for (String chc063 : chc063Batch) {
				// 填充个人信息
				Hc63 hc = new Hc63();
				hc.setChb330(String.valueOf(zuowei.get(jishu)));
				hc.setChc063(chc063);
				hc63Batch.add(hc);
				jishu ++;
			}
			hc63map.put("chb077", hb77.getChb077());
			hc63map.put("aae011", hb77.getAae011());
			hc63map.put("chb140", chb140);
			hc63map.put("aae100", COMAAE100Enum.DATA_EFFECTIVE.getCode());
			hc63map.put("hc63Batch", hc63Batch);
			int result = apiExamRoomArrangeMapper.savePerponExamHb71Batch(hc63map);
		}
		// 更新考场编排状态
		saveHb74Status(chb140, countHb74CHB341(chb140), COMAAE100Enum.DATA_EFFECTIVE.getCode(), null);
		return this.success("加入考场成功");
	}

	/**
	 * 查询当前批次中已安排考试类型的人数
	* @author: liangy  
	* @date 2018年12月29日
	* @param @param chb140
	* @param @param examtype
	* @param @return    
	* @return int   
	* @throws
	 */
	private int selectExamRoomPerponNum(String chb140, String examtype) {
		return apiExamRoomArrangeMapper.getcountHb74CHB341(chb140, examtype, COMAAE100Enum.DATA_EFFECTIVE.getCode());
	}
	
	/**
	 * 考场编排座位号生成  需要循环遍历num + chb330.size()遍(num为需要生成座位号集合的大小) 如果后面维护的童鞋有消耗更小的办法 可以替换掉该方法
	* @author: liangy  
	* @date 2018年12月20日
	* @param @param chb330  待分配、已使用的座位号集合(注：集合传入前需要按照从小到大顺序排序，如果sql没改,是按从小到大排的。。。)
	* @param @param num 考场规定的可容纳人数
	* @param @return    
	* @return List<Integer>   
	* @throws
	 */
	private List<Integer> seatNumberMake(List<String> chb330, int num){
		// 返回的结果集
		List<Integer> result = new ArrayList<>();
		if (chb330 != null) {
			// 已使用的座位号数组
			int[] seatGroup = new int[num + 1]; 
			// 座位号从1开始发放 所以下标、上限都+1
			for (int i = 1; i <= chb330.size(); i ++) {
				if (StringUtils.isNotBlank(chb330.get(i - 1))) {
					// 有值:放入已使用的座位号数组中
					seatGroup[i] = Integer.parseInt(chb330.get(i - 1));
				} else {
					// 无值:默认0,下次遍历时填充对应座位号
					seatGroup[i] = 0;
				}
			}
			int biaoji = 0;
			// 找出空闲的座位号编号 共循环“考场规定的可容纳人数”次
			for (int i = 1; i <= seatGroup.length - 1; i ++) {
				// 初始||正常(没有空闲的座位)情况下
				if (biaoji == 0 || biaoji == i) {
					biaoji = i;
				}
				if (seatGroup[biaoji] == 0) {
					// 空座位号 直接记录 并开始下次循环
					result.add(i);
					// 标记置为正常标记
					biaoji = i + 1;
					continue;
				}
				// 数组中的值（正常情况下是连续的从1开始）+ 总座位数 = 下标(同样从1开始) + 总座位数
				if (seatGroup[biaoji] + num == i + num) {
					// 没有空闲的座位  标记置为正常增长
					biaoji = i + 1;
				} else {
					// 有空闲的座位
					// 记录座位号
					result.add(i);
					// 开始下次循环  标记不变 直到满足条件(意味着匹配上 开始比对集合中下一个座位号)
				}
			}
		} else {
			// 该考场没有被分配过 所有座位号都能使用
			for (int i = 1; i <= num; i ++) {
				result.add(i);
			}
		}
		return result;
	}

	private String countHb74CHB341(String chb140){
		int count = selectExamRoomPerponNum(chb140, HB77EXAMTYPEEnum.THEORY_EXAM.getCode());
		if (count == 0) {
			return HB74CHB341Enum.WAIT_ARRANGE.getCode();
		}
		return HB74CHB341Enum.COMPLETE_ARRANGE.getCode();
		// 已废 编排是否完毕不再跟批次走
		// 获取理论考场是否编排完毕
//		int lilun = selectExamRoomPerponNum(chb140, HB77EXAMTYPEEnum.THEORY_EXAM.getCode());
//		// 获取 实操考场是否编排完毕
//		int shicao = selectExamRoomPerponNum(chb140, HB77EXAMTYPEEnum.OPERATING_EXAM.getCode());
//		// 获取综合考场是否编排完毕
//		int zonghe = selectExamRoomPerponNum(chb140, HB77EXAMTYPEEnum.COMPLEX_EXAM.getCode());
//		if (lilun == 0 && shicao == 0 && zonghe == 0) {
//			// 全部编排
//			return HB74CHB341Enum.COMPLETE_ARRANGE.getCode();
//		}
//		if (lilun == 0 && shicao == 0 && zonghe > 0) {
//			// 理论操作编排
//			return HB74CHB341Enum.THEORY_OPERATING_ARRANGE.getCode();
//		}
//		if (lilun == 0 && shicao > 0 && zonghe == 0) {
//			// 理论综合编排
//			return HB74CHB341Enum.THEORY_COMPLEX_ARRANGE.getCode();
//		}
//		if (lilun > 0 && shicao == 0 && zonghe == 0) {
//			// 操作综合编排
//			return HB74CHB341Enum.OPERATING_COMPLEX_ARRANGE.getCode();
//		}
//		if (lilun == 0 && shicao > 0 && zonghe > 0) {
//			// 理论编排
//			return HB74CHB341Enum.THEORY_ARRANGE.getCode();
//		}
//		if (lilun > 0 && shicao == 0 && zonghe > 0) {
//			// 操作编排
//			return HB74CHB341Enum.OPERATING_ARRANGE.getCode();
//		}
//		if (lilun > 0 && shicao > 0 && zonghe == 0) {
//			// 综合编排
//			return HB74CHB341Enum.COMPLEX_ARRANGE.getCode();
//		}
//		// 未编排
//		return HB74CHB341Enum.WAIT_ARRANGE.getCode();
	}

	/**
	 * 更新鉴定批次表状态
	* @author: liangy  
	* @date 2018年12月24日
	* @param @param chb140  批次内码
	* @param @param chb341  考场编排中心审核状态代码
	* @param @param aae100  有效代码
	* @param @param chb146 总状态代码
	* @param @return    
	* @return String   
	* @throws
	 */
	private int saveHb74Status(String chb140, String chb341, String aae100, String chb146) {
		// 更新考场编排状态
		return apiExamRoomArrangeMapper.saveHb74Chb341(chb140, chb341, aae100, chb146);
	}

	@Override
	public AjaxReturnMsg deleteExaminationBatch(Hb70 hb70) {
		if (hb70 != null && StringUtils.isNotBlank(hb70.getSelectnodes()) && !",".equals(hb70.getSelectnodes())) {
			//去掉最后一个空字符
			hb70.setSelectnodes(hb70.getSelectnodes().substring(0, hb70.getSelectnodes().length() - 1));
		}
		Map hb70map = new HashMap();
		String[] hb70String = new String[]{hb70.getSelectnodes()};
		if (hb70.getSelectnodes().contains(",")) {
			hb70String = hb70.getSelectnodes().split(",");
		}
		hb70map.put("hb70String", hb70String);
		hb70map.put("aae100", COMAAE100Enum.DATA_INVALID.getCode());
		hb70map.put("aae011", hb70.getAae011());
		// 删除批次信息
		int i = apiExamRoomArrangeMapper.deleteExaminationBatch(hb70map);
		// 删除考试记录信息
		apiExamRoomArrangeMapper.deleteExaminationRecord(hb70map);
		// 删除人员参考信息
		// 根据考试批次删除
		hb70map.put("type", "EXAM_BATCH");
		apiExamRoomArrangeMapper.deleteExaminationPerpon(hb70map);
		if (i > 0) {
			return this.success("删除成功");
		}
		return this.error("删除失败");
	}

}
