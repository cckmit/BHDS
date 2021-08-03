package com.bornfire.entity;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface Trouble_shooting_Log extends JpaRepository<Trouble_shooting_Reg, String> {

	@Query(value = "select * from TROUBLE_SHOOTING_REGISTER", nativeQuery = true)
	 List<Trouble_shooting_Reg> findAllCustom();

}