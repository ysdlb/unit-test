package learn.unit.test.custome.onlyparam;

import learn.unit.test.custome.Person;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

@Slf4j
@ExtendWith(PersonOnlyParamsExtension.class)
public class PersonOnlyParamsTest {

    @Test
    public void doTest(Person p) {
        log.info("person -> {}", p.toString());
    }
}
