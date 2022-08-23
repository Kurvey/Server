package com.kurvey.u_life_kurly.user.service;

import com.kurvey.u_life_kurly.user.dto.UserAnswerDto;
import com.kurvey.u_life_kurly.user.entity.*;
import com.kurvey.u_life_kurly.user.repository.*;
import com.kurvey.u_life_kurly.user.util.SelectionSetGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class LifeStyleService {
    private final LifeStyleQuestionRepository lifeStyleQuestionRepository;
    private final UserRepository userRepository;
    private final SelectionSetRepository selectionSetRepository;

    private final UserInfoService userInfoService;
    private final LifeStyleAnswerService lifeStyleAnswerService;
    private final SimilarityService similarityService;

    private final int SELECTION_COUNT = 3;

    @Transactional(readOnly = true)
    public List<LifeStyleQuestion> getLifeStyleQuestions() {
        return lifeStyleQuestionRepository.findAll();
    }


    @Transactional
    public void saveLifeStyleAnswers(Long userId, UserAnswerDto userAnswerDto) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("가입되지 않은 사용자입니다."));
        deleteUserAnswers(user);
        String selects = SelectionSetGenerator.makeSelectionsAsString(userAnswerDto.getSelects().stream().mapToLong(Long::longValue).toArray());
        SelectionSet selectionSet = selectionSetRepository.findBySelection(selects)
                .orElse(null);
        if(selectionSet == null) {
            similarityService.generateSelectionSets(SELECTION_COUNT);
            selectionSet = selectionSetRepository.findBySelection(selects)
                    .orElseThrow(() -> new RuntimeException("선택 조합이 제대로 생성되지 않았습니다."));
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
