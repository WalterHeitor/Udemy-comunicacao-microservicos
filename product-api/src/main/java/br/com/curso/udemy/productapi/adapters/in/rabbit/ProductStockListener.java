package br.com.curso.udemy.productapi.adapters.in.rabbit;

import br.com.curso.udemy.productapi.adapters.in.rabbit.dto.ProductStockDTO;
import br.com.curso.udemy.productapi.aplication.ports.in.product.UpdateProductStockUseCasePort;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class ProductStockListener {

    private final ObjectMapper objectMapper;

    private final UpdateProductStockUseCasePort updateProductStockUseCasePort;

    @RabbitListener(queues = "${app-config.rabbit.queue.product-stock}")
    public void recieveProductStockMessage(ProductStockDTO productStock) throws JsonProcessingException {
        log.info("Recieving message with data: {} and TransactionID: {}",
                objectMapper.writeValueAsString(productStock),
                productStock.getTransactionid());
        log.info(productStock.toString());

        updateProductStockUseCasePort.updateProductStock(productStock);

    }

}
