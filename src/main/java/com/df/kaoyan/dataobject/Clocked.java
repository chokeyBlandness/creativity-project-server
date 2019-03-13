package com.df.kaoyan.dataobject;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Clocked {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long clockedId;
    @Column(nullable = false)
    private Long userId;
    @Column(nullable = false)
    private String content;
    @Column(nullable = false)
    private Date clockInDate;

    public Long getClockedId() {
        return clockedId;
    }

    public void setClockedId(Long clockedId) {
        this.clockedId = clockedId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getClockInDate() {
        return clockInDate;
    }

    public void setClockInDate(Date clockInDate) {
        this.clockInDate = clockInDate;
    }
}
