package learn.unit.test.custome.onlyparam;

import learn.unit.test.custome.Person;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.api.extension.ParameterResolutionException;
import org.junit.jupiter.api.extension.ParameterResolver;

@Slf4j
public class PersonOnlyParamsExtension implements ParameterResolver {

    @Override
    public boolean supportsParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
        log.info("in supportParameter");
        log.info("parameterContext.getParameter() -> {}", parameterContext.getParameter());
        return Person.class.equals(parameterContext.getParameter().getType());
    }

    @Override
    public Object resolveParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
        ExtensionContext.Store store = extensionContext.getStore(ExtensionContext.Namespace.create(this.getClass()));
        log.info("in resolveParameter");
        log.info("from store get -> {}", store.get(Person.class));
        Person person = new Person();
        person.setAge(999999999);
        return person;
    }

}
