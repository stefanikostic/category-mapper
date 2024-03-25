package com.shopcompare.categorymapper.controller;

import com.shopcompare.categorymapper.service.CategoryMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * Rest controller providing endpoints for category mapper operations.
 */
@RestController
@RequiredArgsConstructor
public class CategoryMapperAPI {

    private final CategoryMapper categoryMapper;

    /**
     * Declares an endpoint for mapping provided categories into predefined categories.
     *
     * @param providedCategories categories provided from external service that will be mapped into predefined
     *                           categories.
     * @return key value map of mapped categories.
     */
    @PostMapping(path = "/mapCategories")
    public ResponseEntity<Map<String, String>> getMappedCategories(@RequestBody List<String> providedCategories) {
        return ResponseEntity.ok(categoryMapper.provideCategories(providedCategories));
    }
}
