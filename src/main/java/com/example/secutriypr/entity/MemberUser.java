package com.example.secutriypr.entity;

import com.example.secutriypr.constant.Role;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.*;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MemberUser extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long mno;

    @Column(nullable = false)
    private String name;    //이름

    @Column(unique = true, nullable = false)
    private String email;   //이메일

    @Column(nullable = false)
    @Size(min = 8, max = 20)
    private String password;    //비밀번호

    @Column(nullable = false)
    private String address; //주소

    @Enumerated(EnumType.STRING)
    private Role role;

}
