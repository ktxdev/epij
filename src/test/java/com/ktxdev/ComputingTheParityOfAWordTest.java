package com.ktxdev;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class ComputingTheParityOfAWordTest {

    private static final String TESTS_FILENAME = "src/main/resources/computing_the_parity_of_a_word_test_cases.txt";
    private static final Map<Long, Integer> TEST_CASES = new HashMap<>();

    @BeforeAll
    public static void setUp() {
        try(BufferedReader reader = new BufferedReader(new FileReader(TESTS_FILENAME))) {
            var testCases = reader.lines().collect(Collectors.toList());
            if (testCases.size() % 2 != 0) {
                throw new RuntimeException("Invalid test cases in file");
            }
            for (int i = 0; i < testCases.size(); i += 2) {
                TEST_CASES.put(Long.valueOf(testCases.get(i)), Integer.valueOf(testCases.get(i + 1)));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void testParityBruteForce() {
        for (Long testCase: TEST_CASES.keySet()) {
            short result = ComputingTheParityOfAWord.parityBruteForce(testCase);
            short expected = (short) ((int) TEST_CASES.get(testCase));

            assertEquals(result, expected);
        }
    }

    @Test
    public void testRefinedBruteForce() {
        for (Long testCase: TEST_CASES.keySet()) {
            short result = ComputingTheParityOfAWord.parityRefinedBruteForce(testCase);
            short expected = (short) ((int) TEST_CASES.get(testCase));

            assertEquals(result, expected);
        }
    }

    @Test
    public void testLookupTable() {
        for (Long testCase: TEST_CASES.keySet()) {
            short result = ComputingTheParityOfAWord.parityLookupTable(testCase);
            short expected = (short) ((int) TEST_CASES.get(testCase));

            assertEquals(result, expected);
        }
    }

    @Test
    public void testAssociative() {
        for (Long testCase: TEST_CASES.keySet()) {
            short result = ComputingTheParityOfAWord.parityAssociative(testCase);
            short expected = (short) ((int) TEST_CASES.get(testCase));

            assertEquals(result, expected);
        }
    }

    @Test
    public void testRecursive() {
        for (Long testCase: TEST_CASES.keySet()) {
            short result = ComputingTheParityOfAWord.parityRecursive(testCase);
            short expected = (short) ((int) TEST_CASES.get(testCase));

            assertEquals(result, expected);
        }
    }
}