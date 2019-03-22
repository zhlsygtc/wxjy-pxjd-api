package com.insigma.mvc.serviceimp.appraisal.JDYW_002_009;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.insigma.common.util.DateUtil;
import com.insigma.dto.AjaxReturnMsg;
import com.insigma.enums.COMAAE100Enum;
import com.insigma.enums.HB74CHB326Enum;
import com.insigma.enums.HB74CHB334Enum;
import com.insigma.enums.HB75CHB126Enum;
import com.insigma.enums.HB75CHB146Enum;
import com.insigma.enums.HB75CHB312Enum;
import com.insigma.enums.HC60CZC018Enum;
import com.insigma.enums.HC66CHC018Enum;
import com.insigma.mvc.MvcHelper;
import com.insigma.mvc.dao.appraisal.JDYW_002_009.ApiAppraisalStationDeclareMapper;
import com.insigma.mvc.model.CodeValue;
import com.insigma.mvc.model.appraisal.Hb74;
import com.insigma.mvc.model.appraisal.Hb74Temp_Short;
import com.insigma.mvc.model.appraisal.Hb75;
import com.insigma.mvc.model.appraisal.Hb75Temp;
import com.insigma.mvc.model.appraisal.Hc60Temp_Short;
import com.insigma.mvc.service.appraisal.JDYW_002_009.ApiAppraisalStationDeclareService;
import com.insigma.resolver.AppException;

@Service
@Transactional(rollbackFor=Exception.class)
public class ApiAppraisalStationDeclareServiceImpl extends MvcHelper implements ApiAppraisalStationDeclareService{

	@Resource
	private ApiAppraisalStationDeclareMapper appraisalStationDeclareMapper;

	//获取相应机构的鉴定申报数据
	@Override
	public PageInfo<Hb74Temp_Short> getAppraisalSpeciaList(Hb74Temp_Short hb74Temp_Short) {
		PageHelper.offsetPage(hb74Temp_Short.getOffset(), hb74Temp_Short.getLimit());
		// 查询中心审核阶段数据(不包含中心审核通过数据)[暂时放弃 显示全部 后续需要可以加上]
//		List<String> chb146list = new ArrayList();
//		chb146list.add(HB75CHB146Enum.APPRAISAL_DECLARE.getCode());
//		chb146list.add(HB75CHB146Enum.IDENTIFICATION_CENTER_NOT_PASS.getCode());
//		hb74Temp_Short.setChb146list(chb146list);
		List<CodeValue> chb146Code = new ArrayList();
		CodeValue codeValue = new CodeValue();
		codeValue.setCode_value("3");// 排序 倒序
		codeValue.setCode_name(HB75CHB146Enum.APPRAISAL_DECLARE.getCode());
		chb146Code.add(codeValue);
		codeValue = new CodeValue();
		codeValue.setCode_value("2");// 排序 倒序
		codeValue.setCode_name(HB75CHB146Enum.APPRAISAL_REVIEW_NOT_PASS.getCode());
		chb146Code.add(codeValue);
		codeValue = new CodeValue();
		codeValue.setCode_value("1");// 排序 倒序
		codeValue.setCode_name(HB75CHB146Enum.APPRAISAL_REVIEW_PASS.getCode());
		chb146Code.add(codeValue);
		// 加入排序条件
		hb74Temp_Short.setChb146Code(chb146Code);
		// 查询有效数据
		hb74Temp_Short.setAae100(COMAAE100Enum.DATA_EFFECTIVE.getCode());
		List<Hb74Temp_Short> list= appraisalStationDeclareMapper.getAppraisalSpeciaList(hb74Temp_Short);
		PageInfo<Hb74Temp_Short> pageinfo = new PageInfo<>(list);
		return pageinfo;
	}

	@Override
	public AjaxReturnMsg getAppraisalInfo(String chb140) {
		if (StringUtils.isBlank(chb140)) {
			return this.error("参数错误，请登录重试");
		}
		return this.success(appraisalStationDeclareMapper.getAppraisalInfo(chb140));
	}

