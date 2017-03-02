package maze.tiempo;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONObject;

import java.util.Locale;

/**
 * Created by maze on 01/03/2017.
 */

public class ClimaFragment extends Fragment{
    TextView ciudad;
    TextView detalles;
    TextView temperatura;

    Handler handler;

    public ClimaFragment(){
        handler = new Handler();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_layout, container, false);
        ciudad = (TextView)rootView.findViewById(R.id.ciudad);
        detalles = (TextView)rootView.findViewById(R.id.detalles);
        temperatura = (TextView)rootView.findViewById(R.id.temperatura);
        actualizarDatos(new CiudadPreference(getActivity()).getCity());

        return rootView;
    }
    private void actualizarDatos(final String city){
        new Thread(){
            public void run(){
                final JSONObject json = ObtenerClima.getJSON(getActivity(), city);
                if(json == null){
                    handler.post(new Runnable(){
                        public void run(){
                            Toast.makeText(getActivity(),
                                    getActivity().getString(R.string.invalido),
                                    Toast.LENGTH_LONG).show();
                        }
                    });
                } else {
                    handler.post(new Runnable(){
                        public void run(){
                            renderWeather(json);
                        }
                    });
                }
            }
        }.start();
    }

    private void renderWeather(JSONObject json){
        try {
            ciudad.setText(json.getString("name").toUpperCase(Locale.US) +
                    ", " +
                    json.getJSONObject("sys").getString("country"));

            JSONObject details = json.getJSONArray("weather").getJSONObject(0);
            JSONObject main = json.getJSONObject("main");
            detalles.setText(
                    details.getString("description").toUpperCase(Locale.US) +
                            "\n" + "Humedad: " + main.getString("humidity") + "%" +
                            "\n" + "Presión: " + main.getString("pressure") + " hPa");

            temperatura.setText(
                    String.format("%.2f", main.getDouble("temp"))+ " ℃");


        }catch(Exception e){
            Log.e("Tiempo", "No se han encontrado datos");
        }
    }

    public void changeCity(String city){
       actualizarDatos(city);
    }
}
