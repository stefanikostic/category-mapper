package com.shopcompare.categorymapper.service;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
public class CategorySimilarityCalculator {

    public double checkSimilarString(String word1, String word2) {
        double jaccardSimilarity = calculateJaccardSimilarityBetweenCharacters(word1, word2);

        if (word1.contains(word2)) {
            return jaccardSimilarity + (1 - jaccardSimilarity) * 0.5;
        }

        return jaccardSimilarity;
    }

    public double calculateJaccardSimilarityBetweenWords(String preSavedCategory, String secondCategory) {
        Set<String> firstCategoryWords = tokenize(preSavedCategory, "\\s+");
        Set<String> secondCategoryWords = tokenize(secondCategory, "\\s+");

        Set<String> intersectionWords = new HashSet<>(firstCategoryWords);
        intersectionWords.retainAll(secondCategoryWords);

        Set<String> unionWords = new HashSet<>(firstCategoryWords);
        unionWords.addAll(secondCategoryWords);

        List<String> preSavedCategoryNonMatch = new ArrayList<>(firstCategoryWords);
        preSavedCategoryNonMatch.removeAll(intersectionWords);

        List<String> secondCategoryNonMatch = new ArrayList<>(secondCategoryWords);
        secondCategoryNonMatch.removeAll(intersectionWords);


        Map<String, String> mappedWords = new HashMap<>();
        for (String preSavedNonMatch : preSavedCategoryNonMatch) {
            String mostSimilar = "";
            double maxSimilarity = 0;

            for (String secondNonMatch : secondCategoryNonMatch) {
                double similarityBetweenCharacters = calculateJaccardSimilarityBetweenCharacters(preSavedNonMatch, secondNonMatch);
                if (similarityBetweenCharacters > maxSimilarity) {
                    maxSimilarity = similarityBetweenCharacters;
                }
                mostSimilar = secondNonMatch;
            }

            mappedWords.put(mostSimilar, preSavedCategory);
        }


        return (double) intersectionWords.size() / unionWords.size();
    }

    public double calculateJaccardSimilarityBetweenCharacters(String firstCategory, String secondCategory) {
        Set<String> firstCategoryCharacters = tokenize(firstCategory, "");
        Set<String> secondCategoryCharacters = tokenize(secondCategory, "");

        Set<String> intersectionCharacters = new HashSet<>(firstCategoryCharacters);
        intersectionCharacters.retainAll(secondCategoryCharacters);

        Set<String> unionCharacters = new HashSet<>(firstCategoryCharacters);
        unionCharacters.addAll(secondCategoryCharacters);

        return (double) intersectionCharacters.size() / unionCharacters.size();
    }

    private Set<String> tokenize(String category, String splitter) {
        String[] parts = category.split(splitter);
        Set<String> tokens = new HashSet<>();
        for (String part : parts) {
            tokens.add(part.toLowerCase());
        }

        return tokens;
    }
}
