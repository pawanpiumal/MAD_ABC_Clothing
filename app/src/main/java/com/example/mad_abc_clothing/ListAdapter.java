package com.example.mad_abc_clothing;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.mad_abc_clothing.Database.User;

import java.util.List;

public class ListAdapter extends ArrayAdapter {
    LayoutInflater layoutInflater;


    public ListAdapter(@NonNull Context context, List user) {




        super(context, R.layout.list_employee_item,user);

        layoutInflater = LayoutInflater.from(context);
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        User user = (User)getItem(position);

        if(convertView == null) {
            View view = layoutInflater.inflate(R.layout.list_employee_item, parent, false);
            convertView = view;
        }


        TextView textViewID = convertView.findViewById(R.id.textViewID);
        TextView textViewName = convertView.findViewById(R.id.textViewName);
        TextView textViewGender = convertView.findViewById(R.id.textViewGender);
        TextView textViewTelephone = convertView.findViewById(R.id.textViewTelephone);
        TextView textViewType = convertView.findViewById(R.id.textViewType);




        textViewID .setText(user.getID()+"");
        textViewName.setText(user.getName());
        textViewGender.setText(user.getGender());
        textViewTelephone.setText(user.getTelephone()+"");
        textViewType.setText(user.getType());





        return convertView;
    }
}
