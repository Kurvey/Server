package com.kurvey.u_life_kurly.user.service;

import com.kurvey.u_life_kurly.user.dto.UserAnswerDto;
import com.kurvey.u_life_kurly.user.entity.SelectionSet;
import com.kurvey.u_life_kurly.user.entity.User;
import com.kurvey.u_life_kurly.user.entity.UserInfo;
import com.kurvey.u_life_kurly.user.repository.UserInfoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class UserInfoService {
    private final UserInfoRepository userInfoRepository;

    @Transactional
    public void deleteUserInfoByUser(User user){
        userInfoRepository.deleteByUser(user);
    }

    @Transactional
    public void saveUserInfo(User user, UserAnswerDto userAnswerDto, SelectionSet selectionSet){
        userInfoRepository.save(UserInfo.builder()
                .user(user)
                .numberOfFamily(userAnswerDto.getNumberOfFamily())
                .hasBaby(userAnswerDto.getHasBaby())
                .hasDog(userAnswerDto.getHasDog())
                .hasCat(userAnswerDto.getHasCat())
                .hasPlant(userAnswerDto.getHasPlant())
                .selectionSet(selectionSet)
                .build());
    }
}
