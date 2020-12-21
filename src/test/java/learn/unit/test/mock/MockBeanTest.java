package learn.unit.test.mock;

import learn.unit.test.controller.SomeController;
import learn.unit.test.service.SomeService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

/**
 * WebMvcTest 只会扫描少数类
 * 原来的容器中貌似不存在 SomeService 相关的 Bean
 */
@WebMvcTest(SomeController.class)
@Slf4j
class MockBeanTest {
    @Autowired
    private MockMvc mvc;

    @MockBean
    private SomeService someServiceMock;

    @Test
    public void testExample() throws Exception {
        String mockedStr = "mocked-999";

        Mockito.when(someServiceMock.getSomething(Mockito.anyInt()))
                .thenReturn(mockedStr);

//            Mockito.when(someServiceMock.listSomething(Mockito.anyList()))
//                    .thenReturn(null);

        log.info("request {id}");
        mvc.perform(
                MockMvcRequestBuilders.get("/1")
                        .accept(MediaType.TEXT_PLAIN)
        ).andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("mocked-999"))
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
