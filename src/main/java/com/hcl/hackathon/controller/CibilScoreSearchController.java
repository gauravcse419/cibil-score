package com.hcl.hackathon.controller;

import com.hcl.hackathon.exception.CibilScoreException;
import com.hcl.hackathon.model.CibilScoreDetails;
import com.hcl.hackathon.service.CibilScoreService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
@Tag(name = "Cibil Score", description = "The Cibil Score API")
public class CibilScoreSearchController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());


    @Autowired
    private CibilScoreService cibilScoreService;

    /**
     * Find credit score by pan number.
     *
     * @param panNumber the panNumber
     * @return the list
     */
    @Operation(summary = "Find the cibil scores by pan number", description = "Returns a Cibil score for customer ", tags = { "Cibil Score" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation",
                    content = @Content(schema = @Schema(implementation = CibilScoreDetails.class))),
            @ApiResponse(responseCode = "404", description = "PanNumber not found") })
    @GetMapping(value = "/cibilscore/{panNumber}", produces = { "application/json" })
    public CibilScoreDetails findCibilScoreByPanNumber(
            @Parameter(description="Id of the cibil score to be obtained. Cannot be empty.", required=true)
            @PathVariable String panNumber) {
        logger.debug("Started CibilScoreSearchController.findCibilScoreByPanNumber {} ",panNumber);
        if(StringUtils.isEmpty(panNumber)){
            throw new CibilScoreException(HttpStatus.BAD_REQUEST.value(), "Invalid Pan number");
        }
        return cibilScoreService.findCibilScoreByPanCardNumber(panNumber);
    }
}
