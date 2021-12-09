package com.example.myapplication;


import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
//import androidx.room.Room;

import com.amplifyframework.api.graphql.model.ModelMutation;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.datastore.generated.model.TaskModel;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class addTask extends AppCompatActivity {

    Uri path;

    String fileName;

    FusedLocationProviderClient fusedLocationProviderClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);



        Intent intent = getIntent();
        String action = intent.getAction();
        String type = intent.getType();
        if (Intent.ACTION_SEND.equals(action) && type != null) {
            if (type.startsWith("image/")) {
                handleSendImage(intent); // Handle single image being sent
            }
        }




        EditText titleName = findViewById(R.id.titleName);
        EditText bodyName = findViewById(R.id.bodyName);
        EditText statusName = findViewById(R.id.statusName);


        Button showSubmission = findViewById(R.id.addTask);
        showSubmission.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                String getTitle = titleName.getText().toString();
                String getBody = bodyName.getText().toString();
                String getStatus = statusName.getText().toString();


                Intent intent = new Intent(addTask.this, MainActivity.class);


//                    TaskModel1 taskModel = new TaskModel1(getTitle, getBody, getStatus);

                try {
                    TaskModel taskModel = TaskModel.builder().title(getTitle).body(getBody).state(getStatus).build();

                    Amplify.API.mutate(
                            ModelMutation.create(taskModel),
                            response -> Log.i("MyAmplifyApp", "Added Task with id: " + response.getData().getId()),
                            error -> Log.e("MyAmplifyApp", "Create failed", error)
                    );

                } catch (NullPointerException nullPointerException) {
                    System.out.println("this is an error");

                }
                Toast.makeText(getApplicationContext(), "submitted!", Toast.LENGTH_LONG).show();

                startActivity(intent);


            }
        });
    }
    public void handleSendImage(Intent intent) {
        Uri imageUri = (Uri) intent.getParcelableExtra(Intent.EXTRA_STREAM);
        if (imageUri != null) {
            File files = new File(imageUri.getPath());
            fileName = files.getName();
            Toast.makeText(getApplicationContext(),imageUri.getPath(),Toast.LENGTH_SHORT).show();
        }
    }

}