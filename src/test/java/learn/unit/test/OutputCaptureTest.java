package learn.unit.test;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.system.CapturedOutput;
import org.springframework.boot.test.system.OutputCaptureExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(OutputCaptureExtension.class)
public class OutputCaptureTest {

    @AfterEach
    public void doAfter(CapturedOutput output) {
        assertThat(output.getOut()).contains("ok");
        assertThat(output.getErr()).contains("error");
    }

    @Test
    public void doTest(CapturedOutput output) {
        System.out.println("ok");
        assertThat(output).contains("ok");
        System.err.println("error");
    }
}
