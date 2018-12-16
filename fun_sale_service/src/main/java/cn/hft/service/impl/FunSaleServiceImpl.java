package cn.hft.service.impl;

import cn.hft.entity.FunSale;
import cn.hft.mapper.IFunSaleDao;
import cn.hft.service.IFunSaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@Transactional
public class FunSaleServiceImpl implements IFunSaleService {
    @Autowired
    private IFunSaleDao funSaleDao;
    @Override
    public List<FunSale> findAll() {
        return funSaleDao.findAll();
    }

    @Override
    public FunSale findById(Integer saleID) {
        return funSaleDao.findById(saleID);
    }

    @Override
    public void updateByFunSale(FunSale funSale) {
        funSaleDao.updateByFunSale(funSale);
    }

    @Override
    public void insert(FunSale funSale) {
        funSaleDao.insert(funSale);
    }

    @Override
    public void deleteById(Integer saleID) {
        funSaleDao.deleteById(saleID);
    }
}