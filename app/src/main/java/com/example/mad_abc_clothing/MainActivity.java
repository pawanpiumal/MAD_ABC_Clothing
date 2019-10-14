package com.example.mad_abc_clothing;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    Button buttonAddEmployee;
    Button buttonEditEmployee;
    Button buttonSearchEmployee;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


       buttonAddEmployee = findViewById(R.id.buttonAddEmployee);
       buttonEditEmployee = findViewById(R.id.buttonEditEmployee);
       buttonSearchEmployee = findViewById(R.id.buttonSearchEmployee);


       buttonAddEmployee.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               Intent intent = new Intent(getApplicationContext(),AddEmployee.class);
               startActivity(intent);

           }
       });
        buttonEditEmployee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), EditEmployee.class);
                startActivity(intent);

            }
        });
        buttonSearchEmployee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),ViewEmployee.class);
                startActivity(intent);

            }
        });

    }
}
