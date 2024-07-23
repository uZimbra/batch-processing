package com.rodrigo.batch_processing.reader;

import com.rodrigo.batch_processing.domain.CardType;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.stereotype.Component;

@Component
public class CSVReader {

    public FlatFileItemReader<CardType> reader() {
        var resource = new PathMatchingResourcePatternResolver().getResource("classpath:/database.csv");

        var itemReader = new FlatFileItemReader<CardType>();
        itemReader.setResource(resource);
        itemReader.setName("csv");
        itemReader.setLinesToSkip(1);
        itemReader.setLineMapper(lineMapper());

        return itemReader;
    }

    private LineMapper<CardType> lineMapper() {
        var lineTokenizer = new DelimitedLineTokenizer();
        lineTokenizer.setDelimiter(",");
        lineTokenizer.setStrict(false);
        lineTokenizer.setNames("product", "variant", "description");

        var fieldSetMapper = new BeanWrapperFieldSetMapper<CardType>();
        fieldSetMapper.setTargetType(CardType.class);

        var lineMapper = new DefaultLineMapper<CardType>();
        lineMapper.setLineTokenizer(lineTokenizer);
        lineMapper.setFieldSetMapper(fieldSetMapper);

        return lineMapper;
    }

}
