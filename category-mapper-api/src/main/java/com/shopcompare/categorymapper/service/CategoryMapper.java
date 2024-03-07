package com.shopcompare.categorymapper.service;

import com.shopcompare.categorymapper.configuration.MappedCategoriesConfigurationProperties;
import com.shopcompare.categorymapper.model.Category;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class CategoryMapper {

    private static final String COMMA_WHITE_SPACE_REGEX = ", ";
    private final MappedCategoriesConfigurationProperties mappedCategoriesConfigurationProperties;


    public Map<String, String> provideCategories(List<String> providedCategories) {
        List<Category> preSavedCategories = mappedCategoriesConfigurationProperties.getCategories();

        Map<String, String> categoriesByKey = new HashMap<>();
        for (Category category : preSavedCategories) {
            String categoriesData = category.values();
            List<String> mappedCategories = Arrays.stream(categoriesData.split(COMMA_WHITE_SPACE_REGEX)).toList();
            for (String mappedCategory : mappedCategories) {
                categoriesByKey.put(mappedCategory, category.name());
            }
        }

        Map<String, String> providedCategoryMappedByKey = new HashMap<>();
        for (String providedCategory : providedCategories) {
            String key = categoriesByKey.get(providedCategory);
            if (key != null) {
                providedCategoryMappedByKey.put(providedCategory, key);
            }
        }

        return providedCategoryMappedByKey;
    }

}
