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

	//��ȡ��Ӧ�����ļ����걨����
	@Override
	public PageInfo<Hb74Temp_Short> getPersondispatchList(Hb74Temp_Short hb74Temp_Short) {
		PageHelper.offsetPage(hb74Temp_Short.getOffset(), hb74Temp_Short.getLimit());
		// ��ѯ�������Ž׶���������(��������������ͨ������)
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
		codeValue.setCode_value("4");// �Զ������� ����
		codeValue.setCode_name(HB75CHB146Enum.EXAMINATION_ROOM_ARRANGEMENT_PASS.getCode());
		chb146Code.add(codeValue);
		codeValue = new CodeValue();
		codeValue.setCode_value("3");// ���� ����
		codeValue.setCode_name(HB75CHB146Enum.PERPON_ASSIGN_NOT_PASS.getCode());
		chb146Code.add(codeValue);
		codeValue = new CodeValue();
		codeValue.setCode_value("2");// ���� ����
		codeValue.setCode_name(HB75CHB146Enum.PERPON_ASSIGN.getCode());
		chb146Code.add(codeValue);
		codeValue = new CodeValue();
		codeValue.setCode_value("1");// ���� ����
		codeValue.setCode_name(HB75CHB146Enum.PERPON_ASSIGN_PASS.getCode());
		chb146Code.add(codeValue);
		// ������������
		hb74Temp_Short.setChb146Code(chb146Code);
		// �������״̬��ѡ
		if (StringUtils.isNotBlank(hb74Temp_Short.getChb17c()) && !hb74Temp_Short.getChb17c().equals("undefined")) {
			chb146list = new ArrayList();
			if (hb74Temp_Short.getChb17c().contains(",")) {
				// ��ѡ
				String[] chb17cs = hb74Temp_Short.getChb17c().split(",");
				for (String chb17c : chb17cs) {
					chb146list.add(chb17c);
				}
			} else {
				// ��ѡ
				chb146list.add(hb74Temp_Short.getChb17c());
			}
			hb74Temp_Short.setChb17clist(chb146list);
		}
		// ��ѯ��Ч����
		hb74Temp_Short.setAae100(COMAAE100Enum.DATA_EFFECTIVE.getCode());
		hb74Temp_Short.setCac022(HC73CAC022Enum.YESANDNO_YES.getCode());
		List<Hb74Temp_Short> list= apiPersondispatchMapper.getPersondispatchList(hb74Temp_Short);
		PageInfo<Hb74Temp_Short> pageinfo = new PageInfo<>(list);
		return pageinfo;
	}

	@Override
	public PageInfo<Hb77> getExamRoomList(Hb77 hb77) {
		PageHelper.offsetPage(hb77.getOffset(), hb77.getLimit());
		// ��ѯ ����Ա �࿼Ա
		hb77.setCac022(HC73CAC022Enum.YESANDNO_YES.getCode());
		// ��ѯ��Ч����
		hb77.setAae100(COMAAE100Enum.DATA_EFFECTIVE.getCode());
		List<Hb77> list= apiPersondispatchMapper.getExamRoomList(hb77);
		PageInfo<Hb77> pageinfo = new PageInfo<>(list);
		return pageinfo;
	}

	@Override
	public AjaxReturnMsg examRoomSave(Hb77 hb77) {
		hb77.setChb165(hb77.getChb165() + "~" + hb77.getChb16a());
		// ����������������ת��
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
			// У�鿼�����Ͳ����Ϸ���
			if (HB77EXAMTYPEEnum.fromCode(hb77.getExamtype()) == null) {
				return this.error("�������Ͳ�������Ϊ��");
			}
			// ��������
			result = apiPersondispatchMapper.examRoomInsert(hb77);
		} else {
			// �༭����
			result = apiPersondispatchMapper.examRoomSave(hb77);
		}
		saveHb74Status(hb77.getChb140(), countHb74CHB341(hb77.getChb140()), COMAAE100Enum.DATA_EFFECTIVE.getCode(), HB75CHB146Enum.EXAMINATION_ROOM_ARRANGEMENT.getCode(), HB74CHB326Enum.UNSUBMITTED.getCode());
		if (result == 1) {
			return this.success("���³ɹ�", hb77.getChb077());
		}
		return this.error("����ʧ��", hb77.getChb077());
	}

	@Override
	public AjaxReturnMsg getAppraisalInfo(String chb140) {
		if (StringUtils.isBlank(chb140)) {
			return this.error("�����������¼����");
		}
		return this.success(apiPersondispatchMapper.getAppraisalInfo(chb140));
	}

	@Override
	public PageInfo<Hc63> getExamRoomPerponList(Hc63 hc63) {
		PageHelper.offsetPage(hc63.getOffset(), hc63.getLimit());
		// ��ѯ��Ч����
		hc63.setAae100(COMAAE100Enum.DATA_EFFECTIVE.getCode());
		List<Hc63> list= apiPersondispatchMapper.getExamRoomPerponList(hc63);
		PageInfo<Hc63> pageinfo = new PageInfo<>(list);
		return pageinfo;
	}

	@Override
	public PageInfo<Hc63> getExamRoomPerponSelectList(Hc63 hc63) {
		PageHelper.offsetPage(hc63.getOffset(), hc63.getLimit());
		// ��ѯ��Ч����
		hc63.setAae100(COMAAE100Enum.DATA_EFFECTIVE.getCode());
		List<Hc63> list= apiPersondispatchMapper.getExamRoomPerponSelectList(hc63);
		PageInfo<Hc63> pageinfo = new PageInfo<>(list);
		return pageinfo;
	}

	@Override
	public AjaxReturnMsg savePerponExamRoom(Hc63 hc63) {
		if (hc63 != null && StringUtils.isNotBlank(hc63.getSelectnodes()) && !",".equals(hc63.getSelectnodes())) {
			// ��ѯ��ǰ������Ϣ
			Hb77 hb77info = apiPersondispatchMapper.getExamRoomInfo(hc63.getChb077(), COMAAE100Enum.DATA_EFFECTIVE.getCode());
			if (hb77info == null) {
				return this.error("��ȡ��ǰ������Ϣʧ��");
			}
			// ��ѯ��ǰ������λʹ�����
			Hb77 hb77number = new Hb77();
			hb77number.setExamtype(hc63.getExamtype());
			hb77number.setChb077(hc63.getChb077());
			hb77number.setChb140(hc63.getChb140());
			// ��ȡ���������б�������Ա��Ϣ
			List<String> chb330 = apiPersondispatchMapper.getseatnumber(hb77number);
			// ��ȡ����λ����
			List<Integer> zuowei = seatNumberMake(chb330, hb77info.getChb166());
			//ȥ�����һ�����ַ�
			hc63.setSelectnodes(hc63.getSelectnodes().substring(0, hc63.getSelectnodes().length() - 1));
			String[] chc001Batch = hc63.getSelectnodes().split(",");
			// �����Ҫ���ŵ���λ������Ŀǰ���е���λ�� ��������� ֱ�ӷ���
			if (chc001Batch.length > zuowei.size()) {
				return this.error("�ѳ�����ǰ�������������� ʣ�����������" + zuowei.size() + "�ˣ���������λ����" + chc001Batch.length + "��" );
			}
			Map hc63map = new HashMap();
			List<Hc63> hc63Batch = new ArrayList();
			int jishu = 0;
			// ��������
			for (String chc001 : chc001Batch) {
				// ��������Ϣ
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
			// ���¿�������״̬
			saveHb74Status(hc63.getChb140(), countHb74CHB341(hc63.getChb140()), COMAAE100Enum.DATA_EFFECTIVE.getCode(), HB75CHB146Enum.EXAMINATION_ROOM_ARRANGEMENT.getCode(), HB74CHB326Enum.UNSUBMITTED.getCode());
			return this.success("���뿼���ɹ�");
		}
		return this.error("��������,����");
	}

	@Override
	public AjaxReturnMsg deletePerponExamRoom(Hc63 hc63) {
		if (hc63 != null && StringUtils.isNotBlank(hc63.getSelectnodes()) && !",".equals(hc63.getSelectnodes())) {
			//ȥ�����һ�����ַ�
			hc63.setSelectnodes(hc63.getSelectnodes().substring(0, hc63.getSelectnodes().length() - 1));
			String[] chc001Batch = hc63.getSelectnodes().split(",");
			Map hc63map = new HashMap();
			List<Hc63> hc63Batch = new ArrayList();
			// ��������
			for (String chc001 : chc001Batch) {
				// ��������Ϣ
				Hc63 hc = new Hc63();
				hc.setChc001(chc001);
				hc63Batch.add(hc);
			}
			hc63map.put("examtype", hc63.getExamtype());
			// �����λ��
			hc63map.put("chb330", null);
			// ������ڿ���
			hc63map.put("chb060", null);
			hc63map.put("aae100", COMAAE100Enum.DATA_INVALID.getCode());
			hc63map.put("hc63Batch", hc63Batch);
			int result = apiPersondispatchMapper.deletePerponExamHc63Batch(hc63map);
			// ���¿�������״̬
			saveHb74Status(hc63.getChb140(), countHb74CHB341(hc63.getChb140()), COMAAE100Enum.DATA_EFFECTIVE.getCode(), HB75CHB146Enum.EXAMINATION_ROOM_ARRANGEMENT.getCode(), HB74CHB326Enum.UNSUBMITTED.getCode()
					
					);
			if (result > 0) {
				return this.success("�Ƴ������ɹ�");
			} else {
				return this.error("�Ƴ�����ʧ��");
			}
		}
		return this.error("��������,����");
	}

	@Override
	public AjaxReturnMsg getExamRoomBaseInfo(String chb077) {
		if (StringUtils.isBlank(chb077)) {
			return this.error("�����������¼����");
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
			//ȥ�����һ�����ַ�
			hb77.setSelectnodes(hb77.getSelectnodes().substring(0, hb77.getSelectnodes().length() - 1));
			// ��ȡ�����������ַ�����
			String[] chb077AndExamtypeBatch = hb77.getSelectnodes().split(",");
			// ��������
			String[] chb077Batch = new String[hb77.getSelectnodes().split(",").length];
			// ��������
			String[] examtypeBatch = new String[hb77.getSelectnodes().split(",").length];
			// ����
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
			// ������¼���
			i = 0;
			// ��������
			for (String chb077 : chb077Batch) {
				// ��������Ϣ
				Hb77 hc = new Hb77();
				hc.setChb077(chb077);
				hc.setExamtype(examtypeBatch[i]);
				hb77Batch.add(hc);
				i ++;
			}
			hb77map.put("examtype", hb77.getExamtype());
			// �����λ��
			hb77map.put("chb330", null);
			// ������ڿ���
			hb77map.put("chb060", null);
			hb77map.put("aae100", COMAAE100Enum.DATA_INVALID.getCode());
			hb77map.put("hb77Batch", hb77Batch);
			int result = apiPersondispatchMapper.deletePerponExamRoomBatch(hb77map);
			int result2 = apiPersondispatchMapper.deleteExamRoomBatch(hb77map);
			return this.success("ɾ���ɹ�");
		}
		return this.error("��������,����");
	}

	@Override
	public AjaxReturnMsg saveExamRoomSubmit(Hb74 hb74) {
		// ������ύ����  ������״̬
		if (hb74 != null && HB74CHB341Enum.WAIT_AUDIT.getCode().equals(hb74.getChb341()) && StringUtils.isNotBlank(hb74.getChb140())) {
			// �޸���������״̬
			int result = saveHb74Status(hb74.getChb140(), HB74CHB341Enum.WAIT_AUDIT.getCode(), COMAAE100Enum.DATA_EFFECTIVE.getCode(), HB75CHB146Enum.EXAMINATION_ROOM_ARRANGEMENT.getCode(), HB74CHB326Enum.SUBMITTED.getCode());
			if (result == 1) {
				return this.success("�ύ�ɹ�");
			} else {
				return this.error("�ύʧ��");
			}
		}
		return this.error("��������,����");
	}

	@Override
	public List<Hc73Temp_Short> getAppraiserList(Hc73Temp_Short hc73Temp_Short) {
		// ��ѯ��ǰ������Ҫ������
		Hb77 hb77 = apiPersondispatchMapper.getExamRoomBaseInfo(hc73Temp_Short.getChb077(), COMAAE100Enum.DATA_EFFECTIVE.getCode());
		// ��ѯ�ÿ�����Ҫ������
		List<String> aca109List = apiPersondispatchMapper.getAca109List(hc73Temp_Short.getChb077(), hb77.getExamtype(), COMAAE100Enum.DATA_EFFECTIVE.getCode());
		if (aca109List != null) {
			hc73Temp_Short.setAca109List(aca109List);
		}
		// ���뿼������
		hc73Temp_Short.setChb077(hc73Temp_Short.getChb077());
		// ����Ƹ�������ѯ����
		hc73Temp_Short.setCac009(HC75CAC009Enum.APPOINTED.getCode());
		// ����������Ч����
		hc73Temp_Short.setAae100(COMAAE100Enum.DATA_EFFECTIVE.getCode());
		// �������ͨ����ѯ����
		hc73Temp_Short.setEae052(HC75EAE052Enum.AUDIT_PASS.getCode());
		// ���뿼��ʱ��β�ѯ����
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
				// ����Ա����
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
				// �࿼Ա����
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
			// ���������֮ǰ��ָ�ɼ�¼
			apiPersondispatchMapper.deleteAppraisCorePerson(hb79.getChb140(), COMAAE100Enum.DATA_INVALID.getCode());
			// ��ʼ����
			int result = apiPersondispatchMapper.saveAppraisCorePerson(hb79list);
			if (result > 0) {
				String chb17c = null;
				if (StringUtils.isNotBlank(hb79.getChb17c_status()) && HB74CHB17CEnum.DISPATCHED.getCode().equals(hb79.getChb17c_status())) {
					chb17c = HB74CHB17CEnum.DISPATCHED.getCode();
				}
				if (StringUtils.isNotBlank(hb79.getChb17c_status()) && HB74CHB17CEnum.WAIT_AUDIT.getCode().equals(hb79.getChb17c_status())) {
					chb17c = HB74CHB17CEnum.WAIT_AUDIT.getCode();
				}
				
				// ��������״̬
				return saveAppraisPersonStatus(hb79.getChb140(), chb17c);
			} else {
				return this.error("����ʧ��");
			}
		}
		return this.error("��������,����");
	}

	@Override
	public AjaxReturnMsg saveAppraisPersonStatus(String chb140, String chb17c) {
		int result = apiPersondispatchMapper.saveAppraisPersonStatus(chb140, chb17c, HB75CHB146Enum.PERPON_ASSIGN.getCode(), COMAAE100Enum.DATA_EFFECTIVE.getCode());
		if (result == 1) {
			return this.success("���³ɹ�");
		} else {
			return this.error("����ʧ��");
		}
	}

	@Override
	public List<Hc73> getAppraisCorePersonList(String chb140, String chb077, String aab001){
		List<Hc73> hb79List = apiPersondispatchMapper.getAppraisCorePersonList(chb140, chb077, aab001,COMAAE100Enum.DATA_EFFECTIVE.getCode());
		return hb79List;
	}
	
	/**
	 * ����������λ������  ��Ҫѭ������num + chb330.size()��(numΪ��Ҫ������λ�ż��ϵĴ�С) �������ά����ͯЬ�����ĸ�С�İ취 �����滻���÷���
	* @author: liangy  
	* @date 2018��12��20��
	* @param @param chb330  �����䡢��ʹ�õ���λ�ż���(ע�����ϴ���ǰ��Ҫ���մ�С����˳���������sqlû��,�ǰ���С�����ŵġ�����)
	* @param @param num �����涨�Ŀ���������
	* @param @return    
	* @return List<Integer>   
	* @throws
	 */
	private List<Integer> seatNumberMake(List<String> chb330, int num){
		// ���صĽ����
		List<Integer> result = new ArrayList<>();
		if (chb330 != null) {
			// ��ʹ�õ���λ������
			int[] seatGroup = new int[num + 1]; 
			// ��λ�Ŵ�1��ʼ���� �����±ꡢ���޶�+1
			for (int i = 1; i <= chb330.size(); i ++) {
				if (StringUtils.isNotBlank(chb330.get(i - 1))) {
					// ��ֵ:������ʹ�õ���λ��������
					seatGroup[i] = Integer.parseInt(chb330.get(i - 1));
				} else {
					// ��ֵ:Ĭ��0,�´α���ʱ����Ӧ��λ��
					seatGroup[i] = 0;
				}
			}
			int biaoji = 0;
			// �ҳ����е���λ�ű�� ��ѭ���������涨�Ŀ�������������
			for (int i = 1; i <= seatGroup.length - 1; i ++) {
				// ��ʼ||����(û�п��е���λ)�����
				if (biaoji == 0 || biaoji == i) {
					biaoji = i;
				}
				if (seatGroup[biaoji] == 0) {
					// ����λ�� ֱ�Ӽ�¼ ����ʼ�´�ѭ��
					result.add(i);
					// �����Ϊ�������
					biaoji = i + 1;
					continue;
				}
				// �����е�ֵ������������������Ĵ�1��ʼ��+ ����λ�� = �±�(ͬ����1��ʼ) + ����λ��
				if (seatGroup[biaoji] + num == i + num) {
					// û�п��е���λ  �����Ϊ��������
					biaoji = i + 1;
				} else {
					// �п��е���λ
					// ��¼��λ��
					result.add(i);
					// ��ʼ�´�ѭ��  ��ǲ��� ֱ����������(��ζ��ƥ���� ��ʼ�ȶԼ�������һ����λ��)
				}
			}
		}
		return result;
	}

	private String countHb74CHB341(String chb140){
		// ��ȡ���ۿ����Ƿ�������
		int lilun = apiPersondispatchMapper.getcountHb74CHB341(chb140, HB77EXAMTYPEEnum.THEORY_EXAM.getCode(), COMAAE100Enum.DATA_EFFECTIVE.getCode());
		// ��ȡ ʵ�ٿ����Ƿ�������
		int shicao = apiPersondispatchMapper.getcountHb74CHB341(chb140, HB77EXAMTYPEEnum.OPERATING_EXAM.getCode(), COMAAE100Enum.DATA_EFFECTIVE.getCode());
		// ��ȡ�ۺϿ����Ƿ�������
		int zonghe = apiPersondispatchMapper.getcountHb74CHB341(chb140, HB77EXAMTYPEEnum.COMPLEX_EXAM.getCode(), COMAAE100Enum.DATA_EFFECTIVE.getCode());
		if (lilun == 0 && shicao == 0 && zonghe == 0) {
			// ȫ������
			return HB74CHB341Enum.COMPLETE_ARRANGE.getCode();
		}
		if (lilun == 0 && shicao == 0 && zonghe > 0) {
			// ���۲�������
			return HB74CHB341Enum.THEORY_OPERATING_ARRANGE.getCode();
		}
		if (lilun == 0 && shicao > 0 && zonghe == 0) {
			// �����ۺϱ���
			return HB74CHB341Enum.THEORY_COMPLEX_ARRANGE.getCode();
		}
		if (lilun > 0 && shicao == 0 && zonghe == 0) {
			// �����ۺϱ���
			return HB74CHB341Enum.OPERATING_COMPLEX_ARRANGE.getCode();
		}
		if (lilun == 0 && shicao > 0 && zonghe > 0) {
			// ���۱���
			return HB74CHB341Enum.THEORY_ARRANGE.getCode();
		}
		if (lilun > 0 && shicao == 0 && zonghe > 0) {
			// ��������
			return HB74CHB341Enum.OPERATING_ARRANGE.getCode();
		}
		if (lilun > 0 && shicao > 0 && zonghe == 0) {
			// �ۺϱ���
			return HB74CHB341Enum.COMPLEX_ARRANGE.getCode();
		}
		// δ����
		return HB74CHB341Enum.WAIT_ARRANGE.getCode();
	}

	/**
	 * ���¼������α�״̬
	* @author: liangy  
	* @date 2018��12��24��
	* @param @param chb140  ��������
	* @param @param chb341  ���������������״̬����
	* @param @param aae100  ��Ч����
	* @param @param chb146 ��״̬����
	* @param @return    
	* @return String   
	* @throws
	 */
	private int saveHb74Status(String chb140, String chb341, String aae100, String chb146, String chb326) {
		// ���¿�������״̬
		return apiPersondispatchMapper.saveHb74Chb341(chb140, chb341, aae100, chb146, chb326);
	}

}
