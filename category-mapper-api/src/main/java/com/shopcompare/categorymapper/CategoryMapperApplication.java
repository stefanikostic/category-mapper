package com.shopcompare.categorymapper;

import com.shopcompare.elasticsearch.client.search.ElasticsearchClientSearchMarker;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackageClasses = {CategoryMapperMarker.class, ElasticsearchClientSearchMarker.class})
public class CategoryMapperApplication {

	public static void main(String[] args) {
		SpringApplication.run(CategoryMapperApplication.class, args);
	}

}
