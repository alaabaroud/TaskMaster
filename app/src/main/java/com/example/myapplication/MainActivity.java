package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Set;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button AddButton = findViewById(R.id.AddButton);
        AddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "submitted", Toast.LENGTH_LONG).show();

                Intent AddButtonIntent = new Intent( MainActivity.this, addTask.class);
               startActivity(AddButtonIntent);

            }
        });
        Button Button = findViewById(R.id.allButton);
        Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "submitted", Toast.LENGTH_LONG).show();

                Intent allButtonIntent = new Intent( MainActivity.this, AllTasks.class);
                startActivity(allButtonIntent);

            }
        });

        Button Task1 = findViewById(R.id.Task1);
        Task1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goToTask1 = new Intent(MainActivity.this, TaskDetails.class);
                goToTask1.putExtra("detail", "Task1");
                startActivity(goToTask1);
            }

        });


        Button Task2 = findViewById(R.id.Task2);
        Task2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goToTask2 = new Intent(MainActivity.this, TaskDetails.class);
                goToTask2.putExtra("detail", "Task2");
                startActivity(goToTask2);
            }
        });

        Button Task3 = findViewById(R.id.Task3);
        Task3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goToTask3 = new Intent(MainActivity.this, TaskDetails.class);
                goToTask3.putExtra("detail", "Task3");
                startActivity(goToTask3);
            }
        });

        Button setting = findViewById(R.id.setting);
        setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent Setting = new Intent(MainActivity.this, Settings.class);
            startActivity(Setting);

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
    }
}