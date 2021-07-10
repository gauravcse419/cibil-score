package com.hcl.hackathon.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.Date;

@Data
public class CibilScoreDetails {
    @Schema(description = "name",
            example = "Johan", required = false)
    private String name;

    @Schema(description = "cibilScore",
            example = "850", required = false)
    private Double cibilScore;

    @Schema(description = "panCard",
            example = "BIH8789088", required = false)
    private String panCard;




}
