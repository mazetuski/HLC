package maze.huerto;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

public class DetalleActivity extends AppCompatActivity {

	private String nombre;
	private String descripcion;
	private String descripcionLarga;
	private int imagenRuta;
	private TextView textoNombre, textoDesc, textoDescLarga;
	private ImageView imagen;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_detalle);

		FragmentDetalle pantalla = (FragmentDetalle) getSupportFragmentManager().findFragmentById(R.id.FrgDetalle);

		final Bundle b = this.getIntent().getExtras();
		nombre = b.getString("NOMBRE");
		descripcion = b.getString("DESCRIPCION");
		descripcionLarga = b.getString("DESCRIPCIONLARGA");
		imagenRuta = b.getInt("IMAGEN");

		pantalla.mostrar(nombre, descripcion, descripcionLarga, imagenRuta);

        /*textoNombre = (TextView) findViewById(R.id.titulo);
        textoDesc = (TextView) findViewById(R.id.textoitem);
        textoDescLarga = (TextView) findViewById(R.id.descripcionitem);
        imagen = (ImageView) findViewById(R.id.imagenitem);

        textoNombre.setText(nombre);
        textoDesc.setText(descripcion);
        textoDescLarga.setText(descripcionLarga);
        imagen.setImageResource(imagenRuta);*/
	}
}
