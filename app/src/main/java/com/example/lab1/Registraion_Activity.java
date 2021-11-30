package com.example.lab1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

public class Registraion_Activity extends AppCompatActivity {
    EditText fname,lname,regno,departement;
    Button submit;

    static ArrayList<Student_model> Students;
    Student_model student_model = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registraion);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Register Student");
        fname=(EditText) findViewById(R.id.fname);
        lname=(EditText) findViewById(R.id.lname);
        regno=(EditText) findViewById(R.id.regno);
        departement=(EditText) findViewById(R.id.departement);
        if(Students==null)
            Students=new ArrayList<Student_model>();
       submit=(Button) findViewById(R.id.submit);
       submit.setOnClickListener(new View.OnClickListener() {
           Student_model finalstudent_model=student_model;
           @Override
           public void onClick(View v) {
               finalstudent_model= new Student_model(fname.getText().toString(),lname.getText().toString(),regno.getText().toString(),departement.getText().toString());
               Students.add(finalstudent_model);
               finalstudent_model.setStudents(Students);
               fname.getText().clear();
               lname.getText().clear();
               regno.getText().clear();
               departement.getText().clear();
              // startActivity(intent);
           }
       });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        Intent myIntent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(myIntent);
        return true;
    }
}