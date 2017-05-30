package com.olifia.welcome;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class Edit extends AppCompatActivity {
    Diari diari;
    Button saveEdit;
    EditText editTextJudul, editTextIsi;
    Spinner spinner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        editTextJudul = (EditText) findViewById(R.id.edit_judul);
        editTextIsi = (EditText) findViewById(R.id.edit_isi);
        spinner = (Spinner) findViewById(R.id.edit_mod);
        saveEdit = (Button) findViewById(R.id.save_edit);
        initDiari();
        editTextJudul.setText(diari.getJudul());
        editTextIsi.setText(diari.getIsi());

        saveEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseHandler databaseHandler = new DatabaseHandler(getApplicationContext());
                updateDiari();
                int r = databaseHandler.updateDiari(diari);
                Toast.makeText(getApplicationContext(),r+"",Toast.LENGTH_LONG).show();
                Intent intent = new Intent(Edit.this,navigation.class);
                startActivity(intent);
            }
        });

    }

    void updateDiari(){
        diari.setIsi(editTextIsi.getText().toString());
        diari.setJudul(editTextJudul.getText().toString());
        diari.setKategori(spinner.getSelectedItem().toString());
//        diari.setTanggal();
//        diari.setId(intent.getStringExtra(DatabaseHandler.KOLOM_ID));
    }

    void initDiari(){
        Intent intent = getIntent();
        diari = new Diari();
        diari.setIsi(intent.getStringExtra(DatabaseHandler.KOLOM_ISI));
        diari.setJudul(intent.getStringExtra(DatabaseHandler.KOLOM_JUDUL));
        diari.setKategori(intent.getStringExtra(DatabaseHandler.KOLOM_KATEGORI));
        diari.setTanggal(intent.getStringExtra(DatabaseHandler.KOLOM_TANGGAL));
        diari.setId(intent.getStringExtra(DatabaseHandler.KOLOM_ID));
    }
}
