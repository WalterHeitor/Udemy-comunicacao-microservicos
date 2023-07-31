package br.com.curso.udemy.productapi.adapters.out.rabbit;

import br.com.curso.udemy.productapi.adapters.in.rabbit.sales.SalesConfirmationDTO;
import br.com.curso.udemy.productapi.aplication.ports.out.product.UpdateProductAdapterPort;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class SalesConfimationSenderAdapter implements UpdateProductAdapterPort {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Value("${app-config.rabbit.exchange.product}")
    private String productTopicExchange;
    @Value("${app-config.rabbit.routingKey.sales-confirmation}")
    private String salesConfirmationKey;

    @Override
    public  void sendSalesConfirmationMessage(SalesConfirmationDTO message) {

        try {
            log.info("Sending Sales confirmation message: {}", new ObjectMapper()
                    .writeValueAsString(message));
            rabbitTemplate.convertAndSend(productTopicExchange, salesConfirmationKey, message);
            log.info("Message was sent successfully");
        } catch (JsonProcessingException e) {
            log.error("Error while trying to send message : {}" + e.getMessage(), e);
        }
    }
}
