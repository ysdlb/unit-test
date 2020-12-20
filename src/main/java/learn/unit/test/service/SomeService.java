package learn.unit.test.service;

import learn.unit.test.aspect.SomeAnnotation;
import learn.unit.test.mapper.SomeMapper;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class SomeService {

    @Getter
    @Autowired
    SomeMapper someMapper;

    @SomeAnnotation
    public List<String> listSomething(List<Integer> ids) {
        return someMapper.listSomething(ids);
    }

    @SomeAnnotation
    public String getSomething(Integer id) {
        return someMapper.getSomething(id);
    }


}
