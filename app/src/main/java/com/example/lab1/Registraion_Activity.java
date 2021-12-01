package com.example.lab1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.lab1.SqliteDB_part.operations_student;

import java.util.ArrayList;

public class Registraion_Activity extends AppCompatActivity {
    EditText fname,lname,regno,departement;
    Button submit;

    static ArrayList<Student_model> Students;
    Student_model student_model = null;
    private operations_student dbHandler;

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
        dbHandler = new operations_student(Registraion_Activity.this);
        if(Students==null)
            Students=new ArrayList<Student_model>();
       submit=(Button) findViewById(R.id.submit);
       submit.setOnClickListener(new View.OnClickListener() {
           Student_model finalstudent_model=student_model;
           @Override
           public void onClick(View v) {
               if (fname.getText().length() >= 1 && lname.getText().length() >= 1 && regno.getText().length() >= 1 && departement.getText().length() >= 1) {

                   finalstudent_model = new Student_model(fname.getText().toString(), lname.getText().toString(), Integer.parseInt(regno.getText().toString()), departement.getText().toString());
                   Students.add(finalstudent_model);
                   finalstudent_model.setStudents(Students);
                   dbHandler.addStudent(finalstudent_model);
                   fname.getText().clear();
                   lname.getText().clear();
                   regno.getText().clear();
                   departement.getText().clear();
                   // startActivity(intent);
               }
               else{
                if(fname.getText().length()<1)
                    fname.setError("First name is required");
                   if(lname.getText().length()<1)
                       lname.setError("Last name is required");
                   if(regno.getText().length()<1)
                       regno.setError("Reg number is required");
                   if(departement.getText().length()<1)
                       departement.setError("Department name is required");
               }
           }

       });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Registraion_Activity.this.finish();

        }
        return true;
    }
}