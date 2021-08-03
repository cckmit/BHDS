package com.bornfire.entity;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface BHDS_AMC_Reg extends JpaRepository<BHDS_AMC_Log, String> {

	@Query(value = "select * from BHDS_PRJ_PROJ_AMC", nativeQuery = true)
	 List<BHDS_AMC_Log> findAllCustom();

}
