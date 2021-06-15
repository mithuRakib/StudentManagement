package com.example.studentmanagement.views;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.studentmanagement.database.Student;
import com.example.studentmanagement.database.StudentRepository;

import java.util.List;

public class StudentViewModel extends AndroidViewModel {
    private StudentRepository studentRepository;
    private LiveData<List<Student>> allStudents;
    private LiveData<Student> firstStudent;

    public StudentViewModel(@NonNull Application application) {
        super(application);

        studentRepository = new StudentRepository(application);
        allStudents = studentRepository.getLiveStudentData();
        firstStudent = studentRepository.getFirstStudent();
    }

    public LiveData<List<Student>> getAllStudents() {
        return allStudents;
    }

    public void insertStudent(Student student) {
        studentRepository.insertStd(student);
    }

    public LiveData<Student> getFirstStudent() {
        return firstStudent;
    }

    public void deleteFirstStudent(Student student) {
        studentRepository.deleteFirstStd(student);
    }

    public void deleteAllStudents() {
        studentRepository.deleteAllStd();
    }
}
