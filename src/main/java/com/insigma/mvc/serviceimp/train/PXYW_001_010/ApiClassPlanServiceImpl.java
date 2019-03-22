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
 * 开班计划
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

	// excel标准列名及对应指标名,用于格式检验及文件导出格式生成
	private static String[]HEADERS = new String[] {"公民身份号码,aac002","姓名,aac003","民族,aac005","就业失业登记证号码,chc003","人员类别,chc002","户口性质,chc008",
			                                       "户口所在地区划,eec357","常住地址区划,eec358","家庭住址,aac026","联系电话,aae005","邮编,aae007","电子邮箱,aae015",
			                                       "通讯地址,aae006","开户银行,aab024","银行户名,aab025","银行账号,aab026"};
	
	/**
	 * 查询培训开班列表
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
	 * 查询已报名学员列表
	 */
	@Override
	public PageInfo<Student> signedStuForLook(Student stu) throws Exception {
		PageHelper.offsetPage(stu.getOffset(), stu.getLimit());
		List<Student> list=apiClassPlanMapper.signedStuForLook(stu.getChb055());
		PageInfo<Student> pageinfo = new PageInfo<Student>(list);
		return pageinfo;
	}
	/**
	 * 保存培训开班申报信息
	 */
	@Override
	public AjaxReturnMsg addPlan(Hb65 hb65) throws Exception {
		try{
			String chb055 = hb65.getChb055();
			if(StringUtils.isNotEmpty(chb055)){
				//修改开班计划信息
				apiClassPlanMapper.updatePlan(hb65);
				return this.success("您好，修改开班计划成功");
			}else{
				//新增开班计划信息
				apiClassPlanMapper.addPlan(hb65);
				return this.success("您好，新增开班计划成功");
			}
		}catch(Exception e){
			e.printStackTrace();
			return this.error(e);
		}
	}
	/**
	 * 根据ID查询班级信息
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
	 * 获取单位资质
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
	 * 获取单位资质等级
	 */
	@Override
	public AjaxReturnMsg<List<CodeValue>> getAca11aList(String id, String aca111) {
		List<CodeValue> list = apiClassPlanMapper.getAca11aList(id, aca111);
		return this.success(list);
	}
	/**
	 * 根据id获取smtgroup对象
	 */
	@Override
	public SmtGroup getSmtGroupById(String groupId) throws Exception {
		SmtGroup smtGroup=apiClassPlanMapper.getSmtGroupById(groupId);
		return smtGroup;
	}
	/**
	 * 获取上级部门
	 */
	@Override
	public AjaxReturnMsg<List<CodeValue>> getAab301List() {
		List<CodeValue> list = apiClassPlanMapper.getAab301List();
		return this.success(list);
	}
	/**
	 * 查询该机构所属行政区划
	 */
	@Override
	public SmtGroup getCompanyAab301(String groupId) throws Exception {
		SmtGroup smtGroup=apiClassPlanMapper.getCompanyAab301(groupId);
		return smtGroup; 
	}
	/**
	 * 根据专业名称查询培训工种名称及专业类别
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
	 * 通过主键删除培训计划(改为无效)
	 * @throws Exception 
	 */
	@Override
	@Transactional
	public AjaxReturnMsg<String> delPlan(Hb65 hb65) {
		try {
			apiClassPlanMapper.delPlan(hb65);
			return this.success("删除成功");
		}catch (Exception e) {
			e.printStackTrace();
			return this.error("删除失败,请确定该培训计划已被删除");
		}
	}
	/**
	 * 修改计划状态
	 * @throws Exception 
	 */
	@Override
	@Transactional
	public AjaxReturnMsg<String> changePlan(Hb65 hb65) {
		try {
			apiClassPlanMapper.changePlan(hb65);
			return this.success("切换成功");
		}catch (Exception e) {
			e.printStackTrace();
			return this.error("切换失败,请确定该培训计划状态已被切换");
		}
	}
	/**
	 * 通过身份证号查询学员信息
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
	 * 通过身份证号查询学员附件信息
	 */
	public Hc61 findFileById(Student stu) throws Exception{
		Hc61 hc61 = apiClassPlanMapper.findFileById(stu);
		return hc61;
	}
	/**
	 * 学员信息保存
	 */
	@Transactional
	public AjaxReturnMsg signUp(Student stu) throws Exception{
		try{
			Hc61 hc61  = null;
			if(!"true".equals(stu.getFlag())) {//如果不是头像处理则走此步
				
				//校验是否已报名该计划
				String count = apiClassPlanMapper.findStuWithPlan(stu);
				if(!"0".equals(count) && !"true".equals(stu.getFlag())) {//学员已报名该计划 提示不可继续报名
					return this.error("学员报名失败，该学员已报名该计划，不可重新报名");
				}
				//校验年龄:
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
						return this.error("学员报名失败，女性学员年龄范围应在"+stu.getAgewomanmin()+"岁到"+stu.getAgewomanmax()+"岁之间");
					}
				}else{
					sex = "boy";
					if(age < Integer.parseInt(stu.getAgemanmin()) || age > Integer.parseInt(stu.getAgemanmax())){
						return this.error("学员报名失败，男性学员年龄范围应在"+stu.getAgemanmin()+"岁到"+stu.getAgemanmax()+"岁之间");
					}
				}
				
				String aaa005 = apiCompanyMapper.getAa01ByAaa001("AREA_ID");
				if("320904".equals(aaa005)) {
					Hb65 hb65 = apiClassPlanMapper.getPlanById_df(stu.getChb055());
					//五年内初中高培训各一次
					if(hb65.getAca11a()!=null && !hb65.getAca11a().equals("04") && !hb65.getAca11a().equals("05")) {
						String num_trian = apiClassPlanMapper.checkHc60(stu.getAac002(),hb65.getAca11a());
						String aca11a_s = "";
						switch (hb65.getAca11a()) {
						case "01":
							aca11a_s = "初级";
							break;
						case "02":
							aca11a_s = "中级";
							break;
					    case "03":
					    	aca11a_s = "高级";
					    	break;
						default:
							break;
						}
						if(!"0".equals(num_trian)) {
							return this.error("学员报名失败，该人员五年内已经参加过"+aca11a_s+"培训");
						}
						String num_signPlan = apiClassPlanMapper.checkSignPlan(stu.getAac002(),hb65.getAca11a(),hb65.getChb055());
						if(!"0".equals(num_signPlan)) {
							return this.error("学员报名失败，该人员五年内已经参加过"+aca11a_s+"培训");
						}
					}
					
					//校验本年度是否已享受就业培训补贴
					String num_Subsidy = apiClassPlanMapper.getPersonSubsidyStatus(stu.getAac002());
					if(!"0".equals(num_Subsidy)) {
						return this.error("学员报名失败，该人员本年度已经享受过一次就业培训补贴");
					}
					
					//校验是否在职人员
					/*String num_Status = apiClassPlanMapper.getPersonWorkStatus(stu.getAac002());
					if(!"0".equals(num_Status)) {
						return this.error("学员报名失败，该人员不是失业人员");
					}*/
					
					stu.setBaseinfoid(null);
					
				}else{
					//校验一年内仅培训一类培训类别
					String trainTypeCount = apiClassPlanMapper.trainTypeCount(stu);
					if(!"0".equals(trainTypeCount)) {
						return this.error("学员报名失败，一年内不能既参加就业培训又参加创业培训");
					}
					//校验一年内一门工种只培训一次
					String trainProfeCount = apiClassPlanMapper.trainProfeCount(stu);
					if(!"0".equals(trainProfeCount)) {
						return this.error("学员报名失败，一年内同一工种只能培训一次");
					}
				}
				
				hc61 = apiClassPlanMapper.findStuById(stu);
				int count_hc61;
				if(hc61 == null) {//没有该学员则新增该学员
					count_hc61 = apiClassPlanMapper.addStu(stu);
					if(count_hc61 == 0) {
						return this.error("新增学员信息失败，请联系系统管理员");
					}
				}else {//有该学员则修改
					stu.setChc111(hc61.getChc111());
					count_hc61 = apiClassPlanMapper.updateStu(stu);
					if(count_hc61 == 0) {
						return this.error("修改学员信息失败，请联系系统管理员");
					}
				}								
				
				//报名
				count_hc61 = apiClassPlanMapper.signUp(stu);
				if(count_hc61 == 0) {
					return this.error("学员报名失败，请联系系统管理员");
				}
				
			}
			//学员已新增完毕 再新增头像
			if("true".equals(stu.getFlag())) {
				apiClassPlanMapper.uploadHc61Photo(stu);
			}
			return this.success("您好，学员报名成功",stu.getChc111());
		}catch(Exception e){
			e.printStackTrace();
			return this.error(e);
	    }
	}
	//上传学员头像
	@Override
	public AjaxReturnMsg uploadHc61Photo(String userid, String file_bus_id, String file_name, MultipartFile multipartFile) {
		try {
			//保存图片到文件服务器，同时保存图片记录
			SFileRecord sFileRecord = fileLoadService.uploadFile(multipartFile, Param.TRAIN_GROUP_STUDENT_LOGO, file_name,
					file_bus_id);
			return this.success(sFileRecord);
		} catch (Exception e) {
			return this.error(e.getMessage());
		}
	}
	/**
     * 上传申请相关文件
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
            //保存图片到文件服务器，同时保存图片记录
            SFileRecord sFileRecord = fileLoadService.uploadFile(multipartFile, file_bus_type, file_bus_name,
                    file_bus_id, desc);
            return this.success("上传成功",sFileRecord);
        } catch (Exception e) {
            return this.error(e.getMessage());
        }
    }
    /**
	 * 通过主键删除图片
	 * @throws Exception 
	 */
	@Override
	@Transactional
	public AjaxReturnMsg<String> delPicById(Student stu) {
		try {
			//先删除图片描述数据，然后删除图片数据  
			int count_busFile = apiClassPlanMapper.delBusFilePicById(stu.getPicId());
			int count_file = apiClassPlanMapper.delFilePicById(stu.getPicId());
			if(count_file == 1 && count_busFile == 1) {
				return this.success("删除成功");				
			}else {
				return this.error("删除失败,请确定该图片已被删除");
			}
		}catch (Exception e) {
			e.printStackTrace();
			return this.error("删除失败,请确定该图片已被删除");
		}
	}
	
	/**
	 * 数据处理 增加到临时表
	 */
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
		ApiPersonTrnQuaMapper mapper = session.getMapper(ApiPersonTrnQuaMapper.class);
		
		String aab001 = apiPersonTrnQuaMapper.getBaseinfoid(sExcelBatch.getExcel_batch_aae011());
		
		try {
			// excel行循环
			for (int i = 2; i < list.size(); i++) {
				String[] tempstr = list.get(i);
				
				Hc61_temp temp = new Hc61_temp();
				temp.setAcc117(sExcelBatch.getExcel_batch_number());//导入临时表之导入批次号
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
					// 手动每5000个一提交，提交后无法回滚
					session.commit();
					// 清理缓存，防止溢出
					session.clearCache();
					JvmMemoryUtil.showJvmMemory();
					//更新当前导入数据状态 10%+20%的百份比 导入临时表给30%的分段比例
					int statusnumber=new Float((i+0f)/(list.size()+0f)*20).intValue()+10;
					sExcelBatch.setExcel_batch_data_status(statusnumber);
					apiFileUploadMapper.updateExelBatch(sExcelBatch);
				}
			}
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
		//更新文件记录
		apiFileUploadMapper.updateExelBatch(sExcelBatch);
	}

	/**
	 * 执行数据处理过程
	 * @param executeProc
	 */
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
		        try {  
		    		   SqlSession session = sqlSessionTemplate.getSqlSessionFactory().openSession(false);
		    		   // 通过新的session获取mapper
		    		   ApiClassPlanMapper mapper = session.getMapper(ApiClassPlanMapper.class);
		        	    Date start=new Date();
						log.info("调用过程开始时间"+new Date().toLocaleString());
						//调用过程处理数据
						mapper.executeProc(newsexcelbatch);
						Date end=new Date();
						Long cost=end.getTime()-start.getTime();
						log.info("调用过程结束时间"+new Date().toLocaleString()+"花费"+cost/1000+"s");
						//执行是否成功
						if(newsexcelbatch.getExcel_batch_rt_code() .equals("1")){
							
						}else{
							log.info("导入数据失败,失败原因"+newsexcelbatch.getExcel_batch_rt_msg());
						}
		        } catch (Exception e) {  
		            // TODO Auto-generated catch block  
		            e.printStackTrace();  
		        }  
