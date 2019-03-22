package com.insigma.mvc.serviceimp.appraisal.JDYW_002_009;

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
import com.insigma.enums.HB74CHB326Enum;
import com.insigma.enums.HB74CHB334Enum;
import com.insigma.enums.HB75CHB126Enum;
import com.insigma.enums.HB75CHB146Enum;
import com.insigma.enums.HB75CHB312Enum;
import com.insigma.enums.HC60CZC018Enum;
import com.insigma.enums.HC66CHC018Enum;
import com.insigma.mvc.MvcHelper;
import com.insigma.mvc.dao.appraisal.JDYW_002_009.ApiAppraisalStationDeclareMapper;
import com.insigma.mvc.model.CodeValue;
import com.insigma.mvc.model.appraisal.Hb74;
import com.insigma.mvc.model.appraisal.Hb74Temp_Short;
import com.insigma.mvc.model.appraisal.Hb75;
import com.insigma.mvc.model.appraisal.Hb75Temp;
import com.insigma.mvc.model.appraisal.Hc60Temp_Short;
import com.insigma.mvc.service.appraisal.JDYW_002_009.ApiAppraisalStationDeclareService;
import com.insigma.resolver.AppException;

@Service
@Transactional(rollbackFor=Exception.class)
public class ApiAppraisalStationDeclareServiceImpl extends MvcHelper implements ApiAppraisalStationDeclareService{

	@Resource
	private ApiAppraisalStationDeclareMapper appraisalStationDeclareMapper;

	//��ȡ��Ӧ�����ļ����걨����
	@Override
	public PageInfo<Hb74Temp_Short> getAppraisalSpeciaList(Hb74Temp_Short hb74Temp_Short) {
		PageHelper.offsetPage(hb74Temp_Short.getOffset(), hb74Temp_Short.getLimit());
		// ��ѯ������˽׶�����(�������������ͨ������)[��ʱ���� ��ʾȫ�� ������Ҫ���Լ���]
//		List<String> chb146list = new ArrayList();
//		chb146list.add(HB75CHB146Enum.APPRAISAL_DECLARE.getCode());
//		chb146list.add(HB75CHB146Enum.IDENTIFICATION_CENTER_NOT_PASS.getCode());
//		hb74Temp_Short.setChb146list(chb146list);
		List<CodeValue> chb146Code = new ArrayList();
		CodeValue codeValue = new CodeValue();
		codeValue.setCode_value("3");// ���� ����
		codeValue.setCode_name(HB75CHB146Enum.APPRAISAL_DECLARE.getCode());
		chb146Code.add(codeValue);
		codeValue = new CodeValue();
		codeValue.setCode_value("2");// ���� ����
		codeValue.setCode_name(HB75CHB146Enum.APPRAISAL_REVIEW_NOT_PASS.getCode());
		chb146Code.add(codeValue);
		codeValue = new CodeValue();
		codeValue.setCode_value("1");// ���� ����
		codeValue.setCode_name(HB75CHB146Enum.APPRAISAL_REVIEW_PASS.getCode());
		chb146Code.add(codeValue);
		// ������������
		hb74Temp_Short.setChb146Code(chb146Code);
		// ��ѯ��Ч����
		hb74Temp_Short.setAae100(COMAAE100Enum.DATA_EFFECTIVE.getCode());
		List<Hb74Temp_Short> list= appraisalStationDeclareMapper.getAppraisalSpeciaList(hb74Temp_Short);
		PageInfo<Hb74Temp_Short> pageinfo = new PageInfo<>(list);
		return pageinfo;
	}

	@Override
	public AjaxReturnMsg getAppraisalInfo(String chb140) {
		if (StringUtils.isBlank(chb140)) {
			return this.error("�����������¼����");
		}
		return this.success(appraisalStationDeclareMapper.getAppraisalInfo(chb140));
	}

