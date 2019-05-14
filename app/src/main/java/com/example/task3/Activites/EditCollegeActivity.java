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
import android.widget.Spinner;
import android.widget.Toast;

import com.example.task3.Database.CollegeDB;
import com.example.task3.Database.CollegeModel;
import com.example.task3.R;
import com.example.task3.ViewModel.EditCollegesViewModel;

import java.util.ArrayList;
import java.util.List;

public class EditCollegeActivity extends AppCompatActivity {
    EditCollegesViewModel updateModelView;
    CollegeDB collegeDB;
    Button collegeSubmit;
    List<String> list = new ArrayList<String>();
    Spinner spinner;
    EditText collegeEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_college);
        intit();
        updateModelView = ViewModelProviders.of(this).get(EditCollegesViewModel.class);
        collegeDB = Room.databaseBuilder(getApplicationContext(), CollegeDB.class, "college_db").build();
        String collegeName = collegeEditText.getText().toString();
        int spinnerId = spinner.getSelectedItemPosition();
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
        collegeSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    AsyncTask.execute(new Runnable() {
                        @Override
                        public void run() {
                            CollegeModel collegeModel = new CollegeModel(collegeEditText.getText().toString(), spinner.getSelectedItemPosition());
//                            collegeDB.getCollegeDao().update(collegeEditText.getText().toString(), spinner.getSelectedItemPosition());
                            collegeDB.getCollegeDao().update(collegeModel);
                            Log.v("fuck", collegeDB.getCollegeDao().getAllColleges().get(0));
                            Log.v("fuck", String.valueOf(spinner.getSelectedItemPosition()));
                            Toast.makeText(getApplicationContext(), "College Name Updated Successfully", Toast.LENGTH_LONG).show();
//                            Toasty.success(yourContext, "College Name Updated Successfully!", Toast.LENGTH_SHORT, true).show();

                        }
                    });

                } catch (Exception e) {
                    Log.v("Update College", e.getMessage() + e.getLocalizedMessage());
                }

            }
        });
    }

    void intit() {

        spinner = findViewById(R.id.college_spinner);
        collegeSubmit = findViewById(R.id.change_button);
        collegeEditText = findViewById(R.id.college_name);
    }

}
