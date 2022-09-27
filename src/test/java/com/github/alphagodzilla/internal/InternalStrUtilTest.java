package com.github.alphagodzilla.internal;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class InternalStrUtilTest {
    @Test
    void isEmpty() {
        assertTrue(InternalStrUtil.isEmpty(null));
        assertTrue(InternalStrUtil.isEmpty(""));
        assertFalse(InternalStrUtil.isEmpty("I'm not empty string"));
    }
}
