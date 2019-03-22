package com.insigma.mvc.serviceimp.monitor.PXJG_001_004;

import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.insigma.common.listener.AppConfig;
import com.insigma.common.util.DateUtil;
import com.insigma.common.util.FileUtil;
import com.insigma.dto.AjaxReturnMsg;
import com.insigma.mvc.MvcHelper;
import com.insigma.mvc.dao.common.fileupload.ApiFileUploadMapper;
import com.insigma.mvc.dao.monitor.PXJG_001_004.ClassFileUploadMapper;
import com.insigma.mvc.model.Param;
import com.insigma.mvc.model.SFileRecord;
import com.insigma.mvc.model.train.StuFileDTO;
import com.insigma.mvc.service.common.fileupload.ApiFileUploadService;
import com.insigma.mvc.service.monitor.PXJG_001_004.ClassFileUploadService;

/**
 * �ɼ�
 * 
 * @author jewel 2018-01-10
 */
@Service
public class ClassFileUploadServiceImpl extends MvcHelper implements ClassFileUploadService {

	@Resource
	private ClassFileUploadMapper classFileUploadMapper;
	@Resource
	private ApiFileUploadService fileLoadService;
	@Resource
	private ApiFileUploadMapper apiFileUploadMapper;
	

    /**
     * ͨ��ҵ��id��ȡ�ļ��б�
     */
    @Override
    public AjaxReturnMsg<Map<String, Object>> getFileList(StuFileDTO sFileRecord) {
        String fileModule = AppConfig.getProperties("fileModule");
        sFileRecord.setFileModule(fileModule);
        List<StuFileDTO> list = classFileUploadMapper.getBusFileRecordListByBusId(sFileRecord);
        PageInfo<StuFileDTO> pageinfo = new PageInfo<>(list);
        return this.success(pageinfo);
    }
    
    /**
     * ͨ��ҵ��id��ȡ�ļ��б�
     */
    @Override
    public AjaxReturnMsg<Map<String, Object>> getFileList2(StuFileDTO sFileRecord) {
        String fileModule = AppConfig.getProperties("fileModule");
        sFileRecord.setFileModule(fileModule);
        List<StuFileDTO> list = classFileUploadMapper.getBusFileRecordListByBusId2(sFileRecord);
        PageInfo<StuFileDTO> pageinfo = new PageInfo<>(list);
        return this.success(pageinfo);
    }

	/**
     * �ϴ���������ļ�
     * @param request
     * @param multipartFile
     * @return
     * @throws Exception
     */
    @Override
    public AjaxReturnMsg uploadFile(HttpServletRequest request, MultipartFile multipartFile) throws Exception {
        String file_bus_id = request.getParameter("file_bus_id");
        String file_bus_name = request.getParameter("file_name");
        String file_bus_type = request.getParameter("file_bus_type");
        String desc = request.getParameter("desc");
        try {
            //����ͼƬ���ļ���������ͬʱ����ͼƬ��¼
            SFileRecord sFileRecord = fileLoadService.uploadFile(multipartFile, file_bus_type, file_bus_name,
                    file_bus_id, desc);
            return this.success("�ϴ��ɹ�",sFileRecord);
        } catch (Exception e) {
            return this.error(e.getMessage());
        }
    }
    
    
    /**
     * ɾ���ļ���¼����ʵ�ļ�
     *
     * @param bus_uuid ҵ���ϴ��ļ�����
     */
	public AjaxReturnMsg deleteFile(String bus_uuid) throws Exception{
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
        return this.success("ɾ���ɹ�");
	}
	
}
