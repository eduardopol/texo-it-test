package com.texo.test.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.texo.test.TestApplication;
import com.texo.test.model.MinMaxIntervalWinnersResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@EnableAutoConfiguration
@SpringBootTest(classes = TestApplication.class)
@WebAppConfiguration
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
public class ProducerControllerTest {

    @Autowired
    private ObjectMapper mapper;
    @Autowired
    private WebApplicationContext wac;
    private MockMvc mockMvc;

    @BeforeEach
    public void init() {
        mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }

    @Test
    public void getBiggestSmallestWinningInterval() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("/texo/producers/biggest-smallest-winning-interval")
                        .header("Content-Type", "application/json"))
                .andExpect(status().isOk())
                .andReturn();

        MinMaxIntervalWinnersResponse minMaxIntervalWinnersResponse = mapper.readValue(mvcResult.getResponse().getContentAsString(),
                MinMaxIntervalWinnersResponse.class);

        Assertions.assertEquals(1, minMaxIntervalWinnersResponse.getMin().size());
        Assertions.assertEquals(1, minMaxIntervalWinnersResponse.getMin().get(0).getInterval());
        Assertions.assertEquals("Joel Silver", minMaxIntervalWinnersResponse.getMin().get(0).getProducer());
        Assertions.assertEquals(1, minMaxIntervalWinnersResponse.getMax().size());
        Assertions.assertEquals(13, minMaxIntervalWinnersResponse.getMax().get(0).getInterval());
        Assertions.assertEquals("Matthew Vaughn", minMaxIntervalWinnersResponse.getMax().get(0).getProducer());
    }


}
