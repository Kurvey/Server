package com.kurvey.u_life_kurly.user.repository;

import com.kurvey.u_life_kurly.user.entity.LifeStyleAnswer;
import com.kurvey.u_life_kurly.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LifeStyleAnswerRepository extends JpaRepository<LifeStyleAnswer, Long> {
    void deleteByUser(User user);
}
