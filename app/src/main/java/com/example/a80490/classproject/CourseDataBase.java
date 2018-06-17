package com.example.a80490.classproject;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import static android.os.Build.ID;

/**
 * Created by 李洵枫 on 2018/6/7 0007.
 */

public class CourseDataBase {

    private List<Course> CourseDB;

    public CourseDataBase() {

        CourseDB=new LinkedList<Course>();
    }

    public void addCourse(Course course) {
        CourseDB.add(course);
    }

    public  void addCourse(String name, String place, String whichday, String begin, String lasts){
        Course course=new Course(name,place,whichday,begin,lasts);
        CourseDB.add(course);
    }

    public Iterator<Course> getCourseIterator() {
        return CourseDB.iterator();
    }

    public void removetCourse(String whichday,String begin) {
        Iterator<Course> Ite=getCourseIterator();

        for(int i=0;i<CourseDB.size();i++) {

            Course course=Ite.next();

            if(course.getWhichday().equals(whichday)
                    && course.getBegin().equals(begin)) {
                Ite.remove();
            }
        }

    }

    public Course getCourse(String whichday,String begin) {
        Iterator<Course> Ite=getCourseIterator();
        for(int i=0;i<CourseDB.size();i++) {
            Course course=Ite.next();
            if(course.getWhichday().equals(whichday)
                    && course.getBegin().equals(begin))
                return CourseDB.get(i);
        }
        return null;
    }

    public void displayCourse() {
        if(CourseDB.size()==0) {
            System.out.println("这是一个空集");
        }
        else {
            Iterator<Course> Ite=getCourseIterator();
            String result="";
            while(Ite.hasNext()) {
                result+=Ite.next().toString();
                result+="\n\n";
            }
            // System.out.println("");
            result+="\n";
            System.out.print(result);
        }
    }

    public int getSizeOfCourse() {
        return CourseDB.size();
    }

    public String toString(){
        Iterator<Course> Ite=getCourseIterator();
        String total="";
        while (Ite.hasNext()){
            Course course=Ite.next();
            total+=course.toString()+"*"+"\n";
        }

        return total;
    }

}
