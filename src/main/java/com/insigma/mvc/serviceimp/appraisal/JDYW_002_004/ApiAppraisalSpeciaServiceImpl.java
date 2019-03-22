package com.insigma.mvc.serviceimp.appraisal.JDYW_002_004;

import java.util.ArrayList;
import java.util.Date;
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
import com.insigma.enums.HB75CHB126Enum;
import com.insigma.enums.HB75CHB144Enum;
import com.insigma.enums.HB75CHB146Enum;
import com.insigma.enums.HB75CHB312Enum;
import com.insigma.enums.HC60CHB256Enum;
import com.insigma.enums.HC60CZC018Enum;
import com.insigma.enums.HC66CHC018Enum;
import com.insigma.mvc.MvcHelper;
import com.insigma.mvc.dao.appraisal.JDYW_002_004.ApiAppraisalSpeciaMapper;
import com.insigma.mvc.model.Hc63;
import com.insigma.mvc.model.appraisal.Hb75;
import com.insigma.mvc.model.appraisal.Hb75Temp;
import com.insigma.mvc.model.appraisal.Hc60Dto_Short;
import com.insigma.mvc.model.appraisal.Hc60Temp_Short;
import com.insigma.mvc.model.appraisal.Hc68Temp_Short;
import com.insigma.mvc.service.appraisal.JDYW_002_004.ApiAppraisalSpeciaService;
import com.insigma.resolver.AppException;

@Service
@Transactional(rollbackFor=Exception.class)
public class ApiAppraisalSpeciaServiceImpl extends MvcHelper implements ApiAppraisalSpeciaService{

	@Resource
	private ApiAppraisalSpeciaMapper apiAppraisalSpeciaMapper;

	//��ȡ��Ӧ�����ļ����걨����
	@Override
	public PageInfo<Hb75Temp> getAppraisalSpeciaList(Hb75Temp Hb75Temp) {
		PageHelper.offsetPage(Hb75Temp.getOffset(), Hb75Temp.getLimit());
		// ���������ͨ������ ��������
		Hb75Temp.setChb256(HC60CHB256Enum.APPRAISAL_PASS.getCode());
		// ��ѯ��Ч����
		Hb75Temp.setAae100(COMAAE100Enum.DATA_EFFECTIVE.getCode());
		// ��ѯר�����������걨����
		Hb75Temp.setChb126(HB75CHB126Enum.SPECIAL_APPRAISAL.getCode());
		List<Hb75Temp> list= apiAppraisalSpeciaMapper.getAppraisalSpeciaList(Hb75Temp);
		PageInfo<Hb75Temp> pageinfo = new PageInfo<>(list);
		return pageinfo;
		
	}


	@Override
	public AjaxReturnMsg getAppraisalInfo(String chb120) {
		return this.success(apiAppraisalSpeciaMapper.getAppraisalInfo(chb120));
	}

	@Override
	public PageInfo<Hc60Temp_Short> getAppraisStudentSubmitList(
			Hc60Dto_Short hc60Dto_Short) {
		PageHelper.offsetPage(hc60Dto_Short.getOffset(), hc60Dto_Short.getLimit());
		List<Hc60Temp_Short> list= apiAppraisalSpeciaMapper.getAppraisStudentSubmitList(hc60Dto_Short);
		PageInfo<Hc60Temp_Short> pageinfo = new PageInfo<>(list);
		return pageinfo;
	}

	@Override
	public PageInfo<Hc60Temp_Short> getAppraisStudentNotSubmitList(
			Hc60Temp_Short hc60Temp_Short) {
		// ��ѯ��ܺϸ�ѧԱ
		hc60Temp_Short.setCzc018(HC60CZC018Enum.QUALIFIED.getCode());
		// ��ѯ�ѽ�ҵѧԱ
		hc60Temp_Short.setChc018(HC66CHC018Enum.QUALIFIED.getCode());
		// ��ѯ��Ч����
		hc60Temp_Short.setAae100((COMAAE100Enum.DATA_EFFECTIVE.getCode()));
		List<Hc60Temp_Short> list= apiAppraisalSpeciaMapper.getAppraisStudentNotSubmitList(hc60Temp_Short);
		PageInfo<Hc60Temp_Short> pageinfo = new PageInfo<>(list);
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
		List<Hc60Temp_Short> list= apiAppraisalSpeciaMapper.getAppraisThisStudentList(hc60Temp_Short);
		PageInfo<Hc60Temp_Short> pageinfo = new PageInfo<>(list);
		return pageinfo;
	}

	@Override
	public PageInfo<Hc68Temp_Short> getAppraisClassList(Hc68Temp_Short hc68Temp_Short) {
		PageHelper.offsetPage(hc68Temp_Short.getOffset(), hc68Temp_Short.getLimit());
		// ��ѯ��Ч����
		hc68Temp_Short.setAae100((COMAAE100Enum.DATA_EFFECTIVE.getCode()));
		List<Hc68Temp_Short> list= apiAppraisalSpeciaMapper.getAppraisClassList(hc68Temp_Short);
		PageInfo<Hc68Temp_Short> pageinfo = new PageInfo<>(list);
		return pageinfo;
	}

