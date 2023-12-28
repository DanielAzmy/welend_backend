package Model;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public class Session {
    private long id;
    private long userId;
    private LocalDateTime creationTime;
    private Timestamp creationTimeStamp;
    private String token;
    private String status;

    public Session(LocalDateTime creationTime, String status, String token, long userId) {
        this.creationTime = creationTime;
        this.userId = userId;
        this.token = token;
        this.status = status;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public LocalDateTime getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(LocalDateTime creationTime) {
        this.creationTime = creationTime;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
