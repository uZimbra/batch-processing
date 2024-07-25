package com.rodrigo.batch_processing.repositories;

import com.rodrigo.batch_processing.domain.CardType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CardTypeRepository extends JpaRepository<CardType, Long> {
}
