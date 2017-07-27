package com.yordex.error

import com.yordex.http.RestResource
import com.yordex.model.ApiError

class ApiException extends Exception {

    private ApiError apiError

    private String message

    ApiException(ApiError apiError) {
        this.apiError = apiError
        message = RestResource.OBJECT_MAPPER.writeValueAsString(apiError)
    }

    ApiError getApiError() {
        return apiError
    }

    String getMessage() {
        return message
    }
}
