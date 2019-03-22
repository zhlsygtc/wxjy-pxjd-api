package com.insigma.mvc.serviceimp.train.PXYW_001_013;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.insigma.common.listener.AppConfig;
import com.insigma.common.util.FileUtil;
import com.insigma.common.util.StringUtil;
import com.insigma.dto.AjaxReturnMsg;
import com.insigma.mvc.MvcHelper;
import com.insigma.mvc.dao.common.fileupload.ApiFileUploadMapper;
import com.insigma.mvc.dao.person.ApiRegistMapper;
import com.insigma.mvc.dao.train.PXYW_001_013.ApiHeadTeacherMapper;
import com.insigma.mvc.model.Param;
import com.insigma.mvc.model.SFileRecord;
import com.insigma.mvc.model.train.Hb57;
import com.insigma.mvc.model.train.Hb68;
import com.insigma.mvc.service.common.fileupload.ApiFileUploadService;
import com.insigma.mvc.service.train.PXYW_001_013.ApiHeadTeacherService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Service
@Transactional(rollbackFor=Exception.class)
public class ApiHeadTeacherServiceImpl extends MvcHelper implements ApiHeadTeacherService {

	@Resource
	private ApiHeadTeacherMapper apiHeadTeacherMapper;

	@Resource
	private ApiRegistMapper apiRegistMapper;
	
	@Resource
	private ApiFileUploadService fileLoadService;
	
	@Resource
    private ApiFileUploadMapper apiFileUploadMapper;
	
	//��ȡ��������Ϣ�б�
	@Override
	public PageInfo<Hb57> getHeadTeacherList(Hb57 hb57) {
		PageHelper.offsetPage(hb57.getOffset(), hb57.getLimit());
		List<Hb57> list=apiHeadTeacherMapper.getHeadTeacherList(hb57);
		PageInfo<Hb57> pageinfo = new PageInfo<>(list);
		return pageinfo;
	}

	//����id��ȡ��������Ϣ
	@Override
	public AjaxReturnMsg getHeadTeacherById(String chb059) {
		Hb57 hb57 = apiHeadTeacherMapper.getHeadTeacherById(chb059);
		if(StringUtil.isNotEmpty(hb57.getPhoto_url())){
			//��ͼƬ����ƴ����ȥ
			String fileModule = AppConfig.getProperties("fileModule");
			hb57.setPhoto_url(fileModule+hb57.getPhoto_url());
		}
		return this.success(hb57);
	}

	//�������֤�Ƿ����ڰ����ο�
	@Override
	public AjaxReturnMsg checkAac002(Hb57 hb57) {
		int count = apiHeadTeacherMapper.checkAac002(hb57);
		if(count > 0) {
			return this.error("�����֤���ڰ����ο�");
		}else{
			return this.success("���֤�����");
		}
	
	}

