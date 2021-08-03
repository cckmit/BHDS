package com.bornfire.entity;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface BHDS_Notification_Master_Rep extends JpaRepository<BHDS_Notification_master, String> {

	Optional<BHDS_Notification_master> findById(String directorId);
	

	  @Query(value = "select * from BHDS_NOTIFICATION_MASTER  where del_flg='N'", nativeQuery =
	  true)
	 Page<BHDS_Notification_master> alertlist(Pageable page);

}
