package com.example.keep_fit;



import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@androidx.room.Dao
public interface QueryDB {

    @Insert
    void InsertData(MainClass MainClass);

    @Delete
    void DeleteData(MainClass MainClass);

    @Update
    void UpdateData(MainClass MainClass);


    @Query("SELECT * FROM Main_Table WHERE di = :id")
    MainClass getData(String id);


    @Query("SELECT * FROM Main_Table")
    List<MainClass> getAllData();



}
