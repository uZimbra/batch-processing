package com.rodrigo.batch_processing;

import com.rodrigo.batch_processing.domain.FileHeader;
import com.rodrigo.batch_processing.processors.InvoiceProcessor;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.annotation.AfterStep;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.scope.context.StepContext;
import org.springframework.batch.core.scope.context.StepSynchronizationManager;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.JpaCursorItemReader;
import org.springframework.batch.item.database.builder.JpaCursorItemReaderBuilder;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManagerFactory;

@Configuration
@EnableBatchProcessing
public class BatchConfig {

    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Autowired
    private EntityManagerFactory entityManagerFactory;

    @Bean
    public Job job(Step getOpenedInvoices, @Qualifier("step2") Step step2) {
        return jobBuilderFactory
                .get("job")
                .incrementer(new RunIdIncrementer())
                .start(getOpenedInvoices)
//                .next(step2)
                .build();
    }

    @Bean
    public Step getOpenedInvoices(
            JpaCursorItemReader<FileHeader> invoiceReader,
            InvoiceProcessor processor,
            ItemWriter<FileHeader> cardWriter
    ) {
        return stepBuilderFactory
                .get("getOpenedInvoices")
                .<FileHeader, FileHeader>chunk(1)
                .reader(invoiceReader)
                .reader(invoiceReader)
                .processor(processor)
                .writer(cardWriter)
                .build();
    }

    @Bean(name = "step2")
    public Step nextStep() {
        return stepBuilderFactory.get("step2")
                .tasklet(new Tasklet() {
                    @Override
                    public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {

                        StepContext stepContext = StepSynchronizationManager.getContext();
                        String value = stepContext.getStepExecution().getExecutionContext().getString("fileHeader");
                        System.out.println("Step 2: Retrieved 'fileHeader' from ExecutionContext with value: " + value);
                        return RepeatStatus.FINISHED;
                    }
                })
                .build();
    }

    @Bean
    public JpaCursorItemReader<FileHeader> cardReader() {
//        var sql = new String("select fh.*, sh.*, bi.* from file_header fh " +
//                "left join statement_header sh ON fh.id = sh.file_id " +
//                "left join balance_info bi ON fh.id = bi.file_id");

        var sql = "SELECT fh FROM FileHeader fh";

        return new JpaCursorItemReaderBuilder<FileHeader>()
                .name("jpaCursorItemReader")
                .entityManagerFactory(entityManagerFactory)
                .queryString(sql)
                .build();

//        return new JdbcCursorItemReaderBuilder<FileHeader>()
//                .name("listCardsReader")
//                .
//                .sql(sql)
//                .rowMapper((rs, rowNum) -> {
//                    var fh = new FileHeader();
//
//                    fh.setId(rs.getLong("id"));
//                    fh.setVariant(rs.getInt("variant"));
//                    fh.setRecordType(rs.getInt("record_type"));
//                    fh.setProcessingDate(rs.getInt("processing_date"));
//                    fh.setBatchNumber(rs.getInt("batch_number"));
//                    fh.setProduct(rs.getInt("product"));
//                    fh.setProductDescription(rs.getString("product_description"));
//                    fh.setPrintingWay(rs.getInt("printing_way"));
//
//                    return fh;
//                })
//                .build();
    }

//    @Bean
//    public ItemProcessor<FileHeader, FileHeader> cardProcessor() {
//        return item -> {
//            System.out.println("o item esta sendo processado: " + item.getId());
//
//            var path = Paths.get("output", "invoices");
//
//            File pasta = path.toFile();
//            if (!pasta.exists()) {
//                pasta.mkdirs(); // Cria a pasta e quaisquer diretórios pais necessários
//            }
//
//            var fileName = new StringBuilder();
//            fileName.append("data");
//            fileName.append("_");
//            fileName.append("20240718");
//            fileName.append("_");
//            fileName.append(item.getProduct().toString());
//            fileName.append("_");
//            fileName.append(item.getVariant().toString());
//            fileName.append("_");
//            fileName.append(item.getProductDescription());
//            fileName.append(".OUT");
//
//            var filePath = path.resolve(String.valueOf(fileName));
//
//            var writer = new FileWriter(filePath.toFile());
//            var bufferedWriter = new BufferedWriter(writer);
//
//            bufferedWriter.write("valor adicionado do id" + item.getId());
//            bufferedWriter.newLine();
//            bufferedWriter.close();
//
//            return item;
//        };
//    }

    @Bean
    public ItemWriter<FileHeader> cardWriter() {
        return System.out::println;
    }

//    @Bean
//    public Job job() {
//        return jobBuilderFactory
//                .get("job")
//                .start(checarTipoDeArquivoClientes())
//                .on("CADASTRO").to(cadastrarClientes())
//                .from(checarTipoDeArquivoClientes()).on("ATUALIZACAO").to(atualizarClientes()).end()
//                .build();
//    }

    @Bean
    public Step checarTipoDeArquivoClientes() {
        return stepBuilderFactory
                .get("checarTipoDeArquivoClientes")
                .tasklet((StepContribution contribution, ChunkContext chunkContext) -> {
                    System.out.println("Checando o tipo de arquivo de clientes...");

                    String tipoArquivoClientes = "CADASTRO";
                    // String tipoArquivoClientes = "ATUALIZACAO";

                    chunkContext.getStepContext().getStepExecution().getExecutionContext()
                            .put("TIPO", tipoArquivoClientes);

                    return RepeatStatus.FINISHED;
                })
                .listener(new OperationListener())
                .build();
    }

    public Step cadastrarClientes() {
        return stepBuilderFactory
                .get("cadastrarClientes")
                .tasklet((StepContribution contribution, ChunkContext chunkContext) -> {
                    System.out.println("Cadastrando clientes...");
                    return RepeatStatus.FINISHED;
                })
                .build();
    }

    public Step atualizarClientes() {
        return stepBuilderFactory
                .get("atualizarClientes")
                .tasklet((StepContribution contribution, ChunkContext chunkContext) -> {
                    System.out.println("Atualizando clientes...");
                    return RepeatStatus.FINISHED;
                })
                .build();
    }

    class OperationListener {
        @AfterStep
        public ExitStatus afterStep(StepExecution stepExecution) {
            if (stepExecution.getExecutionContext().get("TIPO").equals("CADASTRO"))
                return new ExitStatus("CADASTRO");
            else if (stepExecution.getExecutionContext().get("TIPO").equals("ATUALIZACAO"))
                return new ExitStatus("ATUALIZACAO");
            else
                return ExitStatus.FAILED;
        }
    }


}
