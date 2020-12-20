package learn.unit.test;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AssertAllTest {

    @Test
    @Disabled
    @DisplayName("assert all")
    public void all() {
        assertAll("Math",
                () -> fail("expected fail"),
                () -> assertEquals(1, 2),
                () -> assertTrue(false)
        );
    }

    @Test
    @Disabled
    @DisplayName("not assert all")
    public void notAll() {
        fail("expected fail");
        assertEquals(1, 2);
        assertTrue(false);
    }

}
