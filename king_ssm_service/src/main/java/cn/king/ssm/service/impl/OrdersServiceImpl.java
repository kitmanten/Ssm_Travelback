package cn.king.ssm.service.impl;

import cn.king.ssm.dao.OrdersDao;
import cn.king.ssm.domain.Orders;
import cn.king.ssm.service.OrdersService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
//订单业务层
@Service("orderService")
@Transactional
public class OrdersServiceImpl implements OrdersService {

    @Autowired
    private OrdersDao ordersDao;

    //查询所有订单及分页
    @Override
    public List<Orders> findAll(int page, int size) throws Exception {
        //分页：参数（pageNum）：开始页码（pagesSize）：每页显示条数；（必须加载dao操作的上面）
        PageHelper.startPage(page,size);
        return ordersDao.findAll();
    }
    //查询订单详情
    @Override
    public Orders findById(String ordersId) throws Exception {
        return ordersDao.findById(ordersId);
    }
}
