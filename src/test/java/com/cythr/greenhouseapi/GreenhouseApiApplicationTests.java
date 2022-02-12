package com.cythr.greenhouseapi;

import com.cythr.greenhouseapi.controllers.GreenhouseController;
import com.cythr.greenhouseapi.models.Greenhouse;
import com.cythr.greenhouseapi.models.GreenhouseData;
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
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.*;

import static javax.management.timer.Timer.ONE_HOUR;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;

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

    Date date = new Date();
    GreenhouseData data1 = new GreenhouseData(1L,"1", new Date(date.getTime() - ONE_HOUR*24*10),3900F,
                                       35F,85F,2700F,25F, 50F,0F);
    GreenhouseData data2 = new GreenhouseData(2L,"1", new Date(date.getTime() - ONE_HOUR*24*2),3950F,
            25F,70F,27550F,15F, 75F,0F);
    GreenhouseData data3 = new GreenhouseData(3L,"1", new Date(date.getTime()),3800F,
            30F,75F,2800F,20F, 60F,0F);
    //BEGIN GREENHOUSE GETTERS TEST
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
    @Test
    @DisplayName("Get Greenhouse by Addr")
    public void getGreenhouseByAddr_success() throws Exception {
        Mockito.when(greenhouseRepository.findByAddr(gh2.getAddr())).thenReturn(gh2);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/greenhouse/2")
                        .contentType(MediaType.APPLICATION_JSON))
                        .andExpect(status().isOk())
                        .andExpect(jsonPath("$", notNullValue()))
                        .andExpect(jsonPath("$.cropType", is("strawberry")));
    }
    @Test
    @DisplayName("Get All Greenhouse Data")
    public void getAllDataGreenhouses_success() throws Exception{
        List<GreenhouseData> ghs = new ArrayList<>(Arrays.asList(data1,data2,data3));

        Mockito.when(greenhouseDataRepository.findAll()).thenReturn(ghs);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/greenhouse/data/")
                        .contentType(MediaType.APPLICATION_JSON))
                        .andExpect(status().isOk())
                        .andExpect(jsonPath("$", hasSize(3)))
                        .andExpect(jsonPath("$[1].luminosity", is(3950.0)));
    }
    //END GREENHOUSE GETTERS TEST

    //BEGIN GREENHOUSE POSTS TEST
    @Test
    @DisplayName("Create New Greenhouse")
    public void createGreenhouse_success() throws Exception {
        Greenhouse gh = Greenhouse.builder()
                .addr("3")
                .cropType("lettuce")
                .tLuminosity(3900F)
                .tTemperature(30F)
                .tHumidity(80F)
                .tMoisture(2600F)
                .build();

        Mockito.when(greenhouseRepository.save(gh)).thenReturn(gh);

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/greenhouse/")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(this.mapper.writeValueAsString(gh));

        mockMvc.perform(mockRequest)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$.addr", is("3")));
    }
    //END GREENHOUSE POSTS TEST

    //BEGIN GREENHOUSE PUT TEST
    @Test
    @DisplayName("Update Greenhouse Success")
    public void updateGreenhouse_success() throws Exception {
        Greenhouse gh = Greenhouse.builder()
                .id(1L)
                .addr("4")
                .cropType("lettuce")
                .tLuminosity(3900F)
                .tTemperature(30F)
                .tHumidity(80F)
                .tMoisture(2600F)
                .build();

        Mockito.when(greenhouseRepository.findById(gh1.getId())).thenReturn(Optional.of(gh1));
        Mockito.when(greenhouseRepository.save(gh)).thenReturn(gh);

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.put("/greenhouse/")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(this.mapper.writeValueAsString(gh));

        mockMvc.perform(mockRequest)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$.addr", is("4")));
    }

    @Test
    @DisplayName("Update Greenhouse Null ID")
    public void updateGreenhouse_nullID() throws Exception {
        Greenhouse gh = Greenhouse.builder()
                .addr("4")
                .cropType("lettuce")
                .tLuminosity(3900F)
                .tTemperature(30F)
                .tHumidity(80F)
                .tMoisture(2600F)
                .build();

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.put("/greenhouse/")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(this.mapper.writeValueAsString(gh));

        mockMvc.perform(mockRequest)
                .andExpect(status().isBadRequest())
                .andExpect(result ->
                        assertTrue(result.getResolvedException() instanceof GreenhouseController.InvalidRequestException))
                .andExpect(result ->
                        assertEquals("Greenhouse object or ID must not be null!", Objects.requireNonNull(result.getResolvedException()).getMessage()));
    }

    @Test
    @DisplayName("Update Greenhouse Not Found")
    public void updateGreenhouse_notFound() throws Exception {
        Greenhouse gh = Greenhouse.builder()
                .id(10L)
                .addr("4")
                .cropType("lettuce")
                .tLuminosity(3900F)
                .tTemperature(30F)
                .tHumidity(80F)
                .tMoisture(2600F)
                .build();

        Mockito.when(greenhouseRepository.findById(gh.getId())).thenReturn(null);

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.put("/greenhouse/")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(this.mapper.writeValueAsString(gh));

        mockMvc.perform(mockRequest)
                .andExpect(status().isBadRequest())
                .andExpect(result ->
                        assertTrue(result.getResolvedException() instanceof GreenhouseController.InvalidRequestException))
                .andExpect(result ->
                        assertEquals("Greenhouse Not Found!", Objects.requireNonNull(result.getResolvedException()).getMessage()));
    }
    //END GREENHOUSE PUT TEST

    //BEGIN GREENHOUSE DELETE TEST
    @Test
    @DisplayName("Delete Greenhouse Success")
    public void deleteGreenhouse_success() throws Exception {
        Mockito.when(greenhouseRepository.findByAddr(gh2.getAddr())).thenReturn(gh2);

        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/greenhouse/2")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
    @Test
    @DisplayName("Delete Greenhouse Not Found")
    public void deleteGreenhouse_notFound() throws Exception {
        Mockito.when(greenhouseRepository.findByAddr("10")).thenReturn(null);

        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/greenhouse/10")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof GreenhouseController.InvalidRequestException))
                .andExpect(result -> assertEquals("Greenhouse Not Found!",result.getResolvedException().getMessage()));
    }
    //END GREENHOUSE DELETE TEST

}
