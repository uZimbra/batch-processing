package com.rodrigo.batch_processing.tests.seeders;
// package com.rodrigo.batch_processing.config.seeders;

// import com.rodrigo.batch_processing.domain.FileHeader;
// import com.rodrigo.batch_processing.repositories.FileHeaderRepository;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.CommandLineRunner;
// import org.springframework.stereotype.Component;

// @Component
// public class FileHeaderSeeder implements CommandLineRunner {

//     @Autowired
//     private FileHeaderRepository fileHeaderRepository;

//     @Override
//     public void run(String... args) throws Exception {
//         var invoice1 = FileHeader.builder()
//                 .id(1L)
//                 .variant(1)
//                 .product(1)
//                 .printingWay(11)
//                 .recordType(0)
//                 .processingDate(20240722)
//                 .productDescription("VISA")
//                 .batchNumber(1)
//                 .build();

//         var invoice2 = FileHeader.builder()
//                 .id(2L)
//                 .variant(1)
//                 .product(1)
//                 .printingWay(11)
//                 .recordType(0)
//                 .processingDate(20240722)
//                 .productDescription("VISA")
//                 .batchNumber(1)
//                 .build();

//         fileHeaderRepository.save(invoice1);
//         fileHeaderRepository.save(invoice2);
//     }

// }
