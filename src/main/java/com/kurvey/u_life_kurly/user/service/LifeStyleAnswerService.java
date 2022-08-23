package com.kurvey.u_life_kurly.user.service;

import com.kurvey.u_life_kurly.error.CustomException;
import com.kurvey.u_life_kurly.user.entity.LifeStyleAnswer;
import com.kurvey.u_life_kurly.user.entity.LifeStyleQuestion;
import com.kurvey.u_life_kurly.user.entity.User;
import com.kurvey.u_life_kurly.user.repository.LifeStyleAnswerRepository;
import com.kurvey.u_life_kurly.user.repository.LifeStyleQuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static com.kurvey.u_life_kurly.response.StatusCode.INVALID_INPUT;
import static com.kurvey.u_life_kurly.response.StatusCode.QUESTION_NOT_FOUND;

@RequiredArgsConstructor
@Service
public class LifeStyleAnswerService {
    private final LifeStyleAnswerRepository lifeStyleAnswerRepository;
    private final LifeStyleQuestionRepository lifeStyleQuestionRepository;

    @Transactional
    public void deleteLifeStyleAnswerByUser(User user){
        lifeStyleAnswerRepository.deleteByUser(user);
    }
    
    @Transactional
    public void saveUserSelects(User user, List<Long> selects, final int SELECTION_COUNT){
        if(selects.size() != SELECTION_COUNT) throw new CustomException(INVALID_INPUT);
        List<LifeStyleAnswer> lifeStyleAnswers = new ArrayList<>();
        List<LifeStyleQuestion> questions = lifeStyleQuestionRepository.findAllByIdIn(selects);
        if(selects.size() != questions.size()) throw new CustomException(QUESTION_NOT_FOUND);
        for(LifeStyleQuestion question : questions){
            lifeStyleAnswers.add(LifeStyleAnswer.builder()
                    .user(user)
                    .lifeStyleQuestion(question)
                    .priority(SELECTION_COUNT - selects.indexOf(question.getId()))
                    .build());
        }
        lifeStyleAnswerRepository.saveAll(lifeStyleAnswers);
    }
}
