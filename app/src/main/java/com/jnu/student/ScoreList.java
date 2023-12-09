package com.jnu.student;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;

public class ScoreList implements Serializable {
    //名字
    private String name;
    //积分
    private int score;
    //时间
    private long time;// = System.currentTimeMillis();

    //初始化
    public ScoreList(String name_, int score_,long time_) {
        this.name=name_;
        this.score=score_;
        this.time=time_;
    }
    public int getScore() {
        return score;
    }
    public String getName() {
        return name;
    }
    public long getTime() {
        return time;
    }
//在android开发中，我有一个ScoreList类型数组A，每个ScoreList有String name，int score，long time这三个值，我想将时间和score绘制一个折线图
}
