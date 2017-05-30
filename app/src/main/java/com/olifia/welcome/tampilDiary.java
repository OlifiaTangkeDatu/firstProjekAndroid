package com.olifia.welcome;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class tampilDiary extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tampil_diary);
        Intent intent = getIntent();
        TextView textView = (TextView) findViewById(R.id.txtTampilDiari);
        textView.setText(intent.getStringExtra(DatabaseHandler.KOLOM_ISI));
    }
}
