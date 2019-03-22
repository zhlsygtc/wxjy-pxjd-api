package com.insigma.mvc.serviceimp.app.APP_001_008;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.insigma.dto.AjaxReturnMsg;
import com.insigma.mvc.MvcHelper;
import com.insigma.mvc.dao.app.APP_001_001.ApiCommonMapper;
import com.insigma.mvc.dao.app.APP_001_008.ApiMessageMapper;
import com.insigma.mvc.model.train.Hc61;
import com.insigma.mvc.model.train.S_info;
import com.insigma.mvc.service.app.APP_001_008.ApiMessageService;

@Service
public class ApiMessageServiceImpl extends MvcHelper implements ApiMessageService{

	@Resource
	ApiMessageMapper apiMessageMapper;
	@Resource
	ApiCommonMapper apiCommonMapper;

	/**
     * ��ȡδ����Ϣ����
     * @param info ��Ϣ����
     * @throws Exception
     */
	@Override
	public String getInfoNum(S_info info) {
		Hc61 hc61 = apiCommonMapper.getStudentInfo(info);
		if(hc61 == null) {
			hc61 = new Hc61();
			hc61.setChc111("");
		}
		info.setChc111(hc61.getChc111());
		info.setIs_read("0");
		List<S_info> list = apiMessageMapper.getInfos(info);
		String num = list.size()+"";
		return num;
	}

	/**
     * ��ȡ��Ϣ
     * @param info ��Ϣ����
     * @throws Exception
     */
	@Override
	public AjaxReturnMsg getInfoS(S_info info) {
		Hc61 hc61 = apiCommonMapper.getStudentInfo(info);
		if(hc61 == null) {
			hc61 = new Hc61();
			hc61.setChc111("");
		}
		info.setChc111(hc61.getChc111());
		List<S_info> list = apiMessageMapper.getInfos(info);
		PageHelper.startPage(info.getCurpage(), 10);
		PageInfo<S_info> pageinfo = new PageInfo<>(list);
		return this.success(pageinfo);
	}

	/**
     * �Ķ���Ϣ
     * @param info ��Ϣ����
     * @throws Exception
     */
	@Override
	public AjaxReturnMsg readInfo(S_info info) {
		int num = apiMessageMapper.readInfo(info);
		if(num == 1) {
			return this.success("�����ɹ�");
		}else{
			return this.error("����ʧ��");
		}
	}
	
	
}
