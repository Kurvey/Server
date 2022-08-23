package com.kurvey.u_life_kurly.user.service;

import com.kurvey.u_life_kurly.error.CustomException;
import com.kurvey.u_life_kurly.user.dto.UserAnswerDto;
import com.kurvey.u_life_kurly.user.entity.LifeStyleQuestion;
import com.kurvey.u_life_kurly.user.entity.SelectionSet;
import com.kurvey.u_life_kurly.user.entity.User;
import com.kurvey.u_life_kurly.user.repository.LifeStyleQuestionRepository;
import com.kurvey.u_life_kurly.user.repository.SelectionSetRepository;
import com.kurvey.u_life_kurly.user.repository.UserRepository;
import com.kurvey.u_life_kurly.user.util.SelectionSetGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.kurvey.u_life_kurly.response.StatusCode.SELECTION_SET_GENERATION_FAULT;
import static com.kurvey.u_life_kurly.response.StatusCode.USER_NOT_FOUND;

@RequiredArgsConstructor
@Service
public class LifeStyleService {
    private final LifeStyleQuestionRepository lifeStyleQuestionRepository;
    private final UserRepository userRepository;
    private final SelectionSetRepository selectionSetRepository;

    private final UserInfoService userInfoService;
    private final LifeStyleAnswerService lifeStyleAnswerService;
    private final SimilarityService similarityService;

    private static final int SELECTION_COUNT = 3;

    @Transactional(readOnly = true)
    public List<LifeStyleQuestion> getLifeStyleQuestions() {
        return lifeStyleQuestionRepository.findAll();
    }


    @Transactional
    public void saveLifeStyleAnswers(Long userId, UserAnswerDto userAnswerDto) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new CustomException(USER_NOT_FOUND));
        deleteUserAnswers(user);
        String selects = SelectionSetGenerator.makeSelectionsAsString(userAnswerDto.getSelects().stream().mapToLong(Long::longValue).toArray());
        SelectionSet selectionSet = selectionSetRepository.findBySelection(selects)
                .orElse(null);
        if(selectionSet == null) {
            similarityService.generateSelectionSets(SELECTION_COUNT);
            selectionSet = selectionSetRepository.findBySelection(selects)
                    .orElseThrow(() -> new CustomException(SELECTION_SET_GENERATION_FAULT));
        }
        userInfoService.saveUserInfo(user, userAnswerDto, selectionSet);
        lifeStyleAnswerService.saveUserSelects(user, userAnswerDto.getSelects(), SELECTION_COUNT);
        similarityService.calculateSimilarities(user, userAnswerDto.getSelects(), SELECTION_COUNT);
    }

    private void deleteUserAnswers(User user) {
        userInfoService.deleteUserInfoByUser(user);
        lifeStyleAnswerService.deleteLifeStyleAnswerByUser(user);
        similarityService.deleteSimilarityByUser(user);
    }


}
