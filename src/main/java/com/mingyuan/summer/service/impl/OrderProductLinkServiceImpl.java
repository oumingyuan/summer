package com.mingyuan.summer.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.mingyuan.summer.domain.CompanyInformation;
import com.mingyuan.summer.domain.Order;
import com.mingyuan.summer.domain.OrderProductLink;
import com.mingyuan.summer.domain.ProductInfo;
import com.mingyuan.summer.mapper.CompanyInformationRepository;
import com.mingyuan.summer.mapper.OrderJpaRepository;
import com.mingyuan.summer.mapper.OrderProductLinkJpaRepository;
import com.mingyuan.summer.mapper.ProductInfoJpaRepository;
import com.mingyuan.summer.service.OrderProductLinkService;
import com.mingyuan.summer.tool.GeneratePDF;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;


@Service
public class OrderProductLinkServiceImpl implements OrderProductLinkService {

    @Autowired
    private OrderProductLinkJpaRepository orderProductLinkJpaRepository;

    @Autowired
    private OrderJpaRepository orderJpaRepository;


    @Autowired
    private ProductInfoJpaRepository productInfoJpaRepository;


    @Autowired
    private CompanyInformationRepository companyInformationRepository;


    /**
     * @param order_list 产品信息列表
     * @param flag       订单标志
     * @param orderId    订单ID
     * @param order      订单信息
     * @throws Exception 产品信息输入不全异常
     */
    @Transactional
    @Override
    public void insertData(JSONArray order_list, String flag, String orderId, Order order) throws Exception {

        orderJpaRepository.saveAndFlush(order);

        BigInteger big_id = orderProductLinkJpaRepository.get_big_id();

        big_id = Optional.ofNullable(big_id).orElse(new BigInteger("0"));

        List<OrderProductLink> list = new LinkedList<>();

        for (int i = 0; i < order_list.size(); i++) {

            BigInteger more = new BigInteger(String.valueOf(i + 1));
            BigInteger id_new = big_id.add(more);
            JSONObject ja = (JSONObject) order_list.get(i);

            String product_id = ja.getString("product_id");
            String unit_price = ja.getString("unit_price");
            String number = ja.getString("number");
            String total_price = ja.getString("total_price");

            if (StringUtils.isNotBlank(product_id) && StringUtils.isNotBlank(unit_price) && StringUtils.isNotBlank(number) && StringUtils.isNotBlank(total_price)) {

                OrderProductLink orderProductLink = new OrderProductLink();

                orderProductLink.setOrderId(orderId);
                orderProductLink.setRemark(flag);
                orderProductLink.setId(id_new);
                orderProductLink.setProductId(product_id);
                orderProductLink.setUnitPrice(new BigDecimal(unit_price));
                orderProductLink.setAmount(Integer.parseInt(number));
                orderProductLink.setTotalPrice(new BigDecimal(total_price));
                orderProductLink.setProductIndex(i + 1);

                list.add(orderProductLink);

            } else if (StringUtils.isBlank(product_id) && StringUtils.isBlank(unit_price) && StringUtils.isBlank(number) && StringUtils.isBlank(total_price)) {

                System.out.println("此行没数据");

            } else throw new Exception("页面输入的数据异常");

        }

        orderProductLinkJpaRepository.saveAll(list);

    }

    @Override
    public void generatePDF(String orderId, String dateShow) {
        Optional<Order> order = orderJpaRepository.findById(orderId);
        Optional<CompanyInformation> c = companyInformationRepository.findById(order.get().getCompanyId());

        List<String> datas = new LinkedList<>();

        datas.add(c.get().getName());
        datas.add(c.get().getPhone1());
        datas.add(dateShow);
        datas.add(c.get().getAddress());
        datas.add(c.get().getPhone2());
        datas.add(orderId);

        List<OrderProductLink> orderProductLinks = orderProductLinkJpaRepository.queryProductByOrderId(orderId);

        for (OrderProductLink o : orderProductLinks) {

            Optional<ProductInfo> product_info = productInfoJpaRepository.findById(o.getProductId());

            datas.add(o.getProductId());
            datas.add(product_info.get().getBarCode());
            datas.add(product_info.get().getName());
            datas.add(String.valueOf(product_info.get().getStandard()));
            datas.add(String.valueOf(o.getUnitPrice()));
            datas.add(String.valueOf(o.getAmount()));
            datas.add(String.valueOf(o.getTotalPrice()));
            datas.add("");
        }
        while (datas.size() < 83) {
            datas.add("");
        }
        datas.add(String.valueOf(order.get().getTotalNumber()));
        datas.add(String.valueOf(order.get().getTotalPrice()));
        datas.add("");

        GeneratePDF.fillTemplate(datas, orderId);

    }


}
