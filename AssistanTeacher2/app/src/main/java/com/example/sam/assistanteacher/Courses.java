package com.example.sam.assistanteacher;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

public class Courses extends AppCompatActivity implements View.OnClickListener{

    Button bLogout;
    Button bAddCourse;
    TextView tvWelcome;
    UserLocalStore userLocalStore;
    ListView listView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_courses);
        listView=(ListView) findViewById(R.id.listView);

        tvWelcome=(TextView)findViewById(R.id.tvWelcome);
        bLogout=(Button) findViewById(R.id.bLogOut);
        bAddCourse=(Button)findViewById(R.id.bAC);
        bLogout.setOnClickListener(this);
        userLocalStore =new UserLocalStore(this);
    }

    @Override
    protected void onStart()
    {
        super.onStart();

        if(authenticate()==true)
        {
            displayUserDetails();
        }
        else {
            startActivity(new Intent(Courses.this,Login.class));
        }
    }

    private boolean authenticate()
    {
        return userLocalStore.getUserLoggedIn();
    }

    private void displayUserDetails()
    {
        User user=userLocalStore.getLoggedInUser();
        tvWelcome.setText("Welcome Mr. '"+user.name+"' Sir");
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.bLogOut:
                userLocalStore.clearUserData();
                userLocalStore.setUserLoggedIn(false);

                startActivity(new Intent(this,Login.class));
            case R.id.bAC:
                startActivity(new Intent(this,AddCourses.class));
                break;
        }

    }
}
