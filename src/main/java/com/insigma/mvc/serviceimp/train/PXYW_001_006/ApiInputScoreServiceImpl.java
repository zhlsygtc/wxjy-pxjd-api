package com.insigma.mvc.serviceimp.train.PXYW_001_006;

import java.util.List;
import javax.annotation.Resource;
import com.insigma.mvc.dao.train.PXYW_001_013.ApiHeadTeacherMapper;
import com.insigma.mvc.model.train.Hb57;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.insigma.dto.AjaxReturnMsg;
import com.insigma.mvc.MvcHelper;
import com.insigma.mvc.dao.train.PXYW_001_001.ApiCompanyMapper;
import com.insigma.mvc.dao.train.PXYW_001_006.ApiInputScoreMapper;
import com.insigma.mvc.model.train.Hb68;
import com.insigma.mvc.model.train.Student;
import com.insigma.mvc.model.train.Zc13;
import com.insigma.mvc.service.train.PXYW_001_006.ApiInputScoreService;
/**
 * 开班申请
 * @author zhanghl
 * 2018-01-10
 */
@Service
public class ApiInputScoreServiceImpl extends MvcHelper implements ApiInputScoreService {
	
	@Resource
	private ApiInputScoreMapper inputScoreMapper;
	@Resource
	private ApiCompanyMapper apiCompanyMapper;
    @Resource
    private ApiHeadTeacherMapper apiHeadTeacherMapper;

