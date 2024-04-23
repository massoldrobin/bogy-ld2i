package it.schwarz.bogy.ld2i.output;

import io.netty.handler.ssl.SslContext;
import io.netty.handler.ssl.SslContextBuilder;
import io.netty.handler.ssl.util.InsecureTrustManagerFactory;
import it.schwarz.bogy.ld2i.domain.usecase.OutputProvider;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import reactor.netty.http.client.HttpClient;

import javax.net.ssl.SSLException;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
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

        String imageAsUrlEncodedString = null;
        try {
            imageAsUrlEncodedString = URLEncoder.encode(imageAsBase64String, StandardCharsets.UTF_8.toString());
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }


        HttpClient httpClient = null;
        try {
            SslContext sslContext = SslContextBuilder
                    .forClient()
                    .trustManager(InsecureTrustManagerFactory.INSTANCE)
                    .build();

            httpClient = HttpClient.create().secure(t -> t.sslContext(sslContext));
        } catch (SSLException e) {
            throw new RuntimeException(e);
        }

        WebClient webClient = WebClient.builder()
                .baseUrl(outputServer)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .clientConnector(new ReactorClientHttpConnector(httpClient))
                .build();







        logger.info("Sending Request ...");
        Mono<String> responseString = webClient.post()
                .uri("/public/sendImageToDisplay?displaySerialNumber=" + displayId)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_FORM_URLENCODED_VALUE)
                .body(BodyInserters.fromFormData("base64EncodedImage", imageAsBase64String))

                .retrieve()
                .bodyToMono(String.class);
        logger.info("Response: {}", responseString.block());

    }
}
