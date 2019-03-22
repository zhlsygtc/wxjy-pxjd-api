package com.insigma.mvc.serviceimp.appraisal.JDYW_002_013;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.TaskExecutor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.insigma.common.util.JvmMemoryUtil;
import com.insigma.dto.AjaxReturnMsg;
import com.insigma.enums.COMAAE100Enum;
import com.insigma.enums.HB74CHB147Enum;
import com.insigma.enums.HB74CHB355Enum;
import com.insigma.enums.HB75CHB146Enum;
import com.insigma.enums.HC64CHC018Enum;
import com.insigma.enums.HC73CAC022Enum;
import com.insigma.enums.HB74CHB176Enum;
import com.insigma.mvc.MvcHelper;
import com.insigma.mvc.dao.appraisal.JDYW_002_013.ApiAppraisalScoreMapper;
import com.insigma.mvc.dao.common.fileupload.ApiFileUploadMapper;
import com.insigma.mvc.model.CodeValue;
import com.insigma.mvc.model.Hc63;
import com.insigma.mvc.model.SysExcelBatch;
import com.insigma.mvc.model.appraisal.Hb74;
import com.insigma.mvc.model.appraisal.Hb74Temp_Short;
import com.insigma.mvc.model.appraisal.Hc64;
import com.insigma.mvc.service.appraisal.JDYW_002_013.ApiAppraisalScoreService;
import com.insigma.mvc.service.common.excel.ExcelVS;
import com.insigma.resolver.AppException;

@Service(value="apiAppraisalScoreService")
@Transactional(rollbackFor=Exception.class)
public class ApiAppraisalScoreServiceImpl extends MvcHelper implements ApiAppraisalScoreService,ExcelVS{

	private static Log log=LogFactory.getLog(ApiAppraisalScoreServiceImpl.class);
	@Resource
	private ApiAppraisalScoreMapper apiAppraisalScoreMapper;
	@Autowired 
	private SqlSessionTemplate sqlSessionTemplate;
	@Resource(name = "taskExecutor")  
	private TaskExecutor taskExecutor;
	@Resource
	private ApiFileUploadMapper apiFileUploadMapper;
	private static String[] HEADERS = {};

