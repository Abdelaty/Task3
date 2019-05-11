package com.example.task3.ViewModel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

import com.example.task3.Database.Database;
import com.example.task3.Database.Model;

public class AddViewModel extends AndroidViewModel {

    private static Database appStudentsDatabase;

    public AddViewModel(@NonNull Application application) {
        super(application);

        appStudentsDatabase = Database.getDatabase(this.getApplication());

    }

    public static void addStudent(final Model model) {
        new addAsyncTask(appStudentsDatabase).execute(model);
    }

    private static class addAsyncTask extends AsyncTask<Model, Void, Void> {

        private Database db;

        addAsyncTask(Database appDatabase) {
            db = appDatabase;
        }

        @Override
        protected Void doInBackground(final Model... params) {
            db.getDao().addStudent(params[0]);
            return null;
        }

    }
}