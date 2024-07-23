package com.rodrigo.batch_processing.repositories;

import com.rodrigo.batch_processing.domain.FileHeader;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FileHeaderRepository extends JpaRepository<FileHeader, Long> {
}
