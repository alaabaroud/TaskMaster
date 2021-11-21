package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class TaskDetails extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_details);

//        String TextTitle = getIntent().getStringExtra("detail");
//        TextView titleHolder= findViewById(R.id.detail);
//        titleHolder.setText(TextTitle);
        DataBase db  = DataBase.getDataBaseObj(this.getApplicationContext());
        TaskDAO taskDao = db.taskDao();
        Task task = taskDao.findTaskByUid(getIntent().getExtras().getInt("detail1"));
        TextView titleholder5 = findViewById(R.id.detail);
        titleholder5.setText(task.getTitle());

        TextView titleholder2 = findViewById(R.id.DetailsBody);
        titleholder2.setText(task.getBody());

        TextView titleholder3 = findViewById(R.id.DetailsStatus);
        titleholder3.setText(task.getState());
    }
}