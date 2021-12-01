package com.example.lab1;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lab1.SqliteDB_part.operations_student;

import java.util.ArrayList;

public class StudentViewHolder extends RecyclerView.ViewHolder {
    TextView fname,lname,regno,department;
    ArrayList<Student_model> student_models;
    Button edit,delete;
      StudentAdapter studentAdapter;
       private operations_student dbHandler;
    public StudentViewHolder(@NonNull View itemView) {

        super(itemView);
        fname=(TextView) itemView.findViewById(R.id.first_name);
        lname=(TextView) itemView.findViewById(R.id.last_name);
        regno=(TextView) itemView.findViewById(R.id.reg_number);
        department=(TextView) itemView.findViewById(R.id.dept);
        edit=(Button) itemView.findViewById(R.id.edit);
        student_models=Student_model.getStudents();
        delete=(Button) itemView.findViewById(R.id.delete);
          dbHandler = new operations_student(((Button) itemView.findViewById(R.id.delete)).getContext());
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("delete:",regno.getText().toString());
                     dbHandler.deleteStudent(regno.getText().toString());
                     for(int i=0;i<student_models.size();i++){
                         Student_model student=student_models.get(i);
                         if(student.getRegno()==(Integer.parseInt(regno.getText().toString()))){
                             student_models.remove(student);
                             studentAdapter=new StudentAdapter(student_models);
                             studentAdapter.notifyDataSetChanged();
                                // Student_List.studentRecycler.notify();
                             Student_List.studentRecycler.setAdapter(studentAdapter);
                         }
                     }
                    

            }
        });

    }
    public void BindStudent(final Student_model student){
        fname.setText(student.getFname());
        lname.setText(student.getLname());
        String reg=""+student.getRegno();
        regno.setText(reg);
        department.setText(student.getDepartement());
    }

}
