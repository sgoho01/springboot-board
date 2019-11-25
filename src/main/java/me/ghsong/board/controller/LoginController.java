package me.ghsong.board.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.ghsong.board.repository.MemberRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author : Song.gunho
 * <p>
 * Date: 2019-11-13
 */
@Controller
@RequiredArgsConstructor
@Slf4j
public class LoginController {

    private final MemberRepository memberRepository;

    @GetMapping("/login")
    public String loginForm(){
        log.info("Login Form");
        return "login/login";
    }

    @GetMapping("/login-error")
    public String loginError(Model model){
        log.info("Login Form :: Login Error");
        model.addAttribute("loginError", true);
        return "login/login";
    }

    @GetMapping("/join")
    public String join(){
        log.info("Join Form");
        return "member/join";
    }

}
