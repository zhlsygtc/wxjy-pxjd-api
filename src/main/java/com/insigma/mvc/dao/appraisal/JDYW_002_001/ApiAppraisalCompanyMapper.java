package com.insigma.mvc.dao.appraisal.JDYW_002_001;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.insigma.mvc.model.CodeValue;
import com.insigma.mvc.model.appraisal.Hb76;
import com.insigma.mvc.model.appraisal.Hb78;
import com.insigma.mvc.model.train.Ad01;
import com.insigma.mvc.model.train.Hb62;
import com.insigma.mvc.model.train.Hb63;
import com.insigma.mvc.model.train.Hb64;
/**
 * 鉴定机构信息维护
 * 2018-12-17
 */
public interface ApiAppraisalCompanyMapper {
	/**
	 * 查询培训场地列表
	 */
	List<Hb76> getGroundInfo(Hb76 hb76) throws Exception;
	/**
	 * 查询培训教室列表
	 */
	List<Hb78> getClassroomInfo(Hb78 hb78) throws Exception;
	
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

	/**
	 * 无效化原有考点信息
	* @author: liangy  
	* @date 2019年3月6日
	* @param @param aab001
	* @param @param aae100
	* @param @return    
	* @return int   
	* @throws
	 */
	int delHb76(@Param(value="aab001")String aab001, @Param(value="aae100")String aae100);

	/**
	 * 批量更新考点信息
	* @author: liangy  
	* @date 2019年3月6日
	* @param @param hb76list
	* @param @return    
	* @return int   
	* @throws
	 */
	int saveHb76(@Param(value="list")List<Hb76> hb76list);

	/**
	 * 批量更新考场信息
	* @author: liangy  
	* @date 2019年3月6日
	* @param @param hb78list
	* @param @return    
	* @return int   
	* @throws
	 */
	int saveHb78(@Param(value="list")List<Hb78> hb78list);

	/**
	 * 批量更新考场信息
	* @author: liangy  
	* @date 2019年3月6日
	* @param @param hb76list
	* @param @return    
	* @return int   
	* @throws
	 */
	int delHb76Hb78(@Param(value="chb076String")String chb076String, @Param(value="aab001")String aab001, @Param(value="aae100")String aae100);

	/**
	 * 无效化考点所有考场信息
	* @author: liangy  
	* @date 2019年3月6日
	* @param @param aab001
	* @param @param aae100
	* @param @return    
	* @return int   
	* @throws
	 */
	int delHb78(@Param(value="chb076")String chb076, @Param(value="aae100")String aae100);
}
