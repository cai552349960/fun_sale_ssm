package cn.hft.controller;

import cn.hft.entity.FunSale;
import cn.hft.entity.Result;
import cn.hft.service.IFunSaleService;
import cn.hft.service.impl.FunSaleServiceImpl;
import com.github.pagehelper.PageInfo;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/funSale")
public class FunSaleController {
    @Autowired
    private IFunSaleService funSaleService;
    @RequestMapping("/findAll.do")
    @ResponseBody
    public PageInfo<FunSale> findAll(@RequestParam(name = "page",required = true,defaultValue = "1")Integer page,@RequestParam(name="size",required = true,defaultValue = "20")Integer size) throws Exception {
        System.out.println("进入findAll方法");
        System.out.println(page);
        System.out.println(size);
        List<FunSale> all = null;
        try {
            all = funSaleService.findAll(page,size);
        } catch (Exception e) {
            e.printStackTrace();
        }
        PageInfo<FunSale> funSalePageInfo = new PageInfo<>(all);
       /* funSalePageInfo.setPageNum(page);
        funSalePageInfo.setPageSize(size);*/
        List<FunSale> list = funSalePageInfo.getList();
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }
        System.out.println(list.size());
        return funSalePageInfo;
    }

    @RequestMapping("/findById")
    @ResponseBody
    public FunSale findById(@RequestParam(name = "saleId", required = true, defaultValue = "1") Integer saleId) {
        return funSaleService.findById(saleId);
    }

    /**
     * 根saleID修改对对象
     *
     * @param funSale 房源对象
     */
    @RequestMapping("/updateByFunSale")
    @ResponseBody
    public Result updateByFunSale(@RequestBody FunSale funSale) {
        try {
            funSaleService.updateByFunSale(funSale);
            return new Result(1, "修改成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(0, "修改失败");
        }
    }

    /**
     * 添加出售房源
     *
     * @param funSale
     */
    @RequestMapping("/insert")
    @ResponseBody
    public Result insert(@RequestBody FunSale funSale) {
        try {
            funSaleService.insert(funSale);
            return new Result(1, "添加成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(0, "添加失败");
        }
    }

    /**
     * 删除出售房源数据
     *
     * @param saleID
     */
    @RequestMapping("/deleteById")
    @ResponseBody
    public Result deleteById(@RequestParam(name = "saleId", required = true, defaultValue = "1")Integer saleID) {
        try {
            funSaleService.deleteById(saleID);
            return new Result(1, "删除成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(0, "删除失败");
        }
    }
    @RequestMapping("/createXml")
    @ResponseBody
    public Result createXml(@RequestParam(name = "page",required = true,defaultValue = "1")Integer page,@RequestParam(name="size",required = true,defaultValue = "20")Integer size) throws Exception {
        try {
            List<FunSale> all = findAll(page, size).getList();
            createXml(all);
            return new Result(1,"生成xml文件成功，请查看D://centreForSSM.xml文件。");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(0,"生成xml文件失败。");
        }
    }
    public boolean createXml(List<FunSale> list){
        boolean b=false;
        Set hset = new HashSet<>();
        Document doc= DocumentHelper.createDocument();//创建document
        Element schoolEle=doc.addElement("funSale");//添加根元素
        schoolEle.addComment("文档的根funSale已经创建。");//添加注释
        List<FunSale> al = list;
        for (FunSale funSale : al) {
            System.out.println(funSale);
        }
        for (int i = 0; i < al.size(); i++) {
            FunSale funSale=al.get(i);
            hset.add(funSale.getSaleID());
        }
        for (Iterator<Integer> it = hset.iterator(); it.hasNext();) {
            Integer saleId=it.next();
            Element classEle=schoolEle.addElement("saleId");
            classEle.addAttribute("saleId", saleId+"");
            System.out.println(saleId);
            for (int i = 0; i < al.size(); i++) {
                FunSale funSale=al.get(i);
                if (saleId == funSale.getSaleID()) {
                    Element UserEle=classEle.addElement("funSale");
                    UserEle.addElement("saleID").addText(funSale.getSaleID()+"");
                    UserEle.addElement("saleSubject").addText(funSale.getSaleSubject()==null?"无":funSale.getSaleSubject()+"");
                    UserEle.addElement("buildName").addText(funSale.getBuildName()==null?"无":funSale.getBuildName()+"");
                    UserEle.addElement("tradeAddr").addText(funSale.getTradeAddr()==null?"无":funSale.getTradeAddr()+"");
                    UserEle.addElement("saleRoom").addText(funSale.getSaleRoom()+"");
                    UserEle.addElement("saleInnerarea").addText(funSale.getSaleInnerarea()+"");
                    UserEle.addElement("regionName").addText(funSale.getRegionName()+"");
                    UserEle.addElement("sectionName").addText(funSale.getSectionName()+"");
                    UserEle.addElement("updateTime").addText(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(funSale.getUpdateTime()));
                    if (funSale.getSaleTotalPrice() == null) {
                        funSale.setSaleTotalPrice(new BigDecimal(0));
                    }
                    UserEle.addElement("saleTotalPrice").addText(funSale.getSaleTotalPrice().toString());
                    if (funSale.getSaleUnitPrice() == null) {
                        funSale.setSaleUnitPrice(new BigDecimal(0));
                    }
                    UserEle.addElement("saleUnitPrice").addText(funSale.getSaleUnitPrice().toString());
                    System.out.println(i);
                }
            }
        }

        try {
            /*自己查帮助文档
             * OutputFormat format=new OutputFormat("  ",true,"gb2312");
             */
            /*
             * 创建一个漂亮的打印格式的OutputFormat
             * 可以通过setEncoding来设置其传输字符串，默认为utf-8
             */
            OutputFormat format = OutputFormat.createPrettyPrint();
            format.setEncoding("utf-8");
   /*创建缩进格式的OutputFormat
   format = OutputFormat.createCompactFormat();
   */
            File file = new File("D://centreForSSM.xml");
            if (file.exists()) {
                file.createNewFile();
            }
            XMLWriter writer = new XMLWriter(new FileWriter(file),format);
            writer.write(doc);
            writer.close();
            b=true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return b;
    }

}
