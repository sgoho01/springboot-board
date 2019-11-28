package me.ghsong.board.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.ghsong.board.Util.ResultUtil;
import me.ghsong.board.entity.Member;
import me.ghsong.board.repository.MemberRepository;
import me.ghsong.board.service.MemberService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

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
    public String loginForm() {
        log.info("::: loginForm :::");
        return "login/login";
    }

    @GetMapping("/login-error")
    public String loginError(Model model) {
        log.info("::: loginForm - loginError :::");
        model.addAttribute("loginError", true);
        return "login/login";
    }

    @GetMapping("/join")
    public String joinForm() {
        log.info("::: joinForm :::");
        return "login/join";
    }

    @PostMapping(value = "/join", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity joinMember(@RequestBody Member member) {
        log.info("::: joinMember :::");

        final Member checkMember = memberRepository.findByMemberId(member.getMemberId()).orElse(null);
        if (checkMember != null) {
            return ResponseEntity.ok(ResultUtil.createResultResponse(false, "사용할수 없는 아이디 입니다."));
        }

        final Long memberSeq = memberService.joinMember(member);

        return ResponseEntity.ok(ResultUtil.createResultResponse(true, "성공"));
    }

    @PostMapping(value = "/duplicate", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity duplicateMemeberId(@RequestBody Map params) {
        log.info("::: duplicateMemeberId :::");
        log.debug("params : {}", params);

        final Member member = memberRepository.findByMemberId(params.get("memberId").toString()).orElse(null);
        log.debug("member : {}", member);

        if (member == null) {
            return ResponseEntity.ok(ResultUtil.createResultResponse(true, "가입할 수 있는 아이디입니다."));
        } else {
            return ResponseEntity.ok(ResultUtil.createResultResponse(false, "가입할 수 없는 아이디입니다."));
        }
    }

}
