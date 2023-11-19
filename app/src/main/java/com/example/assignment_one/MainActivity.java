package com.example.assignment_one;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import com.google.android.material.snackbar.Snackbar;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class MainActivity extends AppCompatActivity {
    private SharedPreferences prefs;
    private SharedPreferences.Editor editor;
    public static final String TASKS = "TASKS";


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setUpPrefs();


    }

    private void setUpPrefs() {
        Context context = this;
         prefs = context.getSharedPreferences(
                getString(R.string.Tasks), Context.MODE_PRIVATE);
        editor = prefs.edit();
    }

    public void Save(View view) {
        EditText AddNewTask = findViewById(R.id.addtask);

        ArrayList<String> Tasks = new ArrayList<>();
        Gson gson = new Gson();
        if(!prefs.contains(TASKS)){
            Tasks.add(AddNewTask.getText().toString());
            editor.putString(TASKS,gson.toJson(Tasks));
        }else{
            String jason =   prefs.getString(TASKS,null);
            Tasks=gson.fromJson(jason,Tasks.getClass());
            Tasks.add(AddNewTask.getText().toString());
            editor.putString(TASKS,gson.toJson(Tasks));
        }

        editor.commit();
        AddNewTask.setText("");
        Snackbar message = Snackbar.make(view,"Your Task Was Saved",Snackbar.LENGTH_LONG);
        message.show();

    }
    public void Move(View view) {
        Intent Nav = new Intent(this,MainActivity2.class);
        startActivity(Nav);
    }
}