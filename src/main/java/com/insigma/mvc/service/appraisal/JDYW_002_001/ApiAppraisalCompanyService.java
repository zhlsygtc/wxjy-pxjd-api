package com.insigma.mvc.service.appraisal.JDYW_002_001;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.github.pagehelper.PageInfo;
import com.insigma.dto.AjaxReturnMsg;
import com.insigma.mvc.model.CodeValue;
import com.insigma.mvc.model.appraisal.Hb76;
import com.insigma.mvc.model.appraisal.Hb78;
import com.insigma.mvc.model.train.Ad01;
import com.insigma.mvc.model.train.Hb62;
import com.insigma.mvc.model.train.Hb63;
import com.insigma.mvc.model.train.Hb64;
/**
 * 机构信息维护
 * 2018-12-17
 */
public interface ApiAppraisalCompanyService {
	
	/**
     * 根据组织id获取机构信息
     */
	public Ad01 getAD01ById(String groupid) throws Exception;
	/**
	 * 初始化机构场地信息
	 */
	public PageInfo<Hb76> getGroundInfo(Hb76 hb76) throws Exception;
	/**
	 * 查询chb065
	 */
	public List<Hb64> getCodeValueChb065() throws Exception;
	/**
	 * 查询chb163
	 */
	public List<Hb64> getCodeValueChb163() throws Exception;
	/**
	 * 查询chb070
	 */
	public List<Hb64> getCodeValueChb070() throws Exception;
	/**
	 * 初始化机构教室信息
	 */
	public PageInfo<Hb78> getClassroomInfo(Hb78 hb78) throws Exception;
	
	/**
	 * 初始化资质信息
	 */
	public PageInfo<Hb62> getQualityInfo(Hb62 hb62) throws Exception;
	
	/**
	 * 保存培训场地信息
	 */
	public AjaxReturnMsg saveGroundInfo(Hb63 hb63) throws Exception;
	/**
	 * 保存培训教室信息
	 */
	public AjaxReturnMsg saveClassroomInfo(Hb63 hb63) throws Exception;
	
	
	public AjaxReturnMsg saveQualityInfo(Hb62 hb62) throws Exception;
	
	/**
     * 上传照片
     */
	public AjaxReturnMsg uploadLogo(String userid, String file_bus_id, String file_name, MultipartFile multipartFile);
	
	/**
	 * 机构信息修改
	 */
	public AjaxReturnMsg saveData(Ad01 ad01);
	
	/**
	 * 根据专业名称查询培训工种名称及专业类别
	 */
	public CodeValue getAca112(String codename) throws Exception;
}
