
package com.example.mad_abc_clothing;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;

import com.example.mad_abc_clothing.Database.DBHandler;
import com.example.mad_abc_clothing.Database.User;

import java.util.List;

public class ViewEmployee extends AppCompatActivity {

    Button buttonSearch;
    ListView listView;
    Spinner spinnerType;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_employee);


         buttonSearch = findViewById(R.id.buttonSearch);
         listView = findViewById(R.id.list);
         spinnerType = findViewById(R.id.spinnerType);

        String[] Type = {"Permanent","Temporary"};
        ArrayAdapter adapter = new ArrayAdapter(getApplicationContext(),R.layout.support_simple_spinner_dropdown_item,Type);
        spinnerType.setAdapter(adapter);

         buttonSearch.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {

                 String Type = spinnerType.getSelectedItem().toString();

                 DBHandler dbHandler = new DBHandler(getApplicationContext());

                 List<User> list = dbHandler.searchEmployee("empType",Type);


                 ListAdapter adapter1 = new ListAdapter(getApplicationContext(),list);
                 listView.setAdapter(adapter1);




             }
         });



    }
}
