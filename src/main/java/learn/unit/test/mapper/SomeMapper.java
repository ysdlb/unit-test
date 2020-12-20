package learn.unit.test.mapper;

import java.util.List;

public interface SomeMapper {
    List<String> listSomething(List<Integer> ids);
    String getSomething(Integer id);
}