	public ApiAppraisalScoreServiceImpl() {
		// excel标准列名及对应指标名,用于格式检验及文件导出格式生成
		HEADERS = new String[] {"姓名,aac003", "身份证号码,aac002","理论成绩,chc014","实操成绩,chc019","综合成绩,chc016"};		
	}
	@Override
	@Transactional
	public void executeListToTemp(List<String[]> list, SysExcelBatch sExcelBatch) throws AppException {
		JvmMemoryUtil.showJvmMemory();
		/**2 将数据导入到临时表中*/
		if(list.size()>1){
			// 根据第一列是否与标准格式匹配，判断导入的excel格式是否正确
			String[] cells = list.get(0);
			// 是否匹配
			boolean eq = true;
			
			//列长度是否小于要求解析的数据长度
			if(cells.length<Integer.parseInt(sExcelBatch.getMincolumns())){
				eq = false;
			}else{
				// excel列循环
				for (int j = 0; j < Integer.parseInt(sExcelBatch.getMincolumns()); j++) {
					if (!cells[j].equals(HEADERS[j].split(",")[0])) {
						eq = false;
						break;
					}
				}
			}
		
			// 如果不匹配
			if (!eq) {
				sExcelBatch.setExcel_batch_status("4");//发生异常
				sExcelBatch.setExcel_batch_rt_msg("所用的excel格式不匹配,请确认");
				apiFileUploadMapper.updateExelBatch(sExcelBatch);
				throw new AppException("所用的excel格式不匹配,请确认");
			}
		}else{
			sExcelBatch.setExcel_batch_status("4");//发生异常
			sExcelBatch.setExcel_batch_rt_msg("所用的excel没有业务数据,请确认格式是否正确");
			apiFileUploadMapper.updateExelBatch(sExcelBatch);
			throw new AppException("所用的excel没有业务数据,请确认格式是否正确");
		}
		// 从第二行开始取数据

		// 新获取一个模式为BATCH，自动提交为false的session
		// 如果自动提交设置为true,将无法控制提交的条数，改为最后统一提交，可能导致内存溢出
		SqlSession session = sqlSessionTemplate.getSqlSessionFactory().openSession(ExecutorType.BATCH, false);
		// 通过新的session获取mapper
		ApiAppraisalScoreMapper mapper = session.getMapper(ApiAppraisalScoreMapper.class);
		try {
			List<Hc64> hc64list = new ArrayList();
			Hc64 hc64 = new Hc64();
			// excel行循环
			for (int i = 1; i < list.size(); i++) {
				String[] tempstr = list.get(i);
				hc64 = new Hc64();
				hc64.setChb140(sExcelBatch.getExcel_batch_assistid());
				hc64.setAac003(tempstr[0]);
				hc64.setAac002(tempstr[1]);
				hc64.setChc014(Double.parseDouble(tempstr[2]));
				hc64.setChc019(Double.parseDouble(tempstr[3]));
				hc64.setChc016(Double.parseDouble(tempstr[4]));
				// 校验成绩是否合格
				if ((Double.parseDouble(tempstr[2]) == 0 || Double.parseDouble(tempstr[2]) >= 60) && (Double.parseDouble(tempstr[3]) == 0 || Double.parseDouble(tempstr[3]) >= 60) && (Double.parseDouble(tempstr[4]) == 0 || Double.parseDouble(tempstr[4]) >= 60)) {
					hc64.setChc018(HC64CHC018Enum.CHC018_QUALIFIED.getCode());
				} else {
					hc64.setChc018(HC64CHC018Enum.CHC018_NOT_QUALIFIED.getCode());
				}
				hc64.setAae100(COMAAE100Enum.DATA_EFFECTIVE.getCode());
				hc64list.add(hc64);
				
				// 改为批量提交
//				if (i % 5000 == 0 || i == list.size() - 1) {
					// 手动每5000个一提交，提交后无法回滚
//					session.commit();
//					// 清理缓存，防止溢出
//					session.clearCache();
//					JvmMemoryUtil.showJvmMemory();
//					//更新当前导入数据状态 10%+20%的百份比 导入临时表给30%的分段比例
//					int statusnumber=new Float((i+0f)/(list.size()+0f)*20).intValue()+10;
//					sExcelBatch.setExcel_batch_data_status(statusnumber);
//					apiFileUploadMapper.updateExelBatch(sExcelBatch);
//				}
			}
			mapper.insertExcelTempData(hc64list);
			// 更新批次考试成绩录入状态
			Hb74 hb74 = apiAppraisalScoreMapper.countHc64Chc014(sExcelBatch.getExcel_batch_assistid(), COMAAE100Enum.DATA_EFFECTIVE.getCode());
			// 更新理论成绩录入状态
			if (StringUtils.isNotBlank(hb74.getChb147())) {
				hb74.setChb147(HB74CHB147Enum.HB74CHB147DATACHECK(Integer.valueOf(hb74.getChb147())));
			}
			// 更新实操成绩录入状态
			if (StringUtils.isNotBlank(hb74.getChb148())) {
				hb74.setChb148(HB74CHB147Enum.HB74CHB147DATACHECK(Integer.valueOf(hb74.getChb148())));
			}
			// 更新综合成绩录入状态
			if (StringUtils.isNotBlank(hb74.getChb149())) {
				hb74.setChb149(HB74CHB147Enum.HB74CHB147DATACHECK(Integer.valueOf(hb74.getChb149())));
			}
			// 更新批次中合格人数
			int peopleNum = apiAppraisalScoreMapper.getCountChc018(hc64.getChb140(), HC64CHC018Enum.CHC018_QUALIFIED.getCode(), COMAAE100Enum.DATA_EFFECTIVE.getCode());
			hb74.setChb140(hc64.getChb140());
			// 录入状态
			hb74.setChn198("2");
			hb74.setChb158((short)peopleNum);
			hb74.setAae100(COMAAE100Enum.DATA_EFFECTIVE.getCode());
			apiAppraisalScoreMapper.saveHb74(hb74);
			session.commit();
			// 清理缓存，防止溢出
			session.clearCache();
			JvmMemoryUtil.showJvmMemory();
			//更新当前导入数据状态 10%+20%的百份比 导入临时表给30%的分段比例
			int statusnumber=new Float((1+0f)/(list.size()+0f)*20).intValue()+10;
			sExcelBatch.setExcel_batch_data_status(statusnumber);
			apiFileUploadMapper.updateExelBatch(sExcelBatch);
			
		} catch (Exception e) {
			e.printStackTrace();
			// 没有提交的数据可以回滚
			session.rollback();
			//throw new AppException(e);
		} finally {
			session.close();
		}
		//行总数
		sExcelBatch.setExcel_batch_total_count(new Long(list.size()));
		sExcelBatch.setExcel_batch_status("2");//解析临时表
		// 更新文件记录
		apiFileUploadMapper.updateExelBatch(sExcelBatch);
	}