	//�����������Ϣ
	@Override
	public AjaxReturnMsg saveHeadTeacher(Hb57 hb57) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		if("".equals(hb57.getChb057())) {
			//�жϴ����֤�Ƿ��Ѿ��ڰ����ο�
			//int count = apiHeadTeacherMapper.checkAac002(hb57);
			//if(count > 0){
			//	return this.error("�����֤���ڰ����ο�");
			//}
			String dataS = hb57.getAac002().substring(6, 14);
			Date aac006 = null;
			try {
				aac006 = sdf.parse(dataS);
			} catch (ParseException e) {
				return this.error("ʱ��ת������");
			}
			hb57.setAac006(aac006);
			String str = hb57.getAac002().substring(16, 17);
			int i = Integer.parseInt(str) % 2;
			if(i == 0) {
				hb57.setAac004("2");
			}else{
				hb57.setAac004("1");
			}
			hb57.setAae100("1");
			hb57.setAae036(new Date());
			int num = apiHeadTeacherMapper.addHeadTeacher(hb57);
			if(num==1) {
				return this.success("����ɹ�", hb57.getChb057());
			}else{
				return this.error("����ʧ��");
			}
		}else{
			int num = apiHeadTeacherMapper.updHeadTeacher(hb57);
			if(num==1) {
				return this.success("����ɹ�", hb57.getChb057());
			}else{
				return this.error("����ʧ��");
			}
		}
	}

	//ɾ����������Ϣ
	@Override
	public AjaxReturnMsg deleteHeadTeacherById(Hb57 hb57) {
		hb57 = apiHeadTeacherMapper.getHeadTeacherById(hb57.getChb057());
		String userId = hb57.getUser_id();
		String bus_uuid = hb57.getBus_uuid() != null ? hb57.getBus_uuid() : "";
		if(hb57.getBus_uuid() != null) {
			deleteFile(hb57.getBus_uuid());
		}
		int deletenum = apiHeadTeacherMapper.deleteHeadTeacherById(hb57.getChb057());
		if(deletenum == 1) {
            if(!"".equals(bus_uuid)) {
                deleteFile(bus_uuid);
            }
			HashMap map = new HashMap();
            map.put("userid", userId);
            map.put("rolecode", "TRAIN-HEADMASTER");
            apiRegistMapper.deleteSUserRoleByUserIdAndRolecode(map);
            if("02".equals(hb57.getChb299())) {
                apiRegistMapper.deleteSUserByUserId(userId);
            }
			return this.success("ɾ���ɹ�");
		}else{
			return this.error("ɾ��ʧ��");
		}
	}

	//����ɾ����������Ϣ
	@Override
	public AjaxReturnMsg deleteHeadTeacherByIds(Hb57 hb57) {
		hb57.setChb057_arr(hb57.getSelectnodes().split(","));
		List idsList = new ArrayList();
		List idsListAll = new ArrayList();
		List<Hb57> list = apiHeadTeacherMapper.getHeadTeacherList(hb57);
		for (Hb57 h:list) {
            idsListAll.add(h.getUser_id());
            if("02".equals(h.getChb299())) {
                idsList.add(h.getUser_id());
            }
			if(h.getBus_uuid() != null) {
				deleteFile(h.getBus_uuid());
			}
		}
		String[] ids = (String[]) idsList.toArray(new String[idsList.size()]);
        String[] idsAll = (String[]) idsListAll.toArray(new String[idsListAll.size()]);
		int deletenum = apiHeadTeacherMapper.deleteHeadTeacherByIds(hb57.getChb057_arr());
		if(deletenum == hb57.getChb057_arr().length){
            HashMap map = new HashMap();
            map.put("ids", idsAll);
            map.put("rolecode", "TRAIN-HEADMASTER");
            apiRegistMapper.deleteSUserRoleByUserIdsAndRolecode(map);
            apiRegistMapper.deleteSUserByUserIds(ids);
			return this.success("����ɾ���ɹ�");
		}else{
			return this.success("����ɾ���ɹ�,������ʧ�ܵ�����,����");
		}
	}

	//�ϴ�������ͷ��
	@Override
	public AjaxReturnMsg uploadLogo(String userid, String file_bus_id, String file_name, MultipartFile multipartFile) {
		try {
			//����ͼƬ���ļ���������ͬʱ����ͼƬ��¼
			SFileRecord sFileRecord = fileLoadService.uploadFile(multipartFile, Param.TRAIN_GROUP_HEADTEACHER_LOGO, file_name,
					file_bus_id);
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

	//�����θ���༶�б��ѯ
	@Override
	public PageInfo<Hb68> getHeadTeacherClasses(Hb57 hb57) {
		PageHelper.offsetPage(hb57.getOffset(), hb57.getLimit());
		List<Hb68> list=apiHeadTeacherMapper.getHeadTeacherClasses(hb57);
		PageInfo<Hb68> pageinfo = new PageInfo<Hb68>(list);
		return pageinfo;
	}

}
