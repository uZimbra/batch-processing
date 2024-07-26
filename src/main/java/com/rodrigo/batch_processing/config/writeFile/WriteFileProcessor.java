package com.rodrigo.batch_processing.config.writeFile;

import java.io.ByteArrayOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;

import com.rodrigo.batch_processing.domain.BalanceInfo;
import com.rodrigo.batch_processing.domain.Invoice;
import com.rodrigo.batch_processing.exception.WriteFileException;
import com.rodrigo.batch_processing.repositories.BalanceInfoRepository;
import com.rodrigo.batch_processing.utils.FileUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.batch.core.annotation.OnProcessError;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class WriteFileProcessor implements ItemProcessor<Invoice, Invoice> {

  private final BalanceInfoRepository balanceInfoRepository;

  private Long batchId;

  @BeforeStep
  public void beforeStep(StepExecution stepExecution) {
    batchId = stepExecution.getJobExecutionId();
  }

  @Override
  public Invoice process(@NonNull Invoice item) throws IOException {
    item.setBalanceInfo(balanceInfoRepository.findByInvoiceId(item.getStatementHeader().getId()));

    var byteArrayOutputStream = new ByteArrayOutputStream();

    try (Writer writer = new OutputStreamWriter(byteArrayOutputStream)) {
      // if (item.getStatementHeader().getContent().contains("error")) {
      // writer.close();
      // throw new Exception("message with error");
      // }
      writer.append(item.getStatementHeader().getRecordType().toString());
      writer.append(item.getStatementHeader().getRecordType().toString());
      writer.append(item.getStatementHeader().getContent());
      writer.append(System.lineSeparator());

      for (BalanceInfo bi : item.getBalanceInfo()) {
        // if (bi.getContent().contains("error")) {
        // writer.close();
        // throw new Exception("message with error");
        // }
        writer.append(bi.getRecordType().toString());
        writer.append(bi.getContent());
        writer.append(System.lineSeparator());
      }
    } catch (Exception ex) {
      throw new WriteFileException("Fail in data validation, " + ex.getMessage());
    }

    try {
      var filename = FileUtils.fileName(item.getStatementHeader().getProduct(), item.getStatementHeader().getVariant(),
          batchId, item.getStatementHeader().getProcessingDate().toString());

      var file = FileUtils.resolveFilePath(filename);

      var fileWriter = new FileWriter(file, true);
      fileWriter.append(byteArrayOutputStream.toString());
      fileWriter.close();
    } catch (Exception ex) {
      throw new WriteFileException("Fail to read or write file, " + ex.getMessage());
    }

    return item;
  }

  @OnProcessError
  public void OnProcessError(@NonNull Invoice item, Exception ex) {
    log.warn("Ocorreu um erro para processar a fatura: {}", item.getStatementHeader().getId(), ex);
  }

}
