package it.schwarz.bogy.ld2i.output;

import com.fasterxml.jackson.databind.ObjectMapper;
import it.schwarz.bogy.ld2i.domain.usecase.OutputProvider;
import org.apache.commons.io.IOUtils;
import org.springframework.jms.connection.CachingConnectionFactory;
import org.springframework.jms.core.JmsTemplate;

import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;

public class JmsOutputProvider implements OutputProvider {


    private final ObjectMapper objectMapper;
    private final JmsTemplate jmsTemplate;

    public JmsOutputProvider(ObjectMapper objectMapper, JmsTemplate jmsTemplate) {
        this.objectMapper = objectMapper;
        this.jmsTemplate = jmsTemplate;

        CachingConnectionFactory ccf = new CachingConnectionFactory();
        ccf.setTargetConnectionFactory(jmsTemplate.getConnectionFactory());
        jmsTemplate.setConnectionFactory(ccf);

        // From the spring documentation
        // https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/jms/support/destination/JmsDestinationAccessor.html#setPubSubDomain(boolean)
        // "true" for the publishing/subscription domain (Topics),
        // "false" for the Point-to-Point domain (Queues)
        jmsTemplate.setPubSubDomain(true);

    }

    @Override
    public void send(String displayId, InputStream image) {
        try {
            byte[] imageAsBytes  = IOUtils.toByteArray(image);
            String imageAsBase64String = Base64.getEncoder().encodeToString(imageAsBytes);

            String msgAsString = objectMapper.writeValueAsString(imageAsBytes);
            System.out.println("Sending Message: $msgAsString");

            jmsTemplate.convertAndSend("hwonsi-test-topic", msgAsString);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }
}
