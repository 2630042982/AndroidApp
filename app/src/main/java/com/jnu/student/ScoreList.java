package com.jnu.student;

import java.util.ArrayList;
import java.util.Calendar;

public class ScoreList {
    //名字
    private String name;
    //积分
    private Integer score;
    //时间
    private long time;// = System.currentTimeMillis();

    //初始化
    public ScoreList(String name_, Integer score_,long time_) {
        this.name=name_;
        this.score=score_;
        this.time=time_;
    }
    public int getscore() {
        return score;
    }

}
