package com.davy.restapi.product.service;

import com.davy.restapi.product.dto.ProductRequestDTO;
import com.davy.restapi.product.entity.Product;
import com.davy.restapi.shared.service.CrudService;

import java.util.Map;

public interface ProductService extends CrudService<Product, ProductRequestDTO> {

    Map<String, Object> filterAndSearchProductsByNamePageable(Long categoryId,
                                                              Long subCategoryId,
                                                              String name,
                                                              int page,
                                                              String sortBy,
                                                              String sortOrder);
}
