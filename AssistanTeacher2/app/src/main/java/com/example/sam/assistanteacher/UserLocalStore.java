package com.example.sam.assistanteacher;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by ASUS on 25-Mar-16.
 */
public class UserLocalStore {
    public static  final String SP_NAME="userDetails";
    SharedPreferences userLocalDatabase;

    public UserLocalStore(Context context)
    {
        userLocalDatabase=context.getSharedPreferences(SP_NAME,0);
    }

    public void storeUserData(User user)
    {
        SharedPreferences.Editor spEditor = userLocalDatabase.edit();
        spEditor.putString("name", user.name);
        spEditor.putString("username",user.username);
        spEditor.putString("password", user.password);
        spEditor.putString("university",user.university);
        spEditor.putString("department",user.department);
        spEditor.putString("sex",user.sex);
        spEditor.putString("designation",user.designation);
        spEditor.commit();
    }

    public  User getLoggedInUser()
    {
        String name=userLocalDatabase.getString("name", "");
        String username=userLocalDatabase.getString("username","");
        String password=userLocalDatabase.getString("password","");
        String university=userLocalDatabase.getString("university","");
        String department=userLocalDatabase.getString("department","");
        String sex=userLocalDatabase.getString("sex","");
        String designation=userLocalDatabase.getString("designation","");


        User storedUser=new User(name,username,password,university,department,sex,designation);
        return storedUser;
    }

    public void setUserLoggedIn(boolean loggedIn){
        SharedPreferences.Editor spEditor=userLocalDatabase.edit();
        spEditor.putBoolean("LoggedIn",loggedIn);
        spEditor.commit();
    }

    public boolean getUserLoggedIn(){
        if (userLocalDatabase.getBoolean("LoggedIn",false)==true)
        {
            return  true;
        }
        else {
            return false;
        }
    }

    public void clearUserData()
    {
        SharedPreferences.Editor spEditor=userLocalDatabase.edit();
        spEditor.clear();
        spEditor.commit();
    }

}
