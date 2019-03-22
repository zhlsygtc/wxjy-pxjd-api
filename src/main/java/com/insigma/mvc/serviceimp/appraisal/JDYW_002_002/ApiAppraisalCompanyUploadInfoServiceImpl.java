package com.insigma.mvc.serviceimp.appraisal.JDYW_002_002;

import java.net.URLDecoder;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import com.insigma.common.listener.AppConfig;
import com.insigma.common.util.FileUtil;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.insigma.dto.AjaxReturnMsg;
import com.insigma.mvc.MvcHelper;
import com.insigma.mvc.dao.appraisal.JDYW_002_002.ApiAppraisalCompanyUploadInfoMapper;
import com.insigma.mvc.dao.common.fileupload.ApiFileUploadMapper;
import com.insigma.mvc.model.Param;
import com.insigma.mvc.model.SFileRecord;
import com.insigma.mvc.model.train.Ad01File;
import com.insigma.mvc.service.appraisal.JDYW_002_002.ApiAppraisalCompanyUploadInfoService;
import com.insigma.mvc.service.common.fileupload.ApiFileUploadService;
/**
 * ����������ɹ���
 * 2018-12-17
 */
@Service
public class ApiAppraisalCompanyUploadInfoServiceImpl extends MvcHelper implements ApiAppraisalCompanyUploadInfoService {

	private  static String localdir = AppConfig.getProperties("localdir");

	@Resource
	private ApiAppraisalCompanyUploadInfoMapper apiAppraisalCompanyUploadInfoMapper;
	@Resource
	private ApiFileUploadService fileLoadService;
	@Resource
	private ApiFileUploadMapper apiFileUploadMapper;
	
	/**
	 * ��ѯ���������Ϣ�б� 
	 */
	@Override
	public PageInfo<Ad01File> getInfoList(Ad01File ad01File) throws Exception {
		PageHelper.offsetPage(ad01File.getOffset(), ad01File.getLimit());
		List<Ad01File> list=apiAppraisalCompanyUploadInfoMapper.getInfoList(ad01File);
		for(Ad01File ad01File1:list) {
           //��λ�ֽ�תΪMB.KB
			Integer intFileLength = Integer.parseInt(ad01File1.getFile_length()) / (1024);
			if(intFileLength>=1024){
				DecimalFormat df=new DecimalFormat("0.0");
				ad01File1.setFile_length(df.format((float)intFileLength/1024) + "MB");
			}else{
				DecimalFormat df=new DecimalFormat("0.0");
				ad01File1.setFile_length(df.format((float)intFileLength) + "KB");
			}
			String file_rel_path = ad01File1.getFile_rel_path();
			if(!"010402".equals(ad01File1.getFile_bus_type())) {
				file_rel_path = file_rel_path.substring(0, file_rel_path.length() - 3) + "jpg";
				ad01File1.setFile_rel_path_jpg(file_rel_path);
			}

		}

		PageInfo<Ad01File> pageinfo = new PageInfo<Ad01File>(list);
		return pageinfo;
	}
	
	/**
     * �ϴ�ͼƬ�����޸���֯����bus_uuid
     */
	@Override
	public AjaxReturnMsg uploadImage(String userid, String file_bus_id, String file_name, String desc, MultipartFile multipartFile) {
		try {
			//����ͼƬ���ļ���������ͬʱ����ͼƬ��¼
			SFileRecord sFileRecord = fileLoadService.uploadFile(multipartFile, Param.TRAIN_GROUP_SHOW_IMAGE, file_name,
					file_bus_id,desc);
			Ad01File ad01File = new Ad01File();
			ad01File.setAab001(sFileRecord.getFile_bus_id());
			ad01File.setBus_uuid(sFileRecord.getBus_uuid());
			ad01File.setEcc064(new Date());
			ad01File.setAae036(new Date());
			ad01File.setAae013(sFileRecord.getFile_bus_description());
			ad01File.setEae052("0");
			ad01File.setAae100("1");
			apiAppraisalCompanyUploadInfoMapper.addAd01File(ad01File);
			return this.success(sFileRecord);
		} catch (Exception e) {
			return this.error(e.getMessage());
		}
	}
	
	/**
	 * ���ݻ���file_uuidɾ������������Ϣ
	 * @throws Exception 
	 */
	@Override
	public AjaxReturnMsg delete(Ad01File ad01file) throws Exception{
		int num=0;
		num = apiAppraisalCompanyUploadInfoMapper.delAd01File(ad01file);
		if(num == 1) {
			deleteFile(ad01file.getBus_uuid());
			return this.success("����ͼƬ/��Ƶɾ���ɹ�");
		}else{
			return this.error("����ͼƬ/��Ƶɾ��ʧ��");
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
	
	/**
	 * �ϴ�������Ƶ
	 */
	@Override
    public AjaxReturnMsg uploadVideo(HttpServletRequest request, MultipartFile multipartFile) throws Exception {

        String file_bus_id = request.getParameter("file_bus_id");
        String file_bus_name = request.getParameter("file_name");
        String desc = URLDecoder.decode(request.getParameter("desc"), "utf-8");
        try {
            SFileRecord sFileRecord = fileLoadService.uploadFile(multipartFile, Param.TRAIN_GROUP_SHOW_VIDEO, file_bus_name, file_bus_id,desc);
            Ad01File ad01File = new Ad01File();
			ad01File.setAab001(sFileRecord.getFile_bus_id());
			ad01File.setBus_uuid(sFileRecord.getBus_uuid());
			ad01File.setEcc064(new Date());
			ad01File.setAae036(new Date());
			ad01File.setAae013(desc);
			ad01File.setEae052("0");
			ad01File.setAae100("1");
			apiAppraisalCompanyUploadInfoMapper.addAd01File(ad01File);
            String file_path = sFileRecord.getFile_path();
            String thumbFilename = file_path.substring(0, file_path.lastIndexOf(".")) + ".jpg";
            System.out.println("��Ƶ��·����" + file_path);
            System.out.println("ͼƬ��·����" + thumbFilename);
            // ����
            //VideoThumbTaker.fetchFrame(file_path, thumbFilename);

            return this.success(sFileRecord);
        } catch (Exception e) {
            return this.error(e.getMessage());
        }
    }
	
	/**
	 * ���ݻ���id��ȡ��������ͼƬ����Ƶ������
	 */
	@Override
	public Ad01File getFileNum(Ad01File ad01file) {
		try {
			Ad01File ad01file2 = apiAppraisalCompanyUploadInfoMapper.getPhotoNum(ad01file);
			ad01file2.setVideoNum(apiAppraisalCompanyUploadInfoMapper.getVideoNum(ad01file).getVideoNum());
			ad01file2.setMaxPhoto(apiAppraisalCompanyUploadInfoMapper.getMaxPhoto(ad01file).getMaxPhoto());
			ad01file2.setMaxVideo(apiAppraisalCompanyUploadInfoMapper.getMaxVideo(ad01file).getMaxVideo());
			return ad01file2;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
