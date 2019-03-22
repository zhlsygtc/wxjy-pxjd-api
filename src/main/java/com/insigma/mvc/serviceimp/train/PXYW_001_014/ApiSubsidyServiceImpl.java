package com.insigma.mvc.serviceimp.train.PXYW_001_014;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.insigma.dto.AjaxReturnMsg;
import com.insigma.mvc.MvcHelper;
import com.insigma.mvc.dao.train.PXYW_001_014.ApiSubsidyMapper;
import com.insigma.mvc.model.train.Hb50;
import com.insigma.mvc.model.train.Hb50Dto;
import com.insigma.mvc.model.train.Hb67;
import com.insigma.mvc.model.train.Hc60;
import com.insigma.mvc.model.train.Student;
import com.insigma.mvc.service.train.PXYW_001_014.ApiSubsidyService;

/**
 * 补贴申报
 * @author Ace
 *
 */
@Service
public class ApiSubsidyServiceImpl extends MvcHelper implements ApiSubsidyService{

	@Resource
	private ApiSubsidyMapper apiSubsidyMapper;
	
	/**
	 * 班期信息列表
	 * @param hb67 班期对象
	 * @return
	 * @throws Exception
	 */
	@Override
	public PageInfo<Hb67> getHb67List(Hb67 hb67) throws Exception {
		List<Hb67> list = apiSubsidyMapper.getHb67List(hb67);
		PageInfo<Hb67> pageInfo = new PageInfo<Hb67>(list);
		return pageInfo;
	}

	/**
     * 生成班期补贴信息
     *
     * @param hb67 班期对象
     * @return
     * @throws Exception
     */
	@Override
	@Transactional
	public AjaxReturnMsg generate(Hb67 hb67) throws Exception {

		Hb50 hb50 = new Hb50();
		hb50.setChb067(hb67.getChb067());
		hb50.setUserid(hb67.getUserid());
		hb50.setBaseinfoid(hb67.getBaseinfoid());
		//生成补贴申请表
		int num = apiSubsidyMapper.addHb50(hb50);
		boolean flag = true;
		if(num == 1) {
			//生成学员补贴信息表
			num  = apiSubsidyMapper.addHc50(hb50);
			if( num <= 0 ) {
				flag = false;
			}
		}else{
			flag = false;
		}
		if(flag) {
			return this.success("补贴信息生成完毕");
		}else{
			return this.error("补贴信息生成失败");
		}
	}

	/**
	 * 查询班期信息
	 */
	@Override
	public Hb67 getHb67ById(String chb067) throws Exception {
		return apiSubsidyMapper.getHb67ById(chb067);
	}
	
	/**
	 * 查询合并班级中学员列表
	 */
	@Override
	public PageInfo<Hc60> getHc60StuList(Hc60 hc60) throws Exception {
		PageHelper.offsetPage(hc60.getOffset(), hc60.getLimit());
		List<Hc60> list=apiSubsidyMapper.getHc60StuList(hc60);
		PageInfo<Hc60> pageinfo = new PageInfo<Hc60>(list);
		return pageinfo;
	}
	
	/**
	 * 查询班期下学员补贴列表
	 */
	@Override
	public PageInfo<Student> subsidyList(Student stu) {
		PageHelper.offsetPage(stu.getOffset(), stu.getLimit());
		List<Student> list=apiSubsidyMapper.subsidyList(stu);
		PageInfo<Student> pageinfo = new PageInfo<Student>(list);
		return pageinfo;
	}
	
	/**
	 * 删除学员补贴信息
	 * @param request
	 * @param stu 学员内码
	 * @return
	 */
	@Override
	public AjaxReturnMsg delete(Student stu) {
		int num = apiSubsidyMapper.delete(stu);
		if(num == 1) {
			return this.success("删除完毕");
		}else{
			return this.error("删除失败");
		}
	}
	
	/**
	 *  查询学员补写信息 
	 */
	@Override
	public Student getHc50ById(Student stu) {
		return apiSubsidyMapper.getHc50ById(stu);
	}
	
	/**
	 *  保存学员补贴信息
	 */
	@Override
	public AjaxReturnMsg update(Student stu) {
		int num = apiSubsidyMapper.update(stu);
		if(num == 1) {
			return this.success("保存成功");
		}else{
			return this.error("保存失败");
		}
	}
	
	/**
     * 重新生成班期补贴信息
     *
     * @param Hb50 补贴对象
     * @return
     * @throws Exception
     */
	@Override
	@Transactional
	public AjaxReturnMsg generateAgain(Hb50 hb50) {
		
		boolean flag = true;
		//删除现有学员补贴信息表
		int num = apiSubsidyMapper.deleteHc50(hb50);
		if(num > 0) {
			//删除现有补贴申请表
			num = apiSubsidyMapper.deleteHb50(hb50);
			if(num == 1) {
				//生成补贴申请表
				num = apiSubsidyMapper.addHb50(hb50);
				if(num == 1) {
					//生成学员补贴信息表
					num  = apiSubsidyMapper.addHc50(hb50);
					if( num <= 0 ) {
						flag = false;
					}
				}else{
					flag = false;
				}
				
			}else{
				flag = false;
			}
		}else{
			flag = false;
		}
		if(flag) {
			return this.success("补贴信息生成完毕");
		}else{
			return this.error("补贴信息生成失败");
		}
	}

	/**
     * 提交班期补贴信息
     *
     * @param hb67 班期对象
     * @return
     * @throws Exception
     */
	@Override
	public AjaxReturnMsg submit(Hb67 hb67) {
		int num = apiSubsidyMapper.submit(hb67);
		if(num == 1) {
			return this.success("提交成功");
		}else{
			return this.error("提交失败");
		}
	}
	@Override
	public AjaxReturnMsg getSubsidyById(String chb050) {
		Hb50Dto hb50 = apiSubsidyMapper.getSubsidyById(chb050);
		return this.success(hb50);
	}
	
	/**
	 * 导出培训补贴学员花名册
	 */
	@Override
	public List<Student> getexportStuBT(Student stu){
		List<Student> list = apiSubsidyMapper.getexportStuBT(stu);
        return list;
	}
}
