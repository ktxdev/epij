package com.ktxdev;

public class ComputingTheParityOfAWord {
    public static short parityBruteForce(long x) {
        short result = 0;
        while (x != 0) {
            result ^= (x & 1);
            x >>>= 1;
        }
        return result;
    }

    public static short parityRefinedBruteForce(long x) {
        short result = 0;
        while (x != 0) {
            result ^= 1;
            x &= (x - 1); // Drops the lowest set bit of x
        }
        return result;
    }

    public static short parityLookupTable(long x) {
        final int[] PRECOMPUTED_PARITY = {0, 1, 1, 0, 1, 0, 0, 1, 1, 0, 0, 1, 0, 1, 1, 0};
        final int WORD_SIZE = 16;
        final int BIT_MASK = 0xFFFF;
        return (short) (PRECOMPUTED_PARITY[(int)((x >>> (3 * WORD_SIZE)) & BIT_MASK)]
                ^ PRECOMPUTED_PARITY[(int)((x >>> (2 * WORD_SIZE)) & BIT_MASK)]
                ^ PRECOMPUTED_PARITY[(int)((x >>> WORD_SIZE) & BIT_MASK)]
                ^ PRECOMPUTED_PARITY[(int)(x & BIT_MASK)]);
    }

    public static short parityAssociative(long x) {
        x ^= x >>> 32;
        x ^= x >>> 16;
        x ^= x >>> 8;
        x ^= x >>> 4;
        x ^= x >>> 2;
        x ^= x >>> 1;
        return (short) (x & 0x1);
    }

    public static short parityRecursive(long x) {
        return parityRecursive(x, 32);
    }

    public static short parityRecursive(long x, int y) {
        if (y == 0) return (short) (x & 0x1);
        return parityRecursive(x ^ (x >>> y), y / 2);
    }
}
