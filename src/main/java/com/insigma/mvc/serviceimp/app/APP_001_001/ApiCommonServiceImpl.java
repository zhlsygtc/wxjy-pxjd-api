package com.insigma.mvc.serviceimp.app.APP_001_001;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.insigma.dto.AjaxReturnMsg;
import com.insigma.mvc.MvcHelper;
import com.insigma.mvc.dao.app.APP_001_001.ApiCommonMapper;
import com.insigma.mvc.model.AdditionalModel;
import com.insigma.mvc.model.Ce01;
import com.insigma.mvc.model.SUser;
import com.insigma.mvc.model.train.Hc61;
import com.insigma.mvc.service.app.APP_001_001.ApiCommonService;

@Service
public class ApiCommonServiceImpl extends MvcHelper implements ApiCommonService{

	@Resource
	ApiCommonMapper apiCommonMapper;

	/**
     * 获取个人基本信息
     * @param suser
     * @return
     * @throws Exception
     */
	@Override
	public AjaxReturnMsg getPersonBaseInfo(SUser suser) {
		Ce01 ce01 = apiCommonMapper.getCe01ByEec001(suser.getBaseinfoid());
        return this.success(ce01);
	}

	/**
	 * 根据用户信息获取学员信息
	 * @return
	 */
	@Override
	public Hc61 getStudentInfo(AdditionalModel add) {
		Hc61 hc61 = apiCommonMapper.getStudentInfo(add);
		if(hc61 == null) {
		    hc61 = new Hc61();
			hc61.setChc111("");
		}
		return hc61;
	}
	
}
