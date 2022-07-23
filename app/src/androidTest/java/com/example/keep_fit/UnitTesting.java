package com.example.keep_fit;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import androidx.room.Room;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;


@RunWith(AndroidJUnit4.class)
public class UnitTesting {
    private CreateDB db;
    private QueryDB dao;
 

    @Before
    public void before() {

        db = Room.inMemoryDatabaseBuilder(
                ApplicationProvider.getApplicationContext(),
                CreateDB.class
        ).build();
        dao = db.Dao();

    }

    @Test
    public void LoadData() throws Exception{


        int prevSize = dao.getAllData().size();

        MainClass MainClass = new MainClass("12/07/22", "12.00",
                100, 90, 70, "Testing");
        MainClass.setDi(System.currentTimeMillis());

        dao.InsertData(MainClass);

        MainClass MainClass1 = new MainClass("11/04/22", "12.00",
                98, 70, 78, "Testing1");
        MainClass1.setDi((System.currentTimeMillis())+100);

        dao.InsertData(MainClass1);

        MainClass MainClass2 = new MainClass("11/07/22", "12.00",
                78, 80, 90, "Testing2");
        MainClass2.setDi(System.currentTimeMillis()+200);

        dao.InsertData(MainClass2);

        int curSize = dao.getAllData().size();

        assertEquals(prevSize+3,curSize);



    }


    @Test
    public void InsertData() throws Exception{


        MainClass MainClass = new MainClass("12/07/22", "12.00",
                100, 12, 100, "Testing");
        MainClass.setDi(System.currentTimeMillis());

        dao.InsertData(MainClass);
        MainClass allData = dao.getData(String.valueOf(MainClass.getDi()));
        assertEquals(allData.getDi(),MainClass.getDi());



    }



    @Test
    public void DeleteData() throws Exception{


        MainClass MainClass = new MainClass("12/07/22", "12.00",
                100, 12, 100, "Testing");
        MainClass.setDi(System.currentTimeMillis());

        dao.InsertData(MainClass);
        dao.DeleteData(MainClass);

        MainClass MainClassNUll = dao.getData(String.valueOf(MainClass.getDi())) ;

        assertNull(MainClassNUll);

    }
    @Test
    public void UpdateData() throws Exception{


        MainClass MainClass = new MainClass("12/07/22", "12.00",
                100, 12, 100, "Testing");
        MainClass.setDi(System.currentTimeMillis());

        dao.InsertData(MainClass);


        MainClass MainClassRetrive = dao.getData(String.valueOf(MainClass.getDi())) ;
        MainClassRetrive.setDiastolic(120);
        dao.UpdateData(MainClassRetrive);

        MainClass MainClassUpdate = dao.getData(String.valueOf(MainClass.getDi())) ;

        assertEquals(MainClassUpdate.getDiastolic(),120);




    }



    @After
    public void finish() {
        db.close();
    }
}