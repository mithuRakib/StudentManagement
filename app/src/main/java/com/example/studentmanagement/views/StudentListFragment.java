package com.example.studentmanagement.views;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.studentmanagement.R;
import com.example.studentmanagement.database.Student;
import com.example.studentmanagement.studentRecyclerView.StudentAdapter;

import java.util.ArrayList;
import java.util.List;

public class StudentListFragment extends Fragment {
    Button addStudent;
    Button deleteFirst;
    Button deleteAll;

    RecyclerView recyclerView;
    StudentAdapter adapter;

    private StudentViewModel studentViewModel;
    private Student firstStudent;

    public StudentListFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_student_list, container, false);
        addStudent = view.findViewById(R.id.addStudentButton);
        deleteFirst = view.findViewById(R.id.deleteFirstButton);
        deleteAll = view.findViewById(R.id.deleteAllButton);

        recyclerView = view.findViewById(R.id.studentRecyclerView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(view.getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);
        adapter = new StudentAdapter();

        studentViewModel = new ViewModelProvider(this).get(StudentViewModel.class);
        studentViewModel.getAllStudents().observe(getViewLifecycleOwner(), new Observer<List<Student>>() {
            @Override
            public void onChanged(List<Student> students) {
                if (students != null) {
                    adapter.setStudents(students);
                }
            }
        });

        recyclerView.setAdapter(adapter);

        addStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.action_studentListFragment_to_addStudentFragment);
            }
        });

        studentViewModel.getFirstStudent().observe(getViewLifecycleOwner(), new Observer<Student>() {
            @Override
            public void onChanged(Student student) {
                firstStudent = student;
            }
        });

        deleteFirst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                studentViewModel.deleteFirstStudent(firstStudent);
            }
        });

        deleteAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                studentViewModel.deleteAllStudents();
            }
        });

        return view;
    }
}