package com.davy.restapi.order.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;

@Builder
@Getter
@Setter
public class OrderCreateRequest {

    @JsonProperty("paymentId")
    private Long PaymentId;
}
