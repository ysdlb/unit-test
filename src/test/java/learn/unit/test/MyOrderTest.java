package learn.unit.test;

import learn.unit.test.service.OtherService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

//@Disabled
//@ExtendWith(SpringExtension.class)
//@ContextConfiguration(classes = Config.class)
//@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class MyOrderTest {
    @Autowired
    private OtherService otherService;

    @BeforeAll
    public static void initAll() {
        log.error("---Inside BeforeAll---");
    }

    @BeforeEach
    public void init(TestInfo testInfo) {
        log.error("Start...{}", testInfo.getDisplayName());
    }

    @AfterEach
    public void tearDown(TestInfo testInfo) {
        log.error("Finished...{}", testInfo.getDisplayName());
    }

    @AfterAll
    public static void tearDownAll() {
        log.error("---Inside AfterAll---");
    }

//    @org.junit.Test
    @Test
    public void messageTest() {
        String msg = otherService.getMessage();
        assertEquals("in OtherService::getMessage", msg);
    }

    @Test
    public void doMultiplyTest() {
        int val = otherService.doMultiPly(5, 10);
        assertEquals(50, val);
    }

    @Test
    public void isValidTest() {
        boolean val = otherService.isValid(100);
        assertTrue(val);
    }

}
