package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.amplifyframework.AmplifyException;
import com.amplifyframework.api.aws.AWSApiPlugin;
import com.amplifyframework.api.graphql.model.ModelQuery;
import com.amplifyframework.auth.cognito.AWSCognitoAuthPlugin;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.datastore.generated.model.TaskModel;
import com.amplifyframework.datastore.generated.model.TaskModel;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


//        List<TaskModel> allTasks = new ArrayList<TaskModel>();
//        allTasks.add(new TaskModel("task1","resubmit the lab","new"));
//        allTasks.add(new TaskModel("task2","study","in progress"));
//        allTasks.add(new TaskModel("task3","do the dishes","in progress"));


//        RecyclerView recyclerView = findViewById(R.id.RecyclerView);
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//        recyclerView.setAdapter(new Adapter(allTasks));


        try {
            // Add these lines to add the AWSApiPlugin plugins
            Amplify.addPlugin(new AWSApiPlugin());
            Amplify.addPlugin(new AWSCognitoAuthPlugin());

            Amplify.configure(getApplicationContext());

            Log.i("MyAmplifyApp", "Initialized Amplify");
        } catch (AmplifyException error) {
            Log.e("MyAmplifyApp", "Could not initialize Amplify", error);
        }
        Amplify.Auth.signInWithWebUI(
                this,
                result -> Log.i("AuthQuickStart", result.toString()),
                error -> Log.e("AuthQuickStart", error.toString())
        );
        Button signOut=findViewById(R.id.logout);
        signOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Amplify.Auth.signOut(
                        () -> Log.i("AuthQuickstart", "Signed out successfully"),
                        error -> Log.e("AuthQuickstart", error.toString())
                );
                Intent intent = new Intent(MainActivity.this,SignupActivity.class);
                startActivity(intent);
            }
        });


//        Button singup = findViewById(R.id.singup);
//        singup.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Amplify.Auth.signInWithWebUI(
//                        MainActivity.this,
//                        result -> Log.i("AuthQuickStart", result.toString()),
//                        error -> Log.e("AuthQuickStart", error.toString())
//
//                );
//                Toast.makeText(MainActivity.this,"signing up",Toast.LENGTH_LONG).show();
//            }
//        });
//
//        Button logout = findViewById(R.id.logout);
//        logout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Amplify.Auth.signOut(
//                        () -> Log.i("AuthQuickstart", "Signed out successfully"),
//                        error -> Log.e("AuthQuickstart", error.toString())
//                );
//                Toast.makeText(MainActivity.this,"logged out",Toast.LENGTH_LONG).show();
//            }
//        });

        List<Task> taskModelsArray=new ArrayList<>();
        RecyclerView recyclerView=findViewById(R.id.RecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new Adapter(taskModelsArray));

        Amplify.Auth.fetchAuthSession(
                result -> Log.i("AmplifyQuickstart", result.toString()),
                error -> Log.e("AmplifyQuickstart", error.toString())
        );


        Handler handler = new Handler(Looper.myLooper(), new Handler.Callback() {
            @Override
            public boolean handleMessage(@NonNull Message message) {
                recyclerView.getAdapter().notifyDataSetChanged();
                return false;
            }
        });


        Amplify.API.query(
                ModelQuery.list(TaskModel.class),

                response -> {
                    for (TaskModel taskModel:response.getData()){
                        Task task=new Task(taskModel.getTitle(), taskModel.getBody(), taskModel.getState());
                        Log.i("hereisthetitle", taskModel.getTitle());

                        taskModelsArray.add(task);
                    }
                    handler.sendEmptyMessage(1);
                },
                error -> Log.e("MyAmplifyApp", error.toString(), error)
        );



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
        TextView userNameHolder = findViewById(R.id.loginUser);
        userNameHolder.setText(userName);
    }

}




