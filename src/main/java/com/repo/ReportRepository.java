package com.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.entity.CitizenPlan;

public interface ReportRepository extends JpaRepository<CitizenPlan, Integer>{

	 	@Query("Select distinct(c.planName) from CitizenPlan c ")
	    List<String> getPlanNames();

	    @Query("Select distinct(c.planStatus) from CitizenPlan c ")
	    List<String> getPlanStatus();
}
