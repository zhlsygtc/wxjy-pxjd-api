package com.insigma.mvc.serviceimp.appraisal.JDYW_002_011;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.eclipse.jetty.util.StringUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.insigma.common.util.DateUtil;
import com.insigma.dto.AjaxReturnMsg;
import com.insigma.enums.COMAAE100Enum;
import com.insigma.enums.HB74CHB341Enum;
import com.insigma.enums.HB75CHB146Enum;
import com.insigma.enums.HB77EXAMTYPEEnum;
import com.insigma.mvc.MvcHelper;
import com.insigma.mvc.dao.appraisal.JDYW_002_011.ApiExamRoomArrangeMapper;
import com.insigma.mvc.model.CodeValue;
import com.insigma.mvc.model.Hc63;
import com.insigma.mvc.model.appraisal.Hb70;
import com.insigma.mvc.model.appraisal.Hb71;
import com.insigma.mvc.model.appraisal.Hb74;
import com.insigma.mvc.model.appraisal.Hb74Temp_Short;
import com.insigma.mvc.model.appraisal.Hb76;
import com.insigma.mvc.model.appraisal.Hb77;
import com.insigma.mvc.model.appraisal.Hb78;
import com.insigma.mvc.service.appraisal.JDYW_002_011.ApiExamRoomArrangeService;
import com.insigma.resolver.AppException;

@Service
@Transactional(rollbackFor=Exception.class)
public class ApiExamRoomArrangeServiceImpl extends MvcHelper implements ApiExamRoomArrangeService{

	@Resource
	private ApiExamRoomArrangeMapper apiExamRoomArrangeMapper;

	//��ȡ��Ӧ�����ļ����걨����
	@Override
	public PageInfo<Hb74Temp_Short> getAppraisalSpeciaList(Hb74Temp_Short hb74Temp_Short) {
		PageHelper.offsetPage(hb74Temp_Short.getOffset(), hb74Temp_Short.getLimit());
		// ��ѯ�������Ž׶�����(��������������ͨ������)
		List<String> chb146list = new ArrayList();
		chb146list.add(HB75CHB146Enum.IDENTIFICATION_CENTER_PASS.getCode());
		chb146list.add(HB75CHB146Enum.EXAMINATION_ROOM_ARRANGEMENT_NOT_PASS.getCode());
		chb146list.add(HB75CHB146Enum.EXAMINATION_ROOM_ARRANGEMENT.getCode());
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
		// �������״̬��ѡ
		if (StringUtils.isNotBlank(hb74Temp_Short.getChb341())) {
			chb146list = new ArrayList();
			if (hb74Temp_Short.getChb341().contains(",")) {
				// ��ѡ
				String[] chb341s = hb74Temp_Short.getChb341().split(",");
				for (String chb341 : chb341s) {
					chb146list.add(chb341);
				}
			} else {
				// ��ѡ
				chb146list.add(hb74Temp_Short.getChb341());
			}
			hb74Temp_Short.setChb341list(chb146list);
		}
		// ������������״̬����ָ��
		List<CodeValue> chb146Code = new ArrayList();
		CodeValue codeValue = new CodeValue();
		codeValue.setCode_value("4");// ���� ����
		codeValue.setCode_name(HB75CHB146Enum.IDENTIFICATION_CENTER_PASS.getCode());
		chb146Code.add(codeValue);
		codeValue = new CodeValue();
		codeValue.setCode_value("3");// ���� ����
		codeValue.setCode_name(HB75CHB146Enum.EXAMINATION_ROOM_ARRANGEMENT_NOT_PASS.getCode());
		chb146Code.add(codeValue);
		codeValue = new CodeValue();
		codeValue.setCode_value("2");// ���� ����
		codeValue.setCode_name(HB75CHB146Enum.EXAMINATION_ROOM_ARRANGEMENT.getCode());
		chb146Code.add(codeValue);
		codeValue = new CodeValue();
		codeValue.setCode_value("1");// ���� ����
		codeValue.setCode_name(HB75CHB146Enum.EXAMINATION_ROOM_ARRANGEMENT_PASS.getCode());
		chb146Code.add(codeValue);
		// ������������
		hb74Temp_Short.setChb146Code(chb146Code);
		// ��ѯ��Ч����
		hb74Temp_Short.setAae100(COMAAE100Enum.DATA_EFFECTIVE.getCode());
		List<Hb74Temp_Short> list= apiExamRoomArrangeMapper.getAppraisalSpeciaList(hb74Temp_Short);
		PageInfo<Hb74Temp_Short> pageinfo = new PageInfo<>(list);
		return pageinfo;
	}

