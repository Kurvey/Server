package com.kurvey.u_life_kurly.user.service;

import com.kurvey.u_life_kurly.config.jwt.JwtTokenProvider;
import com.kurvey.u_life_kurly.error.CustomException;
import com.kurvey.u_life_kurly.user.dto.SignInDto;
import com.kurvey.u_life_kurly.user.dto.UserForm;
import com.kurvey.u_life_kurly.user.entity.User;
import com.kurvey.u_life_kurly.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.kurvey.u_life_kurly.response.StatusCode.*;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final BCryptPasswordEncoder encoder;

    @Transactional
    public Long creatUser(UserForm form){
        if(userRepository.findByUserId(form.getUserId()).isPresent())
            throw new CustomException(DUPLICATED_ID);
        User user = form.toEntity();
        user.setPassword(encoder.encode(user.getPassword()));
        User saved = userRepository.save(user);
        return saved.getId();
    }


    public String authorize(SignInDto signInDto) {
        User user = userRepository.findByUserId(signInDto.getUserId())
                .orElseThrow(() -> new CustomException(USER_NOT_FOUND));
        if(encoder.matches(signInDto.getPassword(), user.getPassword()))
            return jwtTokenProvider.generateToken(signInDto.getUserId());
        throw new CustomException(INCORRECT_PASSWORD);
    }
}


