package com.example.task3.ViewModel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

import com.example.task3.Database.CollegeDB;
import com.example.task3.Database.CollegeModel;

public class EditCollegesViewModel extends AndroidViewModel {

    private static CollegeDB appStudentsDatabase;

    public EditCollegesViewModel(@NonNull Application application) {
        super(application);

        appStudentsDatabase = CollegeDB.getDatabase(this.getApplication());

    }

    public static void editStudent(final CollegeModel model) {
        new EditCollegesViewModel.addAsyncTask(appStudentsDatabase).execute(model);
    }

    private static class addAsyncTask extends AsyncTask<CollegeModel, Void, Void> {

        private CollegeDB db;

        addAsyncTask(CollegeDB appDatabase) {
            db = appDatabase;
        }

        @Override
        protected Void doInBackground(final CollegeModel... params) {
            try {
                db.getCollegeDao().update(params[0]);
            } catch (Exception c) {

            }
            return null;
        }

    }
}