package com.example.task3.Activites;

import android.arch.persistence.room.Room;
import android.content.Intent;
import android.database.sqlite.SQLiteConstraintException;
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
import com.example.task3.Database.Database;
import com.example.task3.R;

public class MainActivity extends AppCompatActivity {


    Button loginButton;
    EditText userId, userPass;
    String KEY = "USERNAMEKEY";
    String KEYP = "USERKEY";
    Boolean b = false;

    Database db;
    CollegeDB collegeDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
//        final Intent intent = new Intent(this, DashboardActivity.class);


        db = Room.databaseBuilder(getApplicationContext(), Database.class, "students_db")
                .build();


        collegeDB = Room.databaseBuilder(getApplicationContext(), CollegeDB.class, "college_db").build();
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                CollegeModel collegeModel = new CollegeModel( "TT");
                CollegeModel collegeMode2 = new CollegeModel( "Media");
                collegeDB.getCollegeDao().add(collegeModel);
                collegeDB.getCollegeDao().add(collegeMode2);
//                Log.v("College", collegeDB.getCollegeDao().getAllColleges().get(0).getCollegeName());
            }
        });


        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (userId.getText().toString().equals("Admin") && userPass.getText().toString().equals("Admin")) {
                    Intent intent2 = new Intent(getApplication(), DashboardActivity.class);
                    intent2.putExtra(KEY, "Admin");
                    startActivity(intent2);
                } else if (checkTask()) {
                    AsyncTask.execute(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                Boolean authenticated;
                                {
                                    runOnUiThread(new Runnable() {
                                        public void run() {
                                            String userName;
                                            userName = db.getDao().getName(Integer.parseInt(userId.getText().toString())).toString();
                                            Intent intent1 = new Intent(getApplication(), DashboardActivity.class);
                                            intent1.putExtra(KEYP, userId.getText());
                                            intent1.putExtra(KEY, userName);
                                            startActivity(intent1);
                                        }
                                    });
                                }
                            } catch (SQLiteConstraintException ex) {
                                runOnUiThread(new Runnable() {
                                    public void run() {
                                        Toast.makeText(getApplicationContext(), "No user in database", Toast.LENGTH_LONG).show();
                                    }
                                });
                            }
                        }
                    });
                } else {

                    Toast.makeText(getApplicationContext(), "No user in database", Toast.LENGTH_LONG).show();

                }
            }
        });
    }

    private boolean checkTask() {
        AsyncTask.execute(new Runnable() {

            @Override
            public void run() {
                try {
                    b = db.getDao().checkAuth(Integer.parseInt(userId.getText().toString()), userPass.getText().toString());
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
}
