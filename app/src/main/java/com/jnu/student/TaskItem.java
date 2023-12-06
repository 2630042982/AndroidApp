package com.jnu.student;

import java.io.Serializable;

public class TaskItem implements Serializable {

    //活动name
    private String name;
    private int score;
    //imageId
    private final int imageId;


    //初始化
    public TaskItem(String name_, int bookId_,int score_) {
        this.name=name_;
        this.imageId=bookId_;
        this.score=score_;
    }
    //返回name
    public String getName() {
        return name;
    }
    //返回score
    public int getscore() {
        return score;
    }
    //返回imageId
    public int getImageId() { return imageId; }
    //修改name函数
    public void setName(String name) {
        this.name = name;
    }
    public void setScore(int score) {
        this.score = score;
    }




}
