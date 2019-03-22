package com.insigma.mvc.serviceimp.train.PXYW_001_010;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

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
import org.springframework.web.multipart.MultipartFile;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.insigma.common.listener.AppConfig;
import com.insigma.common.util.JvmMemoryUtil;
import com.insigma.common.util.StringUtil;
import com.insigma.common.util.UUIDGenerator;
import com.insigma.dto.AjaxReturnMsg;
import com.insigma.mvc.MvcHelper;
import com.insigma.mvc.dao.common.fileupload.ApiFileUploadMapper;
import com.insigma.mvc.dao.train.PXYW_001_001.ApiCompanyMapper;
import com.insigma.mvc.dao.train.PXYW_001_003.ApiPersonTrnQuaMapper;
import com.insigma.mvc.dao.train.PXYW_001_010.ApiClassPlanMapper;
import com.insigma.mvc.model.CodeValue;
import com.insigma.mvc.model.Param;
import com.insigma.mvc.model.SFileRecord;
import com.insigma.mvc.model.SysExcelBatch;
import com.insigma.mvc.model.train.Hb65;
import com.insigma.mvc.model.train.Hc61;
import com.insigma.mvc.model.train.Hc61_temp;
import com.insigma.mvc.model.train.SmtGroup;
import com.insigma.mvc.model.train.Student;
import com.insigma.mvc.service.common.excel.ExcelVS;
import com.insigma.mvc.service.common.fileupload.ApiFileUploadService;
import com.insigma.mvc.service.train.PXYW_001_010.ApiClassPlanService;
import com.insigma.mvc.serviceimp.train.PXYW_001_003.ApiPersonTrnQuaServiceImpl;
import com.insigma.resolver.AppException;

/**
 * ����ƻ�
 * @author zhanghl
 * 2018-04-25
 */
@Service(value="ApiClassPlanService")
public class ApiClassPlanServiceImpl extends MvcHelper implements ApiClassPlanService,ExcelVS {
	private static Log log=LogFactory.getLog(ApiClassPlanServiceImpl.class);
	@Resource
	private ApiClassPlanMapper apiClassPlanMapper;
	@Resource
	private ApiCompanyMapper apiCompanyMapper;
	@Resource
	private ApiFileUploadService fileLoadService;
	@Resource
	private ApiFileUploadMapper apiFileUploadMapper;
	@Autowired 
	private SqlSessionTemplate sqlSessionTemplate;
	@Resource
	private ApiPersonTrnQuaMapper apiPersonTrnQuaMapper;
	
	@Resource(name = "taskExecutor")  
	private TaskExecutor taskExecutor; 

	// excel��׼��������Ӧָ����,���ڸ�ʽ���鼰�ļ�������ʽ����
	private static String[]HEADERS = new String[] {"������ݺ���,aac002","����,aac003","����,aac005","��ҵʧҵ�Ǽ�֤����,chc003","��Ա���,chc002","��������,chc008",
			                                       "�������ڵ�����,eec357","��ס��ַ����,eec358","��ͥסַ,aac026","��ϵ�绰,aae005","�ʱ�,aae007","��������,aae015",
			                                       "ͨѶ��ַ,aae006","��������,aab024","���л���,aab025","�����˺�,aab026"};
	
