package me.ghsong.board.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.ghsong.board.Util.ResultUtil;
import me.ghsong.board.common.ResultResponse;
import me.ghsong.board.entity.Member;
import me.ghsong.board.repository.MemberRepository;
import me.ghsong.board.service.MemberService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

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
    private final MemberService memberService;

    @GetMapping("/login")
    public String loginForm(){
        log.info("::: loginForm :::");
        return "login/login";
    }

    @GetMapping("/login-error")
    public String loginError(Model model){
        log.info("::: loginForm - loginError :::");
        model.addAttribute("loginError", true);
        return "login/login";
    }

    @GetMapping("/join")
    public String joinForm(){
        log.info("::: joinForm :::");
        return "login/join";
    }

    @PostMapping("/join")
    public ResponseEntity joinMember(@RequestBody Member member) {
        log.info("::: join :::");

        final Long memberSeq = memberService.joinMember(member);

        return ResponseEntity.ok(ResultUtil.createResultResponse(true, "성공"));
    }

}
