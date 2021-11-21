package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.net.CookieHandler;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements Adapter.OnNoteListener {
    private RecyclerView mRecyclerView;
    private ArrayList<Task> tasksList = new ArrayList<>();
    private Adapter taskAdapterObj;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button addTask = findViewById(R.id.AddButton);
        addTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToAddTask = new Intent(MainActivity.this, addTask.class);
                startActivity(goToAddTask);
            }
        });


        Button allTask = findViewById(R.id.allButton);
        allTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToallTask = new Intent(MainActivity.this, AllTasks.class);
                startActivity(goToallTask);
            }
        });



        Button setting = findViewById(R.id.setting);
        setting.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick (View v){
//                Toast.makeText(getApplicationContext(), "Button Clicked", Toast.LENGTH_LONG).show();
                Intent goToSetting = new Intent(MainActivity.this, Settings.class);
//                goToDetail3.putExtra("detail", "detail3");
                startActivity(goToSetting);
            }
        });



    }

    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);
        String userName = sharedPreferences.getString("getUserName","the user didn't add a name yet!");
//        Toast.makeText(this, userName,Toast.LENGTH_LONG).show();
        TextView userNameHolder = findViewById(R.id.userNameLable);
        userNameHolder.setText(userName);


        List<Task> tasks ;
        DataBase db  = DataBase.getDataBaseObj(this.getApplicationContext());
        TaskDAO taskDao = db.taskDao();
        tasks = taskDao.getAll();

        RecyclerView recyclerView = findViewById(R.id.RecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new Adapter(tasks,this));
    }




    @Override
    public void onNoteClick(int position, Task task) {
        DataBase db  = DataBase.getDataBaseObj(this.getApplicationContext());
        TaskDAO taskDao = db.taskDao();
        Intent intent = new Intent(this, TaskDetails.class);


        taskDao.findTaskByUid(task.getId());
        intent.putExtra("detail1", taskDao.findTaskByUid(task.getId()).getId());




        startActivity(intent);
    }
}