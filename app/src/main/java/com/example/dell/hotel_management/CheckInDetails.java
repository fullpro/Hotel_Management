package com.example.dell.hotel_management;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class CheckInDetails extends AppCompatActivity implements View.OnClickListener {

    TextView checkinDate, checkoutDate;
    private Context mContext;

    EditText noofDays;
    TextView Amount;
    SQLiteDatabase sqLiteDatabase;
    String CheckIn, CheckOut;
    Button bookIt;
    BookingDetails bookingDetailsActivity;
    int day,month,year,dayout,monthout,yearout;
    int amountGenerated;
    Calendar calendar;
    private String noOfDays;
    private int foo,foo2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_in_details);

        calendar = Calendar.getInstance();

        checkinDate = findViewById(R.id.checkIn);
        checkoutDate = findViewById(R.id.checkOut);
        Amount = findViewById(R.id.amount);
        bookIt = (Button) findViewById(R.id.book);
        noofDays =(EditText) findViewById(R.id.days);

        day = calendar.get(Calendar.DAY_OF_MONTH);
        month = calendar.get(Calendar.MONTH);
        year = calendar.get(Calendar.YEAR);
        dayout = calendar.get(Calendar.DAY_OF_MONTH);
        monthout = calendar.get(Calendar.MONTH);
        yearout = calendar.get(Calendar.YEAR);

        month = month +1;
        monthout = monthout +1;

        checkinDate.setText("Enter Check In");
        checkoutDate.setText("Enter Check Out");

        checkinDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(CheckInDetails.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        month = month +1;
                        checkinDate.setText(dayOfMonth + "/" + month + "/" + year);
                    }
                },month,year,day);
                datePickerDialog.show();
            }
        });

        checkoutDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(CheckInDetails.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        month = month +1;
                        checkoutDate.setText(dayOfMonth + "/" + month + "/" + year);
                    }
                },monthout,yearout,dayout);
                datePickerDialog.show();
            }
        });

        sqLiteDatabase = openOrCreateDatabase("booking", Context.MODE_PRIVATE, null);
        sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS bookings(BookingNo INTEGER PRIMARY KEY AUTOINCREMENT, CheckIn DATE, CheckOut DATE,Amount INTEGER)");
        bookIt.setOnClickListener(this);



        noofDays.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
               try {
                   noOfDays = noofDays.getText().toString();
                   foo = Integer.parseInt(noOfDays);
                    foo2 = foo * 1999;
                   Amount.setText(String.valueOf(foo2 ));
               }catch(Exception e) {
                   Amount.setText(String.valueOf(0));
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


    }


    @Override
    public void onClick(View view) {

       // Date datein,dateout;

        if (view.getId() == R.id.book) {
            CheckIn = checkinDate.getText().toString().trim();
            CheckOut = checkoutDate.getText().toString().trim();

            Date datein = stringToDate(CheckIn, "dd-MM-yyyy");
            Date dateout = stringToDate(CheckIn, "dd-MM-yyyy");
            //String dateIn = CheckIn;
            //SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
            //try {
              //   datein = format.parse(dateIn);
                // dateout = format.parse(dateIn);

            //} catch (ParseException e) {
              //  e.printStackTrace();
            //}



            if (checkinDate.getText().equals("Enter Check In") || checkoutDate.getText().equals("Enter Check Out") || noofDays.getText().equals("Number of Days")) {
                Toast.makeText(this, "Field cannot be empty", Toast.LENGTH_LONG).show();
                return;
            } else {
              sqLiteDatabase.execSQL("Insert Into bookings(CheckIn,CheckOut,Amount)VALUES('"+  datein + "','" + dateout+ "','" + foo2 + "');");
                Toast.makeText(this,"Booking Successful",Toast.LENGTH_LONG).show();

            }

        }

    }

    private Date stringToDate(String aDate,String aFormat) {

        if(aDate==null) return null;
        ParsePosition pos = new ParsePosition(0);
        SimpleDateFormat simpledateformat = new SimpleDateFormat(aFormat);
        Date stringDate = simpledateformat.parse(aDate, pos);
        return stringDate;

    }
}

