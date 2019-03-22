package com.insigma.mvc.service.appraisal.JDYW_002_003;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.insigma.dto.AjaxReturnMsg;
import com.insigma.mvc.model.CodeValue;
import com.insigma.mvc.model.Hc63;
import com.insigma.mvc.model.appraisal.Hb75;
import com.insigma.mvc.model.appraisal.Hb75Temp;
import com.insigma.mvc.model.appraisal.Hc60Dto_Short;
import com.insigma.mvc.model.appraisal.Hc60Temp_Short;
import com.insigma.mvc.model.appraisal.Hc68Temp_Short;

public interface ApiAppraisalDailyService {

	PageInfo<Hb75Temp> getAppraisalDailyList(Hb75Temp hb75Temp);

	AjaxReturnMsg getAppraisalInfo(String chb120);

	PageInfo<Hc60Temp_Short> getAppraisStudentSubmitList(Hc60Dto_Short hc60Dto_Short);

	PageInfo<Hc60Temp_Short> getAppraisStudentNotSubmitList(Hc60Temp_Short hc60Temp_Short);

	PageInfo<Hc68Temp_Short> getAppraisClassList(Hc68Temp_Short hc68Temp_Short);
	
	PageInfo<Hc68Temp_Short> getAppraisClassListForLook(Hc68Temp_Short hc68Temp_Short);

	PageInfo<Hc60Temp_Short> getAppraisThisStudentList(Hc60Temp_Short hc60Temp_Short);

	AjaxReturnMsg insertData(Hb75 hb75);

	AjaxReturnMsg savePerponDataBath(Hc63 hc63);

	AjaxReturnMsg deletebyid(Hb75 hb75);

	AjaxReturnMsg deletebybatch(Hb75 hb75);

	List<Hb75Temp> getAppraisCoreList(Hb75Temp hb75Temp);

	/**
	 * 获取单位资质
	 */
	AjaxReturnMsg<List<CodeValue>> getAca111List(CodeValue codevalue);
	/**
	 * 获取单位资质等级
	 */
	AjaxReturnMsg<List<CodeValue>> getAca11aList(String id, String aca111);
}
