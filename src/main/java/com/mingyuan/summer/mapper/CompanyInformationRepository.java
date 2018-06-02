package com.mingyuan.summer.mapper;

import com.mingyuan.summer.domain.CompanyInformation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyInformationRepository extends JpaRepository<CompanyInformation, String> {
}
