package com.rodrigo.batch_processing.repositories;

import com.rodrigo.batch_processing.domain.FileControl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FileControlRepository extends JpaRepository<FileControl, Long> {
}
