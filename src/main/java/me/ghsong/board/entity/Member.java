package me.ghsong.board.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * @author : Song.gunho
 * <p>
 * Date: 2019-11-07
 * Copyright(©) 2019 by ATOSTUDY.
 */
@Entity
@Table(name = "MEMBER")
@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MEMBER_SEQ")
    private Long memberSeq;

    @Column(name = "MEMBER_NAME", length = 20, nullable = false)
    private String memberName;

    @Column(name = "MEMBER_MOBILE", length = 20, nullable = false, unique = true)
    private String memberMobile;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "CREATE_AT")
    private LocalDateTime createdAt;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "UPDATED_AT")
    private LocalDateTime updatedAt;

    public void setCurrentTime() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    public void update(Member member) {
        this.memberName = member.getMemberName();
        this.updatedAt = LocalDateTime.now();
    }

    @Builder
    public Member(String memberName, String memberMobile) {
        this.memberName = memberName;
        this.memberMobile = memberMobile;
        setCurrentTime();
    }
}
