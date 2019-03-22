package com.insigma.mvc.serviceimp.appraisal.JDYW_002_007;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.insigma.common.listener.AppConfig;
import com.insigma.common.util.FileUtil;
import com.insigma.common.util.StringUtil;
import com.insigma.dto.AjaxReturnMsg;
import com.insigma.enums.COMAAE100Enum;
import com.insigma.enums.HC73CAC022Enum;
import com.insigma.enums.HC75CAC007Enum;
import com.insigma.enums.HC75CAC009Enum;
import com.insigma.enums.HC75EAE052Enum;
import com.insigma.mvc.MvcHelper;
import com.insigma.mvc.dao.appraisal.JDYW_002_007.ApiInvigilatorManageMapper;
import com.insigma.mvc.dao.common.fileupload.ApiFileUploadMapper;
import com.insigma.mvc.model.Param;
import com.insigma.mvc.model.SFileRecord;
import com.insigma.mvc.model.appraisal.Hc73;
import com.insigma.mvc.model.appraisal.Hc75;
import com.insigma.mvc.model.train.Hb68;
import com.insigma.mvc.model.train.Hc74;
import com.insigma.mvc.service.appraisal.JDYW_002_007.ApiInvigilatorManageService;
import com.insigma.mvc.service.common.fileupload.ApiFileUploadService;

@Service
@Transactional(rollbackFor=Exception.class)
public class ApiInvigilatorManageServiceImpl extends MvcHelper implements ApiInvigilatorManageService{

	@Resource
	private ApiInvigilatorManageMapper apiInvigilatorManageMapper;

	@Resource
	private ApiFileUploadService fileLoadService;
	
	@Resource
    private ApiFileUploadMapper apiFileUploadMapper;
	
	//��ȡ��Ӧ�����ļ࿼Ա
	@Override
	public PageInfo<Hc73> getInvigilatorList(Hc73 hc73) {
		PageHelper.offsetPage(hc73.getOffset(), hc73.getLimit());
		if(StringUtils.isNotEmpty(hc73.getAac004())){
			hc73.setA_aac004(hc73.getAac004().split(","));
		}
		if(StringUtils.isNotEmpty(hc73.getAac011())){
			hc73.setA_aac011(hc73.getAac011().split(","));
		}
		if(StringUtils.isNotEmpty(hc73.getAca111())){
			hc73.setA_aca111(hc73.getAca111().split(","));
		}
		// ��ѯ��Ч����
		hc73.setAae100(COMAAE100Enum.DATA_EFFECTIVE.getCode());
		// �Ǽ࿼Ա
		hc73.setCac023(HC73CAC022Enum.YESANDNO_YES.getCode());
		List<Hc73> list=apiInvigilatorManageMapper.getInvigilatorList(hc73);
		PageInfo<Hc73> pageinfo = new PageInfo<>(list);
		return pageinfo;
		
	}

	//���ݼ࿼Ա�����ȡ�࿼Ա��Ϣ
	@Override
	public AjaxReturnMsg getInvigilatorById(String id, String aab001) {
		Hc73 hc73 = apiInvigilatorManageMapper.getInvigilatorById(id, aab001, COMAAE100Enum.DATA_EFFECTIVE.getCode(), HC73CAC022Enum.YESANDNO_YES.getCode());
		if(StringUtil.isNotEmpty(hc73.getPhoto_url())){
			//��ͼƬ����ƴ����ȥ
			String fileModule = AppConfig.getProperties("fileModule");
			hc73.setPhoto_url(fileModule+hc73.getPhoto_url());
		}
		return this.success(hc73);
	}

	//У�����֤
	@Override
	public AjaxReturnMsg<String> checkAac002(Hc73 hc73) {
		List<Hc73> list=apiInvigilatorManageMapper.checkAac002(hc73.getAac002(), COMAAE100Enum.DATA_EFFECTIVE.getCode());
		if(list.size()>0){
			return this.error("�����֤���ڼ࿼Ա��");
		}else{
			return this.success("���֤�����");
		}
	
	}

