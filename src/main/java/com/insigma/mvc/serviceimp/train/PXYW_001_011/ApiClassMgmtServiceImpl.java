package com.insigma.mvc.serviceimp.train.PXYW_001_011;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.insigma.common.listener.AppConfig;
import com.insigma.common.util.JvmMemoryUtil;
import com.insigma.common.util.UUIDGenerator;
import com.insigma.dto.AjaxReturnMsg;
import com.insigma.mvc.MvcHelper;
import com.insigma.mvc.dao.common.fileupload.ApiFileUploadMapper;
import com.insigma.mvc.dao.train.PXYW_001_001.ApiCompanyMapper;
import com.insigma.mvc.dao.train.PXYW_001_011.ApiClassMgmtMapper;
import com.insigma.mvc.model.CodeValue;
import com.insigma.mvc.model.SysExcelBatch;
import com.insigma.mvc.model.train.Hb65;
import com.insigma.mvc.model.train.Hb68;
import com.insigma.mvc.model.train.Hb69;
import com.insigma.mvc.model.train.Hb69Temp;
import com.insigma.mvc.model.train.Student;
import com.insigma.mvc.service.common.excel.ExcelVS;
import com.insigma.mvc.service.train.PXYW_001_011.ApiClassMgmtService;
import com.insigma.mvc.serviceimp.train.PXYW_001_006.ApiInputScoreServiceImpl;
import com.insigma.resolver.AppException;

/**
 * �༶����
 * @author yugw
 * 2018-04-26
 */
@Service(value="classMgmtService")
public class ApiClassMgmtServiceImpl extends MvcHelper implements ApiClassMgmtService,ExcelVS {

