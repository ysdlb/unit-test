package learn.unit.test.mock;

import learn.unit.test.service.OtherService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

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
