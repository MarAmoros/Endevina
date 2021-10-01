package com.example.endrevina;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    //Number of tries
    int tries = 0;
    //Number to guess
    int correctNumber= (int)(Math.random()*10)+1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);




        //Number input
        final EditText numberGuessed;
        numberGuessed  = (EditText) findViewById(R.id.numberGuessed);

        //Button
        final Button button = findViewById(R.id.submit);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Code here executes on main thread after user presses button
                Context context = getApplicationContext();
                int duration = Toast.LENGTH_SHORT;

                if(Integer.valueOf(String.valueOf(numberGuessed.getText()))==correctNumber){
                    CharSequence text = "WIN!\nSCORE: "+tries;
                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                }else{
                    tries++;
                    if(Integer.valueOf(String.valueOf(numberGuessed.getText()))>correctNumber){
                        CharSequence text = "ITS SMALLER\nTRY AGAIN";
                        numberGuessed.setText("");
                        Toast toast = Toast.makeText(context, text, duration);
                        toast.show();
                    }
                    else{
                        CharSequence text = "ITS BIGGER\nTRY AGAIN";
                        numberGuessed.setText("");
                        Toast toast = Toast.makeText(context, text, duration);
                        toast.show();
                    }

                }


            }
        });
    }


}