package me.ghsong.board.entity;

import lombok.*;

import javax.persistence.*;

/**
 * @author : Song.gunho
 * <p>
 * Date: 2019-11-07
 */
@Entity
@Table(name = "MEMBER")
@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MEMBER_SEQ")
    private Long memberSeq;

    @Column(name = "MEMBER_ID", length = 20, nullable = false, unique = true)
    private String memberId;

    @Setter
    @Column(name = "MEMBER_PASSWORD", length = 100, nullable = false)
    private String memberPassword;

    @Column(name = "MEMBER_NAME", length = 20, nullable = false)
    private String memberName;

    @Column(name = "MEMBER_MOBILE", length = 20, nullable = false)
    private String memberMobile;

    @Builder
    public Member(String memberId, String memberPassword, String memberName, String memberMobile) {
        this.memberId = memberId;
        this.memberPassword = memberPassword;
        this.memberName = memberName;
        this.memberMobile = memberMobile;
    }
}
