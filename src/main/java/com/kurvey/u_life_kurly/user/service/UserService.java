package com.kurvey.u_life_kurly.user.service;

import com.kurvey.u_life_kurly.config.jwt.JwtTokenProvider;
import com.kurvey.u_life_kurly.user.Repository.UserRepository;
import com.kurvey.u_life_kurly.user.dto.SignInDto;
import com.kurvey.u_life_kurly.user.dto.UserForm;
import com.kurvey.u_life_kurly.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final BCryptPasswordEncoder encoder;

    @Transactional
    public void creatUser(UserForm form){
        if(userRepository.findByUserId(form.getUserId()).isPresent())
            throw new RuntimeException("중복된 Id입니다.");
        User user = form.toEntity();
        user.setPassword(encoder.encode(user.getPassword()));
        userRepository.save(user);
    }


    public String authorize(SignInDto signInDto) {
        User user = userRepository.findByUserId(signInDto.getUserId())
                .orElseThrow(() -> new RuntimeException("가입되지 않은 Id 입니다."));
        if(encoder.matches(signInDto.getPassword(), user.getPassword()))
            return jwtTokenProvider.generateToken(signInDto.getUserId());
        throw new RuntimeException("로그인에 실패하였습니다.");
    }
}