	@Override
	public AjaxReturnMsg savePerponAudit(Hb75Temp hb75Temp, String operation) {
		// 移入操作时校验鉴定类型合法性
		if ("in".equals(operation) && HB75CHB126Enum.fromCode(hb75Temp.getChb126()) == null) {
			return this.error("鉴定类型参数不合法");
		}
		if (StringUtils.isBlank(hb75Temp.getSelectnodes())) {
			return this.error("请至少选择一个批次进行操作");
		}
		// 判断鉴定批次号是否为空  为空是第一次人员编排 74表先新增对应数据
		Hb74 hb74 = null;
		if (StringUtils.isEmpty(hb75Temp.getChb140())) {
			// 新增74表信息
			hb74 = new Hb74();
			// 批次序列号生成
			// 获取序列 拼接批次编号
			hb74.setAae100(COMAAE100Enum.DATA_EFFECTIVE.getCode());
//			Integer date = Integer.parseInt(DateUtil.dateToCompactString(new Date()).substring(2, 4)) * 100000;
			hb74.setChb140(appraisalStationDeclareMapper.getSQ_HB74_CHB140());
			// 总状态代码 设置为鉴定所站申报状态
			hb74.setChb146(HB75CHB146Enum.APPRAISAL_DECLARE.getCode());
			// 提交状态设为未提交状态
			hb74.setChb326(HB74CHB326Enum.UNSUBMITTED.getCode());
			// 经办人信息录入
			hb74.setAae011(hb75Temp.getAae011());
			// 鉴定类型代码 默认为第一批次鉴定类型 最后一个批次移出后 该鉴定类型记得清空
			hb74.setChb126(hb75Temp.getChb126());
			// 所属单位内码
			hb74.setAab001(hb75Temp.getAab001());
			// 开始鉴定计划新增操作
			int result = appraisalStationDeclareMapper.insertAppraisalPlan(hb74);
			if (result != 1) {
				return this.error("鉴定计划生成失败,请重试");
			}
			// 填充鉴定批次号
			hb75Temp.setChb140(hb74.getChb140());
		}

		// 校验鉴定批次数据、机构批次、批次鉴定类型参数合法性(如果是刚生成批次,跳过校验)
		if (hb74 == null) {
			hb74 = appraisalStationDeclareMapper.getcheckAppraisalPlan(hb75Temp.getChb140(), COMAAE100Enum.DATA_EFFECTIVE.getCode());
			if (hb74 == null) {
				return this.error("未能找到该鉴定计划，请检查该计划是否已经被删除");
			}
			if (StringUtils.isBlank(hb75Temp.getAab001()) || !hb75Temp.getAab001().equals(hb74.getAab001())) {
				return this.error("您对该鉴定计划没有操作权限，请登录重试");
			}
			if ("in".equals(operation) && !hb75Temp.getChb126().equals(hb74.getChb126())) {
				return this.error("加入计划的鉴定类型与计划规定的鉴定类型不一致");
			}
		}
		
		// 更新计划中的批次信息
		//去掉最后一个空字符
		hb75Temp.setSelectnodes(hb75Temp.getSelectnodes().substring(0, hb75Temp.getSelectnodes().length() - 1));
		String[] chb120Batch = hb75Temp.getSelectnodes().split(",");
		// 遍历对象
		Map<String, Object> hb75map = new HashMap();
		List<Hb75> hb75list = new ArrayList();
		for (String chb120 : chb120Batch) {
			// 填充个人信息
			Hb75 hb = new Hb75();
			hb.setChb120(chb120);
			hb75list.add(hb);
		}
		String chb140 = "";
		String chb146 = null;
		// 如果是加入计划 填充chb140的值
		if ("in".equals(operation)) {
			chb140 = hb75Temp.getChb140();
			// 移入总状态修改为"鉴定所站申报"
			chb146 = HB75CHB146Enum.APPRAISAL_DECLARE.getCode();
		} else {
			// 移出总状态修改为"鉴定所站审核通过"
			chb146 = HB75CHB146Enum.APPRAISAL_REVIEW_PASS.getCode();
		}
		hb75map.put("chb312", HB75CHB312Enum.AUDIT_PASS.getCode());
		hb75map.put("chb140", chb140);
		hb75map.put("chb146", chb146);
		hb75map.put("aae100", COMAAE100Enum.DATA_EFFECTIVE.getCode());
		hb75map.put("hb75list", hb75list);
		//修改75中的chb140字段
		int result2 = appraisalStationDeclareMapper.saveAppraisBatchHb74(hb75map);
		//修改63关联的chb140字段
		int result3 = appraisalStationDeclareMapper.saveAppraisBatchHc63(hb75map);
		// 更新批次鉴定人数
		int result4 = appraisalStationDeclareMapper.savePerponSum(hb74.getChb140(), COMAAE100Enum.DATA_EFFECTIVE.getCode());
		if (result2 > 0 && result3 > 0) {
			return this.success("计划更新成功", hb74.getChb140());
		}
		return this.error("计划更新失败");
	}

	@Override
	public PageInfo<Hb75Temp> getAppraisalDeclareist(Hb75Temp hb75Temp) {
		PageHelper.offsetPage(hb75Temp.getOffset(), hb75Temp.getLimit());
		// 查询有效数据
		hb75Temp.setAae100(COMAAE100Enum.DATA_EFFECTIVE.getCode());
		List<Hb75Temp> list= appraisalStationDeclareMapper.getAppraisalDeclareist(hb75Temp);
		PageInfo<Hb75Temp> pageinfo = new PageInfo<>(list);
		return pageinfo;
	}

	@Override
	public PageInfo<Hc60Temp_Short> getAppraisThisStudentList(
			Hc60Temp_Short hc60Temp_Short) {
		// 监管合格
		hc60Temp_Short.setCzc018(HC60CZC018Enum.QUALIFIED.getCode());
		// 结业考试合格
		hc60Temp_Short.setChc018(HC66CHC018Enum.QUALIFIED.getCode());
		// 查询有效数据
		hc60Temp_Short.setAae100((COMAAE100Enum.DATA_EFFECTIVE.getCode()));
		List<Hc60Temp_Short> list= appraisalStationDeclareMapper.getAppraisThisStudentList(hc60Temp_Short);
		PageInfo<Hc60Temp_Short> pageinfo = new PageInfo<>(list);
		return pageinfo;
	}

