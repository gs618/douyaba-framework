package com.github.douyaba.starter.web.protocol;

import com.github.douyaba.web.protocol.model.bo.Payload;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {Application.class})
@Slf4j
public class ApplicationTest {

    @Autowired
    private WebApplicationContext wac;

    private MockMvc mvc;

    @Before
    public void setup(){
        //让每个测试用例启动之前都构建这样一个启动项
        mvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }


    @Test
    public void testGet() throws Exception {
        Payload<String> result = Payload.newInstance();
        result.setCode(200).setData("get").setSuccess(true);
        log.info("Expecting data: {}",result.toString());
        RequestBuilder request = get("/get1");
        ResultActions perform = mvc.perform(request);
        log.info("Actual data: {}", perform.andReturn().getResponse().getContentAsString());
        perform.andExpect(status().isOk())
                .andExpect(content().json(result.toString()));
    }

    @Test
    public void testGetVoid() throws Exception {
        Payload<String> result = Payload.newInstance();
        result.setCode(200).setSuccess(true);
        log.info("Expecting data: {}",result.toString());
        RequestBuilder request = get("/get2");
        ResultActions perform = mvc.perform(request);
        log.info("Actual data: {}", perform.andReturn().getResponse().getContentAsString());
        perform.andExpect(status().isOk())
                .andExpect(content().json(result.toString()));
    }

    @Test
    public void testPostVoid() throws Exception {
        Payload<String> result = Payload.newInstance();
        result.setCode(200).setSuccess(true);
        log.info("Expecting data: {}",result.toString());
        RequestBuilder request = post("/post");
        ResultActions perform = mvc.perform(request);
        log.info("Actual data: {}", perform.andReturn().getResponse().getContentAsString());
        perform.andExpect(status().isOk())
                .andExpect(content().json(result.toString()));
    }

    @Test
    public void testDeleteVoid() throws Exception {
        Payload<String> result = Payload.newInstance();
        result.setCode(200).setSuccess(true);
        log.info("Expecting data: {}",result.toString());
        RequestBuilder request = delete("/delete");
        ResultActions perform = mvc.perform(request);
        log.info("Actual data: {}", perform.andReturn().getResponse().getContentAsString());
        perform.andExpect(status().isOk())
                .andExpect(content().json(result.toString()));
    }

    @Test
    public void testPutVoid() throws Exception {
        Payload<String> result = Payload.newInstance();
        result.setCode(200).setSuccess(true);
        log.info("Expecting data: {}",result.toString());
        RequestBuilder request = put("/put");
        ResultActions perform = mvc.perform(request);
        log.info("Actual data: {}", perform.andReturn().getResponse().getContentAsString());
        perform.andExpect(status().isOk())
                .andExpect(content().json(result.toString()));
    }

}
