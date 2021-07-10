package com.hcl.hackathon.service;

import com.hcl.hackathon.entity.CibilScoreEntity;
import com.hcl.hackathon.exception.ResourceNotFoundException;
import com.hcl.hackathon.model.CibilScoreDetails;
import com.hcl.hackathon.repository.CibilScoreRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
/**
 * Cibil Service Test.
 */
@RunWith(MockitoJUnitRunner.class)
public class CibilServiceTest {

	@Mock
	CibilScoreRepository cibilScoreRepository;

	@InjectMocks
	CibilScoreServiceImpl cibilScoreServiceImpl;

	/**
	 * Find credit score by pan number validation test.
	 */
	@Test
	public void getCibilScoreByPanNoTest() {
		CibilScoreEntity cibilScoreDetailsEntity=new CibilScoreEntity();
		cibilScoreDetailsEntity.setCibilScore(860.0);
		cibilScoreDetailsEntity.setName("Johan");
		cibilScoreDetailsEntity.setPanCardNo("BIH9898998");
		when(cibilScoreRepository.findByPanCardNo(Mockito.anyString())).thenReturn(cibilScoreDetailsEntity);
		CibilScoreDetails cibilScoreDetails=cibilScoreServiceImpl.findCibilScoreByPanCardNumber(cibilScoreDetailsEntity.getPanCardNo());
		verify(cibilScoreRepository).findByPanCardNo(Mockito.anyString());
		assertThat(cibilScoreDetails).isNotNull();
		assertThat(cibilScoreDetails.getPanCard()).isEqualTo("BIH9898998");
		assertThat(cibilScoreDetails.getCibilScore()).isEqualTo(860.0);

	}

	/**
	 * Find credit score by no pan number validation test.
	 */
	@Test(expected = ResourceNotFoundException.class)
	public void getCibilScoreByPanNo_Not_foundTest() {
		Long userId=1l;
		when(cibilScoreRepository.findByPanCardNo(Mockito.anyString())).thenThrow(ResourceNotFoundException.class);
		CibilScoreDetails cibilScoreDetails=cibilScoreServiceImpl.findCibilScoreByPanCardNumber("BIH9898998");
		verify(cibilScoreRepository).findByPanCardNo(Mockito.anyString());
		assertThat(cibilScoreDetails).isNull();
	}

}
