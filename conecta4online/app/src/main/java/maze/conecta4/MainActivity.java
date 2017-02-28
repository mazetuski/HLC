package maze.conecta4;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.preference.PreferenceManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.view.ContextThemeWrapper;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
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
import java.util.Timer;
import java.util.TimerTask;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

public class MainActivity extends AppCompatActivity {

    private final int ids[][] = {
            {R.id.circ00, R.id.circ01, R.id.circ02, R.id.circ03, R.id.circ04, R.id.circ05, R.id.circ06},
            {R.id.circ10, R.id.circ11, R.id.circ12, R.id.circ13, R.id.circ14, R.id.circ15, R.id.circ16},
            {R.id.circ20, R.id.circ21, R.id.circ22, R.id.circ23, R.id.circ24, R.id.circ25, R.id.circ26},
            {R.id.circ30, R.id.circ31, R.id.circ32, R.id.circ33, R.id.circ34, R.id.circ35, R.id.circ36},
            {R.id.circ40, R.id.circ41, R.id.circ42, R.id.circ43, R.id.circ44, R.id.circ45, R.id.circ46},
            {R.id.circ50, R.id.circ51, R.id.circ52, R.id.circ53, R.id.circ54, R.id.circ55, R.id.circ56},
    };

    Game juego = new Game(Game.JUGADOR1);
    RequestQueue queue;
    private String idPartida;
    private String ultimoColor = "2";
    private final String URL = "192.168.1.135";
    private int contadorDialog = 0;
    private boolean abandona = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        queue = VolleySingleton.getInstance(this).getRequestQueue();
        final Bundle b = this.getIntent().getExtras();

