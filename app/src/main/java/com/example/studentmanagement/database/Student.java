package com.example.studentmanagement.database;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;


@Entity(tableName = "student_table")
public class Student {
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "stID") @NonNull
    private int student_ID;

    @ColumnInfo(name="stFName") @NonNull
    private String student_First_Name;

    @ColumnInfo(name = "stLName") @NonNull
    private String student_Last_Name;

    @ColumnInfo(name = "stMajor") @NonNull
    private String student_Major;

    @ColumnInfo(name = "stGPA") @NonNull
    private double student_GPA;

    @ColumnInfo(name = "stPhone", defaultValue = "null")
    private String student_Phone;

    public Student(int student_ID, String student_First_Name, String student_Last_Name, String student_Major, double student_GPA, String student_Phone) {
        this.student_ID = student_ID;
        this.student_First_Name = student_First_Name;
        this.student_Last_Name = student_Last_Name;
        this.student_Major = student_Major;
        this.student_GPA = student_GPA;
        this.student_Phone = student_Phone;
    }

    @Ignore
    public Student(String student_First_Name, String student_Last_Name, String student_Major, Double student_GPA, String student_Phone) {
        this.student_First_Name = student_First_Name;
        this.student_Last_Name = student_Last_Name;
        this.student_Major = student_Major;
        this.student_GPA = student_GPA;
        this.student_Phone = student_Phone;
    }

    public int getStudent_ID() {
        return student_ID;
    }

    public String getStudent_First_Name() {
        return student_First_Name;
    }

    public String getStudent_Last_Name() {
        return student_Last_Name;
    }

    public String getStudent_Major() {
        return student_Major;
    }

    public double getStudent_GPA() {
        return student_GPA;
    }

    public String getStudent_Phone() {
        return student_Phone;
    }
}
