package com.example.secutriypr.controller;

import com.example.secutriypr.dto.MemberUserDTO;
import com.example.secutriypr.service.MemberUserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;

@Controller
@RequiredArgsConstructor
@Log4j2
@RequestMapping("/memberuser")
public class MemberUserController {

    private final MemberUserService memberUserService;

    @GetMapping("/signup")
    public String signupGet(MemberUserDTO memberUserDTO) {

        return "memberuser/signup";
    }

    @PostMapping("/signup")
    public String signupPost(@Valid MemberUserDTO memberUserDTO, BindingResult bindingResult,
                             RedirectAttributes redirectAttributes, Model model) {

        if (bindingResult.hasErrors()) {
            log.info("컨트롤러 : " + memberUserDTO);
            return "memberuser/signup";
        }

        try {

            memberUserService.saveMember(memberUserDTO);

        } catch (IllegalStateException e) {
            log.info("동일한 이메일이 있습니다.");

            model.addAttribute("msg", e.getMessage());
            return "memberuser/signup";
        }

        return "memberuser/signup";
    }

    @GetMapping("/login")
    public String login(MemberUserDTO memberUserDTO, Principal principal) {

        if (principal != null) {
            log.info("로그인한 회원 : " + principal.getName());
        }

        return "memberuser/login";

    }

}
