package com.example.keep_fit;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

/**
 * Model class for database
 * Getter and setter functions
 */

@Entity(tableName = "Main_Table")
public class MainClass implements Serializable {
    public MainClass() {
    }

    @PrimaryKey
    private long di;
    private String date;
    private String timestamp;
    private int Systolic;
    private int Diastolic;
    private int heartRate;
    private String Comment;

    /**
     * setting the data, time, systolic, diastolic, heart rate, comment value to the database
     * @param date
     * @param timestamp
     * @param systolic
     * @param diastolic
     * @param heartRate
     * @param comment
     */
    public MainClass(String date, String timestamp, int systolic, int diastolic, int heartRate, String comment) {
        this.date = date;
        this.timestamp = timestamp;
        Systolic = systolic;
        Diastolic = diastolic;
        this.heartRate = heartRate;
        Comment = comment;
    }

    /**
     * returning primary key named di
     * @return
     */
    public long getDi() {
        return di;
    }

    /**
     * setting the primary key named di
     * @param di
     */
    public void setDi(long di) {
        this.di = di;
    }

    /**
     * get all the data from database
     * @return
     */
    public String getDate() {
        return date;
    }

    /**
     * get the time value from database
     * @return
     */
    public String getTimestamp() {
        return timestamp;
    }

    /**
     * setting the date value to database
     * @param date
     */
    public void setDate(String date) {
        this.date = date;
    }

    /**
     * setting the time value to the database
     * @param timestamp
     */
    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    /**
     * setting the systolic value into the database
     * @param systolic
     */
    public void setSystolic(int systolic) {
        Systolic = systolic;
    }

    /**
     * setting the diastolic value into the database
     * @param diastolic
     */
    public void setDiastolic(int diastolic) {
        Diastolic = diastolic;
    }

    /**
     * setting the heart rate value into the database
     * @param heartRate
     */
    public void setHeartRate(int heartRate) {
        this.heartRate = heartRate;
    }

    /**
     * setting the comment to the database
     * @param comment
     */
    public void setComment(String comment) {
        Comment = comment;
    }

    /**
     * get the systolic value form the database
     * @return
     */
    public int getSystolic() {
        return Systolic;
    }

    /**
     * get the diastolic value from the database
     * @return
     */
    public int getDiastolic() {
        return Diastolic;
    }

    /**
     * get the heart rate from the database
     * @return
     */
    public int getHeartRate() {
        return heartRate;
    }

    /**
     * get the comment from the database
     * @return
     */
    public String getComment() {
        return Comment;
    }

}
