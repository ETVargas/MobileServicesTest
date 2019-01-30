package com.mas.ethan.mobileservicestest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;




public class NydrelActivity extends AppCompatActivity {
    private EditText nameField;
    private EditText foodField;
    private Button pushButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_nydrel);
            nameField = (EditText) findViewById(R.id.EditTextName);
            foodField = (EditText) findViewById(R.id.EditTextFood);
            pushButton  = findViewById(R.id.ButtonSendInfo);
        }

    public void sendInfo(View button) {
        // Do click handling here
        String name = nameField.getText().toString();
        String food = foodField.getText().toString();
    }
}
