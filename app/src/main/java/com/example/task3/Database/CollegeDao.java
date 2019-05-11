package com.example.task3.Database;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

@android.arch.persistence.room.Dao
public interface CollegeDao {
    @Query("select * from CollegeModel")
    List<String> getAllColleges();
//
//    @Query("select college from Model")
//    List<Model> getAllColleges();

    @Insert(onConflict = REPLACE)
    void add(CollegeModel... collegeModels);
}
