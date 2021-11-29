package com.example.myapplication;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
//import androidx.room.Room;

import com.amplifyframework.api.graphql.model.ModelMutation;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.datastore.generated.model.TaskModel;

public class addTask extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);

//        TextView showSubmission=findViewById(R.id.submitted);
//        showSubmission.setText("Submission");





        EditText titleName=findViewById(R.id.titleName);
        EditText bodyName=findViewById(R.id.bodyName);
        EditText statusName=findViewById(R.id.statusName);


        Button showSubmission=findViewById(R.id.addTask);
        showSubmission.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                String getTitle=titleName.getText().toString();
                String getBody=bodyName.getText().toString();
                String getStatus=statusName.getText().toString();




                Intent intent = new Intent(addTask.this, MainActivity.class);


//                    TaskModel1 taskModel = new TaskModel1(getTitle, getBody, getStatus);

                try {
                    TaskModel taskModel=TaskModel.builder().title(getTitle).body(getBody).state(getStatus).build();

                    Amplify.API.mutate(
                            ModelMutation.create(taskModel),
                            response -> Log.i("MyAmplifyApp", "Added Task with id: " + response.getData().getId()),
                            error -> Log.e("MyAmplifyApp", "Create failed", error)
                    );

                }
                catch(NullPointerException nullPointerException){
                    System.out.println("this is an error");

                }
                Toast.makeText(getApplicationContext(), "submitted!", Toast.LENGTH_LONG).show();

                startActivity(intent);





            }


        });
    }

    
}