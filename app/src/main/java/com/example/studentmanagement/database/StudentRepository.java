package com.example.studentmanagement.database;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.studentmanagement.tasksExecutors.BackgroundTasksExecutor;

import java.util.List;

public class StudentRepository {
    StudentDatabase studentDatabase;
    StudentDao studentDao;

    private LiveData<List<Student>> liveStudentData;
    private LiveData<Student> firstStudent;

    public StudentRepository(Application application) {
        studentDatabase = StudentDatabase.getInstance(application);
        studentDao = studentDatabase.studentDao();

        liveStudentData = studentDao.getAllStudents();
        firstStudent = studentDao.getFirstStudent();
    }

    public LiveData<List<Student>> getLiveStudentData() {
        return liveStudentData;
    }

    public void insertStd(Student student) {
        BackgroundTasksExecutor.getInstance().getDiskIO().execute(new Runnable() {
            @Override
            public void run() {
                studentDao.insertStudent(student);
            }
        });
    }

    public LiveData<Student> getFirstStudent() {
        return firstStudent;
    }

    public void deleteFirstStd(Student student) {
        studentDao.deleteFirstStudent(student);
    }

    public void deleteAllStd() {
        studentDao.deleteAllStudents();
    }
}
