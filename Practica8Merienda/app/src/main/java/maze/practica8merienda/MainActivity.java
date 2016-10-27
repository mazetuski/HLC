package maze.practica8merienda;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Button boton;
    private ImageView imagen;
    private TextView texto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        boton = (Button) findViewById(R.id.boton);
        imagen = (ImageView) findViewById(R.id.imagen);
        texto =(TextView) findViewById(R.id.texto);

        boton.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                texto.setText("I'm so full");
                imagen.setImageResource(R.drawable.after_cookie);
            }
        });
    }
}
