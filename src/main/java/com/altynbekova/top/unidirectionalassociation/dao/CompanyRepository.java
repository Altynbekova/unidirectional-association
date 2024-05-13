package com.altynbekova.top.unidirectionalassociation.dao;

import com.altynbekova.top.unidirectionalassociation.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company, Long> {
}