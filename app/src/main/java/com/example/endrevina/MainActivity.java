package com.example.endrevina;

import static android.provider.AlarmClock.EXTRA_MESSAGE;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.app.ActivityManager;

public class MainActivity extends AppCompatActivity {
    //Number of tries
    private int tries = 0;
    //Number to guess
    private int correctNumber= (int)(Math.random()*10)+1;





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
                //Dialog
                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                    //Input nombre
                    final EditText nameInput = new EditText(MainActivity.this);
                   nameInput.setInputType(InputType.TYPE_CLASS_TEXT);
                    builder.setView(nameInput);
                    //title and buttons
                    builder.setMessage("Introduce tu nombre")
                            .setTitle("Nombre")
                            .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            openRecordView(v,nameInput.getText().toString());
                        }
                    });
                    builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.cancel();
                        }
                    });
                    AlertDialog dialog = builder.create();
                    dialog.show();
                //More or less than the correct number
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
    /** Called when the user taps the Send button */
    public void openRecordView(View view,String nameInput) {
        Intent intent = new Intent(this, RecordActivity.class);
        //EditText editText = (EditText) findViewById(R.id.recordView);
      //  String message = editText.getText().toString();
        //intent.putExtra(EXTRA_MESSAGE, tries); //cambiar por array
        intent.setAction(Intent.ACTION_SEND);
        intent.putExtra("name", nameInput);
        intent.putExtra("tries",String.valueOf(tries));
        startActivity(intent);
    }




}