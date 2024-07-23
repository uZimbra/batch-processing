package com.rodrigo.batch_processing.repositories;

import com.rodrigo.batch_processing.domain.StatementHeader;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StatementHeaderRepository extends JpaRepository<StatementHeader, Long> {
//    @Query(value = "SELECT * from statement_header WHERE file_id =:fileId", nativeQuery = true)
    List<StatementHeader> findByFileId(@Param("fileId") Long fileId);
}
