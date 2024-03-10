package com.davy.restapi.product.controller.testsuite;

import com.davy.restapi.shared.TestContainer;
import com.davy.restapi.shared.utils.TestAssertionUtils;
import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.*;
import org.springframework.test.annotation.DirtiesContext;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
public class ProductPaginationTest extends TestContainer {

    @DisplayName("Fetch products paginated without params")
    @Test
    @Order(1)
    public void shouldFetchProductsPaginated() throws Exception {
        String responseBody = restTemplate
                .getForObject("http://localhost:" + port + "/api/v1/products", String.class);
        JSONObject response = new JSONObject(responseBody);
        assertNotNull(responseBody, "Response body should not be null");
        TestAssertionUtils.assertResponseHasExpectedStatusCode(response,200);
        TestAssertionUtils.assertArrayHasExpectedSize(response, "products", 8);
        TestAssertionUtils.assertListResponseHasExpectedFields(response, "products", expectedProductFields());
        TestAssertionUtils.assertResultHasExpectedMetaDataFields(response);
    }

    @DisplayName("Fetch products paginated with page param: 3 and pagesize param: 4")
    @Test
    @Order(2)
    public void shouldFetchProductsPaginatedWithPage3PageSize4() throws Exception {
        int page3 = 2;
        int pageSize4 = 4;
        String responseBody = restTemplate
                .getForObject("http://localhost:" + port + "/api/v1/products?page=" + page3 + "&pagesize=" + pageSize4,
                        String.class);
        JSONObject response = new JSONObject(responseBody);
        TestAssertionUtils.assertResponseHasExpectedStatusCode(response,200);
        TestAssertionUtils.assertArrayHasExpectedSize(response, "products", 3);
        TestAssertionUtils.assertListResponseHasExpectedFields(response, "products", expectedProductFields());
        TestAssertionUtils.assertResultHasExpectedMetaDataFields(response);
    }

    @DisplayName("Fetch products paginated with page param: 1 and pagesize param: 5")
    @Test
    @Order(3)
    public void shouldFetchProductsPaginatedWithPage1PageSize5() throws Exception {
        int page1 = 0;
        int pageSize5 = 5;
        String responseBody = restTemplate
                .getForObject("http://localhost:" + port + "/api/v1/products?page=" + page1 + "&pagesize=" + pageSize5,
                        String.class);
        JSONObject response = new JSONObject(responseBody);
        TestAssertionUtils.assertResponseHasExpectedStatusCode(response,200);
        TestAssertionUtils.assertArrayHasExpectedSize(response, "products", 5);
        TestAssertionUtils.assertListResponseHasExpectedFields(response, "products", expectedProductFields());
        TestAssertionUtils.assertResultHasExpectedMetaDataFields(response);
    }

    @DisplayName("Fetch products paginated with search param: 'the'")
    @Test
    @Order(4)
    public void shouldFetchProductsPaginatedWithSearchFor_the() throws Exception {
        String search = "The";
        String responseBody = restTemplate
                .getForObject("http://localhost:" + port + "/api/v1/products?search=" + search,
                        String.class);
        JSONObject response = new JSONObject(responseBody);
        TestAssertionUtils.assertResponseHasExpectedStatusCode(response,200);
        TestAssertionUtils.assertArrayHasExpectedSize(response, "products", 4);
        TestAssertionUtils.assertListResponseHasExpectedFields(response, "products", expectedProductFields());
        TestAssertionUtils.assertResultHasExpectedMetaDataFields(response);
    }

    @DisplayName("Fetch products paginated with search param: 'fairy tale'")
    @Test
    @Order(5)
    public void shouldFetchProductsPaginatedWithSearchFor_fairy_tale() throws Exception {
        String search = "fairy tale";
        String responseBody = restTemplate
                .getForObject("http://localhost:" + port + "/api/v1/products?search=" + search,
                        String.class);
        JSONObject response = new JSONObject(responseBody);
        TestAssertionUtils.assertResponseHasExpectedStatusCode(response,200);
        TestAssertionUtils.assertArrayHasExpectedSize(response, "products", 1);
        TestAssertionUtils.assertListResponseHasExpectedFields(response, "products", expectedProductFields());
        TestAssertionUtils.assertResultHasExpectedMetaDataFields(response);
    }

