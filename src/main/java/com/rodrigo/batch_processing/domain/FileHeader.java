package com.rodrigo.batch_processing.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Builder;
import lombok.Data;

@Data
@Entity
@Builder
public class FileHeader {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer variant;
    private Integer recordType;
    private Integer processingDate;
    private Integer batchNumber;
    private Integer product;
    private String productDescription;
    private Integer printingWay;
}
