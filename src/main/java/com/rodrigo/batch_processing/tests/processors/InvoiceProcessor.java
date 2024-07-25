package com.rodrigo.batch_processing.tests.processors;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;

import com.rodrigo.batch_processing.domain.BalanceInfo;
import com.rodrigo.batch_processing.domain.Invoice;
import com.rodrigo.batch_processing.domain.StatementHeader;
import com.rodrigo.batch_processing.repositories.BalanceInfoRepository;
import com.rodrigo.batch_processing.repositories.StatementHeaderRepository;
import com.rodrigo.batch_processing.utils.FileUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class InvoiceProcessor implements ItemProcessor<StatementHeader, StatementHeader> {

    private final StatementHeaderRepository statementHeaderRepository;
    private final BalanceInfoRepository balanceInfoRepository;

    @Override
    public StatementHeader process(StatementHeader fileHeader) throws IOException {
        // var invoice = new Invoice();
        // invoice.setFileHeader(fileHeader);
        // invoice.setStatementHeader(statementHeaderRepository.findByFileId(fileHeader.getId()));
        // invoice.setBalanceInfo(balanceInfoRepository.findByFileId(fileHeader.getId()));

        // var file = checkIfFileExists(fileHeader);

        // var writer = new FileWriter(file, true);

        // for (StatementHeader sh : invoice.getStatementHeader()) {
        //     writer.append(sh.getRecordType().toString());
        //     writer.append(sh.getContent());
        //     writer.append(System.lineSeparator());
        // }

        // for (BalanceInfo bi : invoice.getBalanceInfo()) {
        //     writer.append(bi.getRecordType().toString());
        //     writer.append(bi.getContent());
        //     writer.append(System.lineSeparator());
        // }

        // writer.close();

        return null;
    }

    

}
