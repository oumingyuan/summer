package com.mingyuan.summer.controller;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.mingyuan.summer.domain.CompanyInformation;
import com.mingyuan.summer.domain.Order;
import com.mingyuan.summer.domain.ProductInfo;
import com.mingyuan.summer.mapper.CompanyInformationRepository;
import com.mingyuan.summer.mapper.OrderJpaRepository;
import com.mingyuan.summer.mapper.ProductInfoJpaRepository;
import com.mingyuan.summer.service.OrderProductLinkService;
import com.mingyuan.summer.tool.PropertiesUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.Jedis;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@RestController
@RequestMapping(value = "/product")
public class ProductInfoController {


    @Autowired
    private ProductInfoJpaRepository productInfoJpaRepository;

    @Autowired
    private CompanyInformationRepository companyInformationRepository;

    @Autowired
    private OrderProductLinkService OrderProductLinkService;


    /**
     * 保存商品信息
     *
     * @param loginData 商品信息
     */
    @RequestMapping(value = "/save")
    public void save_product(@RequestBody JSONObject loginData) {


        String product_id = loginData.getString("product_id");
        String name = loginData.getString("name");
        String bar_code = loginData.getString("bar_code");
        String comment = loginData.getString("comment");
        String amount = loginData.getString("amount");
        String standard = loginData.getString("standard");

        ProductInfo productInfo = new ProductInfo();
        productInfo.setProductId(product_id);
        productInfo.setName(name);
        productInfo.setBarCode(bar_code);
        productInfo.setComment(comment);
        productInfo.setAmount(Integer.parseInt(amount));
        productInfo.setStandard(Integer.parseInt(standard));

        productInfoJpaRepository.saveAndFlush(productInfo);


    }

    /**
     * 获取所有的产品信息列表，返回给前端页面，方便智能查询
     *
     * @return 产品信息列表
     */
    @RequestMapping(value = "/query")
    public List<ProductInfo> query_product() {

        List<ProductInfo> product_list = productInfoJpaRepository.findAll();

        System.out.println(product_list);

//        Collections.sort(product_list, new Comparator<ProductInfo>() {
//            public int compare(ProductInfo o1, ProductInfo o2) {
//                return o1.getAmount() - o2.getAmount();
//            }
//        });

        Comparator<ProductInfo> comparator_amount = Comparator.comparing(ProductInfo::getAmount);

        product_list.sort(comparator_amount);

        return product_list;

    }

    @RequestMapping(value = "/get_company_information")
    public List<CompanyInformation> get_company_information() {

        return companyInformationRepository.findAll();

    }

    /**
     * 鼠标单击操作
     * 1. 订单信息插入数据库
     * 2. 数据库的订单信息生成 PDF
     *
     * @param loginData 登录信息
     */
    @RequestMapping(value = "/insert_order")
    public Map<String, Object> insert_order(@RequestBody JSONObject loginData) throws Exception {

        Map<String, Object> resultMap = new HashMap<>();

        String flag = loginData.getString("flag");
        String total_number = loginData.getString("total_number");
        String total_price = loginData.getString("total_price");
        String company_id = loginData.getString("company_id");
        JSONArray order_list = loginData.getJSONArray("order_list");

        //时间转为字符串
        LocalDateTime date = LocalDateTime.now();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");

        DateTimeFormatter dateTimeFormatterShow = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        String id = date.format(dateTimeFormatter);  // 2014-11-07 14:10:36
        String dateShow = date.format(dateTimeFormatterShow);


        String orderId = flag + id;//两个表的关联字段

        /*
         * 订单的商家信息插入数据库
         */
        Order order = new Order();

        order.setId(orderId);
        order.setCompanyId(company_id);
        order.setTime(LocalDateTime.now());
        order.setType(flag);
        order.setTotalPrice(new BigDecimal(total_price));
        order.setTotalNumber(Integer.parseInt(total_number));

//        orderJpaRepository.saveAndFlush(order);

        /*
         * 订单和产品关系表插入数据
         */
        OrderProductLinkService.insertData(order_list, flag, orderId, order);


        /*
         * 生成 PDF
         *
         */
        OrderProductLinkService.generatePDF(orderId, dateShow);

        resultMap.put("orderId", orderId);

        Jedis jedis = new Jedis("localhost");

        //设置 redis 字符串数据
        jedis.set("orderId", orderId);


        return resultMap;


    }


    //文件下载相关代码
    @RequestMapping("/downloadPDF")
    public String downloadFile(HttpServletRequest request, HttpServletResponse response) {

        Jedis jedis = new Jedis("localhost");

        String orderId = jedis.get("orderId");

        String fileName = orderId + ".pdf";// 设置文件名，根据业务需要替换成要下载的文件名

        //设置文件路径
//        String realPath = "D:\\my_pdf\\";
        String realPath = PropertiesUtil.getProperties("pdf_path");
        File file = new File(realPath, fileName);

        if (file.exists()) {
            response.setContentType("application/force-download");// 设置强制下载不打开
            response.addHeader("Content-Disposition", "attachment;fileName=" + fileName);// 设置文件名
            byte[] buffer = new byte[1024];
            FileInputStream fis = null;
            BufferedInputStream bis = null;
            try {
                fis = new FileInputStream(file);
                bis = new BufferedInputStream(fis);
                OutputStream os = response.getOutputStream();
                int i = bis.read(buffer);
                while (i != -1) {
                    os.write(buffer, 0, i);
                    i = bis.read(buffer);
                }
                System.out.println("success");
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (bis != null) {
                    try {
                        bis.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if (fis != null) {
                    try {
                        fis.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        return null;
    }


}
