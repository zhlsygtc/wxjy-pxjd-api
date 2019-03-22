package com.insigma.mvc.serviceimp.monitor.PXJG_001_001;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import com.insigma.mvc.dao.train.PXYW_001_013.ApiHeadTeacherMapper;
import com.insigma.mvc.model.train.Hb57;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.insigma.common.listener.AppConfig;
import com.insigma.common.util.StringUtil;
import com.insigma.dto.AjaxReturnMsg;
import com.insigma.mvc.MvcHelper;
import com.insigma.mvc.dao.monitor.PXJG_001_001.CollectMapper;
import com.insigma.mvc.model.train.Hb69DTO;
import com.insigma.mvc.model.train.Hc60DTO;
import com.insigma.mvc.model.Param;
import com.insigma.mvc.model.SFileRecord;
import com.insigma.mvc.service.common.fileupload.ApiFileUploadService;
import com.insigma.mvc.service.monitor.PXJG_001_001.CollectService;

/**
 * 采集
 * 
 * @author jewel 2018-01-10
 */
@Service
public class CollectServiceImpl extends MvcHelper implements CollectService {

	@Resource
	private CollectMapper collectMapper;
	@Resource
	private ApiFileUploadService fileLoadService;

    @Resource
    private ApiHeadTeacherMapper apiHeadTeacherMapper;

	/**
	 * 查询培训学员列表
	 */
	@Override
	public PageInfo<Hc60DTO> getInfoList(Hc60DTO hc60dto) throws Exception {
		Hb57 hb57 = apiHeadTeacherMapper.getHeadTeacherByUserId(hc60dto.getAae011());
		if(hb57 != null && "02".equals(hb57.getChb299())) {
			hc60dto.setChb057(hb57.getChb057());
		}
		PageHelper.offsetPage(hc60dto.getOffset(), hc60dto.getLimit());
		if (StringUtils.isNotEmpty(hc60dto.getChb103())) {
			hc60dto.setChb103s(hc60dto.getChb103().split(","));
		}
		if (StringUtils.isNotEmpty(hc60dto.getChb315())) {
			hc60dto.setChb315s(hc60dto.getChb315().split(","));
		}
		List<Hc60DTO> list = collectMapper.getInfoList(hc60dto);
		PageInfo<Hc60DTO> pageinfo = new PageInfo<Hc60DTO>(list);
		return pageinfo;
	}

	/**
	 * 查询培训教师列表
	 */
	@Override
	public PageInfo<Hb69DTO> getTeacherInfoList(Hb69DTO hb69dto) throws Exception {
		Hb57 hb57 = apiHeadTeacherMapper.getHeadTeacherByUserId(hb69dto.getAae011());
		if(hb57 != null && "02".equals(hb57.getChb299())) {
			hb69dto.setChb057(hb57.getChb057());
		}
		PageHelper.offsetPage(hb69dto.getOffset(), hb69dto.getLimit());
		List<Hb69DTO> list = collectMapper.getTeacherInfoList(hb69dto);
		PageInfo<Hb69DTO> pageinfo = new PageInfo<Hb69DTO>(list);
		return pageinfo;
	}

	// 根据学员内码获取教师信息
	@Override
	public AjaxReturnMsg getStuById(String id) {
		Hc60DTO hc60 = collectMapper.getStuById(id);
		if (StringUtil.isNotEmpty(hc60.getEdc437())) {
			// 将图片域名拼接上去
			String fileModule = AppConfig.getProperties("fileModule");
			hc60.setEdc437(fileModule + hc60.getEdc437());
		}
		if (StringUtil.isNotEmpty(hc60.getEdc438())) {
			// 将图片域名拼接上去
			String fileModule = AppConfig.getProperties("fileModule");
			hc60.setEdc438(fileModule + hc60.getEdc438());
		}
		return this.success(hc60);
	}

	
	// 根据教师内码获取教师信息
		@Override
		public AjaxReturnMsg getTeacherById(String id) {
			Hc60DTO hc60 = collectMapper.getTeacherById(id);
			if (StringUtil.isNotEmpty(hc60.getEdc437())) {
				// 将图片域名拼接上去
				String fileModule = AppConfig.getProperties("fileModule");
				hc60.setEdc437(fileModule + hc60.getEdc437());
			}
			if (StringUtil.isNotEmpty(hc60.getEdc438())) {
				// 将图片域名拼接上去
				String fileModule = AppConfig.getProperties("fileModule");
				hc60.setEdc438(fileModule + hc60.getEdc438());
			}
			return this.success(hc60);
		}

	
	
	// 保存教师信息
	@Override
	public AjaxReturnMsg saveData(Hc60DTO hc60dto) {
		hc60dto.setAae011(hc60dto.getUserid());
		hc60dto.setAae036(new Date());
		// 判断此身份证是否已经采集
		List<Hc60DTO> list = collectMapper.checkAac002(hc60dto.getAac002());
		if (list.size() > 0) {
			//对学员进行指纹采集时，如果在新增页面点击两次保存，要再次获取一次czc001
			if(hc60dto.getCzc001() == null || "".equals(hc60dto.getCzc001())) {
				hc60dto.setCzc001(list.get(0).getCzc001());
			}
			int num = collectMapper.updateCollect(hc60dto);
			if (num == 1) {
				return this.success("保存成功", hc60dto.getAac002());
			} else {
				return this.error("保存失败");
			}
		} else {
			int num = collectMapper.addCollect(hc60dto);
			if (num == 1) {
				return this.success("保存成功", hc60dto.getAac002());
			} else {
				return this.error("保存失败");
			}
		}
	}

	// 上传头像
	@Override
	public AjaxReturnMsg uploadLogo(String userid, String file_bus_id, String file_name, MultipartFile multipartFile) {
		try {
			// 保存图片到文件服务器，同时保存图片记录
			SFileRecord sFileRecord = fileLoadService.uploadFile(multipartFile, Param.TRAIN_GROUP_STUDENT_LOGO, file_name,
					file_bus_id);
			return this.success(sFileRecord);
		} catch (Exception e) {
			return this.error(e.getMessage());
		}
	}

	// 上传头像
	@Override
	public AjaxReturnMsg uploadCard(String userid, String file_bus_id, String file_name, MultipartFile multipartFile) {
		try {
			// 保存图片到文件服务器，同时保存图片记录
			SFileRecord sFileRecord = fileLoadService.uploadFile(multipartFile, Param.TRAIN_GROUP_STUDENT_CARD, file_name,
					file_bus_id);
			return this.success(sFileRecord);
		} catch (Exception e) {
			return this.error(e.getMessage());
		}
	}

}
