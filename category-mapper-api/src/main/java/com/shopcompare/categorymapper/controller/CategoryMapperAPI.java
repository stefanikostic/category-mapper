package com.shopcompare.categorymapper.controller;

import com.shopcompare.categorymapper.service.CategoryMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class CategoryMapperAPI {

    private final CategoryMapper categoryMapper;

    @PostMapping(path = "/mapCategories")
    public ResponseEntity<Map<String, String>> getMappedCategories(@RequestBody List<String> providedCategories) {
        return ResponseEntity.ok(categoryMapper.provideCategories(providedCategories));
    }
}
