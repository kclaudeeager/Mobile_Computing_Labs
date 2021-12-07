package com.example.lab1;

import android.content.Context;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
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
    public StudentViewHolder(@NonNull View itemView,Context context) {

        super(itemView);
        fname=(TextView) itemView.findViewById(R.id.first_name);
        lname=(TextView) itemView.findViewById(R.id.last_name);
        regno=(TextView) itemView.findViewById(R.id.reg_number);
        department=(TextView) itemView.findViewById(R.id.dept);
        edit=(Button) itemView.findViewById(R.id.edit);
        student_models=Student_model.getStudents();
        for(Student_model student_model:student_models){
            Log.d("got: ",student_model.toString());
        }
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
                             studentAdapter=new StudentAdapter(student_models,context);
                             studentAdapter.notifyDataSetChanged();
                                // Student_List.studentRecycler.notify();
                             Student_List.studentRecycler.setAdapter(studentAdapter);
                         }
                     }
                    

            }
        });
        edit.setOnClickListener(new View.OnClickListener() {
            EditText editfname,editlname,editdepartment,editreg;
            Button update;
            @Override
            public void onClick(View v) {
                LayoutInflater inflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                View popupView=inflater.inflate(R.layout.update_layoutt,null);
                if(popupView!=null) {
                    editfname=popupView.findViewById(R.id.edfname);
                    editlname=popupView.findViewById(R.id.edlname);
                    editdepartment=popupView.findViewById(R.id.eddepartement);
                    editreg=popupView.findViewById(R.id.edregno);
                    update=popupView.findViewById(R.id.update);
                    editfname.setText(fname.getText());
                    editlname.setText(lname.getText());
                    editreg.setText(regno.getText());
                    editreg.setEnabled(false);
                    editdepartment.setText(department.getText());
                    //create the popup womdow
                    int width = LinearLayout.LayoutParams.WRAP_CONTENT;
                    int height = LinearLayout.LayoutParams.WRAP_CONTENT;
                    boolean focusable = true;//lets tap outside makes window to dismiss
                    final PopupWindow popupWindow = new PopupWindow(popupView, width, height, focusable);
                    //show the popup window

                    popupWindow.showAtLocation(v, Gravity.CENTER, 4, 50);

                    popupWindow.setElevation(40);
                    //dismiss the window when touched
                    update.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                       Student_model student_model=new Student_model(editfname.getText().toString(),editlname.getText().toString(),Integer.parseInt(editreg.getText().toString()),editdepartment.getText().toString());
                       dbHandler.UpdateStudent(student_model);
                       popupWindow.dismiss();
                      // student_models.notify();
                            for(int i=0;i<student_models.size();i++){
                                if(student_models.get(i).getRegno()==student_model.getRegno())
                                    student_models.get(i).setFname(student_model.getFname());
                                student_models.get(i).setLname(student_model.getLname());
                                student_models.get(i).setDepartement(student_model.getDepartement());
                            }
                            studentAdapter=new StudentAdapter(student_models,context);
                            studentAdapter.notifyDataSetChanged();
                            // Student_List.studentRecycler.notify();
                            Student_List.studentRecycler.setAdapter(studentAdapter);

                        }
                    });
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
