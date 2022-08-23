package com.kurvey.u_life_kurly.user.repository;

import com.kurvey.u_life_kurly.user.entity.Similarity;
import com.kurvey.u_life_kurly.user.entity.User;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SimilarityRepository extends JpaRepository<Similarity, Long> {
    List<Similarity> findAllByUser(User user, Sort sort);
    void deleteByUser(User user);
}
