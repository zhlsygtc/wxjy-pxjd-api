package com.insigma.mvc.serviceimp.appraisal.JDYW_002_012;

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
import com.insigma.enums.HB74CHB17CEnum;
import com.insigma.enums.HB74CHB326Enum;
import com.insigma.enums.HB74CHB341Enum;
import com.insigma.enums.HB75CHB146Enum;
import com.insigma.enums.HB77EXAMTYPEEnum;
import com.insigma.enums.HC73CAC022Enum;
import com.insigma.enums.HC75CAC009Enum;
import com.insigma.enums.HC75EAE052Enum;
import com.insigma.mvc.MvcHelper;
import com.insigma.mvc.dao.appraisal.JDYW_002_012.ApiPersondispatchMapper;
import com.insigma.mvc.model.CodeValue;
import com.insigma.mvc.model.Hc63;
import com.insigma.mvc.model.appraisal.Hb74;
import com.insigma.mvc.model.appraisal.Hb74Temp_Short;
import com.insigma.mvc.model.appraisal.Hb77;
import com.insigma.mvc.model.appraisal.Hb79;
import com.insigma.mvc.model.appraisal.Hc73;
import com.insigma.mvc.model.appraisal.Hc73Temp_Short;
import com.insigma.mvc.service.appraisal.JDYW_002_012.ApiPersondispatchService;
import com.insigma.resolver.AppException;

@Service
@Transactional(rollbackFor=Exception.class)
public class ApiPersondispatchServiceImpl extends MvcHelper implements ApiPersondispatchService{

	@Resource
	private ApiPersondispatchMapper apiPersondispatchMapper;

	//获取相应机构的鉴定申报数据
	@Override
	public PageInfo<Hb74Temp_Short> getPersondispatchList(Hb74Temp_Short hb74Temp_Short) {
		PageHelper.offsetPage(hb74Temp_Short.getOffset(), hb74Temp_Short.getLimit());
		// 查询考场编排阶段以上数据(不包含考场编排通过数据)
		List<String> chb146list = new ArrayList();
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
		List<CodeValue> chb146Code = new ArrayList();
		CodeValue codeValue = new CodeValue();
		codeValue.setCode_value("4");// 自定义排序 倒序
		codeValue.setCode_name(HB75CHB146Enum.EXAMINATION_ROOM_ARRANGEMENT_PASS.getCode());
		chb146Code.add(codeValue);
		codeValue = new CodeValue();
		codeValue.setCode_value("3");// 排序 倒序
		codeValue.setCode_name(HB75CHB146Enum.PERPON_ASSIGN_NOT_PASS.getCode());
		chb146Code.add(codeValue);
		codeValue = new CodeValue();
		codeValue.setCode_value("2");// 排序 倒序
		codeValue.setCode_name(HB75CHB146Enum.PERPON_ASSIGN.getCode());
		chb146Code.add(codeValue);
		codeValue = new CodeValue();
		codeValue.setCode_value("1");// 排序 倒序
		codeValue.setCode_name(HB75CHB146Enum.PERPON_ASSIGN_PASS.getCode());
		chb146Code.add(codeValue);
		// 加入排序条件
		hb74Temp_Short.setChb146Code(chb146Code);
		// 处理编排状态多选
		if (StringUtils.isNotBlank(hb74Temp_Short.getChb17c()) && !hb74Temp_Short.getChb17c().equals("undefined")) {
			chb146list = new ArrayList();
			if (hb74Temp_Short.getChb17c().contains(",")) {
				// 多选
				String[] chb17cs = hb74Temp_Short.getChb17c().split(",");
				for (String chb17c : chb17cs) {
					chb146list.add(chb17c);
				}
			} else {
				// 单选
				chb146list.add(hb74Temp_Short.getChb17c());
			}
			hb74Temp_Short.setChb17clist(chb146list);
		}
		// 查询有效数据
		hb74Temp_Short.setAae100(COMAAE100Enum.DATA_EFFECTIVE.getCode());
		hb74Temp_Short.setCac022(HC73CAC022Enum.YESANDNO_YES.getCode());
		List<Hb74Temp_Short> list= apiPersondispatchMapper.getPersondispatchList(hb74Temp_Short);
		PageInfo<Hb74Temp_Short> pageinfo = new PageInfo<>(list);
		return pageinfo;
	}

