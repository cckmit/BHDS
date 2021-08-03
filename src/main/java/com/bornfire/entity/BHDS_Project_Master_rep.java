package com.bornfire.entity;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface BHDS_Project_Master_rep extends JpaRepository<BHDS_Project_Master_Log, String> {

	@Query(value = "select * from BHDS_PROJ_MASTER where delete_flag='N'", nativeQuery = true)
	 List<BHDS_Project_Master_Log> findAllCustom();
	 
	 @Query(value = "select * from BHDS_PROJ_MASTER where srl_no=?1", nativeQuery = true)
	 BHDS_Project_Master_Log findBySrl(String srl_no);

}