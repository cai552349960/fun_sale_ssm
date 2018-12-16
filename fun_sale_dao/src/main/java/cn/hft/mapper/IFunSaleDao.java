package cn.hft.mapper;


import cn.hft.entity.FunSale;
import org.apache.ibatis.annotations.*;

import java.util.List;
public interface IFunSaleDao {
    /**
     * 查询所有列表
     * @return
     */
    @Select("select SALE_ID, SALE_SUBJECT ,BUILD_NAME, TRADE_ADDR, SALE_ROOM, SALE_INNERAREA, REGION_NAME, SECTION_NAME, UPDATE_TIME, SALE_TOTAL_PRICE, SALE_UNIT_PRICE from [FUN_SALE]")
    public List<FunSale> findAll();

    /**
     * 根据saleId查询结果
     * @param saleID 房源ID
     * @return
     */
    @Select("select SALE_ID, SALE_SUBJECT ,BUILD_NAME, TRADE_ADDR, SALE_ROOM, SALE_INNERAREA, REGION_NAME, SECTION_NAME, UPDATE_TIME, SALE_TOTAL_PRICE, SALE_UNIT_PRICE from [FUN_SALE] where SALE_ID=#{saleID}")
    public FunSale findById(Integer saleID);

    /**
     * 根saleID修改对对象
     * @param funSale 房源对象
     */
    @Update("update [FUN_SALE] set  SALE_SUBJECT=#{saleSubject},BUILD_NAME=#{buildName},TRADE_ADDR=#{tradeAddr},SALE_ROOM=#{saleRoom},SALE_INNERAREA=#{saleInnerarea},REGION_NAME=#{regionName},SECTION_NAME=#{sectionName},UPDATE_TIME=#{updateTime},SALE_TOTAL_PRICE=#{saleTotalPrice},SALE_UNIT_PRICE=#{saleUnitPrice} wherer SALE_ID=#{saleID}")
    public void updateByFunSale(FunSale funSale);

    /**
     * 添加出售房源
     * @param funSale
     */
    @Insert( "insert into [FUN_SALE](COMP_ID,CITY_ID,DEPT_ID,CREATION_TIME,SALE_NO,SALE_USEAGE,SALE_SUBJECT,NUMERIC,SALE_SOURCE,SALE_EXPLRTH,BUILD_NAME,TRADE_ADDR,SALE_ROOM,SALE_INNERAREA,REGION_NAME,SECTION_NAME,UPDATE_TIME,SALE_TOTAL_PRICE,SALE_UNIT_PRICE," +
            "SALE_CONSIGN,SALE_MAP,PLATE_TYPE,SALE_STATUS,INFO_TYPE,SHARE_FLAG,RED_FLAG,FROM_PUBLIC,SALE_ID_OLD,HOUSE_BARGAIN,PANORAMA_MAP,YOUYOU_DEAL,IS_SALE_LEASE,HOUSE_SITUATION,OLD_TRUE_FLAG) VALUES(#{compID},#{cityID},#{deptID},#{creationTime},#{saleNo},#{saleUseage},#{saleSubject},#{numeric},#{saleSource},#{saleExplrth},#{buildName}," +
            "#{tradeAddr},#{saleRoom},#{saleInnerarea},#{regionName},#{sectionName},#{updateTime},#{saleTotalPrice},#{saleUnitPrice},#{saleConsign},#{saleMap},#{plateType},#{saleStatus},#{infoType},#{shareFlag},#{redFlag},#{fromPublic},#{saleIdOld},#{houseBargain},#{panoramaMap},#{youyouDeal},#{isSaleLease},#{houseSituation},#{oldTrueFlag}) ")
    public void insert(FunSale funSale);

    /**
     *删除出售房源数据
     * @param saleID
     */
    @Delete("delete from [FUN_SALE] where SALE_ID=#{saleID}")
    public void deleteById(Integer saleID);

}