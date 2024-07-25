package com.rodrigo.batch_processing.config;

import javax.sql.DataSource;

import com.rodrigo.batch_processing.domain.FileControl;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableBatchProcessing
@RequiredArgsConstructor
public class BatchConfig {

  private final JobBuilderFactory jobBuilderFactory;
  // private final StepBuilderFactory stepBuilderFactory;
  // private final CardTypeItemReader cardTypeReader;
  // private final CardTypeProcessor cardTypeProcessor;

  @Bean
  public Job job(@Qualifier("CreateFileStep") Step createFileStep, @Qualifier("WriteFileStep") Step writeFileStep) {
    return jobBuilderFactory
        .get("job")
        .incrementer(new RunIdIncrementer())
        .start(createFileStep)
        .next(writeFileStep)
        // .next(step2)
        .build();
  }

  // @Bean("makeFileStep")
  // public Step makeFileStep(
  // @Qualifier("fileControlWriter") JdbcBatchItemWriter<FileControl> writer) {
  // // 1 - ler tabela de tipos
  // // 2 - criar arquivo - somente arquivos de faturas existentes - (produto,
  // // variante, data)
  // // 3 - iniciar tabelas de controle de arquivo

  // return stepBuilderFactory
  // .get("MakeFileStep")
  // .allowStartIfComplete(true)
  // .<CardType, FileControl>chunk(1)
  // .reader(cardTypeReader.reader())
  // .processor(cardTypeProcessor)
  // .writer(writer)
  // .build();
  // }

  @Bean
  JdbcBatchItemWriter<FileControl> writer(DataSource dataSource) {
    var sql = """
        INSERT INTO file_control (
          product, variant, file_name, status
        ) VALUES (
         :product, :variant, :fileName, :status
         )
        """;
    return new JdbcBatchItemWriterBuilder<FileControl>()
        .dataSource(dataSource)
        .sql(sql)
        .beanMapped()
        .build();
  }

  @Bean
  public Step processInvoices() {
    // 1 - ler primeira linha
    // 2 - processa pupulando um objeto de fatura com as demais linhas
    // 3 - escreve objeto de fatura no arquivo
    return null;
  }

  // @Bean
  // public Step getOpenedInvoices(
  // CardTypeItemReader cardTypeReader,
  // InvoiceProcessor processor,
  // ItemWriter<FileHeader> cardWriter) {
  // return stepBuilderFactory
  // .get("getOpenedInvoices")
  // .<CardType, FileHeader>chunk(1)
  // .reader(cardTypeReader.cardReader())
  // // .processor(processor)
  // .writer(cardWriter)
  // .build();
  // }

  // @Bean
  // public ItemProcessor<FileHeader, FileHeader> cardProcessor() {
  // return item -> {
  // System.out.println("o item esta sendo processado: " + item.getId());

  // var path = Paths.get("output", "invoices");

  // File pasta = path.toFile();
  // if (!pasta.exists()) {
  // pasta.mkdirs(); // Cria a pasta e quaisquer diretórios pais necessários
  // }

  // var fileName = new StringBuilder();
  // fileName.append("data");
  // fileName.append("_");
  // fileName.append("20240718");
  // fileName.append("_");
  // fileName.append(item.getProduct().toString());
  // fileName.append("_");
  // fileName.append(item.getVariant().toString());
  // fileName.append("_");
  // fileName.append(item.getProductDescription());
  // fileName.append(".OUT");

  // var filePath = path.resolve(String.valueOf(fileName));

  // var writer = new FileWriter(filePath.toFile());
  // var bufferedWriter = new BufferedWriter(writer);

  // bufferedWriter.write("valor adicionado do id" + item.getId());
  // bufferedWriter.newLine();
  // bufferedWriter.close();

  // return item;
  // };
  // }

  // @Bean
  // public ItemWriter<FileHeader> cardWriter() {
  // return System.out::println;
  // }

  // @Bean
  // public Job job() {
  // return jobBuilderFactory
  // .get("job")
  // .start(checarTipoDeArquivoClientes())
  // .on("CADASTRO").to(cadastrarClientes())

  // .from(checarTipoDeArquivoClientes()).on("ATUALIZACAO").to(atualizarClientes()).end()
  // .build();
  // }

  // @Bean
  // public Step checarTipoDeArquivoClientes() {
  // return stepBuilderFactory
  // .get("checarTipoDeArquivoClientes")
  // .tasklet((StepContribution contribution, ChunkContext chunkContext) -> {
  // System.out.println("Checando o tipo de arquivo de clientes...");

  // String tipoArquivoClientes = "CADASTRO";
  // // String tipoArquivoClientes = "ATUALIZACAO";

  // chunkContext.getStepContext().getStepExecution().getExecutionContext()
  // .put("TIPO", tipoArquivoClientes);

  // return RepeatStatus.FINISHED;
  // })
  // .listener(new OperationListener())
  // .build();
  // }

  // public Step cadastrarClientes() {
  // return stepBuilderFactory
  // .get("cadastrarClientes")
  // .tasklet((StepContribution contribution, ChunkContext chunkContext) -> {
  // System.out.println("Cadastrando clientes...");
  // return RepeatStatus.FINISHED;
  // })
  // .build();
  // }

  // public Step atualizarClientes() {
  // return stepBuilderFactory
  // .get("atualizarClientes")
  // .tasklet((StepContribution contribution, ChunkContext chunkContext) -> {
  // System.out.println("Atualizando clientes...");
  // return RepeatStatus.FINISHED;
  // })
  // .build();
  // }

  // class OperationListener {
  // @AfterStep
  // public ExitStatus afterStep(StepExecution stepExecution) {
  // if (stepExecution.getExecutionContext().get("TIPO").equals("CADASTRO"))
  // return new ExitStatus("CADASTRO");
  // else if
  // (stepExecution.getExecutionContext().get("TIPO").equals("ATUALIZACAO"))
  // return new ExitStatus("ATUALIZACAO");
  // else
  // return ExitStatus.FAILED;
  // }
  // }

}
