package com.insigma.mvc.serviceimp.train.PXYW_001_003;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.TaskExecutor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.insigma.common.listener.AppConfig;
import com.insigma.common.util.FileUtil;
import com.insigma.common.util.JvmMemoryUtil;
import com.insigma.common.util.StringUtil;
import com.insigma.common.util.UUIDGenerator;
import com.insigma.dto.AjaxReturnMsg;
import com.insigma.mvc.MvcHelper;
import com.insigma.mvc.dao.common.fileupload.ApiFileUploadMapper;
import com.insigma.mvc.dao.train.PXYW_001_003.ApiPersonTrnQuaMapper;
import com.insigma.mvc.model.CodeValue;
import com.insigma.mvc.model.Param;
import com.insigma.mvc.model.SFileRecord;
import com.insigma.mvc.model.SysExcelBatch;
import com.insigma.mvc.model.train.Hb68;
import com.insigma.mvc.model.train.Hc61_temp;
import com.insigma.mvc.model.train.Student;
import com.insigma.mvc.service.common.excel.ExcelVS;
import com.insigma.mvc.service.common.fileupload.ApiFileUploadService;
import com.insigma.mvc.service.train.PXYW_001_003.ApiPersonTrnQuaService;
import com.insigma.resolver.AppException;

@Service(value="personTrnQuaService")
public class ApiPersonTrnQuaServiceImpl extends MvcHelper implements ApiPersonTrnQuaService,ExcelVS{

private static Log log=LogFactory.getLog(ApiPersonTrnQuaServiceImpl.class);
	
	@Resource
	private ApiFileUploadMapper apiFileUploadMapper;
	@Resource
	private ApiFileUploadService fileLoadService;
	@Autowired 
	private SqlSessionTemplate sqlSessionTemplate;
	
	@Resource ApiPersonTrnQuaMapper apiPersonTrnQuaMapper;

	@Resource(name = "taskExecutor")  
	private TaskExecutor taskExecutor; 

	// excel��׼��������Ӧָ����,���ڸ�ʽ���鼰�ļ�������ʽ����
	private static String[]HEADERS = new String[] {"������ݺ���,aac002","����,aac003","����,aac005","��ҵʧҵ�Ǽ�֤����,chc003","��Ա���,chc002","��������,chc008",
			                                       "�������ڵ�����,eec357","��ס��ַ����,eec358","��ͥסַ,aac026","��ϵ�绰,aae005","�ʱ�,aae007","��������,aae015",
			                                       "ͨѶ��ַ,aae006","��������,aab024","���л���,aab025","�����˺�,aab026"};
	
	@Override
	public PageInfo<Student> getStudentList(Student stu) {
		PageHelper.offsetPage(stu.getOffset(), stu.getLimit());
		List<Student> list=apiPersonTrnQuaMapper.getStudentList(stu);
		PageInfo<Student> pageinfo = new PageInfo<>(list);
		return pageinfo;
	}
	
	@Override
	public PageInfo<Hc61_temp> getPersonList(Hc61_temp temp) {
		PageHelper.offsetPage(0, 1000);
		List<Hc61_temp> list=apiPersonTrnQuaMapper.getPersonList(temp);
		PageInfo<Hc61_temp> pageinfo = new PageInfo<>(list);
		return pageinfo;
	}

