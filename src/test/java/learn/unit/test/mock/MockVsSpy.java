package learn.unit.test.mock;

import learn.unit.test.service.OtherService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

/**
 * 1. mock 与 spy 的区别
 * 2. JUnit4 api 与 JUnit5 api 的使用区别: Extend vs Runner
 * 3. Runner MockitoJUnitRunner 与 Extend MockExtension 的等效操作
 */

@Slf4j
@ExtendWith(MockitoExtension.class)
public class MockVsSpy {

    @Mock
    private OtherService otherServiceMock;

    @Spy
    private OtherService otherServiceSpy;

//    @BeforeEach
//    void doBefore() {
//        MockitoAnnotations.initMocks(this);
//    }

    @Test
    public void forMock() {
        log.info("for mock getI       -> {}", otherServiceMock.getI());
        log.info("for mock getInteger -> {}", otherServiceMock.getInteger());
        log.info("for mock .i         -> {}", otherServiceMock.i);
        log.info("for mock .integer   -> {}", otherServiceMock.integer);
    }
    @Test
    public void forSpy() {
        log.info("for spy getI       -> {}", otherServiceSpy.getI());
        log.info("for spy getInteger -> {}", otherServiceSpy.getInteger());
        log.info("for spy .i         -> {}", otherServiceSpy.i);
        log.info("for spy .integer   -> {}", otherServiceSpy.integer);
    }
}
