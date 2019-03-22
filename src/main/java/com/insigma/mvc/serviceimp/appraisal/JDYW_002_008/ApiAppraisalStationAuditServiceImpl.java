package com.insigma.mvc.serviceimp.appraisal.JDYW_002_008;

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
import com.insigma.dto.AjaxReturnMsg;
import com.insigma.enums.COMAAE100Enum;
import com.insigma.enums.HB75CHB146Enum;
import com.insigma.enums.HB75CHB312Enum;
import com.insigma.enums.HC60CHB256Enum;
import com.insigma.enums.HC60CZC018Enum;
import com.insigma.enums.HC66CHC018Enum;
import com.insigma.mvc.MvcHelper;
import com.insigma.mvc.dao.appraisal.JDYW_002_008.ApiAppraisalStationAuditMapper;
import com.insigma.mvc.model.CodeValue;
import com.insigma.mvc.model.Hc63;
import com.insigma.mvc.model.appraisal.Hb75;
import com.insigma.mvc.model.appraisal.Hb75Temp;
import com.insigma.mvc.model.appraisal.Hc60Temp_Short;
import com.insigma.mvc.model.appraisal.Hc63Temp_Short;
import com.insigma.mvc.model.train.Hc60;
import com.insigma.mvc.service.appraisal.JDYW_002_008.ApiAppraisalStationAuditService;

@Service
@Transactional(rollbackFor=Exception.class)
public class ApiAppraisalStationAuditServiceImpl extends MvcHelper implements ApiAppraisalStationAuditService{

	@Resource
	private ApiAppraisalStationAuditMapper appraisalStationAuditMapper;

	//��ȡ��Ӧ�����ļ����걨����
	@Override
	public PageInfo<Hb75Temp> getAppraisalSpeciaList(Hb75Temp Hb75Temp) {
		PageHelper.offsetPage(Hb75Temp.getOffset(), Hb75Temp.getLimit());
		// ���������ͨ������ ��������
		Hb75Temp.setChb256(HC60CHB256Enum.APPRAISAL_PASS.getCode());
		// ��ѯ��Ч����
		Hb75Temp.setAae100(COMAAE100Enum.DATA_EFFECTIVE.getCode());
		// ��ѯ�����������ύ����
		Hb75Temp.setChb326(HB75CHB146Enum.SUBMITTED.getCode());
		List<Hb75Temp> list= appraisalStationAuditMapper.getAppraisalSpeciaList(Hb75Temp);
		PageInfo<Hb75Temp> pageinfo = new PageInfo<>(list);
		return pageinfo;
		
	}

	@Override
	public AjaxReturnMsg<List<CodeValue>> getTrainComName(CodeValue codevalue) {
		List<CodeValue> list = appraisalStationAuditMapper.getTrainComName(codevalue);
		return this.success(list);
	}
	
	@Override
	public AjaxReturnMsg getAppraisalInfo(String chb120) {
		return this.success(appraisalStationAuditMapper.getAppraisalInfo(chb120));
	}

	@Override
	public PageInfo<Hc60Temp_Short> getAppraisStudentNotSubmitList(
			Hc60Temp_Short hc60Temp_Short) {
		// ��ѯ��Ч����
		hc60Temp_Short.setAae100((COMAAE100Enum.DATA_EFFECTIVE.getCode()));
		// ��ܺϸ�
		hc60Temp_Short.setCzc018(HC60CZC018Enum.QUALIFIED.getCode());
		// ��ҵ���Ժϸ�
		hc60Temp_Short.setChc018(HC66CHC018Enum.QUALIFIED.getCode());
		List<Hc60Temp_Short> list= appraisalStationAuditMapper.getAppraisStudentNotSubmitList(hc60Temp_Short);
		PageInfo<Hc60Temp_Short> pageinfo = new PageInfo<>(list);
		return pageinfo;
	}

	@Override
	public PageInfo<Hc60Temp_Short> getAppraisThisStudentList(
			Hc60Temp_Short hc60Temp_Short) {
		// ��ѯ��Ч����
		hc60Temp_Short.setAae100((COMAAE100Enum.DATA_EFFECTIVE.getCode()));
		// ��ܺϸ�
		hc60Temp_Short.setCzc018(HC60CZC018Enum.QUALIFIED.getCode());
		// ��ҵ���Ժϸ�
		hc60Temp_Short.setChc018(HC66CHC018Enum.QUALIFIED.getCode());
		List<Hc60Temp_Short> list= appraisalStationAuditMapper.getAppraisThisStudentList(hc60Temp_Short);
		PageInfo<Hc60Temp_Short> pageinfo = new PageInfo<>(list);
		return pageinfo;
	}

	@Override
	public AjaxReturnMsg getPerponAuditInfo(String chc063) {
		if (StringUtils.isNotBlank(chc063)) {
			return this.success(appraisalStationAuditMapper.getPerponAuditInfo(chc063));
		}
		return this.error("��������,����");
	}


