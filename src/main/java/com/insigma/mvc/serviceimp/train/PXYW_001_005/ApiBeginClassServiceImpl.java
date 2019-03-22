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
 * 开班申请
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
	
	// excel标准列名及对应指标名,用于格式检验及文件导出格式生成
	private static String[] HEADERS = new String[] {"上课日期,chb160", "课别(上课内容),chb158","课时,chb109","上课开始时间,chb167","上课结束时间,chb186","上课地点(教室名称),chb161","培训教师,aac003","教师身份证号,aac002","教材名称及出版社单位,chb162"};

	/**
	 * 查询培训开班列表
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
	 * 根据ID查询班级信息
	 */
	@Override
	public Hb68 getById(String chb100) throws Exception {
		Hb68 hb68=beginClassMapper.getById(chb100);
		return hb68;
	}
	/**
	 * 获取单位资质
	 */
	@Override
	public AjaxReturnMsg<List<CodeValue>> getAca111List(CodeValue codevalue) {
		List<CodeValue> list = beginClassMapper.getAca111List(codevalue);
		return this.success(list);
	}
	/**
	 * 获取上级部门
	 */
	@Override
	public AjaxReturnMsg<List<CodeValue>> getAab301List() {
		List<CodeValue> list = beginClassMapper.getAab301List();
		return this.success(list);
	}
	/**
	 * 查询该机构所属行政区划
	 */
	@Override
	public SmtGroup getCompanyAab301(String groupId) throws Exception {
		SmtGroup smtGroup=beginClassMapper.getCompanyAab301(groupId);
		return smtGroup; 
	}
	/**
	 * 根据id获取smtgroup对象
	 */
	@Override
	public SmtGroup getSmtGroupById(String groupId) throws Exception {
		SmtGroup smtGroup=beginClassMapper.getSmtGroupById(groupId);
		return smtGroup;
	}
	/**
	 * 查询学员库学员列表
	 */
	@Override
	public List<Student> getStuList(Student stu) throws Exception {
		//用户编号(机构编号、个人编号等)  此处暂时为空值  考虑在sys_user表里加个机构编号
		List<Student> list=beginClassMapper.getStuList(stu);
		return list;
	}
