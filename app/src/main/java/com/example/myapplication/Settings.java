package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Settings extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        Button saveButton = findViewById(R.id.save);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText username = findViewById(R.id.username);
                String getUserName = username.getText().toString();
                SharedPreferences settingShare= PreferenceManager.getDefaultSharedPreferences(Settings.this);
                settingShare.edit().putString("getUserName",getUserName).apply();
                Toast.makeText(Settings.this,"submitted!", Toast.LENGTH_LONG).show();


            }
        });

    }
}