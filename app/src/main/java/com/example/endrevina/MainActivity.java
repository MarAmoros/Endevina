package com.example.endrevina;

import static android.provider.AlarmClock.EXTRA_MESSAGE;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import android.app.ActivityManager;

import java.io.File;
import java.io.IOException;

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
                    dispatchTakePictureIntent();
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

                            openRecordView(v,nameInput.getText().toString()); //lista de records



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
        intent.setAction(Intent.ACTION_SEND);
        intent.putExtra("name", nameInput);
        intent.putExtra("tries",String.valueOf(tries));

        startActivity(intent);


    }


    static final int REQUEST_IMAGE_CAPTURE = 1;

    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // Ensure that there's a camera activity to handle the intent
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            // Create the File where the photo should go
            File photoFile = null;
            try {
                photoFile = getFile();
            } catch (IOException ex) {
                // Error occurred while creating the File

            }
            // Continue only if the File was successfully created
            if (photoFile != null) {
                Uri photoURI = FileProvider.getUriForFile(this,
                        "com.example.android.fileprovider",
                        photoFile);
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
            }
        }
    }

    protected File getFile() throws IOException{
        //guardar a un fitxe
        File path =getApplicationContext().getExternalFilesDir(Environment.DIRECTORY_PICTURES); //para que este en el almacenamiento externo
        File foto= new File(path,"imatge.jpg");
        return foto;
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        setContentView(R.layout.list_item);
        final ImageView imageView = findViewById(R.id.imageView);

        Uri fileUri = null;
        try {
            fileUri = Uri.fromFile(getFile());
            imageView.setImageURI(fileUri);
            setContentView(R.layout.activity_main);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }





}