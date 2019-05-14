package com.example.task3.Adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.task3.Database.Model;
import com.example.task3.R;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.RecyclerViewHolder> {

    private List<Model> borrowModelList;
    private View.OnLongClickListener longClickListener;

    public RecyclerViewAdapter(List<Model> studentModelList, View.OnLongClickListener longClickListener) {
        this.borrowModelList = studentModelList;
        this.longClickListener = longClickListener;
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new RecyclerViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerViewHolder holder, int position) {
        Model studentModel = borrowModelList.get(position);
        holder.nameTextView.setText(studentModel.getName());
        holder.idTextView.setText(String.valueOf(studentModel.id));
        holder.gpaTextView.setText(Double.toString(studentModel.getGpa()));
        holder.collegeTextView.setText(studentModel.getCollege());
        if (String.valueOf(studentModel.isGender()).equals(1)) {

            holder.genderTextView.setText("Male");

        } else {
            holder.genderTextView.setText("Female");


        }
        holder.collegeTextView.setText(studentModel.getCollege());
        holder.itemView.setTag(studentModel);
        holder.itemView.setOnLongClickListener(longClickListener);
    }

    @Override
    public int getItemCount() {
        return borrowModelList.size();
    }

    public void addItems(List<Model> studentModelList) {
        this.borrowModelList = studentModelList;
        notifyDataSetChanged();
    }

    static class RecyclerViewHolder extends RecyclerView.ViewHolder {
        private TextView nameTextView;
        private TextView gpaTextView;
        private TextView genderTextView;
        private TextView collegeTextView;
        private TextView idTextView;

        RecyclerViewHolder(View view) {
            super(view);
            nameTextView = view.findViewById(R.id.student_name);
            idTextView = view.findViewById(R.id.student_id);
            genderTextView = view.findViewById(R.id.student_gender);
            collegeTextView = view.findViewById(R.id.student_college);
            gpaTextView = view.findViewById(R.id.student_gpa);


        }
    }
}