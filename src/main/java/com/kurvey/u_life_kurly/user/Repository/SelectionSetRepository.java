package com.kurvey.u_life_kurly.user.repository;

import com.kurvey.u_life_kurly.user.entity.SelectionSet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SelectionSetRepository extends JpaRepository<SelectionSet, Long> {
    Optional<SelectionSet> findBySelection(String selection);
}
