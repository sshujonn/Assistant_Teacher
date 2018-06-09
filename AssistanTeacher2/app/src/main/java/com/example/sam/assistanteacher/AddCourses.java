package com.example.sam.assistanteacher;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class AddCourses extends AppCompatActivity implements View.OnClickListener {

    Spinner spinner, spinner1, spinner2;
    ArrayAdapter<CharSequence> adapter;
    Button bAddCourse;
    EditText etCourseName, etPrefix, etCourseNo, etPostfix, etCredit, etSession;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_courses);
        spinner = (Spinner) findViewById(R.id.spinnerDegree);
        spinner1 = (Spinner) findViewById(R.id.spinnerYear);
        spinner2 = (Spinner) findViewById(R.id.spinnerTerm);

        adapter = ArrayAdapter.createFromResource(this, R.array.degree, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        adapter = ArrayAdapter.createFromResource(this, R.array.year, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(adapter);

        adapter = ArrayAdapter.createFromResource(this, R.array.term, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(adapter);

        etCourseName = (EditText) findViewById(R.id.etCourseName);
        etPrefix = (EditText) findViewById(R.id.etPrefix);
        etCourseNo = (EditText) findViewById(R.id.etCourseNO);
        etPostfix = (EditText) findViewById(R.id.etPostfix);
        etCredit = (EditText) findViewById(R.id.etCredit);
        etSession = (EditText) findViewById(R.id.etSession);
        bAddCourse = (Button) findViewById(R.id.bAddCourse);

        bAddCourse.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bRegister:

                String CourseName = etCourseName.getText().toString();
                String Prefix = etPrefix.getText().toString();
                String CourseNo = etCourseNo.getText().toString();
                String Postfix = etPostfix.getText().toString();
                String Credit = etCredit.getText().toString();
                String Session = etSession.getText().toString();
                String degree = spinner.getSelectedItem().toString();
                String year = spinner1.getSelectedItem().toString();
                String term = spinner2.getSelectedItem().toString();

                userAddCourse userAddCourse=new userAddCourse(CourseName,Prefix,CourseNo,Postfix,Credit,Session,degree,year,term);

                //registerUser(user);
                userCourse(userAddCourse);
                break;
        }
    }

    private void userCourse(userAddCourse userAddCourse) {
        ServerRequests serverRequests = new ServerRequests(this);
        serverRequests.storeCourseDataInBackground(userAddCourse, new GetCourseCallback() {
            @Override
            public void done(userAddCourse returnCourse) {
                startActivity(new Intent(AddCourses.this, Courses.class));
            }
        });
    }
}
