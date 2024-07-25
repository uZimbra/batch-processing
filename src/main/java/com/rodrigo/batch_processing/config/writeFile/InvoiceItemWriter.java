package com.rodrigo.batch_processing.config.writeFile;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.rodrigo.batch_processing.domain.Invoice;
import com.rodrigo.batch_processing.domain.StatusEnum;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class InvoiceItemWriter {

  @Bean(name = "InvoiceItemWriter")
  public JdbcBatchItemWriter<Invoice> writer(DataSource dataSource) {
    var sql = "UPDATE statement_header SET status = ? WHERE id = ?";

    JdbcBatchItemWriter<Invoice> writer = new JdbcBatchItemWriter<>();
    writer.setDataSource(dataSource);
    writer.setSql(sql);
    writer.setItemPreparedStatementSetter(this::setValues);
    return writer;
  }

  private void setValues(Invoice item, PreparedStatement ps) throws SQLException {
    ps.setString(1, StatusEnum.SUCCESS.name());
    ps.setLong(2, item.getStatementHeader().getId());
  }
}
