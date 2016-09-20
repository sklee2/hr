package com.hyulib.login.domain;

/**
 * Created by sklee on 2015. 12. 31..
 */
public class Data {
    private String name;
    private String userId;
    private String gaeinNo;
    private String uuid;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getGaeinNo() {
        return gaeinNo;
    }

    public void setGaeinNo(String gaeinNo) {
        this.gaeinNo = gaeinNo;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }
}
