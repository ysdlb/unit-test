package learn.unit.test.service;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class OtherService {

    public String getMessage() {
        log.info("in OtherService::getMessage");
        return "in OtherService::getMessage";
    }

    public int doMultiPly(int num1, int num2) {
        log.info("in OtherService::doMultiPly");
        return num1 * num2;
    }

    public boolean isValid(long id) {
        log.info("in OtherService::isValid");
        if (id <= 100) {
            return true;
        }
        return false;
    }

    public Integer integer = 9999;
    public int i = 9999;

    public int getI() {
        return i;
    }

    public Integer getInteger() {
        return integer;
    }
}
