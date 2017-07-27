package com.yordex.model;

import com.yordex.error.ApiException;
import com.yordex.error.ClientException;
import com.yordex.http.RequestOptions;

import java.util.List;

public class YordexCreateOrderExample {
    public static void main(String[] args) {
        RequestOptions requestOptions = RequestOptions.builder().apiKey("api-key").build();
        Order order = Order.builder().buyerId("buyerId").sellerId("sellerId").build();

        try {
            Order newOrder = Order.create(order, requestOptions);
        } catch (ApiException ex) {
            ApiError apiError = ex.getApiError();
            List<ApiError.Error> errors = apiError.getErrors();
            String detail = apiError.getDetail();
            //Handle error
            ex.printStackTrace();
        } catch (ClientException e) {
            //Handle error
            e.printStackTrace();
        }
    }
}
