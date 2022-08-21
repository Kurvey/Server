package com.kurvey.u_life_kurly.user.service;

import com.kurvey.u_life_kurly.user.entity.LifeStyleQuestion;
import com.kurvey.u_life_kurly.user.repository.LifeStyleQuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class LifeStyleService {
    private final LifeStyleQuestionRepository lifeStyleQuestionRepository;

    @Transactional(readOnly = true)
    public List<LifeStyleQuestion> getLifeStyleQuestions() {
        return lifeStyleQuestionRepository.findAll();
    }
}
