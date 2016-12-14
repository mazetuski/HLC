package maze.sumar;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Button boton;
    private EditText numero1;
    private EditText numero2;
    private TextView resultado;
    private int entero1;
    private int entero2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        boton = (Button) findViewById(R.id.sumar);
        numero1 = (EditText) findViewById(R.id.numero1usuario);
        numero2 = (EditText) findViewById(R.id.numero2usuario);
        resultado = (TextView) findViewById(R.id.resultado);

        boton.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View view) {
                String valor=numero1.getText().toString();
                entero1= Integer.parseInt(valor);
                String valor2=numero2.getText().toString();
                entero2= Integer.parseInt(valor2);
                int suma= entero1+entero2;
                resultado.setText(String.valueOf(suma));
            }
        });
    }
}
