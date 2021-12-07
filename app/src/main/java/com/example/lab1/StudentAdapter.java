package com.example.lab1;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class StudentAdapter extends RecyclerView.Adapter<StudentViewHolder> {
    ArrayList<Student_model> students;
    StudentViewHolder studentViewHolder;
Context context;
    public StudentAdapter(ArrayList<Student_model> students, StudentViewHolder studentViewHolder) {
        this.students = students;
        this.studentViewHolder = studentViewHolder;
    }

    public StudentAdapter(StudentViewHolder studentViewHolder) {
        this.studentViewHolder = studentViewHolder;
    }

    public StudentAdapter(ArrayList<Student_model> students,Context context) {
        this.students = students;
        this.context=context;
        //Log.d("add: ",students.get(1).getFname());
    }

    @NonNull
    @Override
    public StudentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.student_view,parent,false);
        return new StudentViewHolder(view,parent.getContext());

    }

    public StudentAdapter() {
    }

    @Override
    public void onBindViewHolder(@NonNull StudentViewHolder holder, int position) {
final Student_model student_model=students.get(position);
holder.BindStudent(student_model);
studentViewHolder=holder;
    }

    @Override
    public int getItemCount() {
        return students.size();
    }
}
