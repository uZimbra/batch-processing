package com.rodrigo.batch_processing.repositories;

import com.rodrigo.batch_processing.domain.StatementHeader;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface StatementHeaderRepository extends JpaRepository<StatementHeader, Long> {

        @Query(value = "SELECT 1 FROM statement_header sh WHERE processing_date = :processingDate AND product = :product AND variant = :variant and status <> 'SUCCESS' LIMIT 1", nativeQuery = true)
        Integer findIfHasInvoicesToProcess(@Param("processingDate") Long date, @Param("product") Long product,
                        @Param("variant") Long variant);

}