	//����࿼Ա��Ϣ
	@Override
	public AjaxReturnMsg saveData(Hc73 hc73) {
		hc73.setAab001(hc73.getBaseinfoid());
		hc73.setAae011(hc73.getUserid());
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String dataS = hc73.getAac002().substring(6, 14);
		Date aac006 = null;
		try {
			aac006 = sdf.parse(dataS);
		} catch (ParseException e) {
			return this.error("ʱ��ת������");
		}
		hc73.setAac006(aac006);
		// �࿼Ա��������
		if(StringUtils.isBlank(hc73.getChc075())) {
			//�жϴ����֤�Ƿ��Ѿ��ڿ�����Ա��
			List<Hc73> list=apiInvigilatorManageMapper.checkAac002(hc73.getAac002(), COMAAE100Enum.DATA_EFFECTIVE.getCode());
			if(list != null && list.size()>0){
				// ����Ա������Ϣ�Ѵ��� ������Ƹ�ù�ϵ
				hc73.setChc073(list.get(0).getChc073());
				// У������Ϸ���
				if (HC75CAC007Enum.fromCode(hc73.getCac007()) == null || HC75CAC009Enum.fromCode(hc73.getCac009()) == null) {
					return this.error("��Ա��ݡ�Ƹ���������Ϊ�ջ�������Ϸ�");
				}
				// ��ѯ�Ƿ����Ƹ�ι�ϵ �������������
				Hc73 checkHc73 = new Hc73();
				checkHc73.setAab001(hc73.getAab001());
				checkHc73.setAae100(COMAAE100Enum.DATA_EFFECTIVE.getCode());
				checkHc73.setCac023(HC73CAC022Enum.YESANDNO_YES.getCode());
				checkHc73.setAac002(hc73.getAac002());
				Integer n = apiInvigilatorManageMapper.getCheckAppointmentPresence(checkHc73);
				if (n != null && n > 0) {
					return this.error("����Ա�Ѵ����������վƸ�ι�ϵ�������ظ�����");
				}
				// ���ڸ���Ա������Ϣ ��δ��ù�����Ա������� ���࿼Ա�� �����࿼Ա���
//				checkHc73 = new Hc73();
//				checkHc73.setAac002(hc73.getAac002());
//				checkHc73.setAae100(COMAAE100Enum.DATA_EFFECTIVE.getCode());
//				checkHc73.setCac023(HC73CAC022Enum.YESANDNO_YES.getCode());
//				checkHc73.setAab001(hc73.getAab001());
				// �����û��࿼Ա���
//				int num = apiInvigilatorManageMapper.updatePerponIdentity(checkHc73);
//				if (num != 1) {
//					return this.error("��Ϣ����ʧ�ܣ�������");
//				}
//				return this.success("�����࿼Ա��Ϣ�ɹ����������д������Ϣ",hc73.getChc073());
			} else {
				// ����Ա������Ϣ������ ����������Ϣ
				String str = hc73.getAac002().substring(16, 17);
				int i = Integer.parseInt(str) % 2;
				if(i == 0) {
					hc73.setAac004("2");
				}else{
					hc73.setAac004("1");
				}
				
				hc73.setAae100(COMAAE100Enum.DATA_EFFECTIVE.getCode());
				hc73.setAae036(new Date());
				// �Ǽ࿼Ա
//				hc73.setCac023(HC73CAC022Enum.YESANDNO_YES.getCode());
				int num = apiInvigilatorManageMapper.addInvigilator(hc73);
				if(num != 1) {
					return this.error("���������Ϣʧ��");
				}
			}
			// �����Ƿ���� ����������������ԱƸ�ι�ϵ
			Hc75 hc75 = new Hc75();
			hc75.setChc073(hc73.getChc073()); // ������Ա����
			hc75.setCac007(hc73.getCac007()); //��Ա��ݴ���
			hc75.setCac009(hc73.getCac009()); //��ԱƸ��������� 
			hc75.setAab001(hc73.getBaseinfoid()); //������վ����
			hc75.setAae100(COMAAE100Enum.DATA_EFFECTIVE.getCode()); // ��Ϣ��Ч����
			hc75.setAae011(hc73.getUserid()); //������
			hc75.setEae052(HC75EAE052Enum.AUDIT_PASS.getCode()); //��˱�־����(ͨ����̨�����Ŀ�����Ա Ĭ��ͨ����� �࿼Ա����Ҫ���)
			hc75.setCac023(HC73CAC022Enum.YESANDNO_YES.getCode());// ��Ƹ��ϵ �࿼Ա
			// ����Ƹ�ι�ϵ
			int num2 = apiInvigilatorManageMapper.insertAppointment(hc75);
			if(num2 != 1) {
				return this.error("����Ƹ�ι�ϵ��Ϣʧ��");
			}
			return this.success("�����࿼Ա��Ϣ�ɹ�",hc73.getChc073());
		}else{
			// У������Ϸ���
			if (HC75CAC007Enum.fromCode(hc73.getCac007()) == null || HC75CAC009Enum.fromCode(hc73.getCac009()) == null) {
				return this.error("��Ա��ݡ�Ƹ���������Ϊ�ջ�������Ϸ�");
			}
			// �޸Ļ�����Ϣ
			int num = apiInvigilatorManageMapper.updateInvigilator(hc73);
			if(num != 1) {
				return this.error("����ʧ��");
			}
			// �޸ļ࿼ԱƸ�ι�ϵ
			Hc75 hc75 = new Hc75();
			hc75.setChc075(hc73.getChc075());
			hc75.setChc073(hc73.getChc073()); // ������Ա����
			hc75.setCac007(hc73.getCac007()); //��Ա��ݴ���
			hc75.setCac009(hc73.getCac009()); //��ԱƸ��������� 
			hc75.setAab001(hc73.getBaseinfoid()); //������վ����
			hc75.setAae100(COMAAE100Enum.DATA_EFFECTIVE.getCode()); // ��Ϣ��Ч����
			hc75.setAae011(hc73.getUserid()); //������
			hc75.setCac023(HC73CAC022Enum.YESANDNO_YES.getCode()); //�࿼Ա
			//ֻҪ���޸ľ���Ҫ���
			hc75.setEae052(HC75EAE052Enum.WAIT_AUDIT.getCode()); //��˱�־����(ͨ����̨�����Ŀ�����Ա Ĭ��ͨ�����)
			int num2 = apiInvigilatorManageMapper.updateAppointment(hc75);
			if(num2 != 1) {
				return this.error("����Ƹ�ι�ϵ��Ϣʧ��");
			}
			return this.success("����࿼Ա��Ϣ�ɹ�",hc73.getChc073());
		}
	}

