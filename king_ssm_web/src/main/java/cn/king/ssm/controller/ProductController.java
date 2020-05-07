package cn.king.ssm.controller;

import cn.king.ssm.domain.Product;
import cn.king.ssm.service.ProductService;
import cn.king.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;
import java.util.List;

/**
 * 产品控制器
 */
@Controller
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    //添加产品
    @RequestMapping("/save.do")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String saveProduct(Product product) throws Exception {
        productService.save(product);
        return "redirect:findAll.do";
    }
    //查询所有产品信息
    @RequestMapping("/findAll.do")
    public ModelAndView findAll () throws Exception {
        ModelAndView mv = new ModelAndView();
        List<Product> products = productService.findAll();
        mv.addObject("productList",products);
        mv.setViewName("product-list");
        return mv;
    }
}
