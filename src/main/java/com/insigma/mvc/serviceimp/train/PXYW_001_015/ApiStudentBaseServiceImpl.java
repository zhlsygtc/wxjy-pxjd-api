package com.insigma.mvc.serviceimp.train.PXYW_001_015;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.insigma.common.listener.AppConfig;
import com.insigma.common.util.FileUtil;
import com.insigma.common.util.StringUtil;
import com.insigma.dto.AjaxReturnMsg;
import com.insigma.mvc.MvcHelper;
import com.insigma.mvc.dao.common.fileupload.ApiFileUploadMapper;
import com.insigma.mvc.dao.train.PXYW_001_015.ApiStudentBaseMapper;
import com.insigma.mvc.model.Param;
import com.insigma.mvc.model.SFileRecord;
import com.insigma.mvc.model.train.Hb68;
import com.insigma.mvc.model.train.Student;
import com.insigma.mvc.service.common.fileupload.ApiFileUploadService;
import com.insigma.mvc.service.train.PXYW_001_015.ApiStudentBaseService;

@Service
public class ApiStudentBaseServiceImpl extends MvcHelper implements ApiStudentBaseService{

	@Resource
	private ApiFileUploadMapper apiFileUploadMapper;
	@Resource
	private ApiFileUploadService fileLoadService;
	
	@Resource 
	private ApiStudentBaseMapper apiStudentBaseMapper;
	
	@Override
	public PageInfo<Student> getStudentList(Student stu) {
		PageHelper.offsetPage(stu.getOffset(), stu.getLimit());
		List<Student> list=apiStudentBaseMapper.getStudentList(stu);
		PageInfo<Student> pageinfo = new PageInfo<>(list);
		return pageinfo;
	}
	


	@Override
	public AjaxReturnMsg getStudentById(String chc111) {
		Student stu = apiStudentBaseMapper.getStudentById(chc111);
		if(StringUtil.isNotEmpty(stu.getPhoto_url())){
			//��ͼƬ����ƴ����ȥ
			String fileModule = AppConfig.getProperties("fileModule");
			stu.setPhoto_url(fileModule+stu.getPhoto_url());
		}
		return this.success(stu);
	}

	@Override
	public AjaxReturnMsg updateStu(Student stu) {
		stu.setAae036(new Date());
		int num = apiStudentBaseMapper.updateStu(stu);
		if(num == 1) {
			return this.success("����ɹ�", stu.getChc111());
		}else{
			return this.error("����ʧ��");
		}
	}

	@Override
	public AjaxReturnMsg uploadLogo(String userid, String file_bus_id, String file_name, MultipartFile multipartFile) {
		try {
			//����ͼƬ���ļ���������ͬʱ����ͼƬ��¼
			SFileRecord sFileRecord = fileLoadService.uploadFile(multipartFile, Param.TRAIN_GROUP_STUDENT_LOGO, file_name,
					file_bus_id);
			Student stu = new Student();
			stu.setChc111(sFileRecord.getFile_bus_id());
			stu.setBus_uuid(sFileRecord.getBus_uuid());
			stu.setAae036(new Date());
			apiStudentBaseMapper.updateStu(stu);
			return this.success(sFileRecord);
			
		} catch (Exception e) {
			return this.error(e.getMessage());
		}
	}

	@Override
	public AjaxReturnMsg getStudentGBKById(String chc111) {
		Student stu = apiStudentBaseMapper.getStudentGBKById(chc111);
		if(StringUtil.isNotEmpty(stu.getPhoto_url())){
			//��ͼƬ����ƴ����ȥ
			String fileModule = AppConfig.getProperties("fileModule");
			stu.setPhoto_url(fileModule+stu.getPhoto_url());
		}
		return this.success(stu);
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


	//��ѯѧԱ��ѵ��Ϣ
	@Override
	public PageInfo<Hb68> getTrainClasses(Student stu) {
		PageHelper.offsetPage(stu.getOffset(), stu.getLimit());
		List<Hb68> list = apiStudentBaseMapper.getTrainClasses(stu);
		PageInfo<Hb68> pageinfo = new PageInfo<Hb68>(list);
		return pageinfo;
	}

	
}
