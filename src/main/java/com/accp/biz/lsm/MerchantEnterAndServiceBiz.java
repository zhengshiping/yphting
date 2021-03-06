package com.accp.biz.lsm;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.accp.dao.lsm.MerchantEnterAndServiceDao;
import com.accp.pojo.Complainttype;
import com.accp.pojo.Languagetype;
import com.accp.pojo.Majortype;
import com.accp.pojo.Resouroe;
import com.accp.pojo.Servicedes;
import com.accp.pojo.Servicelevel;
import com.accp.pojo.Servicetype;
import com.accp.pojo.Sharea;
import com.accp.pojo.User;
import com.accp.vo.lsm.EsLevelVO;
import com.accp.vo.lsm.SameServiceVO;
import com.accp.vo.lsm.SerReserveVO;
import com.accp.vo.lsm.ServiceDetailInfo;
import com.accp.vo.lsm.ServiceMerchantInfo;
import com.accp.vo.lsm.ServiceSelect;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service
@Transactional(propagation = Propagation.SUPPORTS, isolation = Isolation.READ_COMMITTED, readOnly = true)
public class MerchantEnterAndServiceBiz {

	//@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, readOnly = false)
	@Autowired
	private MerchantEnterAndServiceDao dao;
	
	/**
	 * 商家入驻
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, readOnly = false)
	public int merchantMove(User user,float bond) {
		return dao.merchantMove(user,bond);
	}
	/**
	 * 根据服务列表条件查询服务
	 * @return
	 */
	public PageInfo queryServices(ServiceSelect obj,int num,int size){
		PageHelper.startPage(num, size);
		return new PageInfo(dao.queryServices(obj));
	}
	/**
	 * 查询服务类别
	 * @param stpid 服务类别父级编号，为空则代表查询最高级服务类别
	 * @return
	 */
	public List<Servicetype> queryServiceType(Integer stpid,Integer selectNum){
		return dao.queryServiceType(stpid, selectNum);
	}
	/**
	 * 根据一级服务类别查询对应的级别
	 * @param stpid
	 * @return
	 */
	public List<Servicelevel> queryServicelevel(Integer stid){
		return dao.queryServicelevel(stid);
	}
	/**
	 * 查询服务语言
	 * @return
	 */
	public List<Languagetype> queryLanguagetype(){
		return dao.queryLanguagetype();
	}
	/**
	 * 查询擅长专业
	 * @return
	 */
	public List<Majortype> queryMajortype(){
		return dao.queryMajortype();
	}
	/**
	 * 查询中韩行政地区地址信息
	 * @param pid 地址编号
	 * @return
	 */
	public List<Sharea> querySharea(Integer pid,boolean flag){
		return dao.querySharea(pid,flag);
	}
	/**
	 * 查询商家入驻需缴纳保证金金额
	 * @return
	 */
	public float queryBond() {
		return dao.queryBond();
	}
	/**
	 * 查询服务的商家信息
	 */
	public ServiceMerchantInfo queryServiceMerchantInfo(Integer uid,Integer sid) {
		return dao.queryServiceMerchantInfo(uid,sid);
	}
	/**
	 * 查询服务详情信息
	 * @param sid 服务编号
	 * @return
	 */
	public ServiceDetailInfo queryServiceDetailInfo(Integer sid) {
		return dao.queryServiceDetailInfo(sid);
	}
	/**
	 * 查询服务描述
	 * @param sid 服务编号
	 * @return
	 */
	public List<Servicedes> queryServiceDes(Integer sid){
		return dao.queryServiceDes(sid);
	}
	/**
	 * 查询服务评价
	 * @param sid
	 * @return
	 */
	public PageInfo queryEvaluationserviceVO(Integer num,Integer size,Integer sid){
		PageHelper.startPage(num, size);
		return new PageInfo(dao.queryEvaluationserviceVO(sid));
	}
	
	/**
	 * 查询服务评价星级人数
	 * @param sid
	 * @return
	 */
	public EsLevelVO queryEsLevelVO(Integer sid) {
		return dao.queryEsLevelVO(sid);
	}
	/**
	 * 查询同城同类型服务
	 * @param sid
	 * @return
	 */
	public List<SameServiceVO> querySameServiceVO(Integer sid){
		return dao.querySameServiceVO(sid);
	}
	/**
	 * 查询举报原因
	 */
	public List<Complainttype> queryComplainttype(){
		return dao.queryComplainttype();
	}
	/**
	 * 举报商家
	 * @param obj
	 * @return
	 */
	public int saveServiceReport(Integer businessID,Integer serviceID,Integer loginUserID,String stName) {
		return dao.saveServiceReport(businessID, serviceID, loginUserID, stName);
	}
	/**
	 * 查询资料类别
	 * @return
	 */
	public List<Resouroe> queryResouroe(){
		return dao.queryResouroe();
	}
	/**
	 * 提交预定
	 * @return
	 */
	public int submitReserve(SerReserveVO obj) {
		return dao.submitReserve(obj);
	}
}
