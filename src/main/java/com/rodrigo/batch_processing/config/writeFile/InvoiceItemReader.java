package com.rodrigo.batch_processing.config.writeFile;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.rodrigo.batch_processing.domain.Invoice;
import com.rodrigo.batch_processing.domain.StatementHeader;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class InvoiceItemReader {

  @Bean("InvoiceReader")
  public JdbcCursorItemReader<Invoice> invoiceReader(DataSource dataSource) {
    var sql = "SELECT * FROM statement_header WHERE processing_date = ? AND status <> 'SUCCESS'";

    var reader = new JdbcCursorInvoiceReader();
    reader.setName("jpaCursorItemReader");
    reader.setDataSource(dataSource);
    reader.setSql(sql);
    reader.setRowMapper(this::invoiceMapper);

    return reader;
  }

  private Invoice invoiceMapper(ResultSet rs, int rowNum) throws SQLException {
    var sh = StatementHeader.builder()
        .id(rs.getLong("id"))
        .processingDate(rs.getLong("processing_date"))
        .recordType(rs.getInt("record_type"))
        .content(rs.getString("content"))
        .status(rs.getString("status"))
        .product(rs.getLong("product"))
        .variant(rs.getLong("variant"))
        .build();

    var invoice = Invoice.builder()
        .statementHeader(sh)
        .build();

    return invoice;
  }

}