//	/**
//	 * 查询课程表信息
//	 */
//	@Override
//	public List<Hb69> getCourseList(Hb69 hb69) throws Exception {
//		//用户编号(机构编号、个人编号等)  此处暂时为空值  考虑在sys_user表里加个机构编号
//		List<Hb69> list=beginClassMapper.getCourseListForLook(hb69.getChb100());
//		return list;
//	}
	/**
	 * 查询可选学员信息
	 */
	@Override
	public List<Student> getCheck(Student stu)throws Exception {
		List<Student> list=beginClassMapper.getCheck(stu);
		return list;
	}
	/**
	 * 保存培训开班申报信息
	 */
	@Override
	public AjaxReturnMsg saveBaseInfo(Hb68 hb68) throws Exception {
		try{
			String chb100 = hb68.getChb100();
			if(StringUtils.isNotEmpty(chb100)){//修改办班信息
				//校验该办班信息与单位信息是否匹配
				beginClassMapper.updateBaseInfo(hb68);
				return this.success("您好，修改班级编号为【"+chb100+"】的开班基本信息成功", chb100);
			}else{
				//用户编号(机构编号、个人编号等)
				hb68.setAab001(hb68.getAab001());
				hb68.setAae011(hb68.getAae011());
				chb100 = beginClassMapper.getChb100();
				hb68.setChb100(chb100);
				beginClassMapper.saveBaseInfo(hb68);
				return this.success("您好，开班基本信息保存成功，班级编号为：【"+chb100+"】，请继续填写学员信息", chb100);
			}
		}catch(Exception e){
			e.printStackTrace();
			return this.error(e);
		}
	}
	/**
	 * 新增学员至学员表hb60
	 */
	public AjaxReturnMsg saveStu(Student stu) throws Exception{
		try{
			//插入学员
			for(String chc111:stu.getChc111s()) {
				stu.setChc111(chc111);
				beginClassMapper.saveStu(stu);				
			}
			//更新hb61表学员库学员状态
			beginClassMapper.updateHc61(stu);
			return this.success("您好，学员信息保存成功，请填写课程信息");
		}catch(Exception e){
			e.printStackTrace();
			return this.error(e);
	    }
	}
	/**
	 * 通过主键删除开班信息
	 * @throws Exception 
	 */
	@Override
	@Transactional
	public AjaxReturnMsg<String> delClass(Hb68 hb68) {
		try {
			beginClassMapper.updateStu(hb68);
			beginClassMapper.delClassStu(hb68);
			beginClassMapper.delClassBase(hb68);
			return this.success("删除成功");
		}catch (Exception e) {
			e.printStackTrace();
			return this.error("删除失败,请确定该班级已被删除");
		}
	}
	/**
	 * 提交开班信息
	 * @throws Exception 
	 */
	@Override
	@Transactional
	public AjaxReturnMsg<String> submitClass(Hb68 hb68) {
		try {
			beginClassMapper.submitClass(hb68);
			return this.success("提交成功");
		}catch (Exception e) {
			e.printStackTrace();
			return this.error("提交失败,请确定该班级已提交成功");
		}
	}
	/**
	 * 撤销开班信息
	 * @throws Exception 
	 */
	@Override
	@Transactional
	public AjaxReturnMsg<String> revokeClass(Hb68 hb68) {
		try {
			hb68 = beginClassMapper.checkStatus(hb68);
			//如果是已录入则提示无法撤销
			if("1".equals(hb68.getChn198())) {
				return this.error("撤销失败，当前班级成绩已录入，无法撤销");
			}
			int count =beginClassMapper.revokeClass(hb68);
			if(count==1){
				return this.success("撤销成功");
			}else{
				return this.error("撤销失败,请确定此班级已经被撤销了");
			}
		}catch (Exception e) {
			e.printStackTrace();
			return this.error("撤销失败,请确定该班级已撤销成功");
		}
	}
	/**
	 * 查询学员列表
	 */
	@Override
	public PageInfo<Student> getStuListForLook(Student stu) throws Exception {
		PageHelper.offsetPage(stu.getOffset(), stu.getLimit());
		List<Student> list=beginClassMapper.getStuListForLook(stu.getChb100());
		PageInfo<Student> pageinfo = new PageInfo<Student>(list);
		return pageinfo;
	}
	/**
	 * 查询课程表信息
	 */
	@Override
	public List<Hb69> getCourseListForLook(Hb69 hb69)throws Exception {
		PageHelper.offsetPage(hb69.getOffset(), hb69.getLimit());
		List<Hb69> list=beginClassMapper.getCourseListForLook(hb69.getChb100());
		return list;
	}
	/**
	 * 查询改变后的课程表信息
	 */
	@Override
	public List<Hb69> getCourseListForChange(Hb69 hb69)throws Exception {
		PageHelper.offsetPage(0, 1000);
		List<Hb69> list=beginClassMapper.getCourseListForLook(hb69.getChb100());
		return list;
	}
	/**
	 * 获取导入课程表
	 */
	@Override
	public PageInfo<Hb69Temp> getTempCourseList(Hb69Temp temp) {
		List<Hb69Temp> list=beginClassMapper.getTempCourseList(temp);
		PageInfo<Hb69Temp> pageinfo = new PageInfo<>(list);
		return pageinfo;
	}
	/**
	 *录入————校验加保存课程表hb69
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
			//清除掉该班级在缓存表中的数据
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
				//新增hb69Temp
				int count = beginClassMapper.insertHb69Temp(hb69Temp);
				if(count == 0) {
					return this.error("保存课程表失败，错误原因:保存到临时表失败");
				}
			}
			return this.success("您好，课程表信息保存成功");
		}catch(Exception e){
			e.printStackTrace();
			return this.error("保存场地信息失败，错误原因:"+e);
	    }
	}
	/**
	 * 导入---新增课程表hb69
	 */
	public AjaxReturnMsg saveCourseData(Hb69Temp hb69Temp) throws Exception{
		try{
			//清空69
			beginClassMapper.delHb69(hb69Temp.getChb100());
			//插入69表
			for(String chb069_temp:hb69Temp.getChb069s()) {
				hb69Temp.setChb069_temp((chb069_temp));
				beginClassMapper.insertHb69(hb69Temp);
			}
			return this.success("您好，课程表信息保存成功");
		}catch(Exception e){
			e.printStackTrace();
			return this.error(e);
	    }
	}
	/**
	 * 录入---新增课程表hb69
	 */
	public AjaxReturnMsg saveCourse(Hb69Temp hb69Temp) throws Exception{
		try{
			//清空69
			beginClassMapper.delHb69(hb69Temp.getChb100());
			//获取hb69Temp中的数据
			List<Hb69Temp> hb69TempList  = beginClassMapper.getHb69tempList(hb69Temp);
			//插入69表
			for(Hb69Temp HT:hb69TempList) {
				HT.setBaseinfoid(hb69Temp.getBaseinfoid());
				beginClassMapper.insertHb69Input(HT);
			}
			return this.success("您好，课程表信息保存成功");
		}catch(Exception e){
			e.printStackTrace();
			return this.error(e);
	    }
	}
	/**
	 * 修改课程表
	 */
	public AjaxReturnMsg saveCourseForChange(Hb69Temp hb69Temp) throws Exception{
		try{
			//如果课程表已调整过一次，则不保留当前课程表，将当前课程表删除
			String count = beginClassMapper.findHb69(hb69Temp.getChb100());
			if("0".equals(count)) {//首次课程表调整,将当前课程表改为无效
				beginClassMapper.changeToNoEffect(hb69Temp.getChb100());
			}else {//之前有调整过课程表则直接删掉当前有效的课程表
				beginClassMapper.delEffectHb69(hb69Temp.getChb100());				
			}
			//获取hb69Temp中的数据
			List<Hb69Temp> hb69TempList  = beginClassMapper.getHb69tempList(hb69Temp);
			//插入69表
			for(Hb69Temp HT:hb69TempList) {
				HT.setBaseinfoid(hb69Temp.getBaseinfoid());
				beginClassMapper.insertHb69InputForChange(HT);
			}
			return this.success("您好，课程表信息保存成功");
		}catch(Exception e){
			e.printStackTrace();
			return this.error(e);
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
		ApiBeginClassMapper mapper = session.getMapper(ApiBeginClassMapper.class);
		try {
			// excel行循环
			for (int i = 1; i < list.size(); i++) {
				String[] tempstr = list.get(i);
				
				Hb69Temp hb69Temp = new Hb69Temp();
				hb69Temp.setAcc117(sExcelBatch.getExcel_batch_number());//导入临时表之导入批次号
				hb69Temp.setChb069(UUIDGenerator.generate());
				hb69Temp.setChb160(tempstr[0]);//上课日期
				hb69Temp.setChb158(tempstr[1]);//课别(上课内容)
				hb69Temp.setChb109(tempstr[2]);//课时
				hb69Temp.setChb167(tempstr[3]);//上课开始时间
				hb69Temp.setChb186(tempstr[4]);//上课结束时间
				hb69Temp.setChb063(tempstr[5]);//上课地点(教室名称)
				hb69Temp.setAac003(tempstr[6]);//培训教师
				hb69Temp.setAac002(tempstr[7]);//培训教师身份证号码
				hb69Temp.setChb162(tempstr[8]);//教材名称及出版社单位
				hb69Temp.setChb068(sExcelBatch.getExcel_batch_assistid());//班级编号
				hb69Temp.setChc066(i);
				mapper.insertExcelTempData(hb69Temp);
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
				    	SqlSession session = sqlSessionTemplate.getSqlSessionFactory().openSession(false);
				    	// 通过新的session获取mapper
				    	ApiBeginClassMapper mapper = session.getMapper(ApiBeginClassMapper.class);
		        try {  
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
		    }  
		});  
	}
	/**
	 * 查询导入批次号
	 */
	@Override
	public AjaxReturnMsg getExcel_batch_number(Hb69Temp hb69Temp) {
		String excel_batch_number = beginClassMapper.getExcel_batch_number(hb69Temp);
		return this.success(excel_batch_number);
	}
	/**
	 * 通过id删除培训学员
	 */
	@Override
	@Transactional
	public AjaxReturnMsg<String> delStudent(String chc001) {
		int updatenum = beginClassMapper.updateHc61ById(chc001);
		int deletenum = beginClassMapper.delHc60(chc001);
		if(deletenum==1 && updatenum==1){
			return this.success("删除成功");
		}else{
			return this.error("删除失败,请确定此人已经被删除了");
		}
	
	}
	/**
	 * 根据专业名称查询培训工种名称及专业类别
	 */
	public Hb68 getAca112(String aca110) throws Exception{
		Hb68 hb68 =(Hb68) beginClassMapper.getAca112(aca110);
		return hb68;
	}
	/**
	 * 根据专业名称查询补贴标准
	 */
	public Hb68 getAca131(String aca109) throws Exception{
		Hb68 hb68 =(Hb68) beginClassMapper.getAca131(aca109);
		return hb68;
	}
}
