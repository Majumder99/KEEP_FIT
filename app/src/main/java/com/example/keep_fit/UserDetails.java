package com.example.keep_fit;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class UserDetails extends AppCompatActivity {

    DBRepository DBRepo;
    String dateString;
    String timeString;
    public MainClass mainClass = new MainClass();

    Button deleteBtn;
    EditText heartRate;
    EditText systolicRate;
    EditText diastoloicRate;
    EditText commentEditText;
    EditText dateEditText;
    EditText timeEditText;

    TextView heartText ;
    TextView systolicText;
    TextView diastolicText;




    Button SaveBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_details);

        deleteBtn = findViewById(R.id.delete_2);
        heartRate = findViewById(R.id.heartRate_2);
        systolicRate = findViewById(R.id.systolic_2);
        diastoloicRate = findViewById(R.id.diastolic_2);
        commentEditText = findViewById(R.id.comment_2);
        SaveBtn = findViewById(R.id.update_2);
        dateEditText = findViewById(R.id.date_2);
        timeEditText = findViewById(R.id.time_2);

        heartText = findViewById(R.id.heartRate_2) ;
        systolicText = findViewById(R.id.systolic_2) ;
        diastolicText = findViewById(R.id.diastolic_2) ;


        mainClass = (MainClass) getIntent().getSerializableExtra("MainClass");


        DBRepo = new DBRepository(getApplication()); // -------- DB ----------- //





        heartRate.setText(String.valueOf(mainClass.getHeartRate()));
        systolicRate.setText(String.valueOf(mainClass.getSystolic()));
        diastoloicRate.setText(String.valueOf(mainClass.getDiastolic()));
        commentEditText.setText(mainClass.getComment());

        dateEditText.setText(String.valueOf(mainClass.getDate()));
        timeEditText.setText(String.valueOf(mainClass.getTimestamp()));



        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DBRepo.delete(mainClass);

                Intent intent = new Intent(UserDetails.this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });


        SaveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String systolicString = systolicRate.getText().toString() ;
                String diastolicString = diastoloicRate.getText().toString() ;
                String heartRateString = heartRate.getText().toString() ;
                String commentString = commentEditText.getText().toString();

                int systolic = 0 , diastolic = 0 , heartRate_int = 0 ;

                if(!systolicString.isEmpty())
                    systolic = Integer.parseInt(systolicString);
                else{
                    systolicRate.setError("Please enter systolic pressure");
                    systolicRate.requestFocus();
                }

                if(!diastolicString.isEmpty())
                    diastolic = Integer.parseInt(diastolicString);
                else
                {
                    diastoloicRate.setError("Please enter diastolic pressure");
                    diastoloicRate.requestFocus();
                }

                if(!heartRateString.isEmpty())
                    heartRate_int = Integer.parseInt(heartRateString);
                else
                {
                    heartRate.setError("Please enter heart rate");
                    heartRate.requestFocus();
                }

                if(dateEditText.getText().toString().isEmpty()){
                    dateEditText.setError("Please enter a date");
                    dateEditText.requestFocus();
                }

                if (timeEditText.getText().toString().isEmpty()) {
                    timeEditText.setError("Please enter a date");
                    timeEditText.requestFocus();
                }




                MainClass MainClass_new = new MainClass(dateEditText.getText().toString(), timeEditText.getText().toString(),
                        systolic, diastolic, heartRate_int, commentString);

                MainClass_new.setDi(mainClass.getDi());
                DBRepo.update(MainClass_new);

                Intent intent = new Intent(UserDetails.this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();

            }
        });


    }




    
}