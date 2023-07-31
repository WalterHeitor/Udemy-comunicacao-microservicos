package br.com.curso.udemy.productapi.aplication.ports.out.product;

import br.com.curso.udemy.productapi.adapters.in.rabbit.sales.SalesConfirmationDTO;

public interface UpdateProductAdapterPort {

    void sendSalesConfirmationMessage(SalesConfirmationDTO message);
}