	/**
	 * 查询培训开班列表
	 */
	@Override
	public PageInfo<Hb68> getInfoList(Hb68 hb68) throws Exception {
		PageHelper.offsetPage(hb68.getOffset(), hb68.getLimit());
		if(StringUtils.isNotEmpty(hb68.getChb103())) {
			hb68.setChb103s(hb68.getChb103().split(","));
		}
		if(StringUtils.isNotEmpty(hb68.getChn198())) {
			hb68.setChn198s(hb68.getChn198().split(","));
		}
		Hb57 hb57 = apiHeadTeacherMapper.getHeadTeacherByUserId(hb68.getAae011());
		if(hb57 != null && "02".equals(hb57.getChb299())) {
		    hb68.setChb057(hb57.getChb057());
        }
		List<Hb68> list=inputScoreMapper.getInfoList(hb68);
		PageInfo<Hb68> pageinfo = new PageInfo<Hb68>(list);
		return pageinfo;
	}
	/**
	 * 根据ID查询班级信息
	 */
	@Override
	public Hb68 getById(Hb68 hb68) throws Exception {
		String aaa005 = apiCompanyMapper.getAa01ByAaa001("AREA_ID");
		if("320904".equals(aaa005)) {
			hb68=inputScoreMapper.getById_df(hb68.getChb100());
		}else if("610000".equals(aaa005)){
			hb68=inputScoreMapper.getById_sx(hb68.getChb100());
	    }else{
			hb68=inputScoreMapper.getById(hb68.getChb100());
		}
		return hb68;
	}
	/**
	 * 查询学员成绩
	 */
	@Override
	public PageInfo<Student> getStuListForLook(Student stu) throws Exception {
		PageHelper.offsetPage(0, 1000);
		List<Student> list=inputScoreMapper.getStuListForLook(stu.getChb100());
		PageInfo<Student> pageinfo = new PageInfo<Student>(list);
		return pageinfo;
	}
	/**
	 * 根据专业名称查询培训工种名称及专业类别
	 */
	public Hb68 getAca112(String aca110) throws Exception{
		Hb68 hb68 =(Hb68) inputScoreMapper.getAca112(aca110);
		return hb68;
	}
	/**
	 * 新增学员至学员表hc60——勾选合格与不合格
	 */
	public AjaxReturnMsg doQualified(Student stu) throws Exception{
		try{
			//flag为true则表示页面上存在成绩，无论数据库有无成绩，都执行一遍清空成绩操作
			if("true".equals(stu.getFlag())) {
				//清空理论操作综合成绩
				inputScoreMapper.clearHc66(stu.getChb100());
			}
			//检查该学员是否在66表中   不在则生成66表
			for(String chc001:stu.getChc001s()) {
				stu.setChc001(chc001);
				String count_hc66 = inputScoreMapper.checkHc66(stu.getChc001());
				if("0".equals(count_hc66)) {//该学员没有在66表中，需要生成
					inputScoreMapper.createHc66(stu);
				}
				//录入学员成绩
				inputScoreMapper.updateHc66(stu);
			}
			//录入学员成绩则改为部分录入
			inputScoreMapper.updateHb68PartStatus(stu);
			//检查是否学员都被录入了成绩，都被录入了则修改班级录入状态
			inputScoreMapper.updateHb68Status(stu);
			return this.success("录入成功且成绩信息已保存");
		}catch(Exception e){
			e.printStackTrace();
			return this.error("录入失败，错误原因:"+e);
	    }
	}
	/**
	 * 新增学员至学员表hb60——手动录入成绩
	 */
	public AjaxReturnMsg saveScore(Student stu) throws Exception{
		try{
			String contentValue = stu.getContentVal();
			String[] contents = contentValue.split(";");
			String aac002;
			String chc014;
			String chc019;
			String chc016;
			int count;
			int count_check = 0;//校验页面上录入的成绩门数是否一致
			Zc13 zc13 = getZc13(stu.getAab001());
			Double czc910 = zc13.getCzc910();//理论合格成绩
			Double czc912 = zc13.getCzc912();//实操合格成绩
			Double czc914 = zc13.getCzc914();//综合合格成绩
			//检查该学员是否在66表中   不在则生成66表
			for(int i=0;i<contents.length;i++) {
				String aac002Score = contents[i];
				count = 0;
				aac002 = aac002Score.split(",")[0];
				chc014 = aac002Score.split(",")[1];
				chc019 = aac002Score.split(",")[2];
				chc016 = aac002Score.split(",")[3];
				stu.setAac002(aac002);
				//如果三个成绩都为空则合格标志不录入
				if(("".equals(chc014) || "-".equals(chc014))
						&& ("".equals(chc016) || "-".equals(chc016))
						&& ("".equals(chc019) || "-".equals(chc019))) {
				}else {
					stu.setChc018("1");//有任意一门成绩则初始值设为合格
				}
				if(!"".equals(chc014) && !"-".equals(chc014)) {
					stu.setChc014(chc014);//理论
					if(Double.parseDouble(chc014) < czc910) {
						stu.setChc018("0");//不合格
					}
					count+=1;
				}
				if(!"".equals(chc019) && !"-".equals(chc019)) {
					stu.setChc019(chc019);//实操
					if(Double.parseDouble(chc019) < czc912) {
						stu.setChc018("0");//不合格
					}
					count+=2;
				}
				if(!"".equals(chc016) && !"-".equals(chc016)) {
					stu.setChc016(chc016);//综合
					if(Double.parseDouble(chc016) < czc914) {
						stu.setChc018("0");//不合格
					}
					count+=3;
				}
				if(i==0) {//第一名学员的成绩门数做一个记录用于比较
					count_check = count;
				}
				if(count_check != count) {//如果不等 则表示成绩门数不一致 
					i++;
					return this.error("您好，第 <span style = \"color:orange\">"
										+i+"</span> 名学员成绩同其他学员成绩科目数不一致，请修改");
				}
				String count_hc66 = inputScoreMapper.checkByAac002AndChb100(stu);
				if("0".equals(count_hc66)) {//该学员没有在66表中，需要生成
					inputScoreMapper.createHc66ByAac002AndChb100(stu);
				}
				//录入学员成绩
				inputScoreMapper.updateHc66ByAac002AndChb100(stu);
				//更新学员可否发证
				inputScoreMapper.updateHc60Chc029(stu);
				//重置student
				stu.setChc014("");
				stu.setChc019("");
				stu.setChc016("");
				stu.setChc018("");
			}
			//录入学员成绩则改为部分录入
			inputScoreMapper.updateHb68PartStatus(stu);
			//检查是否学员都被录入了成绩，都被录入了则修改班级录入状态
			inputScoreMapper.updateHb68Status(stu);
			return this.success("手动录入成绩已保存成功");
		}catch(Exception e){
			e.printStackTrace();
			return this.error("手动录入成绩保存失败，错误原因:"+e);
	    }
	}
	/**
	 * 提交开班信息
	 * @throws Exception 
	 */
	@Override
	@Transactional
	public AjaxReturnMsg<String> submitScore(Hb68 hb68) {
		try {
			inputScoreMapper.submitScore(hb68);
			return this.success("提交成功");
		}catch (Exception e) {
			e.printStackTrace();
			return this.error("提交失败,请确定该班级成绩信息已提交成功");
		}
	}
	/**
	 * 撤销成绩信息
	 * @throws Exception 
	 */
	@Override
	@Transactional
	public AjaxReturnMsg<String> revokeScore(Hb68 hb68) {
		try {
			int count =inputScoreMapper.revokeScore(hb68);
			if(count==1){
				return this.success("撤销成功");
			}else{
				return this.error("撤销失败,请确定此人已经被撤销了");
			}
		}catch (Exception e) {
			e.printStackTrace();
			return this.error("撤销失败,请确定该班级已撤销成功");
		}
	}
	/**
	 * 查询成绩标准信息
	 */
	@Override
	public Zc13 getZc13(String aab001) throws Exception {
		//以此机构id查询groupid 以groupid查询成绩标准 一直查到为止   查不到则默认给0
		String groupid ;
		String areaId = inputScoreMapper.getAreaId();
		Zc13 zc13 = null;
		int count = 0;
		do {
			if(count > 20 || "G001".equals(aab001)) {//防止突发情况陷入死循环
				//部署区域没有标准则写0
				zc13 = new Zc13();
				zc13.setCzc909(0.00);
				zc13.setCzc910(0.00);
				zc13.setCzc911(0.00);
				zc13.setCzc912(0.00);
				zc13.setCzc913(0.00);
				zc13.setCzc914(0.00);
				return zc13;
			}
			if(areaId.equals(aab001)) {
				//部署区域没有标准则写0
				zc13 = new Zc13();
				zc13.setCzc909(0.00);
				zc13.setCzc910(0.00);
				zc13.setCzc911(0.00);
				zc13.setCzc912(0.00);
				zc13.setCzc913(0.00);
				zc13.setCzc914(0.00);
				return zc13;
			}
			groupid = inputScoreMapper.getGroupId(aab001);
			zc13=inputScoreMapper.getZc13(groupid);
			aab001 = groupid;
			count++;
		}while(zc13 == null);
		return zc13;
	}
}