	@Override
	public AjaxReturnMsg savePerponSubmit(Hb74 hb74) {
		// 校验提交状态
		if (HB74CHB326Enum.fromCode(hb74.getChb326()) == null) {
			return this.error("提交状态参数不合法");
		}
		try {
			// 时间类型转换
			if (StringUtils.isNotBlank(hb74.getChb400_String())) {
				hb74.setChb400(DateUtil.stringToDate(hb74.getChb400_String(), "yyyy-MM-dd"));
			}
			if (StringUtils.isNotBlank(hb74.getChb403_String())) {
				hb74.setChb403(DateUtil.stringToDate(hb74.getChb403_String(), "yyyy-MM-dd"));
			}
			if (StringUtils.isNotBlank(hb74.getChb406_String())) {
				hb74.setChb406(DateUtil.stringToDate(hb74.getChb406_String(), "yyyy-MM-dd"));
			}
		} catch (AppException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String message = "保存成功";
		// 判断提交状态 (保存操作、提交操作)
		if (HB74CHB326Enum.SUBMITTED.getCode().equals(hb74.getChb326())) {
			// 状态改为鉴定所站提交状态
			hb74.setChb146(HB75CHB146Enum.APPRAISAL_DECLARE.getCode());
			hb74.setChb334(HB74CHB334Enum.WAIT_AUDIT.getCode());
			message = "提交成功";
		}
		hb74.setAae100(COMAAE100Enum.DATA_EFFECTIVE.getCode());
		int result = appraisalStationDeclareMapper.savePerponSubmit(hb74);
		if (result == 1) {
			return this.success(message);
		} else {
			return this.error("更新失败，数据可能已经被删除，请检查");
		}
	}

	@Override
	public AjaxReturnMsg delAppraisalDeclare(Hb74 hb74) {
		// 校验鉴定申请批次号是否为空
		if (StringUtils.isBlank(hb74.getChb140())) {
			return this.error("参数错误，请重试");
		}
		// 遍历对象
		Map<String, Object> hb74map = new HashMap();
		List<Hb74> hb74list = new ArrayList();
		// 75数据总状态还原
		hb74list.add(hb74);
		hb74map.put("hb74list", hb74list);
		hb74map.put("chb146", HB75CHB146Enum.APPRAISAL_REVIEW_PASS.getCode());
		//修改75中的chb140字段
		int result = appraisalStationDeclareMapper.delAppraisAllEmptyHb74(hb74map);
		//修改63关联的chb140字段
		int result1 = appraisalStationDeclareMapper.delAppraisAllEmptyHc63(hb74map);
		// 数据置为无效
		hb74.setAae100(COMAAE100Enum.DATA_INVALID.getCode());
		// 逻辑删除对应批次数据
		int result2 = appraisalStationDeclareMapper.delAppraisalDeclare(hb74);
		if (result2 != 1) {
			return this.error("鉴定申请数据删除失败");
		}
		return this.success("删除成功");
	}

	@Override
	public AjaxReturnMsg delAppraisalDeclareBatch(Hb74 hb74) {
		// 校验鉴定申请批次号是否为空
		if (StringUtils.isBlank(hb74.getSelectnodes())) {
			return this.error("参数错误，请重试");
		}
		// 遍历对象
		Map<String, Object> hb74map = new HashMap();
		List<Hb74> hb74list = new ArrayList();
		// 75数据总状态还原
		//去掉最后一个空字符
		hb74.setSelectnodes(hb74.getSelectnodes().substring(0, hb74.getSelectnodes().length() - 1));
		String[] chb140Batch = hb74.getSelectnodes().split(",");
		// 遍历对象
		for (String chb140 : chb140Batch) {
			// 填充个人信息
			Hb74 hb = new Hb74();
			hb.setChb140(chb140);
			hb74list.add(hb);
		}
		hb74list.add(hb74);
		hb74map.put("hb74list", hb74list);
		hb74map.put("chb146", HB75CHB146Enum.APPRAISAL_REVIEW_PASS.getCode());
		//修改75中的chb140字段
		int result = appraisalStationDeclareMapper.delAppraisAllEmptyHb74(hb74map);
		//修改63关联的chb140字段
		int result1 = appraisalStationDeclareMapper.delAppraisAllEmptyHc63(hb74map);
		// 数据置为无效
		hb74.setAae100(COMAAE100Enum.DATA_INVALID.getCode());
		// 逻辑删除对应批次数据
		int result2 = appraisalStationDeclareMapper.delAppraisalDeclareBatch(hb74map);
		if (result2 == chb140Batch.length) {
			return this.success("删除成功");
		}
		return this.error("鉴定申请数据删除失败");
	}

}
