package com.kurvey.u_life_kurly.user.repository;

import com.kurvey.u_life_kurly.user.entity.SelectionSet;
import com.kurvey.u_life_kurly.user.entity.User;
import com.kurvey.u_life_kurly.user.entity.UserInfo;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;
import java.util.Optional;

public interface UserInfoRepository extends JpaRepository<UserInfo, Long>, JpaSpecificationExecutor<UserInfo> {
    Optional<UserInfo> findByUser(User user);
    void deleteByUser(User user);
}
