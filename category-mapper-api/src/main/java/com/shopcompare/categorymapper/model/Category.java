package com.shopcompare.categorymapper.model;

/**
 * Business model representing category.
 *
 * @param name predefined name of the category.
 * @param values all possible variations of the category naming.
 */
public record Category (String name, String values) {}
