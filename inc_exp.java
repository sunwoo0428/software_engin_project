package com.example.sunwo.money_book;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class inc_exp extends AppCompatActivity {

    final int DIALOG_DATE = 1;

    public int exYear = 0;
    public int exMonth = 0;
    public int exDay = 0;

    public int inYear = 0;
    public int inMonth = 0;
    public int inDay = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inc_exp);


        final DBExpense dbExpense = new DBExpense(getApplicationContext(), "money_ex.db", null, 1);
        final DBIncome dbIncome = new DBIncome(getApplicationContext(), "money_in.db", null, 1);

        // DB에 저장 될 속성을 입력받는다
        final EditText etExpense = (EditText) findViewById(R.id.et_expense);
        final EditText etExCategory = (EditText) findViewById(R.id.et_ex_category);
        final EditText etIncome = (EditText) findViewById(R.id.et_income);
        final EditText etInCategory = (EditText) findViewById(R.id.et_in_category);

        // 쿼리 결과 입력
        final TextView exResult = (TextView) findViewById(R.id.ex_result);
        final TextView inResult = (TextView) findViewById(R.id.in_result);

        Button exDate = (Button)findViewById(R.id.btn_insertExDate);
        exDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog(DIALOG_DATE);
            }
        });

        Button inDate = (Button)findViewById(R.id.btn_insertInDate);
        inDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog(DIALOG_DATE);
            }
        });

        Button btnInsertEx = (Button) findViewById(R.id.btn_insertEx);
        btnInsertEx.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                // insert into 테이블명 values (값, 값, 값...);
                String expense = etExpense.getText().toString();
                String category = etExCategory.getText().toString();
                dbExpense.insert("insert into MONEY_EX values(null, "+expense+", '"+category+"',"+exYear+","+exMonth+","+exDay+");");

                exResult.setText( dbExpense.PrintData() );
            }
        });

        Button btnInsertIn = (Button) findViewById(R.id.btn_insertIn);
        btnInsertIn.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                // insert into 테이블명 values (값, 값, 값...);
                String income = etIncome.getText().toString();
                String category = etInCategory.getText().toString();
                dbIncome.insert("insert into MONEY_IN values(null, " + income + ", '" + category + "',"+exYear+","+exMonth+","+exDay+");");

                inResult.setText( dbIncome.PrintData() );
            }
        });


        Button btnDropEx = (Button) findViewById(R.id.btn_dropEx);
        btnDropEx.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                dbExpense.drop("DROP TABLE IF EXISTS MONEY_EX");
//              tvResult.setText( dbManager.PrintData() );
                dbExpense.createTable("CREATE TABLE MONEY_EX( _id INTEGER PRIMARY KEY AUTOINCREMENT, expense INTEGER, category TEXT, year INTEGER, month INTEGER, day INTEGER);");
                exResult.setText("");
            }
        });

        Button btnDropIn = (Button) findViewById(R.id.btn_dropIn);
        btnDropIn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                dbIncome.drop("DROP TABLE IF EXISTS MONEY_IN");
//              tvResult.setText( dbManager.PrintData() );
                dbIncome.createTable("CREATE TABLE MONEY_IN( _id INTEGER PRIMARY KEY AUTOINCREMENT, income INTEGER, category TEXT, year INTEGER, month INTEGER, day INTEGER);");
                inResult.setText("");
            }
        });

    }

    protected Dialog onCreateDialog(int id){
        DatePickerDialog dpd = new DatePickerDialog(inc_exp.this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
//                Toast.makeText(getApplicationContext(),year+"년 "+(monthOfYear+1)+"월 "+dayOfMonth+ "일", Toast.LENGTH_SHORT).show();
                exYear = year;
                exMonth = monthOfYear+1;
                exDay = dayOfMonth;
            }
        },2016,11,11);
        return dpd;
            }
}

