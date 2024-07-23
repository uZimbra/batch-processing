package com.rodrigo.batch_processing.reader;

import com.rodrigo.batch_processing.domain.CardType;
import org.springframework.batch.item.database.JpaCursorItemReader;
import org.springframework.batch.item.database.builder.JpaCursorItemReaderBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManagerFactory;

@Component
public class CardTypeItemReader {

    @Autowired
    private EntityManagerFactory entityManagerFactory;

    public JpaCursorItemReader<CardType> cardReader() {
        var sql = "SELECT fh FROM CardType fh";

        return new JpaCursorItemReaderBuilder<CardType>()
                .name("jpaCursorItemReader")
                .entityManagerFactory(entityManagerFactory)
                .queryString(sql)
                .build();
    }

}
