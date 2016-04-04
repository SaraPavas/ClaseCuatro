package com.example.sarapavas.notasapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.content.Intent;
import android.widget.TextView;
import android.widget.Toast;

public class settings extends AppCompatActivity {

    EditText ePra, eExp, ePro;
    Button bSave,bLimpiar;
    TextView tpPrac, tpProy, tpExpo;
    boolean limpio=false;
    String sExp, sPro,sPra;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        ePra = (EditText)findViewById(R.id.Pprac);
        ePro= (EditText)findViewById(R.id.Pproy);
        eExp = (EditText)findViewById(R.id.Pexpo);
        tpPrac =(TextView)findViewById(R.id.tpPrac);
        tpProy =(TextView)findViewById(R.id.tpProy);
        tpExpo =(TextView)findViewById(R.id.tpExpo);
        bSave = (Button)findViewById(R.id.guardar);
        bLimpiar = (Button)findViewById(R.id.borrar);

        Bundle extras = getIntent().getExtras(); //captura los extras

        eExp.setText(String.valueOf(extras.getDouble("pExp"))); //los pone en los editext
        ePra.setText(String.valueOf(extras.getDouble("pPra")));
        ePro.setText(String.valueOf(extras.getDouble("pProy")));
        sExp = eExp.getText().toString();
        sPra = ePra.getText().toString();
        sPro = ePro.getText().toString();
        bSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { //responde a la actividad principal
                Intent i = new Intent();
                //boolean control=false;
                int re_espBlanco;
                re_espBlanco = espaciosEnBlanco();
                if(re_espBlanco == 1) {
                    if (verificarPorcentajes()) {
                        i.putExtra("prPro", Double.parseDouble(ePro.getText().toString()));
                        i.putExtra("prPra", Double.parseDouble(ePra.getText().toString()));
                        i.putExtra("prExpo", Double.parseDouble(eExp.getText().toString()));
                        setResult(RESULT_OK, i);
                        finish();
                    }
                }else  if(re_espBlanco == 2) {
                    finish();
                }

            }
        });
        bLimpiar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tpPrac.setText("("+sPra+"%) ");
                tpProy.setText("("+sPro+"%) ");
                tpExpo.setText("("+sExp+"%) ");
                eExp.getText().clear();
                ePro.getText().clear();
                ePra.getText().clear();
                limpio = true;
            }
        });
    }
    int espaciosEnBlanco(){
        String sExpo, sProy, sPrac;
        sExpo=eExp.getText().toString();
        sProy = ePro.getText().toString();
        sPrac = ePra.getText().toString();
        //Bundle extras = getIntent().getExtras();
        if(TextUtils.isEmpty(sExpo)&&TextUtils.isEmpty(sProy)&&TextUtils.isEmpty(sPrac)) {
            Toast.makeText(this, getResources().getString(R.string.modificar), Toast.LENGTH_SHORT).show();
            return 2;
        }
        if(sExpo.equals(sExp)  && sProy.equals(sPro)  && sPrac.equals(sPra)){
            Toast.makeText(this, getResources().getString(R.string.modificar), Toast.LENGTH_SHORT).show();
            return 2;
        }
        if(TextUtils.isEmpty(sExpo)||TextUtils.isEmpty(sProy)||TextUtils.isEmpty(sPrac)) {
            Toast.makeText(this, getResources().getString(R.string.EspaciosBlanco), Toast.LENGTH_SHORT).show();
            return 0;
        }
        return 1;
    }
    public boolean verificarPorcentajes(){
        if(Double.parseDouble(ePro.getText().toString()) + Double.parseDouble(ePra.getText().toString())
                + Double.parseDouble(eExp.getText().toString()) != 100) {
            Toast.makeText(this, getResources().getString(R.string.suma), Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
}
