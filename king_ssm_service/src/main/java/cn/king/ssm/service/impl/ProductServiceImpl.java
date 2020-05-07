package cn.king.ssm.service.impl;

import cn.king.ssm.dao.ProductDao;
import cn.king.ssm.domain.Product;
import cn.king.ssm.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 产品业务层接口的实现类
 */
@Service("productService")
@Transactional
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductDao productDao;

    //查询所有产品信息
    @Override
    public List<Product> findAll() throws Exception {
        return productDao.findAll();
    }

    //添加产品
    @Override
    public void save(Product product) throws Exception {
        productDao.save(product);
    }
}
