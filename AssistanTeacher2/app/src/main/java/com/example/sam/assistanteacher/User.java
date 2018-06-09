package com.example.sam.assistanteacher;

/**
 * Created by ASUS on 25-Mar-16.
 */
public class User {

    String name, username, password,university,department,sex,designation;




    public User(String name,String username, String password,String university,String department,String sex,String designation)
    {
        this.name=name;
        this.username=username;
        this.password=password;
        this.university=university;
        this.department=department;
        this.sex=sex;
        this.designation=designation;
    }

    public User(String username, String password)
    {
        this.name="";
        this.username=username;
        this.password=password;
    }


}
