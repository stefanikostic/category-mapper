# Category mapper - Shop compare

Table of contents
---
- [General info](#general-info)
- [How it works](#how-it-works)
<br/>

General info
---
**Shop compare** is a web application that provides prices comparison for different appliances and electronic
devices. The main goal of this application is to enable customers find their required product at the best price available at the
market. <br/>
The category names of the products can differ within different shops. For instance, computer components and computer parts
are the same category, but the namings are different. Secondly, some of the products are sub-categorized in one shop, while generalized into 
one super category in other shop. In order to group the products of all categories correctly, we would need to define predefined categories
and map the variations of category names under one standard category name.

**Category mapper** is a service used that defines standard category names that will be used throughout the system and maps
categories from various inputs into the predefined categories. The predefined categories are persisted in DB.

To achieve correct mapping of the categories from the shops into the standard pre saved category names, we would need also to define
the possible variations of each category. Therefore, in the [mapped-categories.properties](category-mapper-api/src/main/resources/mappedcategories.properties) file we have defined a list of
categories whereas name we have the predefined category name such as in DB, and as values we have all possible different combinations
of that category (different category names from the different shops). <br/>
Besides the naming variation, as above-mentioned, here we want also to group some of the subcategories into one general category
since the granular sub-categorization in some cases is obsolete.

How it works
---
The category mapper is a RESTful API service for mapping list of input categories into the predefined categories using the
mapped categories from configuration properties. More information about the logic of how this is achieved can be found in the 
Javadocs of [CategoryMapperAPI](category-mapper-api/src/main/java/com/shopcompare/categorymapper/controller/CategoryMapperAPI.java)<br/>
The REST call to use the operation to map the list of input categories to the pre-saved ones should look like this: 
- **DEV environment** http://localhost:8089/mapCategories/mapCategories POST method and the body should be a list of category names as strings.
- Example request body: 

`[
  "ТВ/Аудио/Видео ТЕЛЕВИЗОРИ",
  "КУЈНСКИ ПРИБОР И САДОВИ КУЈНСКИ ПРИБОР",
  "МАШИНИ ЗА ПЕРЕЊЕ/СУШЕЊЕ Додатоци"
  ]`

- Example response body:

`{
  "ТВ/Аудио/Видео ТЕЛЕВИЗОРИ": "Телевизори",
  "КУЈНСКИ ПРИБОР И САДОВИ КУЈНСКИ ПРИБОР": "Кујнски прибор",
  "МАШИНИ ЗА ПЕРЕЊЕ/СУШЕЊЕ Додатоци": "Машини за перење и сушење на алишта"
  }`

