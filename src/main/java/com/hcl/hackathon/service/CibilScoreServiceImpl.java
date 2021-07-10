package com.hcl.hackathon.service;

import com.hcl.hackathon.entity.CibilScoreEntity;
import com.hcl.hackathon.exception.ResourceNotFoundException;
import com.hcl.hackathon.model.CibilScoreDetails;
import com.hcl.hackathon.repository.CibilScoreRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

/**
 * The typeCibil ScoreService
 */
@Service
public class CibilScoreServiceImpl implements CibilScoreService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    CibilScoreRepository cibilScoreRepository;

    /**
     * Find the cibil score for customer.
     *
     * @param panCardNumber the pan card id
     * @return the CibilScoreDetails
     */
    @Override
    public CibilScoreDetails findCibilScoreByPanCardNumber(String panCardNumber) {


        logger.debug("Started CibilScoreServiceImpl {}", panCardNumber);

        CibilScoreEntity cibilScoreDetailsResponse = cibilScoreRepository.findByPanCardNo(panCardNumber);
        logger.debug("Response CibilScoreServiceImpl {}", cibilScoreDetailsResponse);
        if (ObjectUtils.isEmpty(cibilScoreDetailsResponse)) {
            throw new ResourceNotFoundException(HttpStatus.NOT_FOUND.value(), "No Cibil score details found for pan number");
        }

        return convertEntityToModel(cibilScoreDetailsResponse);

    }

    private CibilScoreDetails convertEntityToModel(CibilScoreEntity cibilScoreDetailsResponse) {
        CibilScoreDetails cibilScoreDetails=new CibilScoreDetails();
        cibilScoreDetails.setName(cibilScoreDetailsResponse.getName());
        cibilScoreDetails.setCibilScore(cibilScoreDetailsResponse.getCibilScore());
        cibilScoreDetails.setPanCard(cibilScoreDetailsResponse.getPanCardNo());
        return cibilScoreDetails;
    }
}