	@Override
	public AjaxReturnMsg savePerponAudit(Hb75Temp hb75Temp, String operation) {
		// �������ʱУ��������ͺϷ���
		if ("in".equals(operation) && HB75CHB126Enum.fromCode(hb75Temp.getChb126()) == null) {
			return this.error("�������Ͳ������Ϸ�");
		}
		if (StringUtils.isBlank(hb75Temp.getSelectnodes())) {
			return this.error("������ѡ��һ�����ν��в���");
		}
		// �жϼ������κ��Ƿ�Ϊ��  Ϊ���ǵ�һ����Ա���� 74����������Ӧ����
		Hb74 hb74 = null;
		if (StringUtils.isEmpty(hb75Temp.getChb140())) {
			// ����74����Ϣ
			hb74 = new Hb74();
			// �������к�����
			// ��ȡ���� ƴ�����α��
			hb74.setAae100(COMAAE100Enum.DATA_EFFECTIVE.getCode());
//			Integer date = Integer.parseInt(DateUtil.dateToCompactString(new Date()).substring(2, 4)) * 100000;
			hb74.setChb140(appraisalStationDeclareMapper.getSQ_HB74_CHB140());
			// ��״̬���� ����Ϊ������վ�걨״̬
			hb74.setChb146(HB75CHB146Enum.APPRAISAL_DECLARE.getCode());
			// �ύ״̬��Ϊδ�ύ״̬
			hb74.setChb326(HB74CHB326Enum.UNSUBMITTED.getCode());
			// ��������Ϣ¼��
			hb74.setAae011(hb75Temp.getAae011());
			// �������ʹ��� Ĭ��Ϊ��һ���μ������� ���һ�������Ƴ��� �ü������ͼǵ����
			hb74.setChb126(hb75Temp.getChb126());
			// ������λ����
			hb74.setAab001(hb75Temp.getAab001());
			// ��ʼ�����ƻ���������
			int result = appraisalStationDeclareMapper.insertAppraisalPlan(hb74);
			if (result != 1) {
				return this.error("�����ƻ�����ʧ��,������");
			}
			// ���������κ�
			hb75Temp.setChb140(hb74.getChb140());
		}

		// У������������ݡ��������Ρ����μ������Ͳ����Ϸ���(����Ǹ���������,����У��)
		if (hb74 == null) {
			hb74 = appraisalStationDeclareMapper.getcheckAppraisalPlan(hb75Temp.getChb140(), COMAAE100Enum.DATA_EFFECTIVE.getCode());
			if (hb74 == null) {
				return this.error("δ���ҵ��ü����ƻ�������üƻ��Ƿ��Ѿ���ɾ��");
			}
			if (StringUtils.isBlank(hb75Temp.getAab001()) || !hb75Temp.getAab001().equals(hb74.getAab001())) {
				return this.error("���Ըü����ƻ�û�в���Ȩ�ޣ����¼����");
			}
			if ("in".equals(operation) && !hb75Temp.getChb126().equals(hb74.getChb126())) {
				return this.error("����ƻ��ļ���������ƻ��涨�ļ������Ͳ�һ��");
			}
		}
		
		// ���¼ƻ��е�������Ϣ
		//ȥ�����һ�����ַ�
		hb75Temp.setSelectnodes(hb75Temp.getSelectnodes().substring(0, hb75Temp.getSelectnodes().length() - 1));
		String[] chb120Batch = hb75Temp.getSelectnodes().split(",");
		// ��������
		Map<String, Object> hb75map = new HashMap();
		List<Hb75> hb75list = new ArrayList();
		for (String chb120 : chb120Batch) {
			// ��������Ϣ
			Hb75 hb = new Hb75();
			hb.setChb120(chb120);
			hb75list.add(hb);
		}
		String chb140 = "";
		String chb146 = null;
		// ����Ǽ���ƻ� ���chb140��ֵ
		if ("in".equals(operation)) {
			chb140 = hb75Temp.getChb140();
			// ������״̬�޸�Ϊ"������վ�걨"
			chb146 = HB75CHB146Enum.APPRAISAL_DECLARE.getCode();
		} else {
			// �Ƴ���״̬�޸�Ϊ"������վ���ͨ��"
			chb146 = HB75CHB146Enum.APPRAISAL_REVIEW_PASS.getCode();
		}
		hb75map.put("chb312", HB75CHB312Enum.AUDIT_PASS.getCode());
		hb75map.put("chb140", chb140);
		hb75map.put("chb146", chb146);
		hb75map.put("aae100", COMAAE100Enum.DATA_EFFECTIVE.getCode());
		hb75map.put("hb75list", hb75list);
		//�޸�75�е�chb140�ֶ�
		int result2 = appraisalStationDeclareMapper.saveAppraisBatchHb74(hb75map);
		//�޸�63������chb140�ֶ�
		int result3 = appraisalStationDeclareMapper.saveAppraisBatchHc63(hb75map);
		// �������μ�������
		int result4 = appraisalStationDeclareMapper.savePerponSum(hb74.getChb140(), COMAAE100Enum.DATA_EFFECTIVE.getCode());
		if (result2 > 0 && result3 > 0) {
			return this.success("�ƻ����³ɹ�", hb74.getChb140());
		}
		return this.error("�ƻ�����ʧ��");
	}

	@Override
	public PageInfo<Hb75Temp> getAppraisalDeclareist(Hb75Temp hb75Temp) {
		PageHelper.offsetPage(hb75Temp.getOffset(), hb75Temp.getLimit());
		// ��ѯ��Ч����
		hb75Temp.setAae100(COMAAE100Enum.DATA_EFFECTIVE.getCode());
		List<Hb75Temp> list= appraisalStationDeclareMapper.getAppraisalDeclareist(hb75Temp);
		PageInfo<Hb75Temp> pageinfo = new PageInfo<>(list);
		return pageinfo;
	}

	@Override
	public PageInfo<Hc60Temp_Short> getAppraisThisStudentList(
			Hc60Temp_Short hc60Temp_Short) {
		// ��ܺϸ�
		hc60Temp_Short.setCzc018(HC60CZC018Enum.QUALIFIED.getCode());
		// ��ҵ���Ժϸ�
		hc60Temp_Short.setChc018(HC66CHC018Enum.QUALIFIED.getCode());
		// ��ѯ��Ч����
		hc60Temp_Short.setAae100((COMAAE100Enum.DATA_EFFECTIVE.getCode()));
		List<Hc60Temp_Short> list= appraisalStationDeclareMapper.getAppraisThisStudentList(hc60Temp_Short);
		PageInfo<Hc60Temp_Short> pageinfo = new PageInfo<>(list);
		return pageinfo;
	}

