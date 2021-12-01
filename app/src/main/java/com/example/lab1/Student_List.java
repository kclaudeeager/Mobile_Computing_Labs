package com.example.lab1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.session.PlaybackState;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import java.util.ArrayList;

public class Student_List extends AppCompatActivity {
   static RecyclerView studentRecycler;
   ArrayList<Student_model> Students=new ArrayList<>();
   Student_model student_model;
   StudentAdapter studentAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_list);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("List of students");
       // student_model=(Student_model) getIntent().getSerializableExtra("Student");
        if(Student_model.getStudents()!=null)
        for(Student_model student_model:Student_model.getStudents()){
            Log.d("Fname: ",student_model.getFname());
            Students.add(student_model);
        }

        studentRecycler=(RecyclerView) findViewById(R.id.St_list);
        studentAdapter=new StudentAdapter(Students);
        studentRecycler.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        studentRecycler.setVerticalScrollBarEnabled(true);
        studentRecycler.setAdapter(studentAdapter);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()) {
            case android.R.id.home:
                Student_List.this.finish();

        }
        return true;
    }

}