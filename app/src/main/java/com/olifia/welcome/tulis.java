package com.olifia.welcome;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class tulis extends AppCompatActivity {
    Button save;
    Spinner spinnerKategori;
    EditText editTextJudul, editTextIsi,editTextTanggal;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tulis);

        spinnerKategori = (Spinner) findViewById(R.id.mod);
        editTextJudul = (EditText) findViewById(R.id.cobaJudul);
        editTextTanggal = (EditText) findViewById(R.id.tanggal);
        editTextIsi = (EditText) findViewById(R.id.isi);
        save = (Button) findViewById(R.id.save);

        //inisialisasi nilai tanggal
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        editTextTanggal.setText(simpleDateFormat.format(calendar.getTime()));

        save.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                DatabaseHandler databaseHandler = new DatabaseHandler(getApplicationContext());
                Diari diari = new Diari();
                diari.setTanggal(editTextTanggal.getText().toString());
                diari.setIsi(editTextIsi.getText().toString());
                diari.setJudul(editTextJudul.getText().toString());
                diari.setKategori(spinnerKategori.getSelectedItem().toString());
                databaseHandler.addDiari(diari);
                Intent intent = new Intent(tulis.this, navigation.class);
                startActivity(intent);
            }
        });


    }
}
