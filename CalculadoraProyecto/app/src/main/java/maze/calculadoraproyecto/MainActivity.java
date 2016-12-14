package maze.calculadoraproyecto;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    private Button boton1, boton2, boton3, boton4, boton5, boton6, boton7,
            boton8, boton9, boton0, botonC, botonPunto, botonDividir,
            botonMultiplicar, botonSumar, botonRestar, botonIgual, botonEstado;
    private TextView resultadoTexto, textoOperacion;
    private String numeroPulsado = "";
    private double resultadoNumero = 0;
    private boolean borrar = false;
    private boolean sumar = true;
    private boolean restar = true;
    private boolean multiplicar = true;
    private boolean dividir = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        boton1 = (Button) findViewById(R.id.boton1);
        boton2 = (Button) findViewById(R.id.boton2);
        boton3 = (Button) findViewById(R.id.boton3);
        boton4 = (Button) findViewById(R.id.boton4);
        boton5 = (Button) findViewById(R.id.boton5);
        boton6 = (Button) findViewById(R.id.boton6);
        boton7 = (Button) findViewById(R.id.boton7);
        boton8 = (Button) findViewById(R.id.boton8);
        boton9 = (Button) findViewById(R.id.boton9);
        boton0 = (Button) findViewById(R.id.boton0);
        botonC = (Button) findViewById(R.id.botonC);
        botonPunto = (Button) findViewById(R.id.botonPunto);
        botonDividir = (Button) findViewById(R.id.botonDividir);
        botonMultiplicar = (Button) findViewById(R.id.botonMultiplicar);
        botonSumar = (Button) findViewById(R.id.botonSumar);
        botonRestar = (Button) findViewById(R.id.botonRestar);
        botonIgual = (Button) findViewById(R.id.botonIgual);
        botonEstado = (Button) findViewById(R.id.botonMasMenos);
        resultadoTexto = (TextView) findViewById(R.id.textoResultado);
        textoOperacion = (TextView) findViewById(R.id.textoOperacion);

        boton1.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                comprobarBorrado();
                textoOperacion.setText(textoOperacion.getText().toString() + "1");
                //resultadoTexto.setText(resultadoTexto.getText().toString() + "1");
                numeroPulsado += "1";
            }
        });
        boton2.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                comprobarBorrado();
                textoOperacion.setText(textoOperacion.getText().toString() + "2");
                //resultadoTexto.setText(resultadoTexto.getText().toString() + "2");
                numeroPulsado += "2";
            }
        });
        boton3.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                comprobarBorrado();
                textoOperacion.setText(textoOperacion.getText().toString() + "3");
                //resultadoTexto.setText(resultadoTexto.getText().toString() + "3");
                numeroPulsado += "3";
            }
        });
        boton4.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                comprobarBorrado();
                textoOperacion.setText(textoOperacion.getText().toString() + "4");
                //resultadoTexto.setText(resultadoTexto.getText().toString() + "4");
                numeroPulsado += "4";
            }
        });
        boton5.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                comprobarBorrado();
                textoOperacion.setText(textoOperacion.getText().toString() + "5");
                //resultadoTexto.setText(resultadoTexto.getText().toString() + "5");
                numeroPulsado += "5";
            }
        });
        boton6.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                comprobarBorrado();
                textoOperacion.setText(textoOperacion.getText().toString() + "6");
                // resultadoTexto.setText(resultadoTexto.getText().toString() + "6");
                numeroPulsado += "6";
            }
        });
        boton7.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                comprobarBorrado();
                textoOperacion.setText(textoOperacion.getText().toString() + "7");
                // resultadoTexto.setText(resultadoTexto.getText().toString() + "7");
                numeroPulsado += "7";
            }
        });
        boton8.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                comprobarBorrado();
                textoOperacion.setText(textoOperacion.getText().toString() + "8");
                //resultadoTexto.setText(resultadoTexto.getText().toString() + "8");
                numeroPulsado += "8";
            }
        });
        boton9.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                comprobarBorrado();
                textoOperacion.setText(textoOperacion.getText().toString() + "9");
                // resultadoTexto.setText(resultadoTexto.getText().toString() + "9");
                numeroPulsado += "9";
            }
        });
        boton0.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                comprobarBorrado();
                textoOperacion.setText(textoOperacion.getText().toString() + "0");
                //  resultadoTexto.setText(resultadoTexto.getText().toString() + "0");
                numeroPulsado += "0";
            }
        });
        botonPunto.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (comprobarUnPunto()) {
                    comprobarBorrado();
                    textoOperacion.setText(textoOperacion.getText().toString() + ".");
                    // resultadoTexto.setText(resultadoTexto.getText().toString() + ".");
                    numeroPulsado += ".";
                }
            }
        });
        botonC.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                resultadoNumero = 0;
                numeroPulsado = "0";
                resultadoTexto.setText("");
                textoOperacion.setText("");
                activarFuncionesCalculadora();
            }
        });
        botonSumar.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (comprobarNumero() && comprobarOperacion()) {
                    vacio();
                    calcular();
                    resultadoTexto.setText(String.valueOf(resultadoNumero));
                    numeroPulsado = "0";
                    textoOperacion.setText(textoOperacion.getText().toString() + "+");
                    borrar = false;
                    sumar = true;
                }
            }
        });
        botonRestar.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (comprobarNumero() && comprobarOperacion()) {
                    vacio();
                    calcular();
                    resultadoTexto.setText(String.valueOf(resultadoNumero));
                    numeroPulsado = "0";
                    textoOperacion.setText(textoOperacion.getText().toString() + "-");
                    borrar = false;
                    restar = true;
                }
            }
        });
        botonMultiplicar.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (comprobarNumero() && comprobarOperacion()) {
                    vacio();
                    calcular();
                    resultadoTexto.setText(String.valueOf(resultadoNumero));
                    numeroPulsado = "0";
                    textoOperacion.setText(textoOperacion.getText().toString() + "*");
                    borrar = false;
                    multiplicar = true;
                }
            }
        });
        botonDividir.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (comprobarNumero() && comprobarOperacion()) {
                    vacio();
                    calcular();
                    resultadoTexto.setText(String.valueOf(resultadoNumero));
                    numeroPulsado = "0";
                    textoOperacion.setText(textoOperacion.getText().toString() + "/");
                    borrar = false;
                    dividir = true;
                }
            }
        });
        botonIgual.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (comprobarNumero()) {
                    calcular();
                    resultadoTexto.setText(String.valueOf(resultadoNumero));
                    textoOperacion.setText("");
                    numeroPulsado = "0";
                    borrar = true;
                }
            }
        });
        botonEstado.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                String numAux;
                if (comprobarNumero())
                    if (textoOperacion.getText().length() > 0) {
                        if (numeroPulsado.charAt(0) == '-') {
                            textoOperacion.setText(textoOperacion.getText().toString().substring(0, (textoOperacion.getText().length() - 1) - (numeroPulsado.length() -1)));
                            numeroPulsado = numeroPulsado.substring(1);
                            numeroPulsado = "0"+numeroPulsado;
                            textoOperacion.setText(textoOperacion.getText().toString() + numeroPulsado.substring(1));
                        } else {
                            textoOperacion.setText(textoOperacion.getText().toString().substring(0, (textoOperacion.getText().length() -1) - (numeroPulsado.length() -2)));
                            numAux = numeroPulsado;
                            numeroPulsado="-"+numeroPulsado.substring(1);
                            textoOperacion.setText(textoOperacion.getText().toString() +  numeroPulsado);
                        }
                    }
            }
        });
    }

    private boolean comprobarOperacion() {
        if (textoOperacion.getText().length() > 1)
            if (textoOperacion.getText().charAt(textoOperacion.getText().length() - 1) == '+' || textoOperacion.getText().charAt(textoOperacion.getText().length() - 1) == '-'
                    || textoOperacion.getText().charAt(textoOperacion.getText().length() - 1) == '*' || textoOperacion.getText().charAt(textoOperacion.getText().length() - 1) == '/')
                return false;
        return true;
    }

    private void comprobarBorrado() {
        if (borrar) {
            resultadoTexto.setText("");
            resultadoNumero = 0;
            activarFuncionesCalculadora();
            numeroPulsado = "0";
            borrar = false;
        }
    }

    private boolean comprobarNumero() {
        return numeroPulsado.toString().matches("(-)?[0-9].*");
    }

    private void vacio() {
        if (textoOperacion.getText().length() == 0)
            textoOperacion.setText(String.valueOf(resultadoNumero));
    }

    private boolean comprobarUnPunto() {
        int contadorPunto = 0;
        for (int i = 0; i < numeroPulsado.length(); i++)
            if (numeroPulsado.charAt(i) == '.')
                return false;
        if (numeroPulsado.length() == 0)
            return false;
        return true;
    }

    private void iniciarFuncionesCalculadora() {
        sumar = false;
        restar = false;
        multiplicar = false;
        dividir = false;
    }

    private void activarFuncionesCalculadora() {
        sumar = true;
        restar = true;
        multiplicar = true;
        dividir = true;
    }

    private void calcular() {
        if (sumar)
            resultadoNumero += Double.parseDouble(numeroPulsado.toString());
        else if (restar)
            resultadoNumero = resultadoNumero - Double.parseDouble(numeroPulsado.toString());
        else if (multiplicar)
            resultadoNumero = resultadoNumero * Double.parseDouble(numeroPulsado.toString());
        else if (dividir)
            resultadoNumero = resultadoNumero / Double.parseDouble(numeroPulsado.toString());
        iniciarFuncionesCalculadora();
    }
}
