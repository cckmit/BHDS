package com.bornfire.entity;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface AlertManagementRepository extends JpaRepository<AlertManagementEntity, String>{
	
	Optional<AlertManagementEntity> findById(String directorId);
	
	  @Query(value = "select * from AMT  where del_flg='N'", nativeQuery =
	  true)
	 Page<AlertManagementEntity> alertlist(Pageable page);
	  
	  
	 @Modifying
	@Query(value = "UPDATE AMT u set u.DEL_FLG ='Y' where u.SRL_NO =?1", nativeQuery = true)
	int findByfgdg1(String srl_no);
	  
	

}
