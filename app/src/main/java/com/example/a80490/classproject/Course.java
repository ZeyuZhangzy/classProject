package com.example.a80490.classproject;

import static android.R.attr.name;

/**
 * Created by 李洵枫 on 2018/6/7 0007.
 */



public class Course {

    private String name;
    private String place;
    private String whichday;
    private String begin;
    private String lasts;

    //	Date Time;
    public Course(String name, String place, String whichday, String begin, String lasts) {

        this.name = name;
        this.place = place;
        this.whichday = whichday;
        this.begin = begin;
        this.lasts = lasts;

    }

    public String getName() {
        return name;
    }

    public String getPlace() {
        return place;
    }

    public String getWhichday(){
        return whichday;
    }

    public String getBegin(){
        return begin;
    }

    public String getLasts(){
        return lasts;
    }

    public String toString(){

        return name+"_"+place+"_"+whichday+"_"+begin+"_"+lasts;
    }


}
