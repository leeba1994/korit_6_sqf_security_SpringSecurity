package com.study.SpringSecurity.service;

import com.study.SpringSecurity.aspect.annotation.TimeAop;
import com.study.SpringSecurity.domain.entity.Role;
import com.study.SpringSecurity.domain.entity.User;
import com.study.SpringSecurity.dto.request.ReqSignupDto;
import com.study.SpringSecurity.repository.RoleRepository;
import com.study.SpringSecurity.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.Set;

@Service
@RequiredArgsConstructor
public class SignupService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @TimeAop
    @Transactional(rollbackFor = Exception.class)
    public User signup(ReqSignupDto dto) {
//        userRepository.findByUsername(dto.getUsername());
        User user =  dto.toEntity(passwordEncoder);
        Role role = roleRepository.findByName("ROLE_USER").orElseGet(
                () -> roleRepository.save(Role.builder().name("ROLE_USER").build())
        );

        user.setRoles(Set.of(role));
        user = userRepository.save(user);

//        UserRole userRole = UserRole.builder()
//                .user(user)
//                .role(role)
//                .build();
//
//        userRole = userRoleRepository.save(userRole);

        return user;
    }

    public boolean isDuplicatedUsername(String username) {
        return userRepository.findByUsername(username).isPresent();
        //isPresent() 값이 널인지 체크

    }
}
