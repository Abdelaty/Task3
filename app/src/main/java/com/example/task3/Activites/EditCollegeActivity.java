package com.example.task3.Activites;

import android.arch.lifecycle.ViewModelProviders;
import android.arch.persistence.room.Room;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.task3.Database.CollegeDB;
import com.example.task3.Database.CollegeModel;
import com.example.task3.R;
import com.example.task3.ViewModel.EditCollegesViewModel;

public class EditCollegeActivity extends AppCompatActivity {
    EditCollegesViewModel updateModelView;
    CollegeDB collegeDB;
    Button collegeSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_students);
        updateModelView = ViewModelProviders.of(this).get(EditCollegesViewModel.class);
        collegeDB = Room.databaseBuilder(getApplicationContext(), CollegeDB.class, "college_db").build();
        collegeSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    CollegeModel collegeModel = new CollegeModel("");
                    collegeDB.getCollegeDao().update(collegeModel);

                } catch (Exception e) {
                    Log.v("Adding Student", e.getMessage() + e.getLocalizedMessage());
                }

            }
        });
    }

}
