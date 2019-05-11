package com.example.task3.Activites;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.task3.Adapters.RecyclerViewAdapter;
import com.example.task3.Database.Model;
import com.example.task3.R;
import com.example.task3.ViewModel.StudentsListViewModel;

import java.util.ArrayList;
import java.util.List;

public class ViewStudentsActivity extends AppCompatActivity implements View.OnLongClickListener {
    private StudentsListViewModel viewModel;
    private RecyclerViewAdapter recyclerViewAdapter;
    private RecyclerView recyclerView;
    String KEY = "USERNAMEKEY";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_students);


        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);

        recyclerViewAdapter = new RecyclerViewAdapter(new ArrayList<Model>(), this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        recyclerView.setAdapter(recyclerViewAdapter);

        viewModel = ViewModelProviders.of(this).

                get(StudentsListViewModel.class);

        viewModel.getStudentList().

                observe(ViewStudentsActivity.this, new Observer<List<Model>>() {
                    @Override
                    public void onChanged(@Nullable List<Model> itemAndPeople) {
                        recyclerViewAdapter.addItems(itemAndPeople);
                    }
                });

    }

    @Override
    public boolean onLongClick(View v) {
        Model borrowModel = (Model) v.getTag();
        viewModel.deleteItem(borrowModel);
        return true;
    }
}
