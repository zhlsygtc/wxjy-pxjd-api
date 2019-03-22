package com.insigma.mvc.serviceimp.train.PXYW_001_002;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

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
import com.insigma.mvc.MvcHelper;
import com.insigma.mvc.dao.common.fileupload.ApiFileUploadMapper;
import com.insigma.mvc.dao.train.PXYW_001_002.ApiTeacherMapper;
import com.insigma.mvc.model.Param;
import com.insigma.mvc.model.SFileRecord;
import com.insigma.mvc.model.train.Hb61;
import com.insigma.mvc.model.train.Hb68;
import com.insigma.mvc.model.train.Hc74;
import com.insigma.mvc.service.common.fileupload.ApiFileUploadService;
import com.insigma.mvc.service.train.PXYW_001_002.ApiTeacherService;

@Service
@Transactional(rollbackFor=Exception.class)
public class ApiTeacherServiceImpl extends MvcHelper implements ApiTeacherService{

	@Resource
	private ApiTeacherMapper apiTeacherMapper;
	
	@Resource
	private ApiFileUploadService fileLoadService;
	
	@Resource
    private ApiFileUploadMapper apiFileUploadMapper;
	
	//��ȡ��Ӧ�����Ľ�ʦ
	@Override
	public PageInfo<Hb61> getTeacherList(Hb61 hb61) {
		PageHelper.offsetPage(hb61.getOffset(), hb61.getLimit());
		if(StringUtils.isNotEmpty(hb61.getAac004())){
			hb61.setA_aac004(hb61.getAac004().split(","));
		}
		if(StringUtils.isNotEmpty(hb61.getAac011())){
			hb61.setA_aac011(hb61.getAac011().split(","));
		}
		if(StringUtils.isNotEmpty(hb61.getAca111())){
			hb61.setA_aca111(hb61.getAca111().split(","));
		}
		List<Hb61> list=apiTeacherMapper.getTeacherList(hb61);
		PageInfo<Hb61> pageinfo = new PageInfo<>(list);
		return pageinfo;
		
	}

	//���ݽ�ʦ�����ȡ��ʦ��Ϣ
	@Override
	public AjaxReturnMsg getTeacherById(String id) {
		Hb61 hb61 = apiTeacherMapper.getTeacherById(id);
		if(StringUtil.isNotEmpty(hb61.getPhoto_url())){
			//��ͼƬ����ƴ����ȥ
			String fileModule = AppConfig.getProperties("fileModule");
			hb61.setPhoto_url(fileModule+hb61.getPhoto_url());
		}
		return this.success(hb61);
	}

	//У�����֤
	@Override
	public AjaxReturnMsg<String> checkAac002(Hb61 hb61) {
		List<Hb61> list=apiTeacherMapper.checkAac002(hb61);
		if(list.size()>0){
			return this.error("�����֤���ڽ�ʦ��");
		}else{
			return this.success("���֤�����");
		}
	
	}

	//�����ʦ��Ϣ
	@Override
	public AjaxReturnMsg saveData(Hb61 hb61) {
		hb61.setAab001(hb61.getBaseinfoid());
		hb61.setAae011(hb61.getUserid());
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String dataS = hb61.getAac002().substring(6, 14);
		Date aac006 = null;
		try {
			aac006 = sdf.parse(dataS);
		} catch (ParseException e) {
			return this.error("ʱ��ת������");
		}
		hb61.setAac006(aac006);
		if("".equals(hb61.getChb061())) {
			//�жϴ����֤�Ƿ��Ѿ���ʦ�ʿ�
			List<Hb61> list=apiTeacherMapper.checkAac002(hb61);
			if(list.size()>0){
				return this.error("�����֤���ڽ�ʦ��");
			}
			
			String str = hb61.getAac002().substring(16, 17);
			int i = Integer.parseInt(str) % 2;
			if(i == 0) {
				hb61.setAac004("2");
			}else{
				hb61.setAac004("1");
			}
			
			hb61.setEae052("0");
			hb61.setAae100("1");
			hb61.setAae036(new Date());
			int num = apiTeacherMapper.addTeacher(hb61);
			if(num==1) {
				return this.success("���������Ϣ�ɹ����������д������Ϣ",hb61.getChb061());
			}else{
				return this.error("����ʧ��");
			}
		}else{
			//ֻҪ���޸ľ���Ҫ���
//			if("9".equals(hb61.getEae052())) {
				hb61.setEae052("0");
//			}
			int num = apiTeacherMapper.updateTeacher(hb61);
			if(num==1) {
				return this.success("����ɹ�",hb61.getChb061());
			}else{
				return this.error("����ʧ��");
			}
		}
	}

	//ɾ����ʦ��Ϣ
	@Override
	public AjaxReturnMsg<String> deleteTeacher(Hb61 hb61) {
		hb61 = apiTeacherMapper.getTeacherById(hb61.getChb061());
		if(hb61.getBus_uuid()!=null) {
			deleteFile(hb61.getBus_uuid());
		}
		int deletenum = apiTeacherMapper.deleteTeacher(hb61.getChb061());
		if(deletenum == 1) {
			String[] ids = {hb61.getChb061()};
			deletenum = apiTeacherMapper.deleteQualifications(ids);
			
			return this.success("ɾ���ɹ�");
		}else{
			return this.error("ɾ��ʧ��");
		}
	}

