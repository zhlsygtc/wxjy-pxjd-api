package com.insigma.mvc.serviceimp.train.PXYW_001_005;

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
import com.insigma.common.util.UUIDGenerator;
import com.insigma.dto.AjaxReturnMsg;
import com.insigma.mvc.MvcHelper;
import com.insigma.mvc.dao.common.fileupload.ApiFileUploadMapper;
import com.insigma.mvc.dao.train.PXYW_001_005.ApiBeginClassMapper;
import com.insigma.mvc.model.train.Hb68;
import com.insigma.mvc.model.train.Hb69;
import com.insigma.mvc.model.train.Hb69Temp;
import com.insigma.mvc.model.train.SmtGroup;
import com.insigma.mvc.model.CodeValue;
import com.insigma.mvc.model.SysExcelBatch;
import com.insigma.mvc.model.train.Student;
import com.insigma.mvc.service.common.excel.ExcelVS;
import com.insigma.mvc.service.train.PXYW_001_005.ApiBeginClassService;
import com.insigma.mvc.serviceimp.train.PXYW_001_006.ApiInputScoreServiceImpl;
import com.insigma.resolver.AppException;
/**
 * ��������
 * @author zhanghl
 * 2018-01-10
 */
@Service(value="BeginClassService")
public class ApiBeginClassServiceImpl extends MvcHelper implements ApiBeginClassService,ExcelVS {
	private static Log log=LogFactory.getLog(ApiInputScoreServiceImpl.class);
	@Resource
	private ApiFileUploadMapper apiFileUploadMapper;
	@Autowired 
	private SqlSessionTemplate sqlSessionTemplate;
	@Resource(name = "taskExecutor")  
	private TaskExecutor taskExecutor;
	@Resource
	private ApiBeginClassMapper beginClassMapper;
	
	// excel��׼��������Ӧָ����,���ڸ�ʽ���鼰�ļ�������ʽ����
	private static String[] HEADERS = new String[] {"�Ͽ�����,chb160", "�α�(�Ͽ�����),chb158","��ʱ,chb109","�Ͽο�ʼʱ��,chb167","�Ͽν���ʱ��,chb186","�Ͽεص�(��������),chb161","��ѵ��ʦ,aac003","��ʦ���֤��,aac002","�̲����Ƽ������絥λ,chb162"};

