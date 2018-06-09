package com.example.sam.assistanteacher;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class Register extends AppCompatActivity implements View.OnClickListener {

    Spinner spinner, spinner1;
    ArrayAdapter<CharSequence>adapter;
    Button bRegister;
    EditText etName,etUsername,etPassword,etUniversity,etDepartment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        spinner=(Spinner)findViewById(R.id.spinnerSex);
        spinner1=(Spinner)findViewById(R.id.spinnerDesignation);

        adapter=ArrayAdapter.createFromResource(this,R.array.sex,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        adapter=ArrayAdapter.createFromResource(this,R.array.designaton,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(adapter);

        etName=(EditText) findViewById(R.id.etName);
        etUsername=(EditText) findViewById(R.id.etUsername);
        etPassword=(EditText) findViewById(R.id.etPassword);
        etUniversity=(EditText) findViewById(R.id.etUniversity);
        etDepartment=(EditText) findViewById(R.id.etDepartment);
        bRegister=(Button) findViewById(R.id.bRegister);

        bRegister.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.bRegister:

                String name=etName.getText().toString();
                String username=etUsername.getText().toString();
                String password=etPassword.getText().toString();
                String university=etUniversity.getText().toString();
                String department=etDepartment.getText().toString();
                String sex=spinner.getSelectedItem().toString();
                String designation=spinner1.getSelectedItem().toString();

                User user=new User(name,username,password,university,department,sex,designation);

                registerUser(user);
                break;
        }
    }

    private void registerUser(User user)
    {
        ServerRequests serverRequests=new ServerRequests(this);
        serverRequests.storeUserDataInBackground(user, new GetUserCallback() {
            @Override
            public void done(User returnedUser) {
                startActivity(new Intent(Register.this,Login.class));
            }
        });
    }

}
