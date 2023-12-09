package com.jnu.student;

import java.io.Serializable;

public class UserData implements Serializable {
    private String userName;
    private  int imageId;
    private  int totalScore;

    public UserData(String userName_, int imageId_,int totalScore_) {
        this.userName=userName_;
        this.imageId=imageId_;
        this.totalScore=totalScore_;
    }

}
