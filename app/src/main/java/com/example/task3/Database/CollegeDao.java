package com.example.task3.Database;

import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

@android.arch.persistence.room.Dao
public interface CollegeDao {
    @Query("select * from CollegeModel")
    List<String> getAllColleges();

    @Insert(onConflict = REPLACE)
    void add(CollegeModel... collegeModels);

    @Update
    void update(CollegeModel... collegeModels);
//
//    @Query("update CollegeModel Set name=:newname Where name:oldname ")
//    void update(String newname,String oldname);
}