        if (!b.getString("ID").equals("")) {
            idPartida = b.getString("ID");
            cambiarEstadoPartida(b, b.getString("ESTAD"));
            Timer timer = new Timer();
            timer.scheduleAtFixedRate(new TimerTask() {
                @Override
                public void run() {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            if (juego.getEstado().equals("ET") && contadorDialog == 0 && !abandona) {
                                contadorDialog++;
                                AlertDialog dialog = crearDialogAbandono();
                                dialog.show();
                            }

                            if (juego.isTerminado() && contadorDialog == 1) {
                                contadorDialog++;
                                cambiarEstadoPartida(b, "T");
                                AlertDialog dialog = crearDialogOnline();
                                dialog.show();
                            }
                            if (juego.isTerminado() && contadorDialog == 0)
                                contadorDialog++;
                            annadirMovimientos(b.getString("ID"));
                            comprobarPartidaTerminadaOnline(b);
                        }
                    });
                }

            }, 1000, 500);
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.acercaDe:
                startActivity(new Intent(this, About.class));
                return true;
            case R.id.enviarMensaje:
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_SUBJECT, "CONECTA 4");
                intent.putExtra(Intent.EXTRA_TEXT, "Nueva aplicación Android");
                startActivity(intent);
                return true;
            case R.id.ajustes:
                startActivity(new Intent(this, Preferencias.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onBackPressed() {
        AlertDialog dialog = crearDialogSalir();
        dialog.show();
    }

    @Override
    public void onPause() {
        super.onPause();
        Musica.stop();
    }

    @Override
    public void onResume() {
        super.onResume();
        Boolean play = false;
        SharedPreferences sharedPreferences =
                PreferenceManager.getDefaultSharedPreferences(this);
        if (sharedPreferences.contains(Preferencias.PLAY_MUSIC))
            play = sharedPreferences.getBoolean(Preferencias.PLAY_MUSIC,
                    Preferencias.PLAY_MUSIC_DEFAULT);
        if (play == true)
            Musica.play(this, R.raw.musica);

       /* if (sharedPreferences.contains(Preferencias.PLAYER_INICIAL_KEY)) {
            String jug = sharedPreferences.getString(Preferencias.PLAYER_INICIAL_KEY, Preferencias.PLAYER_INICIAL_DEFAULT);
            if (jug != Preferencias.PLAYER_INICIAL_DEFAULT) {
                reiniciarPartida();
                juego.cambiarTurno();
            }
        }*/
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("TURNO", juego.getTurno());
        outState.putBoolean("ESTADO", juego.isTerminado());
        outState.putString("TABLERO", juego.tableroToString());
    }

    @Override
    protected void onRestoreInstanceState(Bundle inState) {
        super.onRestoreInstanceState(inState);
        juego.setTurno(inState.getInt("TURNO"));
        juego.setTerminado(inState.getBoolean("ESTADO"));
        juego.stringToTablero(inState.getString("TABLERO"));
        pintarTablero();
    }


    public void pulsado(View v) {
        Bundle b = this.getIntent().getExtras();
        if (!juego.isTerminado()) {
            for (int i = 0; i < ids.length; i++)
                for (int j = 0; j < ids[i].length; j++) {
                    if (ids[i][j] == v.getId())
                        if (b.getString("ID").equals(""))
                            jugar(j);
                        else {
                            if (juego.getEstado().equals("ET")) {
                                AlertDialog dialog = crearDialogAbandono();
                                dialog.show();
                            } else if (juego.isTerminado()) {
                                AlertDialog dialog = crearDialogOnline();
                                dialog.show();
                            } else
                                jugarOnline(j, b.getString("ID"), b.getString("COLOR"));
                        }
                    if (b.getString("ID").equals("") && juego.getTurno() == juego.getJugador2() && !juego.isTerminado()) {
                        int col = juego.maquinaCol(j);
                        jugar(col);
                    }
                }
        }
        if (juego.isTerminado() && b.getString("ID").equals("")) {
            AlertDialog dialog = crearDialog();
            dialog.show();
        }
    }

    public void jugar(int j) {
        for (int k = 0; k < ids.length; k++)
            if ((k == (ids.length - 1) || !juego.isVacio(k + 1, j)) && juego.isVacio(k, j)) {
                ImageButton boton2 = (ImageButton) findViewById(ids[k][j]);
                juego.setFicha(k, j, juego.getTurno());
                if (juego.getTurno() == juego.getJugador1())
                    if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE)
                        boton2.setImageResource(R.drawable.circulojug1land);
                    else
                        boton2.setImageResource(R.drawable.circulojug1);
                else {
                    if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE)
                        boton2.setImageResource(R.drawable.circulojug2land);
                    else
                        boton2.setImageResource(R.drawable.circulojug2);
                }
                if (juego.comprobarJugadaGanadora(juego.getFila(k), juego.getColumna(j), juego.getOblicuaIzq(k, j), juego.getOblicuaDer(k, j))) {
                    Toast filaGana = Toast.makeText(getApplicationContext(), "Cuatro en raya", Toast.LENGTH_SHORT);
                    filaGana.show();
                } else
                    juego.cambiarTurno();
            }
    }

    public void jugarOnline(int j, String id, String colorTurno) {
        if (!colorTurno.equals(ultimoColor)) {
            for (int k = 0; k < ids.length; k++)
                if ((k == (ids.length - 1) || !juego.isVacio(k + 1, j)) && juego.isVacio(k, j)) {
                    ImageButton boton2 = (ImageButton) findViewById(ids[k][j]);
                    juego.setFicha(k, j, juego.getTurno());
                    String url = "http://" + URL + "/pages/Ejercicios/dbConecta4/move.php?game=" + id + "&x=" + k + "&y=" + j + "&color=" + colorTurno;
                    StringRequest request = new StringRequest(url, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {

                        }
                    }, new Response.ErrorListener() {

                        @Override
                        public void onErrorResponse(VolleyError error) {
                            //something happened, treat the error.
                        }
                    });

                    queue.add(request);
                    if (juego.getTurno() == juego.getJugador1())
                        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE)
                            boton2.setImageResource(R.drawable.circulojug1land);
                        else
                            boton2.setImageResource(R.drawable.circulojug1);
                    else {
                        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE)
                            boton2.setImageResource(R.drawable.circulojug2land);
                        else
                            boton2.setImageResource(R.drawable.circulojug2);
                    }
                    if (juego.comprobarJugadaGanadora(juego.getFila(k), juego.getColumna(j), juego.getOblicuaIzq(k, j), juego.getOblicuaDer(k, j))) {
                        Toast filaGana = Toast.makeText(getApplicationContext(), "Cuatro en raya", Toast.LENGTH_SHORT);
                        filaGana.show();
                    } /*else
                        juego.cambiarTurno();*/
                }
        } else {
            Toast toast = Toast.makeText(getApplicationContext(), "No es tu turno", Toast.LENGTH_LONG);
            toast.show();
        }
    }

    public void pintarTablero() {
        for (int i = 0; i < ids.length; i++)
            for (int j = 0; j < ids[i].length; j++) {
                ImageButton boton = (ImageButton) findViewById(ids[i][j]);
                switch (juego.getTablero()[i][j]) {
                    case Game.JUGADOR1:
                        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE)
                            boton.setImageResource(R.drawable.circulojug1land);
                        else
                            boton.setImageResource(R.drawable.circulojug1);
                        break;
                    case Game.JUGADOR2:
                        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE)
                            boton.setImageResource(R.drawable.circulojug2land);
                        else
                            boton.setImageResource(R.drawable.circulojug2);
                        break;
                }
            }
    }

    public void pintarFicha(int i, int j, String color) {
        ImageButton boton = (ImageButton) findViewById(ids[i][j]);
        if (color.equals(String.valueOf(Game.JUGADOR1)))
            if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE)
                boton.setImageResource(R.drawable.circulojug1land);
            else
                boton.setImageResource(R.drawable.circulojug1);
        else {
            if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE)
                boton.setImageResource(R.drawable.circulojug2land);
            else
                boton.setImageResource(R.drawable.circulojug2);
        }
    }

    public void cambiarEstadoPartida(Bundle b, String estado) {
        String url = "http://" + URL + "/pages/Ejercicios/dbConecta4/cambiarEstado.php?id=" + b.getString("ID") + "&estado=" + estado;
        StringRequest request = new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                //something happened, treat the error.
            }
        });

        queue.add(request);
    }

    public void comprobarPartidaTerminadaOnline(Bundle b) {
        String url = "http://" + URL + "/pages/Ejercicios/dbConecta4/obtenerEstado.php?id=" + b.getString("ID");
        StringRequest request = new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
                    DocumentBuilder db = dbf.newDocumentBuilder();
                    InputSource is = new InputSource();
                    is.setCharacterStream(new StringReader(response));
                    Document doc = db.parse(is);
                    NodeList nodes = doc.getElementsByTagName("estado");
                    for (int i = 0; i < nodes.getLength(); i++) {
                        Element element = (Element) nodes.item(i);
                        if (element.getAttribute("estado").equals("T"))
                            juego.setTerminado(true);
                        if (element.getAttribute("estado").equals("ET"))
                            juego.setEstado("ET");
                    }

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


    public AlertDialog crearDialogOnline() {
        AlertDialog.Builder builder = new AlertDialog.Builder(new ContextThemeWrapper(this, R.style.AppTheme));
        builder.setTitle("Ha Ganado " + (ultimoColor.equals("1") ? "Jugador 1" : "Jugador 2"));
        builder.setNegativeButton("Salir", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                MainActivity.this.finish();
            }
        });
        return builder.create();
    }

    public AlertDialog crearDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(new ContextThemeWrapper(this, R.style.AppTheme));
        builder.setTitle("Ha Ganado " + (juego.getTurno() == 1 ? "Jugador 1" : "Jugador 2"));
        builder.setMessage("Quiere jugar otra vez?");
        builder.setPositiveButton("Jugar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = getIntent();
                finish();
                startActivity(intent);
            }
        });

        builder.setNegativeButton("Salir", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                MainActivity.this.finish();
            }
        });
        return builder.create();
    }

    public AlertDialog crearDialogAbandono() {
        AlertDialog.Builder builder = new AlertDialog.Builder(new ContextThemeWrapper(this, R.style.AppTheme));
        builder.setTitle("El rival ha abandonado");
        builder.setPositiveButton("Salir", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                MainActivity.this.finish();
            }
        });
        return builder.create();
    }


    public AlertDialog crearDialogSalir() {
        AlertDialog.Builder builder = new AlertDialog.Builder(new ContextThemeWrapper(this, R.style.AppTheme));
        builder.setTitle("¿Esta seguro de que quiere salir?");
        builder.setPositiveButton("Salir", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String url = "http://" + URL + "/pages/Ejercicios/dbConecta4/cambiarEstado.php?id=" + idPartida + "&estado=ET";
                StringRequest request = new StringRequest(url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //something happened, treat the error.
                    }
                });
                abandona = true;
                queue.add(request);
                MainActivity.this.finish();
            }
        });

        builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        return builder.create();
    }

    public void annadirMovimientos(String id) {
        String url = "http://" + URL + "/pages/Ejercicios/dbConecta4/moves.php?game=" + id;
        StringRequest request = new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {

                    DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
                    DocumentBuilder db = dbf.newDocumentBuilder();
                    InputSource is = new InputSource();
                    is.setCharacterStream(new StringReader(response));
                    Document doc = db.parse(is);
                    NodeList nodes = doc.getElementsByTagName("move");
                    for (int i = 0; i < nodes.getLength(); i++) {
                        Element element = (Element) nodes.item(i);
                        pintarFicha(Integer.parseInt(element.getAttribute("x")), Integer.parseInt(element.getAttribute("y")), element.getAttribute("color"));
                        juego.setFicha(Integer.parseInt(element.getAttribute("x")), Integer.parseInt(element.getAttribute("y")), Integer.parseInt(element.getAttribute("color")));


                    }
                    Element elem = (Element) nodes.item(nodes.getLength() - 1);
                    ultimoColor = elem.getAttribute("color");
                    juego.cambiarTurno(Integer.parseInt(ultimoColor));
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

   /* public void reiniciarPartida() {
        for (int i = 0; i < ids.length; i++)
            for (int j = 0; j < ids[i].length; j++)
                ids[i][j] = 0;

        juego.vaciarTablero();
    }*/
}
