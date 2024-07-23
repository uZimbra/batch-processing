package com.rodrigo.batch_processing.seeders;

import com.rodrigo.batch_processing.domain.FileHeader;
import com.rodrigo.batch_processing.repositories.FileHeaderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class FileHeaderSeeder implements CommandLineRunner {

    @Autowired
    private FileHeaderRepository fileHeaderRepository;

    @Override
    public void run(String... args) throws Exception {
        var invoice1 = new FileHeader();
        invoice1.setId(1L);
        invoice1.setVariant(1);
        invoice1.setProduct(1);
        invoice1.setPrintingWay(11);
        invoice1.setRecordType(0);
        invoice1.setProcessingDate(20240722);
        invoice1.setProductDescription("VISA");
        invoice1.setBatchNumber(1);

        var invoice2 = new FileHeader();
        invoice2.setId(2L);
        invoice2.setVariant(1);
        invoice2.setProduct(1);
        invoice2.setPrintingWay(11);
        invoice2.setRecordType(0);
        invoice2.setProcessingDate(20240722);
        invoice2.setProductDescription("VISA");
        invoice2.setBatchNumber(1);

        fileHeaderRepository.save(invoice1);
        fileHeaderRepository.save(invoice2);
    }
}
