package com.accp.biz.lhy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.accp.dao.lhy.OrderDao;
import com.accp.dao.lhy.UserDao;
import com.accp.pojo.User;
import com.accp.vo.lhy.OrderInfo;
import com.accp.vo.lhy.Orders;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service
@Transactional(propagation = Propagation.SUPPORTS, isolation = Isolation.READ_COMMITTED, readOnly = true)
public class OrderBiz {
	@Autowired
	private OrderDao orderDao;
	@Autowired
	private UserDao userDao;

	/**
	 * 分页查询订单列表
	 * 
	 * @param order
	 *            订单
	 * @param page
	 *            页数
	 * @param size
	 *            行数
	 * @return
	 */
	public PageInfo<Orders> queryOrderList(Orders order, int page, int size) {
		PageHelper.startPage(page, size);
		return new PageInfo<Orders>(orderDao.queryOrderList(order));
	}

	/**
	 * 获取用户订单信息
	 * 
	 * @param userid
	 *            用户编号
	 * @return
	 */
	public OrderInfo queryOrderInfo(int userid) {
		int topay = 0, receipt = 0, tbconfirmed = 0, tbevaluated = 0;
		for (Orders o : orderDao.queryOrderList(new Orders(userid, null, null))) {
			switch (o.getOrderstatus()) {
			case 1:
				topay++;
				break;
			case 3:
				receipt++;
				break;
			case 4:
				tbconfirmed++;
				break;
			case 7:
				tbevaluated++;
				break;
			}
		}
		return new OrderInfo(topay, receipt, tbconfirmed, tbevaluated);
	}

	/**
	 * 根据编号获取订单
	 * 
	 * @param orderid
	 *            订单编号
	 * @return
	 */
	public Orders queryOrderById(String orderid) {
		return orderDao.queryOrderById(orderid);
	}

	/**
	 * 支付订单
	 * 
	 * @param usermoney
	 *            相对用户余额
	 * @param userid
	 *            用户编号
	 * @param order
	 *            订单
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, readOnly = false)
	public boolean payOrder(int usermoney, int userid, Orders order) {
		User user = userDao.queryUserById(userid);
		if (user.getUsermoney() < usermoney) {
			return false;
		}
		userDao.updateUserMoney(-usermoney, userid);
		return orderDao.updateOrder(order);
	}

	/**
	 * 修改订单
	 * 
	 * @param order
	 *            订单
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, readOnly = false)
	public boolean updateOrder(Orders order) {
		return orderDao.updateOrder(order);
	}

	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, readOnly = false)
	public boolean ok(int usermoney, int userid, Orders order) {
		userDao.updateUserMoney(usermoney, userid);
		return orderDao.updateOrder(order);
	}
}