	private static Log log=LogFactory.getLog(ApiInputScoreServiceImpl.class);
	private  static  String localcity = AppConfig.getProperties("localcity");
	@Resource
	private ApiFileUploadMapper apiFileUploadMapper;
	@Autowired 
	private SqlSessionTemplate sqlSessionTemplate;
	@Resource(name = "taskExecutor")  
	private TaskExecutor taskExecutor;
	@Resource
	private ApiClassMgmtMapper apiClassMgmtMapper;
	@Resource
	private ApiCompanyMapper apiCompanyMapper;
	private static String[] HEADERS = {};
	public ApiClassMgmtServiceImpl() {
		//����Ǵ�������excelУ����
		if("320904".equals(localcity)) {
			// excel��׼��������Ӧָ����,���ڸ�ʽ���鼰�ļ�������ʽ����
			HEADERS = new String[] {"�Ͽ�����,chb160", "�α�(�Ͽ�����),chb158","��ʱ,chb109","�Ͽο�ʼʱ��,chb167","�Ͽν���ʱ��,chb186","��������,chb163","��ѵ��ʦ,aac003","��ʦ���֤��,aac002","�̲����Ƽ������絥λ,chb162"};		
		}else {
			// excel��׼��������Ӧָ����,���ڸ�ʽ���鼰�ļ�������ʽ����
			HEADERS = new String[] {"�Ͽ�����,chb160", "�α�(�Ͽ�����),chb158","��ʱ,chb109","�Ͽο�ʼʱ��,chb167","�Ͽν���ʱ��,chb186","�Ͽεص�(��������),chb161","��ѵ��ʦ,aac003","��ʦ���֤��,aac002","�̲����Ƽ������絥λ,chb162"};		
		}
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
		ApiClassMgmtMapper mapper = session.getMapper(ApiClassMgmtMapper.class);
		try {
			// excel��ѭ��
			for (int i = 1; i < list.size(); i++) {
				String[] tempstr = list.get(i);
				
				Hb69Temp hb69Temp = new Hb69Temp();
				hb69Temp.setAcc117(sExcelBatch.getExcel_batch_number());//������ʱ��֮�������κ�
				hb69Temp.setChb069(UUIDGenerator.generate());
				hb69Temp.setChb160(tempstr[0]);//�Ͽ�����
				hb69Temp.setChb158(tempstr[1]);//�α�(�Ͽ�����)
				hb69Temp.setChb109(tempstr[2]);//��ʱ
				hb69Temp.setChb167(tempstr[3]);//�Ͽο�ʼʱ��
				hb69Temp.setChb186(tempstr[4]);//�Ͽν���ʱ��
				hb69Temp.setChb163(tempstr[5]);//��������
				hb69Temp.setChb063(tempstr[5]);//�Ͽεص�(��������)	
				hb69Temp.setAac003(tempstr[6]);//��ѵ��ʦ
				hb69Temp.setAac002(tempstr[7]);//��ѵ��ʦ���֤����
				hb69Temp.setChb162(tempstr[8]);//�̲����Ƽ������絥λ
				hb69Temp.setChc066(i);
				mapper.insertExcelTempData(hb69Temp);
				if (i % 5000 == 0 || i == list.size() - 1) {
					// �ֶ�ÿ5000��һ�ύ���ύ���޷��ع�
					session.commit();
					// �����棬��ֹ���
					session.clearCache();
					JvmMemoryUtil.showJvmMemory();
					//���µ�ǰ��������״̬ 10%+20%�İٷݱ� ������ʱ���30%�ķֶα���
					int statusnumber=new Float((i+0f)/(list.size()+0f)*20).intValue()+10;
					sExcelBatch.setExcel_batch_data_status(statusnumber);
					apiFileUploadMapper.updateExelBatch(sExcelBatch);
				}
			}
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
		//�����ļ���¼
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
			    	ApiClassMgmtMapper mapper = session.getMapper(ApiClassMgmtMapper.class);
		        try {  
		        	    Date start=new Date();
						log.info("���ù��̿�ʼʱ��"+new Date().toLocaleString());
						//���ù��̴�������
						mapper.executeProc(newsexcelbatch);
						Date end=new Date();
						Long cost=end.getTime()-start.getTime();
						log.info("���ù��̽���ʱ��"+new Date().toLocaleString()+"����"+cost/1000+"s");
						//ִ���Ƿ�ɹ�
						if(newsexcelbatch.getExcel_batch_rt_code() .equals("1")){
							
						}else{
							log.info("��������ʧ��,ʧ��ԭ��"+newsexcelbatch.getExcel_batch_rt_msg());
						}
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

	@Override
	public PageInfo<Hb68> getInfoList(Hb68 hb68) throws Exception {
		PageHelper.offsetPage(hb68.getOffset(), hb68.getLimit());
		if(StringUtils.isNotEmpty(hb68.getChb103())) {
			hb68.setChb103s(hb68.getChb103().split(","));
		}
		if(StringUtils.isNotEmpty(hb68.getChb315())) {
			hb68.setChb315s(hb68.getChb315().split(","));
		}
		List<Hb68> list=apiClassMgmtMapper.getInfoList(hb68);
		PageInfo<Hb68> pageinfo = new PageInfo<Hb68>(list);
		return pageinfo;
	}

	@Override
	public AjaxReturnMsg getPlanById(String planid) {
		Hb65 hb65 = apiClassMgmtMapper.getPlanById(planid);
		return this.success(hb65);
	}

	@Override
	@Transactional
	public AjaxReturnMsg saveBaseInfo(Hb68 hb68) {
		try{
			String chb100 = hb68.getChb100();
			if(StringUtils.isNotEmpty(chb100)){//�޸İ����Ϣ
				apiClassMgmtMapper.updateBaseInfo(hb68);
				return this.success("���ã��޸İ༶���Ϊ��"+chb100+"���Ŀ��������Ϣ�ɹ�", hb68.getChb068()+";"+hb68.getChb055());
			}else{
				
				String aaa005 = apiCompanyMapper.getAa01ByAaa001("AREA_ID");
				//�����������ʹ�������а༶��ţ�������ĸ��д+4λ���+2λ�·�+3λ��ˮ ��TGZX201905008��ʾ�칤ְУ2019��5�·ݿ���İ༶���ð༶��������2019��ĵ�����༶ 
				if("429006".equals(aaa005)) {
					chb100 = apiClassMgmtMapper.getChb100Tm(hb68);
				}else {
					chb100 = apiClassMgmtMapper.getChb100();
				}
				hb68.setChb100(chb100);
				apiClassMgmtMapper.saveBaseInfo(hb68);
				apiClassMgmtMapper.addRelation(hb68);
				return this.success("���ã����������Ϣ����ɹ����༶���Ϊ����"+chb100+"�����������дѧԱ��Ϣ", hb68.getChb068()+";"+hb68.getPlan());
			}
		}catch(Exception e){
			e.printStackTrace();
			return this.error(e);
		}
	}

	@Override
	public List<Student> getStuList(Student stu) {
		//�û����(������š����˱�ŵ�)  �˴���ʱΪ��ֵ  ������sys_user����Ӹ��������
		List<Student> list=apiClassMgmtMapper.getStuList(stu);
		return list;
	}

	@Override
	public Hb68 getById(String chb068) {
		Hb68 hb68=apiClassMgmtMapper.getById(chb068);
		return hb68;
	}

	/**
	 * ��ѯ��ѡѧԱ��Ϣ
	 */
	@Override
	public List<Student> getCheck(Student stu) {
		List<Student> list = apiClassMgmtMapper.getCheck(stu);
		return list;
	}

	/**
	 * ͨ������ɾ��������Ϣ
	 * @throws Exception 
	 */
	@Override
	@Transactional
	public AjaxReturnMsg<String> delClass(Hb68 hb68) {
		try {
			apiClassMgmtMapper.delClassBase(hb68);
			apiClassMgmtMapper.delClassStu(hb68);
			apiClassMgmtMapper.delRelation(hb68);
			return this.success("ɾ���ɹ�");
		}catch (Exception e) {
			e.printStackTrace();
			return this.error("ɾ��ʧ��,��ȷ���ð༶�ѱ�ɾ��");
		}
	}

	/**
	 * ����ѧԱ��ѧԱ��hc60
	 */
	@Override
	public AjaxReturnMsg saveStu(Student stu) {
		try{
			//����ѧԱ
			for(String chc111:stu.getChc111s()) {
				stu.setChc111(chc111);
				apiClassMgmtMapper.saveStu(stu);				
			}
			return this.success("���ã�ѧԱ��Ϣ����ɹ�������д�γ���Ϣ");
		}catch(Exception e){
			e.printStackTrace();
			return this.error(e);
	    }
	}

	/**
	 * ͨ��idɾ����ѵѧԱ
	 */
	@Override
	public AjaxReturnMsg<String> delStudent(String chc001) {
		int deletenum = apiClassMgmtMapper.delHc60(chc001);
		if(deletenum==1){
			return this.success("ɾ���ɹ�");
		}else{
			return this.error("ɾ��ʧ��,��ȷ�������Ѿ���ɾ����");
		}
	}

	/**
	 * ��ѯ�γ̱���Ϣ
	 */
	@Override
	public List<Hb69> getCourseListForLook(Hb69 hb69) {
		List<Hb69> list=apiClassMgmtMapper.getCourseListForLook(hb69.getChb068());
		return list;
	}

	/**
	 * �����γ̱�hb69
	 */
	@Override
	@Transactional
	public AjaxReturnMsg saveCourseData(Hb69Temp hb69Temp) {
		try{
			//���69
			apiClassMgmtMapper.delHb69(hb69Temp.getChb068());
			//����69��
			for(String chb069_temp:hb69Temp.getChb069s()) {
				hb69Temp.setChb069_temp((chb069_temp));
				apiClassMgmtMapper.insertHb69(hb69Temp);
			}
			return this.success("���ã��γ̱���Ϣ����ɹ�");
		}catch(Exception e){
			e.printStackTrace();
			return this.error(e);
	    }
	}

	/**
	 * ��ȡ����γ̱�
	 */
	@Override
	public PageInfo<Hb69Temp> getTempCourseList(Hb69Temp temp) {
		List<Hb69Temp> list=apiClassMgmtMapper.getTempCourseList(temp);
		PageInfo<Hb69Temp> pageinfo = new PageInfo<>(list);
		return pageinfo;
	}

	/**
	 * ��ѯ�������κ�
	 */
	@Override
	public AjaxReturnMsg getSysexcelbatch(Hb69Temp hb69Temp) {
		SysExcelBatch s_excel_batch = apiClassMgmtMapper.getSysexcelbatch(hb69Temp);
		return this.success(s_excel_batch);
	}

	/**
	 * ��ѯѧԱ�б�
	 */
	@Override
	public PageInfo<Student> getStuListForLook(Student stu) {
		PageHelper.offsetPage(stu.getOffset(), stu.getLimit());
		List<Student> list = apiClassMgmtMapper.getStuListForLook(stu.getChb068());
		PageInfo<Student> pageinfo = new PageInfo<Student>(list);
		return pageinfo;
	}

	/**
	 * �ύ������Ϣ
	 * @throws Exception 
	 */
	@Override
	public AjaxReturnMsg<String> submitClass(Hb68 hb68) {
		try {
			apiClassMgmtMapper.submitClass(hb68);
			apiClassMgmtMapper.updatePlan(hb68);
			return this.success("�ύ�ɹ�");
		}catch (Exception e) {
			e.printStackTrace();
			return this.error("�ύʧ��,��ȷ���ð༶���ύ�ɹ�");
		}
	}

	/**
	 * ����������Ϣ
	 * @throws Exception 
	 */
	@Override
	@Transactional
	public AjaxReturnMsg<String> revokeClass(Hb68 hb68) {
		try {
			hb68 = apiClassMgmtMapper.checkStatus(hb68);
			//�������¼������ʾ�޷�����
			if("1".equals(hb68.getChn198())) {
				return this.error("����ʧ�ܣ���ǰ�༶�ɼ���¼�룬�޷�����");
			}
			apiClassMgmtMapper.revokeClass(hb68);
			return this.success("�����ɹ�");
		}catch (Exception e) {
			e.printStackTrace();
			return this.error("����ʧ��,��ȷ���ð༶�ѳ����ɹ�");
		}
	}

	/**
	 * ��ȡ������ѵ�ƻ�
	 */
	@Override
	public AjaxReturnMsg<List<CodeValue>> getPlans(CodeValue codevalue) {
		List<CodeValue> list = apiClassMgmtMapper.getPlans(codevalue);
		return this.success(list);
	}
	/**
	 * ����ѧԱ������
	 * @param stu
	 * @return
	 * @throws Exception
	 */
	@Override
	public List<HashMap> expStuRoster(Student stu) throws Exception {
		List<HashMap> list = apiClassMgmtMapper.expStuRoster(stu);
        return list;
	}
	/**
	 * ������������ȷ�ϱ�������Ϣ
	 */
	@Override
	public AjaxReturnMsg expClassSure(Hb68 hb68) {
		hb68 = apiClassMgmtMapper.expClassSure(hb68);
		return this.success(hb68);
	}
	/**
	 * ������ѧ�ƻ���������Ϣ
	 */
	@Override
	public List<Hb69> getClassCourse(Hb69 hb69){
		List<Hb69> list = apiClassMgmtMapper.getClassCourse(hb69);
        return list;
	}
    /**
     * ͨ��ҵ��id��ȡ�ļ��б�
     */
    @Override
    public AjaxReturnMsg<Map<String, Object>> getClassFile(Hb68 hb68) {
        String fileModule = AppConfig.getProperties("fileModule");
        hb68.setFileModule(fileModule);
        List<Hb68> list = apiClassMgmtMapper.getClassFile(hb68);
        PageHelper.offsetPage(0,1000);//�γ̱���Ϣ��ʾ��һҳ������鿴
        PageInfo<Hb68> pageinfo = new PageInfo<>(list);
        return this.success(pageinfo);
    }
}
