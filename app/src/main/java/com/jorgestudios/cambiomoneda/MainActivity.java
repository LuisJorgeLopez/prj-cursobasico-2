package com.jorgestudios.cambiomoneda;

import android.annotation.SuppressLint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener {
    private RadioGroup radioGroup;
    private EditText monto, tasa1,tasa2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        radioGroup = findViewById(R.id.radioGroup);
        radioGroup.setOnCheckedChangeListener(this);
        Button button = findViewById(R.id.calcular);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                monto = findViewById(R.id.monto);
                tasa1 = findViewById(R.id.tasa1);
                tasa2 = findViewById(R.id.tasa2);
                TextView cambio1 = findViewById(R.id.visablePrimary);
                TextView cambio2 = findViewById(R.id.visableSecond);
                if (monto.getText().toString().isEmpty() || tasa1.getText().toString().isEmpty() || tasa2.getText().toString().isEmpty()) {
                    Toast.makeText(MainActivity.this, "Asegurese de llenar todos los campos requeridos", Toast.LENGTH_SHORT).show();
                }
                else {
                    RadioButton moneda = findViewById(radioGroup.getCheckedRadioButtonId());
                    Integer montoCalcular = Integer.valueOf(String.valueOf(monto.getText()));
                    Float tasaFinal1 = Float.valueOf(String.valueOf(tasa1.getText()));
                    Float tasaFinal2 = Float.valueOf(String.valueOf(tasa2.getText()));
                    if (moneda.getText().equals("DOP")) {
                        cambio1.setText(String.format("de DOP a USD: %s", montoCalcular * tasaFinal1));
                        cambio2.setText(String.format("de DOP a EUR: %s", montoCalcular * tasaFinal2));
                    }
                    else {
                        if (moneda.getText().equals("USD")) {
                            cambio1.setText(String.format("de USD a DOP: %s", montoCalcular * tasaFinal1));
                            cambio2.setText(String.format("de USD a EUR: %s", montoCalcular * tasaFinal2));
                        }
                        else {
                            cambio1.setText(String.format("de EUR a DOP: %s", montoCalcular * tasaFinal1));
                            cambio2.setText(String.format("de EUR a USD: %s", montoCalcular * tasaFinal2));
                        }

                    }

                }
            }
        });
    }
    @SuppressLint("SetTextI18n")
    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int i) {
        TextView textview1 = findViewById(R.id.visablePrimary);
        TextView textview2 = findViewById(R.id.visableSecond);
        TextView textViewTexto1 = findViewById(R.id.tasaTexto1);
        TextView textViewTexto2 = findViewById(R.id.tasaTexto2);
        RadioButton selected = findViewById(radioGroup.getCheckedRadioButtonId());
        if (selected.getText().equals("DOP")) {
            textview1.setText("de DOP a USD");
            textview2.setText("de DOP a EUR");
            textViewTexto1.setText("Tasa a calcular USD : ");
            textViewTexto2.setText("Tasa a calcular EUR : ");
        }
        else {
            if (selected.getText().equals("USD")) {
                textview1.setText("de USD a DOP");
                textview2.setText("de USD a EUR");
                textViewTexto1.setText("Tasa a calcular DOP : ");
                textViewTexto2.setText("Tasa a calcular EUR : ");
            }
            else {
                textview1.setText("de EUR a DOP");
                textview2.setText("de EUR a USD");
                textViewTexto1.setText("Tasa a calcular DOP : ");
                textViewTexto2.setText("Tasa a calcular USD : ");
            }
        }
    }
}

