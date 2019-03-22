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

	// excel标准列名及对应指标名,用于格式检验及文件导出格式生成
	private static String[]HEADERS = new String[] {"公民身份号码,aac002","姓名,aac003","民族,aac005","就业失业登记证号码,chc003","人员类别,chc002","户口性质,chc008",
			                                       "户口所在地区划,eec357","常住地址区划,eec358","家庭住址,aac026","联系电话,aae005","邮编,aae007","电子邮箱,aae015",
			                                       "通讯地址,aae006","开户银行,aab024","银行户名,aab025","银行账号,aab026"};
	
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
			return this.success("删除成功");
		}else{
			return this.error("删除失败");
		}
	}
	
	
	@Override
	public AjaxReturnMsg confirmById(Student stu) {
		stu = apiPersonTrnQuaMapper.getStudentById(stu.getChc111());
		if(stu.getAca111()==null) {
			return this.error("请先完善学员的意向专业！");
		}
		int confirmnum = apiPersonTrnQuaMapper.confirmById(stu.getChc111());
		if(confirmnum == 1) {
			return this.success("确认学员成功");
		}else{
			return this.error("确认学员失败");
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
		taskExecutor.execute(new Runnable() {  
		    @Override  
		    public void run() {  
		        // TODO Auto-generated method stub  
		        try {  
		    		   SqlSession session = sqlSessionTemplate.getSqlSessionFactory().openSession(false);
		    		   // 通过新的session获取mapper
		    		   ApiPersonTrnQuaMapper mapper = session.getMapper(ApiPersonTrnQuaMapper.class);
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
			}
			apiPersonTrnQuaMapper.updatePerson(t);
		}
		return this.success("操作成功");
	}

	@Override
	public AjaxReturnMsg getStudentById(String chc111) {
		Student stu = apiPersonTrnQuaMapper.getStudentById(chc111);
		if(StringUtil.isNotEmpty(stu.getPhoto_url())){
			//将图片域名拼接上去
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
		
	  return this.success("保存成功", stu.getChc111());
	
	}

	@Override
	public AjaxReturnMsg uploadLogo(String userid, String file_bus_id, String file_name, MultipartFile multipartFile) {
		try {
			//保存图片到文件服务器，同时保存图片记录
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
			//将图片域名拼接上去
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
			return this.error("时间转换出错");
		}
		stu.setAac006(aac006);
		String str = stu.getAac002().substring(16, 17);
		int i = Integer.parseInt(str) % 2;
		if(i == 0) {
			stu.setAac004("2");
		}else{
			stu.setAac004("1");
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
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy");
        Date date = new Date();
        String nowDate_str = sdf1.format(date);
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
		stu.setAac006(aac006);
		stu.setIsselected("0");
		stu.setEae052("1");
		stu.setAae100("1");
		stu.setAab001(stu.getBaseinfoid());
		stu.setChc066(nextNo);
		
		int num = apiPersonTrnQuaMapper.saveDate(stu);
		if(num == 1) {
			return this.success("保存成功", stu.getChc111());
		}else{
			return this.error("保存失败");
		}
		
	}

	/**
     * 删除文件记录和真实文件
     *
     * @param bus_uuid 业务上传文件内码
     */
	public AjaxReturnMsg deleteFile(String bus_uuid) {
		SFileRecord sFileRecord = apiFileUploadMapper.getBusFileRecordByBusId(bus_uuid);
		if (sFileRecord == null) {
            return this.error("文件记录不存在");
        }

        //删除文件业务记录
        apiFileUploadMapper.deleteFileByBusUuid(bus_uuid);
        //删除文件记录
        apiFileUploadMapper.deleteFileByFileUuid(sFileRecord.getFile_uuid());
        //删除真实文件
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
				//将图片域名拼接上去
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
			Date date = new Date(); //当前时间
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
			String dateStr = sdf.format(date);
			int year = Integer.parseInt(dateStr.substring(0, 4)); //当前年份
			char sexnum = aac002.charAt(16); //性别标志数
			int yearPer = Integer.parseInt(aac002.substring(6, 10)); //出生年份
			int yearD = year - yearPer; //年龄(粗略)
			int day = Integer.parseInt(dateStr.substring(4)); //当前日期
			int dayPer = Integer.parseInt(aac002.substring(10,14)); //出生日期
			int add = -1;
			if(day >= dayPer) {
				add = 0;
			}
			yearD += add;
			if(sexnum % 2 == 0) {
				if(yearD < 16 || yearD > 50) {
					message = "女性学员年龄范围在"+wonDemand.split(",")[1]+"到"+wonDemand.split(",")[2]+"周岁";
				}
			}else{
				if(yearD < 16 || yearD > 60) {
					message = "男性学员年龄范围在"+manDemand.split(",")[1]+"到"+manDemand.split(",")[2]+"周岁";
				}
			}
		}
		return message;
	}

	//查询学员培训信息
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
