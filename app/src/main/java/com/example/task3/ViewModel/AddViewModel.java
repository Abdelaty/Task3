package com.example.task3.ViewModel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

import com.example.task3.Database.Model;
import com.example.task3.Database.StudentDatabase;

public class AddViewModel extends AndroidViewModel {

    private static StudentDatabase appStudentsDatabase;

    public AddViewModel(@NonNull Application application) {
        super(application);

        appStudentsDatabase = StudentDatabase.getDatabase(this.getApplication());

    }

    public static void addStudent(final Model model) {
        new addAsyncTask(appStudentsDatabase).execute(model);
    }

    private static class addAsyncTask extends AsyncTask<Model, Void, Void> {

        private StudentDatabase db;

        addAsyncTask(StudentDatabase appDatabase) {
            db = appDatabase;
        }

        @Override
        protected Void doInBackground(final Model... params) {
            try {
                db.getDao().addStudent(params[0]);
            } catch (Exception c) {

            }
            return null;
        }

    }
}