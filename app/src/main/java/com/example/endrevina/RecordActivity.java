package com.example.endrevina;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.widget.TableRow;
import android.widget.TextView;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.Hashtable;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import java.util.ArrayList;

/*
    Abans de començar hem de crear
    (1) Un layout per al main amb una ListView anomenada recordsView
            R.id.recordsView
    (2) Un layout (lineal) per a la ListView anomenat list_item
            R.layout.list_item
        Aquest contindrà 2 TextView amb IDs: nom i intents
            R.id.nom
            R.id.intents

    Referències
        - https://github.com/codepath/android_guides/wiki/Using-an-ArrayAdapter-with-ListView
 */

public class RecordActivity extends AppCompatActivity {

    // Model: Record (intents=puntuació, nom)
    class Record {
        public Uri uri;
        public int intents;
        public String nom;

        public Record(int _intents, String _nom ,Uri _uri) {
            intents = _intents;
            nom = _nom;
            uri = _uri;
        }

        public int getIntents(){
            return intents;
        }
    }
    // Model = Taula de records: utilitzem ArrayList
    ArrayList<Record> records;

    // ArrayAdapter serà l'intermediari amb la ListView
    ArrayAdapter<Record> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record);


        //Getting name and tries from main activity
        Intent intent= getIntent();
        String name= intent.getStringExtra("name");
        String tries= intent.getStringExtra("tries");

        //camara
        //



        // Inicialitzem model
        records = new ArrayList<Record>();
        // Afegim alguns exemples
        try {
            records.add( new Record(Integer.parseInt(tries),name,Uri.fromFile(getFile())) );
        } catch (IOException e) {
            e.printStackTrace();
        }
        records.add( new Record(2,"Manolo",null) );
        records.add( new Record(12,"Pepe",null) );
        records.add( new Record(42,"Laura",null) );


        //Ordenar records  https://jarroba.com/ordenar-un-arraylist-en-java/

        Collections.sort(records, new Comparator<Record>() {
            @Override
            public int compare(Record r1, Record r2) {
                return new Integer(r1.getIntents()).compareTo(new Integer(r2.getIntents()));
            }
        });

        // Inicialitzem l'ArrayAdapter amb el layout pertinent
        adapter = new ArrayAdapter<Record>( this, R.layout.list_item, records )
        {
            @Override
            public View getView(int pos, View convertView, ViewGroup container)
            {
                // getView ens construeix el layout i hi "pinta" els valors de l'element en la posició pos
                if( convertView==null ) {
                    // inicialitzem l'element la View amb el seu layout
                    convertView = getLayoutInflater().inflate(R.layout.list_item, container, false);
                }
                // "Pintem" valors (també quan es refresca)



                ((ImageView) convertView.findViewById(R.id.imageView)).setImageURI(getItem(pos).uri);
                ((TextView) convertView.findViewById(R.id.nom)).setText(getItem(pos).nom);
                ((TextView) convertView.findViewById(R.id.intents)).setText(Integer.toString(getItem(pos).intents));
                return convertView;
            }

        };

        // busquem la ListView i li endollem el ArrayAdapter
        ListView lv = (ListView) findViewById(R.id.recordsView);
        lv.setAdapter(adapter);
    }

    protected File getFile() throws IOException{
        //guardar a un fitxe
        File path =getApplicationContext().getExternalFilesDir(Environment.DIRECTORY_PICTURES); //para que este en el almacenamiento externo
        File foto= new File(path,"imatge.jpg");
        return foto;
    }




}