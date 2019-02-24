package com.df.kaoyan.dataobject;

public class ClockRequst {
    private Long clockRequestId;
    private Long userId;
    private String clockRequest;

    public Long getClockRequestId() {
        return clockRequestId;
    }

    public void setClockRequestId(Long clockRequestId) {
        this.clockRequestId = clockRequestId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getClockRequest() {
        return clockRequest;
    }

    public void setClockRequest(String clockRequest) {
        this.clockRequest = clockRequest;
    }
}
