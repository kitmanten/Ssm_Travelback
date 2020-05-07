package cn.king.ssm.service;

import cn.king.ssm.domain.Product;

import java.util.List;

/**
 * 产品业务层接口
 */
public interface ProductService {

    //查询所有产品信息
    public List<Product> findAll() throws Exception;
    //保存产品
    public void save(Product product)throws Exception;
}
