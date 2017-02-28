package maze.conecta4;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import java.io.StringReader;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

public class InterfazOnline extends AppCompatActivity {
    private String urlServer = "192.168.1.135";
    //private String partida = "";
    private String moves = "";
    private Button crearMov, crearPartida;
    private EditText texto, x, y;
    private ListView partidas;
    private int color = 1;
    private int ultimoTurno = 1;
    RequestQueue queue;
    private ArrayList<String> partida;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.interfazonline);

        //crearMov = (Button) findViewById(R.id.crearMovimiento);
        crearPartida = (Button) findViewById(R.id.crearPartida);
        //x = (EditText) findViewById(R.id.x);
        //y = (EditText) findViewById(R.id.y);
        //crearMov = (Button) findViewById(R.id.crearMovimiento);
        partidas = (ListView) findViewById(R.id.partidas);
        //movimientos = (TextView) findViewById(R.id.movimientos);

        queue = VolleySingleton.getInstance(this).getRequestQueue();

        mostrarPartidas();


        /*mostrarMovimientos();*/

        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mostrarPartidas();
                    }
                });
            }

        }, 3000, 5000);


        crearPartida.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //String url = "http://192.168.115.39/pages/Ejercicios/dbConecta4/start.php";
                String url = "http://" + urlServer + "/pages/Ejercicios/dbConecta4/start.php";
                StringRequest request = new StringRequest(url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            String idPartida = "";
                            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
                            DocumentBuilder db = dbf.newDocumentBuilder();
                            InputSource is = new InputSource();
                            is.setCharacterStream(new StringReader(response));
                            Document doc = db.parse(is);
                            NodeList nodes = doc.getElementsByTagName("game");
                            for (int i = 0; i < nodes.getLength(); i++) {
                                Element element = (Element) nodes.item(i);
                                idPartida = element.getAttribute("id");

                            }
                            Intent intent = new Intent(InterfazOnline.this, MainActivity.class);
                            Bundle b = new Bundle();
                            b.putString("ID", idPartida);
                            b.putString("COLOR", "1");
                            b.putString("ESTAD", "J");
                            intent.putExtras(b);
                            mostrarPartidas();
                            startActivity(intent);

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //something happened, treat the error.
                    }
                });

                queue.add(request);

            }
        });

        partidas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String item = (String) parent.getItemAtPosition(position);
                String[] arrItem = item.split("\\s");
                String idPartida = arrItem[1];
                Intent intent = new Intent(InterfazOnline.this, MainActivity.class);
                Bundle b = new Bundle();
                b.putString("ID", idPartida);
                b.putString("COLOR", "2");
                b.putString("ESTAD", "E");
                intent.putExtras(b);
                startActivity(intent);
                /*Toast toast = Toast.makeText(getApplicationContext(), idPartida, Toast.LENGTH_LONG);
                toast.show();*/
            }
        });

    }

    private void mostrarPartidas() {
        // String url = "http://192.168.115.39/pages/Ejercicios/dbConecta4/games.php";
        partida = new ArrayList<String>();
        String url = "http://" + urlServer + "/pages/Ejercicios/dbConecta4/games.php";
        StringRequest request = new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
                    DocumentBuilder db = dbf.newDocumentBuilder();
                    InputSource is = new InputSource();
                    is.setCharacterStream(new StringReader(response));
                    Document doc = db.parse(is);
                    NodeList nodes = doc.getElementsByTagName("game");
                    for (int i = 0; i < nodes.getLength(); i++) {
                        Element element = (Element) nodes.item(i);
                        //                 NodeList name = element.getElementsByTagName("move");
                        //                 Element line = (Element) name.item(0);
                        partida.add("Partida " + element.getAttribute("id") + "\n");

                        /*//jugada =  getCharacterDataFromElement(line); //Utilizada para sacar la información de las etiquetas no de los atributos
                        Toast.makeText(context,jugada
                                , Toast.LENGTH_SHORT)
                                .show();

                        */
                    }
                    ArrayAdapter myAdapter = new ArrayAdapter(getApplicationContext(), R.layout.simpletext, R.id.simpleTexto, partida);
                    partidas.setAdapter(myAdapter);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                //something happened, treat the error.
            }
        });

        queue.add(request);
    }

   /* private void mostrarMovimientos() {
        //String url = "http://192.168.115.39/pages/Ejercicios/dbConecta4/moves.php?game=11";
        String url = "http://192.168.115.170/php/pages/conecta4/moves.php?game=5";
        StringRequest request = new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    moves = "";
                    DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
                    DocumentBuilder db = dbf.newDocumentBuilder();
                    InputSource is = new InputSource();
                    is.setCharacterStream(new StringReader(response));
                    Document doc = db.parse(is);
                    NodeList nodes = doc.getElementsByTagName("move");
                    for (int i = 0; i < nodes.getLength(); i++) {
                        Element element = (Element) nodes.item(i);
                        //                 NodeList name = element.getElementsByTagName("move");
                        //                 Element line = (Element) name.item(0);
                        moves += "X " + element.getAttribute("x") + " Y " + element.getAttribute("y") + " Color " + element.getAttribute("color") + "\n";

                        //jugada =  getCharacterDataFromElement(line); //Utilizada para sacar la información de las etiquetas no de los atributos
                        Toast.makeText(context,jugada
                                , Toast.LENGTH_SHORT)
                                .show();


                    }
                    Element elem = (Element) nodes.item(nodes.getLength() - 1);
                    ultimoTurno = Integer.parseInt(elem.getAttribute("color"));
                    movimientos.setText(moves);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                //something happened, treat the error.
            }
        });

        queue.add(request);
    }*/
}
