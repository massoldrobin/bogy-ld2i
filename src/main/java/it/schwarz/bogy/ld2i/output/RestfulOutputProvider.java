package it.schwarz.bogy.ld2i.output;

import it.schwarz.bogy.ld2i.domain.usecase.OutputProvider;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;

public class RestfulOutputProvider implements OutputProvider {

    private final Logger logger = LoggerFactory.getLogger(RestfulOutputProvider.class);
    private final String outputServer;

    public RestfulOutputProvider(String outputServer) {
        this.outputServer = outputServer;
    }

    @Override
    public void send(String displayId, InputStream image) {
        byte[] imageAsBytes;
        try {
            imageAsBytes = IOUtils.toByteArray(image);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        String imageAsBase64String = Base64.getEncoder().encodeToString(imageAsBytes);

        WebClient webClient = WebClient.builder()
                .baseUrl(outputServer)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();

        //
        Mono<String> responseString = webClient.post()
                .uri("/public/sendImageToDisplay?displaySerialNumber=" + displayId)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_FORM_URLENCODED_VALUE)
                .body(Mono.just(imageAsBase64String), String.class)
                .retrieve()
                .bodyToMono(String.class);
        logger.info(responseString.toString());

    }
}
