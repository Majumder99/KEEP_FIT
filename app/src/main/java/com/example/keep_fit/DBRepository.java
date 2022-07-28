package com.example.keep_fit;


import android.app.Application;
import android.os.AsyncTask;

import java.util.List;

/**
 * For inserting, deleting and updating data into the database
 */
public class DBRepository {

    private QueryDB dao;
    private  List<MainClass>  allData;

    public DBRepository(Application application) {
        CreateDB database = CreateDB.getInstance(application);
        dao = database.Dao();
        allData = dao.getAllData();
    }

    /**
     * inserting into DB
     * @param model
     */
    public void insert(MainClass model) {
        new InsertCourseAsyncTask(dao).execute(model);
    }

    /**
     * updating into DB
     * @param model
     */
    public void update(MainClass model) {
        new UpdateCourseAsyncTask(dao).execute(model);
    }

    /**
     * deleting from DB
     * @param model
     */
    public void delete(MainClass model) {
        new DeleteCourseAsyncTask(dao).execute(model);
    }

    /**
     * below is the method to delete all the courses.
     */
    public void deleteAllData() {
        new DeleteAllCoursesAsyncTask(dao).execute();
    }

    /**
     * below method is to read all the courses.
     * @return
     */
    public List<MainClass> getAllData() {
        return allData;
    }

    /**
     * we are creating a async task method to insert new course.
     */
    private static class InsertCourseAsyncTask extends AsyncTask<MainClass, Void, Void> {
        private QueryDB dao;

        private InsertCourseAsyncTask(QueryDB dao) {
            this.dao = dao;
        }

        @Override
        protected Void doInBackground(MainClass... model) {
            /**
             * below line is use to insert our modal in dao.
             */
            dao.InsertData(model[0]);
            return null;
        }
    }

    /**
     * we are creating a async task method to update our course.
     */
    private static class UpdateCourseAsyncTask extends AsyncTask<MainClass, Void, Void> {
        private QueryDB dao;

        private UpdateCourseAsyncTask(QueryDB dao) {
            this.dao = dao;
        }

        @Override
        protected Void doInBackground(MainClass... models) {
            /**
             * below line is use to update our modal in dao.
             */
            dao.UpdateData(models[0]);
            return null;
        }
    }


    /**
     * we are creating a async task method to delete course.
     */
    private static class DeleteCourseAsyncTask extends AsyncTask<MainClass, Void, Void> {
        private QueryDB dao;

        private DeleteCourseAsyncTask(QueryDB dao) {
            this.dao = dao;
        }

        @Override
        protected Void doInBackground(MainClass... models) {
            /**
             * below line is use to delete our course modal in dao.
             */
            dao.DeleteData(models[0]);
            return null;
        }
    }

    /**
     * we are creating a async task method to delete all courses.
     */
    private static class DeleteAllCoursesAsyncTask extends AsyncTask<Void, Void, Void> {
        private QueryDB dao;
        private DeleteAllCoursesAsyncTask(QueryDB dao) {
            this.dao = dao;
        }
        @Override
        protected Void doInBackground(Void... voids) {
            /**
             * on below line calling method to delete all courses, dao.deleteAllCourses();
             */
            return null;
        }
    }
}
