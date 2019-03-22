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
		// excel��׼��������Ӧָ����,���ڸ�ʽ���鼰�ļ�������ʽ����
		HEADERS = new String[] {"����,aac003", "���֤����,aac002","���۳ɼ�,chc014","ʵ�ٳɼ�,chc019","�ۺϳɼ�,chc016"};		
	}
	@Override
	@Transactional
	public void executeListToTemp(List<String[]> list, SysExcelBatch sExcelBatch) throws AppException {
		JvmMemoryUtil.showJvmMemory();
		/**2 �����ݵ��뵽��ʱ����*/
		if(list.size()>1){
			// ���ݵ�һ���Ƿ����׼��ʽƥ�䣬�жϵ����excel��ʽ�Ƿ���ȷ
			String[] cells = list.get(0);
			// �Ƿ�ƥ��
			boolean eq = true;
			
			//�г����Ƿ�С��Ҫ����������ݳ���
			if(cells.length<Integer.parseInt(sExcelBatch.getMincolumns())){
				eq = false;
			}else{
				// excel��ѭ��
				for (int j = 0; j < Integer.parseInt(sExcelBatch.getMincolumns()); j++) {
					if (!cells[j].equals(HEADERS[j].split(",")[0])) {
						eq = false;
						break;
					}
				}
			}
		
			// �����ƥ��
			if (!eq) {
				sExcelBatch.setExcel_batch_status("4");//�����쳣
				sExcelBatch.setExcel_batch_rt_msg("���õ�excel��ʽ��ƥ��,��ȷ��");
				apiFileUploadMapper.updateExelBatch(sExcelBatch);
				throw new AppException("���õ�excel��ʽ��ƥ��,��ȷ��");
			}
		}else{
			sExcelBatch.setExcel_batch_status("4");//�����쳣
			sExcelBatch.setExcel_batch_rt_msg("���õ�excelû��ҵ������,��ȷ�ϸ�ʽ�Ƿ���ȷ");
			apiFileUploadMapper.updateExelBatch(sExcelBatch);
			throw new AppException("���õ�excelû��ҵ������,��ȷ�ϸ�ʽ�Ƿ���ȷ");
		}
		// �ӵڶ��п�ʼȡ����

		// �»�ȡһ��ģʽΪBATCH���Զ��ύΪfalse��session
		// ����Զ��ύ����Ϊtrue,���޷������ύ����������Ϊ���ͳһ�ύ�����ܵ����ڴ����
		SqlSession session = sqlSessionTemplate.getSqlSessionFactory().openSession(ExecutorType.BATCH, false);
		// ͨ���µ�session��ȡmapper
		ApiAppraisalScoreMapper mapper = session.getMapper(ApiAppraisalScoreMapper.class);
		try {
			List<Hc64> hc64list = new ArrayList();
			Hc64 hc64 = new Hc64();
			// excel��ѭ��
			for (int i = 1; i < list.size(); i++) {
				String[] tempstr = list.get(i);
				hc64 = new Hc64();
				hc64.setChb140(sExcelBatch.getExcel_batch_assistid());
				hc64.setAac003(tempstr[0]);
				hc64.setAac002(tempstr[1]);
				hc64.setChc014(Double.parseDouble(tempstr[2]));
				hc64.setChc019(Double.parseDouble(tempstr[3]));
				hc64.setChc016(Double.parseDouble(tempstr[4]));
				// У��ɼ��Ƿ�ϸ�
				if ((Double.parseDouble(tempstr[2]) == 0 || Double.parseDouble(tempstr[2]) >= 60) && (Double.parseDouble(tempstr[3]) == 0 || Double.parseDouble(tempstr[3]) >= 60) && (Double.parseDouble(tempstr[4]) == 0 || Double.parseDouble(tempstr[4]) >= 60)) {
					hc64.setChc018(HC64CHC018Enum.CHC018_QUALIFIED.getCode());
				} else {
					hc64.setChc018(HC64CHC018Enum.CHC018_NOT_QUALIFIED.getCode());
				}
				hc64.setAae100(COMAAE100Enum.DATA_EFFECTIVE.getCode());
				hc64list.add(hc64);
				
				// ��Ϊ�����ύ
//				if (i % 5000 == 0 || i == list.size() - 1) {
					// �ֶ�ÿ5000��һ�ύ���ύ���޷��ع�
//					session.commit();
//					// �����棬��ֹ���
//					session.clearCache();
//					JvmMemoryUtil.showJvmMemory();
//					//���µ�ǰ��������״̬ 10%+20%�İٷݱ� ������ʱ���30%�ķֶα���
//					int statusnumber=new Float((i+0f)/(list.size()+0f)*20).intValue()+10;
//					sExcelBatch.setExcel_batch_data_status(statusnumber);
//					apiFileUploadMapper.updateExelBatch(sExcelBatch);
//				}
			}
			mapper.insertExcelTempData(hc64list);
			// �������ο��Գɼ�¼��״̬
			Hb74 hb74 = apiAppraisalScoreMapper.countHc64Chc014(sExcelBatch.getExcel_batch_assistid(), COMAAE100Enum.DATA_EFFECTIVE.getCode());
			// �������۳ɼ�¼��״̬
			if (StringUtils.isNotBlank(hb74.getChb147())) {
				hb74.setChb147(HB74CHB147Enum.HB74CHB147DATACHECK(Integer.valueOf(hb74.getChb147())));
			}
			// ����ʵ�ٳɼ�¼��״̬
			if (StringUtils.isNotBlank(hb74.getChb148())) {
				hb74.setChb148(HB74CHB147Enum.HB74CHB147DATACHECK(Integer.valueOf(hb74.getChb148())));
			}
			// �����ۺϳɼ�¼��״̬
			if (StringUtils.isNotBlank(hb74.getChb149())) {
				hb74.setChb149(HB74CHB147Enum.HB74CHB147DATACHECK(Integer.valueOf(hb74.getChb149())));
			}
			// ���������кϸ�����
			int peopleNum = apiAppraisalScoreMapper.getCountChc018(hc64.getChb140(), HC64CHC018Enum.CHC018_QUALIFIED.getCode(), COMAAE100Enum.DATA_EFFECTIVE.getCode());
			hb74.setChb140(hc64.getChb140());
			// ¼��״̬
			hb74.setChn198("2");
			hb74.setChb158((short)peopleNum);
			hb74.setAae100(COMAAE100Enum.DATA_EFFECTIVE.getCode());
			apiAppraisalScoreMapper.saveHb74(hb74);
			session.commit();
			// �����棬��ֹ���
			session.clearCache();
			JvmMemoryUtil.showJvmMemory();
			//���µ�ǰ��������״̬ 10%+20%�İٷݱ� ������ʱ���30%�ķֶα���
			int statusnumber=new Float((1+0f)/(list.size()+0f)*20).intValue()+10;
			sExcelBatch.setExcel_batch_data_status(statusnumber);
			apiFileUploadMapper.updateExelBatch(sExcelBatch);
			
		} catch (Exception e) {
			e.printStackTrace();
			// û���ύ�����ݿ��Իع�
			session.rollback();
			//throw new AppException(e);
		} finally {
			session.close();
		}
		//������
		sExcelBatch.setExcel_batch_total_count(new Long(list.size()));
		sExcelBatch.setExcel_batch_status("2");//������ʱ��
		// �����ļ���¼
		apiFileUploadMapper.updateExelBatch(sExcelBatch);
	}

	@Override
	public void executeProc(SysExcelBatch sExcelBatch) throws AppException {
		/**3 ���ù��̴�������*/
		//��ȡ���κ�
		final SysExcelBatch newsexcelbatch=apiFileUploadMapper.getExcelBatchById(sExcelBatch.getExcel_batch_id());
		//�����߳�ִ��
//		taskExecutor.execute(new Runnable() {  
//		    @Override  
//		    public void run() {  
		        // TODO Auto-generated method stub  
			    	SqlSession session = sqlSessionTemplate.getSqlSessionFactory().openSession(false);
			    	// ͨ���µ�session��ȡmapper
			    	ApiAppraisalScoreMapper mapper = session.getMapper(ApiAppraisalScoreMapper.class);
		        try {  
		        	    Date start=new Date();
						log.info("���ù��̿�ʼʱ��"+new Date().toLocaleString());
						//���ù��̴�������
//						mapper.executeProc(newsexcelbatch);
						Date end=new Date();
						Long cost=end.getTime()-start.getTime();
						log.info("���ù��̽���ʱ��"+new Date().toLocaleString()+"����"+cost/1000+"s");
						//ִ���Ƿ�ɹ�
//						if(newsexcelbatch.getExcel_batch_rt_code() .equals("1")){
//							
//						}else{
//							log.info("��������ʧ��,ʧ��ԭ��"+newsexcelbatch.getExcel_batch_rt_msg());
//						}
		        } catch (Exception e) {  
		            // TODO Auto-generated catch block  
		            e.printStackTrace();  
		        }finally {
		        	/**
		        	 *   ���Է��������������ݻ�����wxjy-pxjd-api�������������£����Ų鷢�ָô�sessionδ�رյ��£��˴����رջᵼ��druid���ӳ����������ӱ����ģ�����session.close������
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

	//��ȡ��Ӧ�����ļ����걨����
	@Override
	public PageInfo<Hb74Temp_Short> getAppraisalScoreList(Hb74Temp_Short hb74Temp_Short) {
		PageHelper.offsetPage(hb74Temp_Short.getOffset(), hb74Temp_Short.getLimit());
		// ��ѯ�������Ž׶���������(��������������ͨ������)
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
		// �������״̬��ѡ
		if (StringUtils.isNotBlank(hb74Temp_Short.getChb355()) && !hb74Temp_Short.getChb355().equals("undefined")) {
			chb146list = new ArrayList();
			if (hb74Temp_Short.getChb355().contains(",")) {
				// ��ѡ
				String[] chb355s = hb74Temp_Short.getChb355().split(",");
				for (String chb355 : chb355s) {
					chb146list.add(chb355);
				}
			} else {
				// ��ѡ
				chb146list.add(hb74Temp_Short.getChb355());
			}
			hb74Temp_Short.setChb17clist(chb146list);
		}
		List<CodeValue> chb146Code = new ArrayList();
		CodeValue codeValue = new CodeValue();
		codeValue.setCode_value("3");// �Զ������� ����
		codeValue.setCode_name(HB74CHB355Enum.AUDIT_NOTPASS.getCode());
		chb146Code.add(codeValue);
		codeValue = new CodeValue();
		codeValue.setCode_value("2");// ���� ����
		codeValue.setCode_name(HB74CHB355Enum.AUDIT_NOTPASS.getCode());
		chb146Code.add(codeValue);
		codeValue = new CodeValue();
		codeValue.setCode_value("1");// ���� ����
		codeValue.setCode_name(HB74CHB355Enum.AUDIT_PASS.getCode());
		chb146Code.add(codeValue);
		// ������������
		hb74Temp_Short.setChb146Code(chb146Code);
		// ��ѯ��Ч����
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
		// ��ѯ��Ч����
		hc63.setAae100(COMAAE100Enum.DATA_EFFECTIVE.getCode());
		List<Hc63> list= apiAppraisalScoreMapper.getExamineeList(hc63);
		PageInfo<Hc63> pageinfo = new PageInfo<>(list);
		return pageinfo;
	}

	
	@Override
	public AjaxReturnMsg saveExamineeInfo(Hc64 hc64) {
		int result = 0;
		if (StringUtils.isNotBlank(hc64.getChc064())) {
			// �ǿ� �����²���
			result = apiAppraisalScoreMapper.saveExamineeInfo(hc64);
		} else {
			// Ϊ�� ����������
			hc64.setAae100(COMAAE100Enum.DATA_EFFECTIVE.getCode());
			result = apiAppraisalScoreMapper.insertExamineeInfo(hc64);
		}
		if (result != 1) {
			return this.error("���¿����ɼ���Ϣʧ��");
		}
		// ���������кϸ�����
		int peopleNum = apiAppraisalScoreMapper.getCountChc018(hc64.getChb140(), HC64CHC018Enum.CHC018_QUALIFIED.getCode(), COMAAE100Enum.DATA_EFFECTIVE.getCode());
		// �������ο��Գɼ�¼��״̬
		Hb74 hb74 = apiAppraisalScoreMapper.countHc64Chc014(hc64.getChb140(), COMAAE100Enum.DATA_EFFECTIVE.getCode());
		// �������۳ɼ�¼��״̬
		if (StringUtils.isNotBlank(hb74.getChb147())) {
			hb74.setChb147(HB74CHB147Enum.HB74CHB147DATACHECK(Integer.valueOf(hb74.getChb147())));
		}
		// ����ʵ�ٳɼ�¼��״̬
		if (StringUtils.isNotBlank(hb74.getChb148())) {
			hb74.setChb148(HB74CHB147Enum.HB74CHB147DATACHECK(Integer.valueOf(hb74.getChb148())));
		}
		// �����ۺϳɼ�¼��״̬
		if (StringUtils.isNotBlank(hb74.getChb149())) {
			hb74.setChb149(HB74CHB147Enum.HB74CHB147DATACHECK(Integer.valueOf(hb74.getChb149())));
		}
		hb74.setChb140(hc64.getChb140());
		// ¼��״̬
		hb74.setChn198("2");
		hb74.setChb158((short)peopleNum);
		hb74.setAae100(COMAAE100Enum.DATA_EFFECTIVE.getCode());
		int result2 = apiAppraisalScoreMapper.saveHb74(hb74);
		return this.success("�����ɼ����³ɹ�");
	}

	@Override
	public AjaxReturnMsg saveSubmitScore(Hb74Temp_Short hb74Temp_Short) {
		// ��ѯ�ύ����������Ϣ
		Hb74Temp_Short hb74 = apiAppraisalScoreMapper.getAppraisalScoreInfo(hb74Temp_Short.getChb140(), COMAAE100Enum.DATA_EFFECTIVE.getCode());
		if (null != hb74 && (HB74CHB147Enum.ALREADY_ENTERED_EXTRANET.getCode().equals(hb74.getChb147()) || HB74CHB147Enum.ALREADY_ENTERED_EXTRANET.getCode().equals(hb74.getChb148()) || HB74CHB147Enum.ALREADY_ENTERED_EXTRANET.getCode().equals(hb74.getChb149()))) {
			int result = apiAppraisalScoreMapper.saveSubmitScore(hb74Temp_Short.getChb140(), HB75CHB146Enum.GRADE_ENTRY.getCode(), HB74CHB355Enum.WAIT_AUDIT.getCode());
			if (1 == result) {
				return this.success("�ύ�ɹ�");
			}
			return this.error("�ύʧ��");
		} else {
			return this.error("���ۡ��������ۺ�������һ��Գɼ����¼������ύ���");
		}
	}

}