	@Override
	public AjaxReturnMsg deleteById(Student stu) {
		stu = apiPersonTrnQuaMapper.getStudentById(stu.getChc111());
		if(stu.getBus_uuid()!=null) {
			deleteFile(stu.getBus_uuid());
		}
		int deletenum = apiPersonTrnQuaMapper.deleteById(stu.getChc111());
		if(deletenum == 1) {
			return this.success("ɾ���ɹ�");
		}else{
			return this.error("ɾ��ʧ��");
		}
	}
	
	
	@Override
	public AjaxReturnMsg confirmById(Student stu) {
		stu = apiPersonTrnQuaMapper.getStudentById(stu.getChc111());
		if(stu.getAca111()==null) {
			return this.error("��������ѧԱ������רҵ��");
		}
		int confirmnum = apiPersonTrnQuaMapper.confirmById(stu.getChc111());
		if(confirmnum == 1) {
			return this.success("ȷ��ѧԱ�ɹ�");
		}else{
			return this.error("ȷ��ѧԱʧ��");
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
		ApiPersonTrnQuaMapper mapper = session.getMapper(ApiPersonTrnQuaMapper.class);
		
		String aab001 = apiPersonTrnQuaMapper.getBaseinfoid(sExcelBatch.getExcel_batch_aae011());
		
		try {
			// excel��ѭ��
			for (int i = 2; i < list.size(); i++) {
				String[] tempstr = list.get(i);
				
				Hc61_temp temp = new Hc61_temp();
				temp.setAcc117(sExcelBatch.getExcel_batch_number());//������ʱ��֮�������κ�
				temp.setChc066(i-1+"");
				temp.setChc061(UUIDGenerator.generate());
				temp.setAac002(tempstr[0]);
				temp.setAac003(tempstr[1]);
				temp.setAac005(tempstr[2]);
				temp.setChc003(tempstr[3]);
				temp.setChc002(tempstr[4]);
				temp.setChc008(tempstr[5]);
				temp.setEec357(tempstr[6]);
				temp.setEec358(tempstr[7]);
				temp.setAac026(tempstr[8]);
				temp.setAae005(tempstr[9]);
				temp.setAae007(tempstr[10]);
				temp.setAae015(tempstr[11]);
				temp.setAae006(tempstr[12]);
				temp.setAab024(tempstr[13]);
				temp.setAab025(tempstr[14]);
				temp.setAab026(tempstr[15]);
				temp.setAab001(aab001);
				
				mapper.insertExcelTempData(temp);
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
		        // TODO Auto-generated method stub  
		        try {  
		    		   SqlSession session = sqlSessionTemplate.getSqlSessionFactory().openSession(false);
		    		   // ͨ���µ�session��ȡmapper
		    		   ApiPersonTrnQuaMapper mapper = session.getMapper(ApiPersonTrnQuaMapper.class);
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
		        }  
		    }  
		});  
	}

	@Override
	public AjaxReturnMsg getExcel_batch_number(Hc61_temp temp) {
		String excel_batch_number = apiPersonTrnQuaMapper.getExcel_batch_number(temp);
		return this.success(excel_batch_number);
	}

	@Override
	public AjaxReturnMsg saveDate(Hc61_temp temp) {
		List<Hc61_temp> list=apiPersonTrnQuaMapper.getPersonCodeList(temp);
		for(Hc61_temp t:list) {
				
			String nextNo = apiPersonTrnQuaMapper.getNextNo();
			
			if("3".equals(t.getAae014())) {
				t.setAae014("4");
				temp.setAac002(t.getAac002());
				Student stu = apiPersonTrnQuaMapper.getStudentByCardId(temp);
				stu.setAac003(t.getAac003());
				stu.setAac005(t.getAac005());
				stu.setChc003(t.getChc003());
				stu.setChc002(t.getChc002());
				stu.setChc008(t.getChc008());
				stu.setEec357(t.getEec357());
				stu.setEec358(t.getEec358());
				stu.setAac026(t.getAac026());
				stu.setAae005(t.getAae005());
				stu.setAae007(t.getAae007());
				stu.setAae015(t.getAae015());
				stu.setAae006(t.getAae006());
				stu.setAab024(t.getAab024());
				stu.setAab025(t.getAab025());
				stu.setAab026(t.getAab026());
				stu.setIsselected("0");
				stu.setAae036(new Date());
				stu.setEcc064_date(new Date());
				stu.setChc066(nextNo);
				apiPersonTrnQuaMapper.updateStu(stu);
			}else{
				t.setAae014("1");
				Student s = new Student();
				s.setChc111(UUIDGenerator.generate());
				s.setAac001(UUIDGenerator.generate());
				s.setAac002(t.getAac002());
				s.setAac003(t.getAac003());
				SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
				String dataS = t.getAac002().substring(6, 14);
				Date aac006 = null;
				try {
					aac006 = sdf.parse(dataS);
				} catch (ParseException e) {
					return this.error("ʱ��ת������");
				}
				s.setAac006(aac006);
				String str = t.getAac002().substring(16, 17);
				int i = Integer.parseInt(str) % 2;
				if(i == 0) {
					s.setAac004("2");
				}else{
					s.setAac004("1");
				}
				s.setAac005(t.getAac005());
				s.setChc003(t.getChc003());
				s.setChc002(t.getChc002());
				s.setChc008(t.getChc008());
				s.setEec357(t.getEec357());
				s.setEec358(t.getEec358());
				s.setAac026(t.getAac026());
				s.setAae005(t.getAae005());
				s.setAae007(t.getAae007());
				s.setAae015(t.getAae015());
				s.setAae006(t.getAae006());
				s.setAab024(t.getAab024());
				s.setAab025(t.getAab025());
				s.setAab026(t.getAab026());
				s.setIsselected("0");
				s.setEae052("1");
				s.setAae100("1");
				s.setAab001(t.getAab001());
				s.setUserid(temp.getUserid());
				s.setChc066(nextNo);
				
				apiPersonTrnQuaMapper.saveDate(s);
				
				t.setChc111(s.getChc111());
			}
			apiPersonTrnQuaMapper.updatePerson(t);
		}
		return this.success("�����ɹ�");
	}

