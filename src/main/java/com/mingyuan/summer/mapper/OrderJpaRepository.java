package com.mingyuan.summer.mapper;

import com.mingyuan.summer.domain.Order;
import com.mingyuan.summer.domain.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderJpaRepository extends JpaRepository<Order, String> {
}
