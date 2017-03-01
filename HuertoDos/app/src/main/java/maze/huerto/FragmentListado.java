package maze.huerto;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by btt_9 on 13/02/2017.
 */

public class FragmentListado extends Fragment {

    private ListView lista;
    private ArrayList<Hortaliza> hortalizas = new ArrayList<Hortaliza>();
    Activity context;
    private HortalizasListener listener;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_listado, container, false);
    }

    @Override
    public void onActivityCreated(Bundle state) {
        super.onActivityCreated(state);

        lista = (ListView)getView().findViewById(R.id.LstListado);

        Hortaliza h1 = new Hortaliza("Tomate", "Tomate es el fruto de la planta conocida como tomatera",
                "Solanum lycopersicum, conocido comúnmente como tomate, tomatera o jitomate, es una especie de planta herbácea del género Solanum de la familia Solanaceae;" +
                        " es nativa de Centro y Sudamérica y su uso como comida se habría originado en México hace unos 2500 años.5 El origen del nombre proviene de la palabra " +
                        "náhuatl (lengua mexica), tomatl.\n" +
                        "Es cultivada en el mundo entero para su consumo tanto fresco como procesado de diferentes modos (salsa, puré, zumo, deshidratado, enlatado).", R.drawable.tomate);

        Hortaliza h2 = new Hortaliza("Berenjena", "Es una planta de fruto comestible, generalmente anual, del género Solanum", "La berenjena (Solanum melongena) es una planta de fruto comestible," +
                " generalmente anual, del género Solanum dentro de la familia de las solanáceas. Fruto in situ (var. «Jaspeada de Gandía») - cáliz espinoso acrescente." +
                " Variabilidad de la planta, o porqué se llama eggplant (planta-huevo) en Inglés.", R.drawable.berenjena);

        Hortaliza h3 = new Hortaliza("Zanahoria", "La zanahoria es una planta herbácea hojas recortadas, flores blancas y raíz puntiaguda",
                "Daucus carota subespecie sativus, llamada popularmente zanahoria, es una hortaliza que pertenece a la familia de las umbelíferas, también denominadas apiáceas," +
                        " y considerada la especie más importante y de mayor consumo dentro de esta familia. Es la forma domesticada de la zanahoria silvestre," +
                        " oriunda de Europa y Asia sudoccidental. Se cultiva por su raíz mucho más grande, sabrosa y de textura menos fibrosa, pero continúa siendo la misma especie.", R.drawable.zanahoria);

        Hortaliza h4 = new Hortaliza("Rábano", " Es una planta de la familia Brassicaceae que se cultiva por sus raíces comestibles.", "" +
                "Raphanus sativus, el rábano, es una planta de la familia Brassicaceae que se cultiva por sus raíces comestibles.\n" +
                "\n" +
                "Hay ciertas subespecies que reciben nombres vulgares diferentes, por ejemplo, R. sativus var. sativus es el rábano o rabanito y " +
                "R. sativus var. longipinnatus se conoce, entre otros nombres, como rábano blanco, rábano japonés o daikon.\n" +
                "\n" +
                "Observaciones: El género Raphanus podría concebirse como constituido por una sola especie, Raphanus raphanistrum, muy polimorfa," +
                " de la que habría surgido por domesticación el rábano cultivado (Raphanus sativus) que," +
                " aquí, sí se considera una especie1 aunque no esté reconocida aún como tal, y su validez sujeta a revisión", R.drawable.rabano);

        hortalizas.add(h1);
        hortalizas.add(h2);
        hortalizas.add(h3);
        hortalizas.add(h4);

        HortalizaAdapter myAdapter = new HortalizaAdapter(this, hortalizas);
        lista.setAdapter(myAdapter);

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> list, View view, int pos, long id) {
                if (listener!=null) {
                    listener.onCorreoSeleccionado(
                            (Hortaliza) lista.getAdapter().getItem(pos));
                }
            }
        });
    }

    public interface HortalizasListener {
        void onCorreoSeleccionado(Hortaliza c);
    }

    public void setHortalizasListener(HortalizasListener listener) {
        this.listener=listener;
    }
}
