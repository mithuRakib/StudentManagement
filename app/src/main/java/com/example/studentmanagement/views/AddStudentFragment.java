package com.example.studentmanagement.views;

import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.studentmanagement.R;
import com.example.studentmanagement.database.Student;
import com.example.studentmanagement.tasksExecutors.BackgroundTasksExecutor;

public class AddStudentFragment extends Fragment {
    EditText stFName;
    EditText stLName;
    EditText stMajor;
    EditText stGPA;
    EditText stPhone;

    Button saveButton;
    Button cancelButton;

    private StudentViewModel studentViewModel;
    private Student student;

    public AddStudentFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add_student, container, false);
        stFName = view.findViewById(R.id.stFName);
        stLName = view.findViewById(R.id.stLName);
        stMajor = view.findViewById(R.id.stMajor);
        stGPA = view.findViewById(R.id.stGPA);
        stPhone = view.findViewById(R.id.stPhone);

        saveButton = view.findViewById(R.id.saveButton);
        cancelButton = view.findViewById(R.id.cancelButton);

        studentViewModel = new ViewModelProvider(this).get(StudentViewModel.class);


        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String fName = stFName.getText().toString();
                String lName = stLName.getText().toString();
                String major = stMajor.getText().toString();
                String phone = stPhone.getText().toString();

                if (!fName.isEmpty() && fName.length() > 2 &&
                        !lName.isEmpty() && lName.length() > 2 &&
                        !major.isEmpty() && major.length() > 2 &&
                        stGPA.getText().length() > 0
                ) {
                    double gpa = Double.valueOf(stGPA.getText().toString());
                    student = new Student(fName, lName, major, gpa, phone);
                    BackgroundTasksExecutor.getInstance().getDiskIO().execute(new Runnable() {
                        @Override
                        public void run() {
                            studentViewModel.insertStudent(student);
                        }
                    });
                    Toast.makeText(view.getContext(), "New Student Has Been Added", Toast.LENGTH_LONG).show();
                    Navigation.findNavController(view).popBackStack();
                } else {
                    stFName.setHintTextColor(Color.rgb(255, 0, 0));
                    stLName.setHintTextColor(Color.rgb(255, 0, 0));
                    stMajor.setHintTextColor(Color.rgb(255, 0, 0));
                    stGPA.setHintTextColor(Color.rgb(255, 0, 0));
                    stPhone.setHintTextColor(Color.rgb(0, 255, 0));

                    stFName.setHint("*First Name Required!");
                    stLName.setHint("*Last Name Required!");
                    stMajor.setHint("*Major Required!");
                    stGPA.setHint("*GPA Name Required!");
                    stPhone.setHint("Phone No# Optional");
                }
            }
        });

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(view.getContext(), "The Operation Has Been Cancelled", Toast.LENGTH_LONG).show();
                Navigation.findNavController(view).popBackStack();
            }
        });



        return view;
    }
}