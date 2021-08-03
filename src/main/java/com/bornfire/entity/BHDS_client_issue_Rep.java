package com.bornfire.entity;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface BHDS_client_issue_Rep extends JpaRepository<BHDS_Client_Issue_Log, String> {
	
	Optional<BHDS_Client_Issue_Log> findById(String directorId);
	

	@Query(value = "select * from BORNFIRE_CLIENT_ISSUELOG  order by report_date desc", nativeQuery = true)
	List<BHDS_Client_Issue_Log> findAllCustom();

	@Query(value = "select * from BORNFIRE_CLIENT_ISSUELOG where issue_srl_no=?1", nativeQuery = true)
    BHDS_Client_Issue_Log findBySrl(String Issue_srl_no);
	
	//getNO
	@Query(value = "select reviews from BORNFIRE_CLIENT_ISSUELOG where issue_srl_no=?1", nativeQuery = true)
    String getNO(String Issue_srl_no);
	
	@Query(value = "select count(*) from BORNFIRE_CLIENT_ISSUELOG where subject=?1 and lodged_date = ?2", nativeQuery = true)
	BigDecimal getcount(String Issue_srl_no,Date lod_date);
	

}