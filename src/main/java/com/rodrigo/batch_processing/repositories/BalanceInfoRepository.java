package com.rodrigo.batch_processing.repositories;

import java.util.List;

import com.rodrigo.batch_processing.domain.BalanceInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BalanceInfoRepository extends JpaRepository<BalanceInfo, Long> {
    List<BalanceInfo> findByInvoiceId(Long inviceId);
}
