package com.example.mad_abc_clothing;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.mad_abc_clothing.Database.DBHandler;
import com.example.mad_abc_clothing.Database.User;

import java.util.List;

public class EditEmployee extends AppCompatActivity {

    EditText editTextName;
    EditText editTexttTelephone;
    EditText editTextEmail;
    RadioButton radioButtonMale;
    RadioButton radioButtonFemale;
    RadioGroup radioGroup;
    Spinner spinnerEmployeeType;
    Button buttonEditEmployee;
    Button buttonSearch;
    EditText editTextID;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_employee);


        editTextName = findViewById(R.id.editTextName);
        editTexttTelephone = findViewById(R.id.editTextTelephone);
        editTextEmail = findViewById(R.id.editTextEmail);
        radioButtonMale = findViewById(R.id.radioButtonMale);
        radioButtonFemale = findViewById(R.id.radioButtonFemale);
        spinnerEmployeeType = findViewById(R.id.spinnerType);
        buttonEditEmployee = findViewById(R.id.buttonEditEmployee);
        buttonSearch = findViewById(R.id.buttonSearch);
        editTextID = findViewById(R.id.editTextID);


        buttonSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String id = editTextID.getText().toString().trim();
                if(TextUtils.isEmpty(id)){
                    Toast.makeText(getApplicationContext(),"Fill ID  Field", Toast.LENGTH_SHORT).show();
                }else{
                    DBHandler dbHandler = new DBHandler(getApplicationContext());
                    User user = new User();
                    List userList = dbHandler.searchEmployee("empID",id);
                    user = (User)userList.get(0);

                    editTextName.setText(user.getName());
                    editTexttTelephone.setText(user.getTelephone()+"");
                    if(TextUtils.equals(user.getGender(),"Male")){
                        radioButtonMale.setChecked(true);
                        radioButtonFemale.setChecked(false);
                    }else{
                        radioButtonFemale.setChecked(true);
                        radioButtonMale.setChecked(false);
                    }



                    if(TextUtils.equals(user.getType(),"Permanent")) {
                        String[] Type = {"Permanent","Temporary"};
                        ArrayAdapter adapter = new ArrayAdapter(getApplicationContext(),R.layout.support_simple_spinner_dropdown_item,Type);
                        spinnerEmployeeType.setAdapter(adapter);
                    }else{
                        String[] Type = {"Temporary" , "Permanent"};
                        ArrayAdapter adapter = new ArrayAdapter(getApplicationContext(),R.layout.support_simple_spinner_dropdown_item,Type);
                        spinnerEmployeeType.setAdapter(adapter);

                    }


                }



            }
        });




        buttonEditEmployee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String id = editTextID.getText().toString().trim();
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

                if(TextUtils.isEmpty(id)||TextUtils.isEmpty(Name)||TextUtils.isEmpty(Email)||TextUtils.isEmpty(Telephone)||TextUtils.isEmpty(Type)||TextUtils.isEmpty(Gender)){
                    Toast.makeText(getApplicationContext(),"Fill all the Fields", Toast.LENGTH_SHORT).show();
                }else{
                    DBHandler dbHandler = new DBHandler(getApplicationContext());
                    int id1 = Integer.parseInt(id);
                    int tele = Integer.parseInt(Telephone);
                    if(dbHandler.updateEmployee(id1, Name,  tele, Gender, Type)){
                        Toast.makeText(getApplicationContext(),"Updated", Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(getApplicationContext(),"Not Updated", Toast.LENGTH_SHORT).show();
                    }
                }



            }
        });


    }
}
