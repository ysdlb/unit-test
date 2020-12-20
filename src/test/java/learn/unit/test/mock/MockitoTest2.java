package learn.unit.test.mock;

import learn.unit.test.controller.SomeController;
import learn.unit.test.service.SomeService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cglib.core.ReflectUtils;
import org.springframework.http.MediaType;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

/**
 * 与`完整的` MockitoTest类 一样
 * 不同：
 * 由于没有了 WebMvcTest 注解，MockMvc 需要手动构建
 * 使用 ReflectionTestUtils 替换 someService 属性后，可以使用 mock 过的类
 * @Mock 与 @Spy 都是在 plain class 上做代理
 * @see org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
 */
@SpringBootTest
@Slf4j
public class MockitoTest2 {

//    @Autowired
    private MockMvc mvc;

//    @Spy
    @Mock
    private SomeService someServiceMock;

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private SomeController someController;

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
    }
}
