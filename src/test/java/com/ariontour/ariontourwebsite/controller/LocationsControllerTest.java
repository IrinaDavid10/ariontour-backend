package com.ariontour.ariontourwebsite.controller;

import com.ariontour.ariontourwebsite.business.AccessTokenDecoder;
import com.ariontour.ariontourwebsite.business.CreateCountryUseCase;
import com.ariontour.ariontourwebsite.business.CreateLocationUseCase;
import com.ariontour.ariontourwebsite.business.GetLocationsUseCase;
import com.ariontour.ariontourwebsite.domain.Country;
import com.ariontour.ariontourwebsite.domain.GetLocationsResponse;
import com.ariontour.ariontourwebsite.domain.Location;
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

import javax.validation.constraints.NotNull;
import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;

@ExtendWith(SpringExtension.class)
@WebMvcTest(LocationsController.class)
public class LocationsControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private CreateCountryUseCase createCountryUseCaseMock;
    @MockBean
    private GetLocationsUseCase getLocationsUseCaseMock;
    @MockBean
    private AccessTokenDecoder accessTokenDecoder;
    @MockBean
    private CreateLocationUseCase createLocationUseCaseMock;
    @Test
    @WithMockUser(username = "Irma", roles = {"ADMIN"})
    void getLocations_shouldReturn200ResponseWithLocationsArray() throws Exception {
        // Create a mock response for the getLocationsUseCase
        Country country = Country.builder().country_name("Netherlands").country_code("NL").build();
        Country countryBr = Country.builder().country_name("Brazil").country_code("BR").build();
        GetLocationsResponse response = GetLocationsResponse.builder()
                .locations(List.of(
                        Location.builder().id(1L)
                                .city("Amsterdam")
                                .country(country)
                                .build(),
                        Location.builder().id(2L)
                                .city("Rio de Janeiro")
                                .country(countryBr)
                                .build()
                ))
                .build();

        when(getLocationsUseCaseMock.getLocations())
                .thenReturn(response);

        mockMvc.perform(MockMvcRequestBuilders.get("/locations"))
                .andDo(print())
                // Verify that the response has an OK HTTP status
                .andExpect(MockMvcResultMatchers.status().isOk())
                // Verify that the response has a Content-Type header with the value application/json
                .andExpect(header().string("Content-Type", APPLICATION_JSON_VALUE))
                // Verify that the response body has the expected locations array
                .andExpect(MockMvcResultMatchers.content().json("""
                        {"locations":[
                                     {
                                         "id":1,
                                         "city":"Amsterdam",
                                         "country": {
                                         "country_name":"Netherlands",
                                         "country_code":"NL"
                                         }
                                     },
                                     {
                                         "id":2,
                                         "city": "Rio de Janeiro",
                                           "country": {
                                         "country_name":"Brazil",
                                         "country_code":"BR"
                                         }
                                         
                                     }]}
                                     """));
        // Verify that the getLocations method of the getLocationsUseCaseMock was called
        verify(getLocationsUseCaseMock).getLocations();
    }

}