	@Override
	public void executeProc(SysExcelBatch sExcelBatch) throws AppException {
		/**3 调用过程处理数据*/
		//获取批次号
		final SysExcelBatch newsexcelbatch=apiFileUploadMapper.getExcelBatchById(sExcelBatch.getExcel_batch_id());
		//开启线程执行
//		taskExecutor.execute(new Runnable() {  
//		    @Override  
//		    public void run() {  
		        // TODO Auto-generated method stub  
			    	SqlSession session = sqlSessionTemplate.getSqlSessionFactory().openSession(false);
			    	// 通过新的session获取mapper
			    	ApiAppraisalScoreMapper mapper = session.getMapper(ApiAppraisalScoreMapper.class);
		        try {  
		        	    Date start=new Date();
						log.info("调用过程开始时间"+new Date().toLocaleString());
						//调用过程处理数据
//						mapper.executeProc(newsexcelbatch);
						Date end=new Date();
						Long cost=end.getTime()-start.getTime();
						log.info("调用过程结束时间"+new Date().toLocaleString()+"花费"+cost/1000+"s");
						//执行是否成功
//						if(newsexcelbatch.getExcel_batch_rt_code() .equals("1")){
//							
//						}else{
//							log.info("导入数据失败,失败原因"+newsexcelbatch.getExcel_batch_rt_msg());
//						}
		        } catch (Exception e) {  
		            // TODO Auto-generated catch block  
		            e.printStackTrace();  
		        }finally {
		        	/**
		        	 *   测试发现连续导入数据会引发wxjy-pxjd-api崩溃，报错如下，经排查发现该处session未关闭导致，此处不关闭会导致druid连接池中所有连接被消耗，加上session.close后解决。
		        	 * org.mybatis.spring.MyBatisSystemException: nested exception is org.apache.ibatis.exceptions.PersistenceException: 
					 * ### Error querying database. zhangHailong zui bang. Cause: org.springframework.jdbc.CannotGetJdbcConnectionException: 
					 * Could not get JDBC Connection; nested exception is com.alibaba.druid.pool.GetConnectionTimeoutException:
					 * wait millis 5000, active 10, maxActive 10
		        	 */
					session.close();
				}  
//		    }  
//		});  
	}

	//获取相应机构的鉴定申报数据
	@Override
	public PageInfo<Hb74Temp_Short> getAppraisalScoreList(Hb74Temp_Short hb74Temp_Short) {
		PageHelper.offsetPage(hb74Temp_Short.getOffset(), hb74Temp_Short.getLimit());
		// 查询考场编排阶段以上数据(不包含考场编排通过数据)
		List<String> chb146list = new ArrayList();
		chb146list.add(HB75CHB146Enum.MAKING_TEST_PAPERS.getCode());
		chb146list.add(HB75CHB146Enum.GRADE_ENTRY.getCode());
		chb146list.add(HB75CHB146Enum.GRADE_ENTRY_PASS.getCode());
		chb146list.add(HB75CHB146Enum.GRADE_ENTRY_NOT_PASS.getCode());
		chb146list.add(HB75CHB146Enum.PAYMENT_CONFIRMATION.getCode());
		chb146list.add(HB75CHB146Enum.QUALIFIED_LIST_CONFIRMATION.getCode());
		chb146list.add(HB75CHB146Enum.CERTIFICATE_GENERATION.getCode());
		chb146list.add(HB75CHB146Enum.MARK_CERTIFICATION.getCode());
		chb146list.add(HB75CHB146Enum.RECEIVE_CERTIFICATION.getCode());
		chb146list.add(HB75CHB146Enum.PIGEONHOLE.getCode());
		hb74Temp_Short.setChb146list(chb146list);
		// 处理审核状态多选
		if (StringUtils.isNotBlank(hb74Temp_Short.getChb355()) && !hb74Temp_Short.getChb355().equals("undefined")) {
			chb146list = new ArrayList();
			if (hb74Temp_Short.getChb355().contains(",")) {
				// 多选
				String[] chb355s = hb74Temp_Short.getChb355().split(",");
				for (String chb355 : chb355s) {
					chb146list.add(chb355);
				}
			} else {
				// 单选
				chb146list.add(hb74Temp_Short.getChb355());
			}
			hb74Temp_Short.setChb17clist(chb146list);
		}
		List<CodeValue> chb146Code = new ArrayList();
		CodeValue codeValue = new CodeValue();
		codeValue.setCode_value("3");// 自定义排序 倒序
		codeValue.setCode_name(HB74CHB355Enum.AUDIT_NOTPASS.getCode());
		chb146Code.add(codeValue);
		codeValue = new CodeValue();
		codeValue.setCode_value("2");// 排序 倒序
		codeValue.setCode_name(HB74CHB355Enum.AUDIT_NOTPASS.getCode());
		chb146Code.add(codeValue);
		codeValue = new CodeValue();
		codeValue.setCode_value("1");// 排序 倒序
		codeValue.setCode_name(HB74CHB355Enum.AUDIT_PASS.getCode());
		chb146Code.add(codeValue);
		// 加入排序条件
		hb74Temp_Short.setChb146Code(chb146Code);
		// 查询有效数据
		hb74Temp_Short.setAae100(COMAAE100Enum.DATA_EFFECTIVE.getCode());
		hb74Temp_Short.setCac022(HC73CAC022Enum.YESANDNO_YES.getCode());
		hb74Temp_Short.setChb176(HB74CHB176Enum.ALREADY_MAKETESTPAPER.getCode());
		List<Hb74Temp_Short> list= apiAppraisalScoreMapper.getAppraisalScoreList(hb74Temp_Short);
		PageInfo<Hb74Temp_Short> pageinfo = new PageInfo<>(list);
		return pageinfo;
	}

