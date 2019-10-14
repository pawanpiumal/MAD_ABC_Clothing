package com.example.mad_abc_clothing.Database;

import android.provider.BaseColumns;

public final class EmployeeInfo {

    private EmployeeInfo() {
    }

    protected static  class EmployeeDetails implements BaseColumns{
        protected static final String TABLE_NAME = "employeeinfo";
        protected static final String COLUMN_NAME_EMP_NAME = "empname";
        protected static final String COLUMN_NAME_EMP_TELEPHONE = "emptelephone";
        protected static final String COLUMN_NAME_EMP_GENDER = "empgender";
        protected static final String COLUMN_NAME_EMP_TYPE = "emptype";



    }



}
