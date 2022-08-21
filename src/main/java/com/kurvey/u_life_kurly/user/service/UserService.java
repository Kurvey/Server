package com.kurvey.u_life_kurly.user.service;

import com.kurvey.u_life_kurly.user.Repository.UserRepository;
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
    private final BCryptPasswordEncoder encoder;

    @Transactional
    public Long creatUser(UserForm form){
        if(userRepository.existsByUserId(form.getUserId()))
            throw new RuntimeException("중복된 Id입니다.");
        User user = form.toEntity();
        user.setPassword(encoder.encode(user.getPassword()));
        userRepository.save(user);
        return user.getId();
    }

}


