package me.ghsong.board.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.time.LocalDateTime;

/**
 * @author : Song.gunho
 * <p>
 * Date: 2019-11-26
 */
@Getter
@MappedSuperclass
public class BaseEntity {


    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "CREATE_AT", updatable = false)
    private LocalDateTime createdAt;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "UPDATED_AT")
    private LocalDateTime updatedAt;

    @Column(name = "STATUS")
    private String status;

    @PrePersist
    public void onCreate(){
        this.createdAt = LocalDateTime.now();
        this.status = "Y";
    }

    @PreUpdate
    public void onUpdate(){
        this.updatedAt = LocalDateTime.now();
    }

    public void onDelete(){
        this.updatedAt = LocalDateTime.now();
        this.status = "N";
    }

}
