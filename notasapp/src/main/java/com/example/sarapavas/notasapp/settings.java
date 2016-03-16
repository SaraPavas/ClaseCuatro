package com.example.sarapavas.notasapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class settings extends AppCompatActivity {

    EditText ePra, eExp, ePro;
    Button bSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        ePra = (EditText)findViewById(R.id.Pprac);
        ePro= (EditText)findViewById(R.id.Pproy);
        eExp = (EditText)findViewById(R.id.Pexpo);
        bSave = (Button)findViewById(R.id.guardar);

        Bundle extras = getIntent().getExtras();

        eExp.setText(String.valueOf(extras.getInt("pExp")));
        ePra.setText(String.valueOf(extras.getInt("pPra")));
        ePro.setText(String.valueOf(extras.getInt("pProy")));

    }
}
