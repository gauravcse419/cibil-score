package com.hcl.hackathon.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hcl.hackathon.exception.CibilScoreException;
import com.hcl.hackathon.exception.ResourceNotFoundException;
import com.hcl.hackathon.model.CibilScoreDetails;
import com.hcl.hackathon.service.CibilScoreService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
/**
 * The type Cibil Score Search Controller
 */
@RunWith(SpringRunner.class)
@WebMvcTest(CibilScoreSearchController.class)
public class CibilScoreSearchControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    CibilScoreService cibilScoreService;

    /**
     * Find credit score by pan number and validate request.
     */
    @Test
    public void getCibilScoreByPanNumber() throws Exception {

        CibilScoreDetails cibilScoreDetails=new CibilScoreDetails();
        cibilScoreDetails.setCibilScore(890.0);
        cibilScoreDetails.setName("johan");
        cibilScoreDetails.setPanCard("ABG76777776");
        when(cibilScoreService.findCibilScoreByPanCardNumber(Mockito.anyString())).thenReturn(cibilScoreDetails);
        mvc.perform(MockMvcRequestBuilders.get("/api/v1/cibilscore/ABG76777776").accept(MediaType.APPLICATION_JSON))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("johan"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.cibilScore").value(890.0))
               .andExpect(MockMvcResultMatchers.jsonPath("$.panCard").value("ABG76777776"));
        verify(cibilScoreService).findCibilScoreByPanCardNumber(Mockito.anyString());
    }
    /**
     * Find credit score by pan number with no pan pardcard request.
     */
    @Test
    public void getCibilScoreByPanNumber_NotFound() throws Exception {
        when(cibilScoreService.findCibilScoreByPanCardNumber(Mockito.anyString()))
                .thenThrow(ResourceNotFoundException.class);

        mvc.perform(MockMvcRequestBuilders.get("/api/v1/cibilscore/ABG76777776"))
                .andExpect(status().isNotFound());
        verify(cibilScoreService).findCibilScoreByPanCardNumber(Mockito.anyString());

    }
    /**
     * Find credit score by pan number Bad request.
     */
    @Test
    public void getCibilScoreByPanNumber_BadRequest() throws Exception {
        when(cibilScoreService.findCibilScoreByPanCardNumber(Mockito.anyString()))
                .thenThrow(CibilScoreException.class);

        mvc.perform(MockMvcRequestBuilders.get("/api/v1/cibilscore/null"))
                .andExpect(status().isBadRequest());
        verify(cibilScoreService).findCibilScoreByPanCardNumber(Mockito.anyString());

    }

    /**
     * Find credit score by pan number with Internal server.
     */
    @Test
    public void getOrdersByUserId_InternalServer() throws Exception {
        when(cibilScoreService.findCibilScoreByPanCardNumber(Mockito.anyString()))
                .thenThrow(RuntimeException.class);

        mvc.perform(MockMvcRequestBuilders.get("/api/v1/cibilscore/ABG76777776"))
                .andExpect(status().isInternalServerError());
        verify(cibilScoreService).findCibilScoreByPanCardNumber(Mockito.anyString());

    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
