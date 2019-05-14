package com.example.task3.Activites;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.task3.R;

import java.util.Objects;

public class DashboardActivity extends AppCompatActivity implements View.OnClickListener {
    Button insertButton, viewButton, editButton;
    TextView welcomeText;
    String KEY = "USERNAMEKEY";
    String KEYP = "USERKEY";
    Intent startIntent;
    String id;

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        Intent intent = this.getIntent();

        insertButton = findViewById(R.id.insert_button);
        viewButton = findViewById(R.id.view_button);
        editButton = findViewById(R.id.edit_button);
        welcomeText = findViewById(R.id.welcome_text);
        insertButton.setOnClickListener(this);
        viewButton.setOnClickListener(this);
        editButton.setOnClickListener(this);
        String user = Objects.requireNonNull(intent.getExtras()).getString(KEY);
        welcomeText.setText("Welcome back " + user);
        id = intent.getExtras().getString(KEYP);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.insert_button:
                startIntent = new Intent(this, InsertStudentsActivity.class);
                startIntent.putExtra(KEYP, id);
                startActivity(startIntent);
                break;

            case R.id.edit_button:
                startIntent = new Intent(this, EditStudentsActivity.class);
                startIntent.putExtra(KEYP, id);

                startActivity(startIntent);

                break;

            case R.id.view_button:
                startIntent = new Intent(this, ViewStudentsActivity.class);
                startIntent.putExtra(KEYP, id);

                startActivity(startIntent);

                break;

            default:
                break;
        }
    }
}
