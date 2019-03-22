package com.insigma.mvc.service.appraisal.JDYW_002_004;

import com.github.pagehelper.PageInfo;
import com.insigma.dto.AjaxReturnMsg;
import com.insigma.mvc.model.Hc63;
import com.insigma.mvc.model.appraisal.Hb75;
import com.insigma.mvc.model.appraisal.Hb75Temp;
import com.insigma.mvc.model.appraisal.Hc60Dto_Short;
import com.insigma.mvc.model.appraisal.Hc60Temp_Short;
import com.insigma.mvc.model.appraisal.Hc68Temp_Short;

public interface ApiAppraisalSpeciaService {

	PageInfo<Hb75Temp> getAppraisalSpeciaList(Hb75Temp hb75Temp);

	AjaxReturnMsg getAppraisalInfo(String chb120);

	PageInfo<Hc60Temp_Short> getAppraisStudentSubmitList(Hc60Dto_Short hc60Dto_Short);

	PageInfo<Hc60Temp_Short> getAppraisStudentNotSubmitList(Hc60Temp_Short hc60Temp_Short);
	
	PageInfo<Hc60Temp_Short> getAppraisThisStudentList(Hc60Temp_Short hc60Temp_Short);

	PageInfo<Hc68Temp_Short> getAppraisClassList(Hc68Temp_Short hc68Temp_Short);
	
	PageInfo<Hc68Temp_Short> getAppraisClassListForLook(Hc68Temp_Short hc68Temp_Short);

	AjaxReturnMsg insertData(Hb75 hb75);

	AjaxReturnMsg savePerponDataBath(Hc63 hc63);

	AjaxReturnMsg deletebyid(Hb75 hb75);

	AjaxReturnMsg deletebybatch(Hb75 hb75);
}
