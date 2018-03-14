package mx.edu.ittepic.a212laboratorio1_sharedpreferences;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText texto1,texto2,texto3,texto4,texto5,texto6,texto7;
    CheckBox baja, lujo;
    Button guardar, mostrar;
    TextView cantidad;
    SeekBar meseros;
    int progressChangedValue = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        texto1=findViewById(R.id.nombre);
        texto2=findViewById(R.id.celular);
        texto3=findViewById(R.id.direccion);
        texto4=findViewById(R.id.fecha);
        texto5=findViewById(R.id.hora);
        texto6=findViewById(R.id.platillos);
        texto7=findViewById(R.id.postres);
        baja=findViewById(R.id.basica);
        lujo=findViewById(R.id.lujo);
        guardar=findViewById(R.id.guardar);
        mostrar=findViewById(R.id.mostrar);
        meseros=findViewById(R.id.meseros);
        cantidad=findViewById(R.id.cantidad);
        meseros.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {


            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                progressChangedValue = progress;
            }

            public void onStartTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub
            }

            public void onStopTrackingTouch(SeekBar seekBar) {
                cantidad.setText("Meseros: "+progressChangedValue);

            }
        });





    }
    public void guardarInf(View view){

        SharedPreferences guardar=getSharedPreferences("general", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor= guardar.edit();
        editor.putString("nombre",texto1.getText().toString());
        editor.putString("celular",texto2.getText().toString());
        editor.putString("direccion",texto3.getText().toString());
        editor.putString("fecha",texto4.getText().toString());
        editor.putString("hora",texto5.getText().toString());
        editor.putString("platillos",texto6.getText().toString());
        editor.putString("postres",texto7.getText().toString());
        if (baja.isChecked()){
            editor.putString("mantel", baja.getText().toString());

        }else{
            editor.putString("mantel", lujo.getText().toString());
        }
        editor.putString("meseros",cantidad.getText().toString());

        editor.apply();
        Toast.makeText(this, "Guardado", Toast.LENGTH_SHORT).show();

    }
    public void mostrar(View view){
        SharedPreferences mostrar= getSharedPreferences("general",Context.MODE_PRIVATE);
        String nombre = mostrar.getString("nombre","");
        String cel = mostrar.getString("celular","");
        String direccion = mostrar.getString("direccion","");
        String fecha = mostrar.getString("fecha","");
        String hora = mostrar.getString("hora","");
        String platillos = mostrar.getString("platillos","");
        String postres = mostrar.getString("postres","");
        String mantel = mostrar.getString("mantel","");
        String cantidad = mostrar.getString("meseros","");

        AlertDialog.Builder mensaje= new AlertDialog.Builder(this);
        mensaje.setTitle("Atencion").
                setMessage("nombre: "+nombre+"\ncelular:  "+cel+"\n direccion: "+direccion+"\nfecha:  "+fecha+"\nhora:  "+hora+"\nplatillos:  "+platillos+"\npostres:  "+postres+"\nmanteleria:  "+mantel+"\n Meseros: "+cantidad).setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        mensaje.show();

    }
}