	/**
	 * ��ѯ��ѵ�����б�
	 */
	@Override
	public PageInfo<Hb68> getInfoList(Hb68 hb68) throws Exception {
		PageHelper.offsetPage(hb68.getOffset(), hb68.getLimit());
		if(StringUtils.isNotEmpty(hb68.getChb103())) {
			hb68.setChb103s(hb68.getChb103().split(","));
		}
		if(StringUtils.isNotEmpty(hb68.getChb315())) {
			hb68.setChb315s(hb68.getChb315().split(","));
		}
		List<Hb68> list=beginClassMapper.getInfoList(hb68);
		PageInfo<Hb68> pageinfo = new PageInfo<Hb68>(list);
		return pageinfo;
	}
	/**
	 * ����ID��ѯ�༶��Ϣ
	 */
	@Override
	public Hb68 getById(String chb100) throws Exception {
		Hb68 hb68=beginClassMapper.getById(chb100);
		return hb68;
	}
	/**
	 * ��ȡ��λ����
	 */
	@Override
	public AjaxReturnMsg<List<CodeValue>> getAca111List(CodeValue codevalue) {
		List<CodeValue> list = beginClassMapper.getAca111List(codevalue);
		return this.success(list);
	}
	/**
	 * ��ȡ�ϼ�����
	 */
	@Override
	public AjaxReturnMsg<List<CodeValue>> getAab301List() {
		List<CodeValue> list = beginClassMapper.getAab301List();
		return this.success(list);
	}
	/**
	 * ��ѯ�û���������������
	 */
	@Override
	public SmtGroup getCompanyAab301(String groupId) throws Exception {
		SmtGroup smtGroup=beginClassMapper.getCompanyAab301(groupId);
		return smtGroup; 
	}
	/**
	 * ����id��ȡsmtgroup����
	 */
	@Override
	public SmtGroup getSmtGroupById(String groupId) throws Exception {
		SmtGroup smtGroup=beginClassMapper.getSmtGroupById(groupId);
		return smtGroup;
	}
	/**
	 * ��ѯѧԱ��ѧԱ�б�
	 */
	@Override
	public List<Student> getStuList(Student stu) throws Exception {
		//�û����(������š����˱�ŵ�)  �˴���ʱΪ��ֵ  ������sys_user����Ӹ��������
		List<Student> list=beginClassMapper.getStuList(stu);
		return list;
	}
//	/**
//	 * ��ѯ�γ̱���Ϣ
//	 */
//	@Override
//	public List<Hb69> getCourseList(Hb69 hb69) throws Exception {
//		//�û����(������š����˱�ŵ�)  �˴���ʱΪ��ֵ  ������sys_user����Ӹ��������
//		List<Hb69> list=beginClassMapper.getCourseListForLook(hb69.getChb100());
//		return list;
//	}
	/**
	 * ��ѯ��ѡѧԱ��Ϣ
	 */
	@Override
	public List<Student> getCheck(Student stu)throws Exception {
		List<Student> list=beginClassMapper.getCheck(stu);
		return list;
	}
	/**
	 * ������ѵ�����걨��Ϣ
	 */
	@Override
	public AjaxReturnMsg saveBaseInfo(Hb68 hb68) throws Exception {
		try{
			String chb100 = hb68.getChb100();
			if(StringUtils.isNotEmpty(chb100)){//�޸İ����Ϣ
				//У��ð����Ϣ�뵥λ��Ϣ�Ƿ�ƥ��
				beginClassMapper.updateBaseInfo(hb68);
				return this.success("���ã��޸İ༶���Ϊ��"+chb100+"���Ŀ��������Ϣ�ɹ�", chb100);
			}else{
				//�û����(������š����˱�ŵ�)
				hb68.setAab001(hb68.getAab001());
				hb68.setAae011(hb68.getAae011());
				chb100 = beginClassMapper.getChb100();
				hb68.setChb100(chb100);
				beginClassMapper.saveBaseInfo(hb68);
				return this.success("���ã����������Ϣ����ɹ����༶���Ϊ����"+chb100+"�����������дѧԱ��Ϣ", chb100);
			}
		}catch(Exception e){
			e.printStackTrace();
			return this.error(e);
		}
	}
	/**
	 * ����ѧԱ��ѧԱ��hb60
	 */
	public AjaxReturnMsg saveStu(Student stu) throws Exception{
		try{
			//����ѧԱ
			for(String chc111:stu.getChc111s()) {
				stu.setChc111(chc111);
				beginClassMapper.saveStu(stu);				
			}
			//����hb61��ѧԱ��ѧԱ״̬
			beginClassMapper.updateHc61(stu);
			return this.success("���ã�ѧԱ��Ϣ����ɹ�������д�γ���Ϣ");
		}catch(Exception e){
			e.printStackTrace();
			return this.error(e);
	    }
	}
	/**
	 * ͨ������ɾ��������Ϣ
	 * @throws Exception 
	 */
	@Override
	@Transactional
	public AjaxReturnMsg<String> delClass(Hb68 hb68) {
		try {
			beginClassMapper.updateStu(hb68);
			beginClassMapper.delClassStu(hb68);
			beginClassMapper.delClassBase(hb68);
			return this.success("ɾ���ɹ�");
		}catch (Exception e) {
			e.printStackTrace();
			return this.error("ɾ��ʧ��,��ȷ���ð༶�ѱ�ɾ��");
		}
	}
	/**
	 * �ύ������Ϣ
	 * @throws Exception 
	 */
	@Override
	@Transactional
	public AjaxReturnMsg<String> submitClass(Hb68 hb68) {
		try {
			beginClassMapper.submitClass(hb68);
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
			hb68 = beginClassMapper.checkStatus(hb68);
			//�������¼������ʾ�޷�����
			if("1".equals(hb68.getChn198())) {
				return this.error("����ʧ�ܣ���ǰ�༶�ɼ���¼�룬�޷�����");
			}
			int count =beginClassMapper.revokeClass(hb68);
			if(count==1){
				return this.success("�����ɹ�");
			}else{
				return this.error("����ʧ��,��ȷ���˰༶�Ѿ���������");
			}
		}catch (Exception e) {
			e.printStackTrace();
			return this.error("����ʧ��,��ȷ���ð༶�ѳ����ɹ�");
		}
	}
	/**
	 * ��ѯѧԱ�б�
	 */
	@Override
	public PageInfo<Student> getStuListForLook(Student stu) throws Exception {
		PageHelper.offsetPage(stu.getOffset(), stu.getLimit());
		List<Student> list=beginClassMapper.getStuListForLook(stu.getChb100());
		PageInfo<Student> pageinfo = new PageInfo<Student>(list);
		return pageinfo;
	}
	/**
	 * ��ѯ�γ̱���Ϣ
	 */
	@Override
	public List<Hb69> getCourseListForLook(Hb69 hb69)throws Exception {
		PageHelper.offsetPage(hb69.getOffset(), hb69.getLimit());
		List<Hb69> list=beginClassMapper.getCourseListForLook(hb69.getChb100());
		return list;
	}
	/**
	 * ��ѯ�ı��Ŀγ̱���Ϣ
	 */
	@Override
	public List<Hb69> getCourseListForChange(Hb69 hb69)throws Exception {
		PageHelper.offsetPage(0, 1000);
		List<Hb69> list=beginClassMapper.getCourseListForLook(hb69.getChb100());
		return list;
	}
	/**
	 * ��ȡ����γ̱�
	 */
	@Override
	public PageInfo<Hb69Temp> getTempCourseList(Hb69Temp temp) {
		List<Hb69Temp> list=beginClassMapper.getTempCourseList(temp);
		PageInfo<Hb69Temp> pageinfo = new PageInfo<>(list);
		return pageinfo;
	}
	/**
	 *¼�롪������У��ӱ���γ̱�hb69
	 */
	public AjaxReturnMsg varifyCourse(Hb69Temp hb69Temp) throws Exception{
		try{
			String[] hb69Datas =  hb69Temp.getHb69Data().split(";");
			String chb160 = "";
			String chb158 = "";
			String chb109 = "";
			String chb167 = "";
			String chb186 = "";
			String chb063 = "";
			String aac003 = "";
			String aac002 = "";
			String chb162 = "";
			//������ð༶�ڻ�����е�����
			beginClassMapper.delHb69Temp(hb69Temp.getChb100());
			for(int i=0;i<hb69Datas.length;i++) {
				chb160 = hb69Datas[i].split(",")[0];
				chb158 = hb69Datas[i].split(",")[1];
				chb109 = hb69Datas[i].split(",")[2];
				chb167 = hb69Datas[i].split(",")[3];
				chb186 = hb69Datas[i].split(",")[4];
				chb063 = hb69Datas[i].split(",")[5];
				aac003 = hb69Datas[i].split(",")[6];
				aac002 = hb69Datas[i].split(",")[7];
				chb162 = hb69Datas[i].split(",")[8];
				
				hb69Temp.setChb160(chb160);
				hb69Temp.setChb158(chb158);
				hb69Temp.setChb109(chb109);
				hb69Temp.setChb167(chb167);
				hb69Temp.setChb186(chb186);
				hb69Temp.setChb063(chb063);
				hb69Temp.setAac003(aac003);
				hb69Temp.setAac002(aac002);
				hb69Temp.setChb162(chb162);
				hb69Temp.setChc066(i+1);
				//����hb69Temp
				int count = beginClassMapper.insertHb69Temp(hb69Temp);
				if(count == 0) {
					return this.error("����γ̱�ʧ�ܣ�����ԭ��:���浽��ʱ��ʧ��");
				}
			}
			return this.success("���ã��γ̱���Ϣ����ɹ�");
		}catch(Exception e){
			e.printStackTrace();
			return this.error("���泡����Ϣʧ�ܣ�����ԭ��:"+e);
	    }
	}
	/**
	 * ����---�����γ̱�hb69
	 */
	public AjaxReturnMsg saveCourseData(Hb69Temp hb69Temp) throws Exception{
		try{
			//���69
			beginClassMapper.delHb69(hb69Temp.getChb100());
			//����69��
			for(String chb069_temp:hb69Temp.getChb069s()) {
				hb69Temp.setChb069_temp((chb069_temp));
				beginClassMapper.insertHb69(hb69Temp);
			}
			return this.success("���ã��γ̱���Ϣ����ɹ�");
		}catch(Exception e){
			e.printStackTrace();
			return this.error(e);
	    }
	}
	/**
	 * ¼��---�����γ̱�hb69
	 */
	public AjaxReturnMsg saveCourse(Hb69Temp hb69Temp) throws Exception{
		try{
			//���69
			beginClassMapper.delHb69(hb69Temp.getChb100());
			//��ȡhb69Temp�е�����
			List<Hb69Temp> hb69TempList  = beginClassMapper.getHb69tempList(hb69Temp);
			//����69��
			for(Hb69Temp HT:hb69TempList) {
				HT.setBaseinfoid(hb69Temp.getBaseinfoid());
				beginClassMapper.insertHb69Input(HT);
			}
			return this.success("���ã��γ̱���Ϣ����ɹ�");
		}catch(Exception e){
			e.printStackTrace();
			return this.error(e);
	    }
	}
	/**
	 * �޸Ŀγ̱�
	 */
	public AjaxReturnMsg saveCourseForChange(Hb69Temp hb69Temp) throws Exception{
		try{
			//����γ̱��ѵ�����һ�Σ��򲻱�����ǰ�γ̱�����ǰ�γ̱�ɾ��
			String count = beginClassMapper.findHb69(hb69Temp.getChb100());
			if("0".equals(count)) {//�״ογ̱����,����ǰ�γ̱��Ϊ��Ч
				beginClassMapper.changeToNoEffect(hb69Temp.getChb100());
			}else {//֮ǰ�е������γ̱���ֱ��ɾ����ǰ��Ч�Ŀγ̱�
				beginClassMapper.delEffectHb69(hb69Temp.getChb100());				
			}
			//��ȡhb69Temp�е�����
			List<Hb69Temp> hb69TempList  = beginClassMapper.getHb69tempList(hb69Temp);
			//����69��
			for(Hb69Temp HT:hb69TempList) {
				HT.setBaseinfoid(hb69Temp.getBaseinfoid());
				beginClassMapper.insertHb69InputForChange(HT);
			}
			return this.success("���ã��γ̱���Ϣ����ɹ�");
		}catch(Exception e){
			e.printStackTrace();
			return this.error(e);
	    }
	}
	/**
	 * ���ݴ��� ���ӵ���ʱ��
	 */
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
		ApiBeginClassMapper mapper = session.getMapper(ApiBeginClassMapper.class);
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
				hb69Temp.setChb063(tempstr[5]);//�Ͽεص�(��������)
				hb69Temp.setAac003(tempstr[6]);//��ѵ��ʦ
				hb69Temp.setAac002(tempstr[7]);//��ѵ��ʦ���֤����
				hb69Temp.setChb162(tempstr[8]);//�̲����Ƽ������絥λ
				hb69Temp.setChb068(sExcelBatch.getExcel_batch_assistid());//�༶���
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