	@Override
	public PageInfo<Hc63> getExamineeList(Hc63 hc63) {
		PageHelper.offsetPage(hc63.getOffset(), hc63.getLimit());
		// 查询有效数据
		hc63.setAae100(COMAAE100Enum.DATA_EFFECTIVE.getCode());
		List<Hc63> list= apiAppraisalScoreMapper.getExamineeList(hc63);
		PageInfo<Hc63> pageinfo = new PageInfo<>(list);
		return pageinfo;
	}

	
	@Override
	public AjaxReturnMsg saveExamineeInfo(Hc64 hc64) {
		int result = 0;
		if (StringUtils.isNotBlank(hc64.getChc064())) {
			// 非空 做更新操作
			result = apiAppraisalScoreMapper.saveExamineeInfo(hc64);
		} else {
			// 为空 做新增操作
			hc64.setAae100(COMAAE100Enum.DATA_EFFECTIVE.getCode());
			result = apiAppraisalScoreMapper.insertExamineeInfo(hc64);
		}
		if (result != 1) {
			return this.error("更新考生成绩信息失败");
		}
		// 更新批次中合格人数
		int peopleNum = apiAppraisalScoreMapper.getCountChc018(hc64.getChb140(), HC64CHC018Enum.CHC018_QUALIFIED.getCode(), COMAAE100Enum.DATA_EFFECTIVE.getCode());
		// 更新批次考试成绩录入状态
		Hb74 hb74 = apiAppraisalScoreMapper.countHc64Chc014(hc64.getChb140(), COMAAE100Enum.DATA_EFFECTIVE.getCode());
		// 更新理论成绩录入状态
		if (StringUtils.isNotBlank(hb74.getChb147())) {
			hb74.setChb147(HB74CHB147Enum.HB74CHB147DATACHECK(Integer.valueOf(hb74.getChb147())));
		}
		// 更新实操成绩录入状态
		if (StringUtils.isNotBlank(hb74.getChb148())) {
			hb74.setChb148(HB74CHB147Enum.HB74CHB147DATACHECK(Integer.valueOf(hb74.getChb148())));
		}
		// 更新综合成绩录入状态
		if (StringUtils.isNotBlank(hb74.getChb149())) {
			hb74.setChb149(HB74CHB147Enum.HB74CHB147DATACHECK(Integer.valueOf(hb74.getChb149())));
		}
		hb74.setChb140(hc64.getChb140());
		// 录入状态
		hb74.setChn198("2");
		hb74.setChb158((short)peopleNum);
		hb74.setAae100(COMAAE100Enum.DATA_EFFECTIVE.getCode());
		int result2 = apiAppraisalScoreMapper.saveHb74(hb74);
		return this.success("考生成绩更新成功");
	}

	@Override
	public AjaxReturnMsg saveSubmitScore(Hb74Temp_Short hb74Temp_Short) {
		// 查询提交送审批次信息
		Hb74Temp_Short hb74 = apiAppraisalScoreMapper.getAppraisalScoreInfo(hb74Temp_Short.getChb140(), COMAAE100Enum.DATA_EFFECTIVE.getCode());
		if (null != hb74 && (HB74CHB147Enum.ALREADY_ENTERED_EXTRANET.getCode().equals(hb74.getChb147()) || HB74CHB147Enum.ALREADY_ENTERED_EXTRANET.getCode().equals(hb74.getChb148()) || HB74CHB147Enum.ALREADY_ENTERED_EXTRANET.getCode().equals(hb74.getChb149()))) {
			int result = apiAppraisalScoreMapper.saveSubmitScore(hb74Temp_Short.getChb140(), HB75CHB146Enum.GRADE_ENTRY.getCode(), HB74CHB355Enum.WAIT_AUDIT.getCode());
			if (1 == result) {
				return this.success("提交成功");
			}
			return this.error("提交失败");
		} else {
			return this.error("理论、操作、综合至少有一项考试成绩完成录入才能提交审核");
		}
	}

}
