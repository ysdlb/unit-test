package learn.unit.test;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Calculator")
public class CalculatorTest {

    private Integer integer;

    @BeforeAll
    public static void init() {
        System.out.println("Start testing");
    }

    @BeforeEach
    public void create() {
        this.integer = Integer.valueOf("1");
    }

    @AfterEach
    public void destroy() {
        this.integer = null;
    }

    @AfterAll
    public static void cleanup() {
        System.out.println("Finish testing");
    }

    @Test
    @DisplayName("Test 1 + 2 = 3")
    public void testAdd() {
        assertEquals(3, integer + 2);
    }

    @Test
    @DisplayName("Test 1 - 1 = 0")
    public void testSubtract() {
        assertEquals(0, this.integer - 1);
    }


    @Disabled
    @Test
    @DisplayName("disabled test")
    public void ignoredTest() {
        System.out.println("This test is disabled");
    }
}
