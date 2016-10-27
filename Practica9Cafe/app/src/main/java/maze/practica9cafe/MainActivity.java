package maze.practica9cafe;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button botonMenos, botonMas, botonOrder;
    private TextView cantidad, texto1;
    private int entero;
    private CheckBox crema, chocolate;
    private int precio=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        botonMas = (Button) findViewById(R.id.botonMas);
        botonMenos = (Button) findViewById(R.id.botonMenos);
        botonOrder = (Button) findViewById(R.id.botonOrder);
        cantidad = (TextView) findViewById(R.id.cantidad);
        texto1 = (TextView) findViewById(R.id.texto1);
        crema = (CheckBox) findViewById(R.id.crema);
        chocolate = (CheckBox) findViewById(R.id.chocolate);

        botonMas.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                String valor = cantidad.getText().toString();
                entero = Integer.parseInt(valor);
                entero++;
                cantidad.setText(String.valueOf(entero));
            }
        });

        botonMenos.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                String valor = cantidad.getText().toString();
                entero = Integer.parseInt(valor);
                if (entero > 0)
                    entero--;
                cantidad.setText(String.valueOf(entero));
            }
        });

        botonOrder.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                String valor = cantidad.getText().toString();
                entero = Integer.parseInt(valor);
                if(entero>0){
                    precio=entero*2;
                    if(crema.isChecked())
                        precio+=0.50;
                    if(chocolate.isChecked())
                        precio+=1;
                    Context contexto = getApplicationContext();
                    String mensaje= "Nombre: "+texto1.getText().toString()+"\n"+
                            "Añadir crema: "+crema.isChecked()+"\n"+
                            "Añadir chocolate: "+chocolate.isChecked()+"\n"+
                            "Cantidad: "+cantidad.getText().toString() +"\n"+
                            "Precio: "+precio;
                    int duracion = Toast.LENGTH_SHORT;
                    Toast toast = Toast.makeText(contexto, mensaje, duracion);
                    toast.show();
                }
            }
        });
    }
}
