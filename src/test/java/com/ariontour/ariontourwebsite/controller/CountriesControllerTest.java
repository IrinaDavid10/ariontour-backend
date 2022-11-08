package com.ariontour.ariontourwebsite.controller;

import com.ariontour.ariontourwebsite.business.CreateCountryUseCase;
import com.ariontour.ariontourwebsite.business.GetCountriesUseCase;
import com.ariontour.ariontourwebsite.domain.Country;
import com.ariontour.ariontourwebsite.domain.CreateCountryRequest;
import com.ariontour.ariontourwebsite.domain.CreateCountryResponse;
import com.ariontour.ariontourwebsite.domain.GetCountryResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(CountriesController.class)
class CountriesControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private GetCountriesUseCase getCountriesUseCaseMock;
    @MockBean
    private CreateCountryUseCase createCountryUseCaseMock;

    @Test
    void getCountries_shouldReturn200ResponseWithCountriesArray() throws Exception {
        GetCountryResponse response = GetCountryResponse.builder()
                .countries(List.of(
                        Country.builder().id(1L)
                                .country_name("Netherlands")
                                .country_code("NL")
                                .build(),
                        Country.builder().id(2L)
                                .country_name("Brazil")
                                .country_code("BR")
                                .build()
                ))
                .build();

        when(getCountriesUseCaseMock.getCountries())
                .thenReturn(response);
        mockMvc.perform(MockMvcRequestBuilders.get("/countries"))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(header().string("Content-Type", APPLICATION_JSON_VALUE))
                .andExpect(MockMvcResultMatchers.content().json("""
                                            {"countries":[
                                                         {
                                                             "id":1,
                                                             "country_name":"Netherlands",
                                                             "country_code":"NL"
                                                         },
                                                         {
                                                             "id":2,
                                                             "country_name":"Brazil",
                                                             "country_code":"BR"
                                                            
                                                         }]}
                                                         """));
        verify(getCountriesUseCaseMock).getCountries();
        verifyNoInteractions(createCountryUseCaseMock);
    }

      @Test
    void createCountry_shouldCreateAndReturn201_WhenRequestValid() throws Exception {
          CreateCountryRequest expectedCountry = CreateCountryRequest.builder()
                  .country_code("NL")
                  .country_name("Netherlands")
                  .build();

          when(createCountryUseCaseMock.createCountry(expectedCountry))
                  .thenReturn(CreateCountryResponse.builder()
                          .countryId(100L)
                          .build());
          mockMvc.perform(MockMvcRequestBuilders.post("/countries")
                  .contentType(APPLICATION_JSON_VALUE)
                  .content("""
                          {
                          "country_code":"NL",
                          "country_name":"Netherlands"
                          }
                          """))
                  .andDo(print())
                  .andExpect(status().isCreated())
                  .andExpect(header().string("Content-Type", APPLICATION_JSON_VALUE))
                  .andExpect(content().json("""
                                                 {"countryId": 100}
                                                       """));
                  verify(createCountryUseCaseMock).createCountry(expectedCountry);
    }

    @Test
    void createCountry_shouldNotCreateAndReturn400_WhenMissingFields() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.post("/countries")
                        .contentType(APPLICATION_JSON_VALUE)
                        .content("""
                                                        {
                                                        "code":"",
                                                        "name":""
                                                        }
                                                        """))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(header().string("Content-Type", APPLICATION_JSON_VALUE))
                .andExpect(content().json("""
                         [
                         {"field": "name", "error": "must not be blank"},
                         {"field": "code", "error": "must not be blank"},
                         {"field": "name", "error": "length must be between 2 and 50"}
                         ]
                      """));
        verifyNoInteractions(createCountryUseCaseMock);
    }
}


