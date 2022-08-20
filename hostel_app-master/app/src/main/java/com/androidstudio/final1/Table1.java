package com.androidstudio.final1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Table1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table1);

        Button btn_click_status_table1 = (Button) findViewById(R.id.btn_status_table1);
        btn_click_status_table1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Table1.this, Table2.class);
                startActivity(intent);
            }
        });

        Button btn_click_back_from_table1 = (Button) findViewById(R.id.btn_back_from_table2);
        btn_click_back_from_table1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Table1.this, Leave_Application.class);
                startActivity(intent);
            }
        });

    }
}