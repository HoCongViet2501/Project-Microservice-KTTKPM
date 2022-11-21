package com.kttk.products.microservice.util;

import com.kttk.products.microservice.dto.request.OrdersRequest;
import dto.response.OrderResponse;
import org.springframework.http.*;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpMessageConverterExtractor;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class RestOrderService {
    private HttpHeaders setHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Accept", MediaType.APPLICATION_JSON_VALUE);
        headers.setContentType(MediaType.APPLICATION_JSON);

        return headers;
    }

    public OrderResponse createOrder(OrdersRequest order) {
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<OrdersRequest> request = new HttpEntity<>(order, setHeaders());
        ResponseEntity<OrderResponse> response = restTemplate.postForEntity("http://localhost:8080/api/orders", request, OrderResponse.class);
        return response.getBody();
    }

    public OrderResponse getOrderById(Integer id) {
        RestTemplate restTemplate = new RestTemplate();
        List<HttpMessageConverter<?>> messageConverters = new ArrayList<HttpMessageConverter<?>>();
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        converter.setSupportedMediaTypes(Collections.singletonList(MediaType.ALL));
        messageConverters.add(converter);
        restTemplate.setMessageConverters(messageConverters);

        HttpEntity<Object> requestBody = new HttpEntity<>(setHeaders());

        ResponseEntity<OrderResponse> res = restTemplate.exchange("http://localhost:8080/api/orders/" + id, HttpMethod.GET, requestBody, OrderResponse.class);

        return res.getBody();
    }
}
