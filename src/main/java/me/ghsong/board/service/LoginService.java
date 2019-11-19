package me.ghsong.board.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.ghsong.board.entity.CustomUser;
import me.ghsong.board.entity.Member;
import me.ghsong.board.entity.Role;
import me.ghsong.board.repository.MemberRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author : Song.gunho
 * <p>
 * Date: 2019-11-19
 * Copyright(©) 2019 by ATOSTUDY.
 */
@Service
@AllArgsConstructor
@Slf4j
public class LoginService implements UserDetailsService {

    private MemberRepository memberRepository;


    @Override
    public UserDetails loadUserByUsername(String memberId) throws UsernameNotFoundException {

        Optional<Member> optionalMember = memberRepository.findByMemberId(memberId);
        Member member = optionalMember.get();
        log.debug("◆◆◆◆◆ member : {}", member.toString());

        List<GrantedAuthority> authorities = new ArrayList<>();

        if ("admin".equalsIgnoreCase(member.getMemberId())) {
            authorities.add(new SimpleGrantedAuthority(Role.ADMIN.getValue()));
        } else {
            authorities.add(new SimpleGrantedAuthority(Role.MEMBER.getValue()));
        }
        log.debug("◆◆◆◆◆ authorities : {}", authorities.toString());

//        return new User(member.getMemberId(), member.getMemberPassword(), authorities);
        return CustomUser.builder()
                .username(member.getMemberId())
                .password(member.getMemberPassword())
                .memberName(member.getMemberName())
                .memberMobile(member.getMemberMobile())
                .authorities(authorities)
                .build();
    }
}
