package com.davy.restapi.inventory.request;

import com.davy.restapi.product.entity.ProductEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class InventoryCreateRequest {

    @JsonProperty("quantity")
    public short quantity;

    @JsonIgnore
    public ProductEntity product;
}
