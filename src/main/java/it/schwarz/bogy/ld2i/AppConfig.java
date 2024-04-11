package it.schwarz.bogy.ld2i;

import it.schwarz.bogy.ld2i.domain.usecase.CreateLabelUseCase;
import it.schwarz.bogy.ld2i.domain.usecase.LabelDataProvider;
import it.schwarz.bogy.ld2i.domain.usecase.OutputProvider;
import it.schwarz.bogy.ld2i.input.FixedValuesInputProvider;
import it.schwarz.bogy.ld2i.output.FileOutputProvider;
import it.schwarz.bogy.ld2i.output.RestfulOutputProvider;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.WebApplicationType;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import java.io.File;

@Configuration
public class AppConfig {

    @Value("${it.schwarz.output-directory}")
    private String outputDirectory;

    @Value("${it.schwarz.output-server}")
    private String outputServer;

    // NONE = CLI
    // SERVLET = WEB
    public static final WebApplicationType WEB_APPLICATION_TYPE =  WebApplicationType.SERVLET; // NONE, SERVLET

    @Bean
    LabelDataProvider getLabelDataProvider() {
        return new FixedValuesInputProvider();
    }

    @Bean
    @Primary
    OutputProvider getFileOutputProvider() {
        return new FileOutputProvider(new File(outputDirectory));
    }

    @Bean
    OutputProvider getRestfulOutputProvider() {
        return new RestfulOutputProvider(outputServer);
    }

    @Bean
    CreateLabelUseCase getCreateLabelUseCase(LabelDataProvider labelDataProvider, OutputProvider outputProvider) {
        return new CreateLabelUseCase(labelDataProvider, outputProvider);
    }


}
