package com.example.task3.Activites;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.arch.persistence.room.Room;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.task3.Database.CollegeDB;
import com.example.task3.Database.CollegeModel;
import com.example.task3.Database.Dao;
import com.example.task3.Database.StudentDatabase;
import com.example.task3.R;

import java.lang.ref.WeakReference;

public class MainActivity extends AppCompatActivity {


    Button loginButton;
    EditText userId, userPass;
    String KEY = "USERNAMEKEY";
    String KEYP = "USERKEY";
    Boolean b = false;

    @SuppressLint("StaticFieldLeak")
    static Context context;
    CollegeDB collegeDB;
    StudentDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        database = Room.databaseBuilder(getApplicationContext(), StudentDatabase.class, "student_db").allowMainThreadQueries().build();


        collegeDB = Room.databaseBuilder(getApplicationContext(), CollegeDB.class, "college_db").build();
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                CollegeModel collegeModel = new CollegeModel("IT");
                CollegeModel collegeMode2 = new CollegeModel("Media");
                collegeDB.getCollegeDao().add(collegeModel);
                collegeDB.getCollegeDao().add(collegeMode2);
                CollegeModel collegeMode3 = new CollegeModel("Engineering");
                CollegeModel collegeMode4 = new CollegeModel("Bio");
                collegeDB.getCollegeDao().add(collegeMode3);
                collegeDB.getCollegeDao().add(collegeMode4);
                Log.v("College", collegeDB.getCollegeDao().getAllColleges().get(1));
            }
        });


        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (userId.getText().toString().equals("Admin") && userPass.getText().toString().equals("Admin")) {
                    Intent intent2 = new Intent(getApplication(), DashboardActivity.class);
                    intent2.putExtra(KEY, "Admin");
                    startActivity(intent2);
                } else {
                    AsyncTask.execute(new Runnable() {
                        @Override
                        public void run() {
                            int mUserId = Integer.parseInt(userId.getText().toString());
                            String mUserPass = userPass.getText().toString();
                            Log.v("tag", "" + mUserId + mUserPass);
                            if (!(database.getDao().checkAuth(mUserId, mUserPass) == null)) {
                                String userName;
                                Log.v("tag", "we in");
                                userName = database.getDao().getName(Integer.parseInt(userId.getText().toString()));
                                Intent intent1 = new Intent(getApplication(), DashboardActivity.class);
                                intent1.putExtra(KEYP, userId.getText());
                                intent1.putExtra(KEY, userName);
                                startActivity(intent1);
                            }
                        }


                    });
                }

            }
        });
    }

    private boolean checkTask() {
        AsyncTask.execute(new Runnable() {

            @Override
            public void run() {
                try {
//                    b = db.getDao().checkAuth(Integer.parseInt(userId.getText().toString()), userPass.getText().toString());
                } catch (Exception r) {
                    toast();
                }
            }

        });

        return b;
    }


    void init() {
        loginButton = findViewById(R.id.button);
        userId = findViewById(R.id.student_id);
        userPass = findViewById(R.id.student_password);
        context = getApplicationContext();
    }

    void toast() {

        Handler mHandler = new Handler(Looper.getMainLooper()) {
            @Override
            public void handleMessage(Message message) {
                Toast.makeText(getApplicationContext(), "ID Must be numeric", Toast.LENGTH_LONG).show();
                // Your worker tells you in the message what to do.
            }
        };

    }

    private static class AgentAsyncTask extends AsyncTask<Void, Void, Integer> {

        //Prevent leak
        private WeakReference<Activity> weakActivity;
        private int asyncUserId;
        private String asyncUserPass;
//        private String license;

        public AgentAsyncTask(Activity activity, String password, int user) {
            weakActivity = new WeakReference<>(activity);
            this.asyncUserPass = password;
            this.asyncUserId = user;
        }

        @Override
        protected Integer doInBackground(Void... params) {
            Dao asyncDao = StudentDatabase.getDatabase(context).getDao();
            return 0;
//            asyncDao.checkAuth(asyncUserId, asyncUserPass);
        }

        @Override
        protected void onPostExecute(Integer agentsCount) {
            Activity activity = weakActivity.get();
            if (activity == null) {
                return;
            }

            if (agentsCount > 0) {
                //2: If it already exists then prompt user
                Toast.makeText(activity, "Agent already exists!", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(activity, "Agent does not exist! Hurray :)", Toast.LENGTH_LONG).show();
                activity.onBackPressed();
            }
        }
    }
}
