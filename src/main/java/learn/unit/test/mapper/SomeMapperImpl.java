package learn.unit.test.mapper;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class SomeMapperImpl implements SomeMapper{
    @Override
    public List<String> listSomething(List<Integer> ids) {
        List<String> list = new ArrayList<>(ids.size());
        for (Integer id : ids) {
            list.add("db-data-" + id);
        }
        return list;
    }

    @Override
    public String getSomething(Integer id) {
        return "db-data-" + id;
    }
}
