package com.example.task3.Database;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity
public class CollegeModel {
    @PrimaryKey
    @ColumnInfo(name = "id")
    private int collegeId;


    @NonNull
    public String getCollegeName() {
        return collegeName;
    }

    public void setCollegeName(@NonNull String collegeName) {
        this.collegeName = collegeName;
    }

    @NonNull
    @ColumnInfo(name = "name")
    private String collegeName;

    public CollegeModel(@NonNull String collegeName, int collegeId) {
        this.collegeName = collegeName;
        this.collegeId = collegeId;
    }

    public int getCollegeId() {
        return collegeId;
    }

    public void setCollegeId(int collegeId) {
        this.collegeId = collegeId;
    }
}
