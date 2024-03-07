package com.shopcompare.categorymapper.controller;

import com.shopcompare.elasticsearch.client.commons.model.Product;
import com.shopcompare.elasticsearch.client.search.service.ElasticsearchClientSearch;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class ProductsAPI {

    private final ElasticsearchClientSearch elasticsearchClientSearch;

    @GetMapping(path = "/products")
    public ResponseEntity<List<Product>> fetchProducts() throws IOException {
        return ResponseEntity.ok(elasticsearchClientSearch.getProductsByName("BOSCH"));
    }

}
