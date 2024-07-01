package com.tarifftales.test.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tarifftales.test.entity.TenantMAWBRP;

public interface TenantMAWBRPRepository extends JpaRepository<TenantMAWBRP, String> {
    // You can add custom query methods here if needed
}
