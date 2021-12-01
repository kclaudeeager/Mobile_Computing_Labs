package com.example.lab1;

import java.util.ArrayList;

public class Student_model  {
    String fname,lname,departement;
    int regno;
   static ArrayList<Student_model> Students;

    public Student_model(int regno) {
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

    public void setRegno(int regno) {
        this.regno = regno;
    }

    public void setDepartement(String departement) {
        this.departement = departement;
    }

    public Student_model(int regno, String departement) {
        this.regno = regno;
        this.departement = departement;
    }

    public String getFname() {
        return fname;
    }

    public String getLname() {
        return lname;
    }

    public int getRegno() {
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

    public Student_model(String fname, String lname, int regno, String departement) {
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
