package com.example.studentmanagement.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Entity;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Student.class}, version = 1, exportSchema = false)
public abstract class StudentDatabase extends RoomDatabase {
    private static final String DB_NAME = "student_db";
    private static final Object LOCK = new Object();
    private static StudentDatabase studentDatabaseInstance;

    public abstract StudentDao studentDao();

    public static StudentDatabase getInstance(Context context) {
        if (studentDatabaseInstance == null) {
            synchronized (LOCK) {
                studentDatabaseInstance = Room.databaseBuilder(
                        context.getApplicationContext(),
                        StudentDatabase.class,
                        DB_NAME
                ).allowMainThreadQueries().build();
            }
        }
        return studentDatabaseInstance;
    }
}
