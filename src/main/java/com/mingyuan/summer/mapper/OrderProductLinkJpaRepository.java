package com.mingyuan.summer.mapper;


import com.mingyuan.summer.domain.OrderProductLink;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import java.math.BigInteger;
import java.util.List;

public interface OrderProductLinkJpaRepository extends JpaRepository<OrderProductLink, BigInteger> {


//    @Query("select p from OrderProductLink p where p.orderId=:orderId")
//    List<OrderProductLink> query_link(@Param("orderId") String orderId);


    @Query("select max(o.id) from OrderProductLink o")
    BigInteger get_big_id();


    @Query(value = "select o from OrderProductLink o where o.orderId=:orderId")
    List<OrderProductLink> queryProductByOrderId(@Param("orderId") String orderId);


}
