package com.example.task3.ViewModel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import com.example.task3.Database.Database;
import com.example.task3.Database.Model;

import java.util.List;

public class StudentsListViewModel extends AndroidViewModel {

    private final LiveData<List<Model>> studentList;

    private Database appDatabase;

    public StudentsListViewModel(Application application) {
        super(application);

        appDatabase = Database.getDatabase(this.getApplication());

        studentList = appDatabase.getDao().getAllStudents();
    }


    public LiveData<List<Model>> getStudentList() {
        return studentList;
    }

    public void deleteItem(Model borrowModel) {
        new deleteAsyncTask(appDatabase).execute(borrowModel);
    }

    private static class deleteAsyncTask extends AsyncTask<Model, Void, Void> {

        private Database db;

        deleteAsyncTask(Database appDatabase) {
            db = appDatabase;
        }

        @Override
        protected Void doInBackground(final Model... params) {
            db.getDao().deleteStudent(params[0]);
            return null;
        }

    }

}