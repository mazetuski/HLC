package maze.huerto;

import android.support.v4.app.Fragment;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by maze on 09/02/2017.
 */

public class HortalizaAdapter extends ArrayAdapter<Hortaliza> {
    private Context context;
    private ArrayList<Hortaliza> hortalizas;

    public HortalizaAdapter(Fragment context, ArrayList<Hortaliza> hortalizas) {
        super(context.getActivity(), R.layout.plantillalista, hortalizas);
        this.context = context.getActivity();
        this.hortalizas = hortalizas;
    }

    public View getView(int position,View convertView, ViewGroup parent) {

        LayoutInflater inflater = LayoutInflater.from(context);
        View item = inflater.inflate(R.layout.plantillalista, null);

        ImageView imagen = (ImageView) item.findViewById(R.id.imagen);
        imagen.setImageResource(hortalizas.get(position).getImagenRuta());

        TextView nombre = (TextView) item.findViewById(R.id.textoPlant);
        nombre.setText(hortalizas.get(position).getNombre());

        TextView descripcion = (TextView) item.findViewById(R.id.descripcion);
        descripcion.setText(hortalizas.get(position).getDescripcion());

        return item;
    }
}