	@Override
	public AjaxReturnMsg savePerponSubmit(Hb74 hb74) {
		// У���ύ״̬
		if (HB74CHB326Enum.fromCode(hb74.getChb326()) == null) {
			return this.error("�ύ״̬�������Ϸ�");
		}
		try {
			// ʱ������ת��
			if (StringUtils.isNotBlank(hb74.getChb400_String())) {
				hb74.setChb400(DateUtil.stringToDate(hb74.getChb400_String(), "yyyy-MM-dd"));
			}
			if (StringUtils.isNotBlank(hb74.getChb403_String())) {
				hb74.setChb403(DateUtil.stringToDate(hb74.getChb403_String(), "yyyy-MM-dd"));
			}
			if (StringUtils.isNotBlank(hb74.getChb406_String())) {
				hb74.setChb406(DateUtil.stringToDate(hb74.getChb406_String(), "yyyy-MM-dd"));
			}
		} catch (AppException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String message = "����ɹ�";
		// �ж��ύ״̬ (����������ύ����)
		if (HB74CHB326Enum.SUBMITTED.getCode().equals(hb74.getChb326())) {
			// ״̬��Ϊ������վ�ύ״̬
			hb74.setChb146(HB75CHB146Enum.APPRAISAL_DECLARE.getCode());
			hb74.setChb334(HB74CHB334Enum.WAIT_AUDIT.getCode());
			message = "�ύ�ɹ�";
		}
		hb74.setAae100(COMAAE100Enum.DATA_EFFECTIVE.getCode());
		int result = appraisalStationDeclareMapper.savePerponSubmit(hb74);
		if (result == 1) {
			return this.success(message);
		} else {
			return this.error("����ʧ�ܣ����ݿ����Ѿ���ɾ��������");
		}
	}

	@Override
	public AjaxReturnMsg delAppraisalDeclare(Hb74 hb74) {
		// У������������κ��Ƿ�Ϊ��
		if (StringUtils.isBlank(hb74.getChb140())) {
			return this.error("��������������");
		}
		// ��������
		Map<String, Object> hb74map = new HashMap();
		List<Hb74> hb74list = new ArrayList();
		// 75������״̬��ԭ
		hb74list.add(hb74);
		hb74map.put("hb74list", hb74list);
		hb74map.put("chb146", HB75CHB146Enum.APPRAISAL_REVIEW_PASS.getCode());
		//�޸�75�е�chb140�ֶ�
		int result = appraisalStationDeclareMapper.delAppraisAllEmptyHb74(hb74map);
		//�޸�63������chb140�ֶ�
		int result1 = appraisalStationDeclareMapper.delAppraisAllEmptyHc63(hb74map);
		// ������Ϊ��Ч
		hb74.setAae100(COMAAE100Enum.DATA_INVALID.getCode());
		// �߼�ɾ����Ӧ��������
		int result2 = appraisalStationDeclareMapper.delAppraisalDeclare(hb74);
		if (result2 != 1) {
			return this.error("������������ɾ��ʧ��");
		}
		return this.success("ɾ���ɹ�");
	}

	@Override
	public AjaxReturnMsg delAppraisalDeclareBatch(Hb74 hb74) {
		// У������������κ��Ƿ�Ϊ��
		if (StringUtils.isBlank(hb74.getSelectnodes())) {
			return this.error("��������������");
		}
		// ��������
		Map<String, Object> hb74map = new HashMap();
		List<Hb74> hb74list = new ArrayList();
		// 75������״̬��ԭ
		//ȥ�����һ�����ַ�
		hb74.setSelectnodes(hb74.getSelectnodes().substring(0, hb74.getSelectnodes().length() - 1));
		String[] chb140Batch = hb74.getSelectnodes().split(",");
		// ��������
		for (String chb140 : chb140Batch) {
			// ��������Ϣ
			Hb74 hb = new Hb74();
			hb.setChb140(chb140);
			hb74list.add(hb);
		}
		hb74list.add(hb74);
		hb74map.put("hb74list", hb74list);
		hb74map.put("chb146", HB75CHB146Enum.APPRAISAL_REVIEW_PASS.getCode());
		//�޸�75�е�chb140�ֶ�
		int result = appraisalStationDeclareMapper.delAppraisAllEmptyHb74(hb74map);
		//�޸�63������chb140�ֶ�
		int result1 = appraisalStationDeclareMapper.delAppraisAllEmptyHc63(hb74map);
		// ������Ϊ��Ч
		hb74.setAae100(COMAAE100Enum.DATA_INVALID.getCode());
		// �߼�ɾ����Ӧ��������
		int result2 = appraisalStationDeclareMapper.delAppraisalDeclareBatch(hb74map);
		if (result2 == chb140Batch.length) {
			return this.success("ɾ���ɹ�");
		}
		return this.error("������������ɾ��ʧ��");
	}

}
