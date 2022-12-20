package com.ariontour.ariontourwebsite.controller;

import com.ariontour.ariontourwebsite.business.AccessTokenDecoder;
import com.ariontour.ariontourwebsite.business.CreateCityUseCase;
import com.ariontour.ariontourwebsite.business.GetCitiesUseCase;
import com.ariontour.ariontourwebsite.domain.City;
import com.ariontour.ariontourwebsite.domain.CreateCityRequest;
import com.ariontour.ariontourwebsite.domain.CreateCityResponse;
import com.ariontour.ariontourwebsite.domain.GetCitiesResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(CityController.class)
public class CityControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private CreateCityUseCase createCityUseCaseMock;
    @MockBean
    private GetCitiesUseCase getCitiesUseCaseMock;
    @MockBean
    private AccessTokenDecoder accessTokenDecoder;

    @Test
    @WithMockUser(username = "Irma", roles = {"ADMIN"})
    void createCity_shouldCreateAndReturn201_WhenRequestValid() throws Exception {
        CreateCityRequest expectedCity = CreateCityRequest.builder()
                .city_name("Amsterdam")
                .country_code("NL")
                .build();

        when(createCityUseCaseMock.createCity(expectedCity))
                .thenReturn(CreateCityResponse.builder()
                        .cityId(100L)
                        .build());
        mockMvc.perform(MockMvcRequestBuilders.post("/cities")
                        .contentType(APPLICATION_JSON_VALUE)
                        .with(csrf())
                        .content("""
                                    {
                                        "city_name":"Amsterdam",
                                        "country_code":"NL"
                                    }
                                """))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(header().string("Content-Type", APPLICATION_JSON_VALUE))
                .andExpect(content().json("""
                            {"cityId": 100}
                        """));

        verify(createCityUseCaseMock).createCity(expectedCity);
    }

    /*
    @Test
    @WithMockUser(username = "Irmassss", roles = {"ADMIN"})
    void createCountry_shouldNotCreateAndReturn400_WhenMissingFields() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.post("/countries")
                        .contentType(APPLICATION_JSON_VALUE)
                        .with(csrf())
                        .content("""
                                                        {
                                                        "country_code":"",
                                                        "country_name":""
                                                        }
                                                        """))

                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(header().string("Content-Type", APPLICATION_JSON_VALUE))
                .andExpect(content().json("""
                         [
                         {"field": "country_name", "error": "must not be blank"},
                         {"field": "country_code", "error": "length must be between 2 and 2"},
                         {"field": "country_code", "error": "must not be blank"},
                         {"field": "country_name", "error": "length must be between 2 and 50"}
                         ]
                      """));

        verifyNoInteractions(createCountryUseCaseMock);
    }
     */

    @Test
    @WithMockUser(username = "Irmassss", roles = {"ADMIN"})
    void createCity_shouldNotCreateAndReturn400_WhenMissingFields() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.post("/cities")
                        .contentType(APPLICATION_JSON_VALUE)
                        .with(csrf())
                        .content("""
                                
                                   {
                                        "city_name":"",
                                        "country_code":""
                                   }      
                                                          
                                """
                        ))
                .andDo(print());
    }

    @Test
    @WithMockUser(username = "Irmassss", roles = {"ADMIN"})
    void getCities_shouldReturn200ResponseWithCitiesArray() throws Exception {
        GetCitiesResponse response = GetCitiesResponse.builder()
                .cities(List.of(
                        City.builder().id(1L)
                                .city_name("Amsterdam")
                                .country_code("NL")
                                .build(),
                        City.builder().id(2L)
                                .city_name("Rio de Janeiro")
                                .country_code("BR")
                                .build()
                ))
                .build();

        when(getCitiesUseCaseMock.getCities())
                .thenReturn(response);

        mockMvc.perform(MockMvcRequestBuilders.get("/cities"))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(header().string("Content-Type", APPLICATION_JSON_VALUE))
                .andExpect(MockMvcResultMatchers.content().json("""
                        {"cities":[
                                     {
                                         "id":1,
                                         "city_name":"Amsterdam",
                                         "country_code":"NL"
                                     },
                                     {
                                         "id":2,
                                         "city_name":"Rio de Janeiro",
                                         "country_code":"BR"
                                        
                                     }
                                     ]
                        }
                                     """));
        verify(getCitiesUseCaseMock).getCities();
        verifyNoInteractions(createCityUseCaseMock);
    }


}