	@Override
	public PageInfo<Hb77> getExamRoomList(Hb77 hb77) {
		PageHelper.offsetPage(hb77.getOffset(), hb77.getLimit());
		// 查询 考评员 监考员
		hb77.setCac022(HC73CAC022Enum.YESANDNO_YES.getCode());
		// 查询有效数据
		hb77.setAae100(COMAAE100Enum.DATA_EFFECTIVE.getCode());
		List<Hb77> list= apiPersondispatchMapper.getExamRoomList(hb77);
		PageInfo<Hb77> pageinfo = new PageInfo<>(list);
		return pageinfo;
	}

	@Override
	public AjaxReturnMsg examRoomSave(Hb77 hb77) {
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
			// 新增操作
			result = apiPersondispatchMapper.examRoomInsert(hb77);
		} else {
			// 编辑操作
			result = apiPersondispatchMapper.examRoomSave(hb77);
		}
		saveHb74Status(hb77.getChb140(), countHb74CHB341(hb77.getChb140()), COMAAE100Enum.DATA_EFFECTIVE.getCode(), HB75CHB146Enum.EXAMINATION_ROOM_ARRANGEMENT.getCode(), HB74CHB326Enum.UNSUBMITTED.getCode());
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
		return this.success(apiPersondispatchMapper.getAppraisalInfo(chb140));
	}

	@Override
	public PageInfo<Hc63> getExamRoomPerponList(Hc63 hc63) {
		PageHelper.offsetPage(hc63.getOffset(), hc63.getLimit());
		// 查询有效数据
		hc63.setAae100(COMAAE100Enum.DATA_EFFECTIVE.getCode());
		List<Hc63> list= apiPersondispatchMapper.getExamRoomPerponList(hc63);
		PageInfo<Hc63> pageinfo = new PageInfo<>(list);
		return pageinfo;
	}

	@Override
	public PageInfo<Hc63> getExamRoomPerponSelectList(Hc63 hc63) {
		PageHelper.offsetPage(hc63.getOffset(), hc63.getLimit());
		// 查询有效数据
		hc63.setAae100(COMAAE100Enum.DATA_EFFECTIVE.getCode());
		List<Hc63> list= apiPersondispatchMapper.getExamRoomPerponSelectList(hc63);
		PageInfo<Hc63> pageinfo = new PageInfo<>(list);
		return pageinfo;
	}

	@Override
	public AjaxReturnMsg savePerponExamRoom(Hc63 hc63) {
		if (hc63 != null && StringUtils.isNotBlank(hc63.getSelectnodes()) && !",".equals(hc63.getSelectnodes())) {
			// 查询当前考场信息
			Hb77 hb77info = apiPersondispatchMapper.getExamRoomInfo(hc63.getChb077(), COMAAE100Enum.DATA_EFFECTIVE.getCode());
			if (hb77info == null) {
				return this.error("获取当前考场信息失败");
			}
			// 查询当前考场座位使用情况
			Hb77 hb77number = new Hb77();
			hb77number.setExamtype(hc63.getExamtype());
			hb77number.setChb077(hc63.getChb077());
			hb77number.setChb140(hc63.getChb140());
			// 获取批次中所有本考场人员信息
			List<String> chb330 = apiPersondispatchMapper.getseatnumber(hb77number);
			// 获取空座位集合
			List<Integer> zuowei = seatNumberMake(chb330, hb77info.getChb166());
			//去掉最后一个空字符
			hc63.setSelectnodes(hc63.getSelectnodes().substring(0, hc63.getSelectnodes().length() - 1));
			String[] chc001Batch = hc63.getSelectnodes().split(",");
			// 如果需要安排的座位数大于目前空闲的座位数 则放弃分配 直接返回
			if (chc001Batch.length > zuowei.size()) {
				return this.error("已超过当前考场可容纳人数 剩余可容纳人数" + zuowei.size() + "人，待安排座位人数" + chc001Batch.length + "人" );
			}
			Map hc63map = new HashMap();
			List<Hc63> hc63Batch = new ArrayList();
			int jishu = 0;
			// 遍历对象
			for (String chc001 : chc001Batch) {
				// 填充个人信息
				Hc63 hc = new Hc63();
				hc.setChb330(String.valueOf(zuowei.get(jishu)));
				hc.setChc001(chc001);
				hc63Batch.add(hc);
				jishu ++;
			}
			hc63map.put("chb077", hc63.getChb077());
			hc63map.put("chb140", hc63.getChb140());
			hc63map.put("examtype", hc63.getExamtype());
			hc63map.put("aae100", COMAAE100Enum.DATA_INVALID.getCode());
			hc63map.put("hc63Batch", hc63Batch);
			int result = apiPersondispatchMapper.savePerponExamHc63Batch(hc63map);
			// 更新考场编排状态
			saveHb74Status(hc63.getChb140(), countHb74CHB341(hc63.getChb140()), COMAAE100Enum.DATA_EFFECTIVE.getCode(), HB75CHB146Enum.EXAMINATION_ROOM_ARRANGEMENT.getCode(), HB74CHB326Enum.UNSUBMITTED.getCode());
			return this.success("加入考场成功");
		}
		return this.error("参数错误,请检查");
	}

	@Override
	public AjaxReturnMsg deletePerponExamRoom(Hc63 hc63) {
		if (hc63 != null && StringUtils.isNotBlank(hc63.getSelectnodes()) && !",".equals(hc63.getSelectnodes())) {
			//去掉最后一个空字符
			hc63.setSelectnodes(hc63.getSelectnodes().substring(0, hc63.getSelectnodes().length() - 1));
			String[] chc001Batch = hc63.getSelectnodes().split(",");
			Map hc63map = new HashMap();
			List<Hc63> hc63Batch = new ArrayList();
			// 遍历对象
			for (String chc001 : chc001Batch) {
				// 填充个人信息
				Hc63 hc = new Hc63();
				hc.setChc001(chc001);
				hc63Batch.add(hc);
			}
			hc63map.put("examtype", hc63.getExamtype());
			// 清空座位号
			hc63map.put("chb330", null);
			// 清空所在考场
			hc63map.put("chb060", null);
			hc63map.put("aae100", COMAAE100Enum.DATA_INVALID.getCode());
			hc63map.put("hc63Batch", hc63Batch);
			int result = apiPersondispatchMapper.deletePerponExamHc63Batch(hc63map);
			// 更新考场编排状态
			saveHb74Status(hc63.getChb140(), countHb74CHB341(hc63.getChb140()), COMAAE100Enum.DATA_EFFECTIVE.getCode(), HB75CHB146Enum.EXAMINATION_ROOM_ARRANGEMENT.getCode(), HB74CHB326Enum.UNSUBMITTED.getCode()
					
					);
			if (result > 0) {
				return this.success("移出考场成功");
			} else {
				return this.error("移出考场失败");
			}
		}
		return this.error("参数错误,请检查");
	}

	@Override
	public AjaxReturnMsg getExamRoomBaseInfo(String chb077) {
		if (StringUtils.isBlank(chb077)) {
			return this.error("参数错误，请登录重试");
		}
		Hb77 hb77 = apiPersondispatchMapper.getExamRoomBaseInfo(chb077, COMAAE100Enum.DATA_EFFECTIVE.getCode());
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
			int result = apiPersondispatchMapper.deletePerponExamRoomBatch(hb77map);
			int result2 = apiPersondispatchMapper.deleteExamRoomBatch(hb77map);
			return this.success("删除成功");
		}
		return this.error("参数错误,请检查");
	}

	@Override
	public AjaxReturnMsg saveExamRoomSubmit(Hb74 hb74) {
		// 如果是提交操作  更改总状态
		if (hb74 != null && HB74CHB341Enum.WAIT_AUDIT.getCode().equals(hb74.getChb341()) && StringUtils.isNotBlank(hb74.getChb140())) {
			// 修改批次中总状态
			int result = saveHb74Status(hb74.getChb140(), HB74CHB341Enum.WAIT_AUDIT.getCode(), COMAAE100Enum.DATA_EFFECTIVE.getCode(), HB75CHB146Enum.EXAMINATION_ROOM_ARRANGEMENT.getCode(), HB74CHB326Enum.SUBMITTED.getCode());
			if (result == 1) {
				return this.success("提交成功");
			} else {
				return this.error("提交失败");
			}
		}
		return this.error("参数错误,请检查");
	}

	@Override
	public List<Hc73Temp_Short> getAppraiserList(Hc73Temp_Short hc73Temp_Short) {
		// 查询当前考场需要的资质
		Hb77 hb77 = apiPersondispatchMapper.getExamRoomBaseInfo(hc73Temp_Short.getChb077(), COMAAE100Enum.DATA_EFFECTIVE.getCode());
		// 查询该考场需要的资质
		List<String> aca109List = apiPersondispatchMapper.getAca109List(hc73Temp_Short.getChb077(), hb77.getExamtype(), COMAAE100Enum.DATA_EFFECTIVE.getCode());
		if (aca109List != null) {
			hc73Temp_Short.setAca109List(aca109List);
		}
		// 加入考场内码
		hc73Temp_Short.setChb077(hc73Temp_Short.getChb077());
		// 加入聘任情况查询条件
		hc73Temp_Short.setCac009(HC75CAC009Enum.APPOINTED.getCode());
		// 加入数据有效条件
		hc73Temp_Short.setAae100(COMAAE100Enum.DATA_EFFECTIVE.getCode());
		// 加入审核通过查询条件
		hc73Temp_Short.setEae052(HC75EAE052Enum.AUDIT_PASS.getCode());
		// 加入考试时间段查询条件
		hc73Temp_Short.setChb164(hb77.getChb164_string());
		List<Hc73Temp_Short> hb73TempList = apiPersondispatchMapper.getAppraiserList(hc73Temp_Short);
		return hb73TempList;
	}

	@Override
	public AjaxReturnMsg saveAppraisCorePerson(Hb79 hb79) {
		if (hb79 != null && (StringUtils.isNotEmpty(hb79.getAac001_selectnodes()) || StringUtils.isNotBlank(hb79.getAac002_selectnodes()))) {
			List<Hb79> hb79list = new ArrayList();
			if (StringUtils.isNotBlank(hb79.getAac001_selectnodes())) {
				String[] aac001_selectnodes = null;
				if (hb79.getAac001_selectnodes().contains(",")) {
					aac001_selectnodes = hb79.getAac001_selectnodes().split(",");
				} else {
					aac001_selectnodes = new String[]{hb79.getAac001_selectnodes()};
				}
				// 考评员加入
				for (String selectnodes : aac001_selectnodes) {
					if (StringUtils.isNotBlank(selectnodes)) {
						Hb79 hb = new Hb79();
						hb.setChb140(hb79.getChb140());
						hb.setAac001(selectnodes);
						hb.setAae011(hb79.getAae011());
						hb.setAae100(COMAAE100Enum.DATA_EFFECTIVE.getCode());
						hb.setChb077(hb79.getChb077());
						hb.setCac022(HC73CAC022Enum.YESANDNO_YES.getCode());
						hb79list.add(hb);
					}
				}
			}
			if (StringUtils.isNotBlank(hb79.getAac002_selectnodes())) {
				String[] aac002_selectnodes = null;
				if (hb79.getAac002_selectnodes().contains(",")) {
					aac002_selectnodes = hb79.getAac002_selectnodes().split(",");
				} else {
					aac002_selectnodes = new String[]{hb79.getAac002_selectnodes()};
				}
				// 监考员加入
				for (String selectnodes : aac002_selectnodes) {
					if (StringUtils.isNotBlank(selectnodes)) {
						Hb79 hb = new Hb79();
						hb.setChb140(hb79.getChb140());
						hb.setAac001(selectnodes);
						hb.setAae011(hb79.getAae011());
						hb.setAae100(COMAAE100Enum.DATA_EFFECTIVE.getCode());
						hb.setChb077(hb79.getChb077());
						hb.setCac023(HC73CAC022Enum.YESANDNO_YES.getCode());
						hb79list.add(hb);
					}
				}
			}
			// 清除该批次之前的指派记录
			apiPersondispatchMapper.deleteAppraisCorePerson(hb79.getChb140(), COMAAE100Enum.DATA_INVALID.getCode());
			// 开始新增
			int result = apiPersondispatchMapper.saveAppraisCorePerson(hb79list);
			if (result > 0) {
				String chb17c = null;
				if (StringUtils.isNotBlank(hb79.getChb17c_status()) && HB74CHB17CEnum.DISPATCHED.getCode().equals(hb79.getChb17c_status())) {
					chb17c = HB74CHB17CEnum.DISPATCHED.getCode();
				}
				if (StringUtils.isNotBlank(hb79.getChb17c_status()) && HB74CHB17CEnum.WAIT_AUDIT.getCode().equals(hb79.getChb17c_status())) {
					chb17c = HB74CHB17CEnum.WAIT_AUDIT.getCode();
				}
				
				// 更新批次状态
				return saveAppraisPersonStatus(hb79.getChb140(), chb17c);
			} else {
				return this.error("保存失败");
			}
		}
		return this.error("参数错误,请检查");
	}

	@Override
	public AjaxReturnMsg saveAppraisPersonStatus(String chb140, String chb17c) {
		int result = apiPersondispatchMapper.saveAppraisPersonStatus(chb140, chb17c, HB75CHB146Enum.PERPON_ASSIGN.getCode(), COMAAE100Enum.DATA_EFFECTIVE.getCode());
		if (result == 1) {
			return this.success("更新成功");
		} else {
			return this.error("更新失败");
		}
	}

	@Override
	public List<Hc73> getAppraisCorePersonList(String chb140, String chb077, String aab001){
		List<Hc73> hb79List = apiPersondispatchMapper.getAppraisCorePersonList(chb140, chb077, aab001,COMAAE100Enum.DATA_EFFECTIVE.getCode());
		return hb79List;
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
		}
		return result;
	}

	private String countHb74CHB341(String chb140){
		// 获取理论考场是否编排完毕
		int lilun = apiPersondispatchMapper.getcountHb74CHB341(chb140, HB77EXAMTYPEEnum.THEORY_EXAM.getCode(), COMAAE100Enum.DATA_EFFECTIVE.getCode());
		// 获取 实操考场是否编排完毕
		int shicao = apiPersondispatchMapper.getcountHb74CHB341(chb140, HB77EXAMTYPEEnum.OPERATING_EXAM.getCode(), COMAAE100Enum.DATA_EFFECTIVE.getCode());
		// 获取综合考场是否编排完毕
		int zonghe = apiPersondispatchMapper.getcountHb74CHB341(chb140, HB77EXAMTYPEEnum.COMPLEX_EXAM.getCode(), COMAAE100Enum.DATA_EFFECTIVE.getCode());
		if (lilun == 0 && shicao == 0 && zonghe == 0) {
			// 全部编排
			return HB74CHB341Enum.COMPLETE_ARRANGE.getCode();
		}
		if (lilun == 0 && shicao == 0 && zonghe > 0) {
			// 理论操作编排
			return HB74CHB341Enum.THEORY_OPERATING_ARRANGE.getCode();
		}
		if (lilun == 0 && shicao > 0 && zonghe == 0) {
			// 理论综合编排
			return HB74CHB341Enum.THEORY_COMPLEX_ARRANGE.getCode();
		}
		if (lilun > 0 && shicao == 0 && zonghe == 0) {
			// 操作综合编排
			return HB74CHB341Enum.OPERATING_COMPLEX_ARRANGE.getCode();
		}
		if (lilun == 0 && shicao > 0 && zonghe > 0) {
			// 理论编排
			return HB74CHB341Enum.THEORY_ARRANGE.getCode();
		}
		if (lilun > 0 && shicao == 0 && zonghe > 0) {
			// 操作编排
			return HB74CHB341Enum.OPERATING_ARRANGE.getCode();
		}
		if (lilun > 0 && shicao > 0 && zonghe == 0) {
			// 综合编排
			return HB74CHB341Enum.COMPLEX_ARRANGE.getCode();
		}
		// 未编排
		return HB74CHB341Enum.WAIT_ARRANGE.getCode();
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
	private int saveHb74Status(String chb140, String chb341, String aae100, String chb146, String chb326) {
		// 更新考场编排状态
		return apiPersondispatchMapper.saveHb74Chb341(chb140, chb341, aae100, chb146, chb326);
	}

}
