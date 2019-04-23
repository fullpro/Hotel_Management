package com.example.dell.hotel_management;


import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class BookingDetails extends AppCompatActivity implements View.OnClickListener {

    Button bt;
    EditText Name, Age, email_id;
    SQLiteDatabase sqlitdb;
    String sname, sage, mailid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_details);

        bt = (Button) findViewById(R.id.proceed);
        Name = (EditText) findViewById(R.id.name);
        Age = (EditText) findViewById(R.id.age);
        email_id = (EditText) findViewById(R.id.email);
        sqlitdb = openOrCreateDatabase("booking", Context.MODE_PRIVATE, null);
        sqlitdb.execSQL("CREATE TABLE IF NOT EXISTS CustRegistration(CustId INTEGER PRIMARY KEY AUTOINCREMENT,CustName VARCHAR(255), Email VARCHAR(255),CustAge INTEGER ) ;");
        bt.setOnClickListener(this);


    }


    @Override
    public void onClick(View v) {


        if (v.getId() == R.id.proceed) {
            sname = Name.getText().toString().trim();
            sage = Age.getText().toString().trim();
            mailid = email_id.getText().toString().trim();
            if (sname.equals("") || mailid.equals("") || sage.equals("")) {
                Toast.makeText(this, "Field cannot be empty", Toast.LENGTH_LONG).show();
                return;
            } else {
                sqlitdb.execSQL("Insert Into CustRegistration(CustName,Email,CustAge)VALUES('" + sname + "','" + mailid + "','" + sage + "');");
                Toast.makeText(this,"Booking Successful",Toast.LENGTH_LONG).show();
                Intent intent = new Intent(BookingDetails.this,CheckInDetails.class);
                startActivity(intent);

            }

        }

    }
}