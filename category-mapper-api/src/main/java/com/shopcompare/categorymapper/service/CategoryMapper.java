package com.shopcompare.categorymapper.service;

import com.shopcompare.categorymapper.configuration.MappedCategoriesConfigurationProperties;
import com.shopcompare.categorymapper.model.Category;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Service whose main goal is to provide mappings of the input categories.
 */
@Service
@RequiredArgsConstructor
public class CategoryMapper {

    private static final String COMMA_WHITE_SPACE_REGEX = ", ";
    private final MappedCategoriesConfigurationProperties mappedCategoriesConfigurationProperties;

    /**
     * This method includes the logic for mapping the provided categories:
     * <ul>
     *     <li>It fetches the predefined categories from configuration properties and converts them into a map where the
     *      key is each variation of the category name and the values is the predefined name of the category.</li>
     *     <li>Traverses through all provided categories and tries to find its corresponding mapping from the map
     *      of predefined categories.</li>
     * </ul>
     * <i>For more information about the predefined categories and its variations, check the mappedcategories
     * .properties. There, we have listed all pre-saved categories in our system with listing its naming
     * variations as value.</i>
     *
     * @param providedCategories input categories that will be mapped into corresponding category names.
     *
     * @return key-value pair map where key presents the provided category and the value is the mapped predefined
     * category name.
     */
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
