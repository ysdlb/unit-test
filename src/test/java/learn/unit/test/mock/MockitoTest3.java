package learn.unit.test.mock;

import learn.unit.test.controller.SomeController;
import learn.unit.test.service.SomeService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

/**
 * 重新用回 MockBean
 * 再加上 SpyBean
 * MockBean vs SpyBean 观感上的区别要比 Mock vs Spy 大
 */
@SpringBootTest
@Slf4j
public class MockitoTest3 {

    private MockMvc mvc;

    @SpyBean
    private SomeService someServiceMock;

    @Autowired
    private WebApplicationContext context;

//    @Autowired
//    private SomeController someController;

    @BeforeEach
    void doBefore() {
        mvc = MockMvcBuilders.webAppContextSetup(context).build();
//        ReflectionTestUtils.setField(someController, "someService", someServiceMock);
    }

    @Test
    public void testExample() throws Exception {
        String mockedStr = "mocked-999";

        Mockito.when(someServiceMock.getSomething(Mockito.anyInt()))
                .thenReturn(mockedStr);
//        Mockito.doReturn(mockedStr).when(someServiceMock).getSomething(Mockito.anyInt());

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


        log.warn("out put stubbing");
        Mockito.mockingDetails(someServiceMock).getStubbings().forEach(
                stubbing -> {
                    log.info("invocations -> {}", stubbing.getInvocation());
                    log.info("wasUsed -> {}", stubbing.wasUsed());
                }
        );

        log.info("SomeMapper -> {}", someServiceMock.getSomeMapper());
    }
}
