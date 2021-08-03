package com.bornfire.entity;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
@Repository
public interface RecordTypeRepository extends JpaRepository<RecordTypeEntity, String>{
	/*
	 * 
	 * @Query(value = "select * from REFERENCE_CODE_TABLE where DEL_FLG='N' ",
	 * nativeQuery = true) Page<RefcodeEntity> refcodelist(Pageable page);
	 * 
	 * 
	 * @Modifying
	 * 
	 * @Query(value =
	 * "UPDATE REFERENCE_CODE_TABLE set DEL_FLG ='Y' where REF_CODE =?1",
	 * nativeQuery = true) String findByfgdg1(String srl_no);
	 */
	 
			@Query(value = "select * from RECORD_TYPE_TABLE ", nativeQuery = true)
		     List<RecordTypeEntity> findAllCustom();

			
			
	 
	
}