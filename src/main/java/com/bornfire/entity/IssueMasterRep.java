package com.bornfire.entity;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface IssueMasterRep extends JpaRepository<BHDSIssueMaster, String> {

	@Query(value = "select * from BORNFIRE_ISSUE_LOG_REGISTER ", nativeQuery = true)
	List<BHDSIssueMaster> findAllCustom();

}