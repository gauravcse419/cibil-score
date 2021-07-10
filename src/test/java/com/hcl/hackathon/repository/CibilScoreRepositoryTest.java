package com.hcl.hackathon.repository;

import com.hcl.hackathon.entity.CibilScoreEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;
/**
 * Cibil ScoreRepository Test
 */
@RunWith(SpringRunner.class)
@DataJpaTest
public class CibilScoreRepositoryTest {
	
	@Autowired
	private TestEntityManager entityManager;

	@Autowired
	CibilScoreRepository repository;
	/**
	 * Should all find cibil score details in db
	 */
	@Test
	public void should_find_no_cibil_score_if_repository_is_empty() {
		Iterable<CibilScoreEntity> cibilScoreDetailsEntity = repository.findAll();

		assertThat(cibilScoreDetailsEntity).isEmpty();
	}

	/**
	 * Should store cibil score details in db
	 */
	@Test
	public void should_store_a_cibil_score() {
		CibilScoreEntity cibilScoreDetailsEntity=new CibilScoreEntity();
		cibilScoreDetailsEntity.setCibilScore(860.0);
		cibilScoreDetailsEntity.setName("Johan");
		cibilScoreDetailsEntity.setPanCardNo("BIH9898998");

		CibilScoreEntity cibilScoreDetailResposne = repository.save(cibilScoreDetailsEntity);

		assertThat(cibilScoreDetailResposne).hasFieldOrPropertyWithValue("cibilScore", 860.0);
		assertThat(cibilScoreDetailResposne).hasFieldOrPropertyWithValue("name", "Johan");
		assertThat(cibilScoreDetailResposne).hasFieldOrPropertyWithValue("panCardNo", "BIH9898998");
	}
	/**
	 * Should find store cibil score details by pan number in db
	 */
	@Test
	public void should_find_cibil_score_by_panCardNo() {
		CibilScoreEntity cibilScoreDetailsEntity=new CibilScoreEntity();
		cibilScoreDetailsEntity.setCibilScore(860.0);
		cibilScoreDetailsEntity.setName("Johan");
		cibilScoreDetailsEntity.setPanCardNo("BIH9898998");
		entityManager.persist(cibilScoreDetailsEntity);


		CibilScoreEntity cibilScoreDetails=entityManager.persist(cibilScoreDetailsEntity);

		CibilScoreEntity foundCibilScore = repository.findByPanCardNo(cibilScoreDetails.getPanCardNo());

		assertThat(foundCibilScore.getPanCardNo()).isEqualTo(cibilScoreDetailsEntity.getPanCardNo());
	}
	


}
