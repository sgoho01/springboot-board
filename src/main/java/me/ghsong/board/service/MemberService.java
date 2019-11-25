package me.ghsong.board.service;

import lombok.RequiredArgsConstructor;
import me.ghsong.board.entity.Member;
import me.ghsong.board.repository.MemberRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author : Song.gunho
 * <p>
 * Date: 2019-11-07
 */
@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;


    /**
     * 회원가입
     *
     * @param member
     * @return
     */
    @Transactional
    public Long joinMember(Member member) {
        // 비밀번호 암호화
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        member.setMemberPassword(passwordEncoder.encode(member.getMemberPassword()));

        return memberRepository.save(member).getMemberSeq();
    }

}
