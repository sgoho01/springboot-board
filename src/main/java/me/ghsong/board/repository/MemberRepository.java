package me.ghsong.board.repository;

import me.ghsong.board.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author : Song.gunho
 * <p>
 * Date: 2019-11-07
 */
@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {

    /**
     * 아이디로 사용자 조회
     * @param memberId
     * @return
     */
    Optional<Member> findByMemberId(String memberId);
}