	public PageInfo<Hc68Temp_Short> getAppraisClassListForLook(Hc68Temp_Short hc68Temp_Short) {
		PageHelper.offsetPage(hc68Temp_Short.getOffset(), hc68Temp_Short.getLimit());
		List<Hc68Temp_Short> list= apiAppraisalSpeciaMapper.getAppraisClassListForLook(hc68Temp_Short);
		PageInfo<Hc68Temp_Short> pageinfo = new PageInfo<>(list);
		return pageinfo;
	}
	
	@Override
	public AjaxReturnMsg insertData(Hb75 hb75) {
		try {
			// ʱ������ת��
			if (StringUtils.isNotBlank(hb75.getChb400_String())) {
				hb75.setChb400(DateUtil.stringToDate(hb75.getChb400_String(), "yyyy-MM-dd"));
			}
			if (StringUtils.isNotBlank(hb75.getChb403_String())) {
				hb75.setChb403(DateUtil.stringToDate(hb75.getChb403_String(), "yyyy-MM-dd"));
			}
			if (StringUtils.isNotBlank(hb75.getChb406_String())) {
				hb75.setChb406(DateUtil.stringToDate(hb75.getChb406_String(), "yyyy-MM-dd"));
			}
		} catch (AppException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		hb75.setAae036(new Date());
		// ��������
		if (StringUtils.isBlank(hb75.getChb120())) {
			// �״δ�����������Ĭ��Ϊδ�ύ״̬
			hb75.setChb146(HB75CHB146Enum.NOT_SUBMIT.getCode());
			// ����δ�ύ״̬
			hb75.setChb326(HB75CHB146Enum.NOT_SUBMIT.getCode());
			// ��ȡ�걨����
			hb75.setAae001(DateUtil.dateToCompactString(new Date()).substring(0, 6));
			// ���ü�������
			hb75.setChb126(HB75CHB126Enum.SPECIAL_APPRAISAL.getCode());
			// �Ƿ���ѵ���������
			hb75.setChb144(HB75CHB144Enum.YESANDNO_YES.getCode());
			// �������� Ĭ��0 �´θ���ʱ��ѯ
			hb75.setChb168(new Short("0"));
			// ��ȡ���� ƴ�����α��
			hb75.setAae100(COMAAE100Enum.DATA_EFFECTIVE.getCode());
//			Integer date = Integer.parseInt(DateUtil.dateToCompactString(new Date()).substring(2, 4)) * 1000000;
			hb75.setChb120(apiAppraisalSpeciaMapper.getSQ_HB75_CHB120());
			int result = apiAppraisalSpeciaMapper.addAppraisBatch(hb75);
			if (result == 1) {
				return this.success("����������Ϣ�ɹ�", hb75.getChb120());
			} else {
				return this.error("����������Ϣʧ��");
			}
		} else {
			// У���Ƿ������ύ״̬
			if (HB75CHB146Enum.SUBMITTED.getCode().equals(hb75.getChb326())) {
				// �޸Ĵ˴��걨����Ա״̬(ѧԱ��Ϣ��)
				Hc63 hc63 = new Hc63();
				hc63.setAae100(COMAAE100Enum.DATA_EFFECTIVE.getCode());
				hc63.setChb120(hb75.getChb120());
				hc63.setAae011(HC60CHB256Enum.APPRAISAL_ING.getCode());
				int result3 = apiAppraisalSpeciaMapper.saveStudentBatch(hc63);
				if (result3 < 1) {
					return this.error("������Ա��Ϣ״̬�ύʧ��");
				}
				// �޸���������ЧѧԱ�걨����״̬ ������Ϊ�����
				hc63.setChb312(HB75CHB312Enum.WAIT_AUDIT.getCode());
				apiAppraisalSpeciaMapper.saveStudentBatchStatus(hc63);
				// ���β���Ϊ�ύ���� ����ύ����Ϣ
				// ���״̬
				hb75.setChb146(HB75CHB146Enum.SUBMITTED.getCode());
				hb75.setChb326(HB75CHB146Enum.SUBMITTED.getCode());
				// ����������Ϣ
				hb75.setChb311(new Date());
				hb75.setChb310(hb75.getAae011());
				hb75.setChb169(new Date());
				// ���������״̬
				hb75.setChb312(HB75CHB312Enum.WAIT_AUDIT.getCode());
				// ��Ч����
				hb75.setAae100(COMAAE100Enum.DATA_EFFECTIVE.getCode());
			}
			// ���²���
			int result = apiAppraisalSpeciaMapper.saveAppraisBatch(hb75);
			if (result == 1) {
				return this.success("����������Ϣ�ɹ�", hb75.getChb120());
			} else {
				return this.error("����������Ϣʧ��");
			}
		}
		
	}

	@Override
	public AjaxReturnMsg savePerponDataBath(Hc63 hc63) {
		List<Hc63> hc63Batch = new ArrayList();
		if (StringUtils.isBlank(hc63.getSelectnodes())) {
			return this.error("������ѡ��һ��ѧԱ���뱾����");
		}
		if (StringUtils.isBlank(hc63.getChb068())) {
			return this.error("δ���ҵ�ѧԱ�İ༶��Ϣ,��ˢ��ҳ������");
		}
		//ȥ�����һ�����ַ�
		hc63.setSelectnodes(hc63.getSelectnodes().substring(0, hc63.getSelectnodes().length() - 1));
		String[] chc001Batch = hc63.getSelectnodes().split(",");
		// ��������
		for (String chc001 : chc001Batch) {
			// ��������Ϣ
			Hc63 hc = new Hc63();
			hc.setChc001(chc001);
			hc63Batch.add(hc);
		}

		// ��Ч����������ԭ����Ϣ
		int result0 = apiAppraisalSpeciaMapper.deletePerponDataBatch(COMAAE100Enum.DATA_INVALID.getCode(), hc63.getChb120(), hc63.getChb068());

		// �����걨����
		Map hc63map = new HashMap();
		hc63map.put("chb120", hc63.getChb120());
		hc63map.put("aca111", hc63.getAca111());
		hc63map.put("aca11a", hc63.getAca11a());
		hc63map.put("aae011", hc63.getAae011());
		hc63map.put("aae100", COMAAE100Enum.DATA_EFFECTIVE.getCode());
		hc63map.put("chb312", HB75CHB312Enum.WAIT_AUDIT.getCode());
		hc63map.put("chb068", hc63.getChb068());
		hc63map.put("hc63Batch", hc63Batch);
		int result = apiAppraisalSpeciaMapper.savePerponDataBath(hc63map);
		// �޸� 63���и���Ա�ļ������״̬
//		Map hc60map = new HashMap();
//		hc60map.put("chb256", HC60CHB256Enum.APPRAISAL_ING);
//		hc60map.put("hc60Batch", hc63Batch);
//		int result2 = apiAppraisalSpeciaMapper.saveStudentBatch(hc60map);
		if (result == chc001Batch.length) {
			return this.success("��������ɹ�");
		} else {
			return this.error("�����������,�����в�������δ��¼��,����");
		}
	}


	@Override
	public AjaxReturnMsg deletebyid(Hb75 hb75) {
		if (hb75 != null && StringUtils.isNotBlank(hb75.getChb120())) {
			// �߼�ɾ�������е�ѧԱ�걨��Ϣ  chc068�������Ϊnullʱ ɾ������
			int result0 = apiAppraisalSpeciaMapper.deletePerponDataBatch(COMAAE100Enum.DATA_INVALID.getCode(), hb75.getChb120(), null);
			// �߼�ɾ��
			hb75.setAae100(COMAAE100Enum.DATA_INVALID.getCode());
			int result = apiAppraisalSpeciaMapper.deletebyid(hb75);
			if (result == 1) {
				return this.success("ɾ���ɹ�");
			} else {
				return this.error("ɾ��ʧ��,���ݿ����Ѿ���ɾ��,����");
			}
		}
		return this.error("��������,����");
	}

	@Override
	public AjaxReturnMsg deletebybatch(Hb75 hb75) {
		if (hb75 != null && StringUtils.isNotBlank(hb75.getSelectnodes()) && !",".equals(hb75.getSelectnodes())) {
			//ȥ�����һ�����ַ�
			hb75.setSelectnodes(hb75.getSelectnodes().substring(0, hb75.getSelectnodes().length() - 1));
			String[] chb120Batch = hb75.getSelectnodes().split(",");
			Map hb75map = new HashMap();
			List<Hb75> hb75Batch = new ArrayList();
			// ��������
			for (String chb120 : chb120Batch) {
				// ��������Ϣ
				Hb75 hb = new Hb75();
				hb.setChb120(chb120);
				hb75Batch.add(hb);
			}
			hb75map.put("aae011", hb75.getAae011());
			hb75map.put("aae100", COMAAE100Enum.DATA_INVALID.getCode());
			hb75map.put("hb75Batch", hb75Batch);
			int result1 = apiAppraisalSpeciaMapper.saveStudentHc63Batch(hb75map);
			int result = apiAppraisalSpeciaMapper.deletebyBatch(hb75map);
			if (result == chb120Batch.length) {
				return this.success("ɾ���ɹ�");
			} else {
				return this.error("ɾ��ʧ��,���ݿ����Ѿ���ɾ��,����");
			}
		}
		return this.error("��������,����");
	}

}