	@Override
	public AjaxReturnMsg getStudentById(String chc111) {
		Student stu = apiPersonTrnQuaMapper.getStudentById(chc111);
		if(StringUtil.isNotEmpty(stu.getPhoto_url())){
			//��ͼƬ����ƴ����ȥ
			String fileModule = AppConfig.getProperties("fileModule");
			stu.setPhoto_url(fileModule+stu.getPhoto_url());
		}
		return this.success(stu);
	}

	@Override
	public AjaxReturnMsg updateStu(Student stu) {
		stu.setAae036(new Date());
		int num = apiPersonTrnQuaMapper.updateStu(stu);
		int num1 = apiPersonTrnQuaMapper.updateStuhc60(stu);
		
	  return this.success("����ɹ�", stu.getChc111());
	
	}

	@Override
	public AjaxReturnMsg uploadLogo(String userid, String file_bus_id, String file_name, MultipartFile multipartFile) {
		try {
			//����ͼƬ���ļ���������ͬʱ����ͼƬ��¼
			SFileRecord sFileRecord = fileLoadService.uploadFile(multipartFile, Param.TRAIN_GROUP_STUDENT_LOGO, file_name,
					file_bus_id);
			Student stu = new Student();
			stu.setChc111(sFileRecord.getFile_bus_id());
			stu.setBus_uuid(sFileRecord.getBus_uuid());
			stu.setAae036(new Date());
			apiPersonTrnQuaMapper.updateStu(stu);
			return this.success(sFileRecord);
			
		} catch (Exception e) {
			return this.error(e.getMessage());
		}
	}

	@Override
	public AjaxReturnMsg getStudentGBKById(String chc111) {
		Student stu = apiPersonTrnQuaMapper.getStudentGBKById(chc111);
		if(StringUtil.isNotEmpty(stu.getPhoto_url())){
			//��ͼƬ����ƴ����ȥ
			String fileModule = AppConfig.getProperties("fileModule");
			stu.setPhoto_url(fileModule+stu.getPhoto_url());
		}
		return this.success(stu);
	}


	@Override
	public AjaxReturnMsg saveStu(Student stu) {
		String nextNo = apiPersonTrnQuaMapper.getNextNo();
		stu.setChc111(UUIDGenerator.generate());
		stu.setAac001("0");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String dataS = stu.getAac002().substring(6, 14);
		Date aac006 = null;
		try {
			aac006 = sdf.parse(dataS);
		} catch (ParseException e) {
			return this.error("ʱ��ת������");
		}
		stu.setAac006(aac006);
		String str = stu.getAac002().substring(16, 17);
		int i = Integer.parseInt(str) % 2;
		if(i == 0) {
			stu.setAac004("2");
		}else{
			stu.setAac004("1");
		}
		//У������:
		String year_str;
		String sex;
		if(stu.getAac002().length()==18) {
			year_str = stu.getAac002().substring(6, 10);
			sex = stu.getAac002().substring(16, 17);
		}else {
			year_str = "19"+stu.getAac002().substring(6, 8);
			sex = stu.getAac002().substring(13, 14);
		}
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy");
        Date date = new Date();
        String nowDate_str = sdf1.format(date);
        int nowDate = Integer.parseInt(nowDate_str);
        int year = Integer.parseInt(year_str);
		int age = nowDate - year;
		if(Integer.parseInt(sex)%2==0){
			sex ="girl";
			if(age < Integer.parseInt(stu.getAgewomanmin()) || age > Integer.parseInt(stu.getAgewomanmax())){
				return this.error("ѧԱ����ʧ�ܣ�Ů��ѧԱ���䷶ΧӦ��"+stu.getAgewomanmin()+"�굽"+stu.getAgewomanmax()+"��֮��");
			}
		}else{
			sex = "boy";
			if(age < Integer.parseInt(stu.getAgemanmin()) || age > Integer.parseInt(stu.getAgemanmax())){
				return this.error("ѧԱ����ʧ�ܣ�����ѧԱ���䷶ΧӦ��"+stu.getAgemanmin()+"�굽"+stu.getAgemanmax()+"��֮��");
			}
		}
		stu.setAac006(aac006);
		stu.setIsselected("0");
		stu.setEae052("1");
		stu.setAae100("1");
		stu.setAab001(stu.getBaseinfoid());
		stu.setChc066(nextNo);
		
		int num = apiPersonTrnQuaMapper.saveDate(stu);
		if(num == 1) {
			return this.success("����ɹ�", stu.getChc111());
		}else{
			return this.error("����ʧ��");
		}
		
	}

