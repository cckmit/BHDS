package com.bornfire.entity;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface RefCodeRepository extends JpaRepository<RefcodeEntity, RefCodeMasterEmbeddedID>{
 

		  @Query(value = "select * from REFERENCE_CODE_TABLE where DEL_FLG='N'", nativeQuery = true)
		 Page<RefcodeEntity> refcodelist(Pageable page);
		 
		  
	/*
	 * @Modifying
	 * 
	 * @Query(value =
	 * "UPDATE REFERENCE_CODE_TABLE set DEL_FLG ='Y' where REF_CODE =?1 and REF_REC_TYPE =?2"
	 * , nativeQuery = true) String findByfgdg1(String srl_no,String rectype);
	 */
		  
	 
			@Query(value = "select  Distinct REC_TYPE,REC_DESC from REFERENCE_CODE_TABLE ", nativeQuery = true)
			
		     List<RefcodeEntity> findAllCustom();

		
			@Query(value = "select * from REFERENCE_CODE_TABLE where REF_CODE =?1 and REF_REC_TYPE =?2" , nativeQuery = true)
			Optional<RefcodeEntity> getValue(String refcode, String recordtype);

			
			
	 
	
}