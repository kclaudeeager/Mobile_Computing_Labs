package com.example.lab1;

import java.io.Serializable;
import java.util.ArrayList;

public class Student_model  {
    String fname,lname,regno,departement;
   static ArrayList<Student_model> Students;

    public Student_model(String regno) {
        this.regno = regno;
    }

    public Student_model(ArrayList<Student_model> students) {
        Students = students;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public void setRegno(String regno) {
        this.regno = regno;
    }

    public void setDepartement(String departement) {
        this.departement = departement;
    }

    public Student_model(String regno, String departement) {
        this.regno = regno;
        this.departement = departement;
    }

    public String getFname() {
        return fname;
    }

    public String getLname() {
        return lname;
    }

    public String getRegno() {
        return regno;
    }

    @Override
    public String toString() {
        return "Student_model{" +
                "fname='" + fname + '\'' +
                ", lname='" + lname + '\'' +
                ", regno='" + regno + '\'' +
                ", departement='" + departement + '\'' +
                '}';
    }

    public String getDepartement() {
        return departement;
    }

    public Student_model(String fname, String lname, String regno, String departement) {
        this.fname = fname;
        this.lname = lname;
        this.regno = regno;
        this.departement = departement;
    }

    public void setStudents(ArrayList<Student_model> students) {
        Students = students;
    }

    public static ArrayList<Student_model> getStudents() {
        return Students;
    }
}
