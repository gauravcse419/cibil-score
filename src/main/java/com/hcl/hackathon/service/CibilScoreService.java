package com.hcl.hackathon.service;

import com.hcl.hackathon.model.CibilScoreDetails;
/**
 * The interface Cibil ScoreService.
 */
public interface CibilScoreService {
    /**
     * Find the cibil score for customer.
     *
     * @param panNumber the pan card id
     * @return the list
     */
    CibilScoreDetails findCibilScoreByPanCardNumber(String panNumber);
}
