package com.mingyuan.summer.mapper;

import com.mingyuan.summer.domain.Order;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface OrderJpaRepository extends JpaRepository<Order, String> {



    //SELECT MAX(id) FROM `company_log`.`order`


    @Query("SELECT MAX(o.id) FROM Order o")
    String get_max_id();



}
