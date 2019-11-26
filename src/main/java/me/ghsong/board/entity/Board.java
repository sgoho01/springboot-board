package me.ghsong.board.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * @author : Song.gunho
 * <p>
 * Date: 2019-11-08
 */
@Entity
@Table(name = "BOARD")
@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@DynamicUpdate
public class Board extends BaseEntity{

    @Id
    @Column(name = "BOARD_SEQ")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long boardSeq;

    @Setter
    @Column(name = "BOARD_TITLE", length = 20, nullable = false)
    private String boardTitle;

    @Setter
    @Column(name = "BOARD_CONTENTS", nullable = false)
    private String boardContents;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "MEMBER_SEQ")
    private Member member;

    @Setter
    @Column(name = "STATUS")
    private String status;

    public void setCreateColumn() {
        this.status = "Y";
    }

    @Builder
    public Board(String boardTitle, String boardContents, Member member) {
        this.boardTitle = boardTitle;
        this.boardContents = boardContents;
        this.member = member;
        setCreateColumn();
    }


}