	/**
	 * ��ѯ��ѵ�����б�
	 */
	@Override
	public PageInfo<Hb65> getPlanList(Hb65 hb65) throws Exception {
		PageHelper.offsetPage(hb65.getOffset(), hb65.getLimit());
		if(StringUtils.isNotEmpty(hb65.getAca11a())) {
			hb65.setAca11as(hb65.getAca11a().split(","));
		}
		if(StringUtils.isNotEmpty(hb65.getChb103())) {
			hb65.setChb103s(hb65.getChb103().split(","));
		}
		if(StringUtils.isNotEmpty(hb65.getChb104())) {
			hb65.setChb104s(hb65.getChb104().split(","));
		}
		List<Hb65> list=apiClassPlanMapper.getPlanList(hb65);
		PageInfo<Hb65> pageinfo = new PageInfo<Hb65>(list);
		return pageinfo;
	}
	/**
	 * ��ѯ�ѱ���ѧԱ�б�
	 */
	@Override
	public PageInfo<Student> signedStuForLook(Student stu) throws Exception {
		PageHelper.offsetPage(stu.getOffset(), stu.getLimit());
		List<Student> list=apiClassPlanMapper.signedStuForLook(stu.getChb055());
		PageInfo<Student> pageinfo = new PageInfo<Student>(list);
		return pageinfo;
	}
	/**
	 * ������ѵ�����걨��Ϣ
	 */
	@Override
	public AjaxReturnMsg addPlan(Hb65 hb65) throws Exception {
		try{
			String chb055 = hb65.getChb055();
			if(StringUtils.isNotEmpty(chb055)){
				//�޸Ŀ���ƻ���Ϣ
				apiClassPlanMapper.updatePlan(hb65);
				return this.success("���ã��޸Ŀ���ƻ��ɹ�");
			}else{
				//��������ƻ���Ϣ
				apiClassPlanMapper.addPlan(hb65);
				return this.success("���ã���������ƻ��ɹ�");
			}
		}catch(Exception e){
			e.printStackTrace();
			return this.error(e);
		}
	}
	/**
	 * ����ID��ѯ�༶��Ϣ
	 */
	@Override
	public Hb65 getPlanById(Hb65 hb65) throws Exception {
		
		String aaa005 = apiCompanyMapper.getAa01ByAaa001("AREA_ID");
		if("320904".equals(aaa005)) {
			hb65=apiClassPlanMapper.getPlanById_df(hb65.getChb055());
		}else if("610000".equals(aaa005)){
			hb65=apiClassPlanMapper.getPlanById_sx(hb65.getChb055());
		}else{
			hb65=apiClassPlanMapper.getPlanById(hb65.getChb055());
		}
		return hb65;
	}
	/**
	 * ��ȡ��λ����
	 */
	@Override
	public AjaxReturnMsg<List<CodeValue>> getAca111List(CodeValue codevalue) {
		String aaa005 = apiCompanyMapper.getAa01ByAaa001("AREA_ID");
		if("610000".equals(aaa005)){
			List<CodeValue> list = apiClassPlanMapper.getAca111List_sx(codevalue);
			return this.success(list);
		}else{
			List<CodeValue> list = apiClassPlanMapper.getAca111List(codevalue);
			return this.success(list);
		}
	}
	/**
	 * ��ȡ��λ���ʵȼ�
	 */
	@Override
	public AjaxReturnMsg<List<CodeValue>> getAca11aList(String id, String aca111) {
		List<CodeValue> list = apiClassPlanMapper.getAca11aList(id, aca111);
		return this.success(list);
	}
	/**
	 * ����id��ȡsmtgroup����
	 */
	@Override
	public SmtGroup getSmtGroupById(String groupId) throws Exception {
		SmtGroup smtGroup=apiClassPlanMapper.getSmtGroupById(groupId);
		return smtGroup;
	}
	/**
	 * ��ȡ�ϼ�����
	 */
	@Override
	public AjaxReturnMsg<List<CodeValue>> getAab301List() {
		List<CodeValue> list = apiClassPlanMapper.getAab301List();
		return this.success(list);
	}
	/**
	 * ��ѯ�û���������������
	 */
	@Override
	public SmtGroup getCompanyAab301(String groupId) throws Exception {
		SmtGroup smtGroup=apiClassPlanMapper.getCompanyAab301(groupId);
		return smtGroup; 
	}
	/**
	 * ����רҵ���Ʋ�ѯ��ѵ�������Ƽ�רҵ���
	 */
	public Hb65 getAca112(String aca111) throws Exception{
		
		Hb65 hb65 = new Hb65();
		String aaa005 = apiCompanyMapper.getAa01ByAaa001("AREA_ID");
		if("320904".equals(aaa005)) {
			hb65 =(Hb65) apiClassPlanMapper.getAca112_df(aca111);
		}else if("610000".equals(aaa005)){
			hb65 =(Hb65) apiClassPlanMapper.getAca112_sx(aca111);
		}else{
			hb65 =(Hb65) apiClassPlanMapper.getAca112(aca111);
		}
		
		return hb65;
	}
	/**
	 * ͨ������ɾ����ѵ�ƻ�(��Ϊ��Ч)
	 * @throws Exception 
	 */
	@Override
	@Transactional
	public AjaxReturnMsg<String> delPlan(Hb65 hb65) {
		try {
			apiClassPlanMapper.delPlan(hb65);
			return this.success("ɾ���ɹ�");
		}catch (Exception e) {
			e.printStackTrace();
			return this.error("ɾ��ʧ��,��ȷ������ѵ�ƻ��ѱ�ɾ��");
		}
	}
	/**
	 * �޸ļƻ�״̬
	 * @throws Exception 
	 */
	@Override
	@Transactional
	public AjaxReturnMsg<String> changePlan(Hb65 hb65) {
		try {
			apiClassPlanMapper.changePlan(hb65);
			return this.success("�л��ɹ�");
		}catch (Exception e) {
			e.printStackTrace();
			return this.error("�л�ʧ��,��ȷ������ѵ�ƻ�״̬�ѱ��л�");
		}
	}
	/**
	 * ͨ�����֤�Ų�ѯѧԱ��Ϣ
	 */
	public Hc61 findStuById(Student stu) throws Exception{
		String aaa005 = apiCompanyMapper.getAa01ByAaa001("AREA_ID");
		if("320904".equals(aaa005)) {
			stu.setBaseinfoid(null);
		}
		Hc61 hc61 = apiClassPlanMapper.findStuById(stu);
		return hc61;
	}
	/**
	 * ͨ�����֤�Ų�ѯѧԱ������Ϣ
	 */
	public Hc61 findFileById(Student stu) throws Exception{
		Hc61 hc61 = apiClassPlanMapper.findFileById(stu);
		return hc61;
	}
	/**
	 * ѧԱ��Ϣ����
	 */
	@Transactional
	public AjaxReturnMsg signUp(Student stu) throws Exception{
		try{
			Hc61 hc61  = null;
			if(!"true".equals(stu.getFlag())) {//�������ͷ�������ߴ˲�
				
				//У���Ƿ��ѱ����üƻ�
				String count = apiClassPlanMapper.findStuWithPlan(stu);
				if(!"0".equals(count) && !"true".equals(stu.getFlag())) {//ѧԱ�ѱ����üƻ� ��ʾ���ɼ�������
					return this.error("ѧԱ����ʧ�ܣ���ѧԱ�ѱ����üƻ����������±���");
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
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
		        Date date = new Date();
		        String nowDate_str = sdf.format(date);
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
				
				String aaa005 = apiCompanyMapper.getAa01ByAaa001("AREA_ID");
				if("320904".equals(aaa005)) {
					Hb65 hb65 = apiClassPlanMapper.getPlanById_df(stu.getChb055());
					//�����ڳ��и���ѵ��һ��
					if(hb65.getAca11a()!=null && !hb65.getAca11a().equals("04") && !hb65.getAca11a().equals("05")) {
						String num_trian = apiClassPlanMapper.checkHc60(stu.getAac002(),hb65.getAca11a());
						String aca11a_s = "";
						switch (hb65.getAca11a()) {
						case "01":
							aca11a_s = "����";
							break;
						case "02":
							aca11a_s = "�м�";
							break;
					    case "03":
					    	aca11a_s = "�߼�";
					    	break;
						default:
							break;
						}
						if(!"0".equals(num_trian)) {
							return this.error("ѧԱ����ʧ�ܣ�����Ա�������Ѿ��μӹ�"+aca11a_s+"��ѵ");
						}
						String num_signPlan = apiClassPlanMapper.checkSignPlan(stu.getAac002(),hb65.getAca11a(),hb65.getChb055());
						if(!"0".equals(num_signPlan)) {
							return this.error("ѧԱ����ʧ�ܣ�����Ա�������Ѿ��μӹ�"+aca11a_s+"��ѵ");
						}
					}
					
					//У�鱾����Ƿ������ܾ�ҵ��ѵ����
					String num_Subsidy = apiClassPlanMapper.getPersonSubsidyStatus(stu.getAac002());
					if(!"0".equals(num_Subsidy)) {
						return this.error("ѧԱ����ʧ�ܣ�����Ա������Ѿ����ܹ�һ�ξ�ҵ��ѵ����");
					}
					
					//У���Ƿ���ְ��Ա
					/*String num_Status = apiClassPlanMapper.getPersonWorkStatus(stu.getAac002());
					if(!"0".equals(num_Status)) {
						return this.error("ѧԱ����ʧ�ܣ�����Ա����ʧҵ��Ա");
					}*/
					
					stu.setBaseinfoid(null);
					
				}else{
					//У��һ���ڽ���ѵһ����ѵ���
					String trainTypeCount = apiClassPlanMapper.trainTypeCount(stu);
					if(!"0".equals(trainTypeCount)) {
						return this.error("ѧԱ����ʧ�ܣ�һ���ڲ��ܼȲμӾ�ҵ��ѵ�ֲμӴ�ҵ��ѵ");
					}
					//У��һ����һ�Ź���ֻ��ѵһ��
					String trainProfeCount = apiClassPlanMapper.trainProfeCount(stu);
					if(!"0".equals(trainProfeCount)) {
						return this.error("ѧԱ����ʧ�ܣ�һ����ͬһ����ֻ����ѵһ��");
					}
				}
				
				hc61 = apiClassPlanMapper.findStuById(stu);
				int count_hc61;
				if(hc61 == null) {//û�и�ѧԱ��������ѧԱ
					count_hc61 = apiClassPlanMapper.addStu(stu);
					if(count_hc61 == 0) {
						return this.error("����ѧԱ��Ϣʧ�ܣ�����ϵϵͳ����Ա");
					}
				}else {//�и�ѧԱ���޸�
					stu.setChc111(hc61.getChc111());
					count_hc61 = apiClassPlanMapper.updateStu(stu);
					if(count_hc61 == 0) {
						return this.error("�޸�ѧԱ��Ϣʧ�ܣ�����ϵϵͳ����Ա");
					}
				}								
				
				//����
				count_hc61 = apiClassPlanMapper.signUp(stu);
				if(count_hc61 == 0) {
					return this.error("ѧԱ����ʧ�ܣ�����ϵϵͳ����Ա");
				}
				
			}
			//ѧԱ��������� ������ͷ��
			if("true".equals(stu.getFlag())) {
				apiClassPlanMapper.uploadHc61Photo(stu);
			}
			return this.success("���ã�ѧԱ�����ɹ�",stu.getChc111());
		}catch(Exception e){
			e.printStackTrace();
			return this.error(e);
	    }
	}
	//�ϴ�ѧԱͷ��
	@Override
	public AjaxReturnMsg uploadHc61Photo(String userid, String file_bus_id, String file_name, MultipartFile multipartFile) {
		try {
			//����ͼƬ���ļ���������ͬʱ����ͼƬ��¼
			SFileRecord sFileRecord = fileLoadService.uploadFile(multipartFile, Param.TRAIN_GROUP_STUDENT_LOGO, file_name,
					file_bus_id);
			return this.success(sFileRecord);
		} catch (Exception e) {
			return this.error(e.getMessage());
		}
	}
	/**
     * �ϴ���������ļ�
     * @param request
     * @param multipartFile
     * @return
     * @throws Exception
     */
    @Override
    public AjaxReturnMsg fileUpload(HttpServletRequest request, MultipartFile multipartFile) throws Exception {
        String file_bus_id = request.getParameter("file_bus_id");
        String file_bus_name = request.getParameter("file_name");
        String file_bus_type = request.getParameter("file_bus_type");
        String desc = request.getParameter("desc");
        try {
            //����ͼƬ���ļ���������ͬʱ����ͼƬ��¼
            SFileRecord sFileRecord = fileLoadService.uploadFile(multipartFile, file_bus_type, file_bus_name,
                    file_bus_id, desc);
            return this.success("�ϴ��ɹ�",sFileRecord);
        } catch (Exception e) {
            return this.error(e.getMessage());
        }
    }
    /**
	 * ͨ������ɾ��ͼƬ
	 * @throws Exception 
	 */
	@Override
	@Transactional
	public AjaxReturnMsg<String> delPicById(Student stu) {
		try {
			//��ɾ��ͼƬ�������ݣ�Ȼ��ɾ��ͼƬ����  
			int count_busFile = apiClassPlanMapper.delBusFilePicById(stu.getPicId());
			int count_file = apiClassPlanMapper.delFilePicById(stu.getPicId());
			if(count_file == 1 && count_busFile == 1) {
				return this.success("ɾ���ɹ�");				
			}else {
				return this.error("ɾ��ʧ��,��ȷ����ͼƬ�ѱ�ɾ��");
			}
		}catch (Exception e) {
			e.printStackTrace();
			return this.error("ɾ��ʧ��,��ȷ����ͼƬ�ѱ�ɾ��");
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
				temp.setAssistid(sExcelBatch.getExcel_batch_assistid());
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
//		taskExecutor.execute(new Runnable() {  
//		    @Override  
//		    public void run() {  
		        // TODO Auto-generated method stub  
		        try {  
		    		   SqlSession session = sqlSessionTemplate.getSqlSessionFactory().openSession(false);
		    		   // ͨ���µ�session��ȡmapper
		    		   ApiClassPlanMapper mapper = session.getMapper(ApiClassPlanMapper.class);
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
//		    }  
//		});  
	}
	/**
	 * �������뱨������
	 */
	@Override
	public AjaxReturnMsg signUpSave(Hc61_temp temp) throws Exception {
		List<Hc61_temp> list=apiPersonTrnQuaMapper.getPersonCodeList(temp);
		//У�鵼����Ա�Ƿ񳬹���ʵ�ʼƻ���ǰ�ɱ���������
		int count_People = Integer.parseInt(apiClassPlanMapper.getPeople(temp));
		if(list.size() > count_People) {
			return this.error("���ã���ǰ�������������üƻ�ʣ��ɱ������������޸ļƻ���ѵ����");			
		}
		for(Hc61_temp t:list) {
				
			String nextNo = apiPersonTrnQuaMapper.getNextNo();
			
			if("3".equals(t.getAae014())) {
				t.setAae014("6");
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
				stu.setChb055(t.getAssistid());
				apiPersonTrnQuaMapper.updateStu(stu);
				//����
				int count_hc61 = apiClassPlanMapper.signUp(stu);
				if(count_hc61 == 0) {
					return this.error("ѧԱ����ʧ�ܣ�����ϵϵͳ����Ա");
				}
			}else{
				t.setAae014("6");
				Student s = new Student();
				s.setChc111(UUIDGenerator.generate());
				s.setAac001(UUIDGenerator.generate());
				s.setAac002(t.getAac002());
				s.setAac003(t.getAac003());
				s.setChb055(t.getAssistid());
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
				//����
				int count_hc61 = apiClassPlanMapper.signUp(s);
				if(count_hc61 == 0) {
					return this.error("ѧԱ����ʧ�ܣ�����ϵϵͳ����Ա");
				}
			}
			apiPersonTrnQuaMapper.updatePerson(t);
		}
		return this.success("�����ɹ�");
	}
	/**
	 * ����word������Ϣ
	 */
	@Override
	public AjaxReturnMsg exportDJK(Student stu) {
		stu = apiClassPlanMapper.exportDJK(stu);
		//ͼƬ��ʱ����
//		if(StringUtil.isNotEmpty(stu.getPhoto_url())){
//			//��ͼƬ����ƴ����ȥ
//			String fileModule = AppConfig.getProperties("fileModule");
//			stu.setPhoto_url(fileModule+stu.getPhoto_url());
//		}
		return this.success(stu);
	}
	
	
}
