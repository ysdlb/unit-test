package learn.unit.test.mapper;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Slf4j
public class SomeMapperImpl implements SomeMapper{
    @Override
    public List<String> listSomething(List<Integer> ids) {
        log.warn("in SomeMapper::listSomething");
        List<String> list = new ArrayList<>(ids.size());
        for (Integer id : ids) {
            list.add("db-data-" + id);
        }
        return list;
    }

    @Override
    public String getSomething(Integer id) {
        log.warn("in SomeMapper::getSomething");
        return "db-data-" + id;
    }
}
