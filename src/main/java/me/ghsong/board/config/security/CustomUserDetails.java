package me.ghsong.board.config.security;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

/**
 * @author : Song.gunho
 * <p>
 * Date: 2019-11-19
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class CustomUserDetails implements UserDetails {

    private String username;
    private String password;
    private boolean isEnabled;
    private boolean isAccountNonExpired;
    private boolean isAccountNonLocked;
    private boolean isCredentialsNonExpired;
    private Collection<? extends GrantedAuthority> authorities;

    private String memberName;
    private String memberMobile;
    private Long memberSeq;

    @Builder
    public CustomUserDetails(String username, String password, String memberName, String memberMobile, Long memberSeq, Collection<? extends GrantedAuthority> authorities) {
        this.username = username;
        this.password = password;
        this.memberName = memberName;
        this.memberMobile = memberMobile;
        this.authorities = authorities;
        this.memberSeq = memberSeq;
        this.isEnabled = true;
        this.isAccountNonExpired = true;
        this.isAccountNonLocked = true;
        this.isCredentialsNonExpired = true;
    }
}
