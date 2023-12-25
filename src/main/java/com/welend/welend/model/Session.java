package com.welend.welend.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Session {
    private long id;
    private long userId;
    private LocalDateTime creationTime;
    private String token;
    private String status;

    public Session(LocalDateTime creationTime, String status, String token, long userId) {
        this.creationTime = creationTime;
        this.userId = userId;
        this.token = token;
        this.status = status;
    }
}
