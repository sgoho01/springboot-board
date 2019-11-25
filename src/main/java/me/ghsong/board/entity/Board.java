package me.ghsong.board.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

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
public class Board {

    @Id
    @Column(name = "BOARD_SEQ")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long boardSeq;

    @Column(name = "BOARD_TITLE", length = 20, nullable = false)
    private String boardTitle;

    @Column(name = "BOARD_CONTENTS", length = 20, nullable = false)
    private String boardContents;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "MEMBER_SEQ")
    private Member member;

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

    @Builder
    public Board(String boardTitle, String boardContents, Member member) {
        this.boardTitle = boardTitle;
        this.boardContents = boardContents;
        this.member = member;
        setCurrentTime();
    }
}
