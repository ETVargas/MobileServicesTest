package com.mas.ethan.mobileservicestest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;


public class NydrelActivity extends AppCompatActivity {
    private EditText nameField;
    private EditText foodField;
    private Button pushButton;
    // Access a Cloud Firestore instance from your Activity


    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_nydrel);
            nameField = (EditText) findViewById(R.id.EditTextName);
            foodField = (EditText) findViewById(R.id.EditTextFood);
            pushButton  = findViewById(R.id.ButtonSendInfo);

            mDatabase = FirebaseDatabase.getInstance().getReference(); //Dont pass any path if you want root of the tree

    }

    public void sendInfo(View button) {
        // Do click handling here
        addUser();
    }


    private void addUser() {
        //getting the values to save
        String name = nameField.getText().toString();
        String food = foodField.getText().toString();

        //checking if the value is provided
        if (!TextUtils.isEmpty(name)) {

            //getting a unique id using push().getKey() method
            //it will create a unique id and we will use it as the Primary Key for our Artist
            String id = mDatabase.push().getKey();

            //creating an Artist Object
            User user = new User(id, name, food);

            //Saving the Artist
            mDatabase.child(id).setValue(user);

            //setting edittext to blank again
            nameField.setText("");
            foodField.setText("");

            //displaying a success toast
            Toast.makeText(this, "User added", Toast.LENGTH_LONG).show();
        } else {
            //if the value is not given displaying a toast
            Toast.makeText(this, "Please enter a name", Toast.LENGTH_LONG).show();
        }
    }
}
