package com.rodrigo.batch_processing.processors;

import com.rodrigo.batch_processing.domain.BalanceInfo;
import com.rodrigo.batch_processing.domain.FileHeader;
import com.rodrigo.batch_processing.Invoice;
import com.rodrigo.batch_processing.domain.StatementHeader;
import com.rodrigo.batch_processing.repositories.BalanceInfoRepository;
import com.rodrigo.batch_processing.repositories.StatementHeaderRepository;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;


@Component
public class InvoiceProcessor implements ItemProcessor<FileHeader, FileHeader> {

    private StatementHeaderRepository statementHeaderRepository;
    private BalanceInfoRepository balanceInfoRepository;

    public InvoiceProcessor(StatementHeaderRepository statementHeaderRepository, BalanceInfoRepository balanceInfoRepository) {
        this.statementHeaderRepository = statementHeaderRepository;
        this.balanceInfoRepository = balanceInfoRepository;
    }

    @Override
    public FileHeader process(FileHeader fileHeader) throws IOException {
        var invoice = new Invoice();
        invoice.setFileHeader(fileHeader);
        invoice.setStatementHeader(statementHeaderRepository.findByFileId(fileHeader.getId()));
        invoice.setBalanceInfo(balanceInfoRepository.findByFileId(fileHeader.getId()));

        var file = checkIfFileExists(fileHeader);

        var writer = new FileWriter(file, true);

        for (StatementHeader sh : invoice.getStatementHeader()) {
            writer.append(sh.getRecordType().toString());
            writer.append(sh.getFiller());
            writer.append(System.lineSeparator());
        }

        for (BalanceInfo bi : invoice.getBalanceInfo()) {
            writer.append(bi.getRecordType().toString());
            writer.append(bi.getRecordSubType());
            writer.append(System.lineSeparator());
        }

        writer.close();

        return null;
    }


    private File checkIfFileExists(FileHeader fileHeader) throws IOException {
        String fileName = "data." + fileHeader.getProcessingDate() +
                ".product." + fileHeader.getProduct() +
                ".variant." + fileHeader.getVariant() +
                ".out";

        var path = Paths.get("output");

        if (!path.toFile().exists()) {
            path.toFile().mkdirs();
        }

        var file = new File(String.valueOf(path.resolve(fileName)));

        if (!file.exists() && (file.createNewFile())) {
            var writer = new FileWriter(file, true);
            writer.append(fileHeader.getRecordType().toString());
            writer.append(fileHeader.getProduct().toString());
            writer.append(fileHeader.getVariant().toString());
            writer.append(fileHeader.getProcessingDate().toString());
            writer.append(fileHeader.getProductDescription());
            writer.append(System.lineSeparator());
            writer.close();
            return file;
        }

        return file;
    }

}
