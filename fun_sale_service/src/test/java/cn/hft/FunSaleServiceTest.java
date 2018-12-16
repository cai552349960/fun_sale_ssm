package cn.hft;

import cn.hft.entity.FunSale;
import cn.hft.service.impl.FunSaleServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class) //表示继承了SpringJUnit4ClassRunner类
@ContextConfiguration(locations = { "classpath:applicationContext.xml" })
public class FunSaleServiceTest {
    @Test
    public void findAllTest() {
        FunSaleServiceImpl funSaleService = new FunSaleServiceImpl();
        List<FunSale> all = funSaleService.findAll();
        System.out.println(all);
    }
}
