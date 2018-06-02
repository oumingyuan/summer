package com.mingyuan.summer.domain;


import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "order", catalog = "company_log")
public class Order {

    private String id;
    private String companyId;
    //    private java.sql.Timestamp time;
    private LocalDateTime time;
    private String type;
    private BigDecimal totalPrice;
    private int totalNumber;


    @Id
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }


    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }


    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }


    public int getTotalNumber() {
        return totalNumber;
    }

    public void setTotalNumber(int totalNumber) {
        this.totalNumber = totalNumber;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id='" + id + '\'' +
                ", companyId='" + companyId + '\'' +
                ", time=" + time +
                ", type='" + type + '\'' +
                ", totalPrice=" + totalPrice +
                ", totalNumber=" + totalNumber +
                '}';
    }
}
