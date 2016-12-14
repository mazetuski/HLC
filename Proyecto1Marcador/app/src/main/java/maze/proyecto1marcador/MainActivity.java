package maze.proyecto1marcador;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Button masUnoLocal, masDosLocal, masTresLocal;
    private Button masUnoVisitante, masDosVisitante, masTresVisitante;
    private Button menosUnoLocal, menosUnoVisitante;
    private TextView marcadorLocal, marcadorVisitante;
    private int marcador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        masUnoLocal = (Button) findViewById(R.id.masUnoLocal);
        masDosLocal = (Button) findViewById(R.id.masDosLocal);
        masTresLocal = (Button) findViewById(R.id.masTresLocal);
        masUnoVisitante = (Button) findViewById(R.id.masUnoVisitante);
        masDosVisitante = (Button) findViewById(R.id.masDosVisitante);
        menosUnoLocal = (Button) findViewById(R.id.menosUnoLocal);
        menosUnoVisitante = (Button) findViewById(R.id.menosUnoVisitante);
        masTresVisitante = (Button) findViewById(R.id.masTresVisitante);
        marcadorLocal = (TextView) findViewById(R.id.marcadorLocal);
        marcadorVisitante = (TextView) findViewById(R.id.marcadorVisitante);

        comprobarBotonesLocal();
        comprobarBotonesVisitante();

        masUnoLocal.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                marcador = Integer.parseInt(marcadorLocal.getText().toString());
                marcador++;
                marcadorLocal.setText(String.valueOf(marcador));
                comprobarBotonesLocal();
            }
        });

        masDosLocal.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                marcador = Integer.parseInt(marcadorLocal.getText().toString());
                marcador+=2;
                marcadorLocal.setText(String.valueOf(marcador));
                comprobarBotonesLocal();
            }
        });

        masTresLocal.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                marcador = Integer.parseInt(marcadorLocal.getText().toString());
                marcador+=3;
                marcadorLocal.setText(String.valueOf(marcador));
                comprobarBotonesLocal();
            }
        });

        masUnoVisitante.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                marcador = Integer.parseInt(marcadorVisitante.getText().toString());
                marcador++;
                marcadorVisitante.setText(String.valueOf(marcador));
                comprobarBotonesVisitante();
            }
        });

        masDosVisitante.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                marcador = Integer.parseInt(marcadorVisitante.getText().toString());
                marcador+=2;
                marcadorVisitante.setText(String.valueOf(marcador));
                comprobarBotonesVisitante();
            }
        });

        masTresVisitante.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                marcador = Integer.parseInt(marcadorVisitante.getText().toString());
                marcador+=3;
                marcadorVisitante.setText(String.valueOf(marcador));
                comprobarBotonesVisitante();
            }
        });

        menosUnoLocal.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                marcador = Integer.parseInt(marcadorLocal.getText().toString());
                marcadorLocal.setText(String.valueOf(marcador - 1));
                comprobarBotonesLocal();
            }
        });


        menosUnoVisitante.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                marcador = Integer.parseInt(marcadorVisitante.getText().toString());
                marcadorVisitante.setText(String.valueOf(marcador - 1));
                comprobarBotonesVisitante();
            }
        });
    }

    private void comprobarBotonesLocal() {
        marcador = Integer.parseInt(marcadorLocal.getText().toString());
        if ((marcador - 1) < 0)
            menosUnoLocal.setEnabled(false);
        else
            menosUnoLocal.setEnabled(true);
    }

    private void comprobarBotonesVisitante() {
        marcador = Integer.parseInt(marcadorVisitante.getText().toString());
        if ((marcador - 1) < 0)
            menosUnoVisitante.setEnabled(false);
        else
            menosUnoVisitante.setEnabled(true);

    }
}
