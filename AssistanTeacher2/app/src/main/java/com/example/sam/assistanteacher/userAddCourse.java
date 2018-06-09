package com.example.sam.assistanteacher;

/**
 * Created by ASUS on 15-Jul-16.
 */
public class userAddCourse {

    String CourseName,Prefix,CourseNo,Postfix,Credit,Session,Degree,Year,Term;

    public userAddCourse(String CourseName,String Prefix,String CourseNo,String Postfix,String Credit,String Session,String Degree,String Year,String Term)
    {
        this.CourseName=CourseName;
        this.Prefix=Prefix;
        this.CourseNo=CourseNo;
        this.Postfix=Postfix;
        this.Credit=Credit;
        this.Session=Session;
        this.Degree=Degree;
        this.Year=Year;
        this.Term=Term;
    }
}
