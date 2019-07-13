package com.wipro.date.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;



@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class DateRangeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void shouldReturnDefaultMessage() throws Exception {
        this.mockMvc.perform(get("/")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("form")));
    }

    @Test
    public void shouldReturnSuccess() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/api/calculate").content("{\"startDate\":\"2019-07-13\",\"endDate\":\"2019-07-20\"}")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("{\"message\":\"There are 7 days between 13/07/2019 and 20/07/2019.\"}")));
    }

    @Test
    public void shouldReturnError() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/api/calculate").content("{\"endDate\":\"2019-07-13\",\"startDate\":\"2019-07-20\"}")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isBadRequest())
                .andExpect(content().string(equalTo("{\"message\":\"End Date must be after Start Date\"}")));
    }

    @Test
    public void shouldReturnValidationErrorEndDate() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/api/calculate").content("{\"endDate\":\"\",\"startDate\":\"2019-07-20\"}")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isBadRequest())
                .andExpect(content().string(equalTo("{\"message\":\"Please provide End Date\"}")));
    }

    @Test
    public void shouldReturnValidationErrorStartDate() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/api/calculate").content("{\"StartDate\":\"\",\"endDate\":\"2019-07-20\"}")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isBadRequest())
                .andExpect(content().string(equalTo("{\"message\":\"Please provide Start Date\"}")));
    }

}
