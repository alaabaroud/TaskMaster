package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class TaskDetails extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_details);

        String TextTitle = getIntent().getStringExtra("detail");
        TextView titleHolder= findViewById(R.id.detail);
        titleHolder.setText(TextTitle);


        
    }
}