	/**
	 * ִ�����ݴ������
	 * @param executeProc
	 */
	@Override
	public void executeProc(SysExcelBatch sExcelBatch) throws AppException {
		/**3 ���ù��̴�������*/
		//��ȡ���κ�
		final SysExcelBatch newsexcelbatch=apiFileUploadMapper.getExcelBatchById(sExcelBatch.getExcel_batch_id());
		//�����߳�ִ��
		taskExecutor.execute(new Runnable() {  
		    @Override  
		    public void run() {  
				    	SqlSession session = sqlSessionTemplate.getSqlSessionFactory().openSession(false);
				    	// ͨ���µ�session��ȡmapper
				    	ApiBeginClassMapper mapper = session.getMapper(ApiBeginClassMapper.class);
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
		    }  
		});  
	}
	/**
	 * ��ѯ�������κ�
	 */
	@Override
	public AjaxReturnMsg getExcel_batch_number(Hb69Temp hb69Temp) {
		String excel_batch_number = beginClassMapper.getExcel_batch_number(hb69Temp);
		return this.success(excel_batch_number);
	}
	/**
	 * ͨ��idɾ����ѵѧԱ
	 */
	@Override
	@Transactional
	public AjaxReturnMsg<String> delStudent(String chc001) {
		int updatenum = beginClassMapper.updateHc61ById(chc001);
		int deletenum = beginClassMapper.delHc60(chc001);
		if(deletenum==1 && updatenum==1){
			return this.success("ɾ���ɹ�");
		}else{
			return this.error("ɾ��ʧ��,��ȷ�������Ѿ���ɾ����");
		}
	
	}
	/**
	 * ����רҵ���Ʋ�ѯ��ѵ�������Ƽ�רҵ���
	 */
	public Hb68 getAca112(String aca110) throws Exception{
		Hb68 hb68 =(Hb68) beginClassMapper.getAca112(aca110);
		return hb68;
	}
	/**
	 * ����רҵ���Ʋ�ѯ������׼
	 */
	public Hb68 getAca131(String aca109) throws Exception{
		Hb68 hb68 =(Hb68) beginClassMapper.getAca131(aca109);
		return hb68;
	}
}
