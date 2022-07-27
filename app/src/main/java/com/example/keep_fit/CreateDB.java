package com.example.keep_fit;


import android.content.Context;
import android.os.AsyncTask;
import android.service.autofill.UserData;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

/**
 * Creating room database
 */

@Database(entities = {MainClass.class}, version = 1)
public abstract class CreateDB extends RoomDatabase {

    /**
     * below line is to create instance for our database class.
     */
    private static CreateDB instance;

    /**
     * below line is to create abstract variable for dao.
     * @return
     */
    public abstract QueryDB Dao();

    /**
     * on below line we are getting instance for our database.
     * @param context
     * @return
     */
    public static synchronized CreateDB getInstance(Context context) {
        /**
         * on below line we are getting instance for our database.
         */
        if (instance == null) {
            /**
             * if the instance is null we are creating a new instance
             */
            instance =
                    /**
                     * for creating a instance for our database
                     * we are creating a database builder and passing
                     * our database class with our database name.
                     */
                    Room.databaseBuilder(context.getApplicationContext(),
                            CreateDB.class, "Main_Table")
                            /**
                             * below line is use to add fall back to
                             * destructive migration to our database.
                             */
                            .fallbackToDestructiveMigration()
                            .allowMainThreadQueries()
                            /**
                             * below line is to add callback to our database.
                             */
                            .addCallback(roomCallback)
                            /**
                             * below line is to build our database.
                             */
                            .build();
        }

        return instance;
    }

    /**
     * callback function
     */
    private static Callback roomCallback = new Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);

            new PopulateDbAsyncTask(instance).execute();
        }
    };

    /**
     * we are creating an async task class to perform task in background.
     */
    private static class PopulateDbAsyncTask extends AsyncTask<Void, Void, Void> {
        PopulateDbAsyncTask(CreateDB instance) {
            QueryDB dao = instance.Dao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            return null;
        }
    }
}