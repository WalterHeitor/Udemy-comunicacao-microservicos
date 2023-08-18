package br.com.curso.udemy.productapi.config.intercepctor;

import br.com.curso.udemy.productapi.adapters.out.persistence.exceptions.ValidationException;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

public class FeignClientAuthInterceptor implements RequestInterceptor {

    private static final String AUTHORIZATION = "Authorization";
    @Override
    public void apply(RequestTemplate template) {
        var currentRequest = getCurrentRequest();
        template
                .header(AUTHORIZATION, currentRequest.getHeader(AUTHORIZATION));
    }

    private HttpServletRequest getCurrentRequest() {

        try {
            return ((ServletRequestAttributes) RequestContextHolder
                    .getRequestAttributes())
                    .getRequest();

        } catch (Exception e) {
            e.printStackTrace();
            throw  new ValidationException("The current request could no be proccessed");
        }
    }
}
