package it.schwarz.bogy.ld2i;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import static it.schwarz.bogy.ld2i.AppConfig.WEB_APPLICATION_TYPE;

@SpringBootApplication
public class AppMain {

    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(AppMain.class);
        if (WEB_APPLICATION_TYPE == WebApplicationType.NONE) {
            application.setWebApplicationType(WebApplicationType.NONE);
        }
        application.run(args);
    }

}
