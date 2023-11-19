package com.example.assignment_one;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.gson.Gson;

import java.util.ArrayList;

public class MainActivity2 extends AppCompatActivity {
    private SharedPreferences prefs;
    public static final String TASKS = "TASKS";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        setUpPrefs();
        ListView TasksListView = findViewById(R.id.Tasks);
        Gson gson = new Gson();
        String jason =   prefs.getString(TASKS,null);
        ArrayList<String> readyTasks = new ArrayList<>();
         readyTasks = gson.fromJson(jason,readyTasks.getClass());
        ArrayAdapter<String> tasksfrompref = new ArrayAdapter<>(MainActivity2.this,R.layout.listviewtasks,readyTasks);
        TasksListView.setAdapter(tasksfrompref);
    }
    private void setUpPrefs() {
        Context context = this;
        prefs = context.getSharedPreferences(
                getString(R.string.Tasks), Context.MODE_PRIVATE);

    }
}