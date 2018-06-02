package com.mingyuan.summer.mapper;

import com.mingyuan.summer.domain.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserInfoJpaRepository extends JpaRepository<UserInfo, String> {

//	UserInfo findByCode(String code);


}
