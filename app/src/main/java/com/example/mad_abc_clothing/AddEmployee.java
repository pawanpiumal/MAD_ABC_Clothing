package com.example.mad_abc_clothing;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.GestureDetector;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.mad_abc_clothing.Database.DBHandler;

import java.lang.ref.PhantomReference;

public class AddEmployee extends AppCompatActivity {


    EditText editTextName;
    EditText editTexttTelephone;
    EditText editTextEmail;
    RadioButton radioButtonMale;
    RadioButton radioButtonFemale;
    RadioGroup radioGroup;
    Spinner spinnerEmployeeType;
    Button buttonAddEmployee;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_employee);



        editTextName = findViewById(R.id.editTextName);
        editTexttTelephone = findViewById(R.id.editTextTelephone);
        editTextEmail = findViewById(R.id.editTextEmail);
        radioButtonMale = findViewById(R.id.radioButtonMale);
        radioButtonFemale = findViewById(R.id.radioButtonFemale);
        radioGroup = findViewById(R.id.radioGroup);
        spinnerEmployeeType = findViewById(R.id.spinnerType);
        buttonAddEmployee = findViewById(R.id.buttonAddEmployee);

        radioGroup.check(R.id.radioButtonMale);


        String[] Type = {"Permanent","Temporary"};
        ArrayAdapter adapter = new ArrayAdapter(getApplicationContext(),R.layout.support_simple_spinner_dropdown_item,Type);
        spinnerEmployeeType.setAdapter(adapter);


        buttonAddEmployee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Name = editTextName.getText().toString().trim();
                String Email = editTextEmail.getText().toString().trim();
                String Telephone = editTexttTelephone.getText().toString().trim();
                String Type = spinnerEmployeeType.getSelectedItem().toString();


                //radio button is already checked to male so validation is not required
                String Gender = null;
                if(radioButtonFemale.isChecked()){
                    Gender= "Female";

                }else if(radioButtonMale.isChecked()){
                    Gender = "Male";
                }

                if(TextUtils.isEmpty(Name)||TextUtils.isEmpty(Email)||TextUtils.isEmpty(Telephone)||TextUtils.isEmpty(Type)||TextUtils.isEmpty(Gender)){
                    Toast.makeText(getApplicationContext(),"Fill all the Fields", Toast.LENGTH_SHORT).show();
                }else{
                    DBHandler dbHandler = new DBHandler(getApplicationContext());
                    int tele = Integer.parseInt(Telephone);
                    if(dbHandler.addEmployee(Name,  tele, Gender, Type)){
                        Toast.makeText(getApplicationContext(),"Added", Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(getApplicationContext(),"Not Added", Toast.LENGTH_SHORT).show();
                    }
                }


            }
        });



    }
}