	@Override
	public PageInfo<Hb70> getExamRoomBatchList(Hb70 hb70) {
		PageHelper.offsetPage(hb70.getOffset(), hb70.getLimit());
		// ���������Ͷ�ѡ
		if (StringUtils.isNotBlank(hb70.getExamtype())) {
			List<String> examtypeList = new ArrayList();
			if (hb70.getExamtype().contains(",")) {
				// ��ѡ
				String[] examtypes = hb70.getExamtype().split(",");
				for (String examtype : examtypes) {
					examtypeList.add(examtype);
				}
			} else {
				// ��ѡ
				examtypeList.add(hb70.getExamtype());
			}
			hb70.setExamtypeList(examtypeList);
		}
		hb70.setAae100(COMAAE100Enum.DATA_EFFECTIVE.getCode());
		List<Hb70> list= apiExamRoomArrangeMapper.getExamRoomBatchList(hb70);
		PageInfo<Hb70> pageinfo = new PageInfo<>(list);
		return pageinfo;
	}

	@Override
	public PageInfo<Hb77> getExamRoomList(Hb77 hb77) {
		PageHelper.offsetPage(hb77.getOffset(), hb77.getLimit());
		// ���������Ͷ�ѡ
		if (StringUtils.isNotBlank(hb77.getExamtype())) {
			List<String> examtypeList = new ArrayList();
			if (hb77.getExamtype().contains(",")) {
				// ��ѡ
				String[] examtypes = hb77.getExamtype().split(",");
				for (String examtype : examtypes) {
					examtypeList.add(examtype);
				}
			} else {
				// ��ѡ
				examtypeList.add(hb77.getExamtype());
			}
			hb77.setExamtypeList(examtypeList);
		}
		hb77.setAae100(COMAAE100Enum.DATA_EFFECTIVE.getCode());
		List<Hb77> list= apiExamRoomArrangeMapper.getExamRoomList(hb77);
		PageInfo<Hb77> pageinfo = new PageInfo<>(list);
		return pageinfo;
	}

