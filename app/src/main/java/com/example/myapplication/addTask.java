package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class addTask extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);
        // button3
        DataBase db  = DataBase.getDataBaseObj(this.getApplicationContext());
        TaskDAO taskDao = db.taskDao();
        Button Button3 = findViewById(R.id.button3);
        Button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText inputTitle=findViewById(R.id.addTitle);
                EditText inputBody=findViewById(R.id.addTitle);
                EditText inputState=findViewById(R.id.allStates);


                String getinputTitle = inputTitle.getText().toString();
                String getinputBody = inputBody.getText().toString();
                String getinputState = inputState.getText().toString();

                taskDao.insertAll(new Task(getinputTitle,getinputBody,getinputState));

                Toast.makeText(getApplicationContext(), "submitted", Toast.LENGTH_LONG).show();

                finish();


            }
        });
    }
}