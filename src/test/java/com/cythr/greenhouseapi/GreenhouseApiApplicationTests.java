package com.cythr.greenhouseapi;

import com.cythr.greenhouseapi.controllers.GreenhouseController;
import com.cythr.greenhouseapi.models.Greenhouse;
import com.cythr.greenhouseapi.repositories.GreenhouseDataRepository;
import com.cythr.greenhouseapi.repositories.GreenhouseRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.*;

@WebMvcTest(GreenhouseController.class)
@DisplayName("Greenhouse Api Tests")
class GreenhouseApiApplicationTests {

    @Autowired
    MockMvc mockMvc;
    @Autowired
    ObjectMapper mapper;

    @MockBean
    GreenhouseRepository greenhouseRepository;
    @MockBean
    GreenhouseDataRepository greenhouseDataRepository;

    Greenhouse gh1 = new Greenhouse(1L,"tomato","1",3900F,35F,90F,2500F);
    Greenhouse gh2 = new Greenhouse(2L,"strawberry","2",3950F,33F,85F,2700F);

    @Test
    @DisplayName("Get All Greenhouse")
    public void getAllGreenhouses_success() throws Exception{
        List<Greenhouse> ghs = new ArrayList<>(Arrays.asList(gh1,gh2));

        Mockito.when(greenhouseRepository.findAll()).thenReturn(ghs);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/greenhouse/")
                        .contentType(MediaType.APPLICATION_JSON))
                        .andExpect(status().isOk())
                        .andExpect(jsonPath("$", hasSize(2)))
                        .andExpect(jsonPath("$[1].cropType", is("strawberry")));
    }

}
