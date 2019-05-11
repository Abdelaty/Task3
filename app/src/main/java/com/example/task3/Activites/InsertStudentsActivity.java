package com.example.task3.Activites;


import android.arch.lifecycle.ViewModelProviders;
import android.arch.persistence.room.Room;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.task3.Database.CollegeDB;
import com.example.task3.Database.Database;
import com.example.task3.Database.Model;
import com.example.task3.R;
import com.example.task3.ViewModel.AddViewModel;

import java.util.ArrayList;
import java.util.List;

public class InsertStudentsActivity extends AppCompatActivity {
    EditText name, id, gpa, password;
    Spinner spinner;
    Button submit;
    int gender;
    private RadioGroup genderBox;
    RadioButton male, female;
    CollegeDB collegeDB;
    List<String> list = new ArrayList<String>();
    Database database;
    AddViewModel addViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_students);
        init();
        addViewModel = ViewModelProviders.of(this).get(AddViewModel.class);

        collegeDB = Room.databaseBuilder(getApplicationContext(), CollegeDB.class, "college_db").build();
        database = Room.databaseBuilder(getApplicationContext(), Database.class, "students_db").build();

        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {

                ArrayAdapter<String> dataAdapter;
                list = collegeDB.getCollegeDao().getAllColleges();
                dataAdapter = new ArrayAdapter<String>(getApplicationContext(),
                        android.R.layout.simple_spinner_item, list);
                dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinner.setAdapter(dataAdapter);
            }
        });
        genderBox.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.male:
                        gender = 1;
                        break;
                    case R.id.female:
                        gender = 2;
                        break;
                }
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Log.v("Adding Student", String.valueOf(Integer.parseInt(String.valueOf(id.getText()))));
                    Log.v("Adding Student", name.getText().toString());
                    Log.v("Adding Student", spinner.getSelectedItem().toString());
                    Log.v("Adding Student", Integer.toString(gender));
                    Log.v("Adding Student", password.getText().toString());
                    Log.v("Adding Student", String.valueOf(Double.parseDouble(gpa.getText().toString())));
                    AddViewModel.addStudent(new Model(
                            Integer.parseInt(String.valueOf(id.getText())),
                            name.getText().toString(),
                            spinner.getSelectedItem().toString(),
                            gender,
                            password.getText().toString(),
                            Double.parseDouble(gpa.getText().toString())

                    ));
                    Toast.makeText(getApplicationContext(), "The student added successfully", Toast.LENGTH_LONG).show();

                    finish();
                } catch (Exception e) {
                    Log.v("Adding Student", e.getMessage().toString() + e.getLocalizedMessage());
                }

            }
        });
    }

    void init() {
        name = findViewById(R.id.student_name);
        submit = findViewById(R.id.submit);
        id = findViewById(R.id.student_id);
        gpa = findViewById(R.id.student_gpa);
        spinner = findViewById(R.id.student_college);
        genderBox = findViewById(R.id.student_gender);
        male = findViewById(R.id.male);
        female = findViewById(R.id.female);
        password = findViewById(R.id.student_password);
    }
}
