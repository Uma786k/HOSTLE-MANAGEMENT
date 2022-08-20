package com.androidstudio.final1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Table2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table2);

        Button btn__click_back_table2 = (Button) findViewById(R.id.btn_back_table2);
        btn__click_back_table2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Table2.this, Table1.class);
                startActivity(intent);
            }
        });

        Button btn_click_apply_table2 = (Button) findViewById(R.id.btn_apply_table2);
        btn_click_apply_table2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Table2.this, Leave_Application.class);
                startActivity(intent);
            }
        });

    }
}