//		    }  
//		});  
	}
	/**
	 * 批量导入报名保存
	 */
	@Override
	public AjaxReturnMsg signUpSave(Hc61_temp temp) throws Exception {
		List<Hc61_temp> list=apiPersonTrnQuaMapper.getPersonCodeList(temp);
		//校验导入人员是否超过了实际计划当前可报开班人数
		int count_People = Integer.parseInt(apiClassPlanMapper.getPeople(temp));
		if(list.size() > count_People) {
			return this.error("您好，当前报名人数超过该计划剩余可报名人数，请修改计划培训人数");			
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
				//报名
				int count_hc61 = apiClassPlanMapper.signUp(stu);
				if(count_hc61 == 0) {
					return this.error("学员报名失败，请联系系统管理员");
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
					return this.error("时间转换出错");
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
				//报名
				int count_hc61 = apiClassPlanMapper.signUp(s);
				if(count_hc61 == 0) {
					return this.error("学员报名失败，请联系系统管理员");
				}
			}
			apiPersonTrnQuaMapper.updatePerson(t);
		}
		return this.success("报名成功");
	}
	/**
	 * 导出word所需信息
	 */
	@Override
	public AjaxReturnMsg exportDJK(Student stu) {
		stu = apiClassPlanMapper.exportDJK(stu);
		//图片暂时不导
//		if(StringUtil.isNotEmpty(stu.getPhoto_url())){
//			//将图片域名拼接上去
//			String fileModule = AppConfig.getProperties("fileModule");
//			stu.setPhoto_url(fileModule+stu.getPhoto_url());
//		}
		return this.success(stu);
	}
	
	
}
