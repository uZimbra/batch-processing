package com.rodrigo.batch_processing.config.updateFileControl;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.rodrigo.batch_processing.domain.FileControl;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class FileControlReader {

  @Bean("FileControlReader")
  public JdbcCursorItemReader<FileControl> fileControlReader(DataSource dataSource) {
    var sql = "SELECT * FROM file_control WHERE batch_number = ?";

    var reader = new JdbcCursorFileControlReader();
    reader.setName("jdbcFileControlReader");
    reader.setDataSource(dataSource);
    reader.setSql(sql);
    reader.setRowMapper(this::fileControlMapper);

    return reader;
  }

  private FileControl fileControlMapper(ResultSet rs, int rowNum) throws SQLException {
    return FileControl.builder()
        .id(rs.getLong("id"))
        .status(rs.getString("status"))
        .product(rs.getLong("product"))
        .variant(rs.getLong("variant"))
        .fileName(rs.getString("file_name"))
        .batchNumber(rs.getLong("batch_number"))
        .build();
  }

}
