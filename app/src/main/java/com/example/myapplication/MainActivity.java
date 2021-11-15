package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button AddButton = findViewById(R.id.AddButton);
        AddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "Submitted", Toast.LENGTH_LONG).show();

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



    }
}