	/**
     * ɾ���ļ���¼����ʵ�ļ�
     *
     * @param bus_uuid ҵ���ϴ��ļ�����
     */
	public AjaxReturnMsg deleteFile(String bus_uuid) {
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
		return null;
	}

	@Override
	public AjaxReturnMsg checkAac002(Student stu) {
		String message = checkAge(stu.getAac002());
		if(message != "") {
			stu.setAae013(message);
			return this.error(stu);
		}
		String aac002 = stu.getAac002();
		stu = apiPersonTrnQuaMapper.getCheckStuInHc61(stu);
		if(stu == null) {
			stu = new Student();
			stu.setChc111("");
			stu.setAac002(aac002);
		}else{
			if(StringUtil.isNotEmpty(stu.getPhoto_url())){
				//��ͼƬ����ƴ����ȥ
				String fileModule = AppConfig.getProperties("fileModule");
				stu.setPhoto_url(fileModule+stu.getPhoto_url());
			}
		}
		return this.success(stu);
	}
	
	public String checkAge(String aac002) {
		String message = "";
		String demand = apiPersonTrnQuaMapper.getDemand("TRAINSTUAGEDEMAND");
		if(demand != null && !demand.equals("")) {
			String manDemand = demand.split(";")[0];
			String wonDemand = demand.split(";")[1];
			Date date = new Date(); //��ǰʱ��
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
			String dateStr = sdf.format(date);
			int year = Integer.parseInt(dateStr.substring(0, 4)); //��ǰ���
			char sexnum = aac002.charAt(16); //�Ա��־��
			int yearPer = Integer.parseInt(aac002.substring(6, 10)); //�������
			int yearD = year - yearPer; //����(����)
			int day = Integer.parseInt(dateStr.substring(4)); //��ǰ����
			int dayPer = Integer.parseInt(aac002.substring(10,14)); //��������
			int add = -1;
			if(day >= dayPer) {
				add = 0;
			}
			yearD += add;
			if(sexnum % 2 == 0) {
				if(yearD < 16 || yearD > 50) {
					message = "Ů��ѧԱ���䷶Χ��"+wonDemand.split(",")[1]+"��"+wonDemand.split(",")[2]+"����";
				}
			}else{
				if(yearD < 16 || yearD > 60) {
					message = "����ѧԱ���䷶Χ��"+manDemand.split(",")[1]+"��"+manDemand.split(",")[2]+"����";
				}
			}
		}
		return message;
	}

	//��ѯѧԱ��ѵ��Ϣ
	@Override
	public PageInfo<Hb68> getTrainClasses(Student stu) {
		PageHelper.offsetPage(stu.getOffset(), stu.getLimit());
		List<Hb68> list = apiPersonTrnQuaMapper.getTrainClasses(stu);
		PageInfo<Hb68> pageinfo = new PageInfo<Hb68>(list);
		return pageinfo;
	}

	@Override
	public AjaxReturnMsg<List<CodeValue>> getAca111List(CodeValue codevalue) {
		List<CodeValue> list = apiPersonTrnQuaMapper.getAca111List(codevalue);
		return this.success(list);
	}
	
	@Override
	public AjaxReturnMsg<List<CodeValue>> getAca109List(CodeValue codevalue) {
		List<CodeValue> list = apiPersonTrnQuaMapper.getAca109List(codevalue);
		return this.success(list);
	}
	
}
