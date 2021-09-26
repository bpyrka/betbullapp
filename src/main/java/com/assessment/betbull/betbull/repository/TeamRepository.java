package com.assessment.betbull.betbull.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.assessment.betbull.betbull.entity.Team;

@Repository
public interface TeamRepository extends JpaRepository<Team, Long> {
}
