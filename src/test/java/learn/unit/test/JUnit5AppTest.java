package learn.unit.test;

import learn.unit.test.service.SomeService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;
//import org.junit.platform.runner.JUnitPlatform;
//import org.junit.runner.RunWith;

import static org.junit.jupiter.api.Assertions.assertEquals;

// 若 IDE 或 构建系统支持 JUnit4 但不支持 JUnit Platform，则需要此注解
//@RunWith(JUnitPlatform.class)
@Slf4j
@DisplayName("Testing using JUnit 5")
public class JUnit5AppTest {

    private SomeService someService;

    @BeforeAll
    public static void init() {
        // Do something before ANY test is run in this class
    }

    @AfterAll
    public static void done() {
        // Do something after ALL tests in this class are run
    }

    @BeforeEach
    public void setUp() throws Exception {
        someService = new SomeService();
    }

    @AfterEach
    public void tearDown() throws Exception {
        someService = null;
    }

    @Test
    @DisplayName("Dummy test")
    void aTest() {
        log.info("As written, this test will always pass!");
        assertEquals(4, (2 + 2));
    }

    @Test
    @Disabled
    @DisplayName("A disabled test")
    void testNotRun() {
        log.info("This test will not run (it is disabled, silly).");
    }
}
