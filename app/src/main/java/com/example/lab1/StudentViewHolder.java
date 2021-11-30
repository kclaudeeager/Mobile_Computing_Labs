package com.example.lab1;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class StudentViewHolder extends RecyclerView.ViewHolder {
    TextView fname,lname,regno,department;
    public StudentViewHolder(@NonNull View itemView) {

        super(itemView);
        fname=(TextView) itemView.findViewById(R.id.first_name);
        lname=(TextView) itemView.findViewById(R.id.last_name);
        regno=(TextView) itemView.findViewById(R.id.reg_number);
        department=(TextView) itemView.findViewById(R.id.dept);

    }
    public void BindStudent(final Student_model student){
        fname.setText(student.getFname());
        lname.setText(student.getLname());
        regno.setText(student.getRegno());
        department.setText(student.getDepartement());
    }
}
