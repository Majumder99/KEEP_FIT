package com.example.keep_fit;



import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@androidx.room.Dao
public interface QueryDB {

    /**
     * inserting data
     * @param MainClass
     */
    @Insert
    void InsertData(MainClass MainClass);

    /**
     * deleting data
     * @param MainClass
     */
    @Delete
    void DeleteData(MainClass MainClass);

    /**
     * updating data
     * @param MainClass
     */
    @Update
    void UpdateData(MainClass MainClass);


    /**
     * query for selecting data form main_table
     * @param id
     * @return
     */
    @Query("SELECT * FROM Main_Table WHERE di = :id")
    MainClass getData(String id);


    /**
     * query for select all from main_table
     * @return
     */
    @Query("SELECT * FROM Main_Table")
    List<MainClass> getAllData();



}
