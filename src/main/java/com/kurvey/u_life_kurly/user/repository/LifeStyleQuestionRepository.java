package com.kurvey.u_life_kurly.user.repository;

import com.kurvey.u_life_kurly.user.entity.LifeStyleQuestion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;

public interface LifeStyleQuestionRepository extends JpaRepository<LifeStyleQuestion, Long> {
    List<LifeStyleQuestion> findAllByIdIn(Collection<Long> ids);
}
