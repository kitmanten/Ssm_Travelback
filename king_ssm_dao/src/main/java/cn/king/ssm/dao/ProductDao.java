package cn.king.ssm.dao;

import cn.king.ssm.domain.Product;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 产品持久层接口
 */

public interface ProductDao {

    //根据id查询产品信息
    @Select("select * from product where id=#{id}")
    public Product findById(String id);

    //查询所有产品信息
    @Select("select * from product")
    public List<Product> findAll() throws Exception;


    //保存产品信息
    @Insert("insert into product(productNum,productName,cityName,departureTime,productPrice,productDesc,productStatus) values(#{productNum},#{productName},#{cityName},#{departureTime},#{productPrice},#{productDesc},#{productStatus})")
    void save(Product product) throws Exception;
}
