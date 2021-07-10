package com.hcl.hackathon.repository;

import com.hcl.hackathon.entity.CibilScoreEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CibilScoreRepository extends JpaRepository<CibilScoreEntity,Long> {


    CibilScoreEntity findByPanCardNo(String panNumber);
}
