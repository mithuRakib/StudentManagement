package com.example.studentmanagement.studentRecyclerView;

import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.studentmanagement.R;

public class StudentViewHolder extends RecyclerView.ViewHolder {
    TextView studentName;
    public StudentViewHolder(@NonNull View itemView) {
        super(itemView);
        studentName = itemView.findViewById(R.id.studentName);
    }
}
