package com.example.secutriypr.service;

import com.example.secutriypr.constant.Role;
import com.example.secutriypr.dto.MemberUserDTO;
import com.example.secutriypr.entity.MemberUser;
import com.example.secutriypr.repository.MemberUserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Log4j2
@RequiredArgsConstructor
@Transactional
public class MemberUserService implements UserDetailsService {

    private final MemberUserRepository memberUserRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;

    //회원가입
    public MemberUserDTO saveMember(MemberUserDTO memberUserDTO) {

        //사용자가 이미 있는지 확인
        MemberUser memberUser =
                memberUserRepository.findByEmail(memberUserDTO.getEmail());

        if (memberUser != null) {

            throw new IllegalStateException("이미 가입된 회원입니다.");
        }

        memberUser =
                modelMapper.map(memberUserDTO, MemberUser.class);

        memberUser.setRole(Role.ADMIN);
        memberUser.setPassword(passwordEncoder.encode(memberUserDTO.getPassword()));

        memberUser =
                memberUserRepository.save(memberUser);

        return modelMapper.map(memberUser, MemberUserDTO.class);
    }


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        log.info("서비스 이메일 : " + email);

        MemberUser memberUser =
                this.memberUserRepository.findByEmail(email);

        if (memberUser == null) {
            throw new UsernameNotFoundException("사용자를 찾을 수 없습니다.");
        }

        log.info("찾은 회원 정보 : " + memberUser);

        String role = "";

        if ("ADMIN".equals(memberUser.getRole().name())) {
            log.info("관리자");
            role = Role.ADMIN.name();
        } else if (Role.USER.name().equals(memberUser.getRole().name())){
            log.info("일반 회원");
            role = Role.USER.name();
        }

        return User.builder()
                .username(memberUser.getEmail())
                .password(memberUser.getPassword())
                .roles(role)
                .build();
    }
}
