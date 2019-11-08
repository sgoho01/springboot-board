package me.ghsong.board.controller;

import lombok.RequiredArgsConstructor;
import me.ghsong.board.entity.Member;
import me.ghsong.board.repository.MemberRepository;
import me.ghsong.board.service.MemberService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author : Song.gunho
 * <p>
 * Date: 2019-11-07
 * Copyright(©) 2019 by ATOSTUDY.
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberController {

    private final MemberService memberService;
    private final MemberRepository memberRepository;


}
