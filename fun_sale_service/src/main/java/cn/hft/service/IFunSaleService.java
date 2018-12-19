package cn.hft.service;

import cn.hft.entity.FunSale;

import java.util.List;

public interface IFunSaleService {
    /**
     * 分页查询所有列表
     * @return
     */
    public List<FunSale> findAll();

    public List<FunSale> findPage(Integer pageNum, Integer pageSize);
    /**
     * 根据saleId查询结果
     * @param saleID 房源ID
     * @return
     */
    public FunSale findById(Integer saleID);

    /**
     * 根saleID修改对对象
     * @param funSale 房源对象
     */
    public void updateByFunSale(FunSale funSale);

    /**
     * 添加出售房源
     * @param funSale
     */
    public void insert(FunSale funSale);

    /**
     *删除出售房源数据
     * @param saleID
     */
    public void deleteById(Integer saleID);
}
