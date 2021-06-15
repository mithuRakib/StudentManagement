package com.example.studentmanagement.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface StudentDao {
    @Query("SELECT * FROM student_table")
    LiveData<List<Student>> getAllStudents();

    @Insert
    void insertStudent(Student student);

    @Query("SELECT * FROM student_table LIMIT 1")
    LiveData<Student> getFirstStudent();

    @Delete
    void deleteFirstStudent(Student student);

    @Query("DELETE FROM student_table")
    void deleteAllStudents();
}
