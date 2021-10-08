package com.example.endrevina;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TableRow;
import android.widget.TextView;

public class RecordActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record);
    //Ranking TextView name
        TextView resultInput = findViewById(R.id.Ranking);
        resultInput.setText("Ranking");
    //Getting name and tries from main activity
         Intent intent= getIntent();
         String name= intent.getStringExtra("name");
         String tries= intent.getStringExtra("tries");
     //Result into row
        TextView row101= findViewById(R.id.row101);
        row101.setText("name");
        TextView row102= findViewById(R.id.row102);
        row102.setText("tries");


        TextView row201= findViewById(R.id.row201);
        row201.setText(name);
        TextView row202= findViewById(R.id.row202);
        row202.setText(tries);

    }




}