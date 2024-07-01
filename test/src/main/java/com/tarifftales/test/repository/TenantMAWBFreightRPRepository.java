package com.tarifftales.test.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tarifftales.test.entity.TenantMAWBFreightRP;

public interface TenantMAWBFreightRPRepository extends JpaRepository<TenantMAWBFreightRP, String> {
    // You can add custom query methods here if needed
}
