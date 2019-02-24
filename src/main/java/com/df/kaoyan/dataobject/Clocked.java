package com.df.kaoyan.dataobject;

import java.util.Date;

public class Clocked {
    private Long clockedId;
    private Long userId;
    private String content;
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
