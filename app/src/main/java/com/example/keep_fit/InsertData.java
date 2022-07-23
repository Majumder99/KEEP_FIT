package com.example.keep_fit;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class InsertData extends AppCompatActivity {


    DBRepository dbRepo;


    EditText dateEdit;
    EditText timeEdit;
    EditText systolicEdit;
    EditText diastolicEdit;
    EditText heartRateEdit;
    EditText commentEdit;
    Button saveBtn;
 
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_data);
         dateEdit = findViewById(R.id.date_1);
        timeEdit = findViewById(R.id.time_1);
        heartRateEdit = findViewById(R.id.heartRate_1);
        systolicEdit = findViewById(R.id.systolic_1);
        diastolicEdit = findViewById(R.id.diastolic_1);
        commentEdit = findViewById(R.id.comment_1);
        saveBtn = findViewById(R.id.save_button);

        dbRepo = new DBRepository(getApplication());


        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String systolicString = systolicEdit.getText().toString();
                String diastolicString = diastolicEdit.getText().toString();
                String heartRateString = heartRateEdit.getText().toString();
                String commentString = commentEdit.getText().toString();


                int systolic = 0, diastolic = 0, heartRate = 0;

                if (!systolicString.isEmpty()) {
                    systolic = Integer.parseInt(systolicString);

                    if (!diastolicString.isEmpty()) {
                        diastolic = Integer.parseInt(diastolicString);

                        if (!heartRateString.isEmpty()) {
                            heartRate = Integer.parseInt(heartRateString);
                            if (dateEdit.getText().toString().isEmpty()) {
                                dateEdit.setError("Please enter a date");
                                dateEdit.requestFocus();
                            } else {
                                if (timeEdit.getText().toString().isEmpty()) {
                                    timeEdit.setError("Please enter a date");
                                    timeEdit.requestFocus();
                                } else {

                                    if (systolic <= 0) {
                                        systolicEdit.setError("Please enter a valid number");
                                        systolicEdit.requestFocus();
                                    } else {
                                        if (diastolic <= 0) {
                                            diastolicEdit.setError("Please enter a valid number");
                                            diastolicEdit.requestFocus();
                                        } else {
                                            if (heartRate <= 0) {
                                                heartRateEdit.setError("Please enter a valid number");
                                                heartRateEdit.requestFocus();
                                            } else {
                                                MainClass MainClass = new MainClass(dateEdit.getText().toString(), timeEdit.getText().toString(),
                                                        systolic, diastolic, heartRate, commentString);

                                                MainClass.setDi(System.currentTimeMillis());
                                                dbRepo.insert(MainClass);

                                                Intent intent = new Intent(InsertData.this, MainActivity.class);

                                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                                startActivity(intent);
                                                finish();

                                            }
                                        }
                                    }


                                }
                            }
                        } else {
                            heartRateEdit.setError("Please enter heart rate");
                            heartRateEdit.requestFocus();
                        }
                    } else {
                        diastolicEdit.setError("Please enter diastolic pressure");
                        diastolicEdit.requestFocus();
                    }
                } else {
                    systolicEdit.setError("Please enter systolic pressure");
                    systolicEdit.requestFocus();
                }


            }
        });

    }



}