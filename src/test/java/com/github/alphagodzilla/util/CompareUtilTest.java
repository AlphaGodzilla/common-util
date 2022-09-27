package com.github.alphagodzilla.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CompareUtilTest {

    @Test
    void isGreaterThan() {
        assertFalse(CompareUtil.isGreaterThan(1, 1));
        assertFalse(CompareUtil.isGreaterThan(1, 2));
        assertTrue(CompareUtil.isGreaterThan(2, 1));
    }

    @Test
    void isLessThan() {
        assertTrue(CompareUtil.isLessThan(1, 2));
        assertFalse(CompareUtil.isLessThan(1, 1));
        assertFalse(CompareUtil.isLessThan(2, 1));
    }

    @Test
    void testEquals() {
        assertTrue(CompareUtil.equals(1, 1));
        assertFalse(CompareUtil.equals(1, 2));
    }
}