    @DisplayName("Fetch products paginated with sort param: 'id' and order param: 'asc'")
    @Test
    @Order(6)
    public void shouldFetchProductsPaginatedWithSortOnIdOrderAsc() throws Exception {
        String sortBy= "id";
        String order = "asc";
        String responseBody = restTemplate
                .getForObject("http://localhost:" + port + "/api/v1/products?sort=" + sortBy + "&order=" + order,
                        String.class);
        JSONObject response = new JSONObject(responseBody);
        TestAssertionUtils.assertResponseHasExpectedStatusCode(response,200);
        TestAssertionUtils.assertArrayHasExpectedSize(response, "products", 8);
        TestAssertionUtils.assertListResponseHasExpectedFields(response, "products", expectedProductFields());
        TestAssertionUtils.assertResultHasExpectedMetaDataFields(response);
    }

    @DisplayName("Fetch products paginated with sort param: 'id' and order param: 'desc'")
    @Test
    @Order(7)
    public void shouldFetchProductsPaginatedWithSortOnIdOrderDesc() throws Exception {
        String sortBy= "id";
        String order = "desc";
        String responseBody = restTemplate
                .getForObject("http://localhost:" + port + "/api/v1/products?sort=" + sortBy + "&order=" + order,
                        String.class);
        JSONObject response = new JSONObject(responseBody);
        TestAssertionUtils.assertResponseHasExpectedStatusCode(response,200);
        TestAssertionUtils.assertArrayHasExpectedSize(response, "products", 8);
        TestAssertionUtils.assertListResponseHasExpectedFields(response, "products", expectedProductFields());
        TestAssertionUtils.assertResultHasExpectedMetaDataFields(response);
    }

    @DisplayName("Fetch products paginated with sort param: 'name' and order param: 'asc'")
    @Test
    @Order(8)
    public void shouldFetchProductsPaginatedWithSortOnNameOrderAsc() throws Exception {
        String sortBy= "name";
        String order = "desc";
        String responseBody = restTemplate
                .getForObject("http://localhost:" + port + "/api/v1/products?sort=" + sortBy + "&order=" + order,
                        String.class);
        JSONObject response = new JSONObject(responseBody);
        TestAssertionUtils.assertResponseHasExpectedStatusCode(response,200);
        TestAssertionUtils.assertArrayHasExpectedSize(response, "products", 8);
        TestAssertionUtils.assertListResponseHasExpectedFields(response, "products", expectedProductFields());
        TestAssertionUtils.assertResultHasExpectedMetaDataFields(response);
    }

    @DisplayName("Fetch products paginated with sort param: 'name' and order param: 'desc'")
    @Test
    @Order(9)
    public void shouldFetchProductsPaginatedWithSortOnNameOrderDesc() throws Exception {
        String sortBy= "name";
        String order = "desc";
        String responseBody = restTemplate
                .getForObject("http://localhost:" + port + "/api/v1/products?sort=" + sortBy + "&order=" + order,
                        String.class);
        JSONObject response = new JSONObject(responseBody);
        TestAssertionUtils.assertResponseHasExpectedStatusCode(response,200);
        TestAssertionUtils.assertArrayHasExpectedSize(response, "products", 8);
        TestAssertionUtils.assertListResponseHasExpectedFields(response, "products", expectedProductFields());
        TestAssertionUtils.assertResultHasExpectedMetaDataFields(response);
    }

    @NotNull
    private static JSONObject getJsonObjectToUpdate() throws JSONException {
        JSONObject requestBody = new JSONObject();
        requestBody.put("name", "Test Product Updated");
        requestBody.put("description", "Test Product Description Updated");
        requestBody.put("imageUrl", "https://example.com/image.jpg");
        requestBody.put("purchasePrice", 49.99);
        requestBody.put("sellingPrice", 59.99);
        requestBody.put("vat", 0);
        requestBody.put("quantity", 100);
        requestBody.put("categoryId", 1);
        requestBody.put("subCategoryId", 2);
        return requestBody;
    }

    @NotNull
    private static JSONObject getJsonObjectToSave() throws JSONException {
        JSONObject requestBody = new JSONObject();
        requestBody.put("name", "Test Product Saved");
        requestBody.put("description", "Test Product Description");
        requestBody.put("imageUrl", "https://example.com/image.jpg");
        requestBody.put("purchasePrice", 49.99);
        requestBody.put("sellingPrice", 59.99);
        requestBody.put("vat", 0);
        requestBody.put("quantity", 100);
        requestBody.put("categoryId", 1);
        requestBody.put("subCategoryId", 2);
        return requestBody;
    }

    private List<String> expectedProductFields() {
        List<String> fields = new ArrayList<>();
        fields.add("id");
        fields.add("name");
        fields.add("imageUrl");
        fields.add("description");
        fields.add("purchasePrice");
        fields.add("sellingPrice");
        fields.add("vat");
        fields.add("category");
        fields.add("inventory");
        fields.add("sellingPrice");
        return fields;
    }
}