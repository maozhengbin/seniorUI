package com.travelto.maomao.travel_together.data.dto;

import cn.bmob.v3.BmobObject;

/**
 * Created by maomao on 2017/3/11.
 */

public class Discuss extends BmobObject {
    private String usernub;
    private String usernickname;
    private String discuss;
    private String assessid;

    public String getUsernub() {
        return usernub;
    }

    public void setUsernub(String usernub) {
        this.usernub = usernub;
    }

    public String getUsernickname() {
        return usernickname;
    }

    public void setUsernickname(String usernickname) {
        this.usernickname = usernickname;
    }

    public String getDiscuss() {
        return discuss;
    }

    public void setDiscuss(String discuss) {
        this.discuss = discuss;
    }

    public String getAssessid() {
        return assessid;
    }

    public void setAssessid(String assessid) {
        this.assessid = assessid;
    }
}
