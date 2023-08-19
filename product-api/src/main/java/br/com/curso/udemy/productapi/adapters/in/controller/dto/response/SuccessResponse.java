package br.com.curso.udemy.productapi.adapters.in.controller.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SuccessResponse {
    private String message;
    public static SuccessResponse create(String s) {
        return new SuccessResponse(s);
    }
}
