package com.example.sarapavas.notasapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.content.Intent;


public class MainActivity extends AppCompatActivity {
    MainActivity objeto;
    EditText ePrac, eExpo, eProy;
    TextView tNotaFinal,tpProy,tpPrac,tpExpo;
    Button calcular;
    Button borrar;
    double pExpo=15, pProy=35, pPrac=50;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ePrac = (EditText)findViewById(R.id.prac);
        eProy= (EditText)findViewById(R.id.proy);
        eExpo = (EditText)findViewById(R.id.expo);
        calcular = (Button)findViewById(R.id.calc);
        tNotaFinal = (TextView)findViewById(R.id.NotaFinal);
        tpProy= (TextView)findViewById(R.id.tpProy);
        tpPrac= (TextView)findViewById(R.id.tpPrac);
        tpExpo= (TextView)findViewById(R.id.tpExpo);
        borrar = (Button)findViewById(R.id.borrar);
        calcular.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {

                boolean control;
                control = espaciosEnBlanco();
                if(control) {
                    if (validarNotas()) {
                        calcularNotas();
                    }
                }
            }
        });
        borrar.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                tNotaFinal.setText("");
                eExpo.getText().clear();
                eProy.getText().clear();
                ePrac.getText().clear();
            }
        });

    }
    public boolean espaciosEnBlanco(){
        String sExp, sProy, sPrac;
        sExp=eExpo.getText().toString();
        sProy = eProy.getText().toString();
        sPrac = ePrac.getText().toString();
        if(TextUtils.isEmpty(sExp)||TextUtils.isEmpty(sProy)||TextUtils.isEmpty(sPrac)) {
            Toast.makeText(this,  getResources().getString(R.string.EspaciosBlanco), Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
    public boolean validarNotas() {
        double exp, proy, notaFinal, prac;
        exp = Double.parseDouble(eExpo.getText().toString());
        proy = Double.parseDouble(eProy.getText().toString());
        prac = Double.parseDouble(ePrac.getText().toString());
        if (exp < 0.0 || exp > 5.0) {
            Toast.makeText(this,  getResources().getString(R.string.notaexpo), Toast.LENGTH_SHORT).show();
            return false;
        }if( proy <0.0 || proy > 5.0) {
            Toast.makeText(this,  getResources().getString(R.string.notaproy), Toast.LENGTH_SHORT).show();
            return false;
        }if (prac < 0.0 || prac > 5.0) {
            Toast.makeText(this,  getResources().getString(R.string.notaprac), Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
    public void calcularNotas() {
        double exp, proy, notaFinal, prac;
        exp = Double.parseDouble(eExpo.getText().toString());
        proy = Double.parseDouble(eProy.getText().toString());
        prac = Double.parseDouble(ePrac.getText().toString());

        notaFinal = (pExpo * exp + pProy * proy + pPrac * prac)/100;
        notaFinal = Math.rint(notaFinal*10)/10;
        tNotaFinal.setText(Double.toString(notaFinal));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) { //inflarnos el menu
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.menu_configurar){
            Toast.makeText(this, getResources().getString(R.string.bConfigurar),Toast.LENGTH_SHORT).show();
            Intent i = new Intent(this, settings.class); //iniciar activitades this = contexto
            i.putExtra("pProy",pProy);//mandar datos no me interesa el tipo
            i.putExtra("pExp",pExpo);
            i.putExtra("pPra",pPrac);
            startActivityForResult(i, 1234);//intent y codigo para identificar la respuesta
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode ==1234 && resultCode==RESULT_OK){

            pProy = data.getExtras().getDouble("prPro");
            pPrac = data.getExtras().getDouble("prPra");
            pExpo = data.getExtras().getDouble("prExpo");
            Toast.makeText(this, "Proyecto = "+Double.toString(pProy)+" Exposicion = "
                    + Double.toString(pExpo)+ " Practicas = "+Double.toString(pPrac),
                    Toast.LENGTH_SHORT).show();
            tpProy.setText("(" + Double.toString(pProy) + "%) ");
            tpPrac.setText("("+Double.toString(pPrac)+"%) ");
            tpExpo.setText("("+Double.toString(pExpo)+"%) ");
        }
    }
}
