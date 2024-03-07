package com.shopcompare.categorymapper.configuration;

import com.shopcompare.categorymapper.model.Category;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@ConfigurationProperties
@PropertySource(value = "mappedcategories.properties", encoding = "UTF-8")
@Getter
@Setter
public class MappedCategoriesConfigurationProperties {

    private List<Category> categories;
}