	@Override
	public AjaxReturnMsg examRoomBatchSave(Hb70 hb70) {
		// ���Կ�ʼ������ʱ�� �Ƚ�
		if (StringUtils.isNotBlank(hb70.getChb165()) && StringUtils.isNotBlank(hb70.getChb16a())) {
			try {
				// ʱ���ʽ����ת��
				Date chb165 = DateUtil.stringToDate(hb70.getChb164_string() + " " + hb70.getChb165(), "yyyy-MM-dd HH:mm");
				Date chb16a = DateUtil.stringToDate(hb70.getChb164_string() + " " + hb70.getChb16a(), "yyyy-MM-dd HH:mm");
				// ʱ��У��
				if (!DateUtil.compare(chb16a, chb165, 0)) {
					return this.error("���Խ���ʱ��������ڿ�ʼʱ��");
				}
			} catch (AppException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			return this.error("���Կ�ʼ���ڡ���ʼʱ�䡢����ʱ�䲻��Ϊ��");
		}
		hb70.setChb165(hb70.getChb165() + "~" + hb70.getChb16a());
		// ����������������ת��
		if (StringUtils.isNotBlank(hb70.getChb164_string())) {
			try {
				hb70.setChb164(DateUtil.stringToDate(hb70.getChb164_string(), "yyyy-MM-dd"));
			} catch (AppException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		hb70.setAae100(COMAAE100Enum.DATA_EFFECTIVE.getCode());
		int result = 0;
		if (StringUtils.isBlank(hb70.getChb070())) {
			// У�鿼�����Ͳ����Ϸ���
			if (HB77EXAMTYPEEnum.fromCode(hb70.getExamtype()) == null) {
				return this.error("�������Ͳ�������Ϊ��");
			}
			// ��������
			result = apiExamRoomArrangeMapper.examRoomBatchInsert(hb70);
		} else {
			// ��ȡ�ɵĿ���������Ϣ
			Hb70 oldhb70 = apiExamRoomArrangeMapper.getExamRoomBaseBatchInfo(hb70.getChb070(), COMAAE100Enum.DATA_EFFECTIVE.getCode());
			// �༭����
			result = apiExamRoomArrangeMapper.examRoomBatchSave(hb70);
			// ���ʱ�䷢����� ���¿������������п��Լ�¼��Ϣ
			if (oldhb70.getChb164() != hb70.getChb164()) {
				oldhb70 = new Hb70();
				oldhb70.setChb070(hb70.getChb070());
				oldhb70.setChb164(hb70.getChb164());
				oldhb70.setAae011(hb70.getAae011());
				oldhb70.setAae100(hb70.getAae100());
				int result1 = apiExamRoomArrangeMapper.saveBatchExamination(oldhb70);
			}
		}
		saveHb74Status(hb70.getChb140(), countHb74CHB341(hb70.getChb140()), COMAAE100Enum.DATA_EFFECTIVE.getCode(), null);
		if (result == 1) {
			return this.success("���³ɹ�", hb70.getChb070());
		}
		return this.error("����ʧ��", hb70.getChb070());
	}

	@Override
	public AjaxReturnMsg examRoomSave(Hb77 hb77) {
		// ���Կ�ʼ������ʱ�� �Ƚ�
		if (StringUtils.isNotBlank(hb77.getChb165()) && StringUtils.isNotBlank(hb77.getChb16a())) {
			try {
				// ʱ���ʽ����ת��
				Date chb165 = DateUtil.stringToDate(hb77.getChb164_string() + " " + hb77.getChb165(), "yyyy-MM-dd HH:mm");
				Date chb16a = DateUtil.stringToDate(hb77.getChb164_string() + " " + hb77.getChb16a(), "yyyy-MM-dd HH:mm");
				// ʱ��У��
				if (!DateUtil.compare(chb16a, chb165, 0)) {
					return this.error("���Խ���ʱ��������ڿ�ʼʱ��");
				}
			} catch (AppException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			return this.error("���Կ�ʼ���ڡ���ʼʱ�䡢����ʱ�䲻��Ϊ��");
		}
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
			// �����ǰ�������ο���������� �����������������
			// ��ѯ��ǰ���δ����俼������
			Hc63 hc63 = new Hc63();
			hc63.setAae100(COMAAE100Enum.DATA_EFFECTIVE.getCode());
			hc63.setChb070(hb77.getChb070());
			hc63.setChb140(hb77.getChb140());
			List<Hc63> hc63list = apiExamRoomArrangeMapper.getExamRoomPerponSelectList(hc63);
			if (hc63list.isEmpty()) {
				return this.error("��ǰ�������ο����Ѿ��������,���������������������");
			}
			// ��������
			result = apiExamRoomArrangeMapper.examRoomInsert(hb77);
		} else {
			// �༭����
			result = apiExamRoomArrangeMapper.examRoomSave(hb77);
		}
		saveHb74Status(hb77.getChb140(), countHb74CHB341(hb77.getChb140()), COMAAE100Enum.DATA_EFFECTIVE.getCode(), null);
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
		return this.success(apiExamRoomArrangeMapper.getAppraisalInfo(chb140));
	}

	@Override
	public PageInfo<Hc63> getExamRoomPerponList(Hc63 hc63) {
		PageHelper.offsetPage(hc63.getOffset(), hc63.getLimit());
		// ��ѯ��Ч����
		hc63.setAae100(COMAAE100Enum.DATA_EFFECTIVE.getCode());
		List<Hc63> list= apiExamRoomArrangeMapper.getExamRoomPerponList(hc63);
		PageInfo<Hc63> pageinfo = new PageInfo<>(list);
		return pageinfo;
	}

	@Override
	public PageInfo<Hc63> getExamRoomPerponSelectList(Hc63 hc63) {
		PageHelper.offsetPage(hc63.getOffset(), hc63.getLimit());
		// ��ѯ��Ч��Ա����
		hc63.setAae100(COMAAE100Enum.DATA_EFFECTIVE.getCode());
		List<Hc63> list= apiExamRoomArrangeMapper.getExamRoomPerponSelectList(hc63);
		PageInfo<Hc63> pageinfo = new PageInfo<>(list);
		return pageinfo;
	}

	@Override
	public AjaxReturnMsg savePerponExamRoom(Hc63 hc63) {
		if (hc63 != null && StringUtils.isNotBlank(hc63.getSelectnodes()) && !",".equals(hc63.getSelectnodes())) {
			// ��ѯ��ǰ������Ϣ
			Hb77 hb77info = apiExamRoomArrangeMapper.getExamRoomInfo(hc63.getChb077(), COMAAE100Enum.DATA_EFFECTIVE.getCode());
			if (hb77info == null) {
				return this.error("��ȡ��ǰ������Ϣʧ��");
			}
			// ��ѯ��ǰ������λʹ�����
			Hb77 hb77number = new Hb77();
			hb77number.setChb077(hc63.getChb077());
			hb77number.setChb140(hc63.getChb140());
			hb77number.setAae100(COMAAE100Enum.DATA_EFFECTIVE.getCode());
			// ��ȡ���������б�������Ա��Ϣ
			List<String> chb330 = apiExamRoomArrangeMapper.getseatnumber(hb77number);
			// ��ȡ����λ����
			List<Integer> zuowei = seatNumberMake(chb330, hb77info.getChb166());
			String[] chc063Batch = null;
			if (hc63.getSelectnodes().contains(",")) {
			//ȥ�����һ�����ַ�
			hc63.setSelectnodes(hc63.getSelectnodes().substring(0, hc63.getSelectnodes().length() - 1));
			chc063Batch = hc63.getSelectnodes().split(",");
			} else {
				// ֻѡ��һ��ֵ
				chc063Batch = new String[]{hc63.getSelectnodes()};
			}
			// �����Ҫ���ŵ���λ������Ŀǰ���е���λ�� ��������� ֱ�ӷ���
			if (chc063Batch.length > zuowei.size()) {
				return this.error("�ѳ�����ǰ�������������� ʣ�����������" + zuowei.size() + "�ˣ���������λ����" + chc063Batch.length + "��" );
			}
			Map hc63map = new HashMap();
			List<Hc63> hc63Batch = new ArrayList();
			int jishu = 0;
			// ��������
			for (String chc063 : chc063Batch) {
				// ��������Ϣ
				Hc63 hc = new Hc63();
				hc.setChb330(String.valueOf(zuowei.get(jishu)));
				hc.setChc063(chc063);
				hc63Batch.add(hc);
				jishu ++;
			}
			hc63map.put("chb077", hc63.getChb077());
			hc63map.put("aae011", hc63.getAae011());
			hc63map.put("chb140", hc63.getChb140());
			hc63map.put("aae100", COMAAE100Enum.DATA_EFFECTIVE.getCode());
			hc63map.put("hc63Batch", hc63Batch);
			int result = apiExamRoomArrangeMapper.savePerponExamHb71Batch(hc63map);
			// ���¿�������״̬
			saveHb74Status(hc63.getChb140(), countHb74CHB341(hc63.getChb140()), COMAAE100Enum.DATA_EFFECTIVE.getCode(), null);
			return this.success("���뿼���ɹ�");
		}
		return this.error("��������,����");
	}

	@Override
	public AjaxReturnMsg deletePerponExamRoom(Hc63 hc63) {
		if (hc63 != null && StringUtils.isNotBlank(hc63.getSelectnodes()) && !",".equals(hc63.getSelectnodes())) {
			//ȥ�����һ�����ַ�
			hc63.setSelectnodes(hc63.getSelectnodes().substring(0, hc63.getSelectnodes().length() - 1));
			String[] chb077Batch = hc63.getSelectnodes().split(",");
			Map hc63map = new HashMap();
			List<Hb71> hc71Batch = new ArrayList();
			// ��������
			for (String chb071 : chb077Batch) {
				// ��������Ϣ
				Hb71 hc = new Hb71();
				hc.setChb071(chb071);
				hc71Batch.add(hc);
			}
			hc63map.put("examtype", hc63.getExamtype());
			// �����λ��
			hc63map.put("chb330", null);
			// ������ڿ���
			hc63map.put("chb060", null);
			hc63map.put("aae100", COMAAE100Enum.DATA_INVALID.getCode());
			hc63map.put("hc71Batch", hc71Batch);
			int result = apiExamRoomArrangeMapper.deletePerponExamHb71Batch(hc63map);
			// ���¿�������״̬
			saveHb74Status(hc63.getChb140(), countHb74CHB341(hc63.getChb140()), COMAAE100Enum.DATA_EFFECTIVE.getCode(), null);
			if (result > 0) {
				return this.success("�Ƴ������ɹ�");
			} else {
				return this.error("�Ƴ�����ʧ��");
			}
		}
		return this.error("��������,����");
	}


	@Override
	public AjaxReturnMsg getExamRoomBaseBatchInfo(String chb070) {
		if (StringUtils.isBlank(chb070)) {
			return this.error("�����������¼����");
		}
		Hb70 hb70 = apiExamRoomArrangeMapper.getExamRoomBaseBatchInfo(chb070, COMAAE100Enum.DATA_EFFECTIVE.getCode());
		if (hb70 != null) {
			hb70.setChb16a(hb70.getChb165().split("~")[1]);
			hb70.setChb165(hb70.getChb165().split("~")[0]);
		}
		return this.success(hb70);
	}

	@Override
	public AjaxReturnMsg getExamRoomBaseInfo(String chb077) {
		if (StringUtils.isBlank(chb077)) {
			return this.error("�����������¼����");
		}
		Hb77 hb77 = apiExamRoomArrangeMapper.getExamRoomBaseInfo(chb077, COMAAE100Enum.DATA_EFFECTIVE.getCode());
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
			int result = apiExamRoomArrangeMapper.deletePerponExamRoomBatch(hb77map);
			int result2 = apiExamRoomArrangeMapper.deleteExamRoomBatch(hb77map);
			return this.success("ɾ���ɹ�");
		}
		return this.error("��������,����");
	}

	@Override
	public AjaxReturnMsg saveExamRoomSubmit(Hb74 hb74) {
		// ������ύ����  ������״̬
		if (hb74 != null && HB74CHB341Enum.WAIT_AUDIT.getCode().equals(hb74.getChb341()) && StringUtils.isNotBlank(hb74.getChb140())) {
			// �޸���������״̬
			int result = saveHb74Status(hb74.getChb140(), HB74CHB341Enum.WAIT_AUDIT.getCode(), COMAAE100Enum.DATA_EFFECTIVE.getCode(), HB75CHB146Enum.EXAMINATION_ROOM_ARRANGEMENT.getCode());
			if (result == 1) {
				return this.success("�ύ�ɹ�");
			} else {
				return this.error("�ύʧ��");
			}
		}
		return this.error("��������,����");
	}

	@Override
	public AjaxReturnMsg getselectExamRoomPerponNum(String chb070, String chb140){
		if (StringUtils.isNotBlank(chb140)) {
			// ���ݿ������α�� �������� ��ѯͳ������
			return this.success(apiExamRoomArrangeMapper.getStatisPerponNumber(chb140, chb070, COMAAE100Enum.DATA_EFFECTIVE.getCode(), COMAAE100Enum.DATA_INVALID.getCode()));
		}
		return this.error("��������,����");
	}

	@Override
	public List<Hb76> getTestCenterList(String aab001) {
		return apiExamRoomArrangeMapper.getTestCenterList(aab001, COMAAE100Enum.DATA_EFFECTIVE.getCode());
	}

	@Override
	public List<Hb78> getExamRoomSelectList(String chb076) {
		return apiExamRoomArrangeMapper.getExamRoomSelectList(chb076, COMAAE100Enum.DATA_EFFECTIVE.getCode());
	}

	@Override
	public AjaxReturnMsg automaticExamArrange(Hb70 hb70) {
		if (StringUtil.isBlank(hb70.getChb070())) {
			return this.error("��������,����");
		}
		// ��ѯ����������Ϣ
		Hb70 hb = apiExamRoomArrangeMapper.getExamRoomBaseBatchInfo(hb70.getChb070(), COMAAE100Enum.DATA_EFFECTIVE.getCode());
		if (null == hb) {
			return this.error("δ�ܲ�ѯ����ǰ����������Ϣ");
		}
		// ��ѯ�ÿ������������п�����Ϣ
		List<Hb77> hb77list = apiExamRoomArrangeMapper.getExamRoomSpaceList(hb70.getChb070(), COMAAE100Enum.DATA_EFFECTIVE.getCode());
		if (hb77list.isEmpty()) {
			return this.error("δ�ܲ�ѯ���������ζ�Ӧ�Ŀ�����Ϣ");
		}
		// ��ѯ��ǰ���δ����俼������
		Hc63 hc63 = new Hc63();
		hc63.setAae100(COMAAE100Enum.DATA_EFFECTIVE.getCode());
		hc63.setChb070(hb70.getChb070());
		hc63.setChb140(hb.getChb140());
		List<Hc63> hc63list = apiExamRoomArrangeMapper.getExamRoomPerponSelectList(hc63);
		if (hc63list.isEmpty()) {
			return this.error("δ�ܲ�ѯ��δ����Ŀ��������鿼���Ƿ��Ѿ��������");
		}

		// �ܿ���������
		int spaceCount = 0;
		for (Hb77 h77 : hb77list) {
			// ���������
			h77.setAae011(hb70.getAae011());
			spaceCount += h77.getChb166();
		}
		BigDecimal spaceSum = new BigDecimal(spaceCount);
		// �ܴ���������
		BigDecimal perpleSum = new BigDecimal(hc63list.size());
		if (perpleSum.compareTo(spaceSum) > 0) {
			return this.error("�����������ڵ�ǰ���п����������������޷��Զ����ţ�����������");
		}
		// ���㿼�����ű����� 
		BigDecimal spacePadding = perpleSum.divide(spaceSum, 2, RoundingMode.HALF_UP);
		return automaticExamOperating(hc63list, hb77list, hb.getChb140(), spacePadding);
	}

	private AjaxReturnMsg automaticExamOperating(List<Hc63> hc63list, List<Hb77> hb77list, String chb140, BigDecimal spacePadding) {
		for (Hb77 hb77 : hb77list) {
			// ��ѯ��ǰ������λʹ�����
			Hb77 hb77number = new Hb77();
			hb77number.setChb077(hb77.getChb077());
			hb77number.setChb140(chb140);
			hb77number.setAae100(COMAAE100Enum.DATA_EFFECTIVE.getCode());
			// ��ȡ���������б�������Ա��Ϣ
			List<String> chb330 = apiExamRoomArrangeMapper.getseatnumber(hb77number);
			// ��ȡ����λ����
			List<Integer> zuowei = seatNumberMake(chb330, hb77.getChb166_sum());
			// ���ݱ����ʽ��з���
			BigDecimal perpleSum = new BigDecimal(hb77.getChb166());
			// �������뱣������
			perpleSum = perpleSum.multiply(spacePadding).setScale(2, BigDecimal.ROUND_DOWN);
			// ѭ����ȡ��������
			String selectnodes = "";
			for (int i = hc63list.size() - 1; i >= 0; i --) {
				if (null == hc63list.get(i)) {
					continue;
				}
				// �Ƚ��Ƿ��Ѿ��������Ͷȹ涨�ķ������
				if (perpleSum.subtract(new BigDecimal(1)).compareTo(new BigDecimal(0)) < 0 && hb77 != hb77list.get(hb77list.size() -1)) {
					// �ѳ��� ��ֹѭ��
					break;
				}
				// ƴ�ӿ�������
				selectnodes += hc63list.get(i).getChc063() + ",";
				// ������ӵĿ����Ӽ������Ƴ�
				hc63list.remove(i);
				perpleSum = perpleSum.subtract(new BigDecimal(1));
			}

			if (StringUtils.isBlank(selectnodes)) {
				continue;
			}
			String[] chc063Batch = null;
			//ȥ�����һ�����ַ�
			selectnodes = selectnodes.substring(0, selectnodes.length() - 1);
			if (selectnodes.contains(",")) {
				chc063Batch = selectnodes.split(",");
			} else {
				// ֻѡ��һ��ֵ
				chc063Batch = new String[]{selectnodes};
			}
			// �����Ҫ���ŵ���λ������Ŀǰ���е���λ�� ��������� ֱ�ӷ���
			if (chc063Batch.length > zuowei.size()) {
				return this.error("�ѳ�����ǰ�������������� ʣ�����������" + zuowei.size() + "�ˣ���������λ����" + chc063Batch.length + "��");
			}
			Map hc63map = new HashMap();
			List<Hc63> hc63Batch = new ArrayList();
			int jishu = 0;
			// ��������
			for (String chc063 : chc063Batch) {
				// ��������Ϣ
				Hc63 hc = new Hc63();
				hc.setChb330(String.valueOf(zuowei.get(jishu)));
				hc.setChc063(chc063);
				hc63Batch.add(hc);
				jishu ++;
			}
			hc63map.put("chb077", hb77.getChb077());
			hc63map.put("aae011", hb77.getAae011());
			hc63map.put("chb140", chb140);
			hc63map.put("aae100", COMAAE100Enum.DATA_EFFECTIVE.getCode());
			hc63map.put("hc63Batch", hc63Batch);
			int result = apiExamRoomArrangeMapper.savePerponExamHb71Batch(hc63map);
		}
		// ���¿�������״̬
		saveHb74Status(chb140, countHb74CHB341(chb140), COMAAE100Enum.DATA_EFFECTIVE.getCode(), null);
		return this.success("���뿼���ɹ�");
	}

	/**
	 * ��ѯ��ǰ�������Ѱ��ſ������͵�����
	* @author: liangy  
	* @date 2018��12��29��
	* @param @param chb140
	* @param @param examtype
	* @param @return    
	* @return int   
	* @throws
	 */
	private int selectExamRoomPerponNum(String chb140, String examtype) {
		return apiExamRoomArrangeMapper.getcountHb74CHB341(chb140, examtype, COMAAE100Enum.DATA_EFFECTIVE.getCode());
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
		} else {
			// �ÿ���û�б������ ������λ�Ŷ���ʹ��
			for (int i = 1; i <= num; i ++) {
				result.add(i);
			}
		}
		return result;
	}

	private String countHb74CHB341(String chb140){
		int count = selectExamRoomPerponNum(chb140, HB77EXAMTYPEEnum.THEORY_EXAM.getCode());
		if (count == 0) {
			return HB74CHB341Enum.WAIT_ARRANGE.getCode();
		}
		return HB74CHB341Enum.COMPLETE_ARRANGE.getCode();
		// �ѷ� �����Ƿ���ϲ��ٸ�������
		// ��ȡ���ۿ����Ƿ�������
//		int lilun = selectExamRoomPerponNum(chb140, HB77EXAMTYPEEnum.THEORY_EXAM.getCode());
//		// ��ȡ ʵ�ٿ����Ƿ�������
//		int shicao = selectExamRoomPerponNum(chb140, HB77EXAMTYPEEnum.OPERATING_EXAM.getCode());
//		// ��ȡ�ۺϿ����Ƿ�������
//		int zonghe = selectExamRoomPerponNum(chb140, HB77EXAMTYPEEnum.COMPLEX_EXAM.getCode());
//		if (lilun == 0 && shicao == 0 && zonghe == 0) {
//			// ȫ������
//			return HB74CHB341Enum.COMPLETE_ARRANGE.getCode();
//		}
//		if (lilun == 0 && shicao == 0 && zonghe > 0) {
//			// ���۲�������
//			return HB74CHB341Enum.THEORY_OPERATING_ARRANGE.getCode();
//		}
//		if (lilun == 0 && shicao > 0 && zonghe == 0) {
//			// �����ۺϱ���
//			return HB74CHB341Enum.THEORY_COMPLEX_ARRANGE.getCode();
//		}
//		if (lilun > 0 && shicao == 0 && zonghe == 0) {
//			// �����ۺϱ���
//			return HB74CHB341Enum.OPERATING_COMPLEX_ARRANGE.getCode();
//		}
//		if (lilun == 0 && shicao > 0 && zonghe > 0) {
//			// ���۱���
//			return HB74CHB341Enum.THEORY_ARRANGE.getCode();
//		}
//		if (lilun > 0 && shicao == 0 && zonghe > 0) {
//			// ��������
//			return HB74CHB341Enum.OPERATING_ARRANGE.getCode();
//		}
//		if (lilun > 0 && shicao > 0 && zonghe == 0) {
//			// �ۺϱ���
//			return HB74CHB341Enum.COMPLEX_ARRANGE.getCode();
//		}
//		// δ����
//		return HB74CHB341Enum.WAIT_ARRANGE.getCode();
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
	private int saveHb74Status(String chb140, String chb341, String aae100, String chb146) {
		// ���¿�������״̬
		return apiExamRoomArrangeMapper.saveHb74Chb341(chb140, chb341, aae100, chb146);
	}

	@Override
	public AjaxReturnMsg deleteExaminationBatch(Hb70 hb70) {
		if (hb70 != null && StringUtils.isNotBlank(hb70.getSelectnodes()) && !",".equals(hb70.getSelectnodes())) {
			//ȥ�����һ�����ַ�
			hb70.setSelectnodes(hb70.getSelectnodes().substring(0, hb70.getSelectnodes().length() - 1));
		}
		Map hb70map = new HashMap();
		String[] hb70String = new String[]{hb70.getSelectnodes()};
		if (hb70.getSelectnodes().contains(",")) {
			hb70String = hb70.getSelectnodes().split(",");
		}
		hb70map.put("hb70String", hb70String);
		hb70map.put("aae100", COMAAE100Enum.DATA_INVALID.getCode());
		hb70map.put("aae011", hb70.getAae011());
		// ɾ��������Ϣ
		int i = apiExamRoomArrangeMapper.deleteExaminationBatch(hb70map);
		// ɾ�����Լ�¼��Ϣ
		apiExamRoomArrangeMapper.deleteExaminationRecord(hb70map);
		// ɾ����Ա�ο���Ϣ
		// ���ݿ�������ɾ��
		hb70map.put("type", "EXAM_BATCH");
		apiExamRoomArrangeMapper.deleteExaminationPerpon(hb70map);
		if (i > 0) {
			return this.success("ɾ���ɹ�");
		}
		return this.error("ɾ��ʧ��");
	}

}
