package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class TaskDetails extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_details);

        DataBase db  = DataBase.getDataBaseObj(this.getApplicationContext());
        TaskDAO taskDao = db.taskDao();
        Task task = taskDao.findTaskByUid(getIntent().getExtras().getInt("Task1"));
        TextView Title = findViewById(R.id.detail);
        Title.setText(task.getTitle());

        TextView Body = findViewById(R.id.DetailsBody);
        Body.setText(task.getBody());

        TextView Status = findViewById(R.id.DetailsStatus);
        Status.setText(task.getState());
    }
}