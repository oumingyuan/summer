package com.mingyuan.summer.domain;


import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.math.BigInteger;

@Entity
@Table(name = "order_product_link", catalog = "company_log")
public class OrderProductLink {


    private BigInteger id;

    private String orderId;
    private String productId;
    private BigDecimal unitPrice;
    private int amount;
    private BigDecimal totalPrice;
    private String remark;
    private int productIndex;

    @Id
    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }


    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }


    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }


    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }


    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }


    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }


    public int getProductIndex() {
        return productIndex;
    }

    public void setProductIndex(int productIndex) {
        this.productIndex = productIndex;
    }

    @Override
    public String toString() {
        return "OrderProductLink{" +
                "id=" + id +
                ", orderId='" + orderId + '\'' +
                ", productId='" + productId + '\'' +
                ", unitPrice=" + unitPrice +
                ", amount=" + amount +
                ", totalPrice=" + totalPrice +
                ", remark='" + remark + '\'' +
                ", productIndex=" + productIndex +
                '}';
    }
}
