package cn.hft.controller;

import cn.hft.entity.FunSale;
import cn.hft.service.IFunSaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/funSale")
public class FunSaleController {
    @Autowired
    private IFunSaleService funSaleService;
    @RequestMapping("/findAll.do")
    public List<FunSale> findAll() throws Exception {
        System.out.println("进入findAll方法");
        List<FunSale> all = null;
        try {
            all = funSaleService.findAll();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(all);
        System.out.println(2);
        return all;
    }
}