	//ɾ���࿼Ա��Ϣ(��Ϊ�࿼Ա������Ϣ���ù�ϵ ɾ��������ɾ��������Ϣ ֻ�����Ӷ��ϵ)
	@Override
	public AjaxReturnMsg<String> deleteInvigilator(Hc73 hc73) {
		hc73 = apiInvigilatorManageMapper.getInvigilatorById(hc73.getChc073(), hc73.getAab001(), COMAAE100Enum.DATA_EFFECTIVE.getCode(), HC73CAC022Enum.YESANDNO_YES.getCode());
//		if(hc73.getBus_uuid()!=null) {
//			deleteFile(hc73.getBus_uuid());
//		}
		// ���Ƹ�ι�ϵ
		int deletenum = apiInvigilatorManageMapper.deleteEmployment(hc73.getChc073(), hc73.getAab001(), COMAAE100Enum.DATA_INVALID.getCode(), HC73CAC022Enum.YESANDNO_YES.getCode());
		if(deletenum == 1) {
			//����ɾ��
//			String[] ids = {hc73.getChc073()};
//			deletenum = apiInvigilatorManageMapper.deleteQualifications(ids);
			
			return this.success("ɾ���ɹ�");
		}else{
			return this.error("ɾ��ʧ��");
		}
	}

