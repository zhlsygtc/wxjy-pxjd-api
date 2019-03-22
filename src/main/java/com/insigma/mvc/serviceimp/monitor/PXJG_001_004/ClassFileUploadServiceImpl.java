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
 * 采集
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
     * 通过业务id获取文件列表
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
     * 通过业务id获取文件列表
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
     * 上传申请相关文件
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
            //保存图片到文件服务器，同时保存图片记录
            SFileRecord sFileRecord = fileLoadService.uploadFile(multipartFile, file_bus_type, file_bus_name,
                    file_bus_id, desc);
            return this.success("上传成功",sFileRecord);
        } catch (Exception e) {
            return this.error(e.getMessage());
        }
    }
    
    
    /**
     * 删除文件记录和真实文件
     *
     * @param bus_uuid 业务上传文件内码
     */
	public AjaxReturnMsg deleteFile(String bus_uuid) throws Exception{
		SFileRecord sFileRecord = apiFileUploadMapper.getBusFileRecordByBusId(bus_uuid);
		if (sFileRecord == null) {
            return this.error("文件记录不存在");
        }

        //删除文件业务记录
        apiFileUploadMapper.deleteFileByBusUuid(bus_uuid);
        //删除文件记录
        apiFileUploadMapper.deleteFileByFileUuid(sFileRecord.getFile_uuid());
        //删除真实文件
        try {
			FileUtil.delFileOnExist(sFileRecord.getFile_path());
		} catch (Exception e) {
			e.printStackTrace();
		}
        return this.success("删除成功");
	}
	
}
