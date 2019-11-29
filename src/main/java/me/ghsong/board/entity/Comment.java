package me.ghsong.board.entity;

import lombok.*;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

/**
 * @author : Song.gunho
 * <p>
 * Date: 2019-11-28
 */
@Entity
@Table(name = "COMMENT")
@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@DynamicUpdate
public class Comment extends BaseEntity {

    @Id
    @Column(name = "COMMENT_SEQ")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commentSeq;

    @Setter
    @Column(name = "COMMENT_CONTENTS")
    private String commentContents;

    @ManyToOne(targetEntity = Board.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "BOARD_SEQ")
    private Board board;

    @ManyToOne(targetEntity = Member.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "MEMBER_SEQ")
    private Member member;

    @Builder
    public Comment(String commentContents, Board board, Member member) {
        this.commentContents = commentContents;
        this.board = board;
        this.member = member;
    }
}
