package com.davy.restapi.order.response;

import com.davy.restapi.order.dto.OrderItems;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderItemsResponse {

    public OrderItems orderItems;
}