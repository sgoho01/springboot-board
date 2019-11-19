package me.ghsong.board.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author : Song.gunho
 * <p>
 * Date: 2019-11-19
 * Copyright(©) 2019 by ATOSTUDY.
 */
@AllArgsConstructor
@Getter
public enum Role {

    ADMIN("ROLE_ADMIN"),
    MEMBER("ROLE_MEMBER");

    private String value;
}
