package it.schwarz.bogy.ld2i;

import it.schwarz.bogy.ld2i.domain.usecase.CreateLabelUseCase;
import static it.schwarz.bogy.ld2i.AppConfig.WEB_APPLICATION_TYPE;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.WebApplicationType;
import org.springframework.stereotype.Component;

@Component
public class AppRunner implements CommandLineRunner {

    private final CreateLabelUseCase createLabelUseCase;


    public AppRunner(CreateLabelUseCase createLabelUseCase) {
        this.createLabelUseCase = createLabelUseCase;
    }

    @Override
    public void run(String... args) {
        if (WEB_APPLICATION_TYPE.equals(WebApplicationType.NONE)) {
            this.createLabelUseCase.createLabelAndOutput();
        }
    }
}
