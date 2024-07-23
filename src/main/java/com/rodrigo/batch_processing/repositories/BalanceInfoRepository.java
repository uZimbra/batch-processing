package com.rodrigo.batch_processing.repositories;

import com.rodrigo.batch_processing.domain.BalanceInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BalanceInfoRepository extends JpaRepository<BalanceInfo, Long> {
    List<BalanceInfo> findByFileId(Long fileId);
}
