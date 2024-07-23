package com.rodrigo.batch_processing.seeders;

import com.rodrigo.batch_processing.domain.StatementHeader;
import com.rodrigo.batch_processing.repositories.StatementHeaderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class StatementHeaderSeeder implements CommandLineRunner {

    @Autowired
    private StatementHeaderRepository statementHeaderRepository;

    @Override
    public void run(String... args) throws Exception {
        var sh1 = new StatementHeader();
        sh1.setId(1L);
        sh1.setFileId(1L);
        sh1.setRecordType(1);
        sh1.setFiller("fat 1 filler 1");

        var sh2 = new StatementHeader();
        sh2.setId(2L);
        sh2.setFileId(1L);
        sh2.setRecordType(1);
        sh2.setFiller("fat 1 filler 2");

        var sh3 = new StatementHeader();
        sh3.setId(3L);
        sh3.setFileId(2L);
        sh3.setRecordType(1);
        sh3.setFiller("fat 2 filler 1");

        statementHeaderRepository.save(sh1);
        statementHeaderRepository.save(sh2);
        statementHeaderRepository.save(sh3);
    }
}
