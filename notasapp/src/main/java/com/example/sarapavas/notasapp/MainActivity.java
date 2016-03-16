package com.example.sarapavas.notasapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText ePrac, eExpo, eProy;
    TextView tNotaFinal;
    Button calcular;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ePrac = (EditText)findViewById(R.id.prac);
        eProy= (EditText)findViewById(R.id.proy);
        eExpo = (EditText)findViewById(R.id.expo);
        calcular = (Button)findViewById(R.id.calc);
        tNotaFinal = (TextView)findViewById(R.id.NotaFinal);
        calcular.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                double exp, proy, notaFinal, prac;
                exp = Double.parseDouble(eExpo.getText().toString());
                proy =  Double.parseDouble(eProy.getText().toString());
                prac =  Double.parseDouble(ePrac.getText().toString());

                notaFinal = 0.2*exp + 0.4*proy+0.4*prac;

                tNotaFinal.setText(Double.toString(notaFinal));


            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.menu_configurar){
            Toast.makeText(this, "Ha presionado opcion configurar",Toast.LENGTH_SHORT).show();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
