package com.insigma.mvc.serviceimp.appraisal.JDYW_002_004;

import java.util.ArrayList;
import java.util.Date;
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
import com.insigma.enums.HB75CHB126Enum;
import com.insigma.enums.HB75CHB144Enum;
import com.insigma.enums.HB75CHB146Enum;
import com.insigma.enums.HB75CHB312Enum;
import com.insigma.enums.HC60CHB256Enum;
import com.insigma.enums.HC60CZC018Enum;
import com.insigma.enums.HC66CHC018Enum;
import com.insigma.mvc.MvcHelper;
import com.insigma.mvc.dao.appraisal.JDYW_002_004.ApiAppraisalSpeciaMapper;
import com.insigma.mvc.model.Hc63;
import com.insigma.mvc.model.appraisal.Hb75;
import com.insigma.mvc.model.appraisal.Hb75Temp;
import com.insigma.mvc.model.appraisal.Hc60Dto_Short;
import com.insigma.mvc.model.appraisal.Hc60Temp_Short;
import com.insigma.mvc.model.appraisal.Hc68Temp_Short;
import com.insigma.mvc.service.appraisal.JDYW_002_004.ApiAppraisalSpeciaService;
import com.insigma.resolver.AppException;

@Service
@Transactional(rollbackFor=Exception.class)
public class ApiAppraisalSpeciaServiceImpl extends MvcHelper implements ApiAppraisalSpeciaService{

	@Resource
	private ApiAppraisalSpeciaMapper apiAppraisalSpeciaMapper;

	//获取相应机构的鉴定申报数据
	@Override
	public PageInfo<Hb75Temp> getAppraisalSpeciaList(Hb75Temp Hb75Temp) {
		PageHelper.offsetPage(Hb75Temp.getOffset(), Hb75Temp.getLimit());
		// 计算鉴定已通过人数 加入条件
		Hb75Temp.setChb256(HC60CHB256Enum.APPRAISAL_PASS.getCode());
		// 查询有效数据
		Hb75Temp.setAae100(COMAAE100Enum.DATA_EFFECTIVE.getCode());
		// 查询专项能力鉴定申报数据
		Hb75Temp.setChb126(HB75CHB126Enum.SPECIAL_APPRAISAL.getCode());
		List<Hb75Temp> list= apiAppraisalSpeciaMapper.getAppraisalSpeciaList(Hb75Temp);
		PageInfo<Hb75Temp> pageinfo = new PageInfo<>(list);
		return pageinfo;
		
	}


	@Override
	public AjaxReturnMsg getAppraisalInfo(String chb120) {
		return this.success(apiAppraisalSpeciaMapper.getAppraisalInfo(chb120));
	}

	@Override
	public PageInfo<Hc60Temp_Short> getAppraisStudentSubmitList(
			Hc60Dto_Short hc60Dto_Short) {
		PageHelper.offsetPage(hc60Dto_Short.getOffset(), hc60Dto_Short.getLimit());
		List<Hc60Temp_Short> list= apiAppraisalSpeciaMapper.getAppraisStudentSubmitList(hc60Dto_Short);
		PageInfo<Hc60Temp_Short> pageinfo = new PageInfo<>(list);
		return pageinfo;
	}

	@Override
	public PageInfo<Hc60Temp_Short> getAppraisStudentNotSubmitList(
			Hc60Temp_Short hc60Temp_Short) {
		// 查询监管合格学员
		hc60Temp_Short.setCzc018(HC60CZC018Enum.QUALIFIED.getCode());
		// 查询已结业学员
		hc60Temp_Short.setChc018(HC66CHC018Enum.QUALIFIED.getCode());
		// 查询有效数据
		hc60Temp_Short.setAae100((COMAAE100Enum.DATA_EFFECTIVE.getCode()));
		List<Hc60Temp_Short> list= apiAppraisalSpeciaMapper.getAppraisStudentNotSubmitList(hc60Temp_Short);
		PageInfo<Hc60Temp_Short> pageinfo = new PageInfo<>(list);
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
		List<Hc60Temp_Short> list= apiAppraisalSpeciaMapper.getAppraisThisStudentList(hc60Temp_Short);
		PageInfo<Hc60Temp_Short> pageinfo = new PageInfo<>(list);
		return pageinfo;
	}

	@Override
	public PageInfo<Hc68Temp_Short> getAppraisClassList(Hc68Temp_Short hc68Temp_Short) {
		PageHelper.offsetPage(hc68Temp_Short.getOffset(), hc68Temp_Short.getLimit());
		// 查询有效数据
		hc68Temp_Short.setAae100((COMAAE100Enum.DATA_EFFECTIVE.getCode()));
		List<Hc68Temp_Short> list= apiAppraisalSpeciaMapper.getAppraisClassList(hc68Temp_Short);
		PageInfo<Hc68Temp_Short> pageinfo = new PageInfo<>(list);
		return pageinfo;
	}

