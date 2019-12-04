package me.ghsong.board.dto;

import lombok.*;

/**
 * @author : Song.gunho
 * <p>
 * Date: 2019-11-25
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class BoardDto {

    private String boardTitle;
    private String boardContents;
    private Long memberSeq;

}