	//����ɾ���࿼Ա��Ϣ
	@Override
	public AjaxReturnMsg batDelete(Hc73 hc73) {
		hc73.setA_chb061(hc73.getSelectnodes().split(","));
//		List<Hc73> list=apiInvigilatorManageMapper.getInvigilatorList(hc73);
//		for (Hc73 h:list) {
//			if(h.getBus_uuid()!=null) {
//				deleteFile(h.getBus_uuid());
//			}
//		}
		// ���Ƹ�ι�ϵ
		Map<String, Object> hc73Map = new HashMap();
		hc73Map.put("aab001", hc73.getAab001());
		hc73Map.put("aae100", COMAAE100Enum.DATA_INVALID.getCode());
		hc73Map.put("A_chb061", hc73.getA_chb061());
		hc73Map.put("cac022", HC73CAC022Enum.YESANDNO_YES.getCode());
		int batdeletenum=apiInvigilatorManageMapper.batDeleteEmployment(hc73Map);
		if(batdeletenum==hc73.getA_chb061().length){
//			apiInvigilatorManageMapper.deleteQualifications(hc73.getA_chb061());
			return this.success("����ɾ���ɹ�");
		}else{
			return this.success("����ɾ���ɹ�,������ʧ�ܵ�����,����");
		}
	}

	
	//��ȡ�࿼Ա������Ϣ�б�
	@Override
	public PageInfo<Hc74> getQualificationList(Hc74 hc74) {
		PageHelper.offsetPage(0, 200);
		List<Hc74> list=apiInvigilatorManageMapper.getQualificationList(hc74.getChb061());
		PageInfo<Hc74> pageinfo = new PageInfo<Hc74>(list);
		return pageinfo;
	}
	
	//����࿼Ա������Ϣ
	@Override
	public AjaxReturnMsg saveQualification(Hc74 hc74) {
		
		List<Hc74> list = apiInvigilatorManageMapper.checkQualification(hc74);
		if(list.size()>0) {
			return this.error("�˼࿼Ա��ӵ�и����ʣ��޷��ظ�����");
		}
		
		hc74.setAae011(hc74.getUserid());
		if("".equals(hc74.getChc074())) {
			hc74.setAae100("1");
			hc74.setAae036(new Date());
			int num = apiInvigilatorManageMapper.saveQualification(hc74);
			if(num == 1) {
				return this.success("����ɹ�", hc74.getChc074());
			}else{
				return this.error("����ʧ��");
			}
		}else{
			int num = apiInvigilatorManageMapper.updateQualification(hc74);
			if(num == 1) {
				return this.success("����ɹ�", hc74.getChc074());
			}else{
				return this.error("����ʧ��");
			}
		}
	}

	//ɾ���࿼Ա������Ϣ
	@Override
	public AjaxReturnMsg<String> deleteQualification(String chc074) {
		Hc74 hc74 = apiInvigilatorManageMapper.getQualification(chc074);
		if(hc74.getBus_uuid()!=null) {
			deleteFile(hc74.getBus_uuid());
		}
		int deletenum = apiInvigilatorManageMapper.deleteQualification(chc074);
		if(deletenum == 1) {
			return this.success("ɾ���ɹ�");
		}else{
			return this.error("ɾ��ʧ��");
		}
	}

	//�������������ȡ������Ϣ
	@Override
	public AjaxReturnMsg getQualification(String chc074) {
		Hc74 hc74 = apiInvigilatorManageMapper.getQualification(chc074);
		if(StringUtil.isNotEmpty(hc74.getPhoto_url())){
			//��ͼƬ����ƴ����ȥ
			String fileModule = AppConfig.getProperties("fileModule");
            hc74.setPhoto_url(fileModule + hc74.getPhoto_url());
		}
		return this.success(hc74);
	}

	//����˼࿼Ա�Ƿ����д�����
	@Override
	public AjaxReturnMsg checkQualification(Hc74 hc74) {
		List<Hc74> list=apiInvigilatorManageMapper.checkQualification(hc74);
		if(list.size()>0){
			return this.error("�˼࿼Ա���и�����");
		}else{
			return this.success("���ʿ����");
		}
	}

