package learn.unit.test.mock;

import learn.unit.test.controller.SomeController;
import learn.unit.test.service.SomeService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

/**
 * no bean -> no mock no proxy ->
 */
@WebMvcTest(SomeController.class)
//@ContextConfiguration(classes = Config.class)
//@EnableAspectJAutoProxy
@Slf4j
public class MockitoTest {
    @Autowired
    private MockMvc mvc;

    @Mock
    private SomeService someServiceMock;

    @Test
    public void testExample() throws Exception {
        String mockedStr = "mocked-999";

        Mockito.when(someServiceMock.getSomething(Mockito.anyInt()))
                .thenReturn(mockedStr);

//        Mockito.when(someServiceMock.listSomething(Mockito.anyList()))
//                .thenReturn(null);

        log.info("request {id}");
        mvc.perform(
                MockMvcRequestBuilders.get("/1")
                        .accept(MediaType.TEXT_PLAIN)
        ).andExpect(MockMvcResultMatchers.status().isOk())
//                .andExpect(MockMvcResultMatchers.content().string("mocked-999"))
                .andDo(MockMvcResultHandlers.print());

        log.info("request list");
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/list")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content("{\"ids\": [1,2,3]}");
        mvc.perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());


        log.info("out put stubbing");
        Mockito.mockingDetails(someServiceMock).getStubbings().forEach(
                stubbing -> {
                    log.info("invocations -> {}", stubbing.getInvocation());
                    log.info("wasUsed -> {}", stubbing.wasUsed());
                }
        );
    }
}
