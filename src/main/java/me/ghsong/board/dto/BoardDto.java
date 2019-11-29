package me.ghsong.board.dto;

import lombok.*;

/**
 * @author : Song.gunho
 * <p>
 * Date: 2019-11-25
 * Copyright(©) 2019 by ATOSTUDY.
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class BoardDto {

    private Long boardSeq;
    private String boardTitle;
    private String boardContents;
    private Long memberSeq;

}