	//���ݼ࿼Ա�����ȡ�࿼Ա��Ϣ����
	@Override
	public AjaxReturnMsg getInvigilatorGBKById(String chb061, String aab001) {
		Hc73 hc73 = apiInvigilatorManageMapper.getInvigilatorGBKById(chb061, aab001, COMAAE100Enum.DATA_EFFECTIVE.getCode(), HC73CAC022Enum.YESANDNO_YES.getCode());
		if(StringUtil.isNotEmpty(hc73.getPhoto_url())){
			//��ͼƬ����ƴ����ȥ
			String fileModule = AppConfig.getProperties("fileModule");
			hc73.setPhoto_url(fileModule+hc73.getPhoto_url());
		}
		return this.success(hc73);
	}

	//���ݼ࿼Ա�����ȡ�࿼Ա��Ϣ����
	@Override
	public AjaxReturnMsg getIdcardInfo(String aac002) {
		Hc73 hc73 = apiInvigilatorManageMapper.getIdcardInfo(aac002, COMAAE100Enum.DATA_EFFECTIVE.getCode());
		if(StringUtil.isNotEmpty(hc73.getPhoto_url())){
			//��ͼƬ����ƴ����ȥ
			String fileModule = AppConfig.getProperties("fileModule");
			hc73.setPhoto_url(fileModule+hc73.getPhoto_url());
		}
		return this.success(hc73);
	}

	//�ϴ��࿼Աͷ��
	@Override
	public AjaxReturnMsg uploadLogo(String userid, String file_bus_id, String file_name, MultipartFile multipartFile) {
		try {
			//����ͼƬ���ļ���������ͬʱ����ͼƬ��¼
			SFileRecord sFileRecord = fileLoadService.uploadFile(multipartFile, Param.TRAIN_GROUP_TEACHER_LOGO, file_name,
					file_bus_id);
			return this.success(sFileRecord);
		} catch (Exception e) {
			return this.error(e.getMessage());
		}
	}

    //�ϴ���ѵ�����࿼Ա����ͼƬ
    @Override
    public AjaxReturnMsg uploadImage(String userid, String file_bus_id, String file_name, MultipartFile multipartFile) {
        try {
            //����ͼƬ���ļ���������ͬʱ����ͼƬ��¼
            SFileRecord sFileRecord = fileLoadService.uploadFile(multipartFile, Param.TRAIN_GROUP_TEACHER_QUALIFICATION_IMAGE, file_name, file_bus_id);
            return this.success(sFileRecord);
        } catch (Exception e) {
            return this.error(e.getMessage());
        }
    }

	/**
     * ɾ���ļ���¼����ʵ�ļ�
     *
     * @param bus_uuid ҵ���ϴ��ļ�����
     */
	public AjaxReturnMsg deleteFile(String bus_uuid) {
		SFileRecord sFileRecord = apiFileUploadMapper.getBusFileRecordByBusId(bus_uuid);
		if (sFileRecord == null) {
            return this.error("�ļ���¼������");
        }

        //ɾ���ļ�ҵ���¼
        apiFileUploadMapper.deleteFileByBusUuid(bus_uuid);
        //ɾ���ļ���¼
        apiFileUploadMapper.deleteFileByFileUuid(sFileRecord.getFile_uuid());
        //ɾ����ʵ�ļ�
        try {
			FileUtil.delFileOnExist(sFileRecord.getFile_path());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	//��ѯ�࿼Ա��ѧ��Ϣ
	@Override
	public PageInfo<Hb68> getInvigilatorClasses(Hc73 hc73) {
		PageHelper.offsetPage(hc73.getOffset(), hc73.getLimit());
		List<Hb68> list=apiInvigilatorManageMapper.getInvigilatorClasses(hc73);
		PageInfo<Hb68> pageinfo = new PageInfo<Hb68>(list);
		return pageinfo;
	}
 	
}
