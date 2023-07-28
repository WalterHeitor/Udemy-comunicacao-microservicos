package br.com.curso.udemy.productapi.adapters.in.rabbit.sales;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SalesConfirmationDTO {

    private String saleId;
    private String status;
}
