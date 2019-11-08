package me.ghsong.board.service;

import lombok.RequiredArgsConstructor;
import me.ghsong.board.repository.MemberRepository;
import org.springframework.stereotype.Service;

/**
 * @author : Song.gunho
 * <p>
 * Date: 2019-11-07
 * Copyright(©) 2019 by ATOSTUDY.
 */
@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;


}
