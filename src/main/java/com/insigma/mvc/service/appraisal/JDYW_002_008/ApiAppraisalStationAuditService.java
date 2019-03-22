package com.insigma.mvc.service.appraisal.JDYW_002_008;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.insigma.dto.AjaxReturnMsg;
import com.insigma.mvc.model.CodeValue;
import com.insigma.mvc.model.appraisal.Hb75;
import com.insigma.mvc.model.appraisal.Hb75Temp;
import com.insigma.mvc.model.appraisal.Hc60Temp_Short;
import com.insigma.mvc.model.appraisal.Hc63Temp_Short;

public interface ApiAppraisalStationAuditService {

	PageInfo<Hb75Temp> getAppraisalSpeciaList(Hb75Temp Hb75Temp);

	AjaxReturnMsg getAppraisalInfo(String chb120);

	PageInfo<Hc60Temp_Short> getAppraisStudentNotSubmitList(Hc60Temp_Short hc60Temp_Short);

	PageInfo<Hc60Temp_Short> getAppraisThisStudentList(Hc60Temp_Short hc60Temp_Short);

	AjaxReturnMsg getPerponAuditInfo(String chc063);
	
	AjaxReturnMsg savePerponAudit(Hc63Temp_Short hc63Temp_Short);

	AjaxReturnMsg getPerponAuditIsPass(String chb120);

	AjaxReturnMsg savePerponAuditAll(Hb75 hb75);

	AjaxReturnMsg<List<CodeValue>> getTrainComName(CodeValue codevalue);
}
