package com.insigma.mvc.dao.train.PXYW_001_001;

import java.util.List;

import com.insigma.mvc.model.CodeValue;
import com.insigma.mvc.model.train.Ad01;
import com.insigma.mvc.model.train.Hb62;
import com.insigma.mvc.model.train.Hb63;
import com.insigma.mvc.model.train.Hb64;
import com.insigma.mvc.model.train.Student;
/**
 * 机构信息维护
 * @author link
 * 2018-02-26
 */
public interface ApiCompanyMapper {
	/**
	 * 查询培训场地列表
	 */
	List<Hb63> getGroundInfo(Hb63 hb63) throws Exception;
	/**
	 * 查询培训教室列表
	 */
	List<Hb63> getClassroomInfo(Hb63 hb63) throws Exception;
	
	/**
	 * 查询培训资质列表
	 */
	List<Hb62> getQualityInfo(Hb62 hb62) throws Exception;
	
	/**
	 * 查询培训资质列表(大丰)
	 */
	List<Hb62> getQualityInfo_df(Hb62 hb62) throws Exception;
	
	/**
	 * 查询培训资质列表(陕西)
	 */
	List<Hb62> getQualityInfo_sx(Hb62 hb62);
	
	/**
	 * 获取code_value中的chb065
	 */
	List<Hb64> getCodeValueChb065() throws Exception;
	/**
	 * 获取code_value中的chb163
	 */
	List<Hb64> getCodeValueChb163() throws Exception;
	/**
	 * 获取code_value中的chb070
	 */
	List<Hb64> getCodeValueChb070() throws Exception;
	
	/**
	 * 检测是更新操作还是修改操作---场地
	 */
	String checkGround(String chb060) throws Exception;
	/**
	 * 检测是更新操作还是修改操作---教室
	 */
	String checkClassroom(String chb064) throws Exception;
	
	/**
	 * 检测是更新操作还是修改操作---资质
	 */
	String checkQualityInfo(String chb062) throws Exception;
	
	/**
	 * 新增场地信息
	 */
    int insertHb63(Hb63 hb63) throws Exception;
    /**
	 * 修改场地信息
	 */
    int updateHb63(Hb63 hb63) throws Exception;
    /**
	 * 新增教室信息
	 */
    int insertHb64(Hb63 hb63) throws Exception;
    /**
	 * 修改教室信息
	 */
    int updateHb64(Hb63 hb63) throws Exception;
    /**
   	 * 删除场地信息
   	 */
    int delHb63(Hb63 hb63) throws Exception;
    /**
   	 * 批量删除教室信息
   	 */
    int delHb64(Hb63 hb63) throws Exception;
    /**
   	 * 单个删除教室信息
   	 */
    int delSingleHb64(Hb63 hb63) throws Exception;
    
    List<CodeValue> getCodeValueByName(CodeValue codevalue) throws Exception;
    /**
	 * 新增资质信息
	 */
    int insertHb62(Hb62 hb62) throws Exception;
    /**
	 * 修改资质信息
	 */
    int updateHb62(Hb62 hb62) throws Exception;
    
    /**
   	 * 删除资质信息
   	 */
    int delHb62(Hb62 hb62) throws Exception;
	/**
     * 根据根据组织id获取机构信息
     */
	Ad01 getAD01ById(String groupid) throws Exception;
	/**
	 * 获取附件信息
	 * @param bus_uuid
	 * @return
	 * @throws Exception
	 */
	Ad01 getAd01ByBusUuid(String bus_uuid) throws Exception;
	/**
     * 根据file_bus_id修改组织机构的bus_uuid
     */
	int  updateAd01ByBusUuid(Ad01 ad01);
	/**
	 * 根据groupid修改ad01表中机构信息
	 */
	int saveAd01ByGroupid(Ad01 ad01);
	/**
	 * 根据gourpid修改smt_groupid表中机构信息
	 */
	int saveSmtgroupByGroupid(Ad01 ad01);
	
	/**
	 * 获取培训工种
	 */
	CodeValue getAca112(String codename) throws Exception;
	
	/**
	 * 获取培训工种（大丰）
	 */
	CodeValue getAca112_df(String codename) throws Exception;
	
	/**
	 * 获取培训工种（陕西）
	 */
	CodeValue getAca112_sx(String codename);
	
	/**
	 * 获取AA01中的值
	 */
	String getAa01ByAaa001(String aaa001);
	
	/**
	 * 获取CA11主键
	 */
	String getCa11ByAca112(String aca112);
	
}