	//����ɾ����ʦ��Ϣ
	@Override
	public AjaxReturnMsg batDelete(Hb61 hb61) {
		hb61.setA_chb061(hb61.getSelectnodes().split(","));
		List<Hb61> list=apiTeacherMapper.getTeacherList(hb61);
		for (Hb61 h:list) {
			if(h.getBus_uuid()!=null) {
				deleteFile(h.getBus_uuid());
			}
		}
		int batdeletenum=apiTeacherMapper.batDelete(hb61.getA_chb061());
		if(batdeletenum==hb61.getA_chb061().length){
			apiTeacherMapper.deleteQualifications(hb61.getA_chb061());
			return this.success("����ɾ���ɹ�");
		}else{
			return this.success("����ɾ���ɹ�,������ʧ�ܵ�����,����");
		}
	}

	
	//��ȡ��ʦ������Ϣ�б�
	@Override
	public PageInfo<Hc74> getQualificationList(Hc74 hc74) {
		PageHelper.offsetPage(0, 200);
		List<Hc74> list=apiTeacherMapper.getQualificationList(hc74.getChb061());
		PageInfo<Hc74> pageinfo = new PageInfo<Hc74>(list);
		return pageinfo;
	}
	
	//�����ʦ������Ϣ
	@Override
	public AjaxReturnMsg saveQualification(Hc74 hc74) {
		
		List<Hc74> list = apiTeacherMapper.checkQualification(hc74);
		if(list.size()>0) {
			return this.error("�˽�ʦ��ӵ�и����ʣ��޷��ظ�����");
		}
		
		hc74.setAae011(hc74.getUserid());
		if("".equals(hc74.getChc074())) {
			hc74.setAae100("1");
			hc74.setAae036(new Date());
			int num = apiTeacherMapper.saveQualification(hc74);
			if(num == 1) {
				return this.success("����ɹ�", hc74.getChc074());
			}else{
				return this.error("����ʧ��");
			}
		}else{
			int num = apiTeacherMapper.updateQualification(hc74);
			if(num == 1) {
				return this.success("����ɹ�", hc74.getChc074());
			}else{
				return this.error("����ʧ��");
			}
		}
	}

	//ɾ����ʦ������Ϣ
	@Override
	public AjaxReturnMsg<String> deleteQualification(String chc074) {
		Hc74 hc74 = apiTeacherMapper.getQualification(chc074);
		if(hc74.getBus_uuid()!=null) {
			deleteFile(hc74.getBus_uuid());
		}
		int deletenum = apiTeacherMapper.deleteQualification(chc074);
		if(deletenum == 1) {
			return this.success("ɾ���ɹ�");
		}else{
			return this.error("ɾ��ʧ��");
		}
	}

	//�������������ȡ������Ϣ
	@Override
	public AjaxReturnMsg getQualification(String chc074) {
		Hc74 hc74 = apiTeacherMapper.getQualification(chc074);
		if(StringUtil.isNotEmpty(hc74.getPhoto_url())){
			//��ͼƬ����ƴ����ȥ
			String fileModule = AppConfig.getProperties("fileModule");
            hc74.setPhoto_url(fileModule + hc74.getPhoto_url());
		}
		return this.success(hc74);
	}

	//����˽�ʦ�Ƿ����д�����
	@Override
	public AjaxReturnMsg checkQualification(Hc74 hc74) {
		List<Hc74> list=apiTeacherMapper.checkQualification(hc74);
		if(list.size()>0){
			return this.error("�˽�ʦ���и�����");
		}else{
			return this.success("���ʿ����");
		}
	}

	//���ݽ�ʦ�����ȡ��ʦ��Ϣ����
	@Override
	public AjaxReturnMsg getTeacherGBKById(String chb061) {
		Hb61 hb61 = apiTeacherMapper.getTeacherGBKById(chb061);
		if(StringUtil.isNotEmpty(hb61.getPhoto_url())){
			//��ͼƬ����ƴ����ȥ
			String fileModule = AppConfig.getProperties("fileModule");
			hb61.setPhoto_url(fileModule+hb61.getPhoto_url());
		}
		return this.success(hb61);
	}

	//�ϴ���ʦͷ��
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

    //�ϴ���ѵ������ʦ����ͼƬ
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

	//��ѯ��ʦ��ѧ��Ϣ
	@Override
	public PageInfo<Hb68> getTeacherClasses(Hb61 hb61) {
		PageHelper.offsetPage(hb61.getOffset(), hb61.getLimit());
		List<Hb68> list=apiTeacherMapper.getTeacherClasses(hb61);
		PageInfo<Hb68> pageinfo = new PageInfo<Hb68>(list);
		return pageinfo;
	}
 	
}
