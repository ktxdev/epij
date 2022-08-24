package com.ktxdev;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class SwapBitsTest {

    private static final String TESTS_FILENAME = "src/main/resources/swap_bits_test_cases.txt";
    private static final Map<Long[], Long> TEST_CASES = new HashMap<>();

    @BeforeAll
    public static void setUp() {
        try(BufferedReader reader = new BufferedReader(new FileReader(TESTS_FILENAME))) {
            var testCases = reader.lines().collect(Collectors.toList());
            if (testCases.size() % 2 != 0) {
                throw new RuntimeException("Invalid test cases in file");
            }
            for (int i = 0; i < testCases.size(); i += 2) {
                Long[] tests = Arrays.stream(testCases.get(i).split(","))
                        .map(Long::valueOf)
                        .toArray(Long[]::new);

                TEST_CASES.put(tests, Long.valueOf(testCases.get(i + 1)));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void testSwapBits() {
        for (Long[] testCase: TEST_CASES.keySet()) {
            long result = SwapBits.swapBits(testCase[0], testCase[1].intValue(), testCase[2].intValue());
            long expected = TEST_CASES.get(testCase);

            assertEquals(result, expected);
        }
    }
}