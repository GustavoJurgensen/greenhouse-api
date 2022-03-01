package com.cythr.greenhouseapi;

import com.cythr.greenhouseapi.controllers.GreenhouseDataController;
import com.cythr.greenhouseapi.models.GreenhouseData;
import com.cythr.greenhouseapi.models.ParseGreenhouseData;
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

import java.sql.Timestamp;
import java.util.*;

import static javax.management.timer.Timer.ONE_HOUR;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.*;

/**
 * Test all requests to Greenhouse Data Table
 */
@WebMvcTest(GreenhouseDataController.class)
@DisplayName("Greenhouse Data Table Tests")
class GreenhouseDataTests {

    @Autowired
    MockMvc mockMvc;
    @Autowired
    ObjectMapper mapper;

    @MockBean
    GreenhouseDataRepository greenhouseDataRepository;

    Date date = new Date();
    GreenhouseData data1 = new GreenhouseData(1L,"1", new Date(date.getTime() - ONE_HOUR*24*10),3900F,
            35F,85F,2700F,25F, 50F,0F);
    GreenhouseData data2 = new GreenhouseData(2L,"1", new Date(date.getTime() - ONE_HOUR*24*2),3950F,
            25F,70F,2755F,15F, 75F,0F);
    GreenhouseData data3 = new GreenhouseData(3L,"1", new Date(date.getTime()),3800F,
            30F,75F,2800F,20F, 60F,0F);

    // --------- Begin GET Methods ---------
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
    @Test
    @DisplayName("Get Greenhouse Humidity Data by Specific Date(One Day)")
    public void getHumidityDataGreenhouses_success() throws Exception{
        List<ParseGreenhouseData> listGh = new ArrayList<>(Collections.singletonList(
                new ParseGreenhouseData(new Date(), data3.getHumidity(), data3.getExHumidity())));

        Mockito.when(greenhouseDataRepository.findHumidityByDate(Mockito.any(Timestamp.class),Mockito.eq("1"))).thenReturn(listGh);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/greenhouse/data/humidity/addr/1/date/oneDay")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].indoor", is(75.0)));
    }
    @Test
    @DisplayName("Get Greenhouse Moisture Data by Specific Date(Five Days)")
    public void getMoistureDataGreenhouses_success() throws Exception{
        List<ParseGreenhouseData> listGh = new ArrayList<>(Arrays.asList(
                new ParseGreenhouseData(new Date(), data2.getMoisture()),
                new ParseGreenhouseData(new Date(), data3.getMoisture())));

        Mockito.when(greenhouseDataRepository.findMoistureByDate(Mockito.any(Timestamp.class),Mockito.eq("1"))).thenReturn(listGh);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/greenhouse/data/moisture/addr/1/date/fiveDays")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].indoor", is(2755.0)));
    }
    @Test
    @DisplayName("Get Greenhouse Temperature Data by Specific Date(One Month)")
    public void getTemperatureDataGreenhouses_success() throws Exception{
        List<ParseGreenhouseData> listGh = new ArrayList<>(Arrays.asList(
                new ParseGreenhouseData(new Date(), data1.getTemperature(), data1.getExTemperature()),
                new ParseGreenhouseData(new Date(), data2.getTemperature(), data2.getExTemperature()),
                new ParseGreenhouseData(new Date(), data3.getTemperature(), data3.getExTemperature())));

        Mockito.when(greenhouseDataRepository.findTemperatureByDate(Mockito.any(Timestamp.class),Mockito.eq("1"))).thenReturn(listGh);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/greenhouse/data/temperature/addr/1/date/thirtyDays")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(3)))
                .andExpect(jsonPath("$[0].indoor", is(35.0)));
    }
    @Test
    @DisplayName("Get Greenhouse Luminosity Data by Specific Date(Six Month)")
    public void getLuminosityDataGreenhouses_success() throws Exception{
        List<ParseGreenhouseData> listGh = new ArrayList<>(Arrays.asList(
                new ParseGreenhouseData(new Date(), data1.getLuminosity()),
                new ParseGreenhouseData(new Date(), data2.getLuminosity()),
                new ParseGreenhouseData(new Date(), data3.getLuminosity())));

        Mockito.when(greenhouseDataRepository.findLuminosityByDate(Mockito.any(Timestamp.class),Mockito.eq("1"))).thenReturn(listGh);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/greenhouse/data/luminosity/addr/1/date/sixMonths")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(3)))
                .andExpect(jsonPath("$[0].indoor", is(3900.0)));
    }
    @Test
    @DisplayName("Get Last Data By Addr")
    public void getLastDataByAddr() throws Exception{

        Mockito.when(greenhouseDataRepository.findLastByAddr("1")).thenReturn(data3);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/greenhouse/data/last/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].luminosity", is(3800.0)));
    }

    // --------- End GET Methods ---------


    // --------- Begin POST Methods ---------
    @Test
    @DisplayName("Create New Greenhouse Data")
    public void createGreenhouseData_success() throws Exception {
        GreenhouseData gh = GreenhouseData.builder()
                .addr("3")
                .exHumidity(10F)
                .exTemperature(10F)
                .exWind(10F)
                .humidity(10F)
                .temperature(10F)
                .luminosity(10F)
                .moisture(10F)
                .build();

        Mockito.when(greenhouseDataRepository.save(gh)).thenReturn(gh);

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/greenhouse/data/")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(this.mapper.writeValueAsString(gh));

        mockMvc.perform(mockRequest)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$.addr", is("3")));
    }
    // --------- End POST Methods ---------
}