	public PageInfo<Hc68Temp_Short> getAppraisClassListForLook(Hc68Temp_Short hc68Temp_Short) {
		PageHelper.offsetPage(hc68Temp_Short.getOffset(), hc68Temp_Short.getLimit());
		List<Hc68Temp_Short> list= apiAppraisalSpeciaMapper.getAppraisClassListForLook(hc68Temp_Short);
		PageInfo<Hc68Temp_Short> pageinfo = new PageInfo<>(list);
		return pageinfo;
	}
	
	@Override
	public AjaxReturnMsg insertData(Hb75 hb75) {
		try {
			// 时间类型转换
			if (StringUtils.isNotBlank(hb75.getChb400_String())) {
				hb75.setChb400(DateUtil.stringToDate(hb75.getChb400_String(), "yyyy-MM-dd"));
			}
			if (StringUtils.isNotBlank(hb75.getChb403_String())) {
				hb75.setChb403(DateUtil.stringToDate(hb75.getChb403_String(), "yyyy-MM-dd"));
			}
			if (StringUtils.isNotBlank(hb75.getChb406_String())) {
				hb75.setChb406(DateUtil.stringToDate(hb75.getChb406_String(), "yyyy-MM-dd"));
			}
		} catch (AppException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		hb75.setAae036(new Date());
		// 新增操作
		if (StringUtils.isBlank(hb75.getChb120())) {
			// 首次创建鉴定批次默认为未提交状态
			hb75.setChb146(HB75CHB146Enum.NOT_SUBMIT.getCode());
			// 设置未提交状态
			hb75.setChb326(HB75CHB146Enum.NOT_SUBMIT.getCode());
			// 获取申报年月
			hb75.setAae001(DateUtil.dateToCompactString(new Date()).substring(0, 6));
			// 设置鉴定类型
			hb75.setChb126(HB75CHB126Enum.SPECIAL_APPRAISAL.getCode());
			// 是否培训后鉴定代码
			hb75.setChb144(HB75CHB144Enum.YESANDNO_YES.getCode());
			// 鉴定人数 默认0 下次更新时查询
			hb75.setChb168(new Short("0"));
			// 获取序列 拼接批次编号
			hb75.setAae100(COMAAE100Enum.DATA_EFFECTIVE.getCode());
//			Integer date = Integer.parseInt(DateUtil.dateToCompactString(new Date()).substring(2, 4)) * 1000000;
			hb75.setChb120(apiAppraisalSpeciaMapper.getSQ_HB75_CHB120());
			int result = apiAppraisalSpeciaMapper.addAppraisBatch(hb75);
			if (result == 1) {
				return this.success("新增批次信息成功", hb75.getChb120());
			} else {
				return this.error("新增批次信息失败");
			}
		} else {
			// 校验是否属于提交状态
			if (HB75CHB146Enum.SUBMITTED.getCode().equals(hb75.getChb326())) {
				// 修改此次申报中人员状态(学员信息表)
				Hc63 hc63 = new Hc63();
				hc63.setAae100(COMAAE100Enum.DATA_EFFECTIVE.getCode());
				hc63.setChb120(hb75.getChb120());
				hc63.setAae011(HC60CHB256Enum.APPRAISAL_ING.getCode());
				int result3 = apiAppraisalSpeciaMapper.saveStudentBatch(hc63);
				if (result3 < 1) {
					return this.error("鉴定人员信息状态提交失败");
				}
				// 修改批次中有效学员申报数据状态 批量改为待审核
				hc63.setChb312(HB75CHB312Enum.WAIT_AUDIT.getCode());
				apiAppraisalSpeciaMapper.saveStudentBatchStatus(hc63);
				// 本次操作为提交操作 填充提交人信息
				// 填充状态
				hb75.setChb146(HB75CHB146Enum.SUBMITTED.getCode());
				hb75.setChb326(HB75CHB146Enum.SUBMITTED.getCode());
				// 填充操作人信息
				hb75.setChb311(new Date());
				hb75.setChb310(hb75.getAae011());
				hb75.setChb169(new Date());
				// 鉴定所审核状态
				hb75.setChb312(HB75CHB312Enum.WAIT_AUDIT.getCode());
				// 有效数据
				hb75.setAae100(COMAAE100Enum.DATA_EFFECTIVE.getCode());
			}
			// 更新操作
			int result = apiAppraisalSpeciaMapper.saveAppraisBatch(hb75);
			if (result == 1) {
				return this.success("更新批次信息成功", hb75.getChb120());
			} else {
				return this.error("更新批次信息失败");
			}
		}
		
	}

	@Override
	public AjaxReturnMsg savePerponDataBath(Hc63 hc63) {
		List<Hc63> hc63Batch = new ArrayList();
		if (StringUtils.isBlank(hc63.getSelectnodes())) {
			return this.error("请至少选择一个学员放入本批次");
		}
		if (StringUtils.isBlank(hc63.getChb068())) {
			return this.error("未能找到学员的班级信息,请刷新页面重试");
		}
		//去掉最后一个空字符
		hc63.setSelectnodes(hc63.getSelectnodes().substring(0, hc63.getSelectnodes().length() - 1));
		String[] chc001Batch = hc63.getSelectnodes().split(",");
		// 遍历对象
		for (String chc001 : chc001Batch) {
			// 填充个人信息
			Hc63 hc = new Hc63();
			hc.setChc001(chc001);
			hc63Batch.add(hc);
		}

		// 无效化本批次中原有信息
		int result0 = apiAppraisalSpeciaMapper.deletePerponDataBatch(COMAAE100Enum.DATA_INVALID.getCode(), hc63.getChb120(), hc63.getChb068());

		// 新增申报数据
		Map hc63map = new HashMap();
		hc63map.put("chb120", hc63.getChb120());
		hc63map.put("aca111", hc63.getAca111());
		hc63map.put("aca11a", hc63.getAca11a());
		hc63map.put("aae011", hc63.getAae011());
		hc63map.put("aae100", COMAAE100Enum.DATA_EFFECTIVE.getCode());
		hc63map.put("chb312", HB75CHB312Enum.WAIT_AUDIT.getCode());
		hc63map.put("chb068", hc63.getChb068());
		hc63map.put("hc63Batch", hc63Batch);
		int result = apiAppraisalSpeciaMapper.savePerponDataBath(hc63map);
		// 修改 63表中该人员的鉴定审核状态
//		Map hc60map = new HashMap();
//		hc60map.put("chb256", HC60CHB256Enum.APPRAISAL_ING);
//		hc60map.put("hc60Batch", hc63Batch);
//		int result2 = apiAppraisalSpeciaMapper.saveStudentBatch(hc60map);
		if (result == chc001Batch.length) {
			return this.success("批量加入成功");
		} else {
			return this.error("批量加入完成,可能有部分数据未能录入,请检查");
		}
	}


	@Override
	public AjaxReturnMsg deletebyid(Hb75 hb75) {
		if (hb75 != null && StringUtils.isNotBlank(hb75.getChb120())) {
			// 逻辑删除批次中的学员申报信息  chc068传入参数为null时 删除所有
			int result0 = apiAppraisalSpeciaMapper.deletePerponDataBatch(COMAAE100Enum.DATA_INVALID.getCode(), hb75.getChb120(), null);
			// 逻辑删除
			hb75.setAae100(COMAAE100Enum.DATA_INVALID.getCode());
			int result = apiAppraisalSpeciaMapper.deletebyid(hb75);
			if (result == 1) {
				return this.success("删除成功");
			} else {
				return this.error("删除失败,数据可能已经被删除,请检查");
			}
		}
		return this.error("参数错误,请检查");
	}

	@Override
	public AjaxReturnMsg deletebybatch(Hb75 hb75) {
		if (hb75 != null && StringUtils.isNotBlank(hb75.getSelectnodes()) && !",".equals(hb75.getSelectnodes())) {
			//去掉最后一个空字符
			hb75.setSelectnodes(hb75.getSelectnodes().substring(0, hb75.getSelectnodes().length() - 1));
			String[] chb120Batch = hb75.getSelectnodes().split(",");
			Map hb75map = new HashMap();
			List<Hb75> hb75Batch = new ArrayList();
			// 遍历对象
			for (String chb120 : chb120Batch) {
				// 填充个人信息
				Hb75 hb = new Hb75();
				hb.setChb120(chb120);
				hb75Batch.add(hb);
			}
			hb75map.put("aae011", hb75.getAae011());
			hb75map.put("aae100", COMAAE100Enum.DATA_INVALID.getCode());
			hb75map.put("hb75Batch", hb75Batch);
			int result1 = apiAppraisalSpeciaMapper.saveStudentHc63Batch(hb75map);
			int result = apiAppraisalSpeciaMapper.deletebyBatch(hb75map);
			if (result == chb120Batch.length) {
				return this.success("删除成功");
			} else {
				return this.error("删除失败,数据可能已经被删除,请检查");
			}
		}
		return this.error("参数错误,请检查");
	}

}
