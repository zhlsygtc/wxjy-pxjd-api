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
 * 班级管理
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
		//如果是大丰则更改excel校验项
		if("320904".equals(localcity)) {
			// excel标准列名及对应指标名,用于格式检验及文件导出格式生成
			HEADERS = new String[] {"上课日期,chb160", "课别(上课内容),chb158","课时,chb109","上课开始时间,chb167","上课结束时间,chb186","教室类型,chb163","培训教师,aac003","教师身份证号,aac002","教材名称及出版社单位,chb162"};		
		}else {
			// excel标准列名及对应指标名,用于格式检验及文件导出格式生成
			HEADERS = new String[] {"上课日期,chb160", "课别(上课内容),chb158","课时,chb109","上课开始时间,chb167","上课结束时间,chb186","上课地点(教室名称),chb161","培训教师,aac003","教师身份证号,aac002","教材名称及出版社单位,chb162"};		
		}
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
		ApiClassMgmtMapper mapper = session.getMapper(ApiClassMgmtMapper.class);
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
				hb69Temp.setChb163(tempstr[5]);//教室类型
				hb69Temp.setChb063(tempstr[5]);//上课地点(教室名称)	
				hb69Temp.setAac003(tempstr[6]);//培训教师
				hb69Temp.setAac002(tempstr[7]);//培训教师身份证号码
				hb69Temp.setChb162(tempstr[8]);//教材名称及出版社单位
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
			    	ApiClassMgmtMapper mapper = session.getMapper(ApiClassMgmtMapper.class);
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
			if(StringUtils.isNotEmpty(chb100)){//修改办班信息
				apiClassMgmtMapper.updateBaseInfo(hb68);
				return this.success("您好，修改班级编号为【"+chb100+"】的开班基本信息成功", hb68.getChb068()+";"+hb68.getChb055());
			}else{
				
				String aaa005 = apiCompanyMapper.getAa01ByAaa001("AREA_ID");
				//如果是天门则使用天门市班级编号：机构字母缩写+4位年份+2位月份+3位流水 如TGZX201905008表示天工职校2019年5月份开班的班级，该班级是天门市2019年的第五个班级 
				if("429006".equals(aaa005)) {
					chb100 = apiClassMgmtMapper.getChb100Tm(hb68);
				}else {
					chb100 = apiClassMgmtMapper.getChb100();
				}
				hb68.setChb100(chb100);
				apiClassMgmtMapper.saveBaseInfo(hb68);
				apiClassMgmtMapper.addRelation(hb68);
				return this.success("您好，开班基本信息保存成功，班级编号为：【"+chb100+"】，请继续填写学员信息", hb68.getChb068()+";"+hb68.getPlan());
			}
		}catch(Exception e){
			e.printStackTrace();
			return this.error(e);
		}
	}

	@Override
	public List<Student> getStuList(Student stu) {
		//用户编号(机构编号、个人编号等)  此处暂时为空值  考虑在sys_user表里加个机构编号
		List<Student> list=apiClassMgmtMapper.getStuList(stu);
		return list;
	}

	@Override
	public Hb68 getById(String chb068) {
		Hb68 hb68=apiClassMgmtMapper.getById(chb068);
		return hb68;
	}

	/**
	 * 查询可选学员信息
	 */
	@Override
	public List<Student> getCheck(Student stu) {
		List<Student> list = apiClassMgmtMapper.getCheck(stu);
		return list;
	}

	/**
	 * 通过主键删除开班信息
	 * @throws Exception 
	 */
	@Override
	@Transactional
	public AjaxReturnMsg<String> delClass(Hb68 hb68) {
		try {
			apiClassMgmtMapper.delClassBase(hb68);
			apiClassMgmtMapper.delClassStu(hb68);
			apiClassMgmtMapper.delRelation(hb68);
			return this.success("删除成功");
		}catch (Exception e) {
			e.printStackTrace();
			return this.error("删除失败,请确定该班级已被删除");
		}
	}

	/**
	 * 新增学员至学员表hc60
	 */
	@Override
	public AjaxReturnMsg saveStu(Student stu) {
		try{
			//插入学员
			for(String chc111:stu.getChc111s()) {
				stu.setChc111(chc111);
				apiClassMgmtMapper.saveStu(stu);				
			}
			return this.success("您好，学员信息保存成功，请填写课程信息");
		}catch(Exception e){
			e.printStackTrace();
			return this.error(e);
	    }
	}

	/**
	 * 通过id删除培训学员
	 */
	@Override
	public AjaxReturnMsg<String> delStudent(String chc001) {
		int deletenum = apiClassMgmtMapper.delHc60(chc001);
		if(deletenum==1){
			return this.success("删除成功");
		}else{
			return this.error("删除失败,请确定此人已经被删除了");
		}
	}

	/**
	 * 查询课程表信息
	 */
	@Override
	public List<Hb69> getCourseListForLook(Hb69 hb69) {
		List<Hb69> list=apiClassMgmtMapper.getCourseListForLook(hb69.getChb068());
		return list;
	}

	/**
	 * 新增课程表hb69
	 */
	@Override
	@Transactional
	public AjaxReturnMsg saveCourseData(Hb69Temp hb69Temp) {
		try{
			//清空69
			apiClassMgmtMapper.delHb69(hb69Temp.getChb068());
			//插入69表
			for(String chb069_temp:hb69Temp.getChb069s()) {
				hb69Temp.setChb069_temp((chb069_temp));
				apiClassMgmtMapper.insertHb69(hb69Temp);
			}
			return this.success("您好，课程表信息保存成功");
		}catch(Exception e){
			e.printStackTrace();
			return this.error(e);
	    }
	}

	/**
	 * 获取导入课程表
	 */
	@Override
	public PageInfo<Hb69Temp> getTempCourseList(Hb69Temp temp) {
		List<Hb69Temp> list=apiClassMgmtMapper.getTempCourseList(temp);
		PageInfo<Hb69Temp> pageinfo = new PageInfo<>(list);
		return pageinfo;
	}

	/**
	 * 查询导入批次号
	 */
	@Override
	public AjaxReturnMsg getSysexcelbatch(Hb69Temp hb69Temp) {
		SysExcelBatch s_excel_batch = apiClassMgmtMapper.getSysexcelbatch(hb69Temp);
		return this.success(s_excel_batch);
	}

	/**
	 * 查询学员列表
	 */
	@Override
	public PageInfo<Student> getStuListForLook(Student stu) {
		PageHelper.offsetPage(stu.getOffset(), stu.getLimit());
		List<Student> list = apiClassMgmtMapper.getStuListForLook(stu.getChb068());
		PageInfo<Student> pageinfo = new PageInfo<Student>(list);
		return pageinfo;
	}

	/**
	 * 提交开班信息
	 * @throws Exception 
	 */
	@Override
	public AjaxReturnMsg<String> submitClass(Hb68 hb68) {
		try {
			apiClassMgmtMapper.submitClass(hb68);
			apiClassMgmtMapper.updatePlan(hb68);
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
			hb68 = apiClassMgmtMapper.checkStatus(hb68);
			//如果是已录入则提示无法撤销
			if("1".equals(hb68.getChn198())) {
				return this.error("撤销失败，当前班级成绩已录入，无法撤销");
			}
			apiClassMgmtMapper.revokeClass(hb68);
			return this.success("撤销成功");
		}catch (Exception e) {
			e.printStackTrace();
			return this.error("撤销失败,请确定该班级已撤销成功");
		}
	}

	/**
	 * 获取机构培训计划
	 */
	@Override
	public AjaxReturnMsg<List<CodeValue>> getPlans(CodeValue codevalue) {
		List<CodeValue> list = apiClassMgmtMapper.getPlans(codevalue);
		return this.success(list);
	}
	/**
	 * 导出学员花名册
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
	 * 导出开班申请确认表所需信息
	 */
	@Override
	public AjaxReturnMsg expClassSure(Hb68 hb68) {
		hb68 = apiClassMgmtMapper.expClassSure(hb68);
		return this.success(hb68);
	}
	/**
	 * 导出教学计划表所需信息
	 */
	@Override
	public List<Hb69> getClassCourse(Hb69 hb69){
		List<Hb69> list = apiClassMgmtMapper.getClassCourse(hb69);
        return list;
	}
    /**
     * 通过业务id获取文件列表
     */
    @Override
    public AjaxReturnMsg<Map<String, Object>> getClassFile(Hb68 hb68) {
        String fileModule = AppConfig.getProperties("fileModule");
        hb68.setFileModule(fileModule);
        List<Hb68> list = apiClassMgmtMapper.getClassFile(hb68);
        PageHelper.offsetPage(0,1000);//课程表信息显示在一页，方便查看
        PageInfo<Hb68> pageinfo = new PageInfo<>(list);
        return this.success(pageinfo);
    }
}
