package maze.huerto;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements FragmentListado.HortalizasListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentListado frgListado
                =(FragmentListado)getSupportFragmentManager()
                .findFragmentById(R.id.FrgListado);

        frgListado.setHortalizasListener(this);
    }

    @Override
    public void onCorreoSeleccionado(Hortaliza h) {
        boolean hayDetalle =
                (getSupportFragmentManager().findFragmentById(R.id.FrgDetalle) != null);

        if(hayDetalle) {
            ((FragmentDetalle)getSupportFragmentManager()
                    .findFragmentById(R.id.FrgDetalle)).mostrar(h.getNombre(), h.getDescripcion(),h.getDescripcionLarga(), h.getImagenRuta());
        }
        else {
            Bundle b = new Bundle();
            b.putString("NOMBRE", h.getNombre());
            b.putString("DESCRIPCION", h.getDescripcion());
            b.putString("DESCRIPCIONLARGA", h.getDescripcionLarga());
            b.putInt("IMAGEN", h.getImagenRuta());
            Intent i = new Intent(this, DetalleActivity.class);
            i.putExtras(b);
            startActivity(i);
        }
    }

}