	@Override
	public AjaxReturnMsg savePerponAudit(Hc63Temp_Short hc63Temp_Short) {
		// У�����״̬�����Ϸ���
		if (HB75CHB312Enum.fromCode(hc63Temp_Short.getChb312()) == null) {
			return this.error("���״̬�������Ϸ�,����");
		}
		// �����˽����ͨ�� ����������Ϊ��
		if (HB75CHB312Enum.AUDIT_NOTPASS.getCode().equals(hc63Temp_Short.getChb312()) && StringUtils.isBlank(hc63Temp_Short.getChb314())) {
			return this.error("��˽��Ϊ��ͨ��ʱ������������Ϊ��");
		}
		// �޸������¼״̬
		hc63Temp_Short.setAae100(COMAAE100Enum.DATA_EFFECTIVE.getCode());
		int result = appraisalStationAuditMapper.savePerponAudit(hc63Temp_Short);
		// ������״̬Ϊ��ͨ�� ���޸�ѧԱ��¼״̬
		int result2 = 1;
		if (HB75CHB312Enum.AUDIT_NOTPASS.getCode().equals(hc63Temp_Short.getChb312()) && StringUtils.isNotBlank(hc63Temp_Short.getChc001())) {
			Hc60 hc60 = new Hc60();
			List<Hc60> hc60list = new ArrayList();
			hc60list.add(hc60);
			hc60.setChc001(hc63Temp_Short.getChc001());
			Map<String, Object> hc60Map = new HashMap();
			hc60Map.put("hc60list", hc60list);
			hc60Map.put("chb256", HC60CHB256Enum.NOT_APPRAISAL.getCode());
			hc60Map.put("aae100", COMAAE100Enum.DATA_EFFECTIVE.getCode());
			result2 = appraisalStationAuditMapper.savePerponInfoAuditBath(hc60Map);
		}
		if (result == 1 && result2 == 1) {
			return this.success("��˳ɹ�");
		} else {
			return this.error("���ʧ��,�����ݿ����Ѿ���ɾ��,����");
		}
	}


	@Override
	public AjaxReturnMsg getPerponAuditIsPass(String chb120) {
		if (StringUtils.isNotBlank(chb120)) {
			return this.success(appraisalStationAuditMapper.getPerponAuditIsPass(chb120, HB75CHB312Enum.AUDIT_NOTPASS.getCode(), COMAAE100Enum.DATA_EFFECTIVE.getCode()));
		}
		return this.error("��������,����");
	}


	@Override
	public AjaxReturnMsg savePerponAuditAll(Hb75 hb75) {
		// У�����״̬�����Ϸ���
		if (HB75CHB312Enum.fromCode(hb75.getChb312()) == null) {
			return this.error("���״̬�������Ϸ�,����");
		}
		// �����˽����ͨ�� ����������Ϊ��
		if (HB75CHB312Enum.AUDIT_NOTPASS.getCode().equals(hb75.getChb312()) && StringUtils.isEmpty(hb75.getChb314())) {
			return this.error("��˽��Ϊ��ͨ��ʱ������������Ϊ��");
		}
		// �޸ı������е�����ѧԱ�걨��Ϣ
		Hc63Temp_Short hc63Temp_Short = new Hc63Temp_Short();
		hc63Temp_Short.setChb120(hb75.getChb120());
		hc63Temp_Short.setChb312(hb75.getChb312());
		hc63Temp_Short.setChb314(hb75.getChb314());
		hc63Temp_Short.setChb327(hb75.getChb327());
		hc63Temp_Short.setAae100(COMAAE100Enum.DATA_EFFECTIVE.getCode());
		int result = appraisalStationAuditMapper.savePerponAudit(hc63Temp_Short);
		if (result < 1) {
			return this.error("������Ա�걨����״̬�޸�ʧ��");
		}
		// �޸ı������е�����ѧԱ��ѧԱ��Ϣ(��ͨ��ʱ�޸�)
		if (HB75CHB312Enum.AUDIT_NOTPASS.getCode().equals(hb75.getChb312())) {
			Hc63 hc63 = new Hc63();
			hc63.setAae011(HC60CHB256Enum.APPRAISAL_NOT_PASS.getCode());
			hc63.setAae100(COMAAE100Enum.DATA_EFFECTIVE.getCode());
			hc63.setChb120(hb75.getChb120());
			int result2 = appraisalStationAuditMapper.saveStudentBatch(hc63);
			if (result2 < 1) {
				return this.error("������Ա��Ϣ״̬�޸�ʧ��");
			}
		}
		// �޸�����״̬
		// δͨ���޸��ύ״̬Ϊ"δ�ύ"
		if (HB75CHB312Enum.AUDIT_NOTPASS.getCode().equals(hb75.getChb312())) {
			hb75.setChb326(HB75CHB146Enum.NOT_SUBMIT.getCode());
			hb75.setChb146(HB75CHB146Enum.APPRAISAL_REVIEW_NOT_PASS.getCode());
		} else {
			// ��״̬����
			hb75.setChb146(HB75CHB146Enum.APPRAISAL_REVIEW_PASS.getCode());
		}
		int result3 = appraisalStationAuditMapper.saveAppraisBatch(hb75);
		if (result3 != 1) {
			return this.error("������Ϣ�޸�ʧ��");
		}
		return this.success("������˳ɹ�");
	}
	
}
