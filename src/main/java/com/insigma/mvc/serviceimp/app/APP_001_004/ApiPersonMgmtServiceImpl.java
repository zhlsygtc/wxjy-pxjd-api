package com.insigma.mvc.serviceimp.app.APP_001_004;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.insigma.common.util.UUIDGenerator;
import com.insigma.dto.AjaxReturnMsg;
import com.insigma.mvc.MvcHelper;
import com.insigma.mvc.dao.app.APP_001_001.ApiCommonMapper;
import com.insigma.mvc.dao.app.APP_001_004.ApiPersonMgmtMapper;
import com.insigma.mvc.model.train.Hb65;
import com.insigma.mvc.model.train.Hc61;
import com.insigma.mvc.model.train.Student;
import com.insigma.mvc.service.app.APP_001_004.ApiPersonMgmtService;

@Service
public class ApiPersonMgmtServiceImpl extends MvcHelper implements ApiPersonMgmtService {

	@Resource
	ApiPersonMgmtMapper apiPersonMgmtMapper;
	
	@Resource
	ApiCommonMapper apiCommonMapper;
	
	/**
	 * ѧԱ�����ƻ���ʼ
	 */
	@Override
	public AjaxReturnMsg signup(Student stu) {
		
		Hc61 hc61 = apiCommonMapper.getStudentInfo(stu);
		
		Map map = new HashMap();
		
		//����Ƿ��ѵǼ���ѧԱ��Ϣ
		if(hc61 == null) {
			map.put("status", "0");
			return this.success("���ȵǼ�ѧԱ��Ϣ", map);
		}
		stu.setChc111(hc61.getChc111());
		
		//��ѯѧԱ�Ƿ��ѱ����üƻ�
		String count = apiPersonMgmtMapper.findStuWithPlan(stu);
		if(!count.equals("0")) {
			map.put("status", "2");
			return this.error("���ѱ����üƻ��������ظ�����", map);
		}
		
		//��ѯѧԱһ�����Ƿ��Ѳμ���ѵ
		String num = apiPersonMgmtMapper.checkNum(stu);
		if(!num.equals("0")) {
			map.put("status", "2");
			return this.error("����ʧ�ܣ�һ����ֻ�ܲμ�һ����ѵ", map);
		}
		
		//��ѯ��ǰҪ�����ļƻ�
		Hb65 hb65 = apiPersonMgmtMapper.getCurrentPlan(stu);
		
		hb65.setChc111(hc61.getChc111());
		
		//У��֮ǰ�Ƿ���ͬ���ֵĽϸߵȼ���ѵ
		String index = apiPersonMgmtMapper.checkGrade(hb65);
		if(!index.equals("0")) {
			map.put("status", "2");
			return this.error("����ʧ�ܣ����Ѳμӹ����ߵȼ��ĸ�רҵ��ѵ��", map);
		}else{
			//У��֮ǰ�Ƿ���ͬ���ֵ�ͬ�ȼ���ѵ
			hb65.setFlag("1");
			index = apiPersonMgmtMapper.checkGrade(hb65);
			if(!index.equals("0")) {
				map.put("status", "2");
				return this.error("����ʧ�ܣ����Ѳμӹ�ͬ�ȼ��ĸ�רҵ��ѵ��", map);
			}
		}
		
		map.put("status", "1");
		return this.success("������Ϣ",map);
		
	}

	/**
     * ��ȡѧԱ������Ϣ
     */
	@Override
	public AjaxReturnMsg getStudentInfo(Student stu) {
		stu = apiPersonMgmtMapper.getStudentInfo(stu);
		return this.success(stu);
	}

	/**
     * ��ȡ��(ѧ)Ա������Ϣ
     */
	@Override
	public AjaxReturnMsg getPersonInfo(Student stu) {
		
		Hc61 hc61 = apiCommonMapper.getStudentInfo(stu);
		if(hc61 == null) {
			stu = apiPersonMgmtMapper.getPersonInfo(stu);
		}else{
			stu.setChc111(hc61.getChc111());
			stu = apiPersonMgmtMapper.getStudentInfo(stu);
		}
		return this.success(stu);
	}

	/**
     * ����ѧԱ��Ϣ
     */
	@Override
	public AjaxReturnMsg saveStudent(Student stu) {
		
		if(stu.getChc111()==null || "".equals(stu.getChc111())) {
			String nextNo = apiPersonMgmtMapper.getNextNo();
			stu.setChc111(UUIDGenerator.generate());
			stu.setAac001("0");
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
			String dataS = stu.getAac002().substring(6, 14);
			Date aac006 = null;
			try {
				aac006 = sdf.parse(dataS);
			} catch (ParseException e) {
				return this.error("ʱ��ת������");
			}
			stu.setAac006(aac006);
			String str = stu.getAac002().substring(16, 17);
			int i = Integer.parseInt(str) % 2;
			if(i == 0) {
				stu.setAac004("2");
			}else{
				stu.setAac004("1");
			}
			
			stu.setAac006(aac006);
			stu.setIsselected("0");
			stu.setEae052("1");
			stu.setAae100("1");
			stu.setAab001(stu.getBaseinfoid());
			stu.setChc066(nextNo);
			
			int num = apiPersonMgmtMapper.saveStu(stu);
			if(num == 1) {
				Map map = new HashMap();
				map.put("chc111", stu.getChc111());
				return this.success("����ɹ�", map);
			}else{
				return this.error("����ʧ��");
			}
		}else{
			stu.setAae036(new Date());
			int num = apiPersonMgmtMapper.updateStu(stu);
			Map map = new HashMap();
			map.put("chc111", stu.getChc111());
			if(num == 1) {
				return this.success("����ɹ�", map);
			}else{
				return this.error("����ʧ��");
			}
		}
		
		
	}
	
	/**
     * ѧԱ�����ƻ�����
     * @param stu ѧԱ����
     * @throws Exception
     */
	@Override
	public AjaxReturnMsg apply(Student stu) {
		int num = apiPersonMgmtMapper.apply(stu);
		if(num == 1) {
			return this.success("�����ɹ�");
		}else{
			return this.error("����ʧ��");
		}
	}
	
	
	
}
