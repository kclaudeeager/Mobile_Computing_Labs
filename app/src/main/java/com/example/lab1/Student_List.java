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

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.lab1.SqliteDB_part.operations_student;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Map;

public class Student_List extends AppCompatActivity {
   static RecyclerView studentRecycler;
   ArrayList<Student_model> Students=new ArrayList<>();
   Student_model student_model;
   StudentAdapter studentAdapter;
    private operations_student dbHandler;
   static ArrayList<Student_model> student_models;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_list);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("List of students");
       // student_model=(Student_model) getIntent().getSerializableExtra("Student");
        dbHandler=new operations_student(this);
        student_models=dbHandler.FetchStudents();
        Student_model.Students=student_models;
        if(Student_model.getStudents()!=null)
        for(Student_model student_model:Student_model.getStudents()){
            Log.d("Fname: ",student_model.getFname());
            Students.add(student_model);

        }


        loadList();
//        studentRecycler=(RecyclerView) findViewById(R.id.St_list);
//        studentAdapter=new StudentAdapter(Students,Student_List.this);
//        studentRecycler.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
//        studentRecycler.setVerticalScrollBarEnabled(true);
//        studentRecycler.setAdapter(studentAdapter);
    }


    @Override

    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()) {
            case android.R.id.home:
                Student_List.this.finish();

        }
        return true;
    }

    public void loadList(){
       // contacts=new ArrayList<Contact>();
        RequestQueue requestQueue= Volley.newRequestQueue(this);
        String url="http://192.168.43.232//PHP-folder/List_Students.php";
        ArrayList<Student_model> students=new ArrayList<>();
        StringRequest request=new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                          //  Log.d("RESPONSE", response);
                           // JSONObject jsonObject = null;

                            //jsonObject = new JSONObject(response);


                            JSONArray jsonArray=new JSONArray(response);
                            //Log.d("json:", jsonArray.toString());

                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject res = null;

                                res = jsonArray.getJSONObject(i);

                                String fname = null;

                                fname = res.getString("fname");

                                String lname = null;

                                lname = res.getString("lname");

                                String reg = null;

                                reg = res.getString("regno");

                                String dep = null;

                                dep = res.getString("department");

                                Student_model stud = new Student_model(fname, lname, Integer.parseInt(reg), dep);

                                Log.d("remote:", stud.toString());
                              // if (!student_models.contains(stud)) {
                                    students.add(stud);
                                //}
                            }
                           for(Student_model student_model1:students) {
                              // Log.d("st1:", student_model1.toString());
                               if(!student_models.contains(student_model1))
                               student_models.add(student_model1);
                           }
                            for(Student_model student_model1: student_models) {
                                Log.d("st2:", student_model1.toString());
                            }
                            Student_model.Students=student_models;
                            if(Student_model.getStudents()!=null)
                                for(Student_model student_model:Student_model.getStudents()){
                                    Log.d("Fname: ",student_model.getFname());
                                    Students.add(student_model);
                                }

                        }
                        catch(Exception e){

                        }
                    }

                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("Error",error.getMessage());
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                return super.getParams();
            }
        };

        requestQueue.add(request);
        for(Student_model student_model1:students)
            Log.d("st1:",student_model1.toString());

        studentRecycler=(RecyclerView) findViewById(R.id.St_list);
        studentAdapter=new StudentAdapter(Students,Student_List.this);
        studentRecycler.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        studentRecycler.setVerticalScrollBarEnabled(true);
        studentRecycler.setAdapter(studentAdapter);
    }

}