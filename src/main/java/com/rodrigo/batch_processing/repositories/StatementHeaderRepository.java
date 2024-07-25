package com.rodrigo.batch_processing.repositories;

import com.rodrigo.batch_processing.domain.StatementHeader;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface StatementHeaderRepository extends JpaRepository<StatementHeader, Long> {

    @Query(value = "SELECT 1 FROM statement_header sh WHERE processing_date = :processingDate AND product = :product AND variant = :variant LIMIT 1", nativeQuery = true)
    Integer findInvoices(@Param("processingDate") Long date, @Param("product") Long product,
            @Param("variant") Long variant);

    @Modifying
    @Transactional
    @Query(value = "UPDATE statement_header SET status = :status WHERE id = :id", nativeQuery = true)
    void updateStatus(@Param("id") Long id, @Param("status") String status);

}
