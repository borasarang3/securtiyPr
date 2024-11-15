package com.example.secutriypr.dto;

import com.example.secutriypr.constant.Role;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MemberUserDTO {

    private Long mno;

    @NotBlank
    @Size(min = 2, max = 10)
    private String name;    //이름

    @NotBlank
    @Size(max = 50)
    @Email
    private String email;   //이메일

    @NotBlank
    @Size(min = 8, max = 20)
    private String password;    //비밀번호

    @NotBlank
    private String address; //주소

    private Role role;

    private LocalDateTime regiDate;

    private LocalDateTime updateDate;

}
