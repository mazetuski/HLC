package maze.huerto;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by btt_9 on 13/02/2017.
 */

public class FragmentDetalle extends Fragment{

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.plantillaitem, container, false);
    }

    public void mostrar(String nombre, String descripcion, String descripcionLarga, int imagenRuta){
        TextView textoNombre = (TextView)getView().findViewById(R.id.titulo);
        TextView textoDesc = (TextView)getView().findViewById(R.id.textoitem);
        TextView textoDescLarga = (TextView)getView().findViewById(R.id.descripcionitem);
        ImageView imagen = (ImageView)getView().findViewById(R.id.imagenitem);

        textoNombre.setText(nombre);
        textoDesc.setText(descripcion);
        textoDescLarga.setText(descripcionLarga);
        imagen.setImageResource(imagenRuta);
    }
}
