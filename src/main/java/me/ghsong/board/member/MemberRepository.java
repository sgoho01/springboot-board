package me.ghsong.board.member;

import me.ghsong.board.member.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author : Song.gunho
 * <p>
 * Date: 2019-11-07
 * Copyright(©) 2019 by ATOSTUDY.
 */
@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
}
