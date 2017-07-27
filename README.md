# Yordex Java SDK [![Build Status](https://travis-ci.org/stripe/stripe-java.svg?branch=master)](https://travis-ci.org/stripe/stripe-java)

You can sign up for a Yordex account at https://yordex.com.

## Requirements

Java 1.7 or later.

## Installation

### Maven users

Add this dependency to your project's POM:

```xml
<dependency>
  <groupId>com.yordex</groupId>
  <artifactId>yordex-java</artifactId>
  <version>1.0.0</version>
</dependency>
```

### Gradle users

Add this dependency to your project's build file:

```groovy
compile "com.yordex:yordex-java:1.0.0"
```

### Others

You'll need to manually install the following JARs:

* The Yordex Zip from <HERE>

## Documentation

Please see the [API docs](https://docs.yordex.com/reference).

## Usage

YordexCreateOrderExample.java

```java
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

```

See the project's [Tests](https://bitbucket.com/) for more examples.

## Testing

You must have Maven installed. To run the tests:

    mvn test

You can run particular tests by passing `-D test=Class#method`. Make sure you use the fully qualified class name to differentiate between
unit and functional tests.
