package com.example.zhenming.demo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class MainActivity extends AppCompatActivity {

    private String todo = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button guardar = findViewById(R.id.button);
        final Button enviar = findViewById(R.id.button2);
        final EditText nombre = findViewById(R.id.editText);
        final EditText apellido = findViewById(R.id.editText2);
        final EditText email = findViewById(R.id.editText3);
        final EditText telefono = findViewById(R.id.editText4);
        final EditText fecha = findViewById(R.id.editText5);


        guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nom = nombre.getText().toString();
                String apel = apellido.getText().toString();
                String correo = email.getText().toString();
                String tel = telefono.getText().toString();
                String fech = fecha.getText().toString();
                String[] fechN = fech.split("/");
                DateTimeFormatter hoy = DateTimeFormatter.ofPattern("yyyy/MM/dd");
                LocalDate localDate = LocalDate.now();
                String[] fechH = hoy.format(localDate).split("/");

                String valid;
                int edad = 0;
                boolean error = false;
                if(TextUtils.isEmpty(nom)){
                    nombre.setError("Digite un nombre");
                    error = true;
                }

                if(TextUtils.isEmpty(apel)){
                    apellido.setError("Digite un apellido");
                    error = true;
                }

                if(TextUtils.isEmpty(correo)){
                    email.setError("Digite un correo");
                    error = true;
                }

                if(TextUtils.isEmpty(tel) && !TextUtils.isDigitsOnly(tel)){
                    telefono.setError("Digite un telefono");
                    error = true;
                }

                if(TextUtils.isEmpty(fech)){
                    fecha.setError("Digite una fecha");
                    error = true;
                }

                edad = Integer.parseInt(fechH[0]) - Integer.parseInt(fechN[0]);
                if (Integer.parseInt(fechN[1]) > Integer.parseInt(fechH[1])) {
                    edad--;
                } else if (Integer.parseInt(fechN[1]) == Integer.parseInt(fechH[1])) {
                    if (Integer.parseInt(fechH[2]) > Integer.parseInt(fechN[2])) {
                        edad--;
                    }
                }
                if(edad <= 0){
                    error = true;
                }


                final TextView mensaje = findViewById(R.id.resultado);
                if(!error){


                    valid = "Su nombre y apellido es : " + nom + " " + apel + "\nSus contactos son: \n"
                            + correo +"\n" + tel +"\nSu edad es:" + edad +"\n\n";

                    todo = todo + valid;
                    mensaje.setText(valid);
                    nombre.setText("");
                    apellido.setText("");
                    email.setText("");
                    telefono.setText("");
                    fecha.setText("");
                    enviar.setVisibility(View.VISIBLE);


                }else{
                    mensaje.setText("No todos los datos fueron ingresados");
                    enviar.setVisibility(View.INVISIBLE);
                }
            }

        });

        enviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MainActivity.this, Main2Activity.class);
                intent.putExtra("nombre", todo);
                startActivity(intent);
            }
        });

    }
}
