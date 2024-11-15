package com.example.secutriypr.repository;

import com.example.secutriypr.entity.MemberUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberUserRepository extends JpaRepository<MemberUser, Long> {

    public MemberUser findByEmail(String email);


}
