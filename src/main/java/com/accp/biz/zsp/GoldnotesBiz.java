package com.accp.biz.zsp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.accp.dao.zsp.GoldnotesDao;
import com.accp.pojo.Banktype;
import com.accp.pojo.Evaluationservice;
import com.accp.pojo.Goldnotes;
import com.accp.pojo.Integralrecord;
import com.accp.pojo.Logistics;
import com.accp.pojo.Putforward;
import com.accp.pojo.Sharea;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service
@Transactional(propagation = Propagation.SUPPORTS, isolation = Isolation.READ_COMMITTED, readOnly = true)
public class GoldnotesBiz {
	
	@Autowired
    private GoldnotesDao dao;
	/**
	 * 金币流向表
	 * @param pageNum  
	 * @param pageSize
	 * @param userId用户id
	 * @return
	 */
    public  PageInfo<Goldnotes> goldnotesQueryAll(Integer pageNum,Integer pageSize,Integer userId) {
    	 PageHelper.startPage(pageNum,pageSize);
  	   return new PageInfo<Goldnotes>(dao.goldnotesQueryAll(userId));
    }
    /**
     * 查询积分流向
     * @param pageNum
     * @param pageSize
     * @param userId
     * @return
     */
    public PageInfo<Integralrecord>IntegralRecordQueryAll(Integer pageNum,Integer pageSize,Integer userId){
   	    PageHelper.startPage(pageNum,pageSize);
    	return new PageInfo<Integralrecord>(dao.IntegralRecordQueryAll(userId));
    }
    /**
     * 添加金币提现记录表
     * @param Goldnotes 
     */
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, readOnly = false)
	public void addPutforWard(Putforward  putforward) {
		dao.addPutforWard(putforward);
	}
    /**
     * 添加金币充值记录表
     * @param goldnotes
     */
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, readOnly = false)
	public void addGoldnotes(Goldnotes  goldnotes) {
    	dao.addGoldnotes(goldnotes);
	}
    /**
     * 查询物流记录
     * @param pageNum
     * @param pageSize
     * @param logistics
     * @return
     */
    public PageInfo<Logistics>getListLogistics(Integer pageNum,Integer pageSize,Logistics logistics){
    	PageHelper.startPage(pageNum, pageSize);
    	return new PageInfo<Logistics>(dao.getListlogistics(logistics));
    }
    /**
     * 查询商品服务评价记录
     * @param pageNum
     * @param pageSize
     * @param evaluationservice
     * @return
     */
  public PageInfo<Evaluationservice>getListEvaluationService(Integer pageNum,Integer pageSize,Evaluationservice evaluationservice){
	  PageHelper.startPage(pageNum, pageSize);
	  return new PageInfo<Evaluationservice>(dao.getListEvaluationService(evaluationservice));
  }
  /**
   * 查询银行类别
   * @return
   */
  public List<Banktype>getListBankType(){
	  return dao.getListBankType();
  }
  /**
   * 添加物流 
   * @param goldnotes
   */
  @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, readOnly = false)
 public void addLogistics(Logistics logistics) {
	 dao.addLogistics(logistics);
 }
  /**
   * 查询物流详情
   * @param id
   * @return
   */
  public Logistics getLogistics(Integer userId,Integer id) {
	  return dao.getLogistics(userId, id);
  }
  public List<Sharea>getShAreaById(Integer id) {
	  return dao.getShAreaById(id);
  }
}
