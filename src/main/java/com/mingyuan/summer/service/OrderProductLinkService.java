package com.mingyuan.summer.service;


import com.alibaba.fastjson.JSONArray;
import com.mingyuan.summer.domain.Order;

public interface OrderProductLinkService {

    void insertData(JSONArray order_list, String flag, String orderId, Order order) throws Exception;

    void generatePDF(String orderId, String dateShow